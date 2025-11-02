package cl.uchile.dcc
package liskov

class TemperatureSensor:
  def readCelsius(): Option[Int] = Some(22)

class HttpTemperatureSensor extends TemperatureSensor:
  override def readCelsius(): Option[Nothing] =
    None

def averageTemperature(sensors: List[TemperatureSensor]): Int =
  var sum = 0
  for sensor <- sensors do
    sum += sensor
      .readCelsius()
      // Necesitamos manejar el caso excepcional explícitamente
      .getOrElse(0)
  sum / sensors.length

@main def main(): Unit =
  val sensors: List[TemperatureSensor] =
    List(new TemperatureSensor, new HttpTemperatureSensor)

  println(averageTemperature(sensors))
