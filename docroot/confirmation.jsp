<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>

<portlet:defineObjects />

<portlet:renderURL var="outputsURL">
    <portlet:param name="jspPage" value="/my-jobs" />
</portlet:renderURL>

<portlet:renderURL var="viewURL">
    <portlet:param name="jspPage" value="/view.jsp" />
</portlet:renderURL>
    
<table style="
    width: 70%;
    ">
  <tr>
    <td><img src="<%= request.getContextPath()%>/image/thumb.png" width="60%" height="20%" /></td>
    <td><b>Input Successfully Submitted, You should receive an email as soon as the task finishes.
    You can check the outputs <a href="/my-jobs">here</a> </b>
</td>
  </tr>
</table>

<p><a href="<%= viewURL %>" style="float:right">&larr;Submit Another Input</a></p>