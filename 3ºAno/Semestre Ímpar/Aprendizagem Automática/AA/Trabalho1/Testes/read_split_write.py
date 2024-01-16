'''
Exemplo de leitura/escrita de csv e manipulação de dados
----
Um DataFrame (pandas) permite identificar exemplos e atributos por id/nome.
Também é possível identificá-los usando índices (numpy arrays)
'''

import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split


# ler ficheiro csv
data=pd.read_csv('contact-lenses.csv')
print(data.shape)
print(data.head())
print()


'''
É possível identificar um atributo através do seu nome (primeira linha do ficheiro csv).
No entanto, os nomes não podem ter o carácter '-', daí ser necessário alterar o nome da 
classe para 'contact_lenses' (no ficheiro csv) 
'''

# separar atributos da classe
X = data.drop('contact_lenses',axis=1)
y = data.contact_lenses
print(X.shape)
print(X.head())
print()
print(y.shape)
print(y.head())
print()


# criar conjs de treino e teste
X_train, X_test, y_train, y_test = train_test_split(X, y,test_size=0.25, random_state=3)
print("\nX_train:\n")
print(X_train.shape)
print(X_train.head())
print("\nX_test:\n")
print(X_test.shape)
print(X_test.head())


# juntar atributos e classe
train = pd.concat([X_train, y_train], axis=1)
print(train.shape)
print(train.head())
test = pd.concat([X_test, y_test], axis=1)
print(test.shape)
print(test.head())


# escrever novos ficheiros
train.to_csv('contact-lenses-train.csv', index=False)
test.to_csv('contact-lenses-test.csv', index=False)



'''
Também é possível usar os índices do array
'''

# separar atributos da classe
X = data.values[:, 0:-1]
y = data.values[:, -1]
print(X[:5,:])
print()
print(y[:5])
print()

# criar conjs de treino e teste
X_train, X_test, y_train, y_test = train_test_split(X, y,test_size=0.25, random_state=3)
print(X_train[:5,:])
print()
print(X_test[:5,:])
print()

# juntar atributos e classe
train = np.insert(X_train, len(X_train[0]), y_train, axis=1)
print(train[:5,:])
print()
test = np.insert(X_test, len(X_test[0]), y_test, axis=1)
print(test[:5,:])
print()
