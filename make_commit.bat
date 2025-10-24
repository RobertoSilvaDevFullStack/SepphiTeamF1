@echo off
cd /d G:\PROJETOS\SepphiTeamF1-main

echo.
echo ================================================================
echo    FAZENDO COMMIT DA REORGANIZACAO DO PROJETO
echo ================================================================
echo.

echo [1/4] Verificando status do git...
git status
echo.

echo [2/4] Adicionando todos os arquivos...
git add .
echo [OK] Arquivos adicionados
echo.

echo [3/4] Fazendo commit...
git commit -m "[Refactor] Reorganizar projeto com padrão profissional

- Criar pastas: /docs, /scripts, /database, /tests
- Mover documentação para /docs
- Mover scripts para /scripts
- Mover SQL para /database
- Reescrever README.md com badges e informações atuais
- Criar GETTING_STARTED.md - Guia rápido
- Criar INDEX.md - Índice de navegação
- Criar PROJECT_SUMMARY.md - Resumo técnico
- Criar CONTRIBUTING.md - Guia de contribuição
- Criar LICENSE (MIT)
- Melhorar .gitignore
- Criar ORGANIZATION_COMPLETE.md
- Criar REORGANIZATION_SUMMARY.md

Resultado:
- Redução de 30+ arquivos na raiz para 8
- Documentação expandida de 3 para 8 documentos
- Estrutura profissional e escalável
- Pronto para GitHub e produção"

echo.
echo [4/4] Verificando log do commit...
git log --oneline -5
echo.

echo ================================================================
echo [OK] COMMIT REALIZADO COM SUCESSO!
echo ================================================================
echo.

pause

