<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="org.apache.struts.action.Action" %>
<%@ page import="ServidorApresentacao.Action.sop.utils.SessionConstants" %>
<%@ page import="DataBeans.InfoGuideSituation" %>
<%@ page import="Util.State" %>
<%@ page import="Util.SituationOfGuide" %>

     <bean:define id="infoGuide" name="<%= SessionConstants.GUIDE %>" scope="request" />
     <bean:define id="number" name="infoGuide" property="number" />
     <bean:define id="year" name="infoGuide" property="year" />
     <bean:define id="version" name="infoGuide" property="version" />

	 <strong>
	 <bean:message key="label.masterDegree.administrativeOffice.guideInformation" 
				   arg0='<%= pageContext.findAttribute("version").toString() %>'
				   arg1='<%= pageContext.findAttribute("number").toString() %>' 
				   arg2='<%= pageContext.findAttribute("year").toString() %>' 
	  />
	 </strong>
	 	
     <table>
     

          <tr>
            <td><bean:message key="label.person.name" /></td>
            <td><bean:write name="infoGuide" property="infoPerson.nome"/></td>
          </tr>

          <tr>
            <td> <bean:message key="label.masterDegree.administrativeOffice.degree"/> </td>
            <td> <bean:write name="guide" property="infoExecutionDegree.infoDegreeCurricularPlan.infoDegree.nome"/> </td>
          </tr>

          <tr> 
            <td><strong>Entidade Pagadora:</strong></td>
            <td>&nbsp;</td>
          </tr>
          <tr> 
            <td><bean:message key="label.masterDegree.administrativeOffice.contributorNumber"/></td>
            <td><bean:write name="guide" property="infoContributor.contributorNumber"/></td>
          </tr>
          <tr> 
            <td><bean:message key="label.masterDegree.administrativeOffice.contributorName"/></td>
            <td><bean:write name="guide" property="infoContributor.contributorName"/></td>
          </tr>
          <tr> 
            <td><bean:message key="label.masterDegree.administrativeOffice.contributorAddress"/></td>
            <td><bean:write name="guide" property="infoContributor.contributorAddress"/></td>
          </tr>

	</table>
	<br>
	<br>
	<table>
		<tr align="center">
			<td><bean:message key="label.masterDegree.administrativeOffice.documentType" /></td>
			<td><bean:message key="label.masterDegree.administrativeOffice.description" /></td>
			<td><bean:message key="label.masterDegree.administrativeOffice.quantity" /></td>
			<td><bean:message key="label.masterDegree.administrativeOffice.price" /></td>
		</tr>

         <logic:iterate id="guideEntry" name="infoGuide" property="infoGuideEntries">
           <tr>
            <td><bean:write name="guideEntry" property="documentType"/></td>
            <td><bean:write name="guideEntry" property="description"/></td>
            <td><bean:write name="guideEntry" property="quantity"/></td>
            <td align="right"><bean:write name="guideEntry" property="price"/> <bean:message key="label.currencySymbol" /></td>
		   </tr>
         </logic:iterate>
         
         <tr>
         	<td></td>
         	<td></td>
         	<td><bean:message key="label.masterDegree.administrativeOffice.guideTotal" />:</td>
         	<td align="right"><bean:write name="infoGuide" property="total"/> <bean:message key="label.currencySymbol" /></td>
         </tr>
     </table>
     
     <br>
     <br>
     <table>
		<tr>
			<td><bean:message key="label.masterDegree.administrativeOffice.creationDate" /></td>
			<td><bean:write name="infoGuide" property="creationDate"/></td>
		</tr>
		<tr>
			<td><bean:message key="label.masterDegree.administrativeOffice.remarks" /></td>
			<td><bean:write name="infoGuide" property="remarks"/></td>
		</tr>
     </table>

	<br>
	<br>

	<strong><bean:message key="label.masterDegree.administrativeOffice.guideSituationList" /></strong>
	
	<br>
	<br>


     <table>

         <logic:iterate id="guideSituation" name="infoGuide" property="infoGuideSituations">
            <% if (((InfoGuideSituation) guideSituation).getState().equals(new State(State.ACTIVE))) { %>
	            
				<tr>
         	    <td><p><b><center><bean:message key="label.masterDegree.administrativeOffice.activeSituation" /></center></b></p></td>
				</tr>
         	<% } %>
           <tr>
			<td><bean:message key="label.masterDegree.administrativeOffice.remarks" /></td>
            <td><bean:write name="guideSituation" property="remarks"/></td>
           </tr>
           <tr>
			<td><bean:message key="label.masterDegree.administrativeOffice.situation" /></td>
            <td><bean:write name="guideSituation" property="situation"/></td>
           </tr>
           <tr>
			<td><bean:message key="label.masterDegree.administrativeOffice.situationDate" /></td>
            <td><bean:write name="guideSituation" property="date"/></td>
           </tr>
           <% if (((InfoGuideSituation) guideSituation).getSituation().equals(SituationOfGuide.PAYED_TYPE)) { %>
           		<tr>
        			<td><bean:message key="label.masterDegree.administrativeOffice.payment" /></td>
        			<td><bean:write name="infoGuide" property="paymentType"/></td>
        		</tr>
         	<% } %>
         </logic:iterate>



     </table>



