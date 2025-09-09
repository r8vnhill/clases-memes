package cl.uchile.dcc
package ocp

// Un círculo conoce su propia fórmula de área.
// Si agregamos una nueva figura, no modificamos código existente → OCP.
class Circle(val radius: Double) extends Shape:
  override val area: Double = math.Pi * math.pow(radius, 2)
