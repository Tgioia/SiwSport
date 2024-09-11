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
import it.uniroma3.siw.repository.GiocatoreRepository;
import it.uniroma3.siw.repository.SquadraRepository;
import it.uniroma3.siw.service.CredentialsService;
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


    // Mostra il form per aggiungere un nuovo giocatore (Admin)
    @GetMapping("/admin/formNewGiocatore")
    public String formNewGiocatore(Model model) {
        model.addAttribute("giocatore", new Giocatore());
        model.addAttribute("squadre", squadraRepository.findAll()); // Fornisce la lista delle squadre per selezionare una squadra
        return "admin/formNewGiocatore.html";
    }

    // Aggiungi un nuovo giocatore (Admin)
    @PostMapping("/admin/giocatore")
    public String newGiocatore(@Valid @ModelAttribute("giocatore") Giocatore giocatore, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            this.giocatoreRepository.save(giocatore); 
            return "redirect:/admin/indexGiocatori";
        } else {
            model.addAttribute("squadre", squadraRepository.findAll());
            return "admin/formNewGiocatore.html"; 
        }
    }

    // Mostra la lista dei giocatori (Admin)
    @GetMapping("/admin/indexGiocatori")
    public String indexGiocatore(Model model) {
        model.addAttribute("giocatori", this.giocatoreRepository.findAll());
        return "admin/indexGiocatori.html";
    }

    // Mostra il form per aggiornare un giocatore (Admin)
    @GetMapping("/admin/formUpdateGiocatore/{id}")
    public String formUpdateGiocatore(@PathVariable("id") Long id, Model model) {
        Giocatore giocatore = giocatoreRepository.findById(id).get();
        model.addAttribute("giocatore", giocatore);
        model.addAttribute("squadre", squadraRepository.findAll()); // In caso di cambio squadra
        return "admin/formUpdateGiocatore.html";
    }

    // Aggiorna un giocatore esistente (Admin)
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

    // Elimina un giocatore (Admin)
    @GetMapping("/admin/giocatore/delete/{id}")
    public String deleteGiocatore(@PathVariable("id") Long id) {
        Giocatore giocatore = this.giocatoreRepository.findById(id).get();
        this.giocatoreRepository.delete(giocatore);
        return "redirect:/admin/indexGiocatori";
    }

    // Visualizza i dettagli di un giocatore
    @GetMapping("/giocatore/{id}")
    public String getGiocatore(@PathVariable("id") Long id, Model model) {
        model.addAttribute("giocatore", this.giocatoreRepository.findById(id).get());
        return "giocatore.html";
    }

    // Visualizza l'elenco di tutti i giocatori
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
