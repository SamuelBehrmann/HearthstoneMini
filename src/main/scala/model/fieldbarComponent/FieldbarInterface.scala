package model.fieldbarComponent

import model.cardComponent.cardImpl.Card
import model.fieldbarComponent.fieldbarImpl.Fieldbar
import model.fieldComponent.fieldImpl.FieldObject
import model.matrixComponent.matrixImpl.Matrix

trait FieldbarInterface {
  val size: Int
  def placeCard(slot: Int, card: Card): Fieldbar
  def removeCard(slot: Int): Fieldbar
  def toMatrix: Matrix[String]
}
