package cl.uchile.dcc
package singleton

class AppConfig private (val appName: String, val theme: String)
// Controla la instancia única desde el objeto compañero
object AppConfig:
  private var instance: Option[AppConfig] = None

  def getInstance: AppConfig =
    if instance.isEmpty then
      instance = Some(
          new AppConfig("CC3002", "light")
      )
    instance.get
