# 📋 Checklist de Implementação - Sistema de Persistência de Dados F1

## ✅ PHASE 1: Preparação (15 minutos)

- [ ] **Backup do Projeto**
  - Fazer backup de `src/` antes de começar
  - Comando: `robocopy src src_backup /E /Y`

- [ ] **Dependências**
  - ✓ PostgreSQL JDBC Driver (já deveria estar no projeto)
  - ✓ Java 8+ (requerido)

- [ ] **Credenciais do Banco**
  - [ ] Host: `dpg-d3rcb58gjchc73cpjjdg-a.oregon-postgres.render.com`
  - [ ] Database: `bdf1`
  - [ ] User: `bdf1`
  - [ ] Password: `fYQe1oWVq7RkbtnA9qKMQP5ZI8AfI9yr`

---

## ✅ PHASE 2: Configuração do Banco (10 minutos)

- [ ] **Acessar Render PostgreSQL**
  - Link: https://render.com/
  - Navegar para PostgreSQL Instance: `dpg-d3rcb58gjchc73cpjjdg-a.oregon-postgres.render.com`
  - Ir para aba "Query"

- [ ] **Executar Script SQL**
  - [ ] Copiar conteúdo de `database_schema.sql`
  - [ ] Colar no Query Editor do Render
  - [ ] Executar (Ctrl + Enter)
  - [ ] Aguardar mensagem de sucesso

- [ ] **Verificar Tabelas Criadas**
  - [ ] Execute: `SELECT * FROM information_schema.tables WHERE table_schema = 'public';`
  - [ ] Verificar se aparecem ~12 tabelas
  - [ ] Prosseguir apenas se tudo estiver OK

---

## ✅ PHASE 3: Copiar Arquivos (5 minutos)

- [ ] **Copiar DAOs para `src/Data/`**
  - [ ] `TeamDAO.java`
  - [ ] `DriverDAO.java`
  - [ ] `EngineerDAO.java`
  - [ ] `CarDAO.java`
  - [ ] `RaceDAO.java`
  - [ ] `RaceResultDAO.java`
  - [ ] `DriverSubstitutionDAO.java`
  - [ ] `ChampionshipStandingDAO.java`

- [ ] **Copiar Classes Gerenciadoras para `src/Data/`**
  - [ ] `DatabaseManager.java`
  - [ ] `DatabaseInitializer.java`

- [ ] **Copiar Exemplos para `src/Data/`**
  - [ ] `UsageExample.java`

- [ ] **Copiar Documentação para raiz do projeto**
  - [ ] `DATABASE_GUIDE.md`
  - [ ] `IMPLEMENTATION_SUMMARY.md`
  - [ ] `QUICKSTART.md`
  - [ ] `database_schema.sql`

---

## ✅ PHASE 4: Atualizar Classes Existentes (10 minutos)

- [ ] **Atualizar `src/Models/TeamMember.java`**
  - [ ] Adicionar `id` e `teamId` como campos
  - [ ] Adicionar getters e setters

- [ ] **Atualizar `src/Models/Team.java`**
  - [ ] Adicionar `id` como campo
  - [ ] Adicionar getters e setters

- [ ] **Atualizar `src/Models/Car.java`**
  - [ ] Adicionar `id` e `teamId` como campos
  - [ ] Adicionar getters e setters

- [ ] **Atualizar `src/Models/Race.java`**
  - [ ] Adicionar `id`, `roundNumber`, `emoji` como campos
  - [ ] Adicionar `getName()`, `getEmoji()`, `setEmoji()`
  - [ ] Adicionar `setId()`, `getId()`, `setRoundNumber()`, `getRoundNumber()`

- [ ] **Compilar e Verificar Erros**
  - Comando: `javac -d bin -sourcepath src src/**/*.java`
  - [ ] Não deve haver erros de compilação

---

## ✅ PHASE 5: Atualizar Main.java (5 minutos)

- [ ] **Opção A: Substituir Main.java**
  - [ ] Usar `MainUpdated.java` como novo Main
  - [ ] Renomear `Main.java` para `Main_backup.java`
  - [ ] Renomear `MainUpdated.java` para `Main.java`

- [ ] **Opção B: Integrar Manualmente**
  - [ ] Adicionar inicialização de `DatabaseManager`
  - [ ] Adicionar verificação de conexão
  - [ ] Adicionar chamada a `DatabaseInitializer` (primeira vez)
  - [ ] Passar `dbManager` para `SimulacaoCorrida`

---

## ✅ PHASE 6: Primeira Execução (5 minutos)

- [ ] **Executar Aplicação**
  ```bash
  cd G:\PROJETOS\SepphiTeamF1-main
  javac -d bin -sourcepath src src/Main/Main.java
  java -cp bin Main.Main
  ```

- [ ] **Responder Perguntas**
  - [ ] Quando perguntado: "É a primeira vez?" → Responder **SIM**
  - [ ] Aguardar população do banco (2-3 segundos)

- [ ] **Verificar Mensagens de Sucesso**
  - [ ] ✓ "Connected to the database successfully"
  - [ ] ✓ "Red Bull Racing carregada com sucesso"
  - [ ] ✓ "Ferrari carregada com sucesso"
  - [ ] ✓ "Mercedes-AMG carregada com sucesso"
  - [ ] ✓ "McLaren carregada com sucesso"
  - [ ] ✓ "Aston Martin carregada com sucesso"
  - [ ] ✓ "24 corridas do calendario criadas!"

