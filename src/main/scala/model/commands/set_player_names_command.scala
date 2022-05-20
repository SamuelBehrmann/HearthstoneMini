package model.commands

import model.Move
import controller.Controller
import util.Command
import model.Field

class SetPlayerNamesCommand(controller: Controller, move: Move) extends Command {
  var memento: Field = controller.field
  override def doStep: Unit = {
    controller.field = controller.field.setPlayerNames(move.p1, move.p2)
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