DROP SCHEMA IF EXISTS telefonija;
CREATE SCHEMA telefonija DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE telefonija;

CREATE TABLE tarife (
	id BIGINT AUTO_INCREMENT,
    naziv VARCHAR(100) NOT NULL,
    opis VARCHAR(100) NOT NULL,
    cena DOUBLE NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE pretplate (
	id BIGINT AUTO_INCREMENT,
    tarifaId BIGINT NOT NULL,
    broj VARCHAR(100) NOT NULL,
    datumIVreme DATETIME NOT NULL,
    trajanjeUgovora BIGINT NOT NULL,
	PRIMARY KEY(id),
    FOREIGN KEY(tarifaId) REFERENCES tarife(id),
    FOREIGN KEY(tarifaId) REFERENCES tarife(id) ON DELETE CASCADE
);

# tarife: naziv, opis, cena
INSERT INTO tarife (id, naziv, opis, cena) VALUES (1, 'Potok 150', '150 min. 150 SMS, 2GB', 800);
INSERT INTO tarife (id, naziv, opis, cena) VALUES (2, 'Potok 300', '300 min. 300 SMS, 2.5GB', 1200);
INSERT INTO tarife (id, naziv, opis, cena) VALUES (3, 'Maslačak 12', 'neograničeni min., neograničeni SMS, 27GB', 1900);
INSERT INTO tarife (id, naziv, opis, cena) VALUES (4, 'Maslačak 25', 'neograničeni min., neograničeni SMS, 40GB', 2300);
INSERT INTO tarife (id, naziv, opis, cena) VALUES (5, 'Maslačak 50', 'neograničeni min., neograničeni SMS, 100GB', 2600);
INSERT INTO tarife (id, naziv, opis, cena) VALUES (6, 'Vrabac lite', 'neograničeni min., neograničeni SMS, neograničeni GB', 3000);
INSERT INTO tarife (id, naziv, opis, cena) VALUES (7, 'Vrabac max', 'neograničeni min., neograničeni SMS, neograničeni GB, besplatni Phoebus', 4500);


# pretplate: tarifa, pretplatnički broj, datum početka, trajanje ugovora
INSERT INTO pretplate (tarifaId, broj,  datumIVreme, trajanjeUgovora) VALUES (1, '+381641111111', '2020-01-01', 12);
INSERT INTO pretplate (tarifaId, broj,  datumIVreme, trajanjeUgovora) VALUES (1, '+381642222222', '2020-06-01', 36);
INSERT INTO pretplate (tarifaId, broj,  datumIVreme, trajanjeUgovora) VALUES (2, '+381643333333', '2020-01-01', 24);
INSERT INTO pretplate (tarifaId, broj,  datumIVreme, trajanjeUgovora) VALUES (2, '+381644444444', '2022-06-01', 12);
INSERT INTO pretplate (tarifaId, broj,  datumIVreme, trajanjeUgovora) VALUES (3, '+381645555555', '2022-01-01', 24);
INSERT INTO pretplate (tarifaId, broj,  datumIVreme, trajanjeUgovora) VALUES (4, '+381641111111', '2021-01-01', 12);
INSERT INTO pretplate (tarifaId, broj,  datumIVreme, trajanjeUgovora) VALUES (4, '+381643333333', '2022-01-01', 12);
INSERT INTO pretplate (tarifaId, broj,  datumIVreme, trajanjeUgovora) VALUES (4, '+381646666666', '2022-06-01', 24);
INSERT INTO pretplate (tarifaId, broj,  datumIVreme, trajanjeUgovora) VALUES (4, '+381647777777', '2022-01-01', 36);
INSERT INTO pretplate (tarifaId, broj,  datumIVreme, trajanjeUgovora) VALUES (5, '+381641111111', '2022-01-01', 24);
INSERT INTO pretplate (tarifaId, broj,  datumIVreme, trajanjeUgovora) VALUES (5, '+381648888888', '2023-01-01', 36);
INSERT INTO pretplate (tarifaId, broj,  datumIVreme, trajanjeUgovora) VALUES (5, '+381647777777', '2023-01-01', 36);
INSERT INTO pretplate (tarifaId, broj,  datumIVreme, trajanjeUgovora) VALUES (6, '+381643333333', '2023-01-01', 12);
INSERT INTO pretplate (tarifaId, broj,  datumIVreme, trajanjeUgovora) VALUES (6, '+381642222222', '2023-01-01', 24);
INSERT INTO pretplate (tarifaId, broj,  datumIVreme, trajanjeUgovora) VALUES (6, '+381645555555', '2023-01-01', 12);
