# 🎯 Roadmap e Contribuições - Sepphi Team F1

## 🚀 Roadmap do Projeto

### ✅ Fase 1: Implementação Base (Concluído)
- [x] Modelo de dados F1 completo
- [x] Sistema de simulação de corridas
- [x] Banco de dados PostgreSQL
- [x] DAOs para persistência
- [x] Menu interativo
- [x] Alertas de substituição

### 🔄 Fase 2: Melhorias (Em Progresso)
- [ ] Interface Gráfica (JavaFX)
- [ ] Dashboard web (React/Vue)
- [ ] Suporte a múltiplos usuários
- [ ] Estatísticas avançadas
- [ ] Export de relatórios (PDF)
- [ ] Replay de corridas

### 📋 Fase 3: Expansão (Planejado)
- [ ] API REST (Spring Boot)
- [ ] Mobile App
- [ ] Ranking online
- [ ] Multiplayer
- [ ] Histórico de temporadas
- [ ] Estatísticas por piloto

---

## 🤝 Como Contribuir

### 1. Reportar Bugs
Se encontrar um erro:
1. Verifique se já foi reportado em Issues
2. Descreva o problema claramente
3. Inclua a saída do erro
4. Mencione seu SO e versão do Java

### 2. Sugerir Melhorias
Abra uma issue com:
- Título descritivo
- Descrição detalhada
- Exemplos de uso se possível
- Benefícios da melhoria

### 3. Enviar código
1. Fork o repositório
2. Crie uma branch: `git checkout -b feature/minha-feature`
3. Faça commits claros: `git commit -m "Add: descrição da feature"`
4. Push para a branch: `git push origin feature/minha-feature`
5. Abra um Pull Request

---

## 📝 Padrões de Código

### Nomenclatura
- Classes: `PascalCase` (ex: `SimulacaoCorrida`)
- Métodos: `camelCase` (ex: `simularCorrida()`)
- Constantes: `UPPER_SNAKE_CASE`
- Variáveis: `camelCase`

### Estrutura de Classes
```java
public class Exemplo {
    // 1. Constantes
    private static final String CONSTANTE = "valor";
    
    // 2. Atributos privados
    private String atributo;
    
    // 3. Construtor
    public Exemplo(String atributo) {
        this.atributo = atributo;
    }
    
    // 4. Getters e Setters
    public String getAttribute() {
        return atributo;
    }
    
    public void setAttribute(String atributo) {
        this.atributo = atributo;
    }
    
    // 5. Métodos públicos
    public void metodoPublico() {
        // implementação
    }
    
    // 6. Métodos privados
    private void metodoPrivado() {
        // implementação
    }
}
```

### Comentários
- Use JavaDoc para métodos públicos
- Comente apenas o "por quê", não o "o quê"
- Mantenha comentários atualizados

```java
/**
 * Simula uma corrida F1
 * 
 * @param indiceCorrida índice da corrida na lista
 * @throws IndexOutOfBoundsException se índice for inválido
 */
public void simularCorrida(int indiceCorrida) {
    // implementação
}
```

---

## 🧪 Testes

### Executar Testes Completos
```bash
scripts/test_all.bat
```

### Testar Apenas Compilação
```bash
# Windows
scripts/test_all.bat

# Linux/Mac
javac -d bin -cp "bin:lib/postgresql-42.7.1.jar" -sourcepath src src/**/*.java
```

### Verificar Conexão com Banco
```bash
scripts/test_connection.bat
```

---

## 🐛 Debugging

### Modo Verbose
```bash
java -cp "bin;lib\postgresql-42.7.1.jar" Main.Main -verbose
```

### Com Stack Trace Completo
```bash
java -cp "bin;lib\postgresql-42.7.1.jar" Main.Main 2>&1 | tee debug.log
```

---

## 📚 Estrutura de Commit

```
[TYPE] Descrição breve (50 caracteres ou menos)

Descrição mais detalhada se necessário (72 caracteres por linha).
Explique O QUÊ foi alterado e POR QUÊ.

Tipos:
- Add: Nova funcionalidade
- Fix: Correção de bug
- Docs: Documentação
- Style: Formatação/Style
- Refactor: Refatoração sem mudança de comportamento
- Perf: Melhoria de performance
- Test: Adição/Modificação de testes
```

### Exemplos
```
[Add] Sistema de alertas para substituição de pilotos

Implementa alertas visuais quando um piloto é substituído.
Inclui motivo da substituição e registro em BD.

Fixes #123
```

---

## 🔍 Checklist para Pull Request

- [ ] Código compilando sem erros
- [ ] Testes passando
- [ ] Sem código comentado
- [ ] Documentação atualizada
- [ ] Commits com mensagens claras
- [ ] Sem dependências desnecessárias
- [ ] Segue padrões do projeto
- [ ] README atualizado se necessário

---

## 📖 Documentação

### Adicionar Nova Feature
1. Crie a classe na pasta apropriada
2. Adicione comentários JavaDoc
3. Atualize `docs/` se necessário
4. Adicione entrada no README

### Atualizar Banco de Dados
1. Modifique `database/database_schema.sql`
2. Crie script de migração se necessário
3. Atualize DAOs correspondentes
4. Documente as alterações

---

## 🎯 Prioridades

### Alta Prioridade 🔴
- Bugs na simulação
- Erros de conexão com BD
- Crash da aplicação

### Média Prioridade 🟡
- Melhorias de performance
- Novos recursos
- Refatoração

### Baixa Prioridade 🟢
- Documentação
- Testes unitários
- Formatação

---

## 💬 Comunicação

- Use Issues para discussões técnicas
- Use Pull Requests para revisão de código
- Seja respeitoso e construtivo

---

## 📊 Estatísticas do Projeto

```
Linhas de Código:     ~3.500 LOC
Classes:              20+ classes
Métodos:              100+ métodos
DAOs:                 8 classes
Tabelas BD:           12+ tabelas
Cobertura:            Bem documentado
```

---

## 🎓 Recursos para Aprender

### Java
- [Oracle Java Docs](https://docs.oracle.com/en/java/)
- [Java Design Patterns](https://refactoring.guru/design-patterns/java)

### PostgreSQL
- [PostgreSQL Docs](https://www.postgresql.org/docs/)
- [JDBC Tutorial](https://docs.oracle.com/javase/tutorial/jdbc/)

### Git
- [Git Documentation](https://git-scm.com/doc)
- [GitHub Flow](https://guides.github.com/introduction/flow/)

---

## ✨ Agradecimentos

Obrigado por contribuir com Sepphi Team F1! 🏁

Seu trabalho ajuda a tornar este projeto melhor para todos.

