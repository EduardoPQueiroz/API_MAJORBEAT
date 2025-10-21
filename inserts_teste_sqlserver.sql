
use MajorBeatDB

INSERT INTO musico (nome, apelido, email, senha, telefone, endereco, biografia, tipo_musico) VALUES 
('João Silva', 'JSilva', 'joao.silva@example.com', 'senhaSegura123', '11912345678', 'Rua das Flores, 123 - São Paulo, SP', 'Músico apaixonado por jazz e música instrumental. Atua há mais de 10 anos.', 'SOLO');

INSERT INTO musico (nome, apelido, email, senha, telefone, endereco, biografia, tipo_musico) VALUES 
('Maria Oliveira', 'MariOli', 'maria.oliveira@example.com', 'melodiaForte456', '21998765432', 'Av. Atlântica, 987 - Rio de Janeiro, RJ', 'Cantora e compositora com influências da MPB e bossa nova.', 'SOLO');

INSERT INTO musico (nome, apelido, email, senha, telefone, endereco, biografia, tipo_musico) VALUES 
('Lucas Andrade', 'LAndrade', 'lucas.andrade@example.com', 'bateriaTop789', '31977772222', 'Rua dos Tambores, 56 - Belo Horizonte, MG', 'Baterista profissional atuando em bandas de rock alternativo.', 'BANDA');

INSERT INTO musico (nome, apelido, email, senha, telefone, endereco, biografia, tipo_musico) VALUES 
('Ana Costa', 'AnaC', 'ana.costa@example.com', 'pianoMelody321', '41987651122', 'Rua Harmonia, 75 - Curitiba, PR', 'Pianista clássica com experiência em orquestras e eventos.', 'SOLO');

INSERT INTO musico (nome, apelido, email, senha, telefone, endereco, biografia, tipo_musico) VALUES 
('Pedro Rocha', 'PRocha', 'pedro.rocha@example.com', 'baixoGroove654', '71988883344', 'Rua dos Ritmos, 222 - Salvador, BA', 'Baixista apaixonado por groove, funk e reggae.', 'BANDA');

INSERT INTO musico (nome, apelido, email, senha, telefone, endereco, biografia, tipo_musico) VALUES 
('Carolina Mendes', 'CaroM', 'carolina.mendes@example.com', 'vozDoce123', '51996667788', 'Av. Central, 45 - Porto Alegre, RS', 'Cantora de pop e soul, com repertório internacional.', 'SOLO');

INSERT INTO musico (nome, apelido, email, senha, telefone, endereco, biografia, tipo_musico) VALUES 
('Diego Almeida', 'DAlmeida', 'diego.almeida@example.com', 'tecladoVivo852', '61911223344', 'Quadra 303, Brasília, DF', 'Tecladista versátil com experiência em estúdios e casamentos.', 'SOLO');
													
												
--INSERTS INSTRUMENTOS E GÊNEROS
-- João Silva
INSERT INTO musico_nome_instrumentos (musico_id_musico, nome_instrumentos) VALUES
(2, 'VIOLAO'),
(2, 'GUITARRA');

-- Maria Oliveira
INSERT INTO musico_nome_instrumentos (musico_id_musico, nome_instrumentos) VALUES
(3, 'VOZ');

-- Lucas Andrade
INSERT INTO musico_nome_instrumentos (musico_id_musico, nome_instrumentos) VALUES
(4, 'BATERIA');

-- Ana Costa
INSERT INTO musico_nome_instrumentos (musico_id_musico, nome_instrumentos) VALUES
(5, 'PIANO');

-- Pedro Rocha
INSERT INTO musico_nome_instrumentos (musico_id_musico, nome_instrumentos) VALUES
(6, 'BAIXO');

-- Carolina Mendes
INSERT INTO musico_nome_instrumentos (musico_id_musico, nome_instrumentos) VALUES
(7, 'VOZ');

-- Diego Almeida
INSERT INTO musico_nome_instrumentos (musico_id_musico, nome_instrumentos) VALUES
(8, 'TECLADO');


-- João Silva
INSERT INTO musico_nome_generos (musico_id_musico, nome_generos) VALUES
(2, 'JAZZ'),
(2, 'BLUES');

