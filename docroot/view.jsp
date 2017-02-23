<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="com.liferay.portal.kernel.util.Validator"%>
<%@ page import="javax.portlet.PortletPreferences"%>
<%@ page import="com.liferay.util.PwdGenerator"%>

<portlet:defineObjects />

<%
String uploadProgressId = PwdGenerator.getPassword(PwdGenerator.KEY3, 4);
    PortletPreferences prefs = renderRequest.getPreferences();
%>

<style>
      .hidden { display: none; }
      .unhidden { display: block; }
</style>

<script type="text/javascript">
		
        function showSudo() {
             var item = document.getElementById('sudo_file');
             if (item) {
                 item.className='unhidden';
             }
         }

         function hideSudo() {
             var item = document.getElementById('sudo_file');
             if (item) {
                item.className='hidden';
             }
         }
</script>
<portlet:actionURL var="editCaseURL" name="uploadCase">
    <portlet:param name="jspPage" value="/edit.jsp" />
</portlet:actionURL>


<portlet:renderURL var="outputsURL">
    <portlet:param name="jspPage" value="/confirmation.jsp" />
</portlet:renderURL>

<table style="
    display: table;
    border-collapse: separate;
    border-spacing: 25px;
    border-color: gray;">
  <tr>
    <td><img width="100%" src="<%= request.getContextPath()%>/image/quantum.jpg" /></td>
    <td><b>Quantum Espresso is an integrated suite of Open-Source computer codes for electronic-structure calculations and materials modeling at the nanoscale. It is based on density-functional theory, plane waves, and pseudopotentials [http://www.quantum-espresso.org/].</b>
<br>Choose one operation and Upload your file/s using the link below and click submit. You should get an email when the task finishes.
You can check your output by using the <a href="<%= outputsURL %>">See Outputs</a> link.
</td>
  </tr>
</table>


<liferay-ui:upload-progress
    	id="<%= uploadProgressId %>"
    	message="uploading"
        redirect="<%= editCaseURL %>"
  		/>

<aui:form style="position:relative; left: 10%;" action="<%= editCaseURL %>" enctype="multipart/form-data" method="post" >
	<aui:field-wrapper> 
		
		<liferay-ui:message key="Please select one function." />
		<table>
			<tr>
				<td style="width:40%">
				
					<aui:input name="calculationType" type="radio" value="pw.x" label="Plane-Wave Self-Consistent Field Calculation (pw.x)" onClick="showSudo()"/>
					<aui:input name="calculationType" type="radio" value="cp.x" label="Phonon Frequencies Calculation (ph.x)" onClick="showSudo()"/>
					<aui:input name="calculationType" type="radio" value="d3.x" label="Phonon-Phonon Interaction Calculation (d3.x)" onClick="showSudo()"/>
					<aui:input name="calculationType" type="radio" value="neb.x" label="Energy Barriers and Reaction Pathway Calculation using the Nudged Elastic Band (NEB) Method (neb.x)" onClick="showSudo()"/> 
					<aui:input name="calculationType" type="radio" value="pwcond.x" label="Complex Band Structure Calculation (pwcond.x)" onClick="showSudo()"/>
					<aui:input name="calculationType" type="radio" value="cp.x" label="Car-Parrinello Molecular Dynamics Calculation (cp.x)" onClick="showSudo()"/>
					<aui:input name="calculationType" type="radio" value="turbo_davidson.x" label="Davidson Turbo TDFT Calculation (turbo_davidson.x)" onClick="showSudo()"/>
					<aui:input name="calculationType" type="radio" value="turbo_lanczos.x" label="Lanczos Turbo TDFT Calculation (turbo_lanczos.x)" onClick="showSudo()"/>
					<aui:input name="calculationType" type="radio" value="turbo_spectrum.x" label="Turbo TDFT Calculation (turbo_spectrum.x)" onClick="showSudo()"/>
					<aui:input name="calculationType" type="radio" value="xspectra.x" label="X-ray Spectra Calculation (xspectra.x)" onClick="showSudo()"/>
				
				</td>
				<td>
				
					<aui:input name="calculationType" type="radio" value="pp.x" label="Data Analysis and Plotting (pp.x)" onClick="hideSudo()"/>
					<aui:input name="calculationType" type="radio" value="dos.x" label="Density of States Calculation (dos.x)" onClick="hideSudo()"/>
					<aui:input name="calculationType" type="radio" value="bands.x" label="Band-related Properties Calculation (bands.x)" onClick="hideSudo()"/>
					<aui:input name="calculationType" type="radio" value="projwfc.x" label="Wavefunction Manipulation (projwfc.x)" onClick="hideSudo()"/>
					<aui:input name="calculationType" type="radio" value="cppp.x" label="Car-Parrinello Post-processing (cppp.x)" onClick="hideSudo()"/>
					<aui:input name="calculationType" type="radio" value="pw_export.x" label="PWSCF Data Post-processing (pw_export.x)" onClick="hideSudo()"/>
				
				</td>
			</tr>
		</table>
	
	<br/><br/>
		<aui:input type="file" name="inputFile" size="75"/>
		
		<div class="unhidden" id="sudo_file">
			<aui:input type="file" name="sudoFile" size="75"/>
		</div>
	</aui:field-wrapper> 
	
	<input type="submit" value="<liferay-ui:message key="submit" />" onClick="<%= uploadProgressId %>.startProgress(); return true;"/>
</aui:form>
<p><a href="/my-jobs" style="float:right">&rarr;See Outputs</a></p>