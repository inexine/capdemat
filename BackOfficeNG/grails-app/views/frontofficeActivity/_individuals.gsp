<g:if test="${activities?.keySet()}">
  <g:each in="${activities.keySet()}" var="${name}">
    <div class="main-box data-detail">
      <h2><g:message code="activity.header.briefReviewOf"/> ${name}</h2>
      <g:each in="${activities.get(name.toString()).keySet()}" var="label">
        <div class="yui-g">
          <h3><g:translateRequestTypeLabel label="${label}"/></h3>
          <div class="yui-u first">
            <dl>
              <g:each in="${activities.get(name.toString()).get(label).keySet()}" status="inx" var="${activity}">
                <g:if test="${inx % 2 == 0}">
                  <dt>${activity} :</dt>
                  <dd>${activities.get(name.toString()).get(label).get(activity).size()}</dd>
                </g:if>
              </g:each>
            </dl>
          </div>
          <div class="yui-u">
            <dl>
              <g:each in="${activities.get(name.toString()).get(label).keySet()}" status="inx" var="${activity}">
                <g:if test="${inx % 2 != 0}">
                  <dt>${activity} :</dt>
                  <dd>${activities.get(name.toString()).get(label).get(activity).size()}</dd>
                </g:if>
              </g:each>
            </dl>
          </div>
          <div class="details-panel">
            <a href="${createLink(action: 'details')}?name=${name.encodeAsURL()}&amp;label=${label.encodeAsURL()}&amp;year=${year ? year : currentYear}&amp;month=${month ? month : currentMonth}">
              <g:message code="action.seeDetails"/>
            </a>
          </div>
        </div>
      </g:each>
    </div>
  </g:each>
</g:if>
<g:else>
  <div class="information-box">
  	<g:message code="activity.message.noActivities" />
  </div>
</g:else>
