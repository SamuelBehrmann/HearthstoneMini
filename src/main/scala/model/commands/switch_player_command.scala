package model.commands

import model.Move
import model.Field
import controller.Controller
import util.Command

class SwitchPlayerCommand(controller: Controller) extends Command {
  var memento: Field = controller.field
  override def doStep: Unit = {
    memento = controller.field
    controller.field = controller.field.switchPlayer()
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