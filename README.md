# Projeto Base de API em Java de Raphael Mendes Stopa

## Rodando o projeto

Este projeto foi desenvolvido, usando uma técnica para desenvolvimento ágil que usa um Docker de Mysql com migrations do liquibase. Para rodar o Docker use o comando abaixo:

```
docker-compose -p “project_raphael” -f src/main/docker/mysql.yml up -d
```

Na primeira inicialização, pode demorar um pouco. Por favor, seja paciente. Dependendo da IDE, pode ser necessário um pequeno ajuste para melhor conexão com o banco de dados. String de conexão: jdbc:mysql://localhost:3306/gft?useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC&createDatabaseIfNotExist=true


## Atenção

Projeto com o Spring Security e JWT. Para pegar seu token, use no endpoint /api/authenticate os seguintes Jsons:

```
Para o gerenciador use:
   {
   "password": "admin",
   "email": "admin@localhost"
   }


Para o para o usuário comum use:
   {
   "password": "user",
   "email": "user@localhost"
   }
  
```
Ainda é possível enviar o campo "rememberMe": true.

Os dados estarão automaticamente no banco de dados graças ao liquibase.

## OpenAPI/Swagger
Este projeto possui uma documentação feita com OpenAPI. Para vê-la acesse: http://localhost:8080/swagger-ui/index.html

## Outras features
* Querys com type safety usando QueryDsl.
* Mappers com mapstruct.
* Erros expostos com zalando.
* !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
* Falta caches e validacoes automaticas do userFalta caches e validacoes automaticas do userFalta caches e validacoes automaticas do userFalta caches e validacoes automaticas do userFalta caches e validacoes automaticas do userFalta caches e validacoes automaticas do userFalta caches e validacoes automaticas do userFalta caches e validacoes automaticas do userFalta caches e validacoes automaticas do user

ARRUMAR SWAGGER, ELE NAO TEM A OPCAO DE AUTENTICAR. fALTA OS CADIADINHOAS AI A REQUISISAO DA SEMPRE NEGADO AAAAAAAAA