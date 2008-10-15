<div id="requestData" class="yellow-yui-tabview">
  <ul class="yui-nav">
    <li class="selected"><a href="#page1"><em><g:message code="homeFolder.adults" /></em></a></li>
    <li><a href="#page1"><em><g:message code="homeFolder.children" /></em></a></li>
  </ul>
  <div class="yui-content">
      
    <!-- Page 1 -->
    <div id="page1">
      <h2><g:message code="property.form" /><span> - <g:message code="homeFolder.adults" /></span></h2>
    
      <!-- start of individual -->
      <g:each var="adult" in="${adults}">
        <g:render template="/requestInstruction/requestType/adult" model="['adult': adult]" />
      </g:each>
      <!-- end of individual -->
      
    </div>
    
     <!-- Page 2 -->
    <div id="page2">
      <h2><g:message code="property.form" /><span> - <g:message code="homeFolder.children" /></span></h2>
    
      <!-- start of individual -->
      <g:each var="child" in="${children}">
        <g:render template="/requestInstruction/requestType/child" 
            model="['child': child, 'childLegalResponsibles': childrenLegalResponsibles[child.id]]" />
      </g:each>
      <!-- end of individual -->
      
    </div>
      
  </div>
</div>
