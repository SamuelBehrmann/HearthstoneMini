
package model.commands

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import _root_.model.Field
import _root_.model.Move
import _root_.model.Card
import _root_.model.EmptyCard
import _root_.model.Player
import controller.Controller
import util.Observer

class SwitchPlayerSpec extends AnyWordSpec with Matchers {
  "A controller" should {
    "when switching players" in {
      val controller = Controller(Field(slotNum = 5, players = List[Player](Player(id = 1)
        .resetAndIncreaseMana(), Player(id = 2))))
      val switchPlayer = new SwitchPlayerCommand(controller)
      val testField = controller.field
      switchPlayer.doStep
      switchPlayer.memento should be (testField)
    }
    "undo / redo step" in {
      val controller = Controller(Field(slotNum = 5, players = List[Player](Player(id = 1)
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
