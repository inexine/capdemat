


  
    <h3><g:message code="cwcr.step.waste.label" /></h3>
    
      
      <dl>
        <dt><g:message code="cwcr.property.compostableWasteType.label" /></dt>
          <dd>
          <g:render template="/frontofficeRequestType/widget/localReferentialDataSummary" 
                    model="['javaName':'compostableWasteType', 'lrEntries': lrTypes.compostableWasteType.entries, 'depth':0]" />
          </dd>
          

      </dl>
      
    
      
      <dl>
        <dt><g:message code="cwcr.property.otherWaste.label" /></dt><dd>${rqt.otherWaste?.toString()}</dd>

      </dl>
      
    
      
      <dl>
        <dt><g:message code="cwcr.property.collectionAddress.label" /></dt>
          <dd>
          <g:if test="${rqt.collectionAddress}">
              <p>${rqt.collectionAddress?.additionalDeliveryInformation}</p>
              <p>${rqt.collectionAddress?.additionalGeographicalInformation}</p>
              <p>${rqt.collectionAddress?.streetNumber} ${rqt.collectionAddress?.streetName}</p>
              <p>${rqt.collectionAddress?.placeNameOrService}</p>
              <p>${rqt.collectionAddress?.postalCode} ${rqt.collectionAddress?.city}</p>
              <p>${rqt.collectionAddress?.countryName}</p>
          </g:if>
          </dd>
          

      </dl>
      
    
  

  
  <g:if test="${!documentTypes.isEmpty()}">
    <h3>${message(code:'request.step.document.label')}</h3>
    <g:each in="${documentTypes}" var="documentType">
      <h4>${message(code:documentType.value.name)}</h4>
      <g:if test="${documentType.value.associated}">
      <dl class="document-linked">
        <g:each in="${documentType.value.associated}" var="document">
        <dt>
          <g:if test="${document.ecitizenNote}">${message(code:'document.header.description')} : ${document.ecitizenNote}<br/></g:if>
          <g:if test="${document.endValidityDate}">${message(code:'document.header.expireOn')} ${formatDate(date:document.endValidityDate,formatName:'format.date')}</g:if>
        </dt>
        <dd>
          <g:if test="${document.isNew}"><span class="tag-state tag-active">${message(code:'document.header.new')}</span>
            <a href="${createLink(controller:'frontofficeDocument',action:'details', id:document.id)}?isRequestCreation=true&sessionUuid=${uuidString}" target="blank">${message(code:'document.header.preview')}</a>
          </g:if>
          <g:else>
            <a href="${createLink(controller:'frontofficeDocument',action:'details', id:document.id)}" target="blank">${message(code:'document.header.preview')}</a>
          </g:else>
          </dd>
        </g:each>
      </dl>
      </g:if>
      <g:else>
        ${message(code:'document.header.noAttachments')}
      </g:else>
    </g:each>
  </g:if>
  

  


