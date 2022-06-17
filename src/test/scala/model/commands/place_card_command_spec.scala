package model.commands

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import _root_.model.Move
import _root_.model.Player
import controller.component.controllerImpl.Controller
import model.card_component.cardImpl.{Card, EmptyCard}
import model.field_component.fieldImpl.FieldInterface
import util.Observer

class place_card_command_spec extends AnyWordSpec with Matchers {
  "A controller" should {
    "do step" in {
      val controller = Controller(FieldInterface(slotNum = 5, players = List[Player](Player(id = 1)
        .resetAndIncreaseMana(), Player(id = 2))))
      val testField = controller.field
      val placeCardCommand = new PlaceCardCommand(controller, Move(handSlot = 2, fieldSlotActive = 2))
      placeCardCommand.doStep
      placeCardCommand.memento should be (testField)
    }
    "undo step" in {
      val controller = Controller(FieldInterface(slotNum = 5, players = List[Player](Player(id = 1)
        .resetAndIncreaseMana(), Player(id = 2))))
      val testField = controller.field
      val placeCardCommand = new PlaceCardCommand(controller, Move(handSlot = 2, fieldSlotActive = 2))
      placeCardCommand.undoStep
      placeCardCommand.memento should be (testField)
    }
    "redo step" in {
      val controller = Controller(FieldInterface(slotNum = 5, players = List[Player](Player(id = 1)
        .resetAndIncreaseMana(), Player(id = 2))))
      val testField = controller.field
      val placeCardCommand = new PlaceCardCommand(controller, Move(handSlot = 2, fieldSlotActive = 2))
      placeCardCommand.redoStep
      placeCardCommand.memento should be (testField)
    }

  }
}
