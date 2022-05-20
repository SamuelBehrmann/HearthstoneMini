package model.commands

import model.Move
import model.Field
import controller.Controller
import util.Command

class AttackCommand(controller: Controller, move: Move) extends Command {
  var memento: Field = controller.field
  override def doStep: Unit = {
    val difference = Math.abs(controller.field.players(0).fieldbar.cardArea.slot(move.fieldSlotActive).attValue - controller.field.players(1).fieldbar.cardArea.slot(move.fieldSlotInactive).defenseValue)
    if(controller.field.players(0).fieldbar.cardArea.slot(move.fieldSlotActive).attValue < controller.field.players(1).fieldbar.cardArea.slot(move.fieldSlotInactive).defenseValue) then
      controller.field = controller.field.destroyCard(0, move.fieldSlotActive).reduceHp(0, difference)
      memento = controller.field
    else if(controller.field.players(0).fieldbar.cardArea.slot(move.fieldSlotActive).attValue > controller.field.players(1).fieldbar.cardArea.slot(move.fieldSlotInactive).defenseValue) then
      controller.field = controller.field.destroyCard(1, move.fieldSlotInactive).reduceHp(1, difference)
      memento = controller.field
    else 
      controller.field = controller.field.destroyCard(0, move.fieldSlotActive).destroyCard(1, move.fieldSlotInactive)
      memento = controller.field
  }
  override def undoStep: Unit = {
    val new_memmento = controller.field
    controller.field = memento
    memento = new_memmento  
  }

  override def redoStep: Unit = {
    val new_memento = controller.field
    controller.field = memento
    memento = new_memento
  }
}