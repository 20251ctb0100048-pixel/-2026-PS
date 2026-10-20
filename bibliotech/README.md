# BiblioTech

Sistema para ajudar a organizar uma biblioteca e facilitar o controle de livros e empréstimos.

## 1. O projeto

O BiblioTech ajuda leitores a consultar e emprestar livros e bibliotecários a controlar os empréstimos e devoluções.

## 2. Histórias de usuário

| ID   | História                                                             |
| ---- | -------------------------------------------------------------------- |
| HU01 | Como leitor, quero consultar um livro para saber se está disponível. |
| HU02 | Como leitor, quero emprestar um livro.                               |
| HU03 | Como leitor, quero devolver um livro.                                |
| HU04 | Como bibliotecário, quero identificar o leitor.                      |
| HU05 | Como bibliotecário, quero consultar os empréstimos.                  |

## 3. Requisitos

| ID   | Requisito                                               | Veio da                |
| ---- | ------------------------------------------------------- | ---------------------- |
| RF01 | O sistema deve permitir consultar livros.               | HU01                   |
| RF02 | O sistema deve permitir emprestar livros.               | HU02                   |
| RF03 | O sistema deve permitir devolver livros.                | HU03                   |
| RF04 | O sistema deve permitir identificar leitores.           | HU04                   |
| RF05 | O sistema deve permitir consultar empréstimos.          | HU05                   |
| RF06 | O sistema deve permitir reservar um livro indisponível. | Necessidade do sistema |

### Requisitos não funcionais

* RNF01: O sistema deve ser simples de usar.
* RNF02: O sistema deve manter os dados organizados.

## 4. Diagramas

![Diagrama de casos de uso](docs/casos-de-uso.svg)

![Diagrama de classes](docs/classes.svg)

## 5. Do diagrama ao Java

Durante a implementação das classes, foram adicionados dois atributos que não estavam no diagrama:

* `Livro.disponivel`: indica se o livro está disponível para empréstimo.
* `Leitor.livrosEmMaos`: indica quantos livros o leitor está com ele.

## 6. Como executar

Na pasta principal do projeto, compile as classes:

```bash
javac bibliotech/*.java
```

Para executar o teste da biblioteca:

```bash
java bibliotech.TesteBiblioteca
```

Para executar os testes dos requisitos:

```bash
java bibliotech.TesteRequisitos
```

## 7. Verificação dos requisitos

O arquivo `TesteRequisitos.java` verifica os requisitos RF01, RF02, RF03, RF04 e RF05.

Resultado atual dos testes:

```text
12 passaram, 0 falharam.
```

Os testes verificam consultas de livros e leitores, disponibilidade de livros, empréstimos, limite de empréstimos por leitor e devoluções.

## 8. O que ainda não faz

O RF06, que trata da reserva de um livro indisponível, ainda não está implementado.

Uma limitação atual é que o sistema funciona pelo código Java e pelo terminal, não possuindo ainda uma interface gráfica para facilitar o uso pelos leitores e bibliotecários.

## Persistência de Dados

O BiblioTech utiliza MariaDB com JDBC para armazenar os livros de forma persistente.

### Banco de dados
- Banco: `bibliotech`
- Tabela: `livros`
- Colunas: `id`, `titulo`, `autor` e `ano`
- Driver JDBC: `lib/mariadb-java-client.jar`

### Classes criadas
- `Conexao.java`: estabelece a conexão com o banco de dados.
- `TesteConexao.java`: verifica a conexão com o banco.
- `LivroDAO.java`: implementa as operações de persistência.
- `TesteLivroDAO.java`: testa o cadastro, a atualização e a exclusão de livros.

### Operações disponíveis
- `adicionarLivro(titulo, autor, ano)`: cadastra um livro.
- `atualizarLivro(id, titulo, autor, ano)`: atualiza um livro existente.
- `excluirLivro(id)`: exclui um livro existente.

Os métodos retornam `true` quando uma linha é afetada e `false` quando nenhuma linha é alterada ou ocorre um erro SQL.

### Como executar

Configure as variáveis de ambiente no terminal:

```bash
export DB_USER=bibliotech
export DB_PASSWORD='BiblioTech2026!'
```

Compile os arquivos:

```bash
javac -cp ".:lib/*" Conexao.java TesteConexao.java LivroDAO.java TesteLivroDAO.java
```

Teste a conexão:

```bash
java -cp ".:lib/*" TesteConexao
```

Teste as operações:

```bash
java -cp ".:lib/*" TesteLivroDAO
```

O servidor MariaDB deve estar em execução antes dos testes.
