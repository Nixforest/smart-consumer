package com.gae.java.smartconsumer.form;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

public class StatusForm extends ActionForm implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer status;
    private Long id;
    /**
     * Get value of id.
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * Set the value for id.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Get value of status.
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * Set the value for status.
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}
