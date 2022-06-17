package model.field_bar_component

import model.card_component.cardImpl.Card
import model.field_bar_component.fieldBarImpl.FieldBar
import model.field_component.fieldImpl.FieldObject
import model.matrix_component.matrixImpl.Matrix

trait FieldBarInterface {
  val size: Int
  def placeCard(slot: Int, card: Card): FieldBar
  def removeCard(slot: Int): FieldBar
  def toMatrix(): Matrix[String]
}
