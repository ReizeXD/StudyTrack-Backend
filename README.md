# 📕Study Track - FrontEnd

*[🔗 Repositório frontend.](https://github.com/ReizeXD/StudyTrack-Frontend.git)*

## 📌 Visão Geral

O **StudyTrack** é uma aplicação web desenvolvida em **Spring Boot** para ajudar usuários a **organizar, monitorar e acompanhar** o progresso de seus estudos de forma simples e eficiente. Ele permite que os usuários **criem metas de estudo**, **registrem o tempo dedicado a cada meta** e **visualizem relatórios de desempenho** para manter a disciplina e a motivação. 

---

## 🛠️ Tecnologias Utilizadas

### 🖥️ Backend
- Java
- Spring Boot 
- Spring Web 
- Spring Email
- Spring Validation 
- Spring Data JPA 
- Hibernate 
- PostgreSQL 
- Lombok 

### 🌐 Frontend
- Angular 
- TypeScript
- MDBootstrap Angular (MDB) 
- RxJS 
- Angular Forms 
---

## 📚 Funcionalidades

### 👤 Autenticação & Usuário
- Registro e login de usuários
- Recuperação de senha por Email.
- Validação com tratamento de erros no backend e frontend
- Proteção de rotas

### 🎯 Metas de Estudo
- Criar, editar e excluir metas
- Definir tempo de estudo semanal e diário
- Status de metas: ativas, concluídas ou expiradas

### ⏱️ Registro de Progresso
- Registro diário do tempo estudado por meta
- Histórico dos registros
- Cálculo de total de horas por meta

### 📊 Dashboard
- Visão geral do progresso:
  - Total de metas criadas
  - Metas ativas
  - Total de horas estudadas
  - Últimos registros
- Interface responsiva e amigável com cards e listas (utilizando MDB)

---

## 🚀 Como Executar

## Back

### Clone o repositório
```bash
git clone https://github.com/ReizeXD/StudyTrack-Backend.git
```
### Acesse o diretório
```bash
cd StudyTrack-Backend
```

### Compile e execute a aplicação
```bash
./mvnw spring-boot:run
```
A aplicação estará disponível em: http://localhost:8080

## Front

Certifique-se de ter o [Node.js](https://nodejs.org/en/) e o [Angular CLI](https://angular.io/cli) instalados.


### Clone o repositório
```bash
git clone https://github.com/seu-usuario/sistema-pesquisa-frontend.git
cd sistema-pesquisa-frontend
```

### Instale as dependências
```bash
npm install
```

###  Executando o projeto localmente
```bash
ng serve
```
Acesse o app em: http://localhost:4200
