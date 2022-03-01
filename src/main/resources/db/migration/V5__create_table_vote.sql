CREATE TABLE tb_vote (
	id int8 NOT NULL,
	register_date timestamp NOT NULL,
	vote_type int4 NOT NULL,
	associate_id int8 NOT NULL,
	record_id int8 NOT NULL,
	CONSTRAINT tb_vote_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE sq_vote
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	NO CYCLE;


