import fr.cg95.cvq.service.authority.impl.LocalAuthorityRegistry
import fr.cg95.cvq.business.users.payment.PaymentState
import fr.cg95.cvq.business.users.payment.PaymentMode
import fr.cg95.cvq.payment.IPaymentService
import fr.cg95.cvq.service.authority.ILocalAuthorityRegistry
import fr.cg95.cvq.util.Critere

import grails.converters.JSON

class PaymentController {
    
    IPaymentService paymentService
    ILocalAuthorityRegistry localAuthorityRegistry
    
    def translationService
    def defaultAction = "initSearch"
    def defaultSortBy = 'initializationDate'
    def supportedKeys = ["requesterLastName", "homeFolderId", "cvqReference","bankReference", "initDateFrom", "initDateTo"]
    def longKeys = ["homeFolderId"]
    def dateKeys = ["initDateFrom", "initDateTo"]
    def resultsPerPage = 25
    
    def beforeInterceptor = {
        session["currentMenu"] = "payment"
    }

    /**
     * Called when first entering the search screen
     */
    def initSearch = {

        render(view:'search', model:['mode':'simple',
                                     'inSearch':false,
                                     'sortBy':defaultSortBy,
                                     'filters':[:]].plus(initSearchReferential()))
    }

    /**
     * Called asynchronously when switching from simple to advanced search mode and vice versa
     */
    def loadSearchForm = {
        def model = ['totalRecords':params.totalRecords,
                     'recordOffset':params.recordOffset,
                     'recordsReturned':params.recordsReturned,
                     'sortBy':params.sortBy,
                     'filterBy':params.filterBy].plus(initSearchReferential())
        
    	if (params.formType == 'simple') {
    		model['mode'] = 'simple'
    		render(template:'simpleSearchForm', model:model)
    	} else {
    		model['mode'] = 'advanced'
    		render(template:'advancedSearchForm', model:model)
    	}
    }   
    
    def initSearchReferential() {
    	return ['allStates':PaymentState.allPaymentStates,
    	        'allBrokers':paymentService.getAllBrokers()]
    }
    
    def configure = {
        def name = "paymentlackmessage.html"
        File file = localAuthorityRegistry.getCurrentLocalAuthorityResource(
            ILocalAuthorityRegistry.HTML_RESOURCE_TYPE,name,false)
            
        if(!file.exists()) {
            localAuthorityRegistry.saveLocalAuthorityResource(
                    ILocalAuthorityRegistry.HTML_RESOURCE_TYPE, 
                    name,"".getBytes());
         
            file = localAuthorityRegistry.getCurrentLocalAuthorityResource(
                    ILocalAuthorityRegistry.HTML_RESOURCE_TYPE,name,false)
        }
        
        if(request.post) {
            def String content = (params.editor == null ? "" : params.editor.toString())
            localAuthorityRegistry.saveLocalAuthorityResource(
                    ILocalAuthorityRegistry.HTML_RESOURCE_TYPE, 
                    name,content.getBytes());

            render([status:"ok", success_msg:message(code:"message.updateDone")] as JSON)
        } else {
            render(view:'configure',model:[editorContent:file.getText()])
        }
    }

    def search = {

        // deal with search criteria
        Set<Critere> criteria = new HashSet<Critere>()
        params.each { key,value ->
            if (supportedKeys.contains(key) && value != "") {
                Critere critere = new Critere()
                critere.attribut = key
                critere.comparatif = Critere.EQUALS
                if (key == 'requesterLastName')
                    critere.comparatif = Critere.STARTSWITH
                if (longKeys.contains(key)) {
                    try {
                        critere.value = Long.valueOf(value.trim())
                    } catch (Exception e) {
                        critere.value = null
                    }
                } else if (dateKeys.contains(key)) {
                    critere.value = DateUtils.stringToDate(value)
                    if (key == 'initDateFrom') {
                        critere.attribut = 'initializationDate'
                        critere.comparatif = Critere.GTE
                    } else { 
                        critere.attribut = 'initializationDate'
                        critere.comparatif = Critere.LTE
                    }
                } else {
                    critere.value = value.trim()
                }
                criteria.add(critere)
            }
        }
        
        // deal with dynamic filters
        def parsedFilters = SearchUtils.parseFilters(params.filterBy)
        parsedFilters.filters.each { key, value ->
            Critere critere = new Critere()
            critere.attribut = key.replaceAll("Filter","")
            critere.comparatif = Critere.EQUALS
            critere.value = value
            criteria.add(critere)
        }
        
        // deal with dynamic sorts
        def sortBy = params.sortBy ? params.sortBy : defaultSortBy 
        def dir = null
        if (sortBy.equals("initializationDate")) dir = "desc"
        
        // deal with pagination settings
        def results = params.results == null ? resultsPerPage : Integer.valueOf(params.results)
        def recordOffset = 0
        if (params.paginatorChange.equals("true"))
            recordOffset = Integer.valueOf(params.recordOffset)        
                    
        def payments = paymentService.get(criteria, sortBy, dir, results, recordOffset)        
                      
        def recordsList = []

        payments.each {
            def record = [
                'id':it.id,
                'broker':it.broker,
                'cvqReference':it.cvqReference,
                'bankReference':it.bankReference,
                'requesterLastName':it.requester.lastName + " " + it.requester.firstName,
                'homeFolderId':it.homeFolder.id,
                'initializationDate':it.initializationDate,
                'commitDate':it.commitDate,
                'paymentState':it.state.toString(),
                'amount':it.getEuroAmount() + " &euro;",
                'paymentMode':it.paymentMode.toString()
            ]		
			recordsList.add(record)
        }
        render(view:'search', model:[
                                     'recordsReturned':payments.size(),
                                     'totalRecords':paymentService.getCount(criteria),
                                     'recordOffset':recordOffset,
                                     'records':recordsList,
                                     'filters':parsedFilters.filters,
                                     'filterBy':parsedFilters.filterBy,
                                     'sortBy':params.sortBy,
                                     'dir':params.dir,
                                     'mode':params.mode,
                                     'inSearch':true].plus(initSearchReferential()))        
    }
}