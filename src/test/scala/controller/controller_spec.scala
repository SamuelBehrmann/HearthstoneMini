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
    "place a card when a card gets placed" in {

      val fieldAfterMove = controller.placeCard(Move(0, 1))
      fieldAfterMove.players(0).fieldbar.cardArea.row(1) shouldBe an[Card]
    }
    "draw a card when draw is called" in {
      val fieldAfterMove = controller.drawCard(Move())
      fieldAfterMove.players(0).gamebar.hand.length should be(5)
    }
    "switch the active player on switch" in {
      val fieldAfterMove = controller.switchPlayer(Move())
      fieldAfterMove.players should be(controller.field.players.reverse)
    }
    "delete a card, and reduce healthpoints of p1 after attack" in {
      val testController = Controller(Field(slotNum = 5, players = List[Player](Player(id = 1).resetAndIncreaseMana(), Player(id = 2).resetAndIncreaseMana()))
        .placeCard(1, 1)
        .switchPlayer().placeCard(1, 1))
      val fieldAfterMove = testController.attack(Move(fieldSlotActive = 1, fieldSlotInactive = 1))
      fieldAfterMove.players(0).fieldbar.cardArea.row(1) shouldBe an[EmptyCard]
      fieldAfterMove.players(1).fieldbar.cardArea.row(1) shouldBe an[Card]
      fieldAfterMove.players(0).gamebar.friedhof.length should be(1)
      fieldAfterMove.players(1).gamebar.friedhof.length should be(0)
      fieldAfterMove.players(0).gamebar.hp.value should be(29)
      fieldAfterMove.players(1).gamebar.hp.value should be(30)
    }
    "delete a card, and reduce healthpoints of p2 after attack" in {
      val testController = Controller(Field(slotNum = 5, players = List[Player](Player(id = 1).resetAndIncreaseMana(), Player(id = 2).resetAndIncreaseMana()))
        .placeCard(1, 1))
      val fieldAfterMove = testController.attack(Move(fieldSlotActive = 1, fieldSlotInactive = 1))
      fieldAfterMove.players(0).fieldbar.cardArea.row(1) shouldBe an[Card]
      fieldAfterMove.players(1).fieldbar.cardArea.row(1) shouldBe an[EmptyCard]
      fieldAfterMove.players(0).gamebar.friedhof.length should be(0)
      fieldAfterMove.players(1).gamebar.friedhof.length should be(1)
      fieldAfterMove.players(0).gamebar.hp.value should be(30)
      fieldAfterMove.players(1).gamebar.hp.value should be(27)
    }
    "delete both cards, and reduce healthpoints NO Healthpoints after attack" in {
      val testController = Controller(Field(slotNum = 5, players = List[Player](Player(id = 1).resetAndIncreaseMana(), Player(id = 2).resetAndIncreaseMana())))
      val fieldAfterMove = testController.attack(Move(fieldSlotActive = 1, fieldSlotInactive = 1))
      fieldAfterMove.players(0).fieldbar.cardArea.row(1) shouldBe an[EmptyCard]
      fieldAfterMove.players(1).fieldbar.cardArea.row(1) shouldBe an[EmptyCard]
      fieldAfterMove.players(0).gamebar.friedhof.length should be(1)
      fieldAfterMove.players(1).gamebar.friedhof.length should be(1)
      fieldAfterMove.players(0).gamebar.hp.value should be(30)
      fieldAfterMove.players(1).gamebar.hp.value should be(30)
    }

    "leave the game on press" in {
      controller.exitGame(Move())
      controller.gameState should be(GameState.EXIT)
    }
    "set the player names" in {
      val fieldAfterMove = controller.setPlayerNames(Move(p1 = "test1", p2 = "test2"))
      fieldAfterMove.players(0).name should be("test1")
      fieldAfterMove.players(1).name should be("test2")
    }
    "notify its observers on change" in {
      class TestObserver(controller: Controller) extends Observer :
        controller.add(this)
        var bing = false

        def update = bing = true
      val testObserver = TestObserver(controller)
      testObserver.bing should be(false)
      controller.doAndPublish(controller.drawCard, Move())
      testObserver.bing should be(true)
    }

  }
}