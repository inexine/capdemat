package fr.cg95.cvq.service.request;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.cg95.cvq.business.request.Request;
import fr.cg95.cvq.business.users.CreationBean;
import fr.cg95.cvq.exception.CvqException;
import fr.cg95.cvq.security.SecurityContext;
import fr.cg95.cvq.util.Critere;

public class RequestSearchServiceTest extends RequestTestCase {

    public void testSearch() throws CvqException {
        
        CreationBean cb = gimmeAnHomeFolderWithRequest();
        Long homeFolderId = cb.getHomeFolderId();
        
        continueWithNewTransaction();
        
        SecurityContext.setCurrentSite(localAuthorityName, SecurityContext.BACK_OFFICE_CONTEXT);
        SecurityContext.setCurrentAgent(agentNameWithCategoriesRoles);

        Set<Critere> criteriaSet = new HashSet<Critere>();
        Critere crit = new Critere();
        crit.setAttribut(Request.SEARCH_BY_REQUESTER_LASTNAME);
        crit.setComparatif(Critere.EQUALS);
        crit.setValue(homeFolderResponsible.getLastName());
        criteriaSet.add(crit);
        Critere crit2 = new Critere();
        crit2.setAttribut(Request.SEARCH_BY_CREATION_DATE);
        crit2.setComparatif(Critere.LT);
        crit2.setValue(new Date());
        criteriaSet.add(crit2);
        Critere crit3 = new Critere();
        crit3.setAttribut(Request.SEARCH_BY_HOME_FOLDER_ID);
        crit3.setComparatif(Critere.EQUALS);
        crit3.setValue(homeFolderId);
        criteriaSet.add(crit3);
        List<Request> carteVoList = requestSearchService.get(criteriaSet, null, null, -1, 0);
        assertEquals(carteVoList.size(),1);

        crit = new Critere();
        crit.setAttribut(Request.SEARCH_BY_REQUESTER_LASTNAME);
        crit.setComparatif(Critere.EQUALS);
        crit.setValue("connaispascegarsla!");
        criteriaSet = new HashSet<Critere>();
        criteriaSet.add(crit);
        carteVoList = requestSearchService.get(criteriaSet, null, null, -1, 0);
        assertEquals(carteVoList.size(),0);

        crit = new Critere();
        crit.setAttribut(Request.SEARCH_BY_REQUEST_ID);
        crit.setComparatif(Critere.EQUALS);
        crit.setValue(cb.getRequestId());
        criteriaSet = new HashSet<Critere>();
        criteriaSet.add(crit);
        carteVoList = requestSearchService.get(criteriaSet, null, null, -1, 0);
        assertEquals(carteVoList.size(), 1);

        crit = new Critere();
        crit.setAttribut(Request.SEARCH_BY_HOME_FOLDER_ID);
        crit.setComparatif(Critere.NEQUALS);
        crit.setValue(String.valueOf(homeFolderId));
        criteriaSet = new HashSet<Critere>();
        criteriaSet.add(crit);
        carteVoList = requestSearchService.get(criteriaSet, null, null, -1, 0);
        for (Request req : carteVoList) {
            assertFalse(req.getId() == homeFolderId);
        }
    }
}
