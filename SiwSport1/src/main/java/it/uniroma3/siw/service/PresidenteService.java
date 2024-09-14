package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Presidente;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.repository.PresidenteRepository;
import jakarta.transaction.Transactional;

@Service
public class PresidenteService {

    @Autowired
    private PresidenteRepository presidenteRepository;

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Transactional
    public void savePresidente(Presidente presidente) {
        presidenteRepository.save(presidente);
    }

	public Optional<Presidente> findByUserId(Long id) {
		return this.presidenteRepository.findById(id);
	}

	public Iterable<Presidente> findAll() {
		return this.presidenteRepository.findAll();
	}
}
