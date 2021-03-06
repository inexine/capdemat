package fr.capwebct.capdemat.plugins.externalservices.capwebctpaymentmodule.webservice.client;

public interface ICapwebctPaymentModuleClient {

    Object loadInvoiceDetails(Object requestPayload);

    Object loadAccountDetails(Object requestPayload);

    Object getFamilyAccounts(Object requestPayload);

    Object creditAccount(Object requestPayload);

    Object sendRequest(Object requestPayload);

    Object checkExternalReferential(Object requestPayload);

    Object loadExternalInformation(Object requestPayload);

    Object getConsumptions(Object requestPayload);
    
    void setFake(boolean fake);
}
