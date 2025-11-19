# 📘 Mentor AI -- Plataforma de Requalificação Profissional (MVP)

O **Mentor AI** é uma plataforma de requalificação profissional voltada
para facilitar o retorno e a recolocação de pessoas no mercado de
trabalho.\
O sistema oferece cadastro de usuários, análise
de perfis e uso de **IA (Groq + Spring AI)** para auxiliar na
recomendação de carreira.

------------------------------------------------------------------------

## 👨‍💻 Integrantes da Equipe

-   **Rafael Macoto**
-   **Fernando Henrique Aguiar**
-   **Gabrielly Macedo**

------------------------------------------------------------------------

## 🚀 Tecnologias Utilizadas

### **Backend**

-   Java 21\
-   Spring Boot 3\
-   Spring Web\
-   Spring Data JPA\
-   Spring Security\
-   Spring Validation\
-   Spring Cache\
-   Spring AMQP (RabbitMQ)\
-   Spring AI com **Groq API**\
-   Oracle Database (ojdbc11)\
-   Lombok

### **Infra**

-   RabbitMQ\
-   SMTP (Gmail) para envio de emails

------------------------------------------------------------------------

## 🤖 IA Integrada (Groq + Spring AI)

A plataforma utiliza o modelo **Llama 3.1 8B** da Groq para gerar
recomendações de carreira personalizadas.\
A IA recebe objetivos profissionais e habilidades do usuário e retorna
um plano estruturado de desenvolvimento.

------------------------------------------------------------------------

## 📂 Estrutura do Projeto

    src/
     └── main/
         ├── java/com/mentorai/
         │   ├── controller/
         │   ├── service/
         │   ├── repository/
         │   ├── model/
         │   └── config/
         |   └── dto/
         |   └── messaging/
         |   └── mapper/
         |   └── exception/
         └── resources/
             ├── application.properties
             └── static/

------------------------------------------------------------------------

## ⚙️ Principais Funcionalidades

✔ Cadastro e autenticação de usuários\
✔ Criação de um plano próprio\
✔ Envio de emails transacionais (SMTP Gmail)\
✔ Processamento assíncrono com RabbitMQ\
✔ Geração de recomendações de carreira via IA\
✔ Persistência em Oracle Database\
✔ Estrutura preparada para escalabilidade

------------------------------------------------------------------------

## 🧠 IA -- Exemplo de Requisição

A IA recebe algo assim:

    Objetivo: Conseguir minha primeira vaga em desenvolvimento backend
    Skills: Java, Spring Boot, MySQL

E retorna:

-   Recomendações de estudo (iniciante → avançado)\
-   Lista de tarefas práticas (5 a 10 itens)

------------------------------------------------------------------------

## 🧪 Rodando os Testes

### Executar todos os testes:

``` bash
./mvnw test
```

### Executar testes com relatório:

``` bash
./mvnw test surefire-report:report
```

### Executar testes individuais:

``` bash
./mvnw -Dtest=NomeDaClasseDeTeste test
```

------------------------------------------------------------------------

## 🛠 Como rodar o projeto

### Certifique-se de subir o RabbitMQ antes de rodar a aplicação executando:
`docker compose up -d`

### 1. Clone o repositório

``` bash
git clone https://github.com/RafaMacoto/mentor-ai.git
```

### 2. Configure o `application.properties`

-   API Key da Groq\
-   SMTP\

### 3. Rode a aplicação

``` bash
./mvnw spring-boot:run
```

------------------------------------------------------------------------

## 📬 Contato

Para dúvidas ou melhorias, procure qualquer integrante da equipe.
