package cl.uchile.dcc
package patternmatching

/** Ejemplo de guards de patrón sobre notificaciones */
@main def pmNotificationGuardsExample(): Unit =
  val important = Seq("867-5309", "victor.nikiforov@gmail.com")
  val text = showNotification(
    Email(
      "victor.nikiforov@gmail.com",
      "Yuuri, starting today I'm your coach.",
      "I'll make you win the Grand Prix Final."
    ),
    important
  )
  println(text)
