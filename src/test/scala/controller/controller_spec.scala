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
    val controller = Controller(Field(slotNum = 5, players = List[Player](Player(id = 1).resetAndIncreaseMana(), Player(id = 2))))
    "have a default gametstate of GameState.PREGAME" in {
      controller.gameState should be(GameState.PREGAME)

    }
    "place a card on field" in {
      controller.placeCard(Move(2, 2))
      controller.field.players(0).fieldbar.cardArea.row(2).isDefined should be(true)
    }
    "draw a card" in {
      controller.drawCard()
      controller.field.players(0).gamebar.hand.length should be(4)
    }
    "setting player names" in {
      controller.setPlayerNames(Move(p1 = "Jan", p2 = "Sam"))
      controller.field.players(0).name should be("Jan")
      controller.field.players(1).name should be("Sam")
    }
    "attacking" in {
      controller.placeCard(Move(2, 2))
      controller.switchPlayer()
      controller.placeCard(Move(2, 2))
      controller.attack(Move(fieldSlotActive = 2, fieldSlotInactive = 2))
      controller.field.players(0).fieldbar.cardArea.row(2).isEmpty should be(true)
    }
    "switching player" in {
      controller.setPlayerNames(Move(p2 = "Sam"))
      controller.switchPlayer()
      controller.field.players(0).name should be("Sam")
    }
    "exit the game" in {
      controller.exitGame()
      controller.gameState should be(GameState.EXIT)
    }
    "undo step / redo step" in {
      controller.drawCard()
      controller.undo
      controller.field.players(0).gamebar.hand.length should be(4)
      controller.redo
      controller.field.players(0).gamebar.hand.length should be(5)
    }
  }
}