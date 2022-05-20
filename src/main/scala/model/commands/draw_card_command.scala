package model.commands

import model.Move
import model.Field
import controller.Controller
import util.Command

class DrawCardCommand(controller: Controller) extends Command {
  var memento: Field = controller.field
  override def doStep: Unit = {
    controller.field = controller.field.drawCard()
    memento = controller.field
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