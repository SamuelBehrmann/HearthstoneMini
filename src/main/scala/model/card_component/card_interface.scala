package model.card_component

import model.field_component.fieldImpl.FieldObject
import model.matrix_component.matrixImpl.Matrix

trait CardInterface {
  val name: String
  val manaCost: Int
  val attValue: Int
  val defenseValue: Int
  val effect: String
  val rarity: String
  def toMatrix(): Matrix[String] = new Matrix[String](FieldObject.standartCardHeight, FieldObject.standartCardWidth, " ")
}
