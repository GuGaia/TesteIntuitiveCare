# Projeto FastAPI + Vue.js - Busca de Operadoras ANS

Este projeto disponibiliza uma API e uma interface web para buscar operadoras de plano de saúde registradas na ANS.

- **Backend**: FastAPI (Python)
- **Frontend**: Vue.js
- **Base de dados**: CSV de Operadoras Ativas da ANS

## Tecnologias Utilizadas

- Python 3.11
- FastAPI
- Pandas
- Uvicorn
- Vue.js 3
- Axios

## Estrutura de Pastas

/Python_Server /backend main.py requirements.txt /arquivos operadoras.csv /frontend (Vue.js app gerado pelo CLI)

## Como Rodar Localmente

### 1. Clonar o projeto

- git clone https://github.com/seu-usuario/operadoras-projeto.git
### 2. Backend
- cd Python_Server
- cd backend
- pip install -r requirements.txt
- python -m uvicorn main:app --reload --host 0.0.0.0 --port 8000
- O backend estará disponível em: http://localhost:8000

### 3. Frontend (Vue.js)
- cd ../frontend
- npm install
- npm run serve
- O frontend estará disponível em: http://localhost:8080
