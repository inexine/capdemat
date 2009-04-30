<html>
  <head>
    <title><g:message code="localAuthority.header.configuration" /></title>
    <link rel="stylesheet" href="${createLinkTo(dir:'css/backoffice',file:'configuration.css')}" />
    <script type="text/javascript" src="${createLinkTo(dir:'js/backoffice',file:'localAuthorityPlatformConfiguration.js')}"></script>
    <meta name="layout" content="main" />
  </head>
  <body>
    <div id="yui-main">
      <div class="yui-b">
        <div class="head">
          <h1><g:message code="localAuthority.header.configuration" /></h1>
        </div>
        <div id="platformConfigurationBox" class="mainbox mainbox-yellow">
          <h2><g:message code="localAuthority.header.setup.platformConfiguration" /></h2>
          <form method="post" id="platformConfigurationForm" action="${createLink(action : 'platformConfiguration')}">
            <div class="error" id="platformConfigurationFormErrors"></div>

                <label class="required" for="documentDigitalizationEnabled">
                  <g:message code="localAuthority.property.documentDigitalizationEnabled" /> :
                </label>
                <input type="radio" class="required validate-one-required" name="documentDigitalizationEnabled" value="1" <g:if test="${documentDigitalizationEnabled == true}">checked="checked"</g:if>/>
                <g:message code="message.yes" />
                <input type="radio" class="required validate-one-required" name="documentDigitalizationEnabled" value="0" <g:if test="${documentDigitalizationEnabled == false}">checked="checked"</g:if>/>
                <g:message code="message.no" />

                <label class="required" for="requestsCreationNotificationEnabled">
                  <g:message code="localAuthority.property.requestsCreationNotificationEnabled" /> :
                </label>
                <input type="radio" class="required validate-one-required" name="requestsCreationNotificationEnabled" value="1" <g:if test="${requestsCreationNotificationEnabled == true}">checked="checked"</g:if>/>
                <g:message code="message.yes" />
                <input type="radio" class="required validate-one-required" name="requestsCreationNotificationEnabled" value="0" <g:if test="${requestsCreationNotificationEnabled == false}">checked="checked"</g:if>/>
                <g:message code="message.no" />

                <label class="required" for="instructionAlertsEnabled">
                  <g:message code="localAuthority.property.instructionAlertsEnabled" /> :
                </label>
                <input type="radio" class="required validate-one-required" name="instructionAlertsEnabled" value="1" <g:if test="${instructionAlertsEnabled == true}">checked="checked"</g:if>/>
                <g:message code="message.yes" />
                <input type="radio" class="required validate-one-required" name="instructionAlertsEnabled" value="0" <g:if test="${instructionAlertsEnabled == false}">checked="checked"</g:if>/>
                <g:message code="message.no" />

                <label class="required" for="instructionAlertsDetailed">
                  <g:message code="localAuthority.property.instructionAlertsDetailed" /> :
                </label>
                <input type="radio" class="required validate-one-required" name="instructionAlertsDetailed" value="1" <g:if test="${instructionAlertsDetailed == true}">checked="checked"</g:if>/>
                <g:message code="message.yes" />
                <input type="radio" class="required validate-one-required" name="instructionAlertsDetailed" value="0" <g:if test="${instructionAlertsDetailed == false}">checked="checked"</g:if>/>
                <g:message code="message.no" />

                <label class="required" for="instructionDefaultAlertDelay">
                  <g:message code="localAuthority.property.instructionDefaultAlertDelay" /> :
                </label>
                <input type="text" class="required validate-number" name="instructionDefaultAlertDelay" id="instructionDefaultAlertDelay" value="${instructionDefaultAlertDelay}" size="3" />
                <g:message code="property.days" />

                <label class="required" for="instructionDefaultMaxDelay">
                  <g:message code="localAuthority.property.instructionDefaultMaxDelay" /> :
                </label>
                <input type="text" class="required validate-number" name="instructionDefaultMaxDelay" id="instructionDefaultMaxDelay" value="${instructionDefaultMaxDelay}" size="3" />
                <g:message code="property.days" />

            <div class="form-button">
              <input id="save" type="button" value="${message(code:'action.save')}" />
            </div>
          </form>
        </div>
      </div>
    </div>
    <div id="narrow" class="yui-b">
      <menu:subMenu id="secondMenu" i18nPrefix="localAuthority.header"
        data="${subMenuEntries}" />
    </div>
  </body>
</html>