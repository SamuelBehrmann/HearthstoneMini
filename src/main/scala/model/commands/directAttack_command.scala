package model.commands

import model.Move
import controller.GameState
import controller.component.controllerImpl.Controller
import util.Command
import model.Card
import model.field_component.fieldImpl.FieldInterface

class DirectAttackCommand(controller: Controller, move: Move) extends Command {
  var memento: FieldInterface = controller.field
  override def doStep: Unit = {
    if (controller.field.players(0).fieldbar.cardArea.slot(move.fieldSlotActive).isDefined) then 
      {
        if !(controller.field.players(1).fieldbar.cardArea.row.count(_.isDefined) > 0 ) then {
          memento = controller.field
          controller.field = controller.field.reduceHp(1, controller.field.players(0).fieldbar.cardArea.slot(move.fieldSlotActive).get.attValue)
          if (controller.field.players(0).gamebar.hp.isEmpty() || controller.field.players(1).gamebar.hp.isEmpty()) then controller.gameState =  GameState.WIN
        } 
    }   
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