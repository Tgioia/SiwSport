package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Squadra;
import it.uniroma3.siw.repository.SquadraRepository;
import jakarta.validation.Valid;

@Controller
public class SquadraController {
    
    @Autowired 
    private SquadraRepository squadraRepository;

    // Mostra il form per creare una nuova squadra
    @GetMapping(value="/admin/formNewSquadra")
    public String formNewSquadra(Model model) {
        model.addAttribute("squadra", new Squadra());
        return "admin/formNewSquadra.html";
    }

    // Mostra il form per aggiornare una squadra esistente
    @GetMapping(value="/admin/formUpdateSquadra/{id}")
    public String formUpdateSquadra(@PathVariable("id") Long id, Model model) {
        model.addAttribute("squadra", squadraRepository.findById(id).get());
        return "admin/formUpdateSquadra.html";
    }

    // Mostra l'indice delle squadre (pagina principale per le squadre)
    @GetMapping(value="/admin/indexSquadra")
    public String indexSquadra(Model model) {
        model.addAttribute("squadre", squadraRepository.findAll());
        return "admin/indexSquadra.html";
    }

    // Gestione per aggiungere una nuova squadra
    @PostMapping("/admin/squadra")
    public String newSquadra(@Valid @ModelAttribute("squadra") Squadra squadra, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            this.squadraRepository.save(squadra); 
            model.addAttribute("squadra", squadra);
            return "squadra.html";
        } else {
            return "admin/formNewSquadra.html"; 
        }
    }

    // Visualizza una singola squadra in base all'ID
    @GetMapping("/squadra/{id}")
    public String getSquadra(@PathVariable("id") Long id, Model model) {
        model.addAttribute("squadra", this.squadraRepository.findById(id).get());
        return "squadra.html";
    }

    // Visualizza tutte le squadre
    @GetMapping("/squadre")
    public String getSquadre(Model model) {		
        model.addAttribute("squadre", this.squadraRepository.findAll());
        return "squadre.html";
    }

    // Gestione per aggiornare una squadra esistente
    @PostMapping("/admin/squadra/update")
    public String updateSquadra(@Valid @ModelAttribute("squadra") Squadra squadra, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            this.squadraRepository.save(squadra); 
            model.addAttribute("squadra", squadra);
            return "squadra.html";
        } else {
            return "admin/formUpdateSquadra.html"; 
        }
    }
}
