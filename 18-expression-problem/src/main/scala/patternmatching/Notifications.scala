package cl.uchile.dcc
package patternmatching

/** Por qué Visitor vs pattern matching:
  * - Algunos lenguajes OO no tienen pattern matching; Visitor simula un "dispatch" doble.
  * - Aquí mostramos pattern matching directo para contrastar con Visitor.
  */
import scala.util.Random

// Coincidencia de patrones en clases
sealed trait Notification
case class Email(sender: String, title: String, body: String)
    extends Notification
case class SMS(caller: String, message: String) extends Notification
case class VoiceRecording(contactName: String, link: String)
    extends Notification

/** Devuelve una descripción según el tipo de notificación */
def showNotification(notification: Notification): String =
  notification match
    case Email(sender, title, _)       => s"Email from $sender: $title"
    case SMS(number, message)          => s"SMS from $number: $message"
    case VoiceRecording(name, link)    => s"Voice msg from $name: $link"

/** Variante con guards de patrón para notificaciones importantes */
def showNotification(notification: Notification, important: Seq[String]): String =
  notification match
    case Email(sender, _, _) if important.contains(sender) =>
      "Email from someone special!"
    case SMS(number, _) if important.contains(number) =>
      "SMS from someone special!"
    case other =>
      // Volvemos a la implementación base (reutilización)
      showNotification(other)

@main def pmNotificationExample(): Unit =
  val msg = showNotification(SMS("12345", "Are you there?"))
  println(msg)
