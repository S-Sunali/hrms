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
        private BigDecimal experienceYears;

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

        @Column(name = "updated_date")
        private LocalDate updatedDate;

        @Column(name = "updated_by")
        private String updatedBy;

    }
