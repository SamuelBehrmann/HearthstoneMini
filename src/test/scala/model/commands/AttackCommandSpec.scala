import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import controller.component.controllerImpl.Controller
import model.commands.{AttackCommand, PlaceCardCommand}
import util.Observer
import controller.component.controllerImpl.Controller
import model.commands.{AttackCommand, PlaceCardCommand}
import util.Observer
import model.cardComponent.cardImpl.{Card, EmptyCard}
import model.gamebarComponent.gamebarImpl.Gamebar
import model.manaComponent.manaImpl.Mana
import model.playerComponent.playerImpl
import model.playerComponent.playerImpl.Player
import model.fieldComponent.fieldImpl.Field
import model.Move
import model.healthpointsComponent.healthpointsImpl.Healthpoints

class AttackCommandSpec extends AnyWordSpec with Matchers {
  val testCards = List[Card](Card("test1", 1, 1, 1, "testEffect1", "testRarety1"),
    Card("test1", 1, 10, 1, "testEffect1", "testRarety1"), Card("test1", 1, 1, 1, "testEffect1", "testRarety1"),
    Card("test1", 1, 1, 20, "testEffect1", "testRarety1"))

  "A controller" should {
    val controller = Controller(Field(slotNum = 5, players = List[Player](playerImpl.Player(id = 1, gamebar = Gamebar(hand = testCards, hp = Healthpoints(1,1), mana = Mana(100,100)))
      , playerImpl.Player(id = 2, gamebar = Gamebar(hand = testCards, hp = Healthpoints(1,1),mana = Mana(100,100))))))
      controller.placeCard(Move(1,1))
      controller.switchPlayer()
      controller.placeCard(Move(0,1))
      controller.switchPlayer()
    val attackCommand = new AttackCommand(controller, Move(fieldSlotActive = 1, fieldSlotInactive = 1))

    "do step" in {
      val field = controller.field
      attackCommand.doStep
      attackCommand.memento should be (field)
    }
    "undo step" in {
      val field = controller.field
      attackCommand.undoStep
      attackCommand.memento should be (field)
    }
    "redo step" in {
      val field = controller.field
      attackCommand.redoStep
      attackCommand.memento should be (field)
    }
  }
}
