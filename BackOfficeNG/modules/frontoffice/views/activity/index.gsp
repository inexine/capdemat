<html>
  <head>
    <meta name="layout" content="fo_main"/>
    <link rel="stylesheet" type="text/css" href="${createLinkTo(dir:'css/frontoffice', file:'activity.css')}" />
    <link rel="stylesheet" type="text/css" href="${createLinkTo(dir:'css/frontoffice/common', file:'data-detail.css')}" />
  </head>
  
  <body>
  <div id="yui-main">
    <div id="main" class="yui-b">
      <g:render template="individuals" />
    </div>
  </div>
  <!-- end of yui-main -->
  
  <div id="narrow" class="yui-b">
    <div class="narrow-box">
      <h3>
        <g:message code="header.filterBy" />
      </h3>
      <div class="body">
        <form action="${createLink(action:'index')}" method="post">
          <label for="mf" class="title"><g:message code="property.date" /> :</label>
          <select name="mf" class="month-filter">
            <g:each in="${(1 .. 12)}">
              <option value="${it}" ${it.equals((params.mf ? Integer.valueOf(params.mf) : month)) ? 'selected="selected"':''}>
                ${monthsNames[it]}
              </option>
            </g:each>
          </select>
          <select name="yf" class="year-filter">
            <g:each in="${(2004 .. (year - 1))}">
              <option value="${it}" ${it.toString() == params.yf ? 'selected="selected"':''}>
                ${it}
              </option> 
            </g:each>
            <option value="${year}" ${!params.yf || year.toString() == params.yf ? 'selected="selected"':''}>
              ${year}
            </option>
          </select>
          <input type="submit" value="${message(code:'action.filter')}"/>
        </form>
      </div>
    </div>
  </div>
  <!-- end of narrow -->
  
  </body>
</html>

