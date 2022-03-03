CREATE UNIQUE INDEX associate_index_cpf ON tb_associate (cpf);

ALTER TABLE tb_associate ADD CONSTRAINT associate_unique_cpf UNIQUE USING INDEX associate_index_cpf;