package com.adt.hrms.controller;

import com.adt.hrms.model.CandidateFollowUp;
import com.adt.hrms.model.Employee;
import com.adt.hrms.request.CandidateFollowUpDataRequest;
import com.adt.hrms.service.CandidateFollowUpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/candidates")
public class CandidateFollowUpController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CandidateFollowUpService candidateFollowUpService;

    @PreAuthorize("@auth.allow('UPLOAD_CANDIDATES_DATA')")
    @PostMapping("/uploadCandidatesData")
    public ResponseEntity<?> uploadCandidateData(@RequestParam("file") MultipartFile file) {
        candidateFollowUpService.saveCandidateToDatabase(file);
        return ResponseEntity.ok(Map.of("Message", "Candidates Data Uploaded and Saved to Database Successfully"));
    }

    @PreAuthorize("@auth.allow('GET_ALL_CANDIDATE_FOLLOW_UP_DATA')")
    @GetMapping("/getAllCandidateFollowUpData")
    public ResponseEntity<List<CandidateFollowUp>> getCandidates() {
        return new ResponseEntity<>(candidateFollowUpService.getCandidates(), HttpStatus.FOUND);
    }

    @PreAuthorize("@auth.allow('GET_CANDIDATE_FOLLOW_UP_DATA_BY_ID')")
    @GetMapping("/getCandidateFollowUpDataById/{Id}")
    public ResponseEntity<CandidateFollowUp> getCandidatesById(@PathVariable("Id") int candidateId) {
        return new ResponseEntity<>(candidateFollowUpService.getCandidateById(candidateId), HttpStatus.OK);
    }

    @PreAuthorize("@auth.allow('UPDATE_CANDIDATE_FOLLOW_UP_DATA_BY_ID')")
    @PutMapping("/updateCandidateFollowUpDataById")
    public ResponseEntity<String> updateCandidateFollowUpDataById(@RequestBody CandidateFollowUpDataRequest followUpDataRequest) {
        try {
            LOGGER.info("Candidate follow up data service :updateCandidate info level log message");
            return new ResponseEntity<>(candidateFollowUpService.updateCandidateFollowUpData(followUpDataRequest), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
