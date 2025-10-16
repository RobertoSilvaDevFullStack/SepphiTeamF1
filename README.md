# 🏎️ Sepphi Team F1 - Simulador de Temporada de Fórmula 1

## 📋 Sobre o Projeto
Simulador completo de temporada da Fórmula 1 2025 desenvolvido em Java com Programação Orientada a Objetos. O sistema simula corridas, gerencia equipes, pilotos, engenheiros e fornece classificações realistas baseadas em desempenho de carros e habilidades dos pilotos.

---

## ✨ Características Principais

### 🏁 Equipes e Pilotos
- **12 Equipes Oficiais** da temporada 2025
- **24 Pilotos Titulares** com estatísticas reais
- **Pilotos Reservas** disponíveis para substituições
- **Chefe de Equipe** para cada time
- **5 Engenheiros especializados** por equipe:
  - Engenheiro de Aerodinâmica
  - Engenheiro de Motor
  - Engenheiro de Suspensão
  - Engenheiro de Eletrônica
  - Engenheiro de Estratégia

### 🏆 Sistema de Corridas
- **24 Grandes Prêmios** do calendário oficial 2025
- **Sistema de Pontuação oficial da F1** (25, 18, 15, 12, 10, 8, 6, 4, 2, 1)
- **Classificação em tempo real** de pilotos e construtores
- **Aleatoriedade balanceada** para resultados imprevisíveis
- **Simulação completa de temporada** ou corridas individuais

### ⚙️ Funcionalidades Avançadas
- **Desenvolvimento de carros** através dos engenheiros
- **Substituição de pilotos** (manual ou aleatória)
- **Detalhamento completo** de equipes e resultados
- **Interface interativa** via menu de texto

---

## 🏁 Equipes Incluídas

| Equipe | País | Pilotos Titulares |
|--------|------|-------------------|
| **Red Bull Racing** | Áustria | Max Verstappen, Yuki Tsunoda |
| **Ferrari** | Itália | Charles Leclerc, Lewis Hamilton |
| **Mercedes-AMG** | Alemanha | George Russell, Andrea Kimi Antonelli |
| **McLaren** | Reino Unido | Lando Norris, Oscar Piastri |
| **Aston Martin** | Reino Unido | Fernando Alonso, Lance Stroll |
| **Alpine** | França | Pierre Gasly, Franco Colapinto |
| **Williams** | Reino Unido | Alexander Albon, Carlos Sainz |
| **RB F1 Team** | Itália | Isack Hadjar, Liam Lawson |
| **Haas F1 Team** | Estados Unidos | Esteban Ocon, Oliver Bearman |
| **Kick Sauber** | Suíça | Nico Hulkenberg, Gabriel Bortoleto |
| **Andretti Cadillac** | Estados Unidos | Valtteri Bottas, Sergio Pérez |
| **Sepphi Team Race** | Brasil | Cloud Strife, Tifa Lockheart |

---

## 🚀 Como Executar

### Pré-requisitos
- Java JDK 8 ou superior
- IDE Java (IntelliJ IDEA, Eclipse, VS Code, etc.) ou compilador Java

### Passos para execução

1. **Clone o repositório**
   ```bash
   git clone https://github.com/RobertoSilvaDevFullStack/SepphiTeamF1.git
   cd SepphiTeamF1
   ```

2. **Compile o projeto**
   ```bash
   javac -d out src/Models/*.java src/TemporadaF1/*.java
   ```

3. **Execute o simulador**
   ```bash
   java -cp out TemporadaF1.SimulacaoCorrida
   ```

---

## 🎮 Menu de Funcionalidades

Ao executar o programa, você terá acesso às seguintes opções:

1. **Melhorar carros** - Os engenheiros trabalham para aumentar a potência dos carros
2. **Criar nova corrida** - Adiciona um Grande Prêmio ao calendário
3. **Simular corrida** - Executa uma corrida específica e mostra os resultados
4. **Ver classificação de equipes** - Exibe o campeonato de construtores
5. **Ver classificação de pilotos** - Exibe o campeonato de pilotos
6. **Ver detalhes de uma equipe** - Informações completas sobre pilotos, carros e engenheiros
7. **Simular temporada completa** - Executa todas as 24 corridas automaticamente
8. **Substituir piloto** - Troca um piloto titular por um reserva

---

## 📅 Calendário 2025 (24 Corridas)

1. GP do Bahrein
2. GP da Arábia Saudita
3. GP da Austrália
4. GP do Japão
5. GP da China
6. GP de Miami
7. GP da Emília-Romanha
8. GP de Mônaco
9. GP da Espanha
10. GP do Canadá
11. GP da Áustria
12. GP da Grã-Bretanha
13. GP da Hungria
14. GP da Bélgica
15. GP da Holanda
16. GP da Itália
17. GP do Azerbaijão
18. GP de Singapura
19. GP dos Estados Unidos
20. GP do México
21. GP de São Paulo
22. GP de Las Vegas
23. GP do Qatar
24. GP de Abu Dhabi

---

## 📝 Estrutura do Projeto

```
SepphiTeamF1/
│
├── src/
│   ├── Models/
│   │   ├── Car.java          # Modelo de carro com potência e coeficiente
│   │   ├── Driver.java       # Modelo de piloto com habilidades
│   │   ├── Engineer.java     # Modelo de engenheiro especializado
│   │   ├── Race.java         # Modelo de corrida com resultados
│   │   ├── Team.java         # Modelo de equipe completa
│   │   ├── TeamBoss.java     # Modelo de chefe de equipe
│   │   └── TeamMember.java   # Classe abstrata base
│   │
│   └── TemporadaF1/
│       ├── TemporadaF1.java      # Lógica principal da temporada
│       └── SimulacaoCorrida.java # Interface e menu do usuário
│
├── README.md
└── .gitignore
```

---

## 🎯 Conceitos Aplicados

- **Programação Orientada a Objetos (POO)**
  - Encapsulamento
  - Herança
  - Polimorfismo
  - Abstração

- **Estruturas de Dados**
  - ArrayList para gerenciamento de coleções
  - HashMap para relações entre objetos

- **Algoritmos**
  - Ordenação de classificações
  - Geração de números aleatórios balanceados
  - Cálculo de pontuações

---

## 💻 Tecnologias Utilizadas

- **Linguagem:** Java
- **Paradigma:** Programação Orientada a Objetos
- **Controle de Versão:** Git & GitHub
- **IDE Recomendada:** IntelliJ IDEA

---

## 👨‍💻 Autor

**Roberto Silva** - Desenvolvimento Full Stack  
GitHub: [@RobertoSilvaDevFullStack](https://github.com/RobertoSilvaDevFullStack)

---

## 📄 Licença

Este projeto é de código aberto e está disponível para fins educacionais.

---

## 🤝 Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para:
- Reportar bugs
- Sugerir novas funcionalidades
- Melhorar a documentação
- Enviar pull requests

---

**Desenvolvido com ☕ e paixão pela Fórmula 1** 🏎️💨
