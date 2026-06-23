# Entrada
def Leia():
    v1 = int(input('Digite um valor '))
    v2 = int(input('Digite outro valor '))
    op = input('Digite a Operação [* / + -]: ')
    msg = f'{v1} {op} {v2}'
    if op == '+':
        res = Soma(v1, v2)
    elif op == '-':
        res = Subtraçao(v1, v2)
    elif op =='*':
        res = Multiplicaçao(v1, v2)
    elif op == '/':
        res = Divisao(v1, v2)
    Escreva(msg, res)
# Soma
def Soma(v1, v2):
    return (v1+v2)

# Subtração
def Subtraçao(v1, v2):
    return (v1-v2)

# Multiplicação
def Multiplicaçao(v1, v2):
    return (v1*v2)

# Divisão 
def Divisao(v1, v2):
    return(v1/v2)

# Saída
def Escreva(msg, resultado):
    print(f'{msg} = {resultado}')

Leia()