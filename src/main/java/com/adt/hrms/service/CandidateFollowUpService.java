package com.adt.hrms.service;

import com.adt.hrms.model.CandidateFollowUp;
import com.adt.hrms.model.Employee;
import com.adt.hrms.repository.CandidateFollowUpRepository;
import com.adt.hrms.request.CandidateFollowUpDataRequest;
import com.adt.hrms.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;

@Service
public class CandidateFollowUpService {

    @Autowired
    private CandidateFollowUpRepository candidateFollowUpRepository;

    public ResponseEntity<String> saveCandidateToDatabase(MultipartFile file) {
        if (ExcelUploadService.isValidExcelFile(file)) {
            try {
                List<CandidateFollowUp> candidates = ExcelUploadService.getCandidateDataFromExcel(file.getInputStream());
                Set<String> existingEmails = candidateFollowUpRepository.findAll()
                        .stream().map(CandidateFollowUp::getEmailAddress).filter(Objects::nonNull).collect(Collectors.toSet());
                Set<String> existingPhoneNumbers = candidateFollowUpRepository.findAll().stream()
                        .map(CandidateFollowUp::getPhoneNumber).filter(Objects::nonNull).collect(Collectors.toSet());
                System.out.println("Existing emails: " + existingEmails);
                System.out.println("Existing phone numbers: " + existingPhoneNumbers);
                List<CandidateFollowUp> newCandidates = new ArrayList<>();
                List<String> duplicateEmails = new ArrayList<>();
                List<String> duplicatePhoneNumbers = new ArrayList<>();
                for (CandidateFollowUp candidate : candidates) {
                    String email = candidate.getEmailAddress();
                    String phoneNumber = candidate.getPhoneNumber();
                    if (email != null) {
                        if (existingEmails.contains(email)) {
                            duplicateEmails.add(email);
                        } else {
                            existingEmails.add(email);
                        }
                    }
                    if (phoneNumber != null) {
                        if (existingPhoneNumbers.contains(phoneNumber)) {
                            duplicatePhoneNumbers.add(phoneNumber);
                        } else {
                            existingPhoneNumbers.add(phoneNumber);
                        }
                    }
                    if (!duplicateEmails.contains(email) && !duplicatePhoneNumbers.contains(phoneNumber)) {
                        newCandidates.add(candidate);
                    }
                }
                System.out.println("Duplicate emails found: " + duplicateEmails);
                System.out.println("Duplicate phone numbers found: " + duplicatePhoneNumbers);
                System.out.println("New candidates to save: " + newCandidates.size());
                if (!newCandidates.isEmpty()) {
                    candidateFollowUpRepository.saveAll(newCandidates);
                }
                StringBuilder responseMessage = new StringBuilder();
                if (!duplicateEmails.isEmpty()) {
                    responseMessage.append("Duplicate email addresses found: ").append(String.join(", ", duplicateEmails)).append(". ");
                }
                if (!duplicatePhoneNumbers.isEmpty()) {
                    responseMessage.append("Duplicate phone numbers found: ").append(String.join(", ", duplicatePhoneNumbers)).append(". ");
                }
                if (responseMessage.length() > 0) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(responseMessage.toString());
                }
                return ResponseEntity.ok("Candidates Data Uploaded and Saved to Database Successfully");
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("This File is not a Valid File");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid file format");
    }

    public List<CandidateFollowUp> getCandidates() {
       return candidateFollowUpRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public CandidateFollowUp getCandidateById(int candidateId){
        Optional<CandidateFollowUp> opt = candidateFollowUpRepository.findById(candidateId);
        if (opt.isPresent())
            return opt.get();
        else
            return null;

    }

    public String updateCandidateFollowUpData(CandidateFollowUpDataRequest followUpDataRequest) {
        Optional<CandidateFollowUp> opt = candidateFollowUpRepository.findById(followUpDataRequest.getId());
        if (!opt.isPresent())
            return "Candidate not found with id: " + followUpDataRequest.getId();
        if (followUpDataRequest.getCandidateName() != null)
            opt.get().setCandidateName(followUpDataRequest.getCandidateName());
        if (followUpDataRequest.getCandDesignation() != null)
            opt.get().setCandDesignation(followUpDataRequest.getCandDesignation());
        if (followUpDataRequest.getPhoneNumber() != null)
            opt.get().setPhoneNumber(followUpDataRequest.getPhoneNumber());
        if (followUpDataRequest.getCurrentSalary() != null)
            opt.get().setCurrentSalary(followUpDataRequest.getCurrentSalary());
        if (followUpDataRequest.getExperienceYears() != null)
            opt.get().setExperienceYears(followUpDataRequest.getExperienceYears());
        if (followUpDataRequest.getExpectedSalary() != null)
            opt.get().setExpectedSalary(followUpDataRequest.getExpectedSalary());
        if (followUpDataRequest.getOfferInHand() != null)
            opt.get().setOfferInHand(followUpDataRequest.getOfferInHand());
        if (followUpDataRequest.getStatus() != null)
            opt.get().setStatus(followUpDataRequest.getStatus());
        if (followUpDataRequest.getNoticePeriod() != null)
            opt.get().setNoticePeriod(followUpDataRequest.getNoticePeriod());
        if (followUpDataRequest.getTechnicalSkills() != null)
            opt.get().setTechnicalSkills(followUpDataRequest.getTechnicalSkills());
        if (followUpDataRequest.getEmailAddress() != null)
            opt.get().setEmailAddress(followUpDataRequest.getEmailAddress());
        if (followUpDataRequest.getCurrentLocation() != null)
            opt.get().setCurrentLocation(followUpDataRequest.getCurrentLocation());
        if (followUpDataRequest.getWorkPreference() != null)
            opt.get().setWorkPreference(followUpDataRequest.getWorkPreference());
        if (followUpDataRequest.getReasonForChange() != null)
            opt.get().setReasonForChange(followUpDataRequest.getReasonForChange());
        if (followUpDataRequest.getNativeLocation() != null)
            opt.get().setNativeLocation(followUpDataRequest.getNativeLocation());
        if (followUpDataRequest.getEducation() != null)
            opt.get().setEducation(followUpDataRequest.getEducation());
        if (followUpDataRequest.getNativeLocation() != null)
            opt.get().setNativeLocation(followUpDataRequest.getNativeLocation());
        if (followUpDataRequest.getPassingYear() != null)
            opt.get().setPassingYear(followUpDataRequest.getPassingYear());
        if (followUpDataRequest.getProfileSubmissionDate() != null)
            opt.get().setProfileSubmissionDate(followUpDataRequest.getProfileSubmissionDate());
        if (followUpDataRequest.getProfileStatus() != null)
            opt.get().setProfileStatus(followUpDataRequest.getProfileStatus());
        if (followUpDataRequest.getPreInterviewFollowUpDate() != null)
            opt.get().setPreInterviewFollowUpDate(followUpDataRequest.getPreInterviewFollowUpDate());
        if (followUpDataRequest.getPreInterviewFollowUpType() != null)
            opt.get().setPreInterviewFollowUpType(followUpDataRequest.getPreInterviewFollowUpType());
        if (followUpDataRequest.getPreInterviewFollowUpStatus() != null)
            opt.get().setPreInterviewFollowUpStatus(followUpDataRequest.getPreInterviewFollowUpStatus());
        if (followUpDataRequest.getInterviewRequestDate() != null)
            opt.get().setInterviewRequestDate(followUpDataRequest.getInterviewRequestDate());
        if (followUpDataRequest.getInterviewSchedulingDate() != null)
            opt.get().setInterviewSchedulingDate(followUpDataRequest.getInterviewSchedulingDate());
        if (followUpDataRequest.getInterviewStatus() != null)
            opt.get().setInterviewStatus(followUpDataRequest.getInterviewStatus());
        if (followUpDataRequest.getInterviewConfirmation() != null)
            opt.get().setInterviewConfirmation(followUpDataRequest.getInterviewConfirmation());
        if (followUpDataRequest.getInterviewRescheduleRequests() != null)
            opt.get().setInterviewRescheduleRequests(followUpDataRequest.getInterviewRescheduleRequests());
        if (followUpDataRequest.getInterviewNoShows() != null)
            opt.get().setInterviewNoShows(followUpDataRequest.getInterviewNoShows());
        if (followUpDataRequest.getOfferFollowUpDate() != null)
            opt.get().setOfferFollowUpDate(followUpDataRequest.getOfferFollowUpDate());
        if (followUpDataRequest.getOfferFollowUpStatus() != null)
            opt.get().setOfferFollowUpStatus(followUpDataRequest.getOfferFollowUpStatus());
        if (followUpDataRequest.getOfferFollowUpComments() != null)
            opt.get().setOfferFollowUpComments(followUpDataRequest.getOfferFollowUpComments());
        if (followUpDataRequest.getOfferFollowUpActionTaken() != null)
            opt.get().setOfferFollowUpActionTaken(followUpDataRequest.getOfferFollowUpActionTaken());
        if (followUpDataRequest.getOnboardingDocumentSubmissionFollowUpDate() != null)
            opt.get().setOnboardingDocumentSubmissionFollowUpDate(followUpDataRequest.getOnboardingDocumentSubmissionFollowUpDate());
        if (followUpDataRequest.getOnboardingDocumentSubmissionFollowUpStatus() != null)
            opt.get().setOnboardingDocumentSubmissionFollowUpStatus(followUpDataRequest.getOnboardingDocumentSubmissionFollowUpStatus());
        if (followUpDataRequest.getOnboardingDocumentSubmissionFollowUpComments() != null)
            opt.get().setOnboardingDocumentSubmissionFollowUpComments(followUpDataRequest.getOnboardingDocumentSubmissionFollowUpComments());
        if (followUpDataRequest.getOnboardingDocumentSubmissionFollowUpActionTaken() != null)
            opt.get().setOnboardingDocumentSubmissionFollowUpActionTaken(followUpDataRequest.getOnboardingDocumentSubmissionFollowUpActionTaken());
        if (followUpDataRequest.getOnboardingProcessFollowUpDate() != null)
            opt.get().setOnboardingProcessFollowUpDate(followUpDataRequest.getOnboardingProcessFollowUpDate());
        if (followUpDataRequest.getOnboardingProcessFollowUpStatus() != null)
            opt.get().setOnboardingProcessFollowUpStatus(followUpDataRequest.getOnboardingProcessFollowUpStatus());
        if (followUpDataRequest.getOnboardingProcessFollowUpComments() != null)
            opt.get().setOnboardingProcessFollowUpComments(followUpDataRequest.getOnboardingProcessFollowUpComments());
        if (followUpDataRequest.getOnboardingProcessFollowUpActionTaken() != null)
            opt.get().setOnboardingProcessFollowUpActionTaken(followUpDataRequest.getOnboardingProcessFollowUpActionTaken());
        if (followUpDataRequest.getUpdatedDate() != null)
            opt.get().setUpdatedDate(followUpDataRequest.getUpdatedDate());
        if (followUpDataRequest.getUpdatedBy() != null)
            opt.get().setUpdatedBy(followUpDataRequest.getUpdatedBy());
        if (followUpDataRequest.getRemarks() != null)
            opt.get().setRemarks(followUpDataRequest.getRemarks());

        return candidateFollowUpRepository.save(opt.get()).getId() + " Employee Updated Successfully";
    }
}
