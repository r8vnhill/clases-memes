package cl.uchile.dcc
package tamagotchi.publishers

import observer.subjects.BaseSubject
import tamagotchi.events.InvalidTransition

class InvalidTransitionPublisher extends BaseSubject[InvalidTransition]
