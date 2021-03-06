package fr.cg95.cvq.business.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @hibernate.class
 *  table="document"
 *  lazy="false"
 *
 * @author bor@zenexity.fr
 */
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /** creation date of the document in the system, set by the model */
    private Date creationDate;
    private Date validationDate;
    /** end validity date of the document, set by the agent at validation time */
    private Date endValidityDate;
    private String ecitizenNote;
    private String agentNote;
    private DocumentState state = DocumentState.PENDING;
    private DepositType depositType = DepositType.PC;
    private DepositOrigin depositOrigin = DepositOrigin.ECITIZEN;
    private Long depositId;
    private Long homeFolderId;
    private Long individualId;
    private DocumentType documentType;
    private boolean certified = false;

    /**
     * As documents are persisted before a request is finished and / or before ecitizen accounts are created, 
     * we need a way to track them without having the whole usual environment. For that, we use the session
     * UUID.
     */
    private String sessionUuid;

    private List<DocumentBinary> datas = new ArrayList<DocumentBinary>();
    private Set<DocumentAction> actions;

    /** default constructor */
    public Document() {
    }

    public Document(Long homeFolderId, String ecitizenNote, DocumentType documentType,
            String sessionUuid) {
        this.homeFolderId = homeFolderId;
        this.ecitizenNote = ecitizenNote;
        this.documentType = documentType;
        this.sessionUuid = sessionUuid;
    }

    /**
     * @hibernate.id
     *  generator-class="sequence"
     *  column="id"
     */
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @hibernate.property
     *  column="creation_date"
     */
    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @hibernate.property
     *  column="validation_date"
     */
    public Date getValidationDate() {
        return this.validationDate;
    }

    public void setValidationDate(Date validationDate) {
        this.validationDate = validationDate;
    }

    /**
     * @hibernate.property
     *  column="end_validity_date"
     */
    public Date getEndValidityDate() {
        return this.endValidityDate;
    }

    public void setEndValidityDate(Date endValidityDate) {
        this.endValidityDate = endValidityDate;
    }

    /**
     * @hibernate.property
     *  column="ecitizen_note"
     */
    public String getEcitizenNote() {
        return this.ecitizenNote;
    }

    public void setEcitizenNote(String ecitizenNote) {
        this.ecitizenNote = ecitizenNote;
    }

    /**
     * @hibernate.property
     *  column="agent_note"
     */
    public String getAgentNote() {
        return this.agentNote;
    }

    public void setAgentNote(String agentNote) {
        this.agentNote = agentNote;
    }

    /**
     * @hibernate.property
     *  column="state"
     *  length="16"
     */
    public DocumentState getState() {
        return this.state;
    }

    public void setState(DocumentState state) {
        this.state = state;
    }

    /**
     * @hibernate.property
     *  column="deposit_type"
     *  length="24"
     */
    public DepositType getDepositType() {
        return this.depositType;
    }

    public void setDepositType(DepositType depositType) {
        this.depositType = depositType;
    }

    /**
     * @hibernate.property
     *  column="deposit_origin"
     *  length="10"
     */
    public DepositOrigin getDepositOrigin() {
        return this.depositOrigin;
    }

    public void setDepositOrigin(DepositOrigin depositOrigin) {
        this.depositOrigin = depositOrigin;
    }

    /**
     * @hibernate.property
     *  column="deposit_id"
     */
    public Long getDepositId() {
        return this.depositId;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    /**
     * @hibernate.property
     *  column="home_folder_id"
     */
    public Long getHomeFolderId() {
        return this.homeFolderId;
    }

    public void setHomeFolderId(Long homeFolderId) {
        this.homeFolderId = homeFolderId;
    }

    /**
     * @hibernate.property
     *  column="individual_id"
     */
    public Long getIndividualId() {
        return this.individualId;
    }

    public void setIndividualId(Long individualId) {
        this.individualId = individualId;
    }

    /**
     * @hibernate.many-to-one
     *  class="fr.cg95.cvq.business.document.DocumentType"
     *  column="document_type_id"
     */
    public DocumentType getDocumentType() {
        return this.documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    /**
     * @hibernate.property
     */
    public boolean getCertified() {
        return certified;
    }

    public void setCertified(boolean certified) {
        this.certified = certified;
    }

    public void setSessionUuid(String sessionUuid) {
        this.sessionUuid = sessionUuid;
    }

    /**
     * @hibernate.property
     *  column="session_uuid"
     */
    public String getSessionUuid() {
        return sessionUuid;
    }

    /**
     * @hibernate.list
     *  inverse="false"
     *  lazy="false"
     *  cascade="all"
     * @hibernate.key
     *  column="document_id"
     * @hibernate.list-index
     *  column="document_binary_index"
     * @hibernate.one-to-many
     *  class="fr.cg95.cvq.business.document.DocumentBinary"
     */
    public List<DocumentBinary> getDatas() {
        return this.datas;
    }

    public void setDatas(List<DocumentBinary> datas) {
        this.datas = datas;
    }

    /**
     * @hibernate.set
     *  lazy="true"
     *  cascade="all"
     *  order-by="id"
     * @hibernate.key
     *  column="document_id"
     * @hibernate.one-to-many
     *  class="fr.cg95.cvq.business.document.DocumentAction"
     */
    public Set<DocumentAction> getActions() {
        return this.actions;
    }

    public void setActions(Set<DocumentAction> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
}
