package com.josuelLoquaz.crm.controller;

import com.josuelLoquaz.crm.domain.Accounts;
import com.josuelLoquaz.crm.domain.Contacts;
import com.josuelLoquaz.crm.domain.Opportunities;
import com.josuelLoquaz.crm.repository.AccountsRepository;
import com.josuelLoquaz.crm.repository.ContactsRepository;
import com.josuelLoquaz.crm.repository.OpportunitiesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ViewController {

    private final AccountsRepository accountsRepository;
    private final ContactsRepository contactsRepository;
    private final OpportunitiesRepository opportunitiesRepository;

    public ViewController(AccountsRepository accountsRepository, ContactsRepository contactsRepository, OpportunitiesRepository opportunitiesRepository) {
        this.accountsRepository = accountsRepository;
        this.contactsRepository = contactsRepository;
        this.opportunitiesRepository = opportunitiesRepository;
    }

    // --- Rotas de Páginas Básicas ---
    @GetMapping("/")
    public String pageHome() { return "home"; }

    @GetMapping("/login")
    public String PageLogin() { return "login"; }

    // --- Rotas para CONTAS (Accounts) ---
    @GetMapping("/dashboard")
    public String dashboard(Model model){
        model.addAttribute("accounts", accountsRepository.findAll());
        return "dashboard";
    }

    @GetMapping("/accounts/new")
    public String showNewAccountForm(Model model) {
        model.addAttribute("account", new Accounts());
        return "account-form";
    }

    @PostMapping("/accounts/save")
    public String saveAccount(@ModelAttribute("account") Accounts account) {
        accountsRepository.save(account);
        return "redirect:/dashboard";
    }

    @GetMapping("/account-detail/{id}")
    public String pageAccountDetail(@PathVariable("id") UUID id, Model model) {
        Optional<Accounts> accountOptional = accountsRepository.findById(id);
        if (accountOptional.isPresent()) {
            model.addAttribute("account", accountOptional.get());
            model.addAttribute("contacts", contactsRepository.findByAccountsId(id));
            model.addAttribute("opportunities", opportunitiesRepository.findByAccountsId(id));
            return "account-detail";
        } else {
            return "redirect:/dashboard";
        }
    }

    // =================================================================
    // 👇 NOVOS MÉTODOS PARA ADICIONAR CONTATOS E OPORTUNIDADES 👇
    // =================================================================

    // --- Rotas para CONTATOS (Contacts) ---

    @GetMapping("/account/{accountId}/contacts/new")
    public String showNewContactForm(@PathVariable("accountId") UUID accountId, Model model) {
        model.addAttribute("contact", new Contacts());
        model.addAttribute("accountId", accountId); // Passa o ID da conta para o formulário
        return "contact-form";
    }

    @PostMapping("/account/{accountId}/contacts/save")
    public String saveContact(@PathVariable("accountId") UUID accountId, @ModelAttribute("contact") Contacts contact) {
        // Busca a conta à qual este contato pertencerá
        accountsRepository.findById(accountId).ifPresent(contact::setAccounts);
        contactsRepository.save(contact);
        return "redirect:/account-detail/" + accountId; // Volta para a página de detalhes da conta
    }

    // --- Rotas para OPORTUNIDADES (Opportunities) ---

    @GetMapping("/account/{accountId}/opportunities/new")
    public String showNewOpportunityForm(@PathVariable("accountId") UUID accountId, Model model) {
        model.addAttribute("opportunity", new Opportunities());
        model.addAttribute("accountId", accountId);
        return "opportunity-form";
    }

    @PostMapping("/account/{accountId}/opportunities/save")
    public String saveOpportunity(@PathVariable("accountId") UUID accountId, @ModelAttribute("opportunity") Opportunities opportunity) {
        accountsRepository.findById(accountId).ifPresent(opportunity::setAccounts);
        opportunitiesRepository.save(opportunity);
        return "redirect:/account-detail/" + accountId;
    }
}