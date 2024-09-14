package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Giocatore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String cognome;

    private LocalDate dataNascita;

    private String luogoNascita;

    private String ruolo;

    @ManyToOne
    private Squadra squadra;
    
    private LocalDate inizioTesseramento;
    
    private LocalDate fineTesseramento;
    
    public LocalDate getInizioTesseramento() {
		return inizioTesseramento;
	}

	public void setInizioTesseramento(LocalDate inizioTesseramento) {
		this.inizioTesseramento = inizioTesseramento;
	}

	public LocalDate getFineTesseramento() {
		return fineTesseramento;
	}

	public void setFineTesseramento(LocalDate termineTesseramento) {
		this.fineTesseramento = fineTesseramento;
	}

	

    @OneToOne
    @JoinColumn(name = "image_id", nullable = true)
    private Image image;

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public void setLuogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public Squadra getSquadra() {
        return squadra;
    }

    public void setSquadra(Squadra squadra) {
        this.squadra = squadra;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cognome);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Giocatore other = (Giocatore) obj;
        return Objects.equals(nome, other.nome) && Objects.equals(cognome, other.cognome);
    }
}
