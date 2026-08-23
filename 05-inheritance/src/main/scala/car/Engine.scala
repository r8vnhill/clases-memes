package cl.uchile.dcc

package car

/**
 * Representa el motor de un auto.
 *
 * Esta clase se utiliza para ilustrar una relación de '''composición'''.
 * En una composición distinguimos:
 *
 *   - un objeto que representa el '''todo''', por ejemplo `Car`;
 *   - objetos que representan sus '''partes''', como [[Engine]].
 *
 * La idea importante es que el motor forma parte de la representación del
 * auto y es administrado por este, en lugar de ser simplemente un objeto
 * que el auto utiliza ocasionalmente.
 *
 * La clase `Engine` por sí sola no establece la relación de composición:
 * esta se hace visible cuando `Car` contiene y posee una instancia de
 * `Engine`.
 */
class Engine:

  /**
   * Simula el encendido del motor.
   *
   * @return
   *   un mensaje indicando que el motor fue encendido.
   */
  def start(): String =
    "Engine started"
