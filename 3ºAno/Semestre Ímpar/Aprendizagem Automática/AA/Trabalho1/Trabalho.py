import pandas as pd
import sys
import os
import numpy as np
from sklearn.model_selection import train_test_split


# Verifica se o número correto de argumentos foi fornecido
if len(sys.argv) != 2:
    sys.exit("Use: python3 <Trabalho.py> <caminhoDoFicheiroCSV>")

# Obtém o caminho do ficheiro CSV a partir do argumento da linha de comando (será o último)
caminhoDoFicheiro = sys.argv[1]

# Verifica se o ficheiro existe
if not os.path.isfile(caminhoDoFicheiro):
    sys.exit(f"O ficheiro {caminhoDoFicheiro} não existe.")

# Carrega os dados do ficheiro CSV
data = pd.read_csv(caminhoDoFicheiro)

# Separar atributos da classe
X = data.iloc[:, :-1].values # Atributos, seleciona todas as linhas do DataFrame e todas as colunas, exceto a última.
y = data.iloc[:, -1].values # Classes, seleciona todas as linhas, mas apenas a última coluna. 

# Criar conjuntos de treino e teste
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.25, random_state=3)


class KNeighborsClassUE:
    # Método de inicialização (construtor) da classe.
    def __init__(self, k, p):
        # - k: número de vizinhos a serem considerados (default: 3).
        # - p: parâmetro de distância (default: 2.0, que corresponde à distância Euclidiana).
        self.k = k
        self.p = p
        
    # Método para treinar o modelo.
    def fit(self, X, Y):
        # - X: Conjunto de dados de treino. (atributos)
        # - y: Etiquetas correspondentes aos dados de treino.
        self.X_train = X
        self.y_train = Y


    def predict(self, X):
        etiquetasPrevistas = []  # Lista para guardar as etiquetas previstas para o conjunto fornecido.

        # Calcular a distância entre o ponto de teste atual e todos os pontos no conjunto de treino.
        for x in X:
            distancia = []  # Lista para guardar todas as distâncias de X aos dados de treino

            for y in self.X_train:
                soma = 0 # Somatório
                
                for i in range(len(x)):
                    soma += abs(x[i] - y[i]) ** self.p   # (x-y) ^ p
                    
                distanciaComPotencia = soma ** (1 / self.p) # Raiz quadrada do somatório
                
                # A distância calculada é adicionada à lista distancia, que vai guardar as distâncias de 
                # x a todos os pontos no conjunto de treino.
                distancia.append(distanciaComPotencia) 
                
                
            # Obter os índices ordenados das distâncias por ordem crescente (menor distância -> maior distância)
            indicesOrdenados  = np.argsort(distancia)

            
            # Contar as classes dos k vizinhos mais próximos
            classes = []  # Armazena as classes dos vizinhos mais próximos.
            contagemClasses = []  # Armazena a contagem de ocorrências de cada classe.

            for i in range(self.k):
                indiceAtual = indicesOrdenados[i]  # Obtém o índice do vizinho atual na lista ordenada.
                classeAtual = self.y_train[indiceAtual]  # Obtém a classe do vizinho atual.

                if classeAtual not in classes:
                    # Se a classe ainda não estiver em 'classes', adiciona-a e inicia a contagem em 1.
                    classes.append(classeAtual)
                    contagemClasses.append(1)
                else:
                    # Se a classe já estiver em 'classes', incrementa a contagem correspondente.
                    indiceClasse = classes.index(classeAtual)
                    contagemClasses[indiceClasse] += 1

            # Obter o índice da classe com maior contagem
            indiceClassePredominante = np.argmax(contagemClasses)

            # Adicionar a classe mais frequente às etiquetas previstas
            etiquetasPrevistas.append(classes[indiceClassePredominante])
            
        return etiquetasPrevistas
    

    def score(self, X, y):
        previsoesCorretas = 0

        X = np.atleast_2d(X) # Garantir que X tenha pelo menos duas dimensões.
        y = np.atleast_2d(y).reshape(-1, 1) 

        previsao = np.array(self.predict(X)).reshape(-1, 1)

        
        for i in range(len(y)):
            if previsao[i] == y[i]:   # Compara cada previsão com a verdadeira etiqueta (y[i]).
                previsoesCorretas += 1  # Se for igual, então a previsão foi correta.

        # Retorna a exatidão
        return float(previsoesCorretas / len(X)) # Número de previsões corretas a dividr pelo número total de exemplos no conjunto de teste
    
    
