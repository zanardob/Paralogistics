-- Drops for starting over
DROP TABLE enumerations;
DROP TABLE deliveries;
DROP TABLE periods;
DROP TABLE depots;
DROP TABLE sites;
DROP TABLE licences;
DROP TABLE schedulings;
DROP TABLE supplies;
DROP TABLE companies;
DROP TABLE receivers;
DROP TABLE deliverers;
DROP TABLE vehicles;
DROP TABLE materials;
DROP SEQUENCE dlv_seq;
DROP SEQUENCE site_seq;
DROP SEQUENCE sch_seq;
DROP SEQUENCE mtr_seq;

-- Creating all the tables
CREATE TABLE materials (
	mtr_id NUMBER,
	mtr_description VARCHAR2(140),
	mtr_weight NUMBER,
	mtr_dimension CHAR(20),
	CONSTRAINT mtr_pk PRIMARY KEY (mtr_id),
	CONSTRAINT mtr_weight_ck CHECK (mtr_weight > 0)
);

CREATE TABLE vehicles (
	vhc_plate CHAR(7),
	vhc_concession_start DATE,
	vhc_concession_end DATE,
	CONSTRAINT vhc_pk PRIMARY KEY (vhc_plate),
	CONSTRAINT vhc_concession_ck CHECK (vhc_concession_end > vhc_concession_start)
);

CREATE TABLE deliverers (
	dlvr_cpf CHAR(11),
	dlvr_name VARCHAR2(100),
	dlvr_rg CHAR(20),
	CONSTRAINT dlvr_pk PRIMARY KEY (dlvr_cpf)
);

CREATE TABLE receivers (
	rcv_cpf CHAR(11),
	rcv_name VARCHAR2(100),
	rcv_rg CHAR(20),
	CONSTRAINT rcv_pk PRIMARY KEY (rcv_cpf)
);

CREATE TABLE companies (
	cpn_cnpj CHAR(14),
	cpn_name VARCHAR2(100),
	cpn_fantasy VARCHAR2(100),
	CONSTRAINT cpn_pk PRIMARY KEY (cpn_cnpj)
);

CREATE TABLE supplies (
	spl_material NUMBER,
	spl_company CHAR(14),
	CONSTRAINT spl_pk PRIMARY KEY (spl_material, spl_company),
	CONSTRAINT spk_mtr_fk FOREIGN KEY (spl_material) REFERENCES materials(mtr_id) ON DELETE CASCADE,
	CONSTRAINT spk_cpn_fk FOREIGN KEY (spl_company) REFERENCES companies(cpn_cnpj) ON DELETE CASCADE
);

CREATE TABLE schedulings (
	sch_id NUMBER,
	sch_company CHAR(14) NOT NULL,
	CONSTRAINT sch_pk PRIMARY KEY (sch_id),
	CONSTRAINT sch_cpn_fk FOREIGN KEY (sch_company) REFERENCES companies(cpn_cnpj) ON DELETE CASCADE
);

CREATE TABLE licences (
	lcc_deliverer CHAR(11),
	lcc_scheduling NUMBER,
	lcc_vehicle CHAR(7) NOT NULL,
	CONSTRAINT lcc_pk PRIMARY KEY (lcc_deliverer, lcc_scheduling),
	CONSTRAINT lcc_dlvr_fk FOREIGN KEY (lcc_deliverer) REFERENCES deliverers(dlvr_cpf) ON DELETE CASCADE,
	CONSTRAINT lcc_sch_fk FOREIGN KEY (lcc_scheduling) REFERENCES schedulings(sch_id) ON DELETE CASCADE,
	CONSTRAINT lcc_vhc_fk FOREIGN KEY (lcc_vehicle) REFERENCES vehicles(vhc_plate) ON DELETE CASCADE
);

CREATE TABLE sites (
	site_id NUMBER,
	site_name VARCHAR2(50),
	site_street VARCHAR2(100),
	site_number CHAR(10),
	site_city VARCHAR2(50),
	site_state CHAR(2),
	site_zip CHAR(8),
	site_company CHAR(14),
	CONSTRAINT site_pk PRIMARY KEY (site_id),
	CONSTRAINT site_cpn_fk FOREIGN KEY (site_company) REFERENCES companies(cpn_cnpj),
	CONSTRAINT site_address_uk UNIQUE (site_name, site_street, site_number, site_city, site_state, site_zip)
);

