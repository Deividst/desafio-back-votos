ALTER TABLE tb_vote ADD CONSTRAINT unique_vote_associate UNIQUE (associate_id, record_id);