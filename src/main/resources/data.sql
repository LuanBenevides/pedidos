CREATE TABLE cliente(
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    cpf VARCHAR(11)
);

CREATE TABLE produto(
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(100),
    preco_unitario NUMERIC(20, 2)
);

CREATE TABLE pedido(
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    cliente_id INTEGER REFERENCES cliente(id),
    data_pedido TIMESTAMP,
    status VARCHAR(40),
    total NUMERIC(20, 14)
);

CREATE TABLE item_pedido(
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    pedido_id INTEGER REFERENCES pedido(id),
    produto_id INTEGER REFERENCES produto(id),
    quantidade INTEGER
);
