package model.commands

import controller.component.controllerImpl.Controller
import model.Move
import model.field_component.FieldInterface
import util.Command

class SetPlayerNamesCommand(controller: Controller, move: Move) extends Command {
  var memento: FieldInterface = controller.field
  override def doStep: Unit = {
    memento = controller.field
    controller.field = controller.field.setPlayerNames(move.p1, move.p2)
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