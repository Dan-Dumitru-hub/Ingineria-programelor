CREATE TABLE utilizatori (
	CNP				NUMBER(13),
	nume_utilizator	VARCHAR2(50) NOT NULL UNIQUE,
	nume			VARCHAR2(50),
	prenume			VARCHAR2(50),
	email			VARCHAR2(50),
	telefon			NUMBER(10),
	tip_utilizator	VARCHAR2(10),
	parola			VARCHAR2(20),
	salt			VARCHAR2(20),
	CONSTRAINT pk1_utilizatori PRIMARY KEY (cnp)
);

CREATE TABLE medicatii (
	nume	VARCHAR2(50),
	doza	NUMBER(10),
	CONSTRAINT pk_medicatii PRIMARY KEY (nume, doza)
);

CREATE TABLE pacienti_per_medici (
	cnp_medic	NUMBER(13),
	cnp_pacient	NUMBER(13),
	CONSTRAINT pk_pacienti_per_medici PRIMARY KEY (cnp_medic, cnp_pacient),
	CONSTRAINT fk1_pacienti_per_medici FOREIGN KEY (cnp_medic) REFERENCES utilizatori(cnp),
	CONSTRAINT fk2_pacienti_per_medici FOREIGN KEY (cnp_pacient) REFERENCES utilizatori(cnp)
);

CREATE TABLE alimentatii_per_pacienti (
	cnp_pacient	NUMBER(13),
	alimentatie	VARCHAR2(1500),
	CONSTRAINT pk_alimentatii_per_pacienti PRIMARY KEY (cnp_pacient),
	CONSTRAINT fk_alimentatii_per_pacienti FOREIGN KEY (cnp_pacient) REFERENCES utilizatori(cnp)
);

CREATE TABLE medicatii_per_pacienti (
	cnp_pacient				NUMBER(13),
	nume_medicatie			VARCHAR2(50),
	doza_medicatie			NUMBER(10),
	frecventa_administrare	VARCHAR2(100),
	CONSTRAINT pk_medicatii_per_pacient PRIMARY KEY (cnp_pacient, nume_medicatie, doza_medicatie),
	CONSTRAINT fk1_medicatii_per_pacient FOREIGN KEY (cnp_pacient) REFERENCES utilizatori(cnp),
	CONSTRAINT fk2_medicatii_per_pacient FOREIGN KEY (nume_medicatie, doza_medicatie) REFERENCES medicatii(nume, doza)
);

COMMIT;
