<div class="account required validation-scope">
  <g:set var="listIndex" value="${editList?.name == 'children' ? editList?.index : ( individuals?.children ? individuals?.children.size() : 0 ) }" />
  <fieldset class="individual add">
    <div class="yui-g">
      <div class="yui-u first">
      <label for="_individuals.children.${listIndex}.lastName" class="required"><g:message code="homeFolder.individual.property.lastName" /></label>
      <input type="text" id="_individuals.children.${listIndex}.lastName" name="_individuals.children[${listIndex}].lastName" value="${editList?.children?.lastName}"
        class="required validate-lastName ${stepStates != null && stepStates['children']?.invalidFields.contains('lastName') ? 'validation-failed' : ''}" title="<g:message code="homeFolder.individual.property.lastName.validationError" />" />

      <label for="_individuals.children.${listIndex}.firstName" class="required"><g:message code="homeFolder.individual.property.firstName" /></label>
      <input type="text" id="_individuals.children.${listIndex}.firstName" name="_individuals.children[${listIndex}].firstName" value="${editList?.children?.firstName}"
      class="required validate-firstName ${stepStates != null && stepStates['children']?.invalidFields.contains('firstName') ? 'validation-failed' : ''}" title="<g:message code="homeFolder.individual.property.firstName.validationError" />" />
    </div>
      
    <div class="yui-u">
      <label for="_individuals.children.${listIndex}.sex" class="required"><g:message code="homeFolder.child.property.sex" /></label>
      <select id="_individuals.children.${listIndex}.sex" name="_individuals.children[${listIndex}].sex" class="required validate-not-first ${stepStates != null && stepStates['children']?.invalidFields.contains('sex') ? 'validation-failed' : ''}" title="<g:message code="homeFolder.child.property.sex.validationError" />">
        <option value=""><g:message code="message.select.defaultOption" /></option>
        <g:each in="${['Male','Female']}">
          <option value="fr.cg95.cvq.business.users.SexType_${it}" ${it == editList?.children?.sex?.toString() ? 'selected="selected"': ''}>
              <g:capdematEnumToText var="${it}" i18nKeyPrefix="homeFolder.child.property.sex" />
          </option>
        </g:each>
      </select>

      <label for="_individuals.children.${listIndex}.birthDate" class="required"><g:message code="homeFolder.individual.property.birthDate" /> <span><g:message code="homeFolder.individual.property.birthDate.help" /></span></label>
      <div class="date required validate-date">
        <select id="_individuals.children.${listIndex}.birthDate_day" name="_individuals.children[${listIndex}].birthDate_day"
          class="day ${stepStates != null && stepStates['children']?.invalidFields?.contains('birthDate') ? 'validation-failed' : ''}">
          <option value=""><g:message code="message.select.defaultOption" /></option>
          <g:each in="${1..31}">
            <option value="${it}"
              <g:if test="${editList?.children?.birthDate?.date == it
                || (editList?.children?.birthDate == null && params['birthDate_day'] == it.toString())}">
                selected="selected"
              </g:if>>
              ${it}
            </option>
          </g:each>
        </select>
        <select id="_individuals.children.${listIndex}.birthDate_month" name="_individuals.children[${listIndex}].birthDate_month"
          class="month ${stepStates != null && stepStates['children']?.invalidFields?.contains('birthDate') ? 'validation-failed' : ''}">
          <option value=""><g:message code="message.select.defaultOption" /></option>
          <g:each in="${1..12}">
            <option value="${it}"
              <g:if test="${editList?.children?.birthDate?.month == (it - 1)
                || (editList?.children?.birthDate == null && params['birthDate_month'] == it.toString())}">
                selected="selected"
              </g:if>>
              <g:message code="month.${it}" />
            </option>
          </g:each>
        </select>
        <input type="text" id="_individuals.children.${listIndex}.birthDate_year" name="_individuals.children[${listIndex}].birthDate_year" maxlength="4" size="3"
          class="year ${stepStates != null && stepStates['children']?.invalidFields?.contains('birthDate') ? 'validation-failed' : ''}"
          value="${editList?.children?.birthDate ? editList?.children?.birthDate.year + 1900 : params['birthDate_year']}" />
       </div>

      </div>

    </div>
    
    <input type="hidden" name="individuals" />
    <input type="hidden" name="objectToBind" value="individuals" />
    <div class="error" id="stepForm-children-error"> </div>
    <g:if test="${editList?.name == 'children'}">
      <input type="submit" id="submit-collectionModify-children-children" name="submit-collectionModify-children-children[${listIndex}]" value="${message(code:'action.save')}" />
      <input type="submit" id="submit-collectionCancel-children-children" name="submit-collectionCancel-children-children[${listIndex}]" value="${message(code:'action.cancel')}" />
    </g:if>
    <g:else>
      <input type="submit" id="submit-collectionAdd-children-children" name="submit-collectionAdd-children-children[${listIndex}]" value="${message(code:'vcr.action.addChild')}" />
    </g:else>
  </fieldset>

  <g:each var="it" in="${individuals?.children}" status="index">
  <fieldset class="individual edit summary-box ${stepStates != null && stepStates['children']?.invalidFields.contains('children[' + index + ']') ? 'validation-failed' : ''}">
    <h4>
      ${it.firstName} ${it.lastName}
      <input type="hidden" name="objectToManage[${index}]" value="individuals" />
      <input type="submit" value="${message(code:'action.modify')}" name="submit-collectionEdit-children-children[${index}]" />
      <input type="submit" value="${message(code:'action.remove')}" name="submit-collectionDelete-children-children[${index}]" />
    </h4>
    <dl>
      <dt><g:message code="homeFolder.child.property.sex" /> : </dt>
      <dd><g:capdematEnumToField var="${it.sex}" i18nKeyPrefix="homeFolder.child.property.sex" /></dd>
      <dt><g:message code="homeFolder.individual.property.birthDate" /> : </dt>
      <dd><span><g:formatDate formatName="format.date" date="${it.birthDate}"/></span></dd>
    </dl>
  </fieldset>
  </g:each>
</div>

