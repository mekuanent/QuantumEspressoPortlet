package com.saigaia.quantum;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import retrofit.mime.TypedFile;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.saigaia.quantum.Connection;
import com.saigaia.quantum.vo.CreateTaskRequest;
import com.saigaia.quantum.vo.CreateTaskResponse;
import com.saigaia.quantum.vo.Input_files;
import com.saigaia.quantum.vo.Output_files;
import com.saigaia.quantum.vo.UploadFilesResponse;

/**
 * Portlet implementation class QuantumPortlet
 */
public class QuantumPortlet extends MVCPortlet {
 
	public void uploadCase(ActionRequest actionRequest,
			ActionResponse actionResponse) throws PortletException,
			IOException {
		
		ThemeDisplay td  =(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		final User user = td.getUser();
		
		final Log logger = LogFactoryUtil.getLog(QuantumPortlet.class);
		
		try {
			
			UploadPortletRequest uploadRequest = PortalUtil
					.getUploadPortletRequest(actionRequest);
			

			if (uploadRequest.getSize("inputFile")==0) {
				throw new Exception();
			}
			
			
			String inputFileName = user.getUserId()+ "" + System.currentTimeMillis() + uploadRequest.getFileName("inputFile");
			File newInputFile = new File(uploadRequest.getFile("inputFile").getParent() + "/" + inputFileName);
			processFile(uploadRequest, newInputFile, "inputFile");
			
			
			String sudoFileName = "";
			File newSudoFile = null;
			
			if(uploadRequest.getFileName("sudoFile") != null && !uploadRequest.getFileName("sudoFile").equals("")){
				sudoFileName = uploadRequest.getFileName("sudoFile");
				newSudoFile = new File(uploadRequest.getFile("sudoFile").getParent() + "/" + sudoFileName);
				processFile(uploadRequest, newSudoFile, "sudoFile");
			}else{
				sudoFileName = user.getUserId()+ "dummyFile.PSU";
				newSudoFile = new File(uploadRequest.getFile("inputFile").getParent() + "/" + sudoFileName);
				prepareDummyFile(newSudoFile);
			}
			
			logger.info("PARAM:::: " + uploadRequest.getParameter("calculationType"));
			logger.info("PARAM:::: " + user.getScreenName());
			
			logger.info("PARAM:::: " + uploadRequest.getFileName("sudoFile"));
			
			CreateTaskRequest ctr = new CreateTaskRequest();
			ctr.application = "4"; 
			ctr.arguments.add(uploadRequest.getParameter("calculationType"));
			ctr.arguments.add(user.getEmailAddress());
			ctr.arguments.add(inputFileName);
			
			ctr.input_files.add(new Input_files("", inputFileName, ""));
			ctr.input_files.add(new Input_files("", sudoFileName, ""));
			
			ctr.output_files.add(new Output_files("outputs.tar.gz",""));
			
			CreateTaskResponse ctResponse = Connection.apiService.createTask(user.getScreenName(), ctr);
			
			logger.info("TASK ID:::: " + ctResponse.getId());
			
			Map<String, TypedFile> files = new HashMap<String, TypedFile>();
			
			TypedFile typedInputFile = new TypedFile("multipart/form-data", newInputFile);
			TypedFile typedSudoFile = new TypedFile("multipart/form-data", newSudoFile);
			
			files.put("file[]", typedInputFile);
			
			UploadFilesResponse ufResponse = Connection.apiService.uploadFiles(ctResponse.getId(), user.getScreenName() + "", files);
			
			files.put("file[]", typedSudoFile);
			UploadFilesResponse ufResponse2 = Connection.apiService.uploadFiles(ctResponse.getId(), user.getScreenName() + "", files);
			
			logger.info("CALLER INFO: created task with id = " + ctResponse.getId());
			
			logger.info("CALLER INFO: upload task 1 finished with status = " + ufResponse.getGestatus());
			
			logger.info("CALLER INFO: upload task 2 finished with status = " + ufResponse2.getGestatus());
			

			String portletName = (String)actionRequest.getAttribute(WebKeys.PORTLET_ID);
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			
			
			PortletURL redirectURL = PortletURLFactoryUtil.create(PortalUtil.getHttpServletRequest(actionRequest), portletName, themeDisplay.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
			redirectURL.setParameter("jspPage", "/confirmation.jsp");
			
			SessionMessages.add(actionRequest, "success");
			actionResponse.sendRedirect(redirectURL.toString()); 

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found.");
			e.printStackTrace();
			SessionMessages.add(actionRequest, "error");
		} catch (NullPointerException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
			SessionMessages.add(actionRequest, "error");
		} catch (IOException e1) {
			System.out.println("Error Reading The File.");
			SessionMessages.add(actionRequest, "error");
			e1.printStackTrace();
		} catch (Exception e){
			System.out.println("General Error" + e.getMessage());
			SessionMessages.add(actionRequest, "error");
			e.printStackTrace();
		}
		
		
	}
	
	public void processFile(UploadPortletRequest uploadRequest, File file, String name) throws FileNotFoundException, IOException{
		
		if(name.equals("sudoFile")){
			uploadRequest.getFile(name).renameTo(file);
		}else{
			InputStream in = new BufferedInputStream(uploadRequest.getFileAsStream(name));

			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			String line = null;
			
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);;
			
			while((line = reader.readLine()) != null){
				
				if(line.contains("!") || (!line.contains("pseudo_dir") && !line.contains("outdir")))
					bw.write(line + "\n");
				else if(!line.contains("!") && line.contains("pseudo_dir")){
					bw.write("pseudo_dir='./'\n");
				}else if(!line.contains("!") && line.contains("outdir")){
					bw.write("outdir='./output'\n");
				}
			}
			
			bw.close();
		}
	}
	
	public void prepareDummyFile(File file) throws IOException{
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("This is a dummy placeholder file");
		bw.close();
	}

}
