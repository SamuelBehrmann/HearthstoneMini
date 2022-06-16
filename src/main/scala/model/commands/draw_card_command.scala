package model.commands

import controller.component.controllerImpl.Controller
import model.Move
import model.field_component.fieldImpl.FieldInterface
import util.Command

class DrawCardCommand(controller: Controller) extends Command {
  var memento: FieldInterface = controller.field
  override def doStep: Unit = {
    memento = controller.field
    controller.field = controller.field.drawCard()
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