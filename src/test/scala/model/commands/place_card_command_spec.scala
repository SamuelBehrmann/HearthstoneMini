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

class place_card_command_spec extends AnyWordSpec with Matchers {
  "A controller" should {
    val controller = Controller(Field(slotNum = 5, players = List[Player](Player(id = 1)
      .resetAndIncreaseMana(), Player(id = 2))))
    val placeCardCommand = new PlaceCardCommand(controller, Move(handSlot = 2, fieldSlotActive = 2))

    "do step" in {
      placeCardCommand.doStep
      placeCardCommand.memento should be (controller.field)
    }
    "undo step" in {
      placeCardCommand.undoStep
      placeCardCommand.memento should be (controller.field)
    }
    "redo step" in {
      placeCardCommand.redoStep
      placeCardCommand.memento should be (controller.field)
    }

  }
}
