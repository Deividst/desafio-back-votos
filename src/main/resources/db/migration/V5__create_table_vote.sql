CREATE TABLE tb_vote (
	id serial NOT NULL,
	register_date timestamp NOT NULL,
	vote_type int4 NOT NULL,
	associate_id int8 NOT NULL,
	record_id int8 NOT NULL,
	CONSTRAINT tb_vote_pkey PRIMARY KEY (id)
);


