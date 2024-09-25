package com.adt.hrms.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CandidateFollowUpDataRequest {

    private Integer id;
    private String candDesignation;
    private String candidateName;
    private String phoneNumber;
    private BigDecimal currentSalary;
    private BigDecimal experienceYears;  // Changed to Integer to match typical usage
    private BigDecimal expectedSalary;
    private Boolean offerInHand;
    private String status;
    private Integer noticePeriod;
    private String technicalSkills;
    private String emailAddress;
    private String currentLocation;
    private String workPreference;
    private String reasonForChange;
    private String nativeLocation;
    private String education;
    private Integer passingYear; // Typically YEAR is represented as Integer
    private LocalDate profileSubmissionDate;
    private String profileStatus;
    private LocalDate preInterviewFollowUpDate;
    private String preInterviewFollowUpType;
    private String preInterviewFollowUpStatus;
    private LocalDate interviewRequestDate;
    private LocalDate interviewSchedulingDate;
    private String interviewStatus;
    private Boolean interviewConfirmation;
    private Integer interviewRescheduleRequests;
    private Integer interviewNoShows;
    private LocalDate offerFollowUpDate;
    private String offerFollowUpStatus;
    private String offerFollowUpComments;
    private String offerFollowUpActionTaken;
    private LocalDate onboardingDocumentSubmissionFollowUpDate;
    private String onboardingDocumentSubmissionFollowUpStatus;
    private String onboardingDocumentSubmissionFollowUpComments;
    private String onboardingDocumentSubmissionFollowUpActionTaken;
    private LocalDate onboardingProcessFollowUpDate;
    private String onboardingProcessFollowUpStatus;
    private String onboardingProcessFollowUpComments;
    private String onboardingProcessFollowUpActionTaken;
    private LocalDate updatedDate;
    private String updatedBy;
    private String remarks;
   // private String role;

}
