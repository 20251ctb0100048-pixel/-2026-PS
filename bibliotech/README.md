# BiblioTech

Sistema para ajudar a organizar uma biblioteca e facilitar o controle de livros e empréstimos.

## 1. O projeto

O BiblioTech ajuda leitores a consultar e emprestar livros e bibliotecários a controlar os empréstimos e devoluções.

## 2. Histórias de usuário

| ID | História |
|---|---|
| HU01 | Como leitor, quero consultar um livro para saber se está disponível. |
| HU02 | Como leitor, quero emprestar um livro. |
| HU03 | Como leitor, quero devolver um livro. |
| HU04 | Como bibliotecário, quero identificar o leitor. |
| HU05 | Como bibliotecário, quero consultar os empréstimos. |

## 3. Requisitos

| ID | Requisito | Veio da |
|---|---|---|
| RF01 | O sistema deve permitir consultar livros. | HU01 |
| RF02 | O sistema deve permitir emprestar livros. | HU02 |
| RF03 | O sistema deve permitir devolver livros. | HU03 |
| RF04 | O sistema deve permitir identificar leitores. | HU04 |
| RF05 | O sistema deve permitir consultar empréstimos. | HU05 |
| RF06 | O sistema deve permitir reservar um livro indisponível. | Necessidade do sistema |

### Requisitos não funcionais

- RNF01: O sistema deve ser simples de usar.
- RNF02: O sistema deve manter os dados organizados.

## 4. Diagramas

![Diagrama de casos de uso](docs/casos-de-uso.svg)

![Diagrama de classes](docs/classes.svg)