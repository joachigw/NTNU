import numpy as np
import sympy as sym

# Oppgave 3
A = np.zeros((5, 5))
b = np.array([[1], [0], [0], [0], [3]])


for i in range(5):
    for j in range(5):
        A[i, j] = (i+1)**(4-j)

detA = np.linalg.det(A)

B = A.copy()

for i in range(5):
    A[i, 0] = b[i]

print(np.linalg.det(A))
print(np.linalg.det(A)/np.linalg.det(B))


# Oppgave 8
x = sym.var('x')
h = (10**-8)


def g(x):
    return (sym.sqrt(1+np.abs(x)))


def f(x, h):
    return (sym.sqrt(1+np.abs(x+h)))


print((f(-2, h) - g(-2))/h)
