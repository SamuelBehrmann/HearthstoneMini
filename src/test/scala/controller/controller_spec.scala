package controller

package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import _root_.model.Field
import _root_.model.Move
import _root_.model.Card
import _root_.model.EmptyCard
import util.Observer

class ControllerSpec extends AnyWordSpec with Matchers {
  "The Controller" should {
    val controller = Controller(new Field(5, "Sam", "Jan"))
    "place a card when a card gets placed" in {
        val fieldAfterMove = controller.placeCard(Move(0, 1))
        fieldAfterMove.players(0).fieldbar.cardArea.row(1) shouldBe an [Card]
    }
    "remove a card when a card gets removed" in {
        val fieldAfterMove0 = controller.placeCard(Move(0, 1, 0))
        fieldAfterMove0.players(0).fieldbar.cardArea.row(1) shouldBe an [Card]
        val fieldAfterMove = controller.destroyCard(Move(0, 0, 0))
        fieldAfterMove.players(0).fieldbar.cardArea.row(0) shouldBe an [EmptyCard]
    }
    "draw a card from deck when a card gets drawn" in {
        val fieldAfterMove = controller.drawCard(Move(0))
        fieldAfterMove.players(0).gamebar.hand.length should be(5)
        fieldAfterMove.players(0).gamebar.deck.length should be(3)
    }
    "increase the Healthpoints when the Healthpoints gets increased" in {
        val fieldAfterMove = controller.increaseHp(Move(0, 0, 20))
        fieldAfterMove.players(0).gamebar.hp.value should be(120)
    }
    "decrease the Healthpoints when the Healthpoints gets decreased" in {
        val fieldAfterMove = controller.reduceHp(Move(0, 0, 20))
        fieldAfterMove.players(0).gamebar.hp.value should be(80)
    }
    "increase the Mana when the Mana gets increased" in {
        val fieldAfterMove = controller.increaseMana(Move(0, 0, 20))
        fieldAfterMove.players(0).gamebar.mana.value should be(30)
    }
    "decrease the Mana when the Mana gets decreased" in {
        val fieldAfterMove = controller.reduceMana(Move(0, 0, 20))
        fieldAfterMove.players(0).gamebar.mana.value should be(-10)
    }
    "switch the active player on switch" in {
        val fieldAfterMove = controller.switchPlayer(Move())
        fieldAfterMove should be (controller.field)
        controller.player should be (1)
    }
    "leave the game on press" in {
        controller.doExit should be(false)
        val fieldAfterMove = controller.exitGame(Move())
        fieldAfterMove should be (controller.field)
        controller.doExit should be(true)
    }
    "set the player names" in {
        val fieldAfterMove = controller.setPlayerNames(Move(p1 = "test1", p2 = "test2"))
        fieldAfterMove.players(0).name should be("test1")
        fieldAfterMove.players(1).name should be("test2")
    }
     "notify its observers on change" in {
      class TestObserver(controller: Controller) extends Observer:
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