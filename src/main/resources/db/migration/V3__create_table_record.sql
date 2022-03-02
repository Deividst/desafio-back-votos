CREATE TABLE tb_record (
	id int8 NOT NULL,
	description varchar(255) NULL,
	register_date timestamp NOT NULL,
	status int4 NULL DEFAULT 1,
	subject varchar(255) NOT NULL,
	session_id int8,
	CONSTRAINT tb_record_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE sq_record
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	NO CYCLE;
