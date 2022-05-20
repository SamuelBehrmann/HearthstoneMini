package controller

package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import _root_.model.Field
import _root_.model.Move
import _root_.model.Card
import _root_.model.EmptyCard
import _root_.model.Player
import util.Observer


class ControllerSpec extends AnyWordSpec with Matchers {
  "The Controller" should {
    val controller = Controller(Field(slotNum = 5, players = List[Player](Player(id = 1).resetAndIncreaseMana(),Player(id = 2))))
    "have a default gametstate of GameState.PREGAME" in {
      controller.gameState should be(GameState.PREGAME)
    }
  }
}