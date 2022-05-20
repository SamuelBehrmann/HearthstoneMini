import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import _root_.model.Field
import _root_.model.Move
import _root_.model.Card
import _root_.model.EmptyCard
import _root_.model.Player
import controller.Controller
import model.commands.{AttackCommand, PlaceCardCommand}
import util.Observer

class attack_command_spec extends AnyWordSpec with Matchers {
  "A controller" should {
    val controller = Controller(Field(slotNum = 5, players = List[Player](Player(id = 1)
      .resetAndIncreaseMana(), Player(id = 2))))
    val attackCommand = new AttackCommand(controller, Move(fieldSlotActive = 2, fieldSlotInactive = 2))

    "do step" in {
      attackCommand.doStep
      attackCommand.memento should be (controller.field)
    }
    "undo step" in {
      attackCommand.undoStep
      attackCommand.memento should be (controller.field)
    }
    "redo step" in {
      attackCommand.redoStep
      attackCommand.memento should be (controller.field)
    }
  }
}
