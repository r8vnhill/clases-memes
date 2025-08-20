package cl.uchile.dcc
package phone

trait Camera:
  def takePhoto(): Unit

trait Phone:
  def makeCall(number: String): Unit

// A Smartphone extends both capabilities
trait Smartphone extends Camera with Phone