CREATE TABLE depots (
	dpt_site NUMBER,
	dpt_number NUMBER,
	dpt_capacity NUMBER,
	dpt_dimension CHAR(20),
	CONSTRAINT dpt_pk PRIMARY KEY (dpt_site, dpt_number),
	CONSTRAINT dpt_site_fk FOREIGN KEY (dpt_site) REFERENCES sites(site_id) ON DELETE CASCADE,
	CONSTRAINT dpt_capacity_ck CHECK (dpt_capacity >= 0)
);

CREATE TABLE periods (
	prd_site NUMBER,
	prd_start DATE,
	prd_end DATE,
	CONSTRAINT prd_pk PRIMARY KEY (prd_site, prd_start, prd_end),
	CONSTRAINT prd_site_fk FOREIGN KEY (prd_site) REFERENCES sites(site_id) ON DELETE CASCADE,
	CONSTRAINT prd_date_ck CHECK (prd_end > prd_start)
);

CREATE TABLE deliveries (
	dlv_id NUMBER,
	dlv_site NUMBER,
	dlv_start DATE,
	dlv_end DATE,
	dlv_scheduling NUMBER NOT NULL,
	dlv_receiver CHAR(11) NOT NULL,
	CONSTRAINT dlv_pk PRIMARY KEY (dlv_id),
	CONSTRAINT dlv_prd_fk FOREIGN KEY (dlv_site, dlv_start, dlv_end) REFERENCES periods(prd_site, prd_start, prd_end) ON DELETE CASCADE,
	CONSTRAINT dlv_sch_fk FOREIGN KEY (dlv_scheduling) REFERENCES schedulings(sch_id) ON DELETE CASCADE,
	CONSTRAINT dlv_rcv_fk FOREIGN KEY (dlv_receiver) REFERENCES receivers(rcv_cpf) ON DELETE CASCADE,
	CONSTRAINT dlv_uk UNIQUE (dlv_site, dlv_start, dlv_end)
);

CREATE TABLE enumerations (
	enum_delivery NUMBER,
	enum_material NUMBER,
	enum_quantity NUMBER,
	CONSTRAINT enum_pk PRIMARY KEY (enum_delivery, enum_material),
	CONSTRAINT enum_dlv_fk FOREIGN KEY (enum_delivery) REFERENCES deliveries(dlv_id) ON DELETE CASCADE,
	CONSTRAINT enum_mtr_fk FOREIGN KEY (enum_material) REFERENCES materials(mtr_id) ON DELETE CASCADE,
	CONSTRAINT enum_quantity_ck CHECK (enum_quantity > 0)
);

-- Sequences to be used by the triggers below
CREATE SEQUENCE mtr_seq;
CREATE SEQUENCE sch_seq;
CREATE SEQUENCE site_seq;
CREATE SEQUENCE dlv_seq;

-- Triggers for auto incrementing the id fields
CREATE OR REPLACE TRIGGER mtr_bir 
BEFORE INSERT ON materials 
FOR EACH ROW
BEGIN
	SELECT mtr_seq.NEXTVAL
	INTO   :new.mtr_id
	FROM   dual;
END;

CREATE OR REPLACE TRIGGER sch_bir 
BEFORE INSERT ON schedulings 
FOR EACH ROW
BEGIN
	SELECT sch_seq.NEXTVAL
	INTO   :new.sch_id
	FROM   dual;
END;

CREATE OR REPLACE TRIGGER site_bir 
BEFORE INSERT ON sites 
FOR EACH ROW
BEGIN
	SELECT site_seq.NEXTVAL
	INTO   :new.site_id
	FROM   dual;
END;

CREATE OR REPLACE TRIGGER dlv_bir 
BEFORE INSERT ON deliveries 
FOR EACH ROW
BEGIN
	SELECT dlv_seq.NEXTVAL
	INTO   :new.dlv_id
	FROM   dual;
END;