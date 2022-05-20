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

class set_player_names_spec extends AnyWordSpec with Matchers {
  "A controller" should {
    val controller = Controller(Field(slotNum = 5, players = List[Player](Player(id = 1)
        .resetAndIncreaseMana(), Player(id = 2))))
    val setPlayerNames = new SetPlayerNamesCommand(controller, Move(p1 = "Sam", p2 = "Jan"))

    "when setting names" in {
      setPlayerNames.doStep
      setPlayerNames.memento should be (controller.field)
    }
    "undo / redo step" in {
      setPlayerNames.undoStep
      setPlayerNames.memento should be (controller.field)

      setPlayerNames.redoStep
      setPlayerNames.memento should be (controller.field)
    }
  }
}
