CREATE TABLE tb_associate (
	id int8 NOT NULL,
	cpf varchar(15) NOT NULL,
	name varchar(255) NULL,
	register_date timestamp NOT NULL,
	CONSTRAINT tb_associate_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE sq_associate
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	NO CYCLE;