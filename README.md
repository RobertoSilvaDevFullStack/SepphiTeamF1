# 🏁 Sepphi Team F1 - Simulador de Temporada 2025

<div align="center">

[![Java](https://img.shields.io/badge/Java-25-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-336791?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Render](https://img.shields.io/badge/Render-Database-46E3B7?style=for-the-badge&logo=render&logoColor=white)](https://render.com/)
[![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)](LICENSE)
[![Status](https://img.shields.io/badge/Status-Production%20Ready-brightgreen?style=for-the-badge)](README.md)

Um simulador completo de temporada de Fórmula 1 com persistência de dados em banco de dados PostgreSQL na nuvem!

[🚀 Quick Start](#-quick-start) • [📋 Recursos](#-recursos) • [🛠️ Tecnologias](#️-tecnologias) • [📚 Documentação](#-documentação)

</div>

---

## 🎯 O Que É?

**Sepphi Team F1** é um simulador avançado de temporada de Fórmula 1 2025 que permite você:

- 🏎️ **Simular corridas individuais** ou temporadas completas (24 GPs)
- 📊 **Acompanhar classificações dinâmicas** de equipes e pilotos em tempo real
- 👥 **Gerenciar equipes** com pilotos, engenheiros e carros
- 🔄 **Substituir pilotos** com alertas detalhados
- 💾 **Persistir todos os dados** em banco de dados PostgreSQL na nuvem
- ⚙️ **Melhorar carros** com engenheiros especializados
- 📈 **Rastrear histórico completo** de todas as corridas e substituições

---

## 🚀 Quick Start

### Pré-requisitos
- **Java 25+** (OpenJDK ou Oracle JDK)
- **PostgreSQL 15+** (ou conta no Render para banco na nuvem)
- **Conexão com Internet** (para banco de dados remoto)

### Instalação Rápida

1. **Clonar o repositório:**
```bash
git clone https://github.com/seu-usuario/SepphiTeamF1.git
cd SepphiTeamF1-main
```

2. **Compilar o projeto:**
```bash
java -version  # Verificar Java
scripts/test_all.bat  # Windows - Compilar e testar
```

3. **Executar a aplicação:**
```bash
scripts/run_app.bat  # Windows
# ou
java -cp "bin;lib\postgresql-42.7.1.jar" Main.Main
```

4. **Na primeira execução:**
- Digite `s` para popular o banco com dados de teste
- Aguarde o carregamento de 5 equipes, 24 corridas e 60+ engenheiros

---

## 🎮 Como Usar

### Menu Principal
```
[F1] >>> SIMULACAO TEMPORADA F1 2025 <<<
==================================================
[*] 1 - Melhorar carros (engenheiros trabalhando)
[+] 2 - Criar nova corrida
[!] 3 - Simular corrida
[T] 4 - Ver classificacao de equipes
[P] 5 - Ver classificacao de pilotos
[=] 6 - Ver detalhes de uma equipe
[C] 7 - Simular temporada completa (24 corridas)
[S] 8 - Substituir piloto manualmente
[X] 9 - Limpar lista de corridas
[0] 0 - Sair
```

### Exemplos de Uso

#### Simular Uma Corrida
```
Escolha uma opção: 3
[!] Corridas disponíveis:
   0 - GP do Bahrein
   1 - GP da Arabia Saudita
   ...
Escolha o número da corrida: 0

[!] >>> INICIANDO CORRIDA <<<
>>> Corrida: GP do Bahrein
>>> RESULTADOS DA CORRIDA <<<
1 - #1 Max Verstappen - 25 pontos
2 - #16 Charles Leclerc - 18 pontos
3 - #81 Oscar Piastri - 15 pontos
...
```

#### Ver Classificação de Equipes
```
Escolha uma opção: 4
*** CLASSIFICACAO DE EQUIPES ***
1. Red Bull Racing - 43 pontos
2. Ferrari - 33 pontos
3. McLaren - 28 pontos
...
```

#### Substituir Piloto com Alerta
```
Escolha uma opção: 8
⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠
[!] >>> ALERTA: SUBSTITUICAO DE PILOTO! <<<
⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠
[T] Equipe: Ferrari
[*] Motivo: Lesão
[ERRO] Piloto saindo: #16 Charles Leclerc
[OK] Piloto entrando: #50 Oliver Bearman
```

---

## 📊 Recursos

### ✨ Funcionalidades Principais

| Recurso | Descrição |
|---------|-----------|
| 🏎️ **Simulação de Corridas** | Simule qualquer corrida individual ou temporada completa |
| 📊 **Classificações Dinâmicas** | Acompanhe pontos de equipes e pilotos em tempo real |
| 👥 **Gerenciamento de Equipes** | 12 equipes F1 com pilotos principais e reservas |
| 🔧 **Melhoria de Carros** | Use engenheiros para aumentar performance |
| 🔄 **Substituição de Pilotos** | Troque pilotos com alertas e registro de motivos |
| 💾 **Persistência de Dados** | Todos os dados salvos em PostgreSQL na nuvem |
| 📈 **Histórico Completo** | Rastreie todas as corridas e substituições |
| ⚠️ **Alertas Visuais** | Notificações claras de eventos importantes |

### 📋 Dados Disponíveis

- **12 Equipes F1 2025** (Red Bull, Ferrari, McLaren, Mercedes, Aston Martin, etc)
- **24+ Pilotos** (Principais e reservas)
- **60+ Engenheiros** Especializados
- **24 Corridas** Do calendário F1 2025
- **Países** De todo o mundo

---

## 🛠️ Tecnologias

### Backend
```
┌─────────────────────────────────┐
│   ARQUITETURA DO PROJETO        │
├─────────────────────────────────┤
│ Java 25 (OpenJDK)               │
│ Padrão DAO (Data Access Object) │
│ Design Pattern MVC (adaptado)   │
└─────────────────────────────────┘
```

### Banco de Dados
```
┌────────────────────────────────────┐
│   INFRAESTRUTURA DE DADOS          │
├────────────────────────────────────┤
│ PostgreSQL 15 (Banco Relacional)   │
│ Render (Hospedagem na Nuvem)       │
│ 12+ Tabelas Otimizadas             │
│ Índices para Performance            │
└────────────────────────────────────┘
```

### Dependências
- **postgresql-42.7.1.jar** - Driver JDBC para PostgreSQL
- **Java Collections Framework** - Estruturas de dados

---

## 📁 Estrutura do Projeto

```
SepphiTeamF1-main/
├── src/                          # Código-fonte
│   ├── Main/
│   │   └── Main.java            # Entrada principal
│   ├── Models/                  # Classes de modelo
│   │   ├── Team.java
│   │   ├── Driver.java
│   │   ├── Engineer.java
│   │   ├── Car.java
│   │   ├── Race.java
│   │   └── ...
│   ├── Data/                    # Data Access Objects (DAOs)
│   │   ├── DatabaseManager.java
│   │   ├── TeamDAO.java
│   │   ├── DriverDAO.java
│   │   ├── CarDAO.java
│   │   ├── RaceDAO.java
│   │   └── ...
│   ├── TemporadaF1/             # Lógica de simulação
│   │   ├── SimulacaoCorrida.java
│   │   └── TemporadaF1.java
│   └── Utils/                   # Utilitários
│       └── EmojiHelper.java
├── bin/                         # Classes compiladas
├── lib/                         # Bibliotecas externas
│   └── postgresql-42.7.1.jar
├── docs/                        # Documentação
│   ├── CHECKLIST.md
│   ├── DATABASE_GUIDE.md
│   ├── QUICKSTART.md
│   └── ...
├── scripts/                     # Scripts de execução
│   ├── run.bat
│   ├── run_app.bat
│   ├── test_all.bat
│   ├── simulate_race.bat
│   └── ...
├── database/                    # Scripts SQL
│   ├── database_schema.sql
│   └── clean_database.sql
├── README.md                    # Este arquivo!
└── .gitignore
```

---

## 🔧 Configuração do Banco de Dados

### Opção 1: PostgreSQL Local
```sql
-- Criar banco de dados
CREATE DATABASE sepphi_f1;

-- Executar script
\i database/database_schema.sql;
```

### Opção 2: Render (Recomendado)
1. Acesse [render.com](https://render.com/)
2. Crie uma instância PostgreSQL
3. Execute `database/database_schema.sql` no console do Render
4. Configure credenciais em `src/Main/Main.java`

---

## 📚 Documentação

### Guias Disponíveis
| Arquivo | Descrição |
|---------|-----------|
| `docs/QUICKSTART.md` | Começar em 5 minutos |
| `docs/CHECKLIST.md` | Lista de implementação |
| `docs/DATABASE_GUIDE.md` | Guia completo do banco |
| `docs/IMPLEMENTATION_SUMMARY.md` | Resumo técnico |

### Executar Testes
```bash
# Testar compilação e conexão
scripts/test_all.bat

# Testar apenas conexão com banco
scripts/test_connection.bat

# Simular uma corrida completa
scripts/simulate_race.bat
```

---

## 🎯 Casos de Uso

### 1️⃣ Simular Corrida Individual
Perfeito para testar estratégias em uma corrida específica sem rodar o calendário completo.

### 2️⃣ Simular Temporada Completa
Execute todas as 24 corridas em sequência e veja como a classificação evolui.

### 3️⃣ Análise de Performance
Use a função de melhoria de carros para otimizar performance das equipes.

### 4️⃣ Gerenciamento de Equipes
Monitore e substitua pilotos conforme necessário durante a temporada.

---

## 📊 Resultados de Testes

### Compilação ✅
```
[OK] Models - 7 classes
[OK] Data - 13 DAOs e gerenciadores
[OK] TemporadaF1 - 2 classes
[OK] Main - 1 classe
```

### Banco de Dados ✅
```
[OK] Conexão PostgreSQL estabelecida
[OK] 12 tabelas criadas
[OK] 5 equipes carregadas
[OK] 24 corridas disponíveis
[OK] 60+ engenheiros cadastrados
```

### Funcionalidades ✅
```
[OK] Simulação de corridas
[OK] Cálculo de pontuação F1
[OK] Classificações dinâmicas
[OK] Substituição de pilotos
[OK] Persistência de dados
[OK] Alertas visuais
```

---

## 🤝 Contribuições

Sugestões de melhorias são bem-vindas! 

### Ideias Futuras
- 🎨 Interface Gráfica (JavaFX)
- 📱 API REST (Spring Boot)
- 📊 Dashboard Web
- 🔄 Multi-usuário
- 📈 Estatísticas avançadas
- 🎬 Replay de corridas

---

## ⚖️ Licença

Este projeto está sob a licença **MIT**. Veja [LICENSE](LICENSE) para mais detalhes.

---

## 📞 Suporte

### Problemas Comuns

#### ❌ "Java não encontrado"
```bash
# Verificar instalação
java -version

# Se não estiver instalado, baixe de:
https://www.oracle.com/java/technologies/downloads/
```

#### ❌ "Erro ao conectar ao banco"
```bash
# Verificar credenciais em Main.java
# Verificar conexão com internet
# Testar com: scripts/test_connection.bat
```

#### ❌ "Caracteres corrompidos na saída"
```bash
# Já está corrigido com símbolos ASCII
# Se persistir, execute com UTF-8:
java -Dfile.encoding=UTF-8 -cp "bin;lib\postgresql-42.7.1.jar" Main.Main
```

---

## 🎓 Tecnologias Aprendidas

✨ **Padrões de Design:**
- DAO (Data Access Object)
- MVC (Model-View-Controller)
- Singleton (DatabaseManager)

✨ **Conceitos Aplicados:**
- OOP (Orientação a Objetos)
- JDBC (Java Database Connectivity)
- SQL Avançado
- Tratamento de Exceções
- Collections Framework
- Arquitetura em Camadas

---

## 🏆 Destaques

- ✅ **100% Funcional** - Código em produção
- ✅ **Bem Documentado** - Comentários e guias completos
- ✅ **Otimizado** - Performance e eficiência garantidas
- ✅ **Persistente** - Dados salvos em banco de dados
- ✅ **Escalável** - Arquitetura preparada para expansão
- ✅ **Profissional** - Estrutura de código limpa e organizada

---

## 📈 Estatísticas

```
Linhas de Código:     ~3.500 LOC
Classes:              20+ classes
DAOs:                 8 Data Access Objects
Tabelas BD:           12+ tabelas
Equipes:              12 equipes F1
Pilotos:              24+ pilotos
Engenheiros:          60+ engenheiros
Corridas:             24 GPs
Status:               ✅ Pronto para Produção
```

---

<div align="center">

### 🚀 Pronto para começar?

[📥 Download](../../archive/refs/heads/main.zip) • [🔗 Clone](https://github.com/seu-usuario/SepphiTeamF1.git) • [⭐ Star](https://github.com/seu-usuario/SepphiTeamF1)

**Feito com ❤️ por Roberto Silva**

Last Updated: Outubro 2025

</div>

