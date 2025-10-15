package cl.uchile.dcc
package tamagotchi.controller

import observer.observers.Observer
import tamagotchi.events.ValidTransition

import cl.uchile.dcc.observer.subjects.Subject

class ValidTransitionHandler(controller: Controller) extends Observer[ValidTransition]:
  override def update(subject: Subject[ValidTransition], response: ValidTransition): Unit =
    controller.onValidTransition(subject, response)
