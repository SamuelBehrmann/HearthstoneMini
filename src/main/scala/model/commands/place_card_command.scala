package model.commands

import model.Move
import model.Field
import controller.Controller
import util.Command

class PlaceCardCommand(controller: Controller, move: Move) extends Command {
  var memento: Field = controller.field
  override def doStep: Unit = {
    memento = controller.field
    controller.field = controller.field.placeCard(move.handSlot, move.fieldSlotActive)
  }
  override def undoStep: Unit = {
    val new_memento = controller.field
    controller.field = memento
    memento = new_memento  
  }

  override def redoStep: Unit = {
    val new_memento = controller.field
    controller.field = memento
    memento = new_memento
  }
}