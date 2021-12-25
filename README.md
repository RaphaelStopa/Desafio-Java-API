# Projeto de API para desafio GFT Starter em Java de Raphael Mendes Stopa

## Rodando o projeto

Este projeto foi desenvolvido, usando uma técnica para desenvolvimento ágil que usa um Docker de Mysql com migrations do liquibase. Para rodar o Docker use o comando abaixo:

```
docker-compose -p “project_raphael” -f src/main/docker/mysql.yml up -d
```

Na primeira inicialização, pode demorar um pouco. Por favor, seja paciente. Dependendo da IDE, pode ser necessário um pequeno ajuste para melhor conexão com o banco de dados. String de conexão: jdbc:mysql://localhost:3306/gft?useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC&createDatabaseIfNotExist=true


## Atenção

Projeto com o Spring Security e JWT. Para pegar seu token, use no endpoint /api/authenticate os seguintes JSons:

```
Para o gerenciador use:
   {
   "password": "Gft2021",
   "user": "Admin"
   }
  

Para o para o usuário comum use:
   {
   "password": "user",
   "user": "User"
   }
  
```
Ainda é possível enviar o campo "rememberMe": true.

Os dados estarão automaticamente no banco de dados graças ao liquibase.


## Documentação
Possui duvidas em relação a minha abordagem quanto a este projeto? Para isto, gerei uma documentação com Ascii que se encontra dentro das pastas do projeto.
```
src/docs/asciidocs
```

## Tarefas pedidas.
- [X] Popular o banco (acontece já na inicialização).
- [x] Ter dois perfis de acesso, um user e outro admin.
- [x] Ter duas APIs, uma (para o user) consumidora.
- [x] CRUD de todas as entidades para o Admin.
- [x] O usuário comum (por meio de sua API) pode apenas ver os dados não sensíveis.


## Tarefas excedentes realizadas.
- [X] Validação de CPF (automática via @CPF).
- [X] As rotas pedidas como excedentes, eu as juntei em grande parte, pois não há necessidade delas serem separadas. Usei o pageable e predicate para o consumidor desta api possa fazer as buscas que ele mesmo julgar necessárias, inclusive as pedidas. Isto é mais coerente e o Clécio no dia 20/12 permitiu tal uso. Segue abaixo cada uma delas e como são chamadas.

/api/{cargos}/asc Listar os Candidatos em ordem alfabética crescente por nome
```
/politicians/users?electivePositionType=CARGO&sort=name,desc
```
/api/{cargos}/desc Listar os Candidato em ordem alfabética decrescente por nome
```
/politicians/users?electivePositionType=CARGO&sort=name,asc
```
/api/{cargos}/{id} Buscar Candidato por id
```
/politicians/users?electivePositionType=CARGO&id={id}
```
/api/{cargos}/leis/{qnt}Filtrar pelo numero de Projetos de leis que o candidato trabalhou
```
/politicians/users/{numberOfLaws}?electivePositionType=CARGO
```

## Outras features
* Querys com type safety usando QueryDsl.
* Mappers com mapstruct.
* Erros expostos com zalando.