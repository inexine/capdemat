package fr.cg95.cvq.business.request.school;

import fr.cg95.cvq.business.request.*;
import fr.cg95.cvq.business.users.*;
import fr.cg95.cvq.business.authority.*;
import fr.cg95.cvq.xml.common.*;
import fr.cg95.cvq.xml.request.school.*;

import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlObject;

import fr.cg95.cvq.xml.common.RequestType;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;

/**
 * Generated class file, do not edit !
 *
 * @hibernate.joined-subclass
 *  table="recreation_activity_registration_request"
 *  lazy="false"
 * @hibernate.joined-subclass-key
 *  column="id"
 */
public class RecreationActivityRegistrationRequest extends Request implements Serializable { 

    private static final long serialVersionUID = 1L;



    public RecreationActivityRegistrationRequest() {
        super();
        classTripPermission = Boolean.valueOf(false);
        childPhotoExploitationPermission = Boolean.valueOf(false);
        hospitalizationPermission = Boolean.valueOf(false);
        rulesAndRegulationsAcceptance = Boolean.valueOf(false);
    }


    public final String modelToXmlString() {

        RecreationActivityRegistrationRequestDocument object = (RecreationActivityRegistrationRequestDocument) this.modelToXml();
        XmlOptions opts = new XmlOptions();
        opts.setSavePrettyPrint();
        opts.setSavePrettyPrintIndent(4);
        opts.setUseDefaultNamespace();
        opts.setCharacterEncoding("UTF-8");
        return object.xmlText(opts);
    }

    public final XmlObject modelToXml() {

        Calendar calendar = Calendar.getInstance();
        Date date = null;
        RecreationActivityRegistrationRequestDocument recreationActivityRegistrationRequestDoc = RecreationActivityRegistrationRequestDocument.Factory.newInstance();
        RecreationActivityRegistrationRequestDocument.RecreationActivityRegistrationRequest recreationActivityRegistrationRequest = recreationActivityRegistrationRequestDoc.addNewRecreationActivityRegistrationRequest();
        super.fillCommonXmlInfo(recreationActivityRegistrationRequest);
        if (this.recreationCenter != null)
            recreationActivityRegistrationRequest.setRecreationCenter(RecreationCenter.modelToXml(this.recreationCenter));
        int i = 0;
        if (authorizedIndividuals != null) {
            fr.cg95.cvq.xml.request.school.RecreationAuthorizedIndividualType[] authorizedIndividualsTypeTab = new fr.cg95.cvq.xml.request.school.RecreationAuthorizedIndividualType[authorizedIndividuals.size()];
            Iterator authorizedIndividualsIt = authorizedIndividuals.iterator();
            while (authorizedIndividualsIt.hasNext()) {
                RecreationAuthorizedIndividual object = (RecreationAuthorizedIndividual) authorizedIndividualsIt.next();
                authorizedIndividualsTypeTab[i] = (RecreationAuthorizedIndividualType) object.modelToXml();
                i = i + 1;
            }
            recreationActivityRegistrationRequest.setAuthorizedIndividualsArray(authorizedIndividualsTypeTab);
        }
        i = 0;
        if (contactIndividuals != null) {
            fr.cg95.cvq.xml.request.school.RecreationContactIndividualType[] contactIndividualsTypeTab = new fr.cg95.cvq.xml.request.school.RecreationContactIndividualType[contactIndividuals.size()];
            Iterator contactIndividualsIt = contactIndividuals.iterator();
            while (contactIndividualsIt.hasNext()) {
                RecreationContactIndividual object = (RecreationContactIndividual) contactIndividualsIt.next();
                contactIndividualsTypeTab[i] = (RecreationContactIndividualType) object.modelToXml();
                i = i + 1;
            }
            recreationActivityRegistrationRequest.setContactIndividualsArray(contactIndividualsTypeTab);
        }
        if (this.classTripPermission != null)
            recreationActivityRegistrationRequest.setClassTripPermission(this.classTripPermission.booleanValue());
        i = 0;
        if (recreationActivity != null) {
            fr.cg95.cvq.xml.common.LocalReferentialDataType[] recreationActivityTypeTab = new fr.cg95.cvq.xml.common.LocalReferentialDataType[recreationActivity.size()];
            Iterator recreationActivityIt = recreationActivity.iterator();
            while (recreationActivityIt.hasNext()) {
                LocalReferentialData object = (LocalReferentialData) recreationActivityIt.next();
                recreationActivityTypeTab[i] = LocalReferentialData.modelToXml(object);
                i = i + 1;
            }
            recreationActivityRegistrationRequest.setRecreationActivityArray(recreationActivityTypeTab);
        }
        if (this.childPhotoExploitationPermission != null)
            recreationActivityRegistrationRequest.setChildPhotoExploitationPermission(this.childPhotoExploitationPermission.booleanValue());
        if (this.hospitalizationPermission != null)
            recreationActivityRegistrationRequest.setHospitalizationPermission(this.hospitalizationPermission.booleanValue());
        if (this.rulesAndRegulationsAcceptance != null)
            recreationActivityRegistrationRequest.setRulesAndRegulationsAcceptance(this.rulesAndRegulationsAcceptance.booleanValue());
        recreationActivityRegistrationRequest.setUrgencyPhone(this.urgencyPhone);
        return recreationActivityRegistrationRequestDoc;
    }