-- Maria Oliveira
INSERT INTO musico_nome_generos (musico_id_musico, nome_generos) VALUES
(3, 'MPB'),
(3, 'BOSSA_NOVA');

-- Lucas Andrade
INSERT INTO musico_nome_generos (musico_id_musico, nome_generos) VALUES
(4, 'ROCK');

-- Ana Costa
INSERT INTO musico_nome_generos (musico_id_musico, nome_generos) VALUES
(5, 'CLÁSSICO');

-- Pedro Rocha
INSERT INTO musico_nome_generos (musico_id_musico, nome_generos) VALUES
(6, 'FUNK'),
(6, 'REGGAE');

-- Carolina Mendes
INSERT INTO musico_nome_generos (musico_id_musico, nome_generos) VALUES
(7, 'POP'),
(7, 'ROCK');

-- Diego Almeida
INSERT INTO musico_nome_generos (musico_id_musico, nome_generos) VALUES
(8, 'GOSPEL');

-- INSERTS: CONTRATANTE
INSERT INTO contratante (nome, senha, telefone, email, endereco, biografia, nome_empresa, tipo_contratante) VALUES ('João Silva', 'SenhaSegura123!', '11987654321', 'joao.silva@example.com', 'Rua das Flores, 123 - São Paulo, SP', 'Produtor de eventos musicais há mais de 10 anos, especializado em shows e festivais.', 'Silva Produções Artísticas', 'ESTABELECIMENTO');
INSERT INTO contratante (nome, senha, telefone, email, endereco, biografia, nome_empresa, tipo_contratante) VALUES ('Maria Oliveira', 'Maria@2025', '21999887766', 'maria.oliveira@example.com', 'Av. Atlântica, 500 - Rio de Janeiro, RJ', 'Apaixonada por música e eventos culturais, contratante independente de bandas locais.', '', 'PESSOA_FISICA');
INSERT INTO contratante (nome, senha, telefone, email, endereco, biografia, nome_empresa, tipo_contratante) VALUES ('Lucas Andrade', 'Luk123!@#', '31988776655', 'lucas.andrade@beatsound.com', 'Rua Belo Horizonte, 742 - Belo Horizonte, MG', 'Gerente do BeatSound Pub, responsável por eventos de música ao vivo toda semana.', 'BeatSound Pub', 'ESTABELECIMENTO');
INSERT INTO contratante (nome, senha, telefone, email, endereco, biografia, nome_empresa, tipo_contratante) VALUES ('Carla Mendes', 'Carla#2024', '41991234567', 'carla.mendes@gmail.com', 'Rua XV de Novembro, 145 - Curitiba, PR', 'Organizadora de eventos particulares e festas corporativas. Ama descobrir novos talentos musicais.', '', 'PESSOA_FISICA');
INSERT INTO contratante (nome, senha, telefone, email, endereco, biografia, nome_empresa, tipo_contratante) VALUES ('Eduardo Lima', 'EduLima@2025', '11995554433', 'eduardo.lima@aurafest.com', 'Av. Paulista, 2000 - São Paulo, SP', 'CEO da AuraFest, empresa especializada em grandes eventos e festivais de música eletrônica.', 'AuraFest Produções', 'ESTABELECIMENTO');

-- INSERTS: EVENTO
INSERT INTO evento (nome, tipo_musico, data, endereco, hora_inicio, hora_fim, descricao, titulo) VALUES ('Noite Acústica no Terraço', 'SOLO', '2025-11-10 20:00:00', 'Av. Paulista, 1500 - São Paulo, SP', '20:00:00', '23:00:00', 'Evento intimista com artistas locais apresentando repertório acústico.', 'Noite Acústica');
INSERT INTO evento (nome, tipo_musico, data, endereco, hora_inicio, hora_fim, descricao, titulo) VALUES ('Festival Rock na Praça', 'BANDA', '2025-12-05 18:00:00', 'Praça da Liberdade - Belo Horizonte, MG', '18:00:00', '23:59:00', 'Festival gratuito com bandas de rock e metal independentes.', 'Rock na Praça');
INSERT INTO evento (nome, tipo_musico, data, endereco, hora_inicio, hora_fim, descricao, titulo) VALUES ('Jazz & Wine Experience', 'BANDA', '2025-11-22 19:30:00', 'Rua das Palmeiras, 250 - Campinas, SP', '19:30:00', '23:30:00', 'Uma noite sofisticada de jazz acompanhada por uma degustação de vinhos selecionados.', 'Jazz & Wine Experience');
INSERT INTO evento (nome, tipo_musico, data, endereco, hora_inicio, hora_fim, descricao, titulo) VALUES ('Sunset Eletrônico', 'SOLO', '2025-12-20 17:00:00', 'Praia de Copacabana - Rio de Janeiro, RJ', '17:00:00', '22:00:00', 'Festa sunset com DJs renomados e clima de verão à beira-mar.', 'Sunset Eletrônico');
INSERT INTO evento (nome, tipo_musico, data, endereco, hora_inicio, hora_fim, descricao, titulo) VALUES ('Sertanejo Night', 'BANDA', '2025-11-30 21:00:00', 'Arena Goiânia - Goiânia, GO', '21:00:00', '02:00:00', 'A maior noite sertaneja da região com duplas de destaque e muito agito.', 'Sertanejo Night');

