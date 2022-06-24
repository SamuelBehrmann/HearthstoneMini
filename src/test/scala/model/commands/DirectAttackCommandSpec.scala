import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import controller.component.controllerImpl.Controller
import model.commands.{AttackCommand, PlaceCardCommand}
import util.Observer
import model.commands.DirectAttackCommand
import model.cardComponent.cardImpl.{Card, EmptyCard}

import model.gamebarComponent.gamebarImpl.Gamebar
import model.manaComponent.manaImpl.Mana
import model.playerComponent.playerImpl
import model.playerComponent.playerImpl.Player
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldImpl.Field
import model.Move

class DirectAttackCommandSpec extends AnyWordSpec with Matchers {
  "A controller" should {
    val controller = Controller(Field(slotNum = 5, players = List[Player](playerImpl.Player(id = 1, gamebar = Gamebar(mana = Mana(100,100)))
      , Player(id = 2))))

    controller.placeCard(Move(handSlot = 0, fieldSlotActive = 0))
    controller.switchPlayer()
    controller.switchPlayer()
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
