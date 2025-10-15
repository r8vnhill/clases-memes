package cl.uchile.dcc
package tamagotchi.publishers

import observer.subjects.BaseSubject
import tamagotchi.events.ValidTransition

class ValidTransitionPublisher extends BaseSubject[ValidTransition]
