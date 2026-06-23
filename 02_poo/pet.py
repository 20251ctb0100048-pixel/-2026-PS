'''
========================================================================
# ARQUIVO      : pet.py
# Disciplina   : Programação de Sistemas (2026-2)
# Aula         : Aula 20 - Por que POO?
# Autor        : Alcides Diniz
# Conceitos    : Classe, objeto, atributos, métodos, encapsulamento
# Atividade    : Classe Pet
========================================================================
'''

class Pet:
    '''
    Esta classe representa um Pet em um sistema simples de hotel para
    pets.

    Em vez de guardar os dados do pet em um dicionário solto, como
    fazíamos
    na programação estruturada, agora agrupamos os dados e comportamentos
    dentro de uma classe.
    '''

    def __init__(self, nome, especie, idade):
        '''
        Método construtor.

        Ele é executado automaticamente quando criamos um novo objeto
        Pet.

        Exemplo:
        pet1 = Pet("Rex", "Cachorro", 5)

        Parâmetros:
        - nome: nome do pet
        - especie: espécie do pet
        - idade: idade do pet
        '''


        self.nome = nome
        self.especie = especie
        self.idade = idade
        self.hospedado = False

    '''
    # ============================================================
    # ATIVIDADE 1:
    # Adicione pelo menos 3 novos atributos para o pet.
    #
    # Sugestões:
    # self.raca
    # self.peso
    # self.nome_dono
    # self.telefone_dono
    # self.vacinado
    # self.observacoes
    #
    # Atenção:
    # Se você adicionar novos atributos, também precisará alterar
    # os parâmetros do __init__.
    # ============================================================
    '''

    def exibir_dados(self):
        '''
        Exibe os dados principais do pet.

        Atualmente, mostra apenas nome, espécie, idade e status de
        hospedagem.

        ATIVIDADE:
        Modifique este método para exibir também os novos atributos
        que você adicionou no __init__.
        '''

        print("\n--- Dados do Pet ---")
        print(f"Nome: {self.nome}")
        print(f"Espécie: {self.especie}")
        print(f"Idade: {self.idade}")
        print(f"Hospedado: {'Sim' if self.hospedado else 'Não'}")

    def registrar_entrada(self):
        '''
        Registra a entrada do pet no hotel.

        Se o pet ainda não estiver hospedado, muda o atributo hospedado
        para True.

        ATIVIDADE:
        Melhore este método para verificar se o pet já está hospedado.
        Se já estiver, mostre uma mensagem avisando.
        '''

        self.hospedado = True
        print(f"{self.nome} entrou no hotel.")

    def registrar_saida(self):
        '''
        Registra a saída do pet do hotel.

        Se o pet estiver hospedado, muda o atributo hospedado para False.

        ATIVIDADE:
        Melhore este método para verificar se o pet realmente está
        hospedado.
        Se não estiver, mostre uma mensagem avisando.
        '''

        self.hospedado = False
        print(f"{self.nome} saiu do hotel.")

    def calcular_diaria(self):
        '''
        Calcula o valor da diária do pet.

        ATIVIDADE:
        Implemente uma regra simples para calcular a diária.

        Sugestão:
        - Pet com idade até 3 anos: R$ 50,00
        - Pet com idade entre 4 e 10 anos: R$ 60,00
        - Pet com mais de 10 anos: R$ 75,00

        Este método deve retornar o valor da diária.
        '''

        # Escreva seu código aqui 
        pass    # pass iz ao interpretador:
                # "Eu sei que deveria haver algo aqui, vou colocar depois, por enquanto apenas ignore e siga em frente".

    def veriicar_vacinacao(self):
        '''
        Veriica se o pet está vacinado
        
        ATIVIDADE:
        para este método funcionar, você precisa criar um atributo chamado self.vacinado no __init__.
        
        Se o pet estiver vacinado, exiba:
        "Vacinação em dia."
        
        Caso contrário, exiba:
        "Atenção: vacinação pendente."
        '''

        # Escreva seu código aqui 
        pass
    def atualizar_peso(self, novo_peso):
        '''
        Atualiza o peso do pet.
        
        ATIVIDADE:
        Para este método uncionar, você precisa criar um atributo chamado self.peso no __init__.
        
        O método deve receber um novo peso e atualizar o valor antigo.
        
        EXEMPLO:
        pet1.atualizar_peso(12.5)
        '''

        # Escreva eu código aqui 
        pass
    def emitir_resumo(self):
        '''
        Exibe um resumo geral do pet.
        
        ATIVIDADE:
        Crie uma mensagem orgnizada cotendo:
        - nome do pet
        - espécie
        - idade
        - nome do dono 
        - peso
        - status de vacinação
        - status de hospedagem
        - valor da diária
        
        Este método deve usar informações dos atributos e também pode chamar outros métodos, como calcular_diaria().
        '''

        # Escreva seu código aqui 
        pass

'''
# ==============================================================
# TESTES DA CLASSE
# ==============================================================
# Depois de completar a classe, crie pelo menos 3 objetos Pet.
# 
# Exemplo:
# pet1 = Pet("Rex", "Cachorro", 5)
# 
# Atenção:
# Se você adicionou novos parâmetros no __init__, será necessário 
# informar esses dados na criação do objeto.
# ===============================================================
'''

pet1 = Pet("Rex", "Cachorro", 5)

pet1.exibir_dados()
pet1.registrar_entrada()
pet1.exibir_dados()

'''
# ===============================================================
# ATIVIDADE FINAL:
# Crie mais dois pets e teste todos os métodos implementados.
# ===============================================================
'''

        









