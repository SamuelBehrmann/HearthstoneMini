package model.fieldbarComponent

import model.cardComponent.cardImpl.Card
import model.fieldbarComponent.fieldbarImpl.Fieldbar
import model.fieldComponent.fieldImpl.FieldObject
import model.matrixComponent.matrixImpl.Matrix

trait FieldbarInterface {
  val size: Int
  def placeCard(slot: Int, card: Card): FieldbarInterface
  def removeCard(slot: Int): FieldbarInterface
  def toMatrix: Matrix[String]
  def reduceDefVal(slotNum: Int, amount: Int): FieldbarInterface
}
