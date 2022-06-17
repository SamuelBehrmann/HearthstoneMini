package controller.component
import model.Move
import model.field_component.FieldInterface
import util.Observable

trait ControllerInterface extends Observable {
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
  def setStrategy(strat: FieldInterface): Unit
  override def toString(): String
}
