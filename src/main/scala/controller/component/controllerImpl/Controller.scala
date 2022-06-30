package controller.component.controllerImpl

import com.google.inject.name.{Named, Names}
import com.google.inject.{Guice, Inject, Injector}
import controller.GameState.*
import controller.component.ControllerInterface
import controller.{GameState, Strategy}
import model.commands.*
import model.fieldComponent.FieldInterface
import model.Move
import _root_.model.fileIOComponent.FileIOInterface
import model.playerComponent.playerImpl.Player
import net.codingwell.scalaguice.InjectorExtensions.*
import util.{Command, Event, Observable, UndoManager}

import java.lang.System.exit
import java.text.Annotation
import scala.HearthstoneMiniModule
import scala.util.{Failure, Success, Try}

case class Controller @Inject() (var field: FieldInterface) extends ControllerInterface {
     val injector: Injector = Guice.createInjector(new HearthstoneMiniModule)
     val fileIO: FileIOInterface = injector.getInstance(classOf[FileIOInterface])
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
               case GameState.MAINGAME => gameState = GameState.WIN
          }
     }
     def setStrategy(strat: FieldInterface) = {
          field = strat
          nextState()
          notifyObservers(Event.PLAY, msg = None)
     }
     def getWinner(): Option[String] = {
          val p1Hp = field.players(0).gamebar.hp.isEmpty
          val p2Hp = field.players(1).gamebar.hp.isEmpty

          if p1Hp then Some(field.players(1).name)
          else if p2Hp then Some(field.players(0).name)
          else None
     }
     def loadField = {
          this.field = fileIO.load
          nextState()
          nextState()
          notifyObservers(Event.PLAY, msg = None)
     }
}