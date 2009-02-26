




  
    <fieldset class="required">
    <legend><g:message code="hccr.property.dwelling.label" /></legend> 
      
    
      <label class="required"><g:message code="hccr.property.dwellingKind.label" /> <span><g:message code="hccr.property.dwellingKind.help" /></span></label>
      
            <select name="dwellingKind" class="required condition-isNotPlaceOfResidence-trigger validate-not-first" title="<g:message code="hccr.property.dwellingKind.validationError" />">
              <option value=""><g:message code="message.select.defaultOption" /></option>
              <g:each in="${['PlaceOfResidence','ThirdPartyPlaceOfResidence','Other']}">
                <option value="fr.cg95.cvq.business.request.social.HccrDwellingKindType_${it}" ${it == rqt.dwellingKind?.toString() ? 'selected="selected"': ''}><g:capdematEnumToField var="${it}" i18nKeyPrefix="hccr.property.dwellingKind" /></option>
              </g:each>
            </select>
            
    
      <label class="required condition-isNotPlaceOfResidence-filled"><g:message code="hccr.property.dwellingPrecision.label" /> <span><g:message code="hccr.property.dwellingPrecision.help" /></span></label>
      
            <input type="text" name="dwellingPrecision" value="${rqt.dwellingPrecision}" 
                    class="required condition-isNotPlaceOfResidence-filled validate-textarea" title="<g:message code="hccr.property.dwellingPrecision.validationError" />">
            
    
      <label class="required"><g:message code="hccr.property.dwellingEstablishmentReception.label" /> <span><g:message code="hccr.property.dwellingEstablishmentReception.help" /></span></label>
      
            <ul class="required">
              <g:each in="${[true,false]}">
              <li>
                <input type="radio" class="required condition-isInEstablishmentReception-trigger validate-boolean" title="" value="${it}" name="dwellingEstablishmentReception" ${it == rqt.dwellingEstablishmentReception ? 'checked="checked"': ''} />
                <g:message code="message.${it ? 'yes' : 'no'}" />
              </li>
              </g:each>
            </ul>
            
    
      <label class="required condition-isInEstablishmentReception-filled"><g:message code="hccr.property.dwellingReceptionType.label" /> <span><g:message code="hccr.property.dwellingReceptionType.help" /></span></label>
      
            <select name="dwellingReceptionType" class="required condition-isInEstablishmentReception-filled validate-not-first" title="<g:message code="hccr.property.dwellingReceptionType.validationError" />">
              <option value=""><g:message code="message.select.defaultOption" /></option>
              <g:each in="${['Internship','Clerkship']}">
                <option value="fr.cg95.cvq.business.request.social.HccrDwellingReceptionKindType_${it}" ${it == rqt.dwellingReceptionType?.toString() ? 'selected="selected"': ''}><g:capdematEnumToField var="${it}" i18nKeyPrefix="hccr.property.dwellingReceptionType" /></option>
              </g:each>
            </select>
            
    
      <label class="required condition-isInEstablishmentReception-filled"><g:message code="hccr.property.dwellingReceptionNaming.label" /> <span><g:message code="hccr.property.dwellingReceptionNaming.help" /></span></label>
      
            <input type="text" name="dwellingReceptionNaming" value="${rqt.dwellingReceptionNaming}" 
                    class="required condition-isInEstablishmentReception-filled " title="<g:message code="hccr.property.dwellingReceptionNaming.validationError" />">
            
    
      <label class="required condition-isInEstablishmentReception-filled"><g:message code="hccr.property.dwellingReceptionAddress.label" /> <span><g:message code="hccr.property.dwellingReceptionAddress.help" /></span></label>
      
            <div class="address-fieldset required condition-isInEstablishmentReception-filled">
            <label><g:message code="address.property.additionalDeliveryInformation" /></label>
            <input type="text" value="${rqt.dwellingReceptionAddress?.additionalDeliveryInformation}" maxlength="38" name="dwellingReceptionAddress.additionalDeliveryInformation"/>  
            <label><g:message code="address.property.additionalGeographicalInformation" /></label>
            <input type="text" value="${rqt.dwellingReceptionAddress?.additionalGeographicalInformation}" maxlength="38" name="dwellingReceptionAddress.additionalGeographicalInformation"/>
            <label><g:message code="address.property.streetNumber" /> - <strong><g:message code="address.property.streetName" /></strong></label>
            <input type="text" class="line1" value="${rqt.dwellingReceptionAddress?.streetNumber}" maxlength="5" name="dwellingReceptionAddress.streetNumber"/>
            <input type="text" class="line2 required" value="${rqt.dwellingReceptionAddress?.streetName}" maxlength="32" name="dwellingReceptionAddress.streetName" title="<g:message code="address.property.streetName.validationError" />" />
            <label><g:message code="address.property.placeNameOrService" /></label>
            <input type="text" value="${rqt.dwellingReceptionAddress?.placeNameOrService}" maxlength="38" name="dwellingReceptionAddress.placeNameOrService"/>
            <label class="required"><g:message code="address.property.postalCode" /> - <g:message code="address.property.city" /></label>
            <input type="text" class="line1 required" value="${rqt.dwellingReceptionAddress?.postalCode}" maxlength="5" name="dwellingReceptionAddress.postalCode" title="<g:message code="address.property.postalCode.validationError" />" />
            <input type="text" class="line2 required" value="${rqt.dwellingReceptionAddress?.city}" maxlength="32" name="dwellingReceptionAddress.city" title="<g:message code="address.property.city.validationError" />" />
            <label><g:message code="address.property.countryName" /></label>
            <input type="text" value="${rqt.dwellingReceptionAddress?.countryName}" maxlength="38" name="dwellingReceptionAddress.countryName"/>
            </div>
            
    
      <label class="required"><g:message code="hccr.property.dwellingSocialReception.label" /> <span><g:message code="hccr.property.dwellingSocialReception.help" /></span></label>
      
            <ul class="required">
              <g:each in="${[true,false]}">
              <li>
                <input type="radio" class="required condition-isInSocialReception-trigger validate-boolean" title="" value="${it}" name="dwellingSocialReception" ${it == rqt.dwellingSocialReception ? 'checked="checked"': ''} />
                <g:message code="message.${it ? 'yes' : 'no'}" />
              </li>
              </g:each>
            </ul>
            
    
      <label class="required condition-isInSocialReception-filled"><g:message code="hccr.property.dwellingSocialReceptionNaming.label" /> <span><g:message code="hccr.property.dwellingSocialReceptionNaming.help" /></span></label>
      
            <input type="text" name="dwellingSocialReceptionNaming" value="${rqt.dwellingSocialReceptionNaming}" 
                    class="required condition-isInSocialReception-filled " title="<g:message code="hccr.property.dwellingSocialReceptionNaming.validationError" />">
            
    
      <label class="required condition-isInSocialReception-filled"><g:message code="hccr.property.dwellingSocialReceptionAddress.label" /> <span><g:message code="hccr.property.dwellingSocialReceptionAddress.help" /></span></label>
      
            <div class="address-fieldset required condition-isInSocialReception-filled">
            <label><g:message code="address.property.additionalDeliveryInformation" /></label>
            <input type="text" value="${rqt.dwellingSocialReceptionAddress?.additionalDeliveryInformation}" maxlength="38" name="dwellingSocialReceptionAddress.additionalDeliveryInformation"/>  
            <label><g:message code="address.property.additionalGeographicalInformation" /></label>
            <input type="text" value="${rqt.dwellingSocialReceptionAddress?.additionalGeographicalInformation}" maxlength="38" name="dwellingSocialReceptionAddress.additionalGeographicalInformation"/>
            <label><g:message code="address.property.streetNumber" /> - <strong><g:message code="address.property.streetName" /></strong></label>
            <input type="text" class="line1" value="${rqt.dwellingSocialReceptionAddress?.streetNumber}" maxlength="5" name="dwellingSocialReceptionAddress.streetNumber"/>
            <input type="text" class="line2 required" value="${rqt.dwellingSocialReceptionAddress?.streetName}" maxlength="32" name="dwellingSocialReceptionAddress.streetName" title="<g:message code="address.property.streetName.validationError" />" />
            <label><g:message code="address.property.placeNameOrService" /></label>
            <input type="text" value="${rqt.dwellingSocialReceptionAddress?.placeNameOrService}" maxlength="38" name="dwellingSocialReceptionAddress.placeNameOrService"/>
            <label class="required"><g:message code="address.property.postalCode" /> - <g:message code="address.property.city" /></label>
            <input type="text" class="line1 required" value="${rqt.dwellingSocialReceptionAddress?.postalCode}" maxlength="5" name="dwellingSocialReceptionAddress.postalCode" title="<g:message code="address.property.postalCode.validationError" />" />
            <input type="text" class="line2 required" value="${rqt.dwellingSocialReceptionAddress?.city}" maxlength="32" name="dwellingSocialReceptionAddress.city" title="<g:message code="address.property.city.validationError" />" />
            <label><g:message code="address.property.countryName" /></label>
            <input type="text" value="${rqt.dwellingSocialReceptionAddress?.countryName}" maxlength="38" name="dwellingSocialReceptionAddress.countryName"/>
            </div>
            
    
    </fieldset>
  
