<%
    fr.cg95.cvq.generator.common.CommonStep.metaClass.i18nPrefix = {
        return "request"
    }

    fr.cg95.cvq.generator.common.CustomStep.metaClass.i18nPrefix = {
        return requestFo.acronym
    }
%>
<html>
  <head>
     <title>\${message(code:'${requestFo.acronym}.description')}</title>
    <meta name="layout" content="fo_main" />
    <link rel="stylesheet" type="text/css" href="\${resource(dir:'css/frontoffice', file:'request.css')}" />
    <script type="text/javascript" src="\${resource(dir:'js/frontoffice',file:'requestCreation.js')}"></script>
    <script type="text/javascript" src="\${resource(dir:'js/frontoffice',file:'condition.js')}"></script>
    <script type="text/javascript" src="\${resource(dir:'js/frontoffice',file:'autofill.js')}"></script>
    <g:if test="\${customJS}">
      <script type="text/javascript" src="\${resource(dir:customJS.dir,file:customJS.file)}"></script>
    </g:if>
  </head>  
  <body>
    <g:set var="requestTypeInfo">
      {"label": "\${requestTypeLabel}"
        ,"steps": [ <% requestFo.steps.eachWithIndex { step, i -> %> "${step.name}${step.required ? '-required' : ''}"${i < requestFo.steps.size() -1 ? ',': ''} <% } %> ]
      }
    </g:set>
    <g:set var="requestTypeInfo" value="\${requestTypeInfo.encodeAsHTML()}" scope="request" />
    <form action="\${createLink(controller:'frontofficeRequestCreation',action:'condition')}"
      method="post" id="conditionsForm">
      <input type="hidden" id="conditionsContainer" name="conditionsContainer" value="" />
      <input type="hidden" name="requestTypeLabel" value="\${requestTypeLabel}" />
    </form>
    <form action="\${createLink(controller:'frontofficeRequestCreation',action:'autofill')}"
      method="post" id="autofillForm">
      <input type="hidden" id="autofillContainer" name="autofillContainer" value="" />
      <input type="hidden" id="triggerName" name="triggerName" value="" />
      <input type="hidden" id="triggerValue" name="triggerValue" value="" />
    </form>
    <g:if test="\${flash.isOutOfAccountRequest}">
      <g:render template="/frontofficeRequestType/loginPanel" />
    </g:if>
    <g:if test="\${flash.confirmationMessage}">
      <div class="information-box">
      	<p>\${flash.confirmationMessage}</p>
      	<g:if test="\${flash.confirmationMessageNotice}">
      	  <strong>\${flash.confirmationMessageNotice}</strong>
      	</g:if>
      </div>
    </g:if>
    <g:if test="\${flash.errorMessage}">
      <div class="error-box">
        <p>\${flash.errorMessage}</p>
        <g:if test="\${flash.errorMessageNotice}">
          <strong>\${flash.errorMessageNotice}</strong>
        </g:if>
      </div>
    </g:if>
    
    <g:render template="/frontofficeRequestType/cancelPanel" />
    <g:if test="\${session.currentEcitizen && !isEdition}">
      <g:render template="/frontofficeRequestType/draftPanel" />
    </g:if>
    
    <g:set var="requestTypeInfo" value="\${requestTypeInfo.encodeAsHTML()}" />
    
    <h2 class="request-creation"> <g:message code="${requestFo.acronym}.label" /></h2>
    <p><g:message code="${requestFo.acronym}.description" /></p> 
    <p><g:message code="request.duration.label" /><strong> : <g:message code="${requestFo.acronym}.duration.value" /></strong></p>
    <p>
      <g:message code="request.requiredDocuments.header" /> :
      <g:if test="\${!documentTypes.isEmpty()}">
        <g:each in="\${documentTypes}" var="documentType" status="index">
          <strong>\${documentType.value.name}<g:if test="\${index < documentTypes.size() - 1}">,</g:if></strong>
        </g:each>
      </g:if>
      <g:else>
        <g:message code="message.none" />
      </g:else>
    </p>

    <div id="requestTabView" class="yui-navset">
      <ul class="yui-nav">
<% requestFo.steps.eachWithIndex { step, i -> %>
  <% if (step.name == 'document') { %>
        <g:if test="\${!documentTypes.isEmpty()}">
  <% } %>
  <% if (i == 0) { %>
        <li class="\${['${step.name}', 'firstStep'].contains(currentStep) ? 'selected' : ''}">
  <% } else {%>  
        <li class="\${currentStep == '${step.name}' ? 'selected' : ''}">
  <% } %>
          <a href="#${step.name}"><em>
          <span class="tag-state \${stepStates!= null ? stepStates.${step.name}.cssClass : 'tag-pending'}"><g:message code="\${stepStates != null ? stepStates.${step.name}.i18nKey : 'request.step.state.uncomplete'}" /></span>
    <% if (step.required) { %>
          <strong>
            <g:message code="${step.i18nPrefix()}.step.${step.name}.label" /> *
          </strong>
    <% } else {%>
          <g:message code="${step.i18nPrefix()}.step.${step.name}.label" />
    <% } %>        
          </em></a>
        </li>    
  <% if (step.name == 'document') { %>
        </g:if>
  <% } %>
<% } %>
		 </ul>
		 
     <div class="yui-content">
