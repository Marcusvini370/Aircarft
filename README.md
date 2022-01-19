<h1 align="center"> Projeto Gestão de Aeronaves </h1>
<p align="center">Sistema de Gestão de Aeronaves com crud e algumas regras de négocio, api foi feita com spring no backend,
e no frontend foi usado o framework angular na versão 13, o projeto tem tratamento de exceções, dto, paginação, documentação com swagger entre outros.</p>

<br>

## 🔖 Tela Principal / Link de acesso 

Você pode visualizar o layout do projeto através desse link https://marcus-aircraft.netlify.app/ , é necessário esperar alguns segundos para que o banco de dados carregue.

![image](https://user-images.githubusercontent.com/51136557/150211039-0403e550-9ed1-47ff-8322-7e315077a782.png)

<br>

## Tela de Cadastro de Aeronvave
![image](https://user-images.githubusercontent.com/51136557/150211100-a32abb7a-525c-4b81-b62a-a600f9583dc2.png)

<br>

## Endpoints do projeto documentados no Swagger com Springfox!

A documentação da api pode ser acessada pelo seguinte site: https://marcus-aircraft.herokuapp.com/aircraft/swagger-ui.html .

![image](https://user-images.githubusercontent.com/51136557/150211245-5ed31969-ec45-47cd-9768-ad0e260650aa.png)

<br>

## 🚀 Tecnologias
- Spring Rest
- jdk 11
- Angular 13
- PostgreSQL
- JPA
- Bootstrap / ng-bootstrap
- ngx-pagination
- Swagger
- ModelMapper

<br>

## 💻 Instalção do projeto

#### O primeiro passo é fazer o clone do projeto no seu ambiente local:

```bash
git clone https://github.com/Marcusvini370/Aircarft.git
```

#### Backend

Depois que o projeto foi clonado no seu ambiente, importe o projeto back-end para sua ide como o intelij, sts ou outra, instale as dependências dele,
após isso é necessário criar um banco de dados postgreSQL com o nome aircraft após isso é só rodar ele na ide que estará funcionando.

<strong>Obs:</strong> o nome do banco de dados pode ser alterado no arquivo application.properties para o de prefeência se preferir.

#### Frontend

Abra o projeto front-end no visual studio ou no de prefeência e entre na pasta do angular com o terminal para rodar o comando:

```bash
npm install
```
 
Para iniciar o angular digite o seguinte comando no terminal:

```bash
npm serve
```

<strong>Obs:</Strong> para rodar o frontend com o backend localmente, a baseServidor encontrada no arquivo
app-constants.rs na pasta app do angular precisa ser a do seu servidor local como o seguinte exemplo : http://localhost:8080/ . 


