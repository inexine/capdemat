package fr.cg95.cvq.exporter.service.endpoint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.springframework.beans.BeansException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.oxm.xmlbeans.XmlBeansMarshaller;

import fr.capwebct.capdemat.AckRequestType;
import fr.capwebct.capdemat.AckRequestsRequestDocument;
import fr.capwebct.capdemat.GetRequestsRequestDocument;
import fr.capwebct.capdemat.AckRequestsRequestDocument.AckRequestsRequest;
import fr.capwebct.capdemat.AckRequestsResponseDocument.AckRequestsResponse;
import fr.capwebct.capdemat.GetRequestsRequestDocument.GetRequestsRequest;
import fr.capwebct.capdemat.GetRequestsResponseDocument.GetRequestsResponse;
import fr.cg95.cvq.business.external.ExternalServiceTrace;
import fr.cg95.cvq.business.external.TraceStatusEnum;
import fr.cg95.cvq.business.users.CreationBean;
import fr.cg95.cvq.business.request.RequestState;
import fr.cg95.cvq.exception.CvqException;
import fr.cg95.cvq.exception.CvqObjectNotFoundException;
import fr.cg95.cvq.external.ExternalServiceBean;
import fr.cg95.cvq.external.IExternalProviderService;
import fr.cg95.cvq.external.IExternalService;
import fr.cg95.cvq.security.SecurityContext;
import fr.cg95.cvq.service.authority.ILocalAuthorityRegistry;
import fr.cg95.cvq.service.authority.LocalAuthorityConfigurationBean;
import fr.cg95.cvq.service.request.IRequestService;
import fr.cg95.cvq.testtool.ServiceTestCase;
import fr.cg95.cvq.util.DateUtils;
import fr.cg95.cvq.xml.common.RequestStateType;

public class RequestServiceEndpointTest extends ServiceTestCase {
    
    private IExternalProviderService fakeExternalService;

    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        
        SecurityContext.setCurrentSite(localAuthorityName, SecurityContext.ADMIN_CONTEXT);

