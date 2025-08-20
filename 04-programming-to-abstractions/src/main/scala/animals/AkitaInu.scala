package cl.uchile.dcc
package animals

import java.awt.Color

class AkitaInu(val name: String) extends Legged with Tailed:
  override val numLegs: Int = 4 // From Legged

  override def walk(): Unit = // From Legged
    println(s"$name is walking on $numLegs legs.")

  override val tailColor: Color = // From Tailed
    Color.WHITE
