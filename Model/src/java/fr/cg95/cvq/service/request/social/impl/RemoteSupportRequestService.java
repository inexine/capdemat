package fr.cg95.cvq.service.request.social.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import fr.cg95.cvq.business.request.Request;
import fr.cg95.cvq.business.request.social.RemoteSupportRequest;
import fr.cg95.cvq.exception.CvqException;
import fr.cg95.cvq.exception.CvqObjectNotFoundException;
import fr.cg95.cvq.service.request.condition.EqualityChecker;
import fr.cg95.cvq.service.request.condition.IConditionChecker;
import fr.cg95.cvq.service.request.impl.RequestService;
import fr.cg95.cvq.service.request.social.IRemoteSupportRequestService;


/**
 * Implementation of the remote support request service.
 * 
 * @author Rafik Djedjig (rdj@zenexity.fr)
 */
public class RemoteSupportRequestService extends RequestService 
    implements IRemoteSupportRequestService {
    
    @Override
    public Long create(final Request request) throws CvqException, CvqObjectNotFoundException {

        RemoteSupportRequest rsr = (RemoteSupportRequest) request;
        performBusinessChecks(rsr);

        return finalizeAndPersist(rsr);
    }
    
    @Override
    public boolean accept(Request request) {
        return request instanceof RemoteSupportRequest;
    }

    @Override
    public Request getSkeletonRequest() throws CvqException {
        return new RemoteSupportRequest();
    }
    
    public final Map<String,IConditionChecker> filledConditions = new HashMap<String,IConditionChecker>();
    private void initFilledConditions() {
        filledConditions.put("requestInformationRequestKind", new EqualityChecker("Couple"));
        filledConditions.put("requestInformationEmergency", new EqualityChecker("true"));
        filledConditions.put("contactKind", new EqualityChecker("Other"));
    }
   
    /**
     * TODO - move to abstract RequestService
     */
    @Override
    public boolean isConditionFilled (Map<String, String> triggers) {
        initFilledConditions();
        boolean test = true;
        for (Entry<String, String> trigger : triggers.entrySet()) {
            if (filledConditions.get(trigger.getKey()) != null 
                && filledConditions.get(trigger.getKey()).test(trigger.getValue()))
                test = test && true;
            else
                return false;
        }
        return test;
    }
    
}
