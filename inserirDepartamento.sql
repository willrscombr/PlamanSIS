INSERT INTO tb_departamento (
   id
  ,nome
  ,sigla
) VALUES (
   1
  ,'Eletrica'  -- nome - IN varchar(255)
  ,'ELT'  -- sigla - IN varchar(255)
);

INSERT INTO tb_departamento (
   id
  ,nome
  ,sigla
) VALUES (
   2
  ,'Mecanica'  -- nome - IN varchar(255)
  ,'MEC'  -- sigla - IN varchar(255)
);

INSERT INTO tb_departamento (
   id
  ,nome
  ,sigla
) VALUES (
   3
  ,'Manutencao'  -- nome - IN varchar(255)
  ,'MAN'  -- sigla - IN varchar(255)
);
INSERT INTO tb_departamento (
   id
  ,nome
  ,sigla
) VALUES (
   4
  ,'Operacional'  -- nome - IN varchar(255)
  ,'OPE'  -- sigla - IN varchar(255)
);

INSERT INTO tb_departamento (
   id
  ,nome
  ,sigla
) VALUES (
   5
  ,'Refrigeracao'  -- nome - IN varchar(255)
  ,'REFR'  -- sigla - IN varchar(255)
);

INSERT INTO tb_departamento (
   id
  ,nome
  ,sigla
) VALUES (
   6
  ,'Planejamento'  -- nome - IN varchar(255)
  ,'PLAN'  -- sigla - IN varchar(255)
);
INSERT INTO tb_departamento (
   id
  ,nome
  ,sigla
) VALUES (
   7
  ,'Administracao'  -- nome - IN varchar(255)
  ,'ADM'  -- sigla - IN varchar(255)
);



INSERT INTO tb_colaborador (
   id_colaborador
  ,cargo
  ,matricula
  ,nome
  ,sobrenome
  ,setor_id
) VALUES (
   1
  ,'Planejador'  -- cargo - IN varchar(255)
  ,'456'  -- matricula - IN varchar(255)
  ,'mauricio'  -- nome - IN varchar(255)
  ,'Teixeira'  -- sobrenome - IN varchar(255)
  ,6   -- setor_id - IN bigint(20);
);

INSERT INTO tb_colaborador (
   id_colaborador
  ,cargo
  ,matricula
  ,nome
  ,sobrenome
  ,setor_id
) VALUES (
   2
  ,'Desenvolvedor'  -- cargo - IN varchar(255)
  ,'815'  -- matricula - IN varchar(255)
  ,'maique'  -- nome - IN varchar(255)
  ,'Rosa'  -- sobrenome - IN varchar(255)
  ,7   -- setor_id - IN bigint(20);
);