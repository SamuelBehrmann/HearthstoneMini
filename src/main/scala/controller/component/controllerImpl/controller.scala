package controller.component.controllerImpl

import com.google.inject.name.Names
import com.google.inject.{Guice, Inject}
import controller.GameState.*
import controller.component.ControllerInterface
import controller.{GameState, Strategy}
import model.commands.*
import model.field_component.FieldInterface
import model.{Move, Player}
import net.codingwell.scalaguice.InjectorExtensions.*
import util.{Event, Observable, UndoManager}
import java.lang.System.exit
import scala.HearthstoneMiniModule

case class Controller @Inject() (var field: FieldInterface) extends ControllerInterface {
     val injector = Guice.createInjector(new HearthstoneMiniModule)
     var gameState: GameState = GameState.CHOOSEMODE
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
          nextState()
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
     def nextState(): Unit = {
          gameState match {
               case GameState.CHOOSEMODE => gameState = GameState.ENTERPLAYERNAMES
               case GameState.ENTERPLAYERNAMES => gameState = GameState.MAINGAME
               case GameState.MAINGAME => gameState = GameState.WIN
          }
     }
     def setStrategy(strat: FieldInterface) = {
          field = strat
          nextState()
          notifyObservers(Event.PLAY)
     }
     override def toString() = field.toString
}