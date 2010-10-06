


  
    <label for="distance" class="required"><g:message code="sgr.property.distance.label" /> *  <span><g:message code="sgr.property.distance.help" /></span></label>
            <select id="distance" name="distance" class="required  validate-not-first ${stepStates != null && stepStates['calculationElements']?.invalidFields?.contains('distance') ? 'validation-failed' : ''}" title="<g:message code="sgr.property.distance.validationError" />">
              <option value=""><g:message code="message.select.defaultOption" /></option>
              <g:each in="${['lessThan30kms','between30and250kms','moreThan250kmsAndAbroad']}">
                <option value="fr.cg95.cvq.business.request.school.DistanceType_${it}" ${it == rqt.distance?.toString() ? 'selected="selected"': ''}><g:capdematEnumToText var="${it}" i18nKeyPrefix="sgr.property.distance" /></option>
              </g:each>
            </select>
            

  

