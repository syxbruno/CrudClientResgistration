## API de Clientes - CRUD

### Descrição

Este projeto implementa uma API REST para gerenciamento de **clientes**. A API permite realizar operações CRUD (Create, Read, Update, Delete) para cadastrar, buscar, atualizar e excluir informações de clientes em um banco de dados.

### Tecnologias Utilizadas

Backend: Java 21, Spring Boot
Banco de Dados: MySQL (produção), H2 (testes)
ORM: Spring Data JPA
Validação: Spring Validation
Testes: Spring Test (JUnit)
Ferramentas: Docker, Maven, Lombok
IDE: IntelliJ

### Funcionalidades

A API possui os seguintes endpoints para realizar operações CRUD:

#### 1. **Buscar por todos os clientes cadastrados**
- **Endpoint**: `/client/find`
- **Método HTTP**: `GET`
- **Descrição**: Busca e retorna todos os clientes existente no banco de dados.

---

#### 2. **Buscar Cliente por id**
- **Endpoint**: `/client/find/{id}`
- **Método HTTP**: `GET`
- **Descrição**: Busca e retorna um cliente já existente no banco de dados baseado no id passado na url.

##### Retorno:
```json
{
    "id": "Long",
    "name": "String",
    "email": "String"
}

```
---

#### 3. **Cadastrar Cliente**
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
---

#### 4. **Deletar Cliente**
- **Endpoint**: `/client/update/{id}`
- **Método HTTP**: `PUT`
- **Descrição**: Atualiza dados de um cliente já cadastrado com base no id passado na url e atualiza de acordo com o json passado.

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
---

#### 5. **Deletar Cliente**
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
