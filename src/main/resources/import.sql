
INSERT INTO piano (modelo, fabricante, numerodeteclas, possuipedais, material, tipo)
VALUES
('Yamaha P-125', 'Yamaha', 88, TRUE, 'Madeira', 'DIGITAL'),
('Roland FP-30X', 'Roland', 88, TRUE, 'Plástico', 'DIGITAL'),
('Kawai ES920', 'Kawai', 88, TRUE, 'Madeira', 'DIGITAL'),
('Steinway Model D', 'Steinway & Sons', 88, TRUE, 'Mogno', 'ACUSTICO'),
('Casio PX-S1100', 'Casio', 88, FALSE, 'Metal', 'DIGITAL');

INSERT INTO fornecedores (nome, cnpj, telefone) 
VALUES
('Yamaha Brasil', '12345678000199', '11999998888'),
('Casio Distribuidora', '98765432000111', '21988887777'),
('Roland Music Ltda', '45678912000133', '31977776666'),
('Kawai Pianos', '11223344000166', '11955553333'),
('Steinway & Sons', '99887766000155', '21333332222');

INSERT INTO estados (nome, sigla) 
VALUES
('São Paulo', 'SP'),
('Rio de Janeiro', 'RJ'),
('Minas Gerais', 'MG'),
('Bahia', 'BA'),
('Paraná', 'PR');

-- Municípios de São Paulo
INSERT INTO municipios (nome, estado_id) VALUES
('São Paulo', 1),
('Campinas', 1),
('Santos', 1);

-- Municípios do Rio de Janeiro
INSERT INTO municipios (nome, estado_id) VALUES
('Rio de Janeiro', 2),
('Niterói', 2);

-- Municípios de Minas Gerais
INSERT INTO municipios (nome, estado_id) VALUES
('Belo Horizonte', 3),
('Uberlândia', 3);

-- Municípios da Bahia
INSERT INTO municipios (nome, estado_id) VALUES
('Salvador', 4),
('Feira de Santana', 4);

-- Municípios do Paraná
INSERT INTO municipios (nome, estado_id) VALUES
('Curitiba', 5),
('Londrina', 5);


