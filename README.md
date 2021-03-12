### Pré-requisitos 

- Necessário ter o *Maven* instalado    
- Necessário ter o *Docker* instalado    
- Necessário ter o *JAVA 11* instalado    
- Necessário ter o *GIT* instalado  

### Clonar o projeto

- git clone git@github.com:macedo2300/cvc-teste-api.git
- Entrar na pasta "cvc-teste-api/conect-new-broker-api"


Para rodar o projeto localmente com o maven executar o comando:
- `mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8085` - Este comando irá rodar a aplicação pelo maven

Para rodar o projeto localmente com o docker executar o comando:
- `mvn clean package` - Este comando irá limpar e criar o jar da aplicação.
- `docker build -t nomeDaImagem .` - Este comando irá criar uma imagem da aplicação.
- `docker run -p 8080:8080 -e PORT=8080 broker/api` - Este comando irá exrcutar o conteiner da aplicação


[SWAGGER DO PROJETO](http://localhost:8080/swagger-ui.html)

### TODO 




