-- Estados
INSERT INTO estados (id, nome, sigla) VALUES
(1, 'São Paulo', 'SP'),
(2, 'Rio de Janeiro', 'RJ'),
(3, 'Minas Gerais', 'MG'),
(4, 'Bahia', 'BA');

-- Municipios
INSERT INTO municipios (id, nome, estado_id) VALUES
(1, 'São Paulo', 1),
(2, 'Campinas', 1),
(3, 'Rio de Janeiro', 2),
(4, 'Belo Horizonte', 3);

-- Usuarios
INSERT INTO usuarios (id, email, senha, perfil) VALUES
(1, 'cliente1@email.com', 'senha123', 'CLIENTE'),
(2, 'cliente2@email.com', 'senha123', 'CLIENTE'),
(3, 'funcionario1@email.com', 'senha123', 'FUNCIONARIO'),
(4, 'funcionario2@email.com', 'senha123', 'FUNCIONARIO');

-- Clientes
INSERT INTO clientes (id, nome, telefone, cpf, usuario_id) VALUES
(1, 'Cliente Um', '11999999999', '12345678900', 1),
(2, 'Cliente Dois', '11988888888', '12345678901', 2);

-- Funcionarios
INSERT INTO funcionarios (id, nome, telefone, cpf, departamento, salario, usuario_id) VALUES
(3, 'Funcionario Um', '11977777777', '12345678902', 'Vendas', 3000.00, 3),
(4, 'Funcionario Dois', '11966666666', '12345678903', 'Suporte', 3200.00, 4);

-- Fornecedores
INSERT INTO fornecedores (id, nome, cnpj, telefone) VALUES
(1, 'Fornecedor A', '11222333444455', '1133334444'),
(2, 'Fornecedor B', '22333444555666', '1144445555'),
(3, 'Fornecedor C', '33444555666777', '1155556666'),
(4, 'Fornecedor D', '44555666777888', '1166667777');

-- Marcas
INSERT INTO marcas (id, nome, cnpj) VALUES
(1, 'Marca X', '1122233344'),
(2, 'Marca Y', '2233344455'),
(3, 'Marca Z', '3344455566'),
(4, 'Marca W', '4455566677');

-- Relacionamento fornecedor_marca
INSERT INTO fornecedor_marca (fornecedor_id, marca_id) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 3),
(4, 4);

-- Endereços para Clientes (DTYPE = EnderecoCliente)
INSERT INTO endereco (id, rua, numero, bairro, cidade, cep, tipoEndereco, municipio_id, estado_id, DTYPE) VALUES
(1, 'Rua Cliente 1', '100', 'Centro', 'São Paulo', '01000-000', 'COMERCIAL', 1, 1, 'EnderecoCliente'),
(2, 'Rua Cliente 2', '200', 'Bairro Alto', 'Campinas', '13000-000', 'RESIDENCIAL', 2, 1, 'EnderecoCliente');

-- Endereços para Pedidos (DTYPE = EnderecoPedido)
INSERT INTO endereco (id, rua, numero, bairro, cidade, cep, tipoEndereco, municipio_id, estado_id, DTYPE) VALUES
(3, 'Rua Entrega 1', '300', 'Jardins', 'São Paulo', '01400-000', 'COMERCIAL', 1, 1, 'EnderecoPedido'),
(4, 'Rua Entrega 2', '400', 'Botafogo', 'Rio de Janeiro', '22250-040', 'RESIDENCIAL', 3, 2, 'EnderecoPedido');

-- Associação Clientes -> Endereços
INSERT INTO clientes_Endereco (Cliente_id, enderecos_id) VALUES
(1, 1),
(2, 2);

-- Pianos
INSERT INTO piano (id, modelo, preco, fabricante, numeroDeTeclas, possuiPedais, material, tipo, marca_id, fornecedor_id) VALUES
(1, 'Modelo A', 5000.00, 'Fabricante 1', 88, true, 'Madeira', 'ACUSTICO', 1, 1),
(2, 'Modelo B', 3000.00, 'Fabricante 2', 76, true, 'Plástico', 'DIGITAL', 2, 2),
(3, 'Modelo C', 4000.00, 'Fabricante 3', 88, false, 'Madeira', 'HIBRIDO', 3, 3),
(4, 'Modelo D', 6000.00, 'Fabricante 4', 88, true, 'Metal', 'ACUSTICO', 4, 4);

-- Pedidos
INSERT INTO pedido (id, dataCriacao, status, total, cliente_id, endereco_pedido_id) VALUES
(1, now(), 'PENDENTE', 10000.00, 1, 3),
(2, now(), 'PROCESSANDO', 7000.00, 2, 4),
(3, now(), 'ENVIADO', 4000.00, 1, 3),
(4, now(), 'ENTREGUE', 6000.00, 2, 4);


INSERT INTO ItemPedido (id, quantidade, subtotal, pedido_id, piano_id)
VALUES 
(1, 1, 3000.00, 1, 1),
(2, 2, 2000.00, 1, 2),
(3, 1, 7000.00, 2, 3);
