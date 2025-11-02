package cl.uchile.dcc
package solid.isp

class Controller(private val _player: Player):
  def hit(): Unit =
    _player.hp = Math.max(0, _player.hp - 10)

  def player: PlayerView = _player
