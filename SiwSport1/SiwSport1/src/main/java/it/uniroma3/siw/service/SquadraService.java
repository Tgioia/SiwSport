package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Squadra;
import it.uniroma3.siw.repository.SquadraRepository;

@Service
public class SquadraService {

    @Autowired
    protected SquadraRepository squadraRepository;

    /**
     * Questo metodo recupera una Squadra dal database in base al suo ID.
     * @param id l'id della Squadra da recuperare dal DB
     * @return la Squadra recuperata, o null se nessuna Squadra con l'ID specificato è stata trovata
     */
    @Transactional
    public Squadra getSquadra(Long id) {
        Optional<Squadra> result = this.squadraRepository.findById(id);
        return result.orElse(null);
    }

    /**
     * Questo metodo salva una Squadra nel database.
     * @param squadra la Squadra da salvare nel DB
     * @return la Squadra salvata
     * @throws DataIntegrityViolationException se una Squadra con lo stesso nome già esiste nel DB
     */
    @Transactional
    public Squadra saveSquadra(Squadra squadra) {
        return this.squadraRepository.save(squadra);
    }

    /**
     * Questo metodo restituisce tutte le Squadre presenti nel database.
     * @return una Lista di tutte le Squadre recuperate
     */
    @Transactional
    public List<Squadra> getAllSquadre() {
        List<Squadra> result = new ArrayList<>();
        Iterable<Squadra> iterable = this.squadraRepository.findAll();
        for(Squadra squadra : iterable)
            result.add(squadra);
        return result;
    }

    /**
     * Questo metodo elimina una Squadra dal database.
     * @param id l'ID della Squadra da eliminare
     */
    @Transactional
    public void deleteSquadra(Long id) {
        this.squadraRepository.deleteById(id);
    }
}
