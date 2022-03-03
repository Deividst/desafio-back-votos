CREATE TABLE tb_session (
	id serial NOT NULL,
	final_date timestamp NOT NULL,
	initial_date timestamp NOT NULL,
	CONSTRAINT tb_session_pkey PRIMARY KEY (id)
);