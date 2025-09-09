package cl.uchile.dcc
package ocp

// Shape define la interfaz común: cada figura sabe calcular su área.
// Gracias al polimorfismo, no necesitamos if/else ni type checks.
trait Shape:
  val area: Double
