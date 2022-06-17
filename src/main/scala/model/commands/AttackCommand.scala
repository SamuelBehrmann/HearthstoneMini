package model.commands

import model.Move
import controller.GameState
import controller.component.controllerImpl.Controller
import model.fieldComponent.FieldInterface
import util.Command
import scala.util.{Failure, Success, Try}

class AttackCommand(controller: Controller, move: Move) extends Command {
  var memento: FieldInterface = controller.field
  var newField: FieldInterface = null

  override def doStep: Try[FieldInterface] = {
    if checkConditions then {
      val difference = Math.abs(controller.field.players.head.fieldbar.cardArea.slot(move.fieldSlotActive).get.attValue
        - controller.field.players(1).fieldbar.cardArea.slot(move.fieldSlotInactive).get.defenseValue)
      if controller.field.players.head.fieldbar.cardArea.slot(move.fieldSlotActive).get.attValue
        < controller.field.players(1).fieldbar.cardArea.slot(move.fieldSlotInactive).get.defenseValue then
        memento = controller.field
        newField = controller.field.destroyCard(0, move.fieldSlotActive).reduceHp(0, difference)
      else if controller.field.players.head.fieldbar.cardArea.slot(move.fieldSlotActive).get.attValue
        > controller.field.players(1).fieldbar.cardArea.slot(move.fieldSlotInactive).get.defenseValue then
        memento = controller.field
        newField = controller.field.destroyCard(1, move.fieldSlotInactive).reduceHp(1, difference)
      else
        memento = controller.field
        newField = controller.field.destroyCard(0, move.fieldSlotActive)
          .destroyCard(1, move.fieldSlotInactive)

      Success(newField)
    }
    else Failure(Exception("you can't attack!"))
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
  override def checkConditions: Boolean =
    controller.field.players.head.fieldbar.cardArea.slot(move.fieldSlotActive).isDefined &&
      controller.field.players(1).fieldbar.cardArea.slot(move.fieldSlotInactive).isDefined &&
      controller.field.players.head.fieldbar.cardArea.slot(move.fieldSlotActive).get.attackCount == 1



}