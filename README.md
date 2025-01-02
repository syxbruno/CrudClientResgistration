## API de Clientes - CRUD

### Descrição

Este projeto implementa uma API REST para gerenciamento de **clientes**. A API permite realizar operações CRUD (Create, Read, Update, Delete) para cadastrar, buscar, atualizar e excluir informações de clientes em um banco de dados.

### Tecnologias Utilizadas

- **Backend**: Java 21, Spring Boot
- **Banco de Dados**: MySQL
- **ORM**: Spring Data JPA
- **Validação**: Spring Validation
- **Ferramentas**: Docker, Maven, Lombok

### Funcionalidades

A API possui os seguintes endpoints para realizar operações CRUD:

#### 1. **Buscar Cliente por id**
- **Endpoint**: `/client/{id}`
- **Método HTTP**: `GET`
- **Descrição**: Busca e retorna um cliente já existente no banco de dados.

##### Retorno:
```json
{
    "id": "Long",
    "name": "String",
    "email": "String"
}

```

#### 2. **Cadastrar Cliente**
- **Endpoint**: `/client/save`
- **Método HTTP**: `POST`
- **Descrição**: Cadastra um novo cliente no sistema.

##### Exemplo de Requisição:
```json
{
    "name": "String",
    "cpf": "String",
    "password": "String",
    "email": "String",
    "phone": "String no formato (XX) XXXXX-XXXX",
    "dateBirth": "LocalDate no formato dd/MM/yyyy"
}

```

#### 3. **Deletar Cliente**
- **Endpoint**: `/client/delete`
- **Método HTTP**: `DELETE`
- **Descrição**: Exclui um cliente já existente no sistema.

##### Exemplo de Requisição:
```json
{
    "id": "Long",
    "name": "String",
    "email": "String"
}

```