    @Override
    public RequestType modelToXmlRequest() {
        RecreationActivityRegistrationRequestDocument recreationActivityRegistrationRequestDoc =
            (RecreationActivityRegistrationRequestDocument) modelToXml();
        return recreationActivityRegistrationRequestDoc.getRecreationActivityRegistrationRequest();
    }

    public static RecreationActivityRegistrationRequest xmlToModel(RecreationActivityRegistrationRequestDocument recreationActivityRegistrationRequestDoc) {

        RecreationActivityRegistrationRequestDocument.RecreationActivityRegistrationRequest recreationActivityRegistrationRequestXml = recreationActivityRegistrationRequestDoc.getRecreationActivityRegistrationRequest();
        Calendar calendar = Calendar.getInstance();
        List list = new ArrayList();
        RecreationActivityRegistrationRequest recreationActivityRegistrationRequest = new RecreationActivityRegistrationRequest();
        recreationActivityRegistrationRequest.fillCommonModelInfo(recreationActivityRegistrationRequest,recreationActivityRegistrationRequestXml);
        if (recreationActivityRegistrationRequestXml.getRecreationCenter() != null)
            recreationActivityRegistrationRequest.setRecreationCenter(RecreationCenter.xmlToModel(recreationActivityRegistrationRequestXml.getRecreationCenter()));
        List<fr.cg95.cvq.business.request.school.RecreationAuthorizedIndividual> authorizedIndividualsList = new ArrayList<fr.cg95.cvq.business.request.school.RecreationAuthorizedIndividual> ();
        if ( recreationActivityRegistrationRequestXml.sizeOfAuthorizedIndividualsArray() > 0) {
            for (int i = 0; i < recreationActivityRegistrationRequestXml.getAuthorizedIndividualsArray().length; i++) {
                authorizedIndividualsList.add(RecreationAuthorizedIndividual.xmlToModel(recreationActivityRegistrationRequestXml.getAuthorizedIndividualsArray(i)));
            }
        }
        recreationActivityRegistrationRequest.setAuthorizedIndividuals(authorizedIndividualsList);
        List<fr.cg95.cvq.business.request.school.RecreationContactIndividual> contactIndividualsList = new ArrayList<fr.cg95.cvq.business.request.school.RecreationContactIndividual> ();
        if ( recreationActivityRegistrationRequestXml.sizeOfContactIndividualsArray() > 0) {
            for (int i = 0; i < recreationActivityRegistrationRequestXml.getContactIndividualsArray().length; i++) {
                contactIndividualsList.add(RecreationContactIndividual.xmlToModel(recreationActivityRegistrationRequestXml.getContactIndividualsArray(i)));
            }
        }
        recreationActivityRegistrationRequest.setContactIndividuals(contactIndividualsList);
        recreationActivityRegistrationRequest.setClassTripPermission(Boolean.valueOf(recreationActivityRegistrationRequestXml.getClassTripPermission()));
        List<fr.cg95.cvq.business.users.LocalReferentialData> recreationActivityList = new ArrayList<fr.cg95.cvq.business.users.LocalReferentialData> ();
        if ( recreationActivityRegistrationRequestXml.sizeOfRecreationActivityArray() > 0) {
            for (int i = 0; i < recreationActivityRegistrationRequestXml.getRecreationActivityArray().length; i++) {
                recreationActivityList.add(LocalReferentialData.xmlToModel(recreationActivityRegistrationRequestXml.getRecreationActivityArray(i)));
            }
        }
        recreationActivityRegistrationRequest.setRecreationActivity(recreationActivityList);
        recreationActivityRegistrationRequest.setChildPhotoExploitationPermission(Boolean.valueOf(recreationActivityRegistrationRequestXml.getChildPhotoExploitationPermission()));
        recreationActivityRegistrationRequest.setHospitalizationPermission(Boolean.valueOf(recreationActivityRegistrationRequestXml.getHospitalizationPermission()));
        recreationActivityRegistrationRequest.setRulesAndRegulationsAcceptance(Boolean.valueOf(recreationActivityRegistrationRequestXml.getRulesAndRegulationsAcceptance()));
        recreationActivityRegistrationRequest.setUrgencyPhone(recreationActivityRegistrationRequestXml.getUrgencyPhone());
        return recreationActivityRegistrationRequest;
    }

    private fr.cg95.cvq.business.authority.RecreationCenter recreationCenter;

    public final void setRecreationCenter(final fr.cg95.cvq.business.authority.RecreationCenter recreationCenter) {
        this.recreationCenter = recreationCenter;
    }


