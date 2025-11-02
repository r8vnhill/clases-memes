package cl.uchile.dcc
package singleton

class DatabaseConnection private (val driver: String, val url: String)
// Controla la instancia única desde el objeto compañero
object DatabaseConnection:
  private var instance: Option[DatabaseConnection] = None

  def getInstance: DatabaseConnection =
    if instance.isEmpty then
      instance = Some(
          new DatabaseConnection("org.postgresql.Driver",
                                 "jdbc:postgresql://localhost:5432/test"
          )
      )
    instance.get