-- INSERTS: CHAT
INSERT INTO chat (musico_id_musico, contratante_id_contratante) VALUES (1, 2);
INSERT INTO chat (musico_id_musico, contratante_id_contratante) VALUES (3, 1);
INSERT INTO chat (musico_id_musico, contratante_id_contratante) VALUES (2, 4);
INSERT INTO chat (musico_id_musico, contratante_id_contratante) VALUES (5, 3);
INSERT INTO chat (musico_id_musico, contratante_id_contratante) VALUES (4, 5);

-- INSERTS: MENSAGEM
INSERT INTO mensagem (texto, proposta, valor, evento_id_evento, chat_id_chat) VALUES ('Olá! Vi seu perfil e gostaria de saber se está disponível para um evento em novembro.', 0, NULL, NULL, 1);
INSERT INTO mensagem (texto, proposta, valor, evento_id_evento, chat_id_chat) VALUES ('Gostei do seu trabalho! Você se apresenta em festas particulares também?', 0, NULL, NULL, 2);
INSERT INTO mensagem (texto, proposta, valor, evento_id_evento, chat_id_chat) VALUES ('Tenho um evento no dia 20/12 e posso pagar R$900 pela apresentação.', 1, 900.0, 4, 3);
INSERT INTO mensagem (texto, proposta, valor, evento_id_evento, chat_id_chat) VALUES ('Podemos fechar por R$1.200 para o evento Sertanejo Night?', 1, 1200.0, 5, 4);
INSERT INTO mensagem (texto, proposta, valor, evento_id_evento, chat_id_chat) VALUES ('Tenho um show acústico no Terraço e o cachê é de R$700, incluindo o som básico.', 1, 700.0, 2, 5);

-- INSERTS: AVALIACAO
INSERT INTO avaliacao (nota, comentario, id_recebedor) VALUES (5, 'Experiência incrível! Profissional super pontual e talentoso, recomendo demais.', 1);
INSERT INTO avaliacao (nota, comentario, id_recebedor) VALUES (4, 'Show muito bom, mas o som teve alguns problemas técnicos no início.', 2);
INSERT INTO avaliacao (nota, comentario, id_recebedor) VALUES (3, 'Apresentação ok, mas esperava mais interação com o público.', 3);
INSERT INTO avaliacao (nota, comentario, id_recebedor) VALUES (2, 'Houve atraso e a comunicação poderia ter sido melhor.', 4);
INSERT INTO avaliacao (nota, comentario, id_recebedor) VALUES (1, 'Infelizmente o profissional não compareceu ao evento e não avisou com antecedência.', 5);
INSERT INTO avaliacao (nota, comentario, id_recebedor) VALUES (5, 'Baita apresentação', 5);


INSERT INTO musico_avaliacoes (musico_id_musico, avaliacoes_id_avaliacao) VALUES
(2, 1),  -- João Silva recebeu a avaliação 1
(3, 2),  -- Maria Oliveira recebeu a avaliação 2
(4, 3),  -- Lucas Andrade recebeu a avaliação 3
(5, 4),  -- Ana Costa recebeu a avaliação 4
(6, 5),  -- Pedro Rocha recebeu a avaliação 5
(6, 6);