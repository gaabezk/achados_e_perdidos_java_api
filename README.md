# Spring Boot API - Achados e Perdidos

#### MySQL Script (Executar na ordem correta):

```sql
-- `achados-e-perdidos`.tb_categoria definition
CREATE TABLE `tb_categoria` (
	`id` binary(16) NOT NULL,
	`nome` varchar(255) DEFAULT NULL,
	`descricao` varchar(255) DEFAULT NULL,
	`data_cadastro` datetime(6) DEFAULT NULL,
	`data_atualizacao` datetime(6) DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- `achados-e-perdidos`.tb_cidade definition
CREATE TABLE `tb_cidade` (
	`id` int NOT NULL AUTO_INCREMENT,
	`nome` varchar(255) DEFAULT NULL,
	`uf` varchar(2) DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- `achados-e-perdidos`.tb_item definition
CREATE TABLE `tb_item` (
	`id` binary(16) NOT NULL,
	`titulo` varchar(255) DEFAULT NULL,
	`descricao` varchar(255) DEFAULT NULL,
	`localizacao` varchar(255) DEFAULT NULL,
	`cidade_id` int DEFAULT NULL,
	`data` datetime(6) DEFAULT NULL,
	`status` enum(
		'APPROVED',
		'REFUSED',
		'RETURNED',
		'WAITING_APPROVAL'
	) DEFAULT NULL,
	`tipo` enum('FOUND', 'LOST') DEFAULT NULL,
	`data_cadastro` datetime(6) DEFAULT NULL,
	`data_atualizacao` datetime(6) DEFAULT NULL,
	PRIMARY KEY (`id`),
	KEY `idx_cidade_id` (`cidade_id`),
	CONSTRAINT `fk_item_cidade` FOREIGN KEY (`cidade_id`) REFERENCES `tb_cidade` (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- `achados-e-perdidos`.tb_imagem definition
CREATE TABLE `tb_imagem` (
	`id` binary(16) NOT NULL,
	`item_id` binary(16) DEFAULT NULL,
	`url` varchar(255) DEFAULT NULL,
	PRIMARY KEY (`id`),
	KEY `idx_item_id` (`item_id`),
	CONSTRAINT `fk_imagem_item` FOREIGN KEY (`item_id`) REFERENCES `tb_item` (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- `achados-e-perdidos`.tb_item_categoria definition
CREATE TABLE `tb_item_categoria` (
	`categoria_id` binary(16) NOT NULL,
	`item_id` binary(16) NOT NULL,
	PRIMARY KEY (`categoria_id`, `item_id`),
	KEY `idx_item_id` (`item_id`),
	CONSTRAINT `fk_item_categoria_categoria` FOREIGN KEY (`categoria_id`) REFERENCES `tb_categoria` (`id`),
	CONSTRAINT `fk_item_categoria_item` FOREIGN KEY (`item_id`) REFERENCES `tb_item` (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;
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
