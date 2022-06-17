import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import _root_.model.Move
import _root_.model.Card
import _root_.model.EmptyCard
import controller.component.controllerImpl.Controller
import model.commands.{AttackCommand, PlaceCardCommand}
import util.Observer
import model.commands.DirectAttackCommand
import model.field_component.fieldImpl.FieldInterface
import model.gamebar_component.GameBarImpl.GameBar
import model.mana_component.manaImpl.Mana
import model.player_component.playerImpl
import model.player_component.playerImpl.Player

class DirectAttackCommandSpec extends AnyWordSpec with Matchers {
  "A controller" should {
    val controller = Controller(FieldInterface(slotNum = 5, players = List[Player](playerImpl.Player(id = 1, gamebar = GameBar(mana = Mana(100,100)))
      , Player(id = 2))))

    controller.placeCard(Move(handSlot = 0, fieldSlotActive = 0))
    val field = controller.field
    val directAttackCommand = new DirectAttackCommand(controller, Move(fieldSlotActive = 0))
    "do step" in {
      directAttackCommand.doStep
      directAttackCommand.memento should be (field)
    }
    "undo step" in {
      val field = controller.field
      directAttackCommand.undoStep
      directAttackCommand.memento should be (field)
    }
    "redo step" in {
      directAttackCommand.redoStep
      directAttackCommand.memento should be (field)
    }
  }
}
