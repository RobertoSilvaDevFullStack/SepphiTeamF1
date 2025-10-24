# 🚀 GUIA DE INÍCIO RÁPIDO - Sepphi Team F1

## ⚡ Setup em 3 Passos

### Passo 1: Compilar o Projeto
```bash
scripts/test_all.bat
```
Isso irá:
- ✅ Verificar Java
- ✅ Compilar todo o código
- ✅ Testar conexão com banco de dados

### Passo 2: Executar a Aplicação
```bash
scripts/run_app.bat
```

Ou diretamente via PowerShell:
```powershell
powershell -ExecutionPolicy Bypass -File "scripts/run_app.ps1"
```

### Passo 3: Popular o Banco (Primeira Vez)
Na primeira execução, o programa perguntará:
```
🤔 É a primeira vez que você executa este programa?
   Deseja popular o banco agora? (s/n): s
```

Responda **`s`** para popular com:
- 5 Equipes F1 completas
- 10 Pilotos principais + Reservas
- 20 Engenheiros especializados
- 10 Carros de corrida
- 24 Corridas do calendário 2025

---

## 📊 Estrutura de Pastas

```
SepphiTeamF1-main/
│
├── 📄 README.md                 ← Você está aqui!
├── 📄 GETTING_STARTED.md
│
├── 📁 src/                      ← Código-fonte
│   ├── Main/
│   ├── Models/
│   ├── Data/
│   ├── TemporadaF1/
│   └── Utils/
│
├── 📁 bin/                      ← Classes compiladas
│
├── 📁 lib/                      ← Bibliotecas
│   └── postgresql-42.7.1.jar
│
├── 📁 scripts/                  ← Scripts de execução
│   ├── run.bat
│   ├── run_app.bat
│   ├── test_all.bat
│   ├── test_connection.bat
│   ├── simulate_race.bat
│   └── download_driver.bat
│
├── 📁 database/                 ← Scripts SQL
│   ├── database_schema.sql
│   └── clean_database.sql
│
└── 📁 docs/                     ← Documentação
    ├── CHECKLIST.md
    ├── DATABASE_GUIDE.md
    ├── QUICKSTART.md
    └── IMPLEMENTATION_SUMMARY.md
```

---

## 🎮 Executar Simulações

### Simular Uma Corrida
```bash
scripts/simulate_race.bat
```
Executa automaticamente:
1. Carrega dados do banco
2. Mostra lista de corridas
3. Simula GP da Arabia Saudita
4. Exibe classificação final
5. Persiste dados no banco

### Testar Conexão com Banco
```bash
scripts/test_connection.bat
```
Verifica:
- ✅ Java instalado
- ✅ Driver PostgreSQL
- ✅ Conexão com banco
- ✅ Tabelas criadas
- ✅ Dados carregados

### Compilar e Testar Tudo
```bash
scripts/test_all.bat
```

---

## 🔧 Configurar Banco de Dados

### Credenciais Padrão
```java
Host:     dpg-d3rcb58gjchc73cpjjdg-a.oregon-postgres.render.com
Database: bdf1
User:     bdf1
Password: fYQe1oWVq7RkbtnA9qKMQP5ZI8AfI9yr
```

### Trocar Credenciais
Edite `src/Main/Main.java`:
```java
DatabaseManager dbManager = new DatabaseManager(
    "seu-host-aqui",
    "seu-database",
    "seu-usuario",
    "sua-senha"
);
```

---

## 🐛 Troubleshooting

### ❌ "Java não encontrado"
**Solução:** Instale Java 25+
- Download: https://www.oracle.com/java/technologies/downloads/
- Verifique: `java -version`

### ❌ "Driver PostgreSQL não encontrado"
**Solução:** Execute o script de download
```bash
scripts/download_driver.bat
```

### ❌ "Erro ao conectar ao banco"
**Verificar:**
1. Conexão com Internet ✓
2. Credenciais corretas ✓
3. Banco online: https://render.com/ ✓

Teste com:
```bash
scripts/test_connection.bat
```

### ❌ "Caracteres corrompidos na saída"
**Solução:** Já está corrigido! Usamos símbolos ASCII.

Se persistir, execute com UTF-8:
```bash
java -Dfile.encoding=UTF-8 -cp "bin;lib\postgresql-42.7.1.jar" Main.Main
```

---

## 📚 Próximos Passos

