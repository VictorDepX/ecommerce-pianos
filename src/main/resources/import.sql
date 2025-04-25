
INSERT INTO piano (modelo, fabricante, numerodeteclas, possuipedais, material, tipo)
VALUES
('Yamaha P-125', 'Yamaha', 88, TRUE, 'Madeira', 'DIGITAL'),
('Roland FP-30X', 'Roland', 88, TRUE, 'Plástico', 'DIGITAL'),
('Kawai ES920', 'Kawai', 88, TRUE, 'Madeira', 'DIGITAL'),
('Steinway Model D', 'Steinway & Sons', 88, TRUE, 'Mogno', 'ACUSTICO'),
('Casio PX-S1100', 'Casio', 88, FALSE, 'Metal', 'DIGITAL');

INSERT INTO fornecedores (id, nome, cnpj, telefone) VALUES
  (1, 'Yamaha Corporation',    '11111111000101', '(11) 99999-1111'),
  (2, 'Casio Computer Co.',    '22222222000102', '(11) 99999-2222'),
  (3, 'Roland Corporation',    '33333333000103', '(11) 99999-3333'),
  (4, 'Kawai Musical Inst.',   '44444444000104', '(11) 99999-4444'),
  (5, 'Steinway & Sons',       '55555555000105', '(11) 99999-5555');


INSERT INTO estados (nome, sigla) 
VALUES
('São Paulo', 'SP'),
('Rio de Janeiro', 'RJ'),
('Minas Gerais', 'MG'),
('Bahia', 'BA'),
('Paraná', 'PR');

------------------ MUNICIPIOS --------------------------------- 

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

---------------------------------------------------------------------

INSERT INTO marcas (id, nome, cnpj) 
VALUES
  (6, 'Nord Keyboards',        '12345678000110'),
  (7, 'Kurzweil Music Systems','12345678000120'),
  (8, 'Korg Inc.',             '12345678000130'),
  (9, 'Bechstein',             '12345678000140'),
  (10, 'Blüthner',             '12345678000150');

  -- FORNECEDOR_MARCA (RELACIONAMENTOS)
INSERT INTO fornecedor_marca (fornecedor_id, marca_id) 
VALUES
  (1, 6), -- Yamaha > Nord
  (1, 7), -- Yamaha > Kurzweil
  (1, 8), -- Yamaha > Korg
  (2, 7), -- Casio > Kurzweil
  (3, 8), -- Roland > Korg
  (4, 10), -- Kawai > Blüthner
  (5, 9);  -- Steinway > Bechstein

  INSERT INTO usuarios (id, email, senha, perfil) 
  VALUES
  (1, 'cliente01@email.com', 'senha123', 'CLIENTE'),
  (2, 'funcionario01@email.com', 'senha456', 'FUNCIONARIO');

INSERT INTO clientes (id, nome, telefone, cpf, usuario_id) 
VALUES
  (1, 'João da Silva', '(11) 91234-5678', '12345678901', 1);

INSERT INTO funcionarios (id, nome, telefone, cpf, salario, departamento, usuario_id) 
VALUES
  (1, 'Maria Oliveira', '(11) 97654-3210', '98765432100', 3500.00, 'Vendas', 2);

