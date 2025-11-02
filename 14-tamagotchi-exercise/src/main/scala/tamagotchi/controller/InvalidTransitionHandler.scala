package cl.uchile.dcc
package tamagotchi.controller

import observer.observers.Observer
import tamagotchi.events.InvalidTransition

import cl.uchile.dcc.observer.subjects.Subject

class InvalidTransitionHandler(controller: Controller)
    extends Observer[InvalidTransition]:
  override def update(subject: Subject[InvalidTransition],
                      response: InvalidTransition
  ): Unit =
    controller.onInvalidTransition(subject, response)