1. **Explorar o Menu:**
   - Simule corridas diferentes
   - Teste as 10 opções do menu
   - Veja como os dados são salvos

2. **Consultar Documentação:**
   - `docs/DATABASE_GUIDE.md` - Guia completo do banco
   - `docs/CHECKLIST.md` - Implementação passo-a-passo

3. **Entender o Código:**
   - Veja `src/Main/Main.java` - Entrada principal
   - Explore `src/Data/` - DAOs (Data Access)
   - Estude `src/TemporadaF1/SimulacaoCorrida.java` - Lógica

---

## 🎯 Menu Principal Explicado

```
[F1] >>> SIMULACAO TEMPORADA F1 2025 <<<
==================================================

[*] 1 - Melhorar carros (engenheiros trabalhando)
   └─ Use os engenheiros para aumentar performance

[+] 2 - Criar nova corrida
   └─ Crie uma corrida customizada

[!] 3 - Simular corrida
   └─ Escolha qual corrida simular

[T] 4 - Ver classificacao de equipes
   └─ Veja pontos de cada equipe

[P] 5 - Ver classificacao de pilotos
   └─ Veja pontos de cada piloto

[=] 6 - Ver detalhes de uma equipe
   └─ Informações completas da equipe

[C] 7 - Simular temporada completa (24 corridas)
   └─ Execute todas as corridas em sequência

[S] 8 - Substituir piloto manualmente
   └─ Troque um piloto com registro de motivo

[X] 9 - Limpar lista de corridas
   └─ Reseta o calendário

[0] 0 - Sair
   └─ Encerra a aplicação
```

---

## 📊 Exemplo de Execução

```
╔════════════════════════════════════════════════════════════╗
║       [CORRIDA] SEPPHI TEAM F1 - SIMULADOR DE TEMPORADA 2025
║                    Versão 2.0 (Com Banco de Dados)          
╚════════════════════════════════════════════════════════════╝

[*] Conectando ao banco de dados...
[OK] Conexão com banco de dados estabelecida com sucesso!

[*] Carregando dados do banco de dados...
[+] 5 equipes carregadas do banco!
[+] 24 corridas carregadas do banco!

[+] 5 equipes carregadas do banco!
[+] 24 corridas carregadas do banco!

==================================================
[F1] >>> SIMULACAO TEMPORADA F1 2025 <<<
==================================================
[*] 1 - Melhorar carros (engenheiros trabalhando)
[+] 2 - Criar nova corrida
[!] 3 - Simular corrida
...

Escolha uma opcao: 3

[!] Corridas disponíveis:
   0 - [FLAG] GP do Bahrein
   1 - [FLAG] GP da Arabia Saudita
   ...

Escolha o número da corrida: 1

==================================================
[!] >>> INICIANDO CORRIDA <<<
==================================================

>>> Corrida: GP da Arabia Saudita
>>> Total de carros: 10

>>> RESULTADOS DA CORRIDA <<<
==================================================
1 - #81 Oscar Piastri  Handicap: 20  Eficiencia: 176,80
2 - #63 George Russell  Handicap: 19  Eficiencia: 170,26
3 - #18 Lance Stroll  Handicap: 16  Eficiencia: 144,77
...

[OK] Corrida finalizada!
```

---

## 💡 Dicas Úteis

- 💾 **Dados Persistem:** Todos os dados são salvos no banco automaticamente
- 🔄 **Simule Múltiplas Vezes:** Você pode simular a mesma corrida várias vezes
- 📊 **Acompanhe Mudanças:** Veja como a classificação muda após cada corrida
- 🎯 **Teste Estratégias:** Melhore carros e mude pilotos para testar diferentes cenários
- ⚠️ **Alertas:** Fique atento aos alertas de substituição de pilotos

---

## 🆘 Precisa de Ajuda?

1. **Leia a documentação:**
   - `docs/QUICKSTART.md` - Início rápido
   - `docs/DATABASE_GUIDE.md` - Banco de dados
   - `docs/CHECKLIST.md` - Checklist completo

2. **Verifique erros comuns:**
   - Execute `scripts/test_all.bat` para diagnóstico

3. **Reporte problemas:**
   - Descreva o erro
   - Inclua a saída do terminal
   - Mencione seu SO (Windows/Linux/Mac)

---

## 🎉 Pronto!

Você está pronto para simular uma temporada completa de Fórmula 1!

**Comece agora:**
```bash
scripts/run_app.bat
```

Divirta-se! 🏁

