package model.commands

import model.Move
import controller.GameState
import controller.component.controllerImpl.Controller
import model.cardComponent.cardImpl.Card
import util.Command
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldImpl.Field
import scala.util.{Success, Try, Failure}

class DirectAttackCommand(controller: Controller, move: Move) extends Command {
  var memento: FieldInterface = controller.field

  override def doStep: Try[FieldInterface] = {
    if checkConditions then {
      memento = controller.field
      val newField = controller.field.reduceHp(1, controller.field.players.head.fieldbar.cardArea.
        slot(move.fieldSlotActive).get.attValue).reduceAttackCount(move.fieldSlotActive)
      if (newField.players(0).gamebar.hp.isEmpty || newField.players(1).gamebar.hp.isEmpty)
      then controller.nextState()
      Success(newField)
    } else
      Failure(Exception("You can't attack directly!"))
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
    (controller.field.players.head.fieldbar.cardArea.slot(move.fieldSlotActive).isDefined)
      && !(controller.field.players(1).fieldbar.cardArea.row.count(_.isDefined) > 0)
      && controller.field.players.head.fieldbar.cardArea.slot(move.fieldSlotActive).get.attackCount >= 1
      && controller.field.turns > 1
}