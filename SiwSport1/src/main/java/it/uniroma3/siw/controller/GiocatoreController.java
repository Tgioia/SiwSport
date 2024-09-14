package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Giocatore;
import it.uniroma3.siw.model.Presidente;
import it.uniroma3.siw.repository.GiocatoreRepository;
import it.uniroma3.siw.repository.SquadraRepository;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.PresidenteService;
import jakarta.validation.Valid;
import jakarta.persistence.EntityNotFoundException;

@Controller
public class GiocatoreController {

    @Autowired
    private GiocatoreRepository giocatoreRepository;

    @Autowired
    private SquadraRepository squadraRepository;
    
    @Autowired
	private CredentialsService credentialsService;
    @Autowired
   	private PresidenteService presidenteService;

    @GetMapping("/presidente/formNewGiocatore")
    public String formNewGiocatore(Model model) {
        model.addAttribute("giocatore", new Giocatore());
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
        Presidente presidente = credentials.getPresidente();
        model.addAttribute("squadre", squadraRepository.findByPresidente(presidente)); 
        return "presidente/formNewGiocatore.html";
    }


    @PostMapping("/presidente/giocatore")
    public String newGiocatore(@Valid @ModelAttribute("giocatore") Giocatore giocatore, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            this.giocatoreRepository.save(giocatore); 
            return "redirect:/";
        } else {
            model.addAttribute("squadre", squadraRepository.findAll());
            return "presidente/formNewGiocatore.html"; 
        }
    }

    @GetMapping("/admin/indexGiocatori")
    public String indexGiocatore(Model model) {
        model.addAttribute("giocatori", this.giocatoreRepository.findAll());
        return "admin/indexGiocatori.html";
    }

    @GetMapping("/admin/formUpdateGiocatore/{id}")
    public String formUpdateGiocatore(@PathVariable("id") Long id, Model model) {
        Giocatore giocatore = giocatoreRepository.findById(id).get();
        model.addAttribute("giocatore", giocatore);
        model.addAttribute("squadre", squadraRepository.findAll()); 
        return "admin/formUpdateGiocatore.html";
    }


    @PostMapping("/admin/giocatore/update")
    public String updateGiocatore(@Valid @ModelAttribute("giocatore") Giocatore giocatore, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            this.giocatoreRepository.save(giocatore); 
            return "redirect:/admin/indexGiocatori";
        } else {
            model.addAttribute("squadre", squadraRepository.findAll());
            return "admin/formUpdateGiocatore.html"; 
        }
    }

    @GetMapping("/presidente/giocatore/delete/{id}")
    public String deleteGiocatore(@PathVariable("id") Long id) {
        Giocatore giocatore = this.giocatoreRepository.findById(id).get();
        this.giocatoreRepository.delete(giocatore);
        return "redirect:/admin/indexGiocatori";
    }


    @GetMapping("/giocatore/{id}")
    public String getGiocatore(@PathVariable("id") Long id, Model model) {
        model.addAttribute("giocatore", this.giocatoreRepository.findById(id).get());
        return "giocatore.html";
    }

    @GetMapping("/giocatori")
    public String getGiocatori(Model model) {
        model.addAttribute("giocatori", this.giocatoreRepository.findAll());
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
            return "admin/indexGiocatori";
    	}
        return "giocatori.html";
    }
}
