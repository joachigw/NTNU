import numpy as np

a = 0
b = 2
n = 20
h = (b - a) / (n - 1)
x = np.linspace(a, b, n)
f = (x**2 + 2) - (2*x**2 - 2)

I_trap = (h/2)*(f[0] +
                2 * sum(f[1:n-1]) + f[n-1])
err_trap = 2 - I_trap

print(I_trap)
print(err_trap)
