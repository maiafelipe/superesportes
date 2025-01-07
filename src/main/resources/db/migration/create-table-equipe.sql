DROP TABLE IF EXISTS equipe;
CREATE TABLE equipe(
    id bigint not null auto_increment,
    nome varchar(150) not null, 
    local varchar(100), 
    PRIMARY KEY(id)
);

ALTER TABLE atleta ADD CONSTRAINT fk_equipe FOREIGN KEY (id_equipe) REFERENCES equipe(id);


