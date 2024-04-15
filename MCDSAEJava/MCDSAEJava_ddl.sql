-- Generated by Mocodo 4.2.4

CREATE TABLE ATHLETE (
  PRIMARY KEY (id_A),
  id_A     INT NOT NULL,
  nom_A     VARCHAR(20),
  prenom_A  VARCHAR(20),
  sexe_A    VARCHAR(1),
  force_A    INT,
  agilite  INT,
  endurance INT,
  id_P      INT NOT NULL
);

CREATE TABLE EPREUVE (
  PRIMARY KEY (id_Ep),
  id_Ep    INT NOT NULL,
  nom       VARCHAR(20),
  categorie VARCHAR(20),
  style     VARCHAR(20),
  id_S      INT NOT NULL
);

CREATE TABLE EQUIPE (
  PRIMARY KEY (id_E),
  id_E  INT NOT NULL,
  nom_E VARCHAR(20)
);

CREATE TABLE PARTICIPER (
  PRIMARY KEY (id_A_responsable, id_Ep),
  id_A_responsable INT NOT NULL,
  id_Ep            INT NOT NULL
);

CREATE TABLE PARTICIPEREQUIPE (
  PRIMARY KEY (id_E_responsable, id_Ep),
  id_E_responsable INT NOT NULL,
  id_Ep            INT NOT NULL
);

CREATE TABLE PAYS (
  PRIMARY KEY (id_P),
  id_P      INT NOT NULL,
  nom_P     VARCHAR(20),
  nb_Or    INT,
  nb_Argent INT,
  nb_Bronze INT
);

CREATE TABLE SPORT (
  PRIMARY KEY (id_S),
  id_S   INT NOT NULL,
  nom    VARCHAR(20),
  milieu VARCHAR(20)
);

ALTER TABLE ATHLETE ADD FOREIGN KEY (id_P) REFERENCES PAYS (id_P);

ALTER TABLE EPREUVE ADD FOREIGN KEY (id_S) REFERENCES SPORT (id_S);

ALTER TABLE PARTICIPER ADD FOREIGN KEY (id_Ep) REFERENCES EPREUVE (id_Ep);
ALTER TABLE PARTICIPER ADD FOREIGN KEY (id_A_responsable) REFERENCES ATHLETE (id_A);
ALTER TABLE PARTICIPEREQUIPE ADD FOREIGN KEY (id_Ep) REFERENCES EPREUVE (id_Ep);
ALTER TABLE PARTICIPEREQUIPE ADD FOREIGN KEY (id_E_responsable) REFERENCES EQUIPE (id_E);
