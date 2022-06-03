package controller.component
import model.{Field, Move}
import util.Observable

trait ControllerInterface() extends Observable {
  def placeCard(move: Move): Unit
  def drawCard(): Unit
  def setPlayerNames(move: Move): Unit
  def attack(move: Move): Unit
  def directAttack(move: Move): Unit
  def switchPlayer(): Unit
  def exitGame(): Unit
  def undo: Unit
  def redo: Unit
  def nextState(): Unit
  def setStrategy(strat: Field): Unit
  override def toString(): String
}
