ALTER TABLE despesas
    MODIFY mes int NOT NULL

ALTER TABLE despesas
    modify ano int

ALTER TABLE receitas
    MODIFY mes int NOT NULL

ALTER TABLE receitas
    modify ano int

UPDATE receitas
SET ano = COALESCE(ano, 0)
WHERE ano IS NULL;

