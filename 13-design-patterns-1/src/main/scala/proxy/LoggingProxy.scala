package cl.uchile.dcc
package proxy

class LoggingProxy extends Service:
  private val realService = new RealService()

  override def request(): Unit =
    println("[PRE] Iniciando operación...")
    realService.request()
    println("[POST] Operación completada.")

@main def loggingProxyMain(): Unit =
  val proxy: Service = new LoggingProxy()
  proxy.request()
