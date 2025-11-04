package cl.uchile.dcc
package patternmatching

/** Coincidencia de patrones en tipos (por clase concreta) */
sealed trait Device
case class Phone(model: String) extends Device:
  def screenOff = "Turning screen off"
case class Computer(model: String) extends Device:
  def screenSaverOn = "Turning screen saver on..."

/** Match por tipo dinámico */
def goIdle(device: Device): String =
  device match
    case p: Phone    => p.screenOff
    case c: Computer => c.screenSaverOn

@main def pmDeviceExample(): Unit =
  println(goIdle(Phone("iPhone")))
  println(goIdle(Computer("ThinkPad")))
