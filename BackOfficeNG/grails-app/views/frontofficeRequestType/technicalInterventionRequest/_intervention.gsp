


  
    <label class="required"><g:message code="tir.property.interventionType.label" /> *  <span><g:message code="tir.property.interventionType.help" /></span></label>
            <g:set var="interventionTypeIndex" value="${0}" scope="flash" />
            <g:render template="/frontofficeRequestType/widget/localReferentialData" 
                      model="['javaName':'interventionType', 'i18nPrefixCode':'tir.property.interventionType', 'htmlClass':'required condition-otherIntervention-trigger  ', 
                              'lrEntries': lrTypes.interventionType.entries, 'depth':0]" />
            

  

  
    <label for="otherInterventionLabel" class="required condition-otherIntervention-filled"><g:message code="tir.property.otherInterventionLabel.label" /> *  <span><g:message code="tir.property.otherInterventionLabel.help" /></span></label>
            <input type="text" id="otherInterventionLabel" name="otherInterventionLabel" value="${rqt.otherInterventionLabel?.toString()}" 
                    class="required condition-otherIntervention-filled  validate-string ${stepStates != null && stepStates['intervention']?.invalidFields?.contains('otherInterventionLabel') ? 'validation-failed' : ''}" title="<g:message code="tir.property.otherInterventionLabel.validationError" />"   />
            

  

  
    <label class="required"><g:message code="tir.property.interventionPlace.label" /> *  <span><g:message code="tir.property.interventionPlace.help" /></span></label>
            <div class="address-fieldset required  ${stepStates != null && stepStates['intervention']?.invalidFields?.contains('interventionPlace') ? 'validation-failed' : ''}">
            <label for="interventionPlace.additionalDeliveryInformation"><g:message code="address.property.additionalDeliveryInformation" /></label>
            <input type="text" class="validate-addressLine38 ${stepStates != null && stepStates['intervention']?.invalidFields?.contains('interventionPlace.additionalDeliveryInformation') ? 'validation-failed' : ''}" value="${rqt.interventionPlace?.additionalDeliveryInformation}" maxlength="38" id="interventionPlace.additionalDeliveryInformation" name="interventionPlace.additionalDeliveryInformation" />  
            <label for="interventionPlace.additionalGeographicalInformation"><g:message code="address.property.additionalGeographicalInformation" /></label>
            <input type="text" class="validate-addressLine38 ${stepStates != null && stepStates['intervention']?.invalidFields?.contains('interventionPlace.additionalGeographicalInformation') ? 'validation-failed' : ''}" value="${rqt.interventionPlace?.additionalGeographicalInformation}" maxlength="38" id="interventionPlace.additionalGeographicalInformation" name="interventionPlace.additionalGeographicalInformation" />
            <label for="interventionPlace.streetNumber"><g:message code="address.property.streetNumber" /></label> - 
            <label for="interventionPlace.streetName" class="required"><g:message code="address.property.streetName" /> *</label><br />
            <input type="text" class="line1 validate-streetNumber ${stepStates != null && stepStates['intervention']?.invalidFields?.contains('interventionPlace.streetNumber') ? 'validation-failed' : ''}" value="${rqt.interventionPlace?.streetNumber}" size="5" maxlength="5" id="interventionPlace.streetNumber" name="interventionPlace.streetNumber" />
            <input type="text" class="line2 required validate-streetName ${stepStates != null && stepStates['intervention']?.invalidFields?.contains('interventionPlace.streetName') ? 'validation-failed' : ''}" value="${rqt.interventionPlace?.streetName}" maxlength="32" id="interventionPlace.streetName" name="interventionPlace.streetName" title="<g:message code="address.property.streetName.validationError" />" />
            <label for="interventionPlace.placeNameOrService"><g:message code="address.property.placeNameOrService" /></label>
            <input type="text" class="validate-addressLine38 ${stepStates != null && stepStates['intervention']?.invalidFields?.contains('interventionPlace.placeNameOrService') ? 'validation-failed' : ''}" value="${rqt.interventionPlace?.placeNameOrService}" maxlength="38" id="interventionPlace.placeNameOrService" name="interventionPlace.placeNameOrService" />
            <label for="interventionPlace.postalCode" class="required"><g:message code="address.property.postalCode" /> * </label> - 
            <label for="interventionPlace.city" class="required"><g:message code="address.property.city" /> *</label><br />
            <input type="text" class="line1 required validate-postalCode ${stepStates != null && stepStates['intervention']?.invalidFields?.contains('interventionPlace.postalCode') ? 'validation-failed' : ''}" value="${rqt.interventionPlace?.postalCode}" size="5" maxlength="5" id="interventionPlace.postalCode" name="interventionPlace.postalCode" title="<g:message code="address.property.postalCode.validationError" />" />
            <input type="text" class="line2 required validate-city ${stepStates != null && stepStates['intervention']?.invalidFields?.contains('interventionPlace.city') ? 'validation-failed' : ''}" value="${rqt.interventionPlace?.city}" maxlength="32" id="interventionPlace.city" name="interventionPlace.city" title="<g:message code="address.property.city.validationError" />" />
            <label for="interventionPlace.countryName"><g:message code="address.property.countryName" /></label>
            <input type="text" class="validate-addressLine38 ${stepStates != null && stepStates['intervention']?.invalidFields?.contains('interventionPlace.countryName') ? 'validation-failed' : ''}" value="${rqt.interventionPlace?.countryName}" maxlength="38" id="interventionPlace.countryName" name="interventionPlace.countryName" />
            </div>
            

  

  
    <label for="interventionDescription" class="required"><g:message code="tir.property.interventionDescription.label" /> *  <span><g:message code="tir.property.interventionDescription.help" /></span></label>
            <textarea id="interventionDescription" name="interventionDescription" class="required  validate-textarea ${stepStates != null && stepStates['intervention']?.invalidFields?.contains('interventionDescription') ? 'validation-failed' : ''}" title="<g:message code="tir.property.interventionDescription.validationError" />" rows="3" cols=""  >${rqt.interventionDescription}</textarea>
            

  

