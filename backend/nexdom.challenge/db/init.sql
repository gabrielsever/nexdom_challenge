CREATE TABLE produto(
	cod_produto VARCHAR(60) PRIMARY KEY,
	dsc_produto VARCHAR(100) NOT NULL,
	tipo_produto VARCHAR(2) NOT NULL,
	vlr_fornecedor DECIMAL(17,2) NOT NULL,
	vlr_venda DECIMAL(17,2) NOT NULL,
	qtd_estoque numeric(9)
	);
	
CREATE TABLE movto_estoque(
	id SERIAL PRIMARY KEY,
	cod_produto VARCHAR(60) NOT NULL,
	tipo_movto CHAR NOT NULL,
	vlr_total DECIMAL(17,2) NOT NULL,
	qtd_movto numeric(9) NOT NULL,
	dt_movto DATE NOT NULL
);