package it.uniroma3.siw.repository;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Presidente;
import it.uniroma3.siw.model.Squadra;

@Repository
public interface SquadraRepository extends CrudRepository<Squadra, Long> {

	boolean existsByNomeAndAnnoFondazione(String nome, int annoFondazione);

	Iterable<Squadra> findByPresidente(Presidente presidente);

}

