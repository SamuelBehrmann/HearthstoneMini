package controller

package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import _root_.model.Field
import _root_.model.Move
import _root_.model.Card
import _root_.model.EmptyCard
import _root_.model.Player
import _root_.model.GameBar
import util.Observer

class ControllerSpec extends AnyWordSpec with Matchers {
  val testCards = List[Card](Card("test1", 1, 1, 1, "testEffect1", "testRarety1"),
        Card("test1", 1, 1, 1, "testEffect1", "testRarety1"), Card("test1", 1, 1, 1, "testEffect1", "testRarety1"),
        Card("test1", 1, 1, 1, "testEffect1", "testRarety1"))

  "The Controller" should {
    val controller = Controller(Field(slotNum = 5,
      players = List[Player](Player(id = 1,
      gamebar = GameBar(hand = testCards)).resetAndIncreaseMana(),
      Player(id = 2))))
    "have a default gametstate of GameState.PREGAME" in {
      controller.gameState should be(GameState.CHOOSEMODE)
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
    "do a direct attack" in {
      controller.placeCard(Move(2, 2))
      controller.directAttack(Move(fieldSlotActive = 2))
      controller.field.players(1).gamebar.hp.value should be(29)
    }
    "undo step / redo step" in {
      controller.drawCard()
      controller.undo
      controller.field.players(0).gamebar.hand.length should be(4)
      controller.redo
      controller.field.players(0).gamebar.hand.length should be(5)
    }
    "setStrategy should set a strategy based on input" in {
      controller.setStrategy(Strategy.adminStrategy())
      controller.field.getPlayerById(1).gamebar.hp.value should be (100)
      controller.field.getPlayerById(1).gamebar.mana.value should be (100)
    }
    "should set gamestate to Exit" in {
      controller.exitGame()
      controller.gameState should be(GameState.EXIT)
    }
  }
}