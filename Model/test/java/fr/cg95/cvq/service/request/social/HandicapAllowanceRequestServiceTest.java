package fr.cg95.cvq.service.request.social;

import fr.cg95.cvq.business.users.*;
import fr.cg95.cvq.business.request.*;
import fr.cg95.cvq.business.authority.*;
import fr.cg95.cvq.business.document.*;
import fr.cg95.cvq.business.request.social.*;
import fr.cg95.cvq.exception.*;
import fr.cg95.cvq.security.SecurityContext;
import fr.cg95.cvq.service.document.IDocumentTypeService;
import fr.cg95.cvq.service.request.IRequestService;
import fr.cg95.cvq.service.request.social.IHandicapAllowanceRequestService;
import fr.cg95.cvq.util.Critere;

import fr.cg95.cvq.testtool.ServiceTestCase;
import fr.cg95.cvq.testtool.BusinessObjectsFactory;

import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;

/**
 * Generated by Velocity if not present, can be edited safely !
 */
public class HandicapAllowanceRequestServiceTest extends ServiceTestCase {

    protected IHandicapAllowanceRequestService iHandicapAllowanceRequestService;

    protected void onSetUp() throws Exception {
    	super.onSetUp();
        iHandicapAllowanceRequestService = 
            (IHandicapAllowanceRequestService) getBean(StringUtils.uncapitalize("HandicapAllowanceRequest") + "Service");
    }

