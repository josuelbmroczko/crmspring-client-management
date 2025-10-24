package com.josuelLoquaz.crm.controller;

import com.josuelLoquaz.crm.domain.CrmAssociation;
import com.josuelLoquaz.crm.repository.CrmAssociationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/CrmAssociation")
public class CrmAssociationController {

    private CrmAssociationRepository crmAssociationRepository;
    public CrmAssociationController(CrmAssociationRepository crmAssociationRepository){
        this.crmAssociationRepository=crmAssociationRepository;
    }

    @GetMapping
    public List<CrmAssociation> listCrmAssociation(){
        return crmAssociationRepository.findAll();
    }
}
