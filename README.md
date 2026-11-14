# Copa do Mundo 2026 — app Android

Aplicativo Android para acompanhar e simular a Copa do Mundo de 2026: grupos, jogos, classificação e mata-mata, com os dados guardados no aparelho em SQLite.

Trabalho "App Tema Livre 1" da disciplina **Programação para Dispositivos Móveis II** (ADS, UniSALESIANO Araçatuba), entregue em abril de 2026. O enunciado pedia tema livre com pelo menos 3 tabelas e um relacionamento 1-para-n.

## Funcionalidades

- Lista de grupos e seleções
- Jogos por fase
- Classificação dos grupos
- Mata-mata
- Simulações de resultados salvas no banco

## Stack

- Java
- Android SDK (minSdk 29, targetSdk 36), AppCompat, Material e ConstraintLayout
- SQLite com `SQLiteOpenHelper` e classes DAO
- RecyclerView com adapters próprios
- Gradle com Kotlin DSL

## Banco de dados

Seis tabelas: `grupos`, `selecoes`, `fases`, `jogos`, `simulacoes` e `simulacao_jogos`. O `DBHelper` cria as tabelas e insere os dados iniciais.

## Como rodar

1. Abra a pasta no Android Studio.
2. Aguarde a sincronização do Gradle.
3. Execute em um emulador ou aparelho com Android 10 ou mais recente.

## Estrutura

```
app/src/main/java/com/example/copadomundo2026/
  MainActivity, GruposActivity, JogosActivity,
  ClassificacaoActivity, MataMataActivity
  bd/        DBHelper
  dao/       GrupoDAO, SelecaoDAO, JogoDAO, SimulacaoDAO
  modelos/   Grupo, Selecao, Jogo, Simulacao
app/src/main/java/adapter/   adapters das listas
```

## Autor

Fabio Luiz Florêncio da Silva · [github.com/Fabioluiiz22010](https://github.com/Fabioluiiz22010) · [LinkedIn](https://www.linkedin.com/in/fabio-l-florencio/)
