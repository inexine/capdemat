package fr.cg95.cvq.business.request;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @hibernate.class
 *  table="request_action"
 *  lazy="false"
 *
 * @author bor@zenexity.fr
 */
public class RequestAction implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String SEARCH_BY_AGENT_ID = "agent_id";
    public static final String SEARCH_BY_TYPE = "type";
    public static final String SEARCH_BY_DATE = "date";
    public static final String SEARCH_BY_RESULTING_STATE = "resultingState";

    private Long id;
    private Long agentId;
    private RequestActionType type;
    private String note;
    private Date date;
    private RequestState resultingState;
    private String message;
    private byte[] file;
    private String filename;

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
     *  column="agent_id"
     */
    public Long getAgentId() {
        return this.agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    /**
     * @hibernate.property
     *  column="note"
     *  length="1024"
     */
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @hibernate.property
     *  column="date"
     */
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @hibernate.property
     *  column="resulting_state"
     *  not-null="false"
     *  length="16"
     */
    public RequestState getResultingState() {
        return this.resultingState;
    }

    public void setResultingState(RequestState resultingState) {
        this.resultingState = resultingState;
    }

    /**
     * @hibernate.property
     *  column="message"
     *  length="1024"
     */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * @hibernate.property
     *  column="filename"
     */
    public String getFilename() {
        return filename;
    }
    
    public void setFilename (String filename) {
        this.filename = filename;
    }

    /**
     * @hibernate.property
     *  type="binary"
     *  access="field"
     * @hibernate.column
     *  name="file"
     */
    public byte[] getFile() {
        return this.file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    /**
     * @hibernate.property
     *  column="type"
     */
    public RequestActionType getType() {
        return type;
    }

    public void setType(RequestActionType type) {
        this.type = type;
    }
}
