# 📑 ÍNDICE DE NAVEGAÇÃO - Sepphi Team F1

## 🚀 COMECE AQUI

1. **[README.md](README.md)** ← Documentação Principal
   - Visão geral do projeto
   - Tecnologias utilizadas
   - Como começar

2. **[GETTING_STARTED.md](GETTING_STARTED.md)** ← Guia Rápido
   - Setup em 3 passos
   - Exemplos de execução
   - Troubleshooting

3. **[PROJECT_SUMMARY.md](PROJECT_SUMMARY.md)** ← Resumo Técnico
   - Estatísticas do projeto
   - Arquitetura
   - Estrutura de pastas

---

## 📚 DOCUMENTAÇÃO

### Para Usuários
| Arquivo | Descrição |
|---------|-----------|
| [GETTING_STARTED.md](GETTING_STARTED.md) | Como executar o programa |
| [docs/QUICKSTART.md](docs/QUICKSTART.md) | Início rápido |
| [docs/CHECKLIST.md](docs/CHECKLIST.md) | Checklist de implementação |

### Para Desenvolvedores
| Arquivo | Descrição |
|---------|-----------|
| [PROJECT_SUMMARY.md](PROJECT_SUMMARY.md) | Visão técnica do projeto |
| [docs/DATABASE_GUIDE.md](docs/DATABASE_GUIDE.md) | Guia completo do banco |
| [docs/IMPLEMENTATION_SUMMARY.md](docs/IMPLEMENTATION_SUMMARY.md) | Detalhes técnicos |
| [CONTRIBUTING.md](CONTRIBUTING.md) | Como contribuir |

---

## 🗂️ ESTRUTURA DE PASTAS

### Pasta: `/src` (Código-fonte)
```
src/
├── Main/
│   └── Main.java                 ← Entrada principal do programa
├── Models/                       ← Modelos de dados F1
│   ├── Team.java
│   ├── Driver.java
│   ├── Engineer.java
│   ├── Car.java
│   ├── Race.java
│   └── ...
├── Data/                         ← Data Access Objects (DAOs)
│   ├── DatabaseManager.java      ← Gerenciador central
│   ├── TeamDAO.java
│   ├── DriverDAO.java
│   └── ...
├── TemporadaF1/                  ← Lógica de simulação
│   ├── SimulacaoCorrida.java     ← Menu principal
│   └── TemporadaF1.java
└── Utils/                        ← Utilitários
    └── EmojiHelper.java          ← Gerenciador de símbolos
```

### Pasta: `/scripts` (Scripts de Execução)
```
scripts/
├── run.bat                       ← Executar aplicação
├── run_app.bat                   ← Versão alternativa
├── test_all.bat                  ← Compilar e testar
├── test_connection.bat           ← Testar conexão BD
├── simulate_race.bat             ← Simular uma corrida
└── download_driver.bat           ← Baixar driver PostgreSQL
```

### Pasta: `/database` (Scripts SQL)
```
database/
├── database_schema.sql           ← Criar tabelas
└── clean_database.sql            ← Limpar banco
```

### Pasta: `/docs` (Documentação Técnica)
```
docs/
├── CHECKLIST.md                  ← Checklist de implementação
├── DATABASE_GUIDE.md             ← Guia de banco de dados
├── QUICKSTART.md                 ← Início rápido
├── EXECUTIVE_SUMMARY.md          ← Resumo executivo
└── IMPLEMENTATION_SUMMARY.md     ← Resumo técnico
```

### Pasta: `/lib` (Bibliotecas Externas)
```
lib/
└── postgresql-42.7.1.jar         ← Driver JDBC PostgreSQL
```

### Pasta: `/bin` (Classes Compiladas)
```
bin/
├── Models/                       ← 7 arquivos .class
├── Data/                         ← 13 arquivos .class
├── TemporadaF1/                  ← 2 arquivos .class
├── Main/                         ← 1 arquivo .class
└── Utils/                        ← 1 arquivo .class
```

---

## ⚡ COMANDOS RÁPIDOS

### Executar Aplicação
```bash
# Windows
scripts/run_app.bat

# Compilar e executar
scripts/test_all.bat && scripts/run_app.bat
```

### Testar Banco de Dados
```bash
scripts/test_connection.bat
```

### Simular Corrida
```bash
scripts/simulate_race.bat
```

### Compilar Manualmente
```bash
javac -d bin -cp "bin;lib\postgresql-42.7.1.jar" -sourcepath src src/**/*.java
```

### Executar Manualmente
```bash
java -cp "bin;lib\postgresql-42.7.1.jar" Main.Main
```

---

## 📊 ESTRUTURA DE ARQUIVOS NA RAIZ

