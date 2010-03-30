


  
    <fieldset class="required">
    <legend><g:message code="hccr.property.dwelling.label" /></legend>
    
      <label for="dwellingKind" class="required"><g:message code="hccr.property.dwellingKind.label" /> *  <span><g:message code="hccr.property.dwellingKind.help" /></span></label>
            <select id="dwellingKind" name="dwellingKind" class="required condition-isNotPlaceOfResidence-trigger  validate-not-first" title="<g:message code="hccr.property.dwellingKind.validationError" />">
              <option value=""><g:message code="message.select.defaultOption" /></option>
              <g:each in="${['PlaceOfResidence','ThirdPartyPlaceOfResidence','Other']}">
                <option value="fr.cg95.cvq.business.request.social.HccrDwellingKindType_${it}" ${it == rqt.dwellingKind?.toString() ? 'selected="selected"': ''}><g:capdematEnumToText var="${it}" i18nKeyPrefix="hccr.property.dwellingKind" /></option>
              </g:each>
            </select>
            

    
      <label for="dwellingPrecision" class="required condition-isNotPlaceOfResidence-filled"><g:message code="hccr.property.dwellingPrecision.label" /> *  <span><g:message code="hccr.property.dwellingPrecision.help" /></span></label>
            <textarea id="dwellingPrecision" name="dwellingPrecision" class="required condition-isNotPlaceOfResidence-filled  validate-textarea" title="<g:message code="hccr.property.dwellingPrecision.validationError" />" rows="2" cols=""  maxlength="120">${rqt.dwellingPrecision}</textarea>
            

    
      <label class="required"><g:message code="hccr.property.dwellingEstablishmentReception.label" /> *  <span><g:message code="hccr.property.dwellingEstablishmentReception.help" /></span></label>
            <ul class="yes-no required">
              <g:each in="${[true,false]}">
              <li>
                <input type="radio" id="dwellingEstablishmentReception_${it ? 'yes' : 'no'}" class="required condition-isInEstablishmentReception-trigger  validate-one-required boolean" title="" value="${it}" name="dwellingEstablishmentReception" ${it == rqt.dwellingEstablishmentReception ? 'checked="checked"': ''} />
                <label for="dwellingEstablishmentReception_${it ? 'yes' : 'no'}"><g:message code="message.${it ? 'yes' : 'no'}" /></label>
              </li>
              </g:each>
            </ul>
            

    
      <label for="dwellingReceptionType" class="required condition-isInEstablishmentReception-filled"><g:message code="hccr.property.dwellingReceptionType.label" /> *  <span><g:message code="hccr.property.dwellingReceptionType.help" /></span></label>
            <select id="dwellingReceptionType" name="dwellingReceptionType" class="required condition-isInEstablishmentReception-filled  validate-not-first" title="<g:message code="hccr.property.dwellingReceptionType.validationError" />">
              <option value=""><g:message code="message.select.defaultOption" /></option>
              <g:each in="${['Internship','Clerkship']}">
                <option value="fr.cg95.cvq.business.request.social.HccrDwellingReceptionKindType_${it}" ${it == rqt.dwellingReceptionType?.toString() ? 'selected="selected"': ''}><g:capdematEnumToText var="${it}" i18nKeyPrefix="hccr.property.dwellingReceptionType" /></option>
              </g:each>
            </select>
            

    
      <label for="dwellingReceptionNaming" class="required condition-isInEstablishmentReception-filled"><g:message code="hccr.property.dwellingReceptionNaming.label" /> *  <span><g:message code="hccr.property.dwellingReceptionNaming.help" /></span></label>
            <input type="text" id="dwellingReceptionNaming" name="dwellingReceptionNaming" value="${rqt.dwellingReceptionNaming?.toString()}" 
                    class="required condition-isInEstablishmentReception-filled  " title="<g:message code="hccr.property.dwellingReceptionNaming.validationError" />"  maxlength="80" />
            

    
      <label class="required condition-isInEstablishmentReception-filled"><g:message code="hccr.property.dwellingReceptionAddress.label" /> *  <span><g:message code="hccr.property.dwellingReceptionAddress.help" /></span></label>
            <div class="address-fieldset required condition-isInEstablishmentReception-filled ">
            <label for="dwellingReceptionAddress.additionalDeliveryInformation"><g:message code="address.property.additionalDeliveryInformation" /></label>
            <input type="text" value="${rqt.dwellingReceptionAddress?.additionalDeliveryInformation}" maxlength="38" id="dwellingReceptionAddress.additionalDeliveryInformation" name="dwellingReceptionAddress.additionalDeliveryInformation" />  
            <label for="dwellingReceptionAddress.additionalGeographicalInformation"><g:message code="address.property.additionalGeographicalInformation" /></label>
            <input type="text" value="${rqt.dwellingReceptionAddress?.additionalGeographicalInformation}" maxlength="38" id="dwellingReceptionAddress.additionalGeographicalInformation" name="dwellingReceptionAddress.additionalGeographicalInformation" />
            <label for="dwellingReceptionAddress.streetNumber"><g:message code="address.property.streetNumber" /></label> - 
            <label for="dwellingReceptionAddress.streetName" class="required"><g:message code="address.property.streetName" /> *</label><br />
            <input type="text" class="line1" value="${rqt.dwellingReceptionAddress?.streetNumber}" size="5" maxlength="5" id="dwellingReceptionAddress.streetNumber" name="dwellingReceptionAddress.streetNumber" />
            <input type="text" class="line2 required" value="${rqt.dwellingReceptionAddress?.streetName}" maxlength="32" id="dwellingReceptionAddress.streetName" name="dwellingReceptionAddress.streetName" title="<g:message code="address.property.streetName.validationError" />" />
            <label for="dwellingReceptionAddress.placeNameOrService"><g:message code="address.property.placeNameOrService" /></label>
            <input type="text" value="${rqt.dwellingReceptionAddress?.placeNameOrService}" maxlength="38" id="dwellingReceptionAddress.placeNameOrService" name="dwellingReceptionAddress.placeNameOrService" />
            <label for="dwellingReceptionAddress.postalCode" class="required"><g:message code="address.property.postalCode" /> * </label> - 
            <label for="dwellingReceptionAddress.city" class="required"><g:message code="address.property.city" /> *</label><br />
            <input type="text" class="line1 required" value="${rqt.dwellingReceptionAddress?.postalCode}" size="5" maxlength="5" id="dwellingReceptionAddress.postalCode" name="dwellingReceptionAddress.postalCode" title="<g:message code="address.property.postalCode.validationError" />" />
            <input type="text" class="line2 required" value="${rqt.dwellingReceptionAddress?.city}" maxlength="32" id="dwellingReceptionAddress.city" name="dwellingReceptionAddress.city" title="<g:message code="address.property.city.validationError" />" />
            <label for="dwellingReceptionAddress.countryName"><g:message code="address.property.countryName" /></label>
            <input type="text" value="${rqt.dwellingReceptionAddress?.countryName}" maxlength="38" id="dwellingReceptionAddress.countryName" name="dwellingReceptionAddress.countryName" />
            </div>
            

    
      <label class="required"><g:message code="hccr.property.dwellingSocialReception.label" /> *  <span><g:message code="hccr.property.dwellingSocialReception.help" /></span></label>
            <ul class="yes-no required">
              <g:each in="${[true,false]}">
              <li>
                <input type="radio" id="dwellingSocialReception_${it ? 'yes' : 'no'}" class="required condition-isInSocialReception-trigger  validate-one-required boolean" title="" value="${it}" name="dwellingSocialReception" ${it == rqt.dwellingSocialReception ? 'checked="checked"': ''} />
                <label for="dwellingSocialReception_${it ? 'yes' : 'no'}"><g:message code="message.${it ? 'yes' : 'no'}" /></label>
              </li>
              </g:each>
            </ul>
            

    
      <label for="dwellingSocialReceptionNaming" class="required condition-isInSocialReception-filled"><g:message code="hccr.property.dwellingSocialReceptionNaming.label" /> *  <span><g:message code="hccr.property.dwellingSocialReceptionNaming.help" /></span></label>
            <input type="text" id="dwellingSocialReceptionNaming" name="dwellingSocialReceptionNaming" value="${rqt.dwellingSocialReceptionNaming?.toString()}" 
                    class="required condition-isInSocialReception-filled  " title="<g:message code="hccr.property.dwellingSocialReceptionNaming.validationError" />"  maxlength="80" />
            

    
      <label class="required condition-isInSocialReception-filled"><g:message code="hccr.property.dwellingSocialReceptionAddress.label" /> *  <span><g:message code="hccr.property.dwellingSocialReceptionAddress.help" /></span></label>
            <div class="address-fieldset required condition-isInSocialReception-filled ">
            <label for="dwellingSocialReceptionAddress.additionalDeliveryInformation"><g:message code="address.property.additionalDeliveryInformation" /></label>
            <input type="text" value="${rqt.dwellingSocialReceptionAddress?.additionalDeliveryInformation}" maxlength="38" id="dwellingSocialReceptionAddress.additionalDeliveryInformation" name="dwellingSocialReceptionAddress.additionalDeliveryInformation" />  
            <label for="dwellingSocialReceptionAddress.additionalGeographicalInformation"><g:message code="address.property.additionalGeographicalInformation" /></label>
            <input type="text" value="${rqt.dwellingSocialReceptionAddress?.additionalGeographicalInformation}" maxlength="38" id="dwellingSocialReceptionAddress.additionalGeographicalInformation" name="dwellingSocialReceptionAddress.additionalGeographicalInformation" />
            <label for="dwellingSocialReceptionAddress.streetNumber"><g:message code="address.property.streetNumber" /></label> - 
            <label for="dwellingSocialReceptionAddress.streetName" class="required"><g:message code="address.property.streetName" /> *</label><br />
            <input type="text" class="line1" value="${rqt.dwellingSocialReceptionAddress?.streetNumber}" size="5" maxlength="5" id="dwellingSocialReceptionAddress.streetNumber" name="dwellingSocialReceptionAddress.streetNumber" />
            <input type="text" class="line2 required" value="${rqt.dwellingSocialReceptionAddress?.streetName}" maxlength="32" id="dwellingSocialReceptionAddress.streetName" name="dwellingSocialReceptionAddress.streetName" title="<g:message code="address.property.streetName.validationError" />" />
            <label for="dwellingSocialReceptionAddress.placeNameOrService"><g:message code="address.property.placeNameOrService" /></label>
            <input type="text" value="${rqt.dwellingSocialReceptionAddress?.placeNameOrService}" maxlength="38" id="dwellingSocialReceptionAddress.placeNameOrService" name="dwellingSocialReceptionAddress.placeNameOrService" />
            <label for="dwellingSocialReceptionAddress.postalCode" class="required"><g:message code="address.property.postalCode" /> * </label> - 
            <label for="dwellingSocialReceptionAddress.city" class="required"><g:message code="address.property.city" /> *</label><br />
            <input type="text" class="line1 required" value="${rqt.dwellingSocialReceptionAddress?.postalCode}" size="5" maxlength="5" id="dwellingSocialReceptionAddress.postalCode" name="dwellingSocialReceptionAddress.postalCode" title="<g:message code="address.property.postalCode.validationError" />" />
            <input type="text" class="line2 required" value="${rqt.dwellingSocialReceptionAddress?.city}" maxlength="32" id="dwellingSocialReceptionAddress.city" name="dwellingSocialReceptionAddress.city" title="<g:message code="address.property.city.validationError" />" />
            <label for="dwellingSocialReceptionAddress.countryName"><g:message code="address.property.countryName" /></label>
            <input type="text" value="${rqt.dwellingSocialReceptionAddress?.countryName}" maxlength="38" id="dwellingSocialReceptionAddress.countryName" name="dwellingSocialReceptionAddress.countryName" />
            </div>
            

    
    </fieldset>
  

