package controller

import util.Observable
import util.UndoManager
import model.Field
import model.Player
import model.Move
import controller.GameState._
import java.lang.System.exit
import model.commands.DrawCardCommand
import model.commands.PlaceCardCommand
import model.commands.SetPlayerNamesCommand
import model.commands.AttackCommand
import model.commands.SwitchPlayerCommand

case class Controller(var field: Field) extends Observable {
     var gameState: GameState = GameState.PREGAME
     private val undoManager: UndoManager = new UndoManager

     def placeCard(move: Move): Unit = {
          undoManager.doStep(new PlaceCardCommand(this, move))
          notifyObservers
     }
     def drawCard(): Unit = {
          undoManager.doStep(new DrawCardCommand(this))
          notifyObservers
     }
     def setPlayerNames(move: Move): Unit = {
          undoManager.doStep(new SetPlayerNamesCommand(this, move))
          notifyObservers
     }
     def attack(move: Move): Unit = {
          undoManager.doStep(new AttackCommand(this, move))
          notifyObservers
     }
     def switchPlayer(): Unit = {
          undoManager.doStep(new SwitchPlayerCommand(this))
          notifyObservers
     }
     def exitGame(): Unit = {
          gameState = GameState.EXIT
          notifyObservers
     }
     def undo: Unit = {
          undoManager.undoStep
          notifyObservers
     }
     def redo: Unit = {
          undoManager.redoStep
          notifyObservers
     }
     override def toString() = field.toString
}