    protected HandicapAllowanceRequest fillMeARequest() throws CvqException {

        HandicapAllowanceRequest request = new HandicapAllowanceRequest();
            if ("AdultRequesterFirstName".length() > 38)
        request.setAdultRequesterFirstName("AdultRequesterFirstName".substring(0, 38));
      else
        request.setAdultRequesterFirstName("AdultRequesterFirstName");
                  if ("LessThan20ReferentBirthPlaceCity".length() > 32)
        request.setLessThan20ReferentBirthPlaceCity("LessThan20ReferentBirthPlaceCity".substring(0, 32));
      else
        request.setLessThan20ReferentBirthPlaceCity("LessThan20ReferentBirthPlaceCity");
                          if ("LessThan20FatherMobilePhone".length() > 10)
        request.setLessThan20FatherMobilePhone("LessThan20FatherMobilePhone".substring(0, 10));
      else
        request.setLessThan20FatherMobilePhone("LessThan20FatherMobilePhone");
                  if ("LessThan20ReferentHomePhone".length() > 10)
        request.setLessThan20ReferentHomePhone("LessThan20ReferentHomePhone".substring(0, 10));
      else
        request.setLessThan20ReferentHomePhone("LessThan20ReferentHomePhone");
                        	    request.setLessThan20ReferentAddress(address);
                        request.setWritingHelp(Boolean.valueOf(true));
            request.setFamilyHasFamilyDependents(Boolean.valueOf(true));
                request.setLessThan20ParentalAuthorityDepartment("LessThan20ParentalAuthorityDepartment");
                  if ("LessThan20LegalRepresentativeHomePhone".length() > 10)
        request.setLessThan20LegalRepresentativeHomePhone("LessThan20LegalRepresentativeHomePhone".substring(0, 10));
      else
        request.setLessThan20LegalRepresentativeHomePhone("LessThan20LegalRepresentativeHomePhone");
                  if ("AdultRequesterLastName".length() > 38)
        request.setAdultRequesterLastName("AdultRequesterLastName".substring(0, 38));
      else
        request.setAdultRequesterLastName("AdultRequesterLastName");
                    request.setHelperResponsability("HelperResponsability");
                  request.setPaymentAgencyBeneficiary(HarPaymentAgencyBeneficiaryType.INSURED);
                    request.setLessThan20FatherJob("LessThan20FatherJob");
                request.setAdultRequesterBirthDate(new Date());
              request.setRequestInformationRequesterProfile(HarRequestInformationProfileType.ADULT);
                  request.setRequestInformationKind(HarRequestInformationKindType.FIRST);
                        	    request.setPaymentAgencyAddress(address);
                                	    request.setLessThan20FatherAddress(address);
                          if ("AdultRequesterBirthPlaceCity".length() > 32)
        request.setAdultRequesterBirthPlaceCity("AdultRequesterBirthPlaceCity".substring(0, 32));
      else
        request.setAdultRequesterBirthPlaceCity("AdultRequesterBirthPlaceCity");
                    request.setComments("Comments");
                    request.setAdultRequesterEmail("AdultRequesterEmail");
                        	    request.setSocialSecurityAgencyAddress(address);
                                	    request.setDwellingReceptionAddress(address);
                          if ("AdultRequesterMobilePhone".length() > 10)
        request.setAdultRequesterMobilePhone("AdultRequesterMobilePhone".substring(0, 10));
      else
        request.setAdultRequesterMobilePhone("AdultRequesterMobilePhone");
                  if ("LessThan20ReferentMobilePhone".length() > 10)
        request.setLessThan20ReferentMobilePhone("LessThan20ReferentMobilePhone".substring(0, 10));
      else
        request.setLessThan20ReferentMobilePhone("LessThan20ReferentMobilePhone");
                  if ("AdultLegalAccessRepresentativeLastName".length() > 38)
        request.setAdultLegalAccessRepresentativeLastName("AdultLegalAccessRepresentativeLastName".substring(0, 38));
      else
        request.setAdultLegalAccessRepresentativeLastName("AdultLegalAccessRepresentativeLastName");
                  request.setAdultLegalAccessKind(HarAdultLegalAccessKindType.SAUVEGARDE_JUSTICE);
                  if ("LessThan20ReferentFirstName".length() > 38)
        request.setLessThan20ReferentFirstName("LessThan20ReferentFirstName".substring(0, 38));
      else
        request.setLessThan20ReferentFirstName("LessThan20ReferentFirstName");
                  if ("LessThan20LegalRepresentativeFirstName".length() > 38)
        request.setLessThan20LegalRepresentativeFirstName("LessThan20LegalRepresentativeFirstName".substring(0, 38));
      else
        request.setLessThan20LegalRepresentativeFirstName("LessThan20LegalRepresentativeFirstName");
                  if ("LessThan20FatherFirstName".length() > 38)
        request.setLessThan20FatherFirstName("LessThan20FatherFirstName".substring(0, 38));
      else
        request.setLessThan20FatherFirstName("LessThan20FatherFirstName");
                  if ("AdultLegalAccessRepresentativeMobilePhone".length() > 10)
        request.setAdultLegalAccessRepresentativeMobilePhone("AdultLegalAccessRepresentativeMobilePhone".substring(0, 10));
      else
        request.setAdultLegalAccessRepresentativeMobilePhone("AdultLegalAccessRepresentativeMobilePhone");
                    request.setLessThan20MotherReductionRatio("LessThan20MotherReductionRatio");
                  if ("LessThan20RequesterMobilePhone".length() > 10)
        request.setLessThan20RequesterMobilePhone("LessThan20RequesterMobilePhone".substring(0, 10));
      else
        request.setLessThan20RequesterMobilePhone("LessThan20RequesterMobilePhone");
                  if ("LessThan20FatherHomePhone".length() > 10)
        request.setLessThan20FatherHomePhone("LessThan20FatherHomePhone".substring(0, 10));
      else
        request.setLessThan20FatherHomePhone("LessThan20FatherHomePhone");
                  request.setDwellingReceptionType(HarDwellingReceptionType.INTERNSHIP);
                    request.setLessThan20FatherReductionRatio("LessThan20FatherReductionRatio");
                        	    request.setAdultRequesterAddress(address);
                            request.setPaymentAgencyName("PaymentAgencyName");
                    request.setDwellingSocialReceptionNaming("DwellingSocialReceptionNaming");
                  request.setFamilyStatus(FamilyStatusType.MARRIED);
                        	    request.setLessThan20MotherAddress(address);
                            request.setLessThan20Requester(homeFolderWoman);
                  if ("LessThan20ParentalAuthorityName".length() > 38)
        request.setLessThan20ParentalAuthorityName("LessThan20ParentalAuthorityName".substring(0, 38));
      else
        request.setLessThan20ParentalAuthorityName("LessThan20ParentalAuthorityName");
                  if ("AdultLegalAccessRepresentativePrecision".length() > 50)
        request.setAdultLegalAccessRepresentativePrecision("AdultLegalAccessRepresentativePrecision".substring(0, 50));
      else
        request.setAdultLegalAccessRepresentativePrecision("AdultLegalAccessRepresentativePrecision");
                        	    request.setLessThan20LegalRepresentativeAddress(address);
                            request.setSocialSecurityAgencyName("SocialSecurityAgencyName");
                  request.setDwellingReceptionNaming(HarDwellingReceptionType.INTERNSHIP);
                  if ("LessThan20MotherFirstName".length() > 38)
        request.setLessThan20MotherFirstName("LessThan20MotherFirstName".substring(0, 38));
      else
        request.setLessThan20MotherFirstName("LessThan20MotherFirstName");
                  if ("SocialSecurityNumber".length() > 13)
        request.setSocialSecurityNumber("SocialSecurityNumber".substring(0, 13));
      else
        request.setSocialSecurityNumber("SocialSecurityNumber");
                  if ("LessThan20MotherMobilePhone".length() > 10)
        request.setLessThan20MotherMobilePhone("LessThan20MotherMobilePhone".substring(0, 10));
      else
        request.setLessThan20MotherMobilePhone("LessThan20MotherMobilePhone");
                request.setLessThan20MotherActivityReduction(Boolean.valueOf(true));
              if ("LessThan20ReferentLastName".length() > 38)
        request.setLessThan20ReferentLastName("LessThan20ReferentLastName".substring(0, 38));
      else
        request.setLessThan20ReferentLastName("LessThan20ReferentLastName");
                    request.setAdultLegalAccessRepresentativeEmail("AdultLegalAccessRepresentativeEmail");
                  request.setLessThan20ReferentBirthPlaceCountry(CountryType.UNKNOWN);
                    request.setPaymentAgencyBeneficiaryNumber("PaymentAgencyBeneficiaryNumber");
                request.setLessThan20LegalRepresentative(Boolean.valueOf(true));
              if ("AdultLegalAccessRepresentativeHomePhone".length() > 10)
        request.setAdultLegalAccessRepresentativeHomePhone("AdultLegalAccessRepresentativeHomePhone".substring(0, 10));
      else
        request.setAdultLegalAccessRepresentativeHomePhone("AdultLegalAccessRepresentativeHomePhone");
                  request.setAdultRequesterBirthPlaceCountry(CountryType.UNKNOWN);
                        	    request.setDwellingSocialReceptionAddress(address);
                        request.setRequestInformationExpirationDate(new Date());
              request.setAdultRequesterTitle(TitleType.MISTER);
                        	    request.setAdultLegalAccessRepresentativeAddress(address);
                          if ("LessThan20RequesterHomePhone".length() > 10)
        request.setLessThan20RequesterHomePhone("LessThan20RequesterHomePhone".substring(0, 10));
      else
        request.setLessThan20RequesterHomePhone("LessThan20RequesterHomePhone");
                  request.setAdultLegalAccessRepresentativeKind(HarAdultLegalAccessRepresentativeKindType.FAMILY_MEMBER);
                request.setLessThan20ReferentBirthDate(new Date());
            request.setAdultLegalAccessPresence(Boolean.valueOf(true));
            request.setDwellingEstablishmentReception(Boolean.valueOf(true));
                request.setLessThan20RequesterEmail("LessThan20RequesterEmail");
                request.setDwellingSocialReception(Boolean.valueOf(true));
            request.setHopesAndNeeds(Boolean.valueOf(true));
            request.setLessThan20FatherActivityReduction(Boolean.valueOf(true));
              if ("LessThan20FatherLastName".length() > 38)
        request.setLessThan20FatherLastName("LessThan20FatherLastName".substring(0, 38));
      else
        request.setLessThan20FatherLastName("LessThan20FatherLastName");
                  if ("LessThan20MotherLastName".length() > 38)
        request.setLessThan20MotherLastName("LessThan20MotherLastName".substring(0, 38));
      else
        request.setLessThan20MotherLastName("LessThan20MotherLastName");
                  if ("LessThan20MotherHomePhone".length() > 10)
        request.setLessThan20MotherHomePhone("LessThan20MotherHomePhone".substring(0, 10));
      else
        request.setLessThan20MotherHomePhone("LessThan20MotherHomePhone");
                    request.setDwellingPrecision("DwellingPrecision");
                  request.setLessThan20ParentalAuthorityHolder(HarLessThan20ParentalAuthorityHolderType.FATHER);
                  if ("LessThan20LegalRepresentativeMobilePhone".length() > 10)
        request.setLessThan20LegalRepresentativeMobilePhone("LessThan20LegalRepresentativeMobilePhone".substring(0, 10));
      else
        request.setLessThan20LegalRepresentativeMobilePhone("LessThan20LegalRepresentativeMobilePhone");
                  request.setSocialSecurityMemberShipKind(HarSocialSecurityMemberShipKindType.INSURED);
                  request.setLessThan20ReferentTitle(TitleType.MISTER);
                    request.setNeeds("Needs");
                    request.setLessThan20ReferentEmail("LessThan20ReferentEmail");
                    request.setLessThan20MotherJob("LessThan20MotherJob");
                  if ("AdultLegalAccessRepresentativeFirstName".length() > 38)
        request.setAdultLegalAccessRepresentativeFirstName("AdultLegalAccessRepresentativeFirstName".substring(0, 38));
      else
        request.setAdultLegalAccessRepresentativeFirstName("AdultLegalAccessRepresentativeFirstName");
                  if ("LessThan20LegalRepresentativeLastName".length() > 38)
        request.setLessThan20LegalRepresentativeLastName("LessThan20LegalRepresentativeLastName".substring(0, 38));
      else
        request.setLessThan20LegalRepresentativeLastName("LessThan20LegalRepresentativeLastName");
                  if ("HelperName".length() > 38)
        request.setHelperName("HelperName".substring(0, 38));
      else
        request.setHelperName("HelperName");
                  request.setDwellingKind(HarDwellingKindType.DOMICILE);
                  if ("AdultRequesterHomePhone".length() > 10)
        request.setAdultRequesterHomePhone("AdultRequesterHomePhone".substring(0, 10));
      else
        request.setAdultRequesterHomePhone("AdultRequesterHomePhone");
                    request.setHopes("Hopes");
      
        // Means Of Contact
        MeansOfContact meansOfContact = iMeansOfContactService.getMeansOfContactByType(
                    MeansOfContactEnum.EMAIL);
        request.setMeansOfContact(meansOfContact);
        
        HandicapAllowanceRequestFeeder.feed(request);
        
        return request;
    }
        	
