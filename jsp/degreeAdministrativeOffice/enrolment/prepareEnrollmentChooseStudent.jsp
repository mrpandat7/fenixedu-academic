<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<h2><bean:message key="title.student.LEEC.enrollment"/></h2>
<html:errors/>
<html:form action="/curricularCoursesEnrollment" focus="studentNumber">
	<html:hidden property="method" value="start"/>
	<html:hidden property="page" value="1"/>
	<html:hidden property="executionDegreeId" value="<%=pageContext.findAttribute("executionDegreeId").toString()%>"/>
	<table>
		<tr>
			<td><bean:message key="label.choose.student"/>&nbsp;</td>
			<td>
				<input type="text" name="studentNumber" size="5" maxlength="5"/>
			</td>
		</tr>
	</table>
	<br/>
	<br/>
	<html:submit styleClass="inputbutton">
		<bean:message key="button.submit.student"/>
	</html:submit>
</html:form>
