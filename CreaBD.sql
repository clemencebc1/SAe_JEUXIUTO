drop table IF EXISTS USER;
drop table IF EXISTS  PARTICIPER;
drop table IF EXISTS EPREUVE;
drop table IF EXISTS ATHLETE;
drop table IF EXISTS  EQUIPE;
drop table IF EXISTS  SPORT;
drop table IF EXISTS PAYS; 



CREATE TABLE PAYS (
  PRIMARY KEY (id_P),
  id_P      INT NOT NULL,
  nom_P     VARCHAR(20),
  nb_Or    INT,
  nb_Argent INT,
  nb_Bronze INT
);

CREATE TABLE SPORT (
  id_S   INT NOT NULL,
  nom    VARCHAR(20),
  milieu VARCHAR(20),
  PRIMARY KEY (id_S)
);

CREATE TABLE EPREUVE (
  id_Ep    INT NOT NULL,
  nom       VARCHAR(50),
  categorie VARCHAR(1),
  id_S      INT NOT NULL,
  PRIMARY KEY(id_Ep,id_S)

);

CREATE TABLE EQUIPE (
  id_E  INT NOT NULL PRIMARY KEY,
  nom_E VARCHAR(50),
  sexe_E VARCHAR(1)
);

CREATE TABLE ATHLETE (
  id_A     INT NOT NULL PRIMARY KEY,
  nom_A     VARCHAR(20),
  prenom_A  VARCHAR(20),
  sexe_A    VARCHAR(1),
  id_P      INT NOT NULL,
  force_A    INT,
  endurance INT,
  agilite  INT,
  id_E INT
);

CREATE TABLE PARTICIPER (
  PRIMARY KEY (id_A, id_Ep),
  id_A INT NOT NULL,
  id_Ep INT NOT NULL
);

CREATE TABLE USER(
  PRIMARY KEY (nom_U,mdp_U,role_u),
  nom_U VARCHAR(50),
  mdp_U VARCHAR(50),
  role_u VARCHAR(20)
);


ALTER TABLE ATHLETE ADD FOREIGN KEY (id_P) REFERENCES PAYS (id_P);
ALTER TABLE ATHLETE ADD FOREIGN KEY (id_E) REFERENCES EQUIPE (id_E);

ALTER TABLE EPREUVE ADD FOREIGN KEY (id_S) REFERENCES SPORT (id_S);

ALTER TABLE PARTICIPER ADD FOREIGN KEY (id_Ep) REFERENCES EPREUVE (id_Ep);
ALTER TABLE PARTICIPER ADD FOREIGN KEY (id_A) REFERENCES ATHLETE (id_A);


