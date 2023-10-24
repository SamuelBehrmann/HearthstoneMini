package hearthstoneMini
package controller.component
import model.Move
import model.fieldComponent.FieldInterface
import util.Observable
import hearthstoneMini.controller.Strategy

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
  def setStrategy(strat: Strategy): Unit
  override def toString(): String
}
