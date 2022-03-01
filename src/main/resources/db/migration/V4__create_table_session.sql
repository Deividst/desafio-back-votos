CREATE TABLE tb_session (
	id int8 NOT NULL,
	final_date timestamp NOT NULL,
	initial_date timestamp NOT NULL,
	CONSTRAINT tb_session_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE sq_session
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	NO CYCLE;