# Spring Boot API - Achados e Perdidos

#### MySQL Script (Executar na ordem correta):

```sql
-- Primeira tabela
CREATE TABLE IF NOT EXISTS `tb_categoria` (
   `id` binary(16) NOT NULL,
   `descricao` varchar(255) DEFAULT NULL,
   `nome` varchar(255) DEFAULT NULL,
   `data_cadastro` datetime(6) DEFAULT NULL,
   `data_atualizacao` datetime(6) DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Segunda tabela
CREATE TABLE IF NOT EXISTS `tb_cidade` (
   `id` int NOT NULL,
   `uf` varchar(45) DEFAULT NULL,
   `nome` varchar(512) DEFAULT NULL,
   PRIMARY KEY (`id`),
   UNIQUE KEY `uk_cidade_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Terceira tabela
CREATE TABLE IF NOT EXISTS `tb_usuario` (
   `id` binary(16) NOT NULL,
   `email` varchar(255) DEFAULT NULL,
   `nome_completo` varchar(255) DEFAULT NULL,
   `telefone` varchar(255) DEFAULT NULL,
   `data_cadastro` datetime(6) DEFAULT NULL,
   `data_atualizacao` datetime(6) DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Quarta tabela
CREATE TABLE IF NOT EXISTS `tb_postagem` (
   `id` binary(16) NOT NULL,
   `descricao` varchar(255) DEFAULT NULL,
   `data_cadastro` datetime(6) DEFAULT NULL,
   `titulo` varchar(255) DEFAULT NULL,
   `data_atualizacao` datetime(6) DEFAULT NULL,
   `item_id` binary(16) DEFAULT NULL,
   `usuario_id` binary(16) DEFAULT NULL,
   `status` varchar(48) NOT NULL,
   PRIMARY KEY (`id`),
   KEY `fk_postagem_item` (`item_id`),
   KEY `fk_postagem_usuario` (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Quinta tabela
CREATE TABLE IF NOT EXISTS `tb_item` (
   `id` binary(16) NOT NULL,
   `data` datetime(6) DEFAULT NULL,
   `descricao` varchar(255) DEFAULT NULL,
   `tipo` enum('LOST','FOUND') DEFAULT NULL,
   `localizacao` varchar(255) DEFAULT NULL,
   `nome` varchar(255) DEFAULT NULL,
   `cidade_id` int DEFAULT NULL,
   `postagem_id` binary(16) DEFAULT NULL,
   PRIMARY KEY (`id`),
   UNIQUE KEY `uk_item_postagem` (`postagem_id`),
   KEY `fk_item_cidade` (`cidade_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Sexta tabela
CREATE TABLE IF NOT EXISTS `tb_imagem` (
   `id` binary(16) NOT NULL,
   `url` varchar(255) DEFAULT NULL,
   `item_id` binary(16) NOT NULL,
   PRIMARY KEY (`id`),
   KEY `fk_imagem_item` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Sétima tabela (relacionamento entre postagem e categoria)
CREATE TABLE IF NOT EXISTS `tb_postagem_categoria` (
   `postagem_id` binary(16) NOT NULL,
   `categoria_id` binary(16) NOT NULL,
   PRIMARY KEY (`postagem_id`,`categoria_id`),
   KEY `fk_postagem_categoria` (`categoria_id`),
   CONSTRAINT `fk_postagem_categoria` FOREIGN KEY (`postagem_id`) REFERENCES `tb_postagem` (`id`),
   CONSTRAINT `fk_categoria_postagem` FOREIGN KEY (`categoria_id`) REFERENCES `tb_categoria` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Adicionando chaves estrangeiras
ALTER TABLE `tb_postagem`
   ADD CONSTRAINT `fk_postagem_item` FOREIGN KEY (`item_id`) REFERENCES `tb_item` (`id`),
   ADD CONSTRAINT `fk_postagem_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `tb_usuario` (`id`);

ALTER TABLE `tb_item`
   ADD CONSTRAINT `fk_item_cidade` FOREIGN KEY (`cidade_id`) REFERENCES `tb_cidade` (`id`),
   ADD CONSTRAINT `fk_item_postagem` FOREIGN KEY (`postagem_id`) REFERENCES `tb_postagem` (`id`);

ALTER TABLE `tb_imagem`
   ADD CONSTRAINT `fk_imagem_item` FOREIGN KEY (`item_id`) REFERENCES `tb_item` (`id`);

```

## Download planilha de Municipios IBGE:

> Link: https://geoftp.ibge.gov.br/organizacao_do_territorio/estrutura_territorial/divisao_territorial/

1. Antes de executar este script, certifique-se de ter feito o download e a conversão do arquivo Excel para .csv.
2. Substitua o caminho correto do arquivo .csv no comando LOAD DATA INFILE.
3. Certifique-se de usar barras invertidas para o caminho do arquivo no sistema Windows, ou barras normais no sistema
   Linux/Mac.

```sql
    LOAD DATA INFILE 'C:/caminho/do/seu/arquivo/cidades.csv'
    INTO TABLE `achados-e-perdidos`.`tb_cidade`
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    IGNORE 1 ROWS
    (@dummy, uf, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, @dummy, nome);
```
