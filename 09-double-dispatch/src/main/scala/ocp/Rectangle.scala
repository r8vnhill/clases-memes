package cl.uchile.dcc
package ocp

// Un rectángulo también implementa Shape con su propia fórmula.
// Cada clase encapsula su comportamiento.
class Rectangle(val width: Double, val height: Double) extends Shape:
  override val area: Double = width * height
