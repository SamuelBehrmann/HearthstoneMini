package controller.component.controllerImpl

import com.google.inject.name.Names
import com.google.inject.{Guice, Inject}
import controller.GameState.*
import controller.component.ControllerInterface
import controller.{GameState, Strategy}
import model.commands.*
import model.fieldComponent.FieldInterface
import model.Move
import model.playerComponent.playerImpl.Player
import net.codingwell.scalaguice.InjectorExtensions.*
import util.{Event, Observable, UndoManager, Command}
import java.lang.System.exit
import scala.HearthstoneMiniModule
import scala.util.{Failure, Success, Try}

case class Controller @Inject() (var field: FieldInterface) extends ControllerInterface {
     val injector = Guice.createInjector(new HearthstoneMiniModule)
     var gameState: GameState = GameState.CHOOSEMODE
     private val undoManager: UndoManager = new UndoManager

     def placeCard(move: Move): Unit = doStep(new PlaceCardCommand(this, move))
     def drawCard(): Unit = doStep(new DrawCardCommand(this))
     def setPlayerNames(move: Move): Unit = {
          nextState()
          doStep(new SetPlayerNamesCommand(this, move))
     }
     def attack(move: Move): Unit = doStep(new AttackCommand(this, move))
     def directAttack(move: Move): Unit = doStep(new DirectAttackCommand(this, move))
     def switchPlayer(): Unit = doStep(new SwitchPlayerCommand(this))

     private def doStep(command: Command): Unit = {
          command.doStep match {
               case Success(newField) => {
                    field = newField
                    undoManager.doStep(command)
                    notifyObservers(Event.PLAY, msg = None)
               }
               case Failure(x) => notifyObservers(Event.ERROR, msg = Some(x.getMessage))
          }
     }
     def undo: Unit = {
          undoManager.undoStep
          notifyObservers(Event.PLAY, msg = None)
     }
     def redo: Unit = {
          undoManager.redoStep
          notifyObservers(Event.PLAY, msg = None)
     }
     def exitGame(): Unit = {
          gameState = GameState.EXIT
          notifyObservers(Event.EXIT, msg = None)
     }
     def nextState(): Unit = {
          gameState match {
               case GameState.CHOOSEMODE => gameState = GameState.ENTERPLAYERNAMES
               case GameState.ENTERPLAYERNAMES => gameState = GameState.MAINGAME
               case GameState.MAINGAME => {
                    gameState = GameState.WIN
                    notifyObservers(Event.PLAY, msg = None)
               }
          }
     }
     def setStrategy(strat: FieldInterface) = {
          field = strat
          nextState()
          notifyObservers(Event.PLAY, msg = None)
     }
     override def toString() = field.toString
}