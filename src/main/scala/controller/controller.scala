package controller

import util.Observable
import util.UndoManager
import model.Field
import model.Player
import model.Move
import controller.GameState.*

import java.lang.System.exit
import model.commands.{AttackCommand, DirectAttackCommand, DrawCardCommand, PlaceCardCommand, SetPlayerNamesCommand, SwitchPlayerCommand}
import util.Event

case class Controller(var field: Field) extends Observable {
     var gameState: GameState = GameState.PREGAME
     private val undoManager: UndoManager = new UndoManager

     def placeCard(move: Move): Unit = {
          undoManager.doStep(new PlaceCardCommand(this, move))
          notifyObservers(Event.PLAY)
     }
     def drawCard(): Unit = {
          undoManager.doStep(new DrawCardCommand(this))
          notifyObservers(Event.PLAY)
     }
     def setPlayerNames(move: Move): Unit = {
          undoManager.doStep(new SetPlayerNamesCommand(this, move))
          notifyObservers(Event.PLAY)
     }
     def attack(move: Move): Unit = {
          undoManager.doStep(new AttackCommand(this, move))
          notifyObservers(Event.PLAY)
     }
     def directAttack(move: Move): Unit = {
          undoManager.doStep(new DirectAttackCommand(this, move))
          notifyObservers(Event.PLAY)
     }
     def switchPlayer(): Unit = {
          undoManager.doStep(new SwitchPlayerCommand(this))
          notifyObservers(Event.PLAY)
     }
     def exitGame(): Unit = {
          gameState = GameState.EXIT
          notifyObservers(Event.EXIT)
     }
     def undo: Unit = {
          undoManager.undoStep
          notifyObservers(Event.PLAY)
     }
     def redo: Unit = {
          undoManager.redoStep
          notifyObservers(Event.PLAY)
     }
     override def toString() = field.toString
}