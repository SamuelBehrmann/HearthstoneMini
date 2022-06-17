package model.commands

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import controller.component.controllerImpl.Controller
import model.card_component.cardImpl.{Card, EmptyCard}

import model.player_component.playerImpl.Player
import util.Observer
import model.field_component.FieldInterface
import model.Move
import model.field_component.fieldImpl.Field

class set_player_names_spec extends AnyWordSpec with Matchers {
  "A controller" should {
    "when setting names" in {
      val controller = Controller(Field(slotNum = 5, players = List[Player](Player(id = 1)
        .resetAndIncreaseMana(), Player(id = 2))))
      val setPlayerNames = new SetPlayerNamesCommand(controller, Move(p1 = "Sam", p2 = "Jan"))
      val testField = controller.field
      setPlayerNames.doStep
      setPlayerNames.memento should be (testField)
    }
    "undo / redo step" in {
      val controller = Controller(Field(slotNum = 5, players = List[Player](Player(id = 1)
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
