"""
agenda.py - Aula 23 (Programação de Sistemas, 2026)
Agenda de Contatos: classe inicial.
"""

# ======================================================
# Disciplina : Programação de Sistemas
# Aula       : 23 - Menu interativo e persistência de objetos
# Tipo       : Gabarito (Mão na Massa)
# Autor      : Alcides Diniz
# Data       : 2026
# Descrição  : Agenda de Contatos com menu, CRUD em memória
#              e dois formatos de persistência (.txt e binário).
#              Serve de modelo para o Sistema de Hotel para Pets V2.0.
# ======================================================

# Importamos pickle: módulo padrão do Python para "serializar" objetos
# (transformar um objeto Python em bytes que podem ser gravados em disco
# e depois recuperados intactos).
import pickle


# =====================================================
# CLASSE Contato - representa um contato da agenda
# =====================================================
# Em vez de guardar nome, telefone e email em três listas paralelas
# (um padrão estruturado, frágil e propenso a erros), agrupamos esses
# dados - e os comportamentos relacionados - dentro de uma classe.
class Contato:
    """Representa um contato simples na agenda."""

def __init__(self, nome, telefone, email):
    #O construtor é o "cartório" do objeto: aqui registramos
    #tudo o que esse Contato precisa saber sobre si mesmo.
    self.nome = nome 
    self.telefone = telefone
    self.email = email

def exibir(self):
    # Comportamento (método) que pertence ao objeto: o próprio
    # contato sabe se mostrar. Assim, quem usa a classe não
    # precisa saber por dentro como ela é organizada.
    print(f" Nome : {self.nome}")
    print(f" Telefone: {self.telefone}")
    print(f" Email : {self.email}")


# Teste rápido da clase 
if __name__ == "__main__":
 c1 = Contato("Maria Silva", "42 99999-0001", "maria@email.com")
 c1.exibir()

def menu():
    while True:
        print("\n======== AGENDA ========")
        print("\1 - Cadastrar contato")
        print("2 - Listar contatos")
        print("0 - Sair")
        opcao = input("Opção: ")

        if opcao == "1":
            print("(em breve: cadastrar)")
        elif opcao == "2":
            print("(em breve: listar)")
        elif opcao == "0":
            print("Até logo!")
            break
        else:
            print("Opçaõ inválida.")


if __name__ == "__main__":
    menu()


def cadastrar(contatos):
    print("\n--- Novo contato ---")
    nome = input("Nome    : ")
    telefone = input("Telefone    : ")
    email = input("Email    : ")
    contatos.append(Contato(nome, telefone, email))
    print("  Contato cadastrado.")


def listar(contatos):
    if not contatos:
        print("\n(agenda vazia)")
        return
    print(f"\n--- Agenda ({len(contatos)} contatos) ---")
    for i, c in enumerate(contatos, start=1):
        print(f"\n[[i]]")
        c.exibir()


def remover(contatos):
    listar(contatos)
    if not contatos:
        return
    indice = int(input("\nN° do contato a remover: ")) - 1
    if 0 <= indice < len(contatos):
        removido = contatos.pop(indice)
        print(f"  Contato '{removido.nome}' removido.")
    else:
        print("Indice inválido.")


def carregar_de_txt(caminho):
    contatos = []
    try:
        with open(caminho, "r", encoding="utf-8") as arquivo:
            for linha in arquivo:
                linha = linha.strip()
                if not linha:
                    continue
                partes = linha.split(";")
                nome, telefone, email = partes[0], partes[1], partes[2]
                contatos.append(Contato(nome, telefone, email))
    except FileNotFoundError:
        print(f"Arquivo {caminho} ainda não existe. Começando vazio.")
    return contatos


import pickle


def salvar_em_binario(contatos, caminho):
    with open(caminho, "wb") as arquivo:
        pickle.dump(contatos, arquivo)
    print(f"  {len(contatos)} contato(s) salvo(s) em {caminho}")



def carregar_de_binario(caminho):
    try:
        with open(caminho, "rb") as arquivo:
            return pickle.load(arquivo)
    except FileNotFoundError:
        print(f"Arquivo {caminho} ainda não existe. Começando vazio")
        return []    