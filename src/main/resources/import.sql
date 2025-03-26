-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

INSERT INTO piano (modelo, fabricante, numero_de_teclas, possui_pedais, material, tipo)
VALUES
('Yamaha P-125', 'Yamaha', 88, TRUE, 'Madeira', 'DIGITAL'),
('Roland FP-30X', 'Roland', 88, TRUE, 'Plástico', 'DIGITAL'),
('Kawai ES920', 'Kawai', 88, TRUE, 'Madeira', 'DIGITAL'),
('Steinway Model D', 'Steinway & Sons', 88, TRUE, 'Mogno', 'ACUSTICO'),
('Casio PX-S1100', 'Casio', 88, FALSE, 'Metal', 'DIGITAL');
