package fr.cg95.cvq.external.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import fr.cg95.cvq.business.request.Request;
import fr.cg95.cvq.business.users.Individual;
import fr.cg95.cvq.business.users.payment.ExternalAccountItem;
import fr.cg95.cvq.business.users.payment.ExternalDepositAccountItem;
import fr.cg95.cvq.business.users.payment.ExternalInvoiceItem;
import fr.cg95.cvq.business.users.payment.PurchaseItem;
import fr.cg95.cvq.exception.CvqConfigurationException;
import fr.cg95.cvq.exception.CvqException;
import fr.cg95.cvq.external.ExternalServiceBean;
import fr.cg95.cvq.external.IExternalProviderService;

/**
 * Empty implementation of the {@link IExternalProviderService external provider service interface}
 * that is meant to be used by external services who wish to retrieve requests from
 * CapDemat (instead of waiting for CapDemat to send them).
 *  
 */
public class EntryPointExternalService implements IExternalProviderService {
    private String label;
    
    public void checkConfiguration(ExternalServiceBean externalServiceBean)
            throws CvqConfigurationException {
    }
    
    public void creditHomeFolderAccounts(Collection<PurchaseItem> purchaseItems,
            String cvqReference, String bankReference, Long homeFolderId, String externalHomeFolderId, String externalId, Date validationDate)
            throws CvqException {
    }

    public Map<String, List<ExternalAccountItem>> getAccountsByHomeFolder(Long homeFolderId, String externalHomeFolderId, String externalId)
            throws CvqException {
        return null;
    }
    
    public Map<Date, String> getConsumptionsByRequest(Request request, Date dateFrom, Date dateTo)
            throws CvqException {
        return null;
    }
    
    public Map<Individual, Map<String, String>> getIndividualAccountsInformation(Long homeFolderId, String externalHomeFolderId, String externalId)
            throws CvqException {
        return null;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return label;
    }
    
    public String helloWorld() throws CvqException {
        return null;
    }
    
    public void loadDepositAccountDetails(ExternalDepositAccountItem edai) throws CvqException {
    }
    
    public void loadInvoiceDetails(ExternalInvoiceItem eii) throws CvqException {
    }
    
    public String sendRequest(Request request) throws CvqException {
        return null;
    }    
}