package model.commands

import model.Move
import model.Field
import controller.Controller
import util.Command

class AttackCommand(controller: Controller, move: Move) extends Command {
  var memento: Field = controller.field
  override def doStep: Unit = {
    if (controller.field.players(0).fieldbar.cardArea.slot(move.fieldSlotActive).isDefined && controller.field.players(1).fieldbar.cardArea.slot(move.fieldSlotInactive).isDefined)
    then {
      val difference = Math.abs(controller.field.players(0).fieldbar.cardArea.slot(move.fieldSlotActive).get.attValue - controller.field.players(1).fieldbar.cardArea.slot(move.fieldSlotInactive).get.defenseValue)
      if(controller.field.players(0).fieldbar.cardArea.slot(move.fieldSlotActive).get.attValue < controller.field.players(1).fieldbar.cardArea.slot(move.fieldSlotInactive).get.defenseValue) then
        controller.field = controller.field.destroyCard(0, move.fieldSlotActive).reduceHp(0, difference)
        memento = controller.field
      else if(controller.field.players(0).fieldbar.cardArea.slot(move.fieldSlotActive).get.attValue > controller.field.players(1).fieldbar.cardArea.slot(move.fieldSlotInactive).get.defenseValue) then
        controller.field = controller.field.destroyCard(1, move.fieldSlotInactive).reduceHp(1, difference)
        memento = controller.field
      else 
        memento = controller.field
        controller.field = controller.field.destroyCard(0, move.fieldSlotActive).destroyCard(1, move.fieldSlotInactive)
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