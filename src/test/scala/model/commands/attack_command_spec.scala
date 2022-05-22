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
import model.GameBar
import model.Mana

class attack_command_spec extends AnyWordSpec with Matchers {
  val testCards = List[Card](Card("test1", 1, 1, 1, "testEffect1", "testRarety1"),
    Card("test1", 1, 10, 1, "testEffect1", "testRarety1"), Card("test1", 1, 1, 1, "testEffect1", "testRarety1"),
    Card("test1", 1, 1, 20, "testEffect1", "testRarety1"))

  "A controller" should {
    val controller = Controller(Field(slotNum = 5, players = List[Player](Player(id = 1, gamebar = GameBar(hand = testCards, mana = Mana(100,100)))
      , Player(id = 2, gamebar = GameBar(hand = testCards, mana = Mana(100,100))))))
      controller.placeCard(Move(3,1))
      controller.placeCard(Move(0,2))
      controller.switchPlayer()
      controller.placeCard(Move(1,1))
      controller.placeCard(Move(0,2))
    val attackCommand = new AttackCommand(controller, Move(fieldSlotActive = 1, fieldSlotInactive = 2))
    val attackCommandEven = new AttackCommand(controller, Move(fieldSlotActive = 2, fieldSlotInactive = 2))
    val attackCommandLoss = new AttackCommand(controller, Move(fieldSlotActive = 2, fieldSlotInactive = 1))

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
    "do even step" in {
      val field = controller.field
      attackCommandEven.doStep
      attackCommandEven.memento should be (field)
    }
    "undo even step" in {
      val field = controller.field
      attackCommand.undoStep
      attackCommand.memento should be (field)
    }
     "do loss step" in {
      val field = controller.field
      attackCommandLoss.doStep
      attackCommandLoss.memento should be (field)
    }
    "redo step" in {
      val field = controller.field
      attackCommand.redoStep
      attackCommand.memento should be (field)
    }
  }
}
