CREATE TABLE receitas(
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         descricao VARCHAR(180) NOT NULL,
                         valor DOUBLE NOT NULL,
                         mes int NOT NULL,  -- Usando o formato 'YYYY-MM'
                         PRIMARY KEY (id)
);

CREATE TABLE despesas(
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         descricao VARCHAR(180) NOT NULL,
                         valor DOUBLE NOT NULL,
                         mes int NOT NULL,  -- Usando o formato 'YYYY-MM'
                         PRIMARY KEY (id)
);

CREATE TABLE usuarios(
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(180) NOT NULL,
    senha VARCHAR(180) not null,

    primary key (id)

)

