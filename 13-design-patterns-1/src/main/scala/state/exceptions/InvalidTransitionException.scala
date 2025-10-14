package cl.uchile.dcc
package state.exceptions

class InvalidTransitionException(action: String, state: String)
  extends Exception(
    s"Invalid transition: cannot '$action' when in state '$state'")
