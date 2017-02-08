--<ScriptOptions statementTerminator=";"/>

CREATE TABLE radiology_dates (
		radiology_id INT8,
		dates DATE
	);

CREATE TABLE sequence (
		seq_name VARCHAR(50) NOT NULL,
		seq_count NUMERIC(38 , 0)
	);

CREATE TABLE radiology (
		id INT8 NOT NULL
	);

CREATE TABLE drugtreatment (
		id INT8 NOT NULL,
		dosage FLOAT8,
		drug VARCHAR(255)
	);

CREATE TABLE message (
		id INT8 NOT NULL,
		text VARCHAR(80)
	);

CREATE TABLE patient (
		id INT8 NOT NULL,
		dob DATE,
		name VARCHAR(255),
		patientid INT8
	);

CREATE TABLE surgery (
		id INT8 NOT NULL,
		surgerydate DATE
	);

CREATE TABLE treatment (
		id INT8 NOT NULL,
		treatmenttype VARCHAR(31),
		diagnosis VARCHAR(255),
		patient_fk INT8,
		provider_fk INT8
	);

CREATE TABLE provider (
		proid INT8 NOT NULL,
		specialization VARCHAR(255),
		name VARCHAR(255),
		npi INT8
	);

CREATE UNIQUE INDEX sequence_pkey ON sequence (seq_name ASC);

CREATE UNIQUE INDEX drugtreatment_pkey ON drugtreatment (id ASC);

CREATE UNIQUE INDEX treatment_pkey ON treatment (id ASC);

CREATE UNIQUE INDEX provider_pkey ON provider (proid ASC);

CREATE UNIQUE INDEX surgery_pkey ON surgery (id ASC);

CREATE UNIQUE INDEX radiology_pkey ON radiology (id ASC);

CREATE UNIQUE INDEX message_pkey ON message (id ASC);

CREATE UNIQUE INDEX patient_pkey ON patient (id ASC);

ALTER TABLE sequence ADD CONSTRAINT sequence_pkey PRIMARY KEY (seq_name);

ALTER TABLE provider ADD CONSTRAINT provider_pkey PRIMARY KEY (proid);

ALTER TABLE radiology ADD CONSTRAINT radiology_pkey PRIMARY KEY (id);

ALTER TABLE surgery ADD CONSTRAINT surgery_pkey PRIMARY KEY (id);

ALTER TABLE drugtreatment ADD CONSTRAINT drugtreatment_pkey PRIMARY KEY (id);

ALTER TABLE message ADD CONSTRAINT message_pkey PRIMARY KEY (id);

ALTER TABLE patient ADD CONSTRAINT patient_pkey PRIMARY KEY (id);

ALTER TABLE treatment ADD CONSTRAINT treatment_pkey PRIMARY KEY (id);

ALTER TABLE drugtreatment ADD CONSTRAINT fk_drugtreatment_id FOREIGN KEY (id)
	REFERENCES treatment (id);

ALTER TABLE radiology ADD CONSTRAINT fk_radiology_id FOREIGN KEY (id)
	REFERENCES treatment (id);

ALTER TABLE surgery ADD CONSTRAINT fk_surgery_id FOREIGN KEY (id)
	REFERENCES treatment (id);

ALTER TABLE radiology_dates ADD CONSTRAINT fk_radiology_dates_radiology_id FOREIGN KEY (radiology_id)
	REFERENCES treatment (id);