- [ ] **Menu Apareceu?**
  - [ ] Se sim, parabéns! ✅
  - [ ] Se não, verificar logs de erro

---

## ✅ PHASE 7: Testar Funcionalidades (15 minutos)

### Teste 1: Listar Equipes
- [ ] Menu → Opção 6 (Ver detalhes de uma equipe)
- [ ] Deve listar: Red Bull, Ferrari, Mercedes, McLaren, Aston Martin
- [ ] Cada equipe deve mostrar pilotos, engenheiros, carros

### Teste 2: Simular Corrida
- [ ] Menu → Opção 3 (Simular corrida)
- [ ] Deve mostrar lista de corridas
- [ ] Selecionar uma corrida
- [ ] Simular resultado
- [ ] Verificar se resultado foi salvo (segunda execução deve mostrar corrida como simulada)

### Teste 3: Ver Classificação
- [ ] Menu → Opção 4 (Ver classificação de equipes)
- [ ] Deve mostrar:
  - [ ] Posição de cada equipe
  - [ ] Pontos totais
  - [ ] Corridas completadas
- [ ] Após simular corrida, pontos devem ser atualizados

### Teste 4: Substituição de Piloto
- [ ] Menu → Opção 8 (Substituir piloto manualmente)
- [ ] Selecionar equipe, piloto a sair, piloto a entrar
- [ ] Verificar alerta com emoji ⚠️
- [ ] Segunda execução deve mostrar histórico de substituições

### Teste 5: Melhorar Carros
- [ ] Menu → Opção 1 (Melhorar carros)
- [ ] Engenheiros trabalham
- [ ] Performance deve aumentar
- [ ] Dados devem persistir na próxima execução

### Teste 6: Persistência de Dados
- [ ] Simular várias corridas
- [ ] Fechar aplicação
- [ ] Reabrir aplicação
- [ ] Responder **NÃO** quando perguntado sobre popular banco
- [ ] [ ] Verificar que todos os dados anteriores ainda existem
- [ ] ✅ SE TUDO PERSISTIU = SUCESSO!

---

## ✅ PHASE 8: Validação Final (5 minutos)

- [ ] **Testes de Banco de Dados**
  ```sql
  -- Execute no Render Query:
  SELECT COUNT(*) FROM teams;           -- Deve retornar 5
  SELECT COUNT(*) FROM drivers;         -- Deve retornar 10+
  SELECT COUNT(*) FROM engineers;       -- Deve retornar 20+
  SELECT COUNT(*) FROM cars;            -- Deve retornar 10
  SELECT COUNT(*) FROM races;           -- Deve retornar 24
  ```

- [ ] **Testes de Persistência**
  - [ ] Simular 3 corridas
  - [ ] Verificar `race_results` tem 30+ registros
  - [ ] Verificar `drivers` com `points_of_season` > 0
  - [ ] Verificar `driver_substitutions` se fez substituição

- [ ] **Performance**
  - [ ] Aplicação não deve travar ao carregar dados
  - [ ] Menu deve responder rapidamente
  - [ ] Simular corrida deve levar < 5 segundos

---

## 📊 Estatísticas Esperadas Após Conclusão

| Item | Quantidade | Status |
|------|-----------|--------|
| **Arquivos Java Criados** | 11 | ✅ |
| **Arquivos Documentação** | 4 | ✅ |
| **Tabelas do Banco** | 12 | ✅ |
| **Equipes Carregadas** | 5 | ✅ |
| **Pilotos** | 10+ | ✅ |
| **Engenheiros** | 20+ | ✅ |
| **Carros** | 10 | ✅ |
| **Corridas** | 24 | ✅ |
| **DAOs Implementados** | 8 | ✅ |
| **Linhas de Código Novo** | 3000+ | ✅ |

---

## 🚨 Troubleshooting

Se algo não funcionar, verifique nesta ordem:

### ❌ Erro: "Connection refused"
1. [ ] Testar credenciais no Render Dashboard
2. [ ] Verificar se banco está online
3. [ ] Verificar internet
4. [ ] Tentar novamente em 30 segundos

### ❌ Erro: "Table 'X' not found"
1. [ ] Voltar para Render Query
2. [ ] Executar `database_schema.sql` novamente
3. [ ] Verificar mensagens de erro no Render
4. [ ] Limpar console e tentar novamente

### ❌ Erro: "Duplicate key value"
1. [ ] Limpar tabelas: `TRUNCATE TABLE <table> CASCADE;`
2. [ ] Executar `database_schema.sql` novamente
3. [ ] Reexecutar DatabaseInitializer

### ❌ Erro: "NullPointerException"
1. [ ] Verificar se `dbManager` foi inicializado
2. [ ] Verificar se dados foram carregados do banco
3. [ ] Adicionar validações nulas no código

---

## ✨ Parabéns! 🎉

Se todos os checkboxes acima estiverem marcados, você completou com sucesso a implementação do sistema de persistência de dados!

**Próximos passos recomendados:**
1. Criar backup automático do banco
2. Implementar DataService para lógica de negócios
3. Adicionar cache em memória
4. Criar relatórios em PDF
5. Desenvolver dashboard web

---

**Última Atualização**: Outubro 2025
**Versão**: 1.0
**Tempo Total Estimado**: 1 hora
**Nível de Dificuldade**: ⭐⭐ Intermediário


