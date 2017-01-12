package com.epam.autum.selection.entity;

/**
 * Created by Tapac on 02.01.2017.
 */
public class ApplicationMark {

    private Integer applicantMarkID;
    private Integer applicationID;

    public ApplicationMark() {
    }

    public ApplicationMark(Integer applicantMarkID, Integer applicationID) {
        this.applicantMarkID = applicantMarkID;
        this.applicationID = applicationID;
    }

    public Integer getApplicantMarkID() {
        return applicantMarkID;
    }

    public void setApplicantMarkID(Integer applicantMarkID) {
        this.applicantMarkID = applicantMarkID;
    }

    public Integer getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(Integer applicationID) {
        this.applicationID = applicationID;
    }

    @Override
    public String toString() {
        return "ApplicationMark{" +
                "applicantMarkID=" + applicantMarkID +
                ", applicationID=" + applicationID +
                '}';
    }
}
