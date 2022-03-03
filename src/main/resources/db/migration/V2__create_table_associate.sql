CREATE TABLE tb_associate (
	id serial NOT NULL,
	cpf varchar(15) NOT NULL,
	name varchar(255) NOT NULL,
	register_date timestamp NOT NULL,
	CONSTRAINT tb_associate_pkey PRIMARY KEY (id)
);