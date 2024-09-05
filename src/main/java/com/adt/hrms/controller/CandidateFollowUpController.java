package com.adt.hrms.controller;

import com.adt.hrms.model.CandidateFollowUp;
import com.adt.hrms.service.CandidateFollowUpService;
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

    @Autowired
    private CandidateFollowUpService candidateFollowUpService;

    @PreAuthorize("@auth.allow('UPLOAD_CANDIDATES_DATA')")
    @PostMapping("/uploadCandidatesData")
    public ResponseEntity<?>uploadCandidateData(@RequestParam("file")MultipartFile file){
        candidateFollowUpService.saveCandidateToDatabase(file);
        return ResponseEntity.ok(Map.of("Message","Candidates Data Uploaded and Saved to Database Successfully"));
    }

    @PreAuthorize("@auth.allow('GET_ALL_CANDIDATE_FOLLOW_UP_DATA')")
    @GetMapping("/getAllCandidateFollowUpData")
    public ResponseEntity<List<CandidateFollowUp>> getCandidates(){
        return new ResponseEntity<>(candidateFollowUpService.getCandidates(),HttpStatus.FOUND);
    }
}
