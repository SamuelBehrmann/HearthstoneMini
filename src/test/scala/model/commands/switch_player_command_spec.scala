
package model.commands

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import _root_.model.Move
<<<<<<< HEAD
import _root_.model.Card
import _root_.model.EmptyCard
=======
import _root_.model.Player
>>>>>>> fix/components
import controller.component.controllerImpl.Controller
import model.card_component.cardImpl.{Card, EmptyCard}
import model.field_component.fieldImpl.FieldInterface
import model.player_component.playerImpl.Player
import util.Observer

class SwitchPlayerSpec extends AnyWordSpec with Matchers {
  "A controller" should {
    "when switching players" in {
      val controller = Controller(FieldInterface(slotNum = 5, players = List[Player](Player(id = 1)
        .resetAndIncreaseMana(), Player(id = 2))))
      val switchPlayer = new SwitchPlayerCommand(controller)
      val testField = controller.field
      switchPlayer.doStep
      switchPlayer.memento should be (testField)
    }
    "undo / redo step" in {
      val controller = Controller(FieldInterface(slotNum = 5, players = List[Player](Player(id = 1)
        .resetAndIncreaseMana(), Player(id = 2))))
      val switchPlayer = new SwitchPlayerCommand(controller)
      val testField = controller.field
      switchPlayer.undoStep
      switchPlayer.memento should be (testField)

      switchPlayer.redoStep
      switchPlayer.memento should be (testField)
    }
  }
}
