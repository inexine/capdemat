<html>
  <head>
    <title>${message(code:'payment.title.history')}</title>
    <meta name="layout" content="fo_main" />
    <link rel="stylesheet" type="text/css" href="${resource(dir:'css/frontoffice', file:'payment.css')}" />
  </head>
  
  <body>
    <form action="${createLink(action:'history')}" method="post" id="mainForm">
      
      <div id="yui-main"> 
        <div id="main" class="yui-b">
          <g:if test="${inProgressPayments}">
            <g:render template="paymentList" 
                model="${[payments : inProgressPayments, paginate : false, 
                    title : 'payment.header.inProgressPayments',
                    noPaymentsMsg : 'payment.message.noInitializedPayments']}"/>
          </g:if>
          <g:render template="paymentList" 
            model="${[payments : paymentsHistory, paginate : true, 
                title : 'payment.header.paymentsHistory',
                noPaymentsMsg : 'payment.message.noFinishedPayments']}"/>
        </div> 
      </div> <!-- end of yui-main -->
      
      %{--
        <div id="narrow" class="yui-b">
         
          <div id="requestSubject" class="narrow-box">
            <h3>
              <g:message code="header.filterBy" />
            </h3>
            <div class="body">
              <label for="st">
                <g:message code="property.state" /> :
              </label>
              
              <g:select id="st" name="st"
                from="${paymentStates}" value="${state?.st}"
                noSelection="['':message(code:'search.filter.defaultValue')]"
                valueMessagePrefix="payment.state" />
              
              <input type="submit" value="${message(code:'action.filter')}"/>
            </div>
          </div>
        </div>
      --}%
      
      <div id="narrow" class="yui-b">
        
        <div class="narrow-box">
          <h3>
           <g:message code="header.display" />
          </h3>
          <div class="body">
            <a class="top-link" href="${createLink(action:'index')}">
              <g:message code="payment.header.accountAndCartStatus" />
            </a>
          </div>
        </div>
      </div>
      <!-- end of narrow -->
      
      <g:hiddenField name="ps" value="${pageState}" />
    </form>
  </body>
</html>

