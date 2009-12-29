package fr.cg95.cvq.service.request.school.impl;

import fr.cg95.cvq.business.users.SectionType;
import fr.cg95.cvq.business.request.Request;
import fr.cg95.cvq.business.request.school.SchoolRegistrationRequest;
import fr.cg95.cvq.exception.CvqException;
import fr.cg95.cvq.exception.CvqModelException;
import fr.cg95.cvq.security.SecurityContext;
import fr.cg95.cvq.service.request.impl.RequestService;
import fr.cg95.cvq.service.request.school.ISchoolRegistrationRequestService;

/**
 * Implementation of the school registration request service.
 *
 * @author Benoit Orihuela (bor@zenexity.fr)
 */
public final class SchoolRegistrationRequestService
    extends RequestService implements ISchoolRegistrationRequestService {

    @Override
    public void onRequestValidated(final Request request)
        throws CvqModelException {

        // check school association has been done before validating request
        SchoolRegistrationRequest srr = (SchoolRegistrationRequest) request;
        if (srr.getSchool() == null)
            throw new CvqModelException("srr.property.school.validationError");
        if (srr.getSection().equals(SectionType.UNKNOWN))
            throw new CvqModelException("srr.property.section.validationError");
    }

    @Override
    public boolean accept(final Request request) {
        return request instanceof SchoolRegistrationRequest;
    }

    @Override
    public Request getSkeletonRequest() throws CvqException {
        SchoolRegistrationRequest request =
            new SchoolRegistrationRequest();
        if (SecurityContext.getCurrentEcitizen() != null)
            request.setUrgencyPhone(SecurityContext.getCurrentEcitizen().getOfficePhone());
        return request;
    }
}
