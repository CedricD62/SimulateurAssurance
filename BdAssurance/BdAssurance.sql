DROP DATABASE IF EXISTS BdAssurance;
CREATE DATABASE BdAssurance;
USE BdAssurance;

CREATE TABLE Client
(
    NumClient     SMALLINT          (3)     AUTO_INCREMENT,
    Nom           VARCHAR           (100)   NOT NULL,
    Prenom        VARCHAR           (100)   NOT NULL,
    DateNaissance VARCHAR           (15)    NOT NULL,
    Marie         BOOLEAN DEFAULT   0       NOT NULL,
    Pacse         BOOLEAN DEFAULT   0       NOT NULL,
    NbrEnfants    SMALLINT          (2)     NOT NULL,

    CONSTRAINT pk_Client PRIMARY KEY (NumClient)
);

CREATE TABLE ContratVoiture
(
    NumContratVoiture   SMALLINT            (3)     AUTO_INCREMENT,
    NbrChevaux          SMALLINT            (2)     NOT NULL,
    BonusMalus          DECIMAL             (1,1)   NOT NULL,
    NbrAnneeAssurance   SMALLINT            (2)     NOT NULL,
    AssuranceConjoint   BOOLEAN DEFAULT     0       NOT NULL,
    AssuranceEnfant     BOOLEAN DEFAULT     0       NOT NULL,
    TarifAssurance      DECIMAL             (4,2)   NOT NULL,
    NumClient           SMALLINT            (3)     NOT NULL,

    CONSTRAINT pk_ContratVoiture PRIMARY KEY (NumContratVoiture),
    CONSTRAINT fk_ContratVoiture_Client FOREIGN KEY (NumClient) REFERENCES Client (NumClient) 
);

CREATE TABLE ContratMoto
(
    NumContratMoto      SMALLINT    (3)     AUTO_INCREMENT,
    NbrCylindree        SMALLINT    (2)     NOT NULL,
    BonusMalus          DECIMAL     (2,2)   NOT NULL,
    NbrAnneeAssurance   SMALLINT    (2)     NOT NULL,
    TarifAssurance      DECIMAL     (4,2)   NOT NULL,
    NumClient           SMALLINT    (2)     NOT NULL,

    CONSTRAINT pk_ContratMoto PRIMARY KEY (NumContratMoto),
    CONSTRAINT fk_ContratMoto_Client FOREIGN KEY (NumClient) REFERENCES Client (NumClient) 
);

CREATE TABLE ContratMaison
(
    NumContratMaison    SMALLINT        (3)   AUTO_INCREMENT,
    TypeLogement        VARCHAR         (100)   NOT NULL,
    Garage              BOOLEAN DEFAULT 0       NOT NULL,
    Superficie          DECIMAL         (5,2)   NOT NULL,
    TailleFoyer         SMALLINT        (2)     NOT NULL,
    TarifAssurance      DECIMAL         (4,2)   NOT NULL,
    NumClient           SMALLINT        (3)     NOT NULL,

    CONSTRAINT pk_ContratMaison PRIMARY KEY (NumContratMaison),
    CONSTRAINT fk_ContratMaison_Client FOREIGN KEY (NumClient) REFERENCES Client (NumClient) 
);

CREATE TABLE JustificatifVoiture
(
    NumDossierVoiture   SMALLINT    (2)    AUTO_INCREMENT,
    Rib                 VARCHAR     (500),
    CopieCarteGrise     VARCHAR     (500),
    CopiePermisAssure   VARCHAR     (500),
    CopiePermisConjoint VARCHAR     (500),
    CopiePermisEnfant   VARCHAR     (500),
    CniAssure           VARCHAR     (500),  
    NumContratVoiture   SMALLINT    (3)   NOT NULL,

    CONSTRAINT pk_JustificatifVoiture PRIMARY KEY (NumDossierVoiture),
    CONSTRAINT fk_NumDossierVoiture_ContratVoiture FOREIGN KEY (NumContratVoiture) REFERENCES ContratVoiture (NumContratVoiture)
);

CREATE TABLE JustificatifMoto
(
    NumDossierMoto      SMALLINT    (2)    AUTO_INCREMENT,
    Rib                 VARCHAR     (500),
    CopieCarteGrise     VARCHAR     (500),
    CopiePermisAssure   VARCHAR     (500),
    CniAssure           VARCHAR     (500),
    NumContratMoto      SMALLINT    (3)   NOT NULL,

    CONSTRAINT pk_JustificatifMoto PRIMARY KEY (NumDossierMoto),
    CONSTRAINT fk_JustificatifMoto_ContratMoto FOREIGN KEY (NumContratMoto) REFERENCES ContratMoto (NumContratMoto)
);

CREATE TABLE JustificatifMaison
(
    NumDossierMaison    SMALLINT    (2)    AUTO_INCREMENT,
    Rib                 VARCHAR     (500),
    CniAssure           VARCHAR     (500),
    ContratLocation     VARCHAR     (500),
    NumContratMaison    SMALLINT    (3)   NOT NULL,

    CONSTRAINT pk_JustificatifMaison PRIMARY KEY (NumDossierMaison),
    CONSTRAINT fk_JustificatifMaison_ContratMaison FOREIGN KEY (NumContratMaison) REFERENCES ContratMaison (NumContratMaison)
);