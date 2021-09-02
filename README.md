# Aulão Problema N+1 queries com Spring Data JPA

[![Image](https://img.youtube.com/vi/sqbqoR-lMf8/mqdefault.jpg "Vídeo no Youtube")](https://youtu.be/sqbqoR-lMf8)

#### Testes SQL

```
SELECT * FROM tb_product LIMIT 0,5

SELECT * FROM tb_product LIMIT 5,5

SELECT * FROM tb_product 
	INNER JOIN tb_product_category ON tb_product.id = tb_product_category.product_id
	INNER JOIN tb_category ON tb_category.id = tb_product_category.category_id

SELECT * FROM tb_product 
	INNER JOIN tb_product_category ON tb_product.id = tb_product_category.product_id
	INNER JOIN tb_category ON tb_category.id = tb_product_category.category_id
	LIMIT 0,5

SELECT * FROM tb_product WHERE id IN (1,2,3,4,5)

SELECT * FROM tb_product 
	INNER JOIN tb_product_category ON tb_product.id = tb_product_category.product_id
	INNER JOIN tb_category ON tb_category.id = tb_product_category.category_id
	WHERE tb_product.id IN (1,2,3,4,5)
```  
 

Resolução do problema comentada nas classes mofificadas.

Dicas

# SELECT nos produtos (uso da clausula limit para limitar a quantidade para o retorno)

#Problema das N+1 consultas (para buscar n registros é preciso ir ao banco uma vez para buscar as linhas e depois ir mais N vezes para buscar seus relacionamentos.

#A classe Product é uma entidade e é gerenciada pelo JPA, enquanto o JPA não for fechado, os objetos do tipo product ficarão com uma sessão aberta com o banco. Se no meio do processo for necessário mais algum dado, a JPA faz a busca no banco.

#Com o uso do DTO você torna o objeto independente do banco de dados e de futuras transações. 

#Todos os atributos de uma Entidade devem ser transformados em DTO

#Fluxo de packages:
-entities
-repositories
-dto
-services
-controllers(implementação dos resources) ou resources(representa um objeto com dados e podem se relacionar com outros resources)
