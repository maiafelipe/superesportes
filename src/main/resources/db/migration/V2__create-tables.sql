DROP TABLE IF EXISTS atleta;
CREATE TABLE atleta(
    id bigint not null auto_increment,
    nome varchar(150) not null, 
    idade int, 
    peso float,
    altura float,
    id_equipe bigint,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS equipe;
CREATE TABLE equipe(
    id bigint not null auto_increment,
    nome varchar(150) not null, 
    local varchar(100), 
    PRIMARY KEY(id)
);

ALTER TABLE atleta ADD CONSTRAINT fk_equipe FOREIGN KEY (id_equipe) REFERENCES equipe(id);

DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario(
    id bigint not null auto_increment,
    username varchar(45) not null,
    email varchar(90) not null,
    password varchar(64) not null,
    nome_completo varchar(100) not null,
    role varchar(45) not null,
    enable tinyint(4),
    PRIMARY KEY(id)
);