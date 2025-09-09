package cl.uchile.dcc
package cachipun.play

// Clase base común para las manos del juego.
// Define toString para mostrar el nombre de la jugada.
abstract class AbstractHand extends Hand:
  override def toString: String =
    getClass.getSimpleName
