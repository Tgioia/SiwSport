package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Giocatore;
import it.uniroma3.siw.model.Squadra;

public interface GiocatoreRepository extends CrudRepository<Giocatore, Long> {
	public List<Giocatore> findAllBySquadra(Squadra squadra);
}