    /**
     * @hibernate.many-to-one
     *  column="recreation_center_id"
     *  class="fr.cg95.cvq.business.authority.RecreationCenter"
     */
    public final fr.cg95.cvq.business.authority.RecreationCenter getRecreationCenter() {
        return this.recreationCenter;
    }

    private List<fr.cg95.cvq.business.request.school.RecreationAuthorizedIndividual> authorizedIndividuals;

    public final void setAuthorizedIndividuals(final List<fr.cg95.cvq.business.request.school.RecreationAuthorizedIndividual> authorizedIndividuals) {
        this.authorizedIndividuals = authorizedIndividuals;
    }


    /**
     * @hibernate.list
     *  inverse="false"
     *  lazy="false"
     *  cascade="all"
     * @hibernate.key
     *  column="recreation_activity_registration_request_id"
     * @hibernate.list-index
     *  column="authorized_individuals_index"
     * @hibernate.one-to-many
     *  class="fr.cg95.cvq.business.request.school.RecreationAuthorizedIndividual"
     */
    public final List<fr.cg95.cvq.business.request.school.RecreationAuthorizedIndividual> getAuthorizedIndividuals() {
        return this.authorizedIndividuals;
    }

    private List<fr.cg95.cvq.business.request.school.RecreationContactIndividual> contactIndividuals;

    public final void setContactIndividuals(final List<fr.cg95.cvq.business.request.school.RecreationContactIndividual> contactIndividuals) {
        this.contactIndividuals = contactIndividuals;
    }


    /**
     * @hibernate.list
     *  inverse="false"
     *  lazy="false"
     *  cascade="all"
     * @hibernate.key
     *  column="recreation_activity_registration_request_id"
     * @hibernate.list-index
     *  column="contact_individuals_index"
     * @hibernate.one-to-many
     *  class="fr.cg95.cvq.business.request.school.RecreationContactIndividual"
     */
    public final List<fr.cg95.cvq.business.request.school.RecreationContactIndividual> getContactIndividuals() {
        return this.contactIndividuals;
    }

    private Boolean classTripPermission;

    public final void setClassTripPermission(final Boolean classTripPermission) {
        this.classTripPermission = classTripPermission;
    }


    /**
     * @hibernate.property
     *  column="class_trip_permission"
     */
    public final Boolean getClassTripPermission() {
        return this.classTripPermission;
    }

    private List<fr.cg95.cvq.business.users.LocalReferentialData> recreationActivity;

    public final void setRecreationActivity(final List<fr.cg95.cvq.business.users.LocalReferentialData> recreationActivity) {
        this.recreationActivity = recreationActivity;
    }


    /**
     * @hibernate.list
     *  inverse="false"
     *  lazy="false"
     *  cascade="all"
     *  table="recreation_activity_registration_request_recreation_activity"
     * @hibernate.key
     *  column="recreation_activity_registration_request_id"
     * @hibernate.list-index
     *  column="recreation_activity_index"
     * @hibernate.many-to-many
     *  column="recreation_activity_id"
     *  class="fr.cg95.cvq.business.users.LocalReferentialData"
     */
    public final List<fr.cg95.cvq.business.users.LocalReferentialData> getRecreationActivity() {
        return this.recreationActivity;
    }

    private Boolean childPhotoExploitationPermission;

    public final void setChildPhotoExploitationPermission(final Boolean childPhotoExploitationPermission) {
        this.childPhotoExploitationPermission = childPhotoExploitationPermission;
    }


    /**
     * @hibernate.property
     *  column="child_photo_exploitation_permission"
     */
    public final Boolean getChildPhotoExploitationPermission() {
        return this.childPhotoExploitationPermission;
    }

    private Boolean hospitalizationPermission;

    public final void setHospitalizationPermission(final Boolean hospitalizationPermission) {
        this.hospitalizationPermission = hospitalizationPermission;
    }


    /**
     * @hibernate.property
     *  column="hospitalization_permission"
     */
    public final Boolean getHospitalizationPermission() {
        return this.hospitalizationPermission;
    }

    private Boolean rulesAndRegulationsAcceptance;

    public final void setRulesAndRegulationsAcceptance(final Boolean rulesAndRegulationsAcceptance) {
        this.rulesAndRegulationsAcceptance = rulesAndRegulationsAcceptance;
    }


    /**
     * @hibernate.property
     *  column="rules_and_regulations_acceptance"
     */
    public final Boolean getRulesAndRegulationsAcceptance() {
        return this.rulesAndRegulationsAcceptance;
    }

    private String urgencyPhone;

    public final void setUrgencyPhone(final String urgencyPhone) {
        this.urgencyPhone = urgencyPhone;
    }


    /**
     * @hibernate.property
     *  column="urgency_phone"
     *  length="10"
     */
    public final String getUrgencyPhone() {
        return this.urgencyPhone;
    }

}
