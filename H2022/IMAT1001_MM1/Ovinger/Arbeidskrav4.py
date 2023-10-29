import numpy as np
from scipy.integrate import quad
import matplotlib.pyplot as plt


def f(x):
    return x**4


# res = quad(f, -2, 2)

x = np.linspace(-4, 4, 1000)
plt.plot(x, f(x))
plt.axvline(-3)
plt.axvline(3)
plt.axhline(81)
print(f(-3))
print(f(3))
plt.show()
