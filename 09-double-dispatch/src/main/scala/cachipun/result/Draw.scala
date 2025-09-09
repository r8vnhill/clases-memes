package cl.uchile.dcc
package cachipun.result

// Caso de empate. Se modela como un objeto único (singleton).
// No necesitamos almacenar información adicional: todos los empates son iguales.
// Usar un singleton evita crear instancias redundantes y simplifica comparaciones.
// En cambio, Win debe ser una clase porque necesitamos guardar qué mano ganó.
object Draw extends GameResult:
  override def toString: String = "Draw"