    protected void completeValidateAndDelete(HandicapAllowanceRequest request) 
    	throws CvqException, java.io.IOException {
    	
        // add a document to the request
        ///////////////////////////////

        Document doc = new Document();
        doc.setEcitizenNote("Ma carte d'identitÃ© !");
        doc.setDepositOrigin(DepositOrigin.ECITIZEN);
        doc.setDepositType(DepositType.PC);
        doc.setHomeFolderId(request.getHomeFolderId());
        doc.setIndividualId(request.getRequesterId());
        doc.setDocumentType(iDocumentTypeService.getDocumentTypeByType(IDocumentTypeService.IDENTITY_RECEIPT_TYPE));
        Long documentId = iDocumentService.create(doc);
        iHandicapAllowanceRequestService.addDocument(request.getId(), documentId);
        Set<RequestDocument> documentsSet =
            iHandicapAllowanceRequestService.getAssociatedDocuments(request.getId());
        assertEquals(documentsSet.size(), 1);

        // FIXME : test list of pending / in-progress registrations
        Critere testCrit = new Critere();
        testCrit.setAttribut(Request.SEARCH_BY_HOME_FOLDER_ID);
        testCrit.setComparatif(Critere.EQUALS);
        testCrit.setValue(request.getHomeFolderId());
        Set<Critere> testCritSet = new HashSet<Critere>();
        testCritSet.add(testCrit);
        List<Request> allRequests = iRequestService.get(testCritSet, null, null, -1, 0);
        assertNotNull(allRequests);

        // close current session and re-open a new one
        continueWithNewTransaction();
        
        SecurityContext.setCurrentSite(localAuthorityName,
                                        SecurityContext.BACK_OFFICE_CONTEXT);
        SecurityContext.setCurrentAgent(agentNameWithCategoriesRoles);
        iHandicapAllowanceRequestService.complete(request.getId());
        iHandicapAllowanceRequestService.validate(request.getId());

        // close current session and re-open a new one
        continueWithNewTransaction();
        
        byte[] generatedCertificate = iRequestService.getCertificate(request.getId(),
                                                                     RequestState.PENDING);

        if (generatedCertificate == null)
            fail("No certificate found");
            
        //     Write tele-service xml data file
        File xmlFile = File.createTempFile("tmp" + request.getId(), ".xml");
        FileOutputStream xmlFos = new FileOutputStream(xmlFile);
        xmlFos.write(iRequestService.getById(request.getId()).modelToXmlString().getBytes());

        File file = File.createTempFile("tmp" + request.getId(), ".pdf");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(generatedCertificate);

        // close current session and re-open a new one
        continueWithNewTransaction();
        
        // delete request
        iHandicapAllowanceRequestService.delete(request.getId());
    }

    public void testWithHomeFolderPojo()
    		throws CvqException, CvqObjectNotFoundException,
                java.io.FileNotFoundException, java.io.IOException {

         SecurityContext.setCurrentSite(localAuthorityName, SecurityContext.FRONT_OFFICE_CONTEXT);

         // create a vo card request (to create home folder and associates)
         CreationBean cb = gimmeAnHomeFolder();

         SecurityContext.setCurrentEcitizen(cb.getLogin());

         // get the home folder id
         HomeFolder homeFolder = iHomeFolderService.getById(cb.getHomeFolderId());
         assertNotNull(homeFolder);
         Long homeFolderId = homeFolder.getId();
         assertNotNull(homeFolderId);

         // fill and create the request
         //////////////////////////////

         HandicapAllowanceRequest request = fillMeARequest();
         request.setRequesterId(SecurityContext.getCurrentUserId());
         request.setHomeFolderId(homeFolderId);
         HandicapAllowanceRequestFeeder.setSubject(request, 
             iHandicapAllowanceRequestService.getSubjectPolicy(), null, homeFolder);
         
         Long requestId =
              iHandicapAllowanceRequestService.create(request);

         HandicapAllowanceRequest requestFromDb =
        	 	(HandicapAllowanceRequest) iHandicapAllowanceRequestService.getById(requestId);
         assertEquals(requestId, requestFromDb.getId());
         assertNotNull(requestFromDb.getRequesterId());
         assertNotNull(requestFromDb.getRequesterLastName());
         if (requestFromDb.getSubjectId() != null)
             assertNotNull(requestFromDb.getSubjectLastName());
         
         completeValidateAndDelete(requestFromDb);

         HomeFolder homeFolderAfterDelete = iHomeFolderService.getById(homeFolderId);
         assertNotNull(homeFolderAfterDelete);
         assertNotNull(iHomeFolderService.getHomeFolderResponsible(homeFolderAfterDelete.getId()));
         
         SecurityContext.resetCurrentSite();
    }


    public void testWithoutHomeFolder()
        throws CvqException, CvqObjectNotFoundException,
               java.io.FileNotFoundException, java.io.IOException {

	      if (!iHandicapAllowanceRequestService.supportUnregisteredCreation())
	         return;

	      startTransaction();
	
        SecurityContext.setCurrentSite(localAuthorityName, SecurityContext.FRONT_OFFICE_CONTEXT);
        
        HandicapAllowanceRequest request = fillMeARequest();

        Address address = BusinessObjectsFactory.gimmeAdress("12", "Rue d'Aligre", "Paris", "75012");
        Adult requester =
            BusinessObjectsFactory.gimmeAdult(TitleType.MISTER, "LASTNAME", "requester", address,
                                              FamilyStatusType.MARRIED);
        requester.setPassword("requester");
        requester.setAdress(address);
        iHomeFolderService.addHomeFolderRole(requester, RoleEnum.HOME_FOLDER_RESPONSIBLE);
        HandicapAllowanceRequestFeeder.setSubject(request, 
            iHandicapAllowanceRequestService.getSubjectPolicy(), requester, null);

        Long requestId =
             iHandicapAllowanceRequestService.create(request, requester, requester);
        
        // close current session and re-open a new one
        continueWithNewTransaction();
        
        // start testing request creation
        /////////////////////////////////

        HandicapAllowanceRequest requestFromDb =
            (HandicapAllowanceRequest) iHandicapAllowanceRequestService.getById(requestId);
        assertEquals(requestId, requestFromDb.getId());
        assertNotNull(requestFromDb.getRequesterId());
        assertNotNull(requestFromDb.getRequesterLastName());
        if (requestFromDb.getSubjectId() != null)
            assertNotNull(requestFromDb.getSubjectLastName());
        
        Long homeFolderId = requestFromDb.getHomeFolderId();
        Long requesterId = requestFromDb.getRequesterId();

        // close current session and re-open a new one
        continueWithNewTransaction();
        
        completeValidateAndDelete(requestFromDb);
        
        // close current session and re-open a new one
        continueWithNewTransaction();
        
        try {
            iHomeFolderService.getById(homeFolderId);
            fail("should not have found home folder");
        } catch (CvqObjectNotFoundException confe) {
            // great, that was expected
        }
        try {
            iIndividualService.getById(requesterId);
            fail("should not have found requester");
        } catch (CvqObjectNotFoundException confe) {
            // great, that was expected
        }

        SecurityContext.resetCurrentSite();
        
        commitTransaction();
    }
}