<% requestFo.steps.each { step -> %>
  <% if (step.name == 'document') { %>
        <g:if test="\${!documentTypes.isEmpty()}">
  <% } %>
       <div id="${step.name}">
         <form method="post" ${step.name == 'document' ? 'enctype=\"multipart/form-data\"' : ''} id="stepForm-${step.camelCaseName}" action="<g:createLink action="step" />">
           <input type="hidden" name="returnUrl" value="\${returnUrl}" />
           <h3>
             <span class="tag-state \${stepStates!= null ? stepStates.${step.name}.cssClass : 'tag-pending'}"><g:message code="\${stepStates != null ? stepStates.${step.name}.i18nKey : 'request.step.state.uncomplete'}" /></span>
  <% if (step.required) { %>
             <span class="tag-state tag-required"><g:message code="request.step.required" /></span>
  <% } %>
             <g:message code="${step.i18nPrefix()}.step.${step.name}.label" />
             <span><g:message code="${step.i18nPrefix()}.step.${step.name}.desc" /></span>
             <span class="error">\${stepStates?.${step.name}?.errorMsg}</span>
           </h3>
           <p class="required-fields-notice"><g:message code="request.message.requiredFieldsNotice"/></p>
           <div>
  <% if (step.name == 'validation') { %>
             <g:if test="\${meansOfContact.size() > 0}">
              <label for="meansOfContact" class="required">
               <g:message code="request.meansOfContact.chooseMessage"/> *
              </label>
              <select id="meansOfContact" name="meansOfContact" class="required \${stepStates != null && stepStates['validation']?.invalidFields?.contains('meansOfContact') ? 'validation-failed' : ''}">
               <g:each in="\${meansOfContact}" var="moc">
                 <option value="\${moc.key}" <g:if test="\${rqt.meansOfContact?.type == moc.key}">selected="selected"</g:if>>\${moc.label}</option>
               </g:each>
              </select>
             </g:if>
             <g:else>
               <p>\${message(code:'request.meansOfContact.message.notAvailable')}</p>
             </g:else>
             <div class="summary-box">
    <% requestFo.stepBundles.eachWithIndex { stepBundle, index -> %>
            <g:render template="/frontofficeRequestType/${requestFo.camelCaseName + '/'}${step.name}${index}" />
    <% } %>
            </div>
            <h3><g:message code="request.step.note.label" /></h3>
            <label for="requestNote"><g:message code="request.step.note.desc" /></label>
            <textarea id="requestNote" name="requestNote" rows="" cols="">\${params.requestNote}</textarea>
            <label><span id="requestNoteLimit"></span></label>
            <h3><g:message code="request.step.${step.name}.label" /></h3>
            <g:if test="\${!hasHomeFolder}">
              <g:render template="/frontofficeRequestType/outOfAccountValidation" />
            </g:if>
            <div id="useAcceptance" class="\${stepStates != null && stepStates['validation']?.invalidFields?.contains('useAcceptance') ? 'validation-failed' : ''}">
             <input type="checkbox" name="useAcceptance" class="required validate-one-required"
                    title="\${message(code:'request.error.useAcceptanceRequired')}" />
             <a href="\${createLink(controller:'localAuthorityResource',action:'resource',id:'use')}" target="blank">
               <g:message code="request.step.validation.useAcceptance"/>
             </a>
           </div>
  <% } else { %>
            <g:render template="/frontofficeRequestType/${step.name != 'document' ? requestFo.camelCaseName + '/' : ''}${step.name}" />         
  <% } %>
           </div>
           <div class="error" id="stepForm-${step.camelCaseName}-error"> </div>
           <!-- Input submit-->
           <input type="hidden" name="requestTypeInfo" value="\${requestTypeInfo}" />
           <input type="hidden" name="uuidString" value="\${uuidString}" />
  <% if (step.name == 'validation') { %>
           <g:if test="\${missingSteps == null}">
             <div><strong><g:message code="request.step.validation.allRequiredSteps"/></strong></div>
           </g:if>
           <g:elseif test="\${missingSteps.size() > 0}">
             <div>
               <strong><g:message code="request.step.validation.requiredSteps"/></strong>
               <ul>
                 <g:each var="missingStep" in="\${missingSteps}">
                   <li>
                     <a id="active-tab-\${missingStep}" href="#\${missingStep}">
                       <g:message code="${requestFo.acronym}.step.\${missingStep}.label" />
                     </a>
                   </li>
                 </g:each>
               </ul>
             </div>
           </g:elseif>
           <input type="submit" id="submit-step-${step.camelCaseName}" name="submit-step-${step.camelCaseName}" class="submit-step" value="\${message(code:'action.send')}" \${missingSteps == null || missingSteps.size() > 0 ? 'disabled=\"disabled\"': ''}/>
  <% } else if (step.name != 'document') { %>
           <input type="submit" id="submit-step-${step.camelCaseName}" name="submit-step-${step.camelCaseName}" class="submit-step" value="\${message(code:'action.validate')}" />
  <% } %>
         </form>
         
         <g:if test="\${helps.${step.name} != null}">       
         <div class="requestHelp">
           <h3><g:message code="header.help"/></h3>
           \${helps.${step.name}}
         </div>
         </g:if>
       </div>  
  <% if (step.name == 'document') { %>
        </g:if>
  <% } %>
<% } %>        
 	    </div><!-- end yui-content -->
    </div><!-- end requestTabView -->
  
  </body>
</html>
