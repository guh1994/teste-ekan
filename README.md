# TESTE EKAN

### Requisitos

1. JDK 19
2.  Maven 3

### Rodando

1. Clone o projeto: `https://github.com/guh1994/teste-ekan`
2. Entre na pasta `teste-ekan` e execute: `mvn spring-boot:run`
3. Acesse: `http://localhost:8080/login`
4. Envie um POST com o seguinte body 
   ```json
      {
        "login":"user",
        "password": "123456"
      }
5. Salve o token gerado.
6. Para cada requisição envie no headers o seguinte
7. Key = Authorization
8. Value = Bearer + Token
    (Exemplo: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEkgRWthbiIsInN1YiI6InVzZXIiLCJleHAiOjE3MjA5MzI3NjJ9.Ge5ei5qt3Iupk8IruAwpmw7aFGXNkW2BfjVQEoweXWU)


### Requisições
1. Envie um GET para `http://localhost:8080/beneficiary` para encontrar todos os benenficiarios.
2. Envie um GET para `http://localhost:8080/{id}` para encontrar o benificiario pelo id.
3. Envie um GET para `http://localhost:8080/document/{id}` para encontrar todos documentos de um beneficiario.
4. Envie um POST para `http://localhost:8080/beneficiary` para criar um beneficiario (Necessita de um body em json no formato listado no item 7).
5. Envie um PUT para `http://localhost:8080/beneficiary` para atualizar um beneficiario (Necessita de um body em json no formato listado no item 9). 
6. Envie um DELETE para `http://localhost:8080/{id}`para deleter um beneficiario.
8. Formato Json para Criar Customer
Envie o json no seguinte formato.
      ```json 
            {
    "id": 1,
    "name": "Roberval Coutinho dos Santos",
    "phone": "11988776655",
    "bornDate":"1985-05-04",
    "document":[
        {
        "documentType":"Curriculo",
        "description": "Curriculo"
        },
        {
        "documentType":"Catalago",
        "description": "Catalago"
        }
    ]
    }
9. Formato Json para Atualizar
Envie o json no seguinte formato.
      ```json
      {
    "id": 1,
    "name": "Roberto Coutinho dos Santos",
    "phone": "11988776655",
    "bornDate":"1985-05-04",
    "document":[
        {
        "documentType":"Curriculo",
        "description": "Curriculo"
        },
        {
        "documentType":"Catalago",
        "description": "Catalago"
        },
        {
        "documentType":"Revista",
        "description": "Revista"
        }
    ]
    }
   
