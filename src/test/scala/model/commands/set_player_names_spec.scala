package model.commands

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import _root_.model.Move
import _root_.model.Card
import _root_.model.EmptyCard
import _root_.model.Player
import controller.component.controllerImpl.Controller
import model.field_component.fieldImpl.FieldInterface
import util.Observer

class set_player_names_spec extends AnyWordSpec with Matchers {
  "A controller" should {
    "when setting names" in {
      val controller = Controller(FieldInterface(slotNum = 5, players = List[Player](Player(id = 1)
        .resetAndIncreaseMana(), Player(id = 2))))
      val setPlayerNames = new SetPlayerNamesCommand(controller, Move(p1 = "Sam", p2 = "Jan"))
      val testField = controller.field
      setPlayerNames.doStep
      setPlayerNames.memento should be (testField)
    }
    "undo / redo step" in {
      val controller = Controller(FieldInterface(slotNum = 5, players = List[Player](Player(id = 1)
        .resetAndIncreaseMana(), Player(id = 2))))
      val setPlayerNames = new SetPlayerNamesCommand(controller, Move(p1 = "Sam", p2 = "Jan"))
      val testField = controller.field
      setPlayerNames.undoStep
      setPlayerNames.memento should be (testField)

      setPlayerNames.redoStep
      setPlayerNames.memento should be (testField)
    }
  }
}
