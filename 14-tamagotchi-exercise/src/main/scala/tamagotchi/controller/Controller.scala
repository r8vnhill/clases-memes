package cl.uchile.dcc
package tamagotchi.controller

import tamagotchi.events.{InvalidTransition, ValidTransition}

import cl.uchile.dcc.observer.subjects.Subject
import cl.uchile.dcc.tamagotchi.Tamagotchi

class Controller:
  private val tamagotchi = new Tamagotchi
  tamagotchi.validTransitionPublisher.attach(new ValidTransitionHandler(this))
  tamagotchi.invalidTransitionPublisher.attach(new InvalidTransitionHandler(this))
  
  def onValidTransition(subject: Subject[ValidTransition], response: ValidTransition): Unit =
    println(s"Valid transition from ${subject.toString} to ${response.toString}")

  def onInvalidTransition(subject: Subject[InvalidTransition], response: InvalidTransition): Unit =
    println(s"Invalid transition from ${subject.toString} to ${response.toString}")