class NBayesClassUE:
    def __init__(self, alpha):
        # Parâmetro aditivo de suavização para o cálculo da estimativa de probabilidades (Lidstone)
        # alpha = 1 é equivalente ao estimador de Laplace
        # alpha = 0 não há suavização
        self.alpha = alpha


    def fit(self, X, y):
        self.classesUnicas = set(y) # Classes únicas presentes no conjunto de etiquetas de y.
        self.probabilidadesClasse = {} # Lista que vai ser usada para armazenar as probabilidades calculadas.

        # Calcula as probabilidades para cada classe
        for classe in self.classesUnicas:
            self.probabilidadesClasse[classe] = float(list(y).count(classe) + self.alpha) / (len(y) + (self.alpha * len(self.classesUnicas)))
        # list(y).count(classe):  Número de instâncias no conjunto de dados que pertencem à classe. nx + alpha
        # Divisor da fórmula: (len(y) + (self.alpha * len(self.classesUnicas))) número total de instâncias + alpha * número de classes únicas no conjunto de dados

        # Calcula as probabilidades para cada atributo e classe
        for i in range(len(X[0])): # O loop percorre cada índice dos atributos no conjunto de dados.
            #X[0]: comprimento da primeira dimensão de X 
            
            # Conjunto de valores únicos para o atributo i
            atributosUnicos = set(X[:, i]) # Cria um conjunto das atributos únicos na coluna i do conjunto de dados X.

            for atributo in atributosUnicos:
                for classe in self.classesUnicas:
                    quantidade = 0

                    # Contagem de instâncias onde o atributo é igual a 'atributo' e a classe é igual a 'classe'
                    for j in range(len(X)):
                        if X[j][i] == atributo and y[j] == classe:
                            quantidade += 1

                    # Cálculo da probabilidade suavizada 
                    probabilidadeSuavizada = float(quantidade + self.alpha) / (list(y).count(classe) + self.alpha * len(atributosUnicos))

                    # Armazenamento da probabilidade condicional no dicionário
                    self.probabilidadesClasse[f"[{atributo}|{classe}]"] = probabilidadeSuavizada


    # Método predict(X): aplica o modelo e devolve as etiquetas previstas para o conjunto fornecido
    # X: array com forma (nexemplos, natributos). Dados de teste;
    def predict(self, X):
        previsoes = []  # Lista para armazenar as previsões para cada instância em X

        for atributos in X:
            probabilidadeMaxima = -1  # Variável que vai guardar a maior probabilidade
            classeMaiorProbabilidade = " "  # Variável que vai guardar a classe com maior probabilidade

            # Avaliar cada classe presente 
            for classe in self.classesUnicas:
                prob = self.probabilidadesClasse[classe] # Variável prob inicializada com a probabilidade da classe

                for atributo in atributos:
                    prob = prob * self.probabilidadesClasse[f"[{atributo}|{classe}]"]
                    # Atualiza a prob, multiplicando pela probabilidade condicional do atributo dado a classe

                # Comparar e atualizar se a probabilidade atual for maior que a máxima encontrada até agora (prob e classe)
                if prob > probabilidadeMaxima:
                    probabilidadeMaxima = prob
                    classeMaiorProbabilidade = classe

            previsoes.append(classeMaiorProbabilidade)

        return previsoes



    def score(self, X, y):
        # Método score(X, y): prevê o valor associado a cada exemplo do conjunto e devolve a exatidão
        # X: array com forma (nexemplos, natributos). Dados (treino ou teste);
        # y: array com forma (nexemplos). Etiquetas (treino ou teste);

        previsoesCorretas = 0

        X = np.atleast_2d(X)  # Garantir que X tenha pelo menos duas dimensões.
        y = np.atleast_2d(y).reshape(-1, 1)

        previsao = np.array(self.predict(X)).reshape(-1, 1)

        for i in range(len(y)):
            if previsao[i] == y[i]:  # Compara cada previsão com a verdadeira etiqueta (y[i]).
                previsoesCorretas += 1  # Se for igual, então a previsão foi correta.

        # Retorna a exatidão
        return float(previsoesCorretas / len(X))  # Número de previsões corretas dividido pelo número total de exemplos no conjunto de teste


print("Escolha o classificador:")
print("1. KNeighborsClassUE")
print("2. NBayesClassUE")

# Obtém a escolha por parte do utilizador, sobre o classificador a usar
escolha = input("Digite o número correspondente ao classificador desejado: ")

if escolha == "1":
    vizinhos = 3
    potencia = 2.0
    
    vizinhosLinha = input("Digite o valor de número de vizinhos mais próximos a considerar: ")
    
    if len(vizinhosLinha) != 0:
        vizinhos = int(vizinhosLinha)
        
    potenciaLinha = input("Digite o valor de número de vizinhos mais próximos a considerar: ")
    
    if len(potenciaLinha) != 0:
        potencia = int(potenciaLinha)
    
    
    classificador = KNeighborsClassUE(vizinhos, potencia)
        
elif escolha == "2":
    alpha = 1
    
    alphaLinha = input("Digite o valor do alpha a considerar: ")
    
    if len(alphaLinha) != 0:
        alpha = float(alphaLinha)
    
    classificador = NBayesClassUE(alpha)
else:
    sys.exit("Escolha inválida.")



classificador.fit(X_train, y_train)
previsões = classificador.predict(X_test)
exatidão = classificador.score(X_test, y_test)

print("Exatidão do classificador:", exatidão)

# Ficheiro rice.csv
# desempenho KNN: K={1, 3, 5, 9}, p={1, 2}
# K = 1   P = 1     desempenho = 0.8793284365162645
# K = 3   P = 1     desempenho = 0.8982161594963274
# K = 5   P = 1     desempenho = 0.9055613850996852
# K = 9   P = 1     desempenho = 0.9045120671563484
# K = 1   P = 2     desempenho = 0.8709338929695698
# K = 3   P = 2     desempenho = 0.887722980062959
# K = 5   P = 2     desempenho = 0.9003147953830011
# K = 9   P = 2     desempenho = 0.8919202518363064

# Ficheiro iris.csv
# desempenho KNN: K={1, 3, 5, 9}, p={1, 2}
# K = 1   P = 1     desempenho = 0.9473684210526315
# K = 3   P = 1     desempenho = 0.9473684210526315
# K = 5   P = 1     desempenho = 0.9473684210526315
# K = 9   P = 1     desempenho = 0.9736842105263158
# K = 1   P = 2     desempenho = 0.9473684210526315
# K = 3   P = 2     desempenho = 0.9473684210526315
# K = 5   P = 2     desempenho = 0.9473684210526315
# K = 9   P = 2     desempenho = 0.9736842105263158

# Ficheiro wdbc.csv
# desempenho KNN: K={1, 3, 5, 9}, p={1, 2}
# K = 1   P = 1     desempenho = 0.8391608391608392
# K = 3   P = 1     desempenho = 0.8041958041958042
# K = 5   P = 1     desempenho = 0.7692307692307693
# K = 9   P = 1     desempenho = 0.7132867132867133
# K = 1   P = 2     desempenho = 0.8041958041958042
# K = 3   P = 2     desempenho = 0.8041958041958042
# K = 5   P = 2     desempenho = 0.7342657342657343
# K = 9   P = 2     desempenho = 0.6783216783216783



# Ficheiro bc-nominal.csv
# desempenho Naïve Bayes: alpha={0, 1, 3, 5}
# alfa = 0  desempenho = 0.8
# alfa = 1  desempenho = 0.8
# alfa = 3  desempenho = 0.7857142857142857
# alfa = 5  desempenho = 0.7857142857142857


# Ficheiro contact-lenses.csv
# desempenho Naïve Bayes: alpha={0, 1, 3, 5}
# alfa = 0  desempenho = 0.8333333333333334
# alfa = 1  desempenho = 1.0
# alfa = 3  desempenho = 1.0
# alfa = 5  desempenho = 1.0


# Ficheiro weather-nominal.csv
# desempenho Naïve Bayes: alpha={0, 1, 3, 5}
# alfa = 0  desempenho = 0.75
# alfa = 1  desempenho = 0.75
# alfa = 3  desempenho = 0.75
# alfa = 5  desempenho = 0.75