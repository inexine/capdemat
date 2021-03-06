package fr.cg95.cvq.service.request.impl;

import fr.cg95.cvq.business.request.Request;
import fr.cg95.cvq.dao.IGenericDAO;
import fr.cg95.cvq.exception.CvqException;
import fr.cg95.cvq.service.request.IRequestService;
import fr.cg95.cvq.service.request.IRequestWorkflowService;

/**
 * Partial implementation of the {@link IRequestService}, only provides functionalities
 * common to all request types and default implementations of un-necessary extension points.
 *
 * @author Benoit Orihuela (bor@zenexity.fr)
 */
public abstract class RequestService implements IRequestService {

    protected String localReferentialFilename;
    protected String placeReservationFilename;
    protected String externalReferentialFilename;
    protected Boolean supportUnregisteredCreation = Boolean.FALSE;
    protected String subjectPolicy = IRequestWorkflowService.SUBJECT_POLICY_NONE;
    protected String label;
    protected Boolean isOfRegistrationKind;
    protected String defaultDisplayGroup;
    protected int filingDelay;
    protected boolean archiveDocuments;

    protected IGenericDAO genericDAO;

    public void init() {
        // empty method to avoid breaking specific services instantiation by spring
    }

    @Override
    public void onRequestCreated(Request request) throws CvqException {
    }

    @Override
    public void onRequestModified(Request request) throws CvqException {
    }

    @Override
    public void onRequestValidated(Request request)
        throws CvqException {
    }

    @Override
    public void onRequestCancelled(Request request) throws CvqException {
    }

    @Override
    public void onRequestRejected(Request request) throws CvqException {
    }

    @Override
    public boolean onPaymentValidated(Request request, String paymentReference)
        throws CvqException {
        return false;
    }

    @Override
    public boolean onPaymentRefused(Request request)
        throws CvqException {
        return false;
    }

    @Override
    public boolean onPaymentCancelled(Request request)
        throws CvqException {
        return false;
    }

    @Override
    public void onExternalServiceSendRequest(Request request, String sendRequestResult) throws CvqException {
    }

    @Override
    public String getLocalReferentialFilename() {
        return this.localReferentialFilename;
    }

    public void setLocalReferentialFilename(final String filename) {
        this.localReferentialFilename = filename;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public void setLabel(final String label) {
        this.label = label;
    }

    public void setSupportUnregisteredCreation(String supportUnregisteredCreation) {
        this.supportUnregisteredCreation = Boolean.valueOf(supportUnregisteredCreation);
    }

    @Override
    public boolean supportUnregisteredCreation() {
        return supportUnregisteredCreation == null ? false : supportUnregisteredCreation;
    }

    public void setPlaceReservationFilename(String placeReservationFilename) {
        this.placeReservationFilename = placeReservationFilename;
    }

    @Override
    public String getPlaceReservationFilename() {
        return placeReservationFilename;
    }

    public void setExternalReferentialFilename(String externalReferentialFilename) {
        this.externalReferentialFilename = externalReferentialFilename;
    }

    @Override
    public String getExternalReferentialFilename() {
        return externalReferentialFilename;
    }

    @Override
    public String getSubjectPolicy() {
        return subjectPolicy;
    }

    public void setSubjectPolicy(final String subjectPolicy) {
        this.subjectPolicy = subjectPolicy;
    }

    @Override
    public boolean isOfRegistrationKind() {
        return isOfRegistrationKind == null ? false : isOfRegistrationKind;
    }

    public void setIsOfRegistrationKind(String isOfRegistrationKind) {
        this.isOfRegistrationKind = Boolean.valueOf(isOfRegistrationKind);
    }

    @Override
	public String getDefaultDisplayGroup() {
        return defaultDisplayGroup;
    }

    public void setDefaultDisplayGroup(String defaultDisplayGroup) {
        this.defaultDisplayGroup = defaultDisplayGroup;
    }

    public void setGenericDAO(IGenericDAO genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Override
    public int getFilingDelay() {
        return filingDelay;
    }

    public void setFilingDelay(int filingDelay) {
        if (filingDelay < 1 || filingDelay > 36) {
            throw new RuntimeException("Filing delay must be between 1 and 36 months");
        }
        this.filingDelay = filingDelay;
    }

    @Override
    public boolean isArchiveDocuments() {
        return archiveDocuments;
    }

    public void setArchiveDocuments(boolean archiveDocuments) {
        this.archiveDocuments = archiveDocuments;
    }
}
