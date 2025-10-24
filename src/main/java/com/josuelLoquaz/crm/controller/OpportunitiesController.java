package com.josuelLoquaz.crm.controller;

import com.josuelLoquaz.crm.domain.Opportunities;
import com.josuelLoquaz.crm.repository.OpportunitiesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/opportunities")
public class OpportunitiesController {
    private OpportunitiesRepository opportunitiesRepository;
    public OpportunitiesController(OpportunitiesRepository opportunitiesRepository){
        this.opportunitiesRepository=opportunitiesRepository;
    }

    @PostMapping
    public Opportunities addOportunity(@RequestBody Opportunities opportunities){
        return opportunitiesRepository.save(opportunities);
    }
    @GetMapping
    public List<Opportunities> ListOpportunities(){
         return opportunitiesRepository.findAll();
    }

    @GetMapping("/account/{accountId}")
    public List<Opportunities> getOpportunitiesByAccountId(@PathVariable UUID accountId) {
        return opportunitiesRepository.findByAccountsId(accountId);
    }
}
