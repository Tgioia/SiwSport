-- Aggiungi un utente ADMIN in credentials
insert into credentials (id, username, password, role) 
values (nextval('credentials_seq'), 'admin', '{bcrypt}$2a$10$wFtp/2jONqcs2UttZsRiC.qxORuFOhXjxMHvK4LP9PcLlsfAqPtk6', 'ADMIN');

-- Aggiungi un utente DEFAULT in credentials
insert into credentials (id, username, password, role) 
values (nextval('credentials_seq'), 'user', '{bcrypt}$2a$10$7jEdAVzpoOhLl8ZEXsxteu1CjTLmXx7G4M2aNEH4lzQ8/USZs4aVm', 'DEFAULT');

-- Aggiungi gli utenti nella tabella users se esiste (collegata a credentials)
-- Per esempio, se `users` è collegato a `credentials` tramite foreign key o direttamente associato
insert into users (id, username, password) 
values (nextval('users_seq'), 'admin', '{bcrypt}$2a$10$wFtp/2jONqcs2UttZsRiC.qxORuFOhXjxMHvK4LP9PcLlsfAqPtk6');

insert into users (id, username, password) 
values (nextval('users_seq'), 'user', '{bcrypt}$2a$10$7jEdAVzpoOhLl8ZEXsxteu1CjTLmXx7G4M2aNEH4lzQ8/USZs4aVm');

-- Aggiungi una squadra
insert into squadra (id, nome, anno_fondazione, indirizzo_sede) 
values (nextval('squadra_seq'), 'Squadra A', 2005, 'Via Roma 1, Milano');

-- Aggiungi un giocatore e collegalo alla squadra
insert into giocatore (id, nome, cognome, data_nascita, luogo_nascita, ruolo, squadra_id) 
values (nextval('giocatore_seq'), 'Mario', 'Rossi', '1990-05-15', 'Roma', 'Attaccante', currval('squadra_seq'));
