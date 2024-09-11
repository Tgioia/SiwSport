package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Giocatore;
import it.uniroma3.siw.repository.GiocatoreRepository;

@Service
public class GiocatoreService {

    @Autowired
    protected GiocatoreRepository giocatoreRepository;

    /**
     * Questo metodo recupera un Giocatore dal database in base al suo ID.
     * @param id l'id del Giocatore da recuperare dal DB
     * @return il Giocatore recuperato, o null se nessun Giocatore con l'ID specificato è stato trovato
     */
    @Transactional
    public Giocatore getGiocatore(Long id) {
        Optional<Giocatore> result = this.giocatoreRepository.findById(id);
        return result.orElse(null);
    }

    /**
     * Questo metodo salva un Giocatore nel database.
     * @param giocatore il Giocatore da salvare nel DB
     * @return il Giocatore salvato
     * @throws DataIntegrityViolationException se un Giocatore con gli stessi dati già esiste nel DB
     */
    @Transactional
    public Giocatore saveGiocatore(Giocatore giocatore) {
        return this.giocatoreRepository.save(giocatore);
    }

    /**
     * Questo metodo restituisce tutti i Giocatori presenti nel database.
     * @return una Lista di tutti i Giocatori recuperati
     */
    @Transactional
    public List<Giocatore> getAllGiocatori() {
        List<Giocatore> result = new ArrayList<>();
        Iterable<Giocatore> iterable = this.giocatoreRepository.findAll();
        for(Giocatore giocatore : iterable)
            result.add(giocatore);
        return result;
    }

    /**
     * Questo metodo elimina un Giocatore dal database.
     * @param id l'ID del Giocatore da eliminare
     */
    @Transactional
    public void deleteGiocatore(Long id) {
        this.giocatoreRepository.deleteById(id);
    }
}
