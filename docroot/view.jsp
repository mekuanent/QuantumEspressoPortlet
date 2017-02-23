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
		
	
	<aui:select name="calculationType">
	    <aui:option value="pw.x">Plane-Wave Self-Consistent Field Calculation (pw.x)</aui:option>
	    <aui:option value="ph.x">Phonon Frequencies Calculation (ph.x)</aui:option>
	    <aui:option value="d3.x">Phonon-Phonon Interaction Calculation (d3.x)</aui:option>
	    <aui:option value="neb.x">Energy Barriers and Reaction Pathway Calculation using the Nudged Elastic Band (NEB) Method (neb.x)</aui:option>
	    <aui:option value="pwcond.x">Complex Band Structure Calculation (pwcond.x)</aui:option>
	    <aui:option value="cp.x">Car-Parrinello Molecular Dynamics Calculation (cp.x)</aui:option>
	    <aui:option value="turbo_davidson.x">Davidson Turbo TDFT Calculation (turbo_davidson.x)</aui:option>
	    <aui:option value="turbo_lanczos.x">Lanczos Turbo TDFT Calculation (turbo_lanczos.x)</aui:option>
	    <aui:option value="turbo_spectrum.x">Turbo TDFT Calculation (turbo_spectrum.x)</aui:option>
	    <aui:option value="xspectra.x">X-ray Spectra Calculation (xspectra.x)</aui:option>
	    <aui:option value="pp.x">Data Analysis and Plotting (pp.x)</aui:option>
	    <aui:option value="dos.x">Density of States Calculation (dos.x)</aui:option>
	    <aui:option value="bands.x">Band-related Properties Calculation (bands.x)</aui:option>
	    <aui:option value="projwfc.x">Wavefunction Manipulation (projwfc.x)</aui:option>
	    <aui:option value="cppp.x">Car-Parrinello Post-processing (cppp.x)</aui:option>
	    <aui:option value="pw_export.x">PWSCF Data Post-processing (pw_export.x)</aui:option>
	</aui:select>
	
	<br/><br/>
	
		<liferay-ui:message key="Please Upload your input file usually <b>.in</b> extension" />
	
		<aui:input type="file" name="inputFile" size="75"/>
		
		<div class="unhidden" id="sudo_file">
			<br/>Please upload the related file to your input usually <b>".PSU"</b> extension, 
			<br><b>you can ignore this if you don't need it</b>
			<aui:input type="file" name="sudoFile" size="75"/>(OPTIONAL)
		</div>
	</aui:field-wrapper> 
	
	<input type="submit" value="<liferay-ui:message key="submit" />" onClick="<%= uploadProgressId %>.startProgress(); return true;"/>
</aui:form>
<p><a href="/my-jobs" style="float:right">&rarr;See Outputs</a></p>