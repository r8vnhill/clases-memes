package cl.uchile.dcc
package library.model

/** Clase base para Items con nombre y año. */
abstract class AbstractItem(override val name: String, override val year: Int)
    extends Item
