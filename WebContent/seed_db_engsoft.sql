-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tempo de Geração: Dez 01, 2011 as 10:49 AM
-- Versão do Servidor: 5.1.54
-- Versão do PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de Dados: `engsoft`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `alternativasdasquestoes`
--

CREATE TABLE IF NOT EXISTS `alternativasdasquestoes` (
  `QuestaoDeMultiplaEscolha_id` bigint(20) NOT NULL,
  `alternativas` varchar(255) DEFAULT NULL,
  KEY `FK9BD26A7390E4E8F` (`QuestaoDeMultiplaEscolha_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `alternativasdasquestoes`
--

INSERT INTO `alternativasdasquestoes` (`QuestaoDeMultiplaEscolha_id`, `alternativas`) VALUES
(2, 'Alternativa 1'),
(2, 'Alternativa 2 - Certa'),
(2, 'Alternativa 3'),
(6, 'A1'),
(6, 'A2'),
(6, 'A3'),
(6, 'A4'),
(6, 'A5');

-- --------------------------------------------------------

--
-- Estrutura da tabela `disciplina`
--

CREATE TABLE IF NOT EXISTS `disciplina` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Extraindo dados da tabela `disciplina`
--

INSERT INTO `disciplina` (`id`, `nome`) VALUES
(1, 'MAC 007');

-- --------------------------------------------------------

--
-- Estrutura da tabela `listadeexercicios`
--

CREATE TABLE IF NOT EXISTS `listadeexercicios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enunciado` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `peso` int(11) DEFAULT NULL,
  `prazoDeEntrega` datetime DEFAULT NULL,
  `visivel` bit(1) DEFAULT NULL,
  `autoCorrecao` int(11) DEFAULT NULL,
  `numeroMaximoDeEnvios` int(11) DEFAULT NULL,
  `id_turma` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE78489341B0D88D1` (`id_turma`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Extraindo dados da tabela `listadeexercicios`
--

INSERT INTO `listadeexercicios` (`id`, `enunciado`, `nome`, `peso`, `prazoDeEntrega`, `visivel`, `autoCorrecao`, `numeroMaximoDeEnvios`, `id_turma`) VALUES
(1, 'Enunciado', 'Lista1', 10, '2011-10-25 12:38:35', '1', NULL, NULL, NULL),
(2, 'Enunciado', 'Lista 2', 10, '2011-10-31 23:55:18', '1', 0, 5, NULL),
(3, 'Enunciado L3', 'Lista 3', 10, '2011-12-01 23:55:55', '1', 1, 20, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `listaderespostas`
--

CREATE TABLE IF NOT EXISTS `listaderespostas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `notaFinal` int(11) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `numeroDeEnvios` int(11) DEFAULT NULL,
  `id_aluno` bigint(20) DEFAULT NULL,
  `id_lista` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK36F59A8E7D592E5E` (`id_lista`),
  KEY `FK36F59A8E18EDF3DD` (`id_aluno`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Extraindo dados da tabela `listaderespostas`
--

INSERT INTO `listaderespostas` (`id`, `notaFinal`, `estado`, `numeroDeEnvios`, `id_aluno`, `id_lista`) VALUES
(3, NULL, NULL, NULL, 1, 1),
(4, NULL, NULL, NULL, 1, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `listadesolucoes`
--

CREATE TABLE IF NOT EXISTS `listadesolucoes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_lista` bigint(20) DEFAULT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1C5FB7E3F111EAA3` (`id_usuario`),
  KEY `FK1C5FB7E37D592E5E` (`id_lista`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Extraindo dados da tabela `listadesolucoes`
--

INSERT INTO `listadesolucoes` (`id`, `id_lista`, `id_usuario`) VALUES
(1, 3, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `notasdalista`
--

CREATE TABLE IF NOT EXISTS `notasdalista` (
  `ListaDeRespostas_id` bigint(20) NOT NULL,
  `notas` int(11) DEFAULT NULL,
  KEY `FK1DE94F0156EC9FAF` (`ListaDeRespostas_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `notasdalista`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `propriedadesdalistaderespostas`
--

CREATE TABLE IF NOT EXISTS `propriedadesdalistaderespostas` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4AAE1535C084707E` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `propriedadesdalistaderespostas`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `questao`
--

CREATE TABLE IF NOT EXISTS `questao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enunciado` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Extraindo dados da tabela `questao`
--

INSERT INTO `questao` (`id`, `enunciado`) VALUES
(1, 'Essa eh a pergunta'),
(2, 'Essa eh a pergunta de Multipla Escolha'),
(3, 'Essa eh a pergunta de V ou F. Essa eh V'),
(4, 'Enunciado da Questão v1'),
(5, 'Enunciado da Questão v2'),
(6, 'Enunciado da Questão');

-- --------------------------------------------------------

--
-- Estrutura da tabela `questaodemultiplaescolha`
--

CREATE TABLE IF NOT EXISTS `questaodemultiplaescolha` (
  `resposta` int(11) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `respostaUnica` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2EA6C58E7E8A1D16` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `questaodemultiplaescolha`
--

INSERT INTO `questaodemultiplaescolha` (`resposta`, `id`, `respostaUnica`) VALUES
(2, 2, NULL),
(16, 6, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `questaodesubmissaodearquivo`
--

CREATE TABLE IF NOT EXISTS `questaodesubmissaodearquivo` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6E2B74937E8A1D16` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `questaodesubmissaodearquivo`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `questaodetexto`
--

CREATE TABLE IF NOT EXISTS `questaodetexto` (
  `resposta` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD09BA517E8A1D16` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `questaodetexto`
--

INSERT INTO `questaodetexto` (`resposta`, `id`) VALUES
('Essa eh a resposta', 1),
('Resposta da Questão v1', 4);

-- --------------------------------------------------------

--
-- Estrutura da tabela `questaodevouf`
--

CREATE TABLE IF NOT EXISTS `questaodevouf` (
  `resposta` bit(1) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6BC78F9B7E8A1D16` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `questaodevouf`
--

INSERT INTO `questaodevouf` (`resposta`, `id`) VALUES
('1', 3),
('0', 5);

-- --------------------------------------------------------

--
-- Estrutura da tabela `questoesdalista`
--

CREATE TABLE IF NOT EXISTS `questoesdalista` (
  `ListaDeExercicios_id` bigint(20) NOT NULL,
  `questao_id` bigint(20) DEFAULT NULL,
  `peso` int(11) DEFAULT NULL,
  `ordem` int(11) DEFAULT NULL,
  KEY `FKB6642B4B8833AB25` (`ListaDeExercicios_id`),
  KEY `FKB6642B4BA0FC0325` (`questao_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `questoesdalista`
--

INSERT INTO `questoesdalista` (`ListaDeExercicios_id`, `questao_id`, `peso`, `ordem`) VALUES
(1, 1, NULL, 2),
(1, 2, NULL, 3),
(1, 3, NULL, 1),
(2, 1, 1, 1),
(2, 2, 1, 2),
(2, 3, 1, 3),
(3, 1, 1, 1),
(3, 3, 1, 2),
(3, 6, 1, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `respostasdalista`
--

CREATE TABLE IF NOT EXISTS `respostasdalista` (
  `ListaDeRespostas_id` bigint(20) NOT NULL,
  `valor` varchar(255) DEFAULT NULL,
  `booleano` bit(1) DEFAULT NULL,
  `inteiro` int(11) DEFAULT NULL,
  `texto` varchar(255) DEFAULT NULL,
  `questao_id` bigint(20) DEFAULT NULL,
  `nota` double DEFAULT NULL,
  KEY `FK2FA7E33456EC9FAF` (`ListaDeRespostas_id`),
  KEY `FK2FA7E334A0FC0325` (`questao_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `respostasdalista`
--

INSERT INTO `respostasdalista` (`ListaDeRespostas_id`, `valor`, `booleano`, `inteiro`, `texto`, `questao_id`, `nota`) VALUES
(3, 'Mano', NULL, NULL, NULL, 3, NULL),
(3, '2', NULL, NULL, NULL, 1, NULL),
(3, '0', NULL, NULL, NULL, 2, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `solucoesdalista`
--

CREATE TABLE IF NOT EXISTS `solucoesdalista` (
  `ListaDeSolucoes_id` bigint(20) NOT NULL,
  `nota` double DEFAULT NULL,
  `questao_id` bigint(20) DEFAULT NULL,
  `valor` varchar(255) DEFAULT NULL,
  KEY `FKD0B0DF873026F785` (`ListaDeSolucoes_id`),
  KEY `FKD0B0DF87A0FC0325` (`questao_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `solucoesdalista`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `turma`
--

CREATE TABLE IF NOT EXISTS `turma` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `disciplina_id` bigint(20) DEFAULT NULL,
  `professor_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4D69F4566E09405` (`professor_id`),
  KEY `FK4D69F4572D1D2EF` (`disciplina_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Extraindo dados da tabela `turma`
--

INSERT INTO `turma` (`id`, `nome`, `disciplina_id`, `professor_id`) VALUES
(1, '2011_2 Professor W', 1, 2),
(2, 'Turma6', 1, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `turma_aluno`
--

CREATE TABLE IF NOT EXISTS `turma_aluno` (
  `turma_id` bigint(20) NOT NULL,
  `aluno_id` bigint(20) NOT NULL,
  KEY `FK73148F51A7594005` (`aluno_id`),
  KEY `FK73148F51350475C5` (`turma_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `turma_aluno`
--

INSERT INTO `turma_aluno` (`turma_id`, `aluno_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `turma_listas`
--

CREATE TABLE IF NOT EXISTS `turma_listas` (
  `id_lista` bigint(20) NOT NULL,
  `id_turma` bigint(20) NOT NULL,
  KEY `FK2EE74F6A7D592E5E` (`id_lista`),
  KEY `FK2EE74F6A1B0D88D1` (`id_turma`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `turma_listas`
--

INSERT INTO `turma_listas` (`id_lista`, `id_turma`) VALUES
(1, 1),
(2, 1),
(3, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `DTYPE` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `privilegio` int(11) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Extraindo dados da tabela `usuario`
--

-- OBS: senhas para usuários de teste
-- login: alunow senha: alunow
-- login: professorw senha: professorw
-- login: admin senha: professorw

INSERT INTO `usuario` (`DTYPE`, `id`, `email`, `login`, `nome`, `privilegio`, `senha`) VALUES
('Aluno', 1, 'alunow@usp.br', 'alunow', 'Wallace Aluno', 0, 'ec924f2b64b764b1738e7854789a1bd3'),
('Professor', 2, 'professorw@usp.br', 'professorw', 'Wallace Professor', 2, '77ae27dd3b84f07d3bafd3364e464'),
('Aluno', 3, 'email@email.com', 'teste', 'Teste', 0, 'e8d95a51f3af4a3b134bf6bb680a213a'),
('Aluno', 4, 'teste2', 'teste2', 'Teste2', 0, 'e8d95a51f3af4a3b134bf6bb680a213a'),
('Aluno', 5, 'senha', 'susanna', 'suzana', 0, 'e8d95a51f3af4a3b134bf6bb680a213a'),
('Aluno', 6, 'senha', 'senha', 'senha', 0, 'e8d95a51f3af4a3b134bf6bb68a213a'),
('Aluno', 7, 'email@email.com', 'admin', 'admin', 3, '77ae27dd3b84f07d3bafd3364e464');

--
-- Restrições para as tabelas dumpadas
--

--
-- Restrições para a tabela `alternativasdasquestoes`
--
ALTER TABLE `alternativasdasquestoes`
  ADD CONSTRAINT `FK9BD26A7390E4E8F` FOREIGN KEY (`QuestaoDeMultiplaEscolha_id`) REFERENCES `questaodemultiplaescolha` (`id`);

--
-- Restrições para a tabela `listadeexercicios`
--
ALTER TABLE `listadeexercicios`
  ADD CONSTRAINT `FKE78489341B0D88D1` FOREIGN KEY (`id_turma`) REFERENCES `turma` (`id`);

--
-- Restrições para a tabela `listaderespostas`
--
ALTER TABLE `listaderespostas`
  ADD CONSTRAINT `FK36F59A8E18EDF3DD` FOREIGN KEY (`id_aluno`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `FK36F59A8E7D592E5E` FOREIGN KEY (`id_lista`) REFERENCES `listadeexercicios` (`id`);

--
-- Restrições para a tabela `listadesolucoes`
--
ALTER TABLE `listadesolucoes`
  ADD CONSTRAINT `FK1C5FB7E37D592E5E` FOREIGN KEY (`id_lista`) REFERENCES `listadeexercicios` (`id`),
  ADD CONSTRAINT `FK1C5FB7E3F111EAA3` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);

--
-- Restrições para a tabela `notasdalista`
--
ALTER TABLE `notasdalista`
  ADD CONSTRAINT `FK1DE94F0156EC9FAF` FOREIGN KEY (`ListaDeRespostas_id`) REFERENCES `listaderespostas` (`id`);

--
-- Restrições para a tabela `propriedadesdalistaderespostas`
--
ALTER TABLE `propriedadesdalistaderespostas`
  ADD CONSTRAINT `FK4AAE1535C084707E` FOREIGN KEY (`id`) REFERENCES `listaderespostas` (`id`);

--
-- Restrições para a tabela `questaodemultiplaescolha`
--
ALTER TABLE `questaodemultiplaescolha`
  ADD CONSTRAINT `FK2EA6C58E7E8A1D16` FOREIGN KEY (`id`) REFERENCES `questao` (`id`);

--
-- Restrições para a tabela `questaodesubmissaodearquivo`
--
ALTER TABLE `questaodesubmissaodearquivo`
  ADD CONSTRAINT `FK6E2B74937E8A1D16` FOREIGN KEY (`id`) REFERENCES `questao` (`id`);

--
-- Restrições para a tabela `questaodetexto`
--
ALTER TABLE `questaodetexto`
  ADD CONSTRAINT `FKD09BA517E8A1D16` FOREIGN KEY (`id`) REFERENCES `questao` (`id`);

--
-- Restrições para a tabela `questaodevouf`
--
ALTER TABLE `questaodevouf`
  ADD CONSTRAINT `FK6BC78F9B7E8A1D16` FOREIGN KEY (`id`) REFERENCES `questao` (`id`);

--
-- Restrições para a tabela `questoesdalista`
--
ALTER TABLE `questoesdalista`
  ADD CONSTRAINT `FKB6642B4B8833AB25` FOREIGN KEY (`ListaDeExercicios_id`) REFERENCES `listadeexercicios` (`id`),
  ADD CONSTRAINT `FKB6642B4BA0FC0325` FOREIGN KEY (`questao_id`) REFERENCES `questao` (`id`);

--
-- Restrições para a tabela `respostasdalista`
--
ALTER TABLE `respostasdalista`
  ADD CONSTRAINT `FK2FA7E33456EC9FAF` FOREIGN KEY (`ListaDeRespostas_id`) REFERENCES `listaderespostas` (`id`),
  ADD CONSTRAINT `FK2FA7E334A0FC0325` FOREIGN KEY (`questao_id`) REFERENCES `questao` (`id`);

--
-- Restrições para a tabela `solucoesdalista`
--
ALTER TABLE `solucoesdalista`
  ADD CONSTRAINT `FKD0B0DF873026F785` FOREIGN KEY (`ListaDeSolucoes_id`) REFERENCES `listadesolucoes` (`id`),
  ADD CONSTRAINT `FKD0B0DF87A0FC0325` FOREIGN KEY (`questao_id`) REFERENCES `questao` (`id`);

--
-- Restrições para a tabela `turma`
--
ALTER TABLE `turma`
  ADD CONSTRAINT `FK4D69F4566E09405` FOREIGN KEY (`professor_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `FK4D69F4572D1D2EF` FOREIGN KEY (`disciplina_id`) REFERENCES `disciplina` (`id`);

--
-- Restrições para a tabela `turma_aluno`
--
ALTER TABLE `turma_aluno`
  ADD CONSTRAINT `FK73148F51350475C5` FOREIGN KEY (`turma_id`) REFERENCES `turma` (`id`),
  ADD CONSTRAINT `FK73148F51A7594005` FOREIGN KEY (`aluno_id`) REFERENCES `usuario` (`id`);

--
-- Restrições para a tabela `turma_listas`
--
ALTER TABLE `turma_listas`
  ADD CONSTRAINT `FK2EE74F6A1B0D88D1` FOREIGN KEY (`id_turma`) REFERENCES `turma` (`id`),
  ADD CONSTRAINT `FK2EE74F6A7D592E5E` FOREIGN KEY (`id_lista`) REFERENCES `listadeexercicios` (`id`);
