# Microserviço de Usuários
[![Coverage](http://leozvasconcellos-sonarqube.eastus.azurecontainer.io:9000/api/project_badges/measure?project=LeonardoZV_backend-challenge&metric=coverage&token=8955df9992b8458c5a2e11756467f1f836e17218)](http://leozvasconcellos-sonarqube.eastus.azurecontainer.io:9000/dashboard?id=LeonardoZV_backend-challenge)
[![Quality Gate Status](http://leozvasconcellos-sonarqube.eastus.azurecontainer.io:9000/api/project_badges/measure?project=LeonardoZV_backend-challenge&metric=alert_status&token=8955df9992b8458c5a2e11756467f1f836e17218)](http://leozvasconcellos-sonarqube.eastus.azurecontainer.io:9000/dashboard?id=LeonardoZV_backend-challenge)

Este microserviço é responsável pelas requisições ao domínio de Usuários.

Nesta versão 1.0, só está disponível o endpoint de validação de senha.

# Pré-Requisitos

A aplicação precisa dos seguintes softwares para executar:

- Java 11
- Maven 3

# Compilando

Primeiro, realize a instalação das dependencias da aplicação via Maven com o comando:

```bash
mvn clean install 
```

Em seguida, inicie o programa através da classe de inicialização `BackendChallengeApplication`

# Endpoint de Validação de Senha

Este endpoint tem como objetivo permitir a validação das senhas de acordo com as regras abaixo:

- Nove ou mais caracteres
- Ao menos 1 dígito
- Ao menos 1 letra minúscula
- Ao menos 1 letra maiúscula
- Ao menos 1 caractere especial
    - Considere como especial os seguintes caracteres: !@#$%^&*()-+
- Não possuir caracteres repetidos dentro do conjunto
- Espaços em branco não devem ser considerados como caracteres válidos.

URL de acesso à API:

```bash
http://localhost:8080/user/password/validate
```

Exemplo de requisição:

```
{
  "password" : "AbTp9!fok"
}
```

Exemplo de resposta:

```
{
    "errors": [
        {
            "code": 400,
            "type": "password-validation-002",
            "message": "Campo password deve conter nove ou mais caracteres."
        }
    ]
}
```

Exemplo de envio da requisição utilizando o utilitário curl.

```bash
curl -X POST "http://localhost:8080/user/password/validate" -H "accept: application/json" -H "Content-Type: application/json" -d "{\"password\": \"AbTp9!fok\"}"
```

Também pode ser utilizada a interface do Swagger para teste dos endpoints e consulta da documentação:

```bash
http://localhost:8080/swagger-ui.html
```

# Racional da Solução

A idéia foi não apenas criar um microserviço para o domínio da senha e sim um microserviço para o domínio de usuários, contendo o endpoint para alteração de senha.

Abandonei o requisito de retornar um booleano por entender que a melhor experiência para o usuário seria de retornarmos no detalhe quais as regras não foram atendidas, como muitas outras aplicações já fazem.

O microserviço foi estruturado da seguinte maneira:

### Config

`SwaggerConfiguration` responsável por conter as configurações do Swagger.

### Controllers

`UserController` é responsável por controlar os endpoints da aplicação.

Caso a senha não contenha erros de validação, a aplicação retorna `OK`.

Caso a senha contenha erros de validação, a aplicação retorna `BAD REQUEST` mais a lista de todos os erros, como no exemplo:

```
{
    "errors": [
        {
            "code": 400,
            "type": "password-validation-002",
            "message": "Campo password deve conter nove ou mais caracteres."
        },
        {
            "code": 400,
            "type": "password-validation-003",
            "message": "Campo password deve conter ao menos um dígito."
        },
        {
            "code": 400,
            "type": "password-validation-005",
            "message": "Campo password deve conter ao menos uma letra maiúscula."
        },
        {
            "code": 400,
            "type": "password-validation-006",
            "message": "Campo password deve conter ao menos um caractere especial."
        },
        {
            "code": 400,
            "type": "password-validation-008",
            "message": "Campo password não deve conter caracteres repetidos."
        }
    ]
}
```

Caso ocorra algum erro interno não tratado, a aplicação retorna `INTERNAL SERVER ERROR`.

`ExceptionController` é responsável por centralizer o tratamento de exceções customizadas da aplicação, sendo nesta versão apenas `InvalidPasswordException`.

### Services

`UserService` é a interface responsável pelas regras de negócio do domínio de Usuários.

`UserServiceImpl` é uma implementação da interface do serviço `UserSerive` utilizando as regras especificadas no desafio.

Optei pela utilização de expressões regulares para realizar as validações pela sua simplicidade em identificar padrões.

### Models

`ValidatePasswordRequest` representa o modelo de requisição da API, contendo a senha.

`ApiErrorResponse` representa o modelo de resposta da API em caso de erro, contendo uma lista de `Erro`.

`Error` representa o modelo dos erros, contendo o status code do erro, tipo do erro e mensagem do erro.

### Exceptions

`InvalidPasswordException` representa uma exceção à ser disparada quando houverem erros de validação de senha.
