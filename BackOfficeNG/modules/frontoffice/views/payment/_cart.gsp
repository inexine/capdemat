<div id="narrow" class="yui-b">
  <div class="requestBox">
    <h3>
      <g:message code="payment.header.cart" />
    </h3>
    <div class="body">
      <g:if test="${session.payment}">
        <ul class="cart-list">
          <g:each in="${session.payment.purchaseItems}" var="${record}">
            <li>
              ${record.label} - ${record.amount / 100} €
            </li>
          </g:each>
        </ul>
        <p style="text-align:right;font-weight:bold;margin-top:0.5em;">Total : ${session.payment.amount / 100} €</p>
        <p>
        <a href="${createLink(action:'cartDetails')}" target="_self">
          <g:message code="action.finishShopping"/>
        </a>
        </p>
      </g:if>
      <g:else>
        <g:message code="message.cartIsEmpty" />
      </g:else>
    </div>
  </div>
</div>