# Problemas comunues y soluciones

| Problema     | Causa   | Solución
|------------| ------ |-----
| mensaje se imprime con basura | se usa new String(buffer) sin indicar longiutd | usar new Stirng(buffer, 0, leidos)
