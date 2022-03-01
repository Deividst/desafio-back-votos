ALTER TABLE tb_record ADD CONSTRAINT fk_tb_record_session_id FOREIGN KEY (session_id) REFERENCES tb_session(id);

ALTER TABLE tb_vote ADD CONSTRAINT fk_tb_vote_record_id FOREIGN KEY (record_id) REFERENCES tb_record(id);
ALTER TABLE tb_vote ADD CONSTRAINT fk_tb_vote_associate_id FOREIGN KEY (associate_id) REFERENCES tb_associate(id);