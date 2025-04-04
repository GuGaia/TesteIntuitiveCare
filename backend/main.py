from fastapi import FastAPI, Query
from fastapi.responses import JSONResponse
import pandas as pd
from fastapi.middleware.cors import CORSMiddleware

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

operadoras = pd.read_csv('./files/operadoras.csv', sep=';', encoding='utf-8')

# Padroniza nome de colunas
operadoras.columns = operadoras.columns.str.strip().str.lower().str.replace(' ', '_')

@app.get("/buscar-operadora")
async def buscar_operadora(q: str = Query(..., description="Texto para busca na razão social")):
    try:
        resultados = operadoras[
            operadoras['razao_social'].fillna("").str.contains(q, case=False, na=False)
        ]
        registros = resultados[['registro_ans', 'razao_social']].to_dict(orient="records")
        return JSONResponse(content={"resultado": registros})
    except Exception as e:
        return JSONResponse(content={"erro": str(e)}, status_code=500)
