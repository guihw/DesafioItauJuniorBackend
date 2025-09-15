# API de Transações - Desafio Itaú

Uma API REST desenvolvida em Spring Boot para gerenciamento de transações financeiras e cálculo de estatísticas em tempo real, de acordo com o desafio Itaú para desenvolvedor back-end júnior.

## 📋 Sobre o Projeto

Esta API permite:
- Receber e armazenar transações financeiras (sem persistência, conforme os requisitos do desafio)
- Calcular estatísticas das transações dos últimos 60 segundos
- Limpar todas as transações armazenadas

Os dados são armazenados em memória, sem uso de banco de dados ou cache externos.

## 🚀 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Web**
- **Bean Validation**
- **Lombok**
- **Maven**

## 🛠️ Como Executar

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6+

### Passos para execução

1. **Clone o repositório**
```bash
git clone <url-do-repositorio>
cd desafioitau
```

2. **Compile o projeto**
```bash
mvn clean compile
```

3. **Execute a aplicação**
```bash
mvn spring-boot:run
```

4. **A API estará disponível em:**
```
http://localhost:8080
```

### Executando os testes
```bash
mvn test
```

## 📚 Documentação da API

### 1. Criar Transação
Recebe uma nova transação financeira.

**Endpoint:** `POST /transacao`

**Body:**
```json
{
    "valor": 123.45,
    "dataHora": "2024-08-07T12:34:56.789-03:00"
}
```

**Responses:**
- `201 Created` - Transação aceita
- `422 Unprocessable Entity` - Transação inválida (valor negativo ou data futura)
- `400 Bad Request` - JSON malformado

**Validações:**
- `valor`: Deve ser maior ou igual a 0
- `dataHora`: Deve estar no passado ou presente (formato ISO 8601)

### 2. Obter Estatísticas
Retorna estatísticas das transações dos últimos 60 segundos.

**Endpoint:** `GET /estatistica`

**Response:** `200 OK`
```json
{
    "count": 10,
    "sum": 1234.56,
    "avg": 123.456,
    "min": 12.34,
    "max": 123.56
}
```

**Campos:**
- `count`: Quantidade de transações
- `sum`: Soma total dos valores
- `avg`: Média dos valores
- `min`: Menor valor
- `max`: Maior valor

*Nota: Se não houver transações nos últimos 60 segundos, todos os valores retornam 0.*

### 3. Limpar Transações
Remove todas as transações armazenadas.

**Endpoint:** `DELETE /transacao`

**Response:** `200 OK` (sem corpo)

## 🏗️ Arquitetura

```
src/
├── main/java/com/guilh/desafioitau/
│   ├── controller/          # Controladores REST
│   │   └── endpoints.java
│   ├── service/            # Lógica de negócio
│   │   └── TransactionService.java
│   ├── model/              # Modelos e repositório
│   │   ├── TransactionModel.java
│   │   └── TransactionStorage.java
│   ├── dto/                # Objetos de transferência
│   │   └── DtoTransaction.java
│   ├── exception/          # Tratamento de exceções
│   │   └── GlobalExceptionHandler.java
│   └── DesafioitauApplication.java
└── test/                   # Testes automatizados
```

## 🧪 Exemplos de Uso

### Enviando uma transação
```bash
curl -X POST http://localhost:8080/transacao \
  -H "Content-Type: application/json" \
  -d '{
    "valor": 100.50,
    "dataHora": "2024-09-15T10:30:00.000-03:00"
  }'
```

### Obtendo estatísticas
```bash
curl -X GET http://localhost:8080/estatistica
```

### Limpando transações
```bash
curl -X DELETE http://localhost:8080/transacao
```

Este projeto foi desenvolvido como parte de um desafio técnico.

---

**Nota**: Esta aplicação foi desenvolvida seguindo os requisitos específicos do desafio técnico, priorizando simplicidade e atendimento aos critérios estabelecidos.