```
SepphiTeamF1-main/
│
├── 📄 README.md                  ← Documentação principal
├── 📄 GETTING_STARTED.md         ← Guia rápido
├── 📄 PROJECT_SUMMARY.md         ← Resumo técnico
├── 📄 CONTRIBUTING.md            ← Contribuições
├── 📄 INDEX.md                   ← Este arquivo!
├── 📄 LICENSE                    ← MIT License
│
├── 📁 src/                       ← Código-fonte (20+ classes)
├── 📁 bin/                       ← Classes compiladas
├── 📁 lib/                       ← Bibliotecas externas
├── 📁 scripts/                   ← Scripts de execução
├── 📁 database/                  ← Scripts SQL
├── 📁 docs/                      ← Documentação técnica
├── 📁 tests/                     ← Testes (vazio - futuro)
│
├── 📁 .git/                      ← Repositório Git
├── 📄 .gitignore                 ← Git ignore
├── 📄 SepphiTeamF1.iml           ← Config IntelliJ
└── 📄 organize_project.bat       ← Script de organização (pode ser removido)
```

---

## 🎯 FLUXO DE USO

```
1. CLONE/DOWNLOAD
   └─ git clone https://github.com/.../SepphiTeamF1.git

2. COMPILE
   └─ scripts/test_all.bat

3. EXECUTE
   └─ scripts/run_app.bat

4. RESPONDA "s"
   └─ Para popular o banco na primeira execução

5. USE O MENU
   └─ Escolha opções 1-9 para diferentes ações

6. OS DADOS SÃO SALVOS
   └─ Automaticamente no PostgreSQL
```

---

## 🔍 ENCONTRAR O QUE PRECISA

### Preciso executar o programa
→ [GETTING_STARTED.md](GETTING_STARTED.md) - Seção "Executar Simulações"

### Preciso entender a arquitetura
→ [PROJECT_SUMMARY.md](PROJECT_SUMMARY.md) - Seção "🏗️ Arquitetura"

### Preciso configurar o banco
→ [docs/DATABASE_GUIDE.md](docs/DATABASE_GUIDE.md)

### Preciso contribuir com código
→ [CONTRIBUTING.md](CONTRIBUTING.md)

### Tenho um erro
→ [GETTING_STARTED.md](GETTING_STARTED.md) - Seção "🐛 Troubleshooting"

### Quero entender o código
→ Leia [src/](src/) começando por `src/Main/Main.java`

### Quero ver dados de teste
→ Execute `scripts/simulate_race.bat`

---

## 📈 ESTATÍSTICAS

| Métrica | Valor |
|---------|-------|
| Documentos | 8 arquivos |
| Pastas Organizadas | 5 pastas |
| Classes Java | 20+ |
| DAOs | 8 |
| Linhas de Código | ~3.500 |
| Tabelas BD | 12+ |
| Status | ✅ Pronto |

---

## ✨ O QUE FOI REORGANIZADO

### ✅ Antes
```
SepphiTeamF1-main/
├── CHECKLIST.md
├── DATABASE_GUIDE.md
├── EXECUTIVE_SUMMARY.md
├── QUICKSTART.md
├── database_schema.sql
├── run.bat
├── test_all.bat
├── ... (30+ arquivos soltos)
```

### ✅ Depois
```
SepphiTeamF1-main/
├── README.md
├── GETTING_STARTED.md
├── docs/                 ← Documentação
├── scripts/              ← Scripts
├── database/             ← SQL
├── src/                  ← Código
└── bin/                  ← Compilado
```

---

## 🎓 RECOMENDAÇÕES DE LEITURA

### Para Iniciantes
1. [README.md](README.md) - Visão geral
2. [GETTING_STARTED.md](GETTING_STARTED.md) - Como executar
3. [docs/QUICKSTART.md](docs/QUICKSTART.md) - Começar

### Para Desenvolvedores
1. [PROJECT_SUMMARY.md](PROJECT_SUMMARY.md) - Arquitetura
2. [docs/DATABASE_GUIDE.md](docs/DATABASE_GUIDE.md) - Banco
3. [CONTRIBUTING.md](CONTRIBUTING.md) - Código
4. [src/](src/) - Explorar o código

### Para Administradores
1. [docs/CHECKLIST.md](docs/CHECKLIST.md) - Setup completo
2. [database/database_schema.sql](database/database_schema.sql) - Banco
3. [scripts/](scripts/) - Scripts de execução

---

## 🚀 PRÓXIMAS FASES

- 🎨 GUI com JavaFX
- 🌐 API REST com Spring
- 📱 App Mobile
- 📊 Dashboard Web
- 👥 Multiplayer

---

## 📞 REFERÊNCIA RÁPIDA

| Ação | Comando |
|------|---------|
| Executar | `scripts/run_app.bat` |
| Testar | `scripts/test_all.bat` |
| Simular | `scripts/simulate_race.bat` |
| Compilar | `scripts/test_all.bat` |
| Limpar BD | `database/clean_database.sql` |

---

## ✅ CHECKLIST

- [x] Código organizado
- [x] Scripts na pasta `/scripts`
- [x] SQL na pasta `/database`
- [x] Docs na pasta `/docs`
- [x] README atualizado
- [x] Guia de início rápido
- [x] Documentação técnica
- [x] Pronto para uso

---

<div align="center">

### 🎉 Projeto Completamente Organizado!

**Estrutura profissional e pronta para produção**

[⬅️ Voltar ao README](README.md) • [📚 Documentação](docs/) • [🚀 Começar](GETTING_STARTED.md)

</div>