        ExternalServiceBean esb = new ExternalServiceBean();
        esb.setGenerateTracedRequest(true);
        List<String> requestTypes = new ArrayList<String>();
        requestTypes.add(IRequestService.VO_CARD_REGISTRATION_REQUEST);
        esb.setRequestTypes(requestTypes);
        LocalAuthorityConfigurationBean lacb = SecurityContext.getCurrentConfigurationBean();
        fakeExternalService = (IExternalProviderService) getBean("fakeExternalService");
        lacb.registerExternalService(fakeExternalService, esb);
    }
    
    public void testGetAndAckFlow() throws Exception {

        ConfigurableApplicationContext context = getContext(getConfigLocations());
        SecurityContext.setCurrentSite(localAuthorityName, SecurityContext.BACK_OFFICE_CONTEXT);
        SecurityContext.setCurrentExternalService("Fake External Service");
        
        /* Initialize internal variables */
        AckRequestServiceEndpoint endpoint1 = 
            new AckRequestServiceEndpoint(new XmlBeansMarshaller());
        RequestServiceEndpoint endpoint2 = new RequestServiceEndpoint(new XmlBeansMarshaller());
        IRequestService defaultRequestService = 
            (IRequestService) context.getBean("defaultRequestService");
        IExternalService externalService = 
            (IExternalService) context.getBean("externalService");
        ILocalAuthorityRegistry localAuthorityRegistry = 
            (ILocalAuthorityRegistry) context.getBean("localAuthorityRegistry");
        endpoint1.setExternalService(externalService);
        endpoint2.setExternalService(externalService);
        endpoint2.setDefaultRequestService(defaultRequestService);
        endpoint2.setLocalAuthorityRegistry(localAuthorityRegistry);
        
        try {
            externalService.deleteTraces(null, "capdemat");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            
            CreationBean cb = this.gimmeAnHomeFolder();
            this.continueWithNewTransaction();
            
            SecurityContext.setCurrentSite(localAuthorityName, SecurityContext.BACK_OFFICE_CONTEXT);
            SecurityContext.setCurrentExternalService("Fake External Service");

            GetRequestsRequest getRequest = GetRequestsRequest.Factory.newInstance();
            AckRequestsRequest ackRequest = AckRequestsRequest.Factory.newInstance();
            GetRequestsRequestDocument requestDocument = 
                GetRequestsRequestDocument.Factory.newInstance();
            
            getRequest.setDateFrom(calendar);
            getRequest.setState(RequestStateType.Enum.forString(RequestState.PENDING.toString()));
            requestDocument.setGetRequestsRequest(getRequest);
            
            // test we get our VO card request
            GetRequestsResponse getResponse = 
                (GetRequestsResponse) endpoint2.invokeInternal(requestDocument);
            int getCountBefore = getResponse.getRequestArray().length;
            Assert.assertEquals(1, getCountBefore);
            
            int tracesCount = 
                externalService.getTraces(null, null, null, DateUtils.parseDate("13/07/2007"), null)
                    .size();
            Assert.assertEquals(1, tracesCount);
            
            /* Create acknowledgement response */
            AckRequestType[] types = new AckRequestType[1];
            AckRequestType type = AckRequestType.Factory.newInstance();
            type.setRequestId(cb.getRequestId());
            type.setErroneous(false);
            types[0] = type;
            
            ackRequest.setAckElementsArray(types);
            AckRequestsRequestDocument ackRequestDocument = 
                AckRequestsRequestDocument.Factory.newInstance();
            ackRequestDocument.setAckRequestsRequest(ackRequest);
            AckRequestsResponse ackResponse = 
                (AckRequestsResponse) endpoint1.invokeInternal(ackRequestDocument);
            Assert.assertNotNull(ackResponse);
            Assert.assertTrue(ackResponse.getAccomplished());
            
            int newCount = externalService
                .getTraces(null, null, null, DateUtils.parseDate("13/07/2007"), null)
                .size();
            
            Assert.assertEquals(2, newCount);
            
            getResponse = (GetRequestsResponse) endpoint2.invokeInternal(requestDocument);
            Assert.assertEquals(0, getResponse.getRequestArray().length);
            
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unwaited exception trown : " + e.getMessage());
        } finally {
            externalService.deleteTraces(null, "capdemat");
        }
    }
    
    public void testAckServiceEndpoint() throws Exception {
        ConfigurableApplicationContext context = getContext(getConfigLocations());
        SecurityContext.setCurrentSite(localAuthorityName, SecurityContext.BACK_OFFICE_CONTEXT);
        SecurityContext.setCurrentExternalService("Fake External Service");
        
        /* Initialize internal variables */
        AckRequestServiceEndpoint endpoint1 = 
            new AckRequestServiceEndpoint(new XmlBeansMarshaller());
        RequestServiceEndpoint endpoint2 = new RequestServiceEndpoint(new XmlBeansMarshaller());
        IRequestService defaultRequestService = 
            (IRequestService) context.getBean("defaultRequestService");
        IExternalService externalService = 
            (IExternalService) context.getBean("externalService");
        ILocalAuthorityRegistry localAuthorityRegistry = 
            (ILocalAuthorityRegistry) context.getBean("localAuthorityRegistry");
        endpoint1.setExternalService(externalService);
        endpoint2.setExternalService(externalService);
        endpoint2.setDefaultRequestService(defaultRequestService);
        endpoint2.setLocalAuthorityRegistry(localAuthorityRegistry);
        
        try {
            externalService.deleteTraces(null, "capdemat");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            
            this.gimmeAnHomeFolder();
            this.continueWithNewTransaction();
            
            GetRequestsRequest getRequest = GetRequestsRequest.Factory.newInstance();
            AckRequestsRequest ackRequest = AckRequestsRequest.Factory.newInstance();
            GetRequestsRequestDocument requestDocument = 
                GetRequestsRequestDocument.Factory.newInstance();
            
            getRequest.setDateFrom(calendar);
            getRequest.setState(RequestStateType.Enum.forString(RequestState.PENDING.toString()));
            requestDocument.setGetRequestsRequest(getRequest);
            
            /* Create sent traces */
            GetRequestsResponse getResponse = 
                (GetRequestsResponse) endpoint2.invokeInternal(requestDocument);
            int getCountBefore = getResponse.getRequestArray().length;
            
            Assert.assertEquals(1, getCountBefore);
            
            getRequest.setRequestTypeLabel(IRequestService.VO_CARD_REGISTRATION_REQUEST);
            requestDocument.setGetRequestsRequest(getRequest);

            getResponse = (GetRequestsResponse) endpoint2.invokeInternal(requestDocument);
            getCountBefore = getResponse.getRequestArray().length;
            
            Assert.assertEquals(1, getCountBefore);
            
            int tracesCount = 
                externalService.getTraces(null, null, null, DateUtils.parseDate("13/07/2007"), null)
                    .size();
            Assert.assertNotSame(0, tracesCount);
            
            /* Create acknowledged traces */
            AckRequestType[] types = new AckRequestType[3];
            AckRequestType type = AckRequestType.Factory.newInstance();
            type.setRequestId(2345L);
            type.setErroneous(false);
            types[0] = type;
            
            type = AckRequestType.Factory.newInstance();
            type.setRequestId(2346L);
            type.setErroneous(false);
            types[1] = type;
            
            type = AckRequestType.Factory.newInstance();
            type.setRequestId(2347L);
            type.setErroneous(true);
            types[2] = type;
            
            ackRequest.setAckElementsArray(types);
            AckRequestsRequestDocument ackRequestDocument = AckRequestsRequestDocument.Factory.newInstance();
            ackRequestDocument.setAckRequestsRequest(ackRequest);
            AckRequestsResponse ackResponse = (AckRequestsResponse) endpoint1.invokeInternal(ackRequestDocument);
            Assert.assertNotNull(ackResponse);

            int newCount = externalService
                .getTraces(null, null, null, DateUtils.parseDate("13/07/2007"), null)
                .size();
            
            Assert.assertEquals(newCount, tracesCount+3);
            
            ExternalServiceTrace trace = externalService.getTraces(null, null, TraceStatusEnum.ERROR, null, null)
                .iterator().next();
            
            Assert.assertEquals((long)trace.getKey(), 2347L);
            Assert.assertEquals(trace.getKeyOwner(),"capdemat");
            Assert.assertEquals(trace.getStatus(), TraceStatusEnum.ERROR);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unwaited exception trown : " + e.getMessage());
        } finally {
            externalService.deleteTraces(null, "capdemat");
        }
        
    }
    
    public void testSecuredEndpoint() throws Exception {
        try {
            SecurityContext.resetCurrentSite();
            SecurityContext.setCurrentSite(localAuthorityName, SecurityContext.BACK_OFFICE_CONTEXT);
            SecurityContext.setCurrentAgent(agentNameWithCategoriesRoles);
            IExternalService externalService = 
                (IExternalService) getContext(getConfigLocations()).getBean("externalService");
            
            ConfigurableApplicationContext cac;
            cac = getContext(getConfigLocations());
            IRequestService requestService = (IRequestService) cac.getBean("defaultRequestService");
            ILocalAuthorityRegistry localAuthorityRegistry = 
                (ILocalAuthorityRegistry) cac.getBean("localAuthorityRegistry");
            SecurityContext.setCurrentSite(localAuthorityName, SecurityContext.ADMIN_CONTEXT);
            
            XmlBeansMarshaller xmlBeansMarshaller = new XmlBeansMarshaller();
            RequestServiceEndpoint endpoint = new RequestServiceEndpoint(xmlBeansMarshaller);
            endpoint.setDefaultRequestService(requestService);
            endpoint.setExternalService(externalService);
            endpoint.setLocalAuthorityRegistry(localAuthorityRegistry);
            GetRequestsRequestDocument pendedRequestDocument = 
                GetRequestsRequestDocument.Factory.newInstance();
            GetRequestsRequest pendedRequest = GetRequestsRequest.Factory.newInstance();
            
            pendedRequestDocument.setGetRequestsRequest(pendedRequest);
            endpoint.invokeInternal(pendedRequestDocument);
            fail();
            
        } catch (CvqObjectNotFoundException e) {
            fail("Unwaited exception raised");
        } catch (CvqException e) {
            // Do nothing because raised exception is a part of normal behavior in current situation
        } catch (BeansException e) {
            fail("Unwaited exception raised");
        } 
        
    }
    
    public void testAccessPermissions() {
        try {
            SecurityContext.setCurrentSite(localAuthorityName, SecurityContext.BACK_OFFICE_CONTEXT);
            SecurityContext.setCurrentExternalService("Fake External Service");
            IExternalService externalService = 
                (IExternalService) getContext(getConfigLocations()).getBean("externalService");
            
            ConfigurableApplicationContext cac;
            cac = getContext(getConfigLocations());
            IRequestService requestService = (IRequestService) cac.getBean("defaultRequestService");
            ILocalAuthorityRegistry localAuthorityRegistry = 
                (ILocalAuthorityRegistry) cac.getBean("localAuthorityRegistry");
            
            XmlBeansMarshaller xmlBeansMarshaller = new XmlBeansMarshaller();
            RequestServiceEndpoint endpoint = new RequestServiceEndpoint(xmlBeansMarshaller);
            endpoint.setDefaultRequestService(requestService);
            endpoint.setExternalService(externalService);
            endpoint.setLocalAuthorityRegistry(localAuthorityRegistry);
            
            GetRequestsRequestDocument pendedRequestDocument = 
                GetRequestsRequestDocument.Factory.newInstance();
            GetRequestsRequest pendedRequest = GetRequestsRequest.Factory.newInstance();
            pendedRequest.setRequestTypeLabel("Dude Registration");
            
            pendedRequestDocument.setGetRequestsRequest(pendedRequest);
            GetRequestsResponse getResponse = 
                (GetRequestsResponse) endpoint.invokeInternal(pendedRequestDocument);
            Assert.assertNotSame(getResponse.getError().length(), 0);
            
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unwaited exception raised");
        }
    }
    
    public void testRequestServiceEndpoint() throws Exception {
        SecurityContext.setCurrentSite(localAuthorityName, SecurityContext.BACK_OFFICE_CONTEXT);
        SecurityContext.setCurrentExternalService("Fake External Service");
        
        IExternalService externalService = 
            (IExternalService) getContext(getConfigLocations()).getBean("externalService");
        
        ConfigurableApplicationContext cac;
        
        try {
            int activeCountBefore = 0;
            cac = getContext(getConfigLocations());
            IRequestService requestService = (IRequestService) cac.getBean("defaultRequestService");
            ILocalAuthorityRegistry localAuthorityRegistry = 
                (ILocalAuthorityRegistry) cac.getBean("localAuthorityRegistry");
            
            XmlBeansMarshaller xmlBeansMarshaller = new XmlBeansMarshaller();
            
            /* Initialize internal variables */
            RequestServiceEndpoint endpoint = new RequestServiceEndpoint(xmlBeansMarshaller);
            endpoint.setDefaultRequestService(requestService);
            endpoint.setExternalService(externalService);
            endpoint.setLocalAuthorityRegistry(localAuthorityRegistry);
            GetRequestsRequestDocument pendedRequestDocument = GetRequestsRequestDocument.Factory.newInstance();
            GetRequestsRequest pendedRequest = GetRequestsRequest.Factory.newInstance();
            
            /*
             * Retrieving requests that has pending status and has fromDate
             * equals to current date
             */
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            pendedRequest.setDateFrom(calendar);
            pendedRequest.setState(RequestStateType.Enum.forString(RequestState.PENDING.toString()));
            
            pendedRequestDocument.setGetRequestsRequest(pendedRequest);
            GetRequestsResponse pendedResponse = (GetRequestsResponse) endpoint.invokeInternal(pendedRequestDocument);
            int pendedCountBefore = pendedResponse.getRequestArray().length;
            
            /* Retrieving requests with an another state */
            GetRequestsRequestDocument activeRequestDocument = GetRequestsRequestDocument.Factory.newInstance();
            GetRequestsRequest activeRequest = GetRequestsRequest.Factory.newInstance();
            activeRequest.setDateFrom(pendedRequest.getDateFrom());
            activeRequest.setState(RequestStateType.Enum.forString(RequestState.ACTIVE.toString()));
            
            activeRequestDocument.setGetRequestsRequest(activeRequest);
            GetRequestsResponse activeResponse = (GetRequestsResponse) endpoint.invokeInternal(activeRequestDocument);
            activeCountBefore = activeResponse.getRequestArray().length;
            
            /* Create new request and child entities */
            this.gimmeAnHomeFolder();
            this.gimmeAnHomeFolder();
            this.continueWithNewTransaction();
            
            pendedResponse = (GetRequestsResponse) endpoint.invokeInternal(pendedRequestDocument);
            activeResponse = (GetRequestsResponse) endpoint.invokeInternal(activeRequestDocument);
            int pendedCountAfter = pendedResponse.getRequestArray().length;
            int activeCountAfter = activeResponse.getRequestArray().length;
            
            Assert.assertEquals("Pended request counts don't match", pendedCountBefore + 2, pendedCountAfter);
            Assert.assertEquals("Active request counts don't match", activeCountBefore, activeCountAfter);
            SecurityContext.resetCurrentSite();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unwaited exception trown : " + e.getMessage());
        } finally {
            externalService.deleteTraces(null, "capdemat");
        }
    }
}