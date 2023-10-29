import numpy as np

# A = np.array([[1, 2, 3], [2, 5, 3], [1, 0, 8]])
# A_inv = np.linalg.inv(A)
# print(A_inv)

A = np.array([[4, 1, -3], [1, 3, 0], [0, 1, 3]])
B = np.array([4, 3, -2])

AB_solve = np.linalg.solve(A, B)

print(AB_solve)

C = np.array([[-2, -6, -4, 0], [2, 3, 1, 2], [-3, 2, 3, 1], [1, 3, 2, 9]])
D = np.array([0, 0, 1, 0])

CD_solve = np.linalg.solve(C, D)

print(CD_solve)

E = np.array([[2, 1, 0], [1, -1, 1]])
F = np.array([[1, 2], [0, 0], [-1, 1]])

EF_solve = E @ F

print(EF_solve)


G = np.array([[1, -2, 1], [0, 2, -8], [5, 0, -5, ]])

G_solve = np.linalg.inv(G)

print(G_solve)


E = np.array([[1, -2, 1], [0, 2, -8], [5, 0, -5]])

E_solve = np.linalg.matrix_rank(E)

print(E_solve)
