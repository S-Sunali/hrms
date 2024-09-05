package com.adt.hrms.service;

import com.adt.hrms.model.CandidateFollowUp;
import com.adt.hrms.repository.CandidateFollowUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
        return candidateFollowUpRepository.findAll();
    }
}
