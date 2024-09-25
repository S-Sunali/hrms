package com.adt.hrms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( catalog = "EmployeeDB", schema = "employee_schema", name ="candidate_followup")
public class CandidateFollowUp {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "candidate_followup_seq")
        @SequenceGenerator(name = "candidate_followup_seq", sequenceName = "employee_schema.candidate_followup_seq", allocationSize = 1)
        private Integer id;

        @Column(name = "cand_designation")
        private String candDesignation;

        @Column(name = "candidate_name")
        private String candidateName;

        @Column(name = "phone_number")
        private String phoneNumber;

        @Column(name = "current_salary")
        private BigDecimal currentSalary;

        @Column(name = "experience_years")
        private BigDecimal experienceYears;  // Changed to Integer to match typical usage

        @Column(name = "expected_salary")
        private BigDecimal expectedSalary;

        @Column(name = "offer_in_hand")
        private Boolean offerInHand;

        @Column(name = "status")
        private String status;

        @Column(name = "notice_period")
        private Integer noticePeriod;

        @Column(name = "technical_skills", columnDefinition = "TEXT")
        private String technicalSkills;

        @Column(name = "email_address")
        private String emailAddress;

        @Column(name = "current_location")
        private String currentLocation;

        @Column(name = "work_preference")
        private String workPreference;

        @Column(name = "reason_for_change")
        private String reasonForChange;

        @Column(name = "native_location")
        private String nativeLocation;

        @Column(name = "education")
        private String education;

        @Column(name = "passing_year")
        private Integer passingYear; // Typically YEAR is represented as Integer

        @Column(name = "profile_submission_date")
        private LocalDate profileSubmissionDate;

        @Column(name = "profile_status")
        private String profileStatus;

        @Column(name = "pre_interview_follow_up_date")
        private LocalDate preInterviewFollowUpDate;

        @Column(name = "pre_interview_follow_up_type")
        private String preInterviewFollowUpType;

        @Column(name = "pre_interview_follow_up_status")
        private String preInterviewFollowUpStatus;

        @Column(name = "interview_request_date")
        private LocalDate interviewRequestDate;

        @Column(name = "interview_scheduling_date")
        private LocalDate interviewSchedulingDate;

        @Column(name = "interview_status")
        private String interviewStatus;

        @Column(name = "interview_confirmation")
        private Boolean interviewConfirmation;

        @Column(name = "interview_reschedule_requests")
        private Integer interviewRescheduleRequests;

        @Column(name = "interview_no_shows")
        private Integer interviewNoShows;

        @Column(name = "offer_follow_up_date")
        private LocalDate offerFollowUpDate;

        @Column(name = "offer_follow_up_status")
        private String offerFollowUpStatus;

        @Column(name = "offer_follow_up_comments", columnDefinition = "TEXT")
        private String offerFollowUpComments;

        @Column(name = "offer_follow_up_action_taken")
        private String offerFollowUpActionTaken;

        @Column(name = "onboarding_document_submission_follow_up_date")
        private LocalDate onboardingDocumentSubmissionFollowUpDate;

        @Column(name = "onboarding_document_submission_follow_up_status")
        private String onboardingDocumentSubmissionFollowUpStatus;

        @Column(name = "onboarding_document_submission_follow_up_comments", columnDefinition = "TEXT")
        private String onboardingDocumentSubmissionFollowUpComments;

        @Column(name = "onboarding_document_submission_follow_up_action_taken")
        private String onboardingDocumentSubmissionFollowUpActionTaken;

        @Column(name = "onboarding_process_follow_up_date")
        private LocalDate onboardingProcessFollowUpDate;

        @Column(name = "onboarding_process_follow_up_status")
        private String onboardingProcessFollowUpStatus;

        @Column(name = "onboarding_process_follow_up_comments", columnDefinition = "TEXT")
        private String onboardingProcessFollowUpComments;

        @Column(name = "onboarding_process_follow_up_action_taken")
        private String onboardingProcessFollowUpActionTaken;

        @Column(name = "updated_date")
        private LocalDate updatedDate;

        @Column(name = "updated_by")
        private String updatedBy;

        @Column(name = "remarks", columnDefinition = "TEXT")
        private String remarks;

      /*  @Column(name="role")
        private String role;*/

    }
