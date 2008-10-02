<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html:xhtml/>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/fenix-renderers.tld" prefix="fr" %>

<bean:define id="type" name="enrolmentBean" property="groupType"/>
<bean:define id="actionName" name="actionName" />

<em><bean:message key="label.academicAdminOffice" bundle="ACADEMIC_OFFICE_RESOURCES"/></em>
<h2><strong><bean:message key="label.course.enrolments" bundle="ACADEMIC_OFFICE_RESOURCES"/> <bean:message key="<%= type.toString() %>" bundle="ACADEMIC_OFFICE_RESOURCES"/></strong></h2>
<br/>


<fr:form action='<%= "/" + actionName + ".do" %>'>
	<fr:edit id="enrolmentBean"
			 name="enrolmentBean"
			 schema="studentOptionalEnrolment.base">
		<fr:destination name="postBack" path='<%= "/" + actionName + ".do?method=postBack" %>' />
		<fr:layout name="tabular" >
				<fr:property name="classes" value="tstyle4"/>
		        <fr:property name="columnClasses" value="listClasses,,"/>
		</fr:layout>
	</fr:edit>
</fr:form>

<logic:notPresent name="enrolmentBean" property="degreeCurricularPlan">
	<fr:form action='<%= "/" + actionName + ".do?method=back2" %>'>
		<fr:edit id="enrolmentBean" name="enrolmentBean" visible="false" />
		<html:submit><bean:message key="back" bundle="ACADEMIC_OFFICE_RESOURCES"/></html:submit>
	</fr:form>
</logic:notPresent>

<html:messages id="message" message="true" bundle="ACADEMIC_OFFICE_RESOURCES">
	<br/>
	<span class="error"><!-- Error messages go here --><bean:write name="message" /></span>
	<br/>
</html:messages>

<br />
<br />

<logic:present name="enrolmentBean" property="degreeCurricularPlan">
	<fr:form action='<%= "/" + actionName + ".do?method=back2" %>'>
		<fr:edit id="enrolmentBean" name="enrolmentBean" visible="false" />

		<fr:edit id="degreeCurricularPlan" name="enrolmentBean">
			<fr:layout name="student-optional-enrolments">
				<fr:property name="linkFormat" value='<%= "/" + actionName + ".do?method=enrol&amp;scpID=${studentCurricularPlan.idInternal}&amp;executionPeriodID=${executionPeriod.idInternal}&amp;degreeType=${degreeType}&amp;degreeID=${degree.idInternal}&amp;dcpID=${degreeCurricularPlan.idInternal}&amp;type=${groupType}" %>' />
			</fr:layout>
		</fr:edit>
		<br/>
		<br/>		
		<html:submit><bean:message key="back" bundle="ACADEMIC_OFFICE_RESOURCES"/></html:submit>
	</fr:form>
</logic:present>
