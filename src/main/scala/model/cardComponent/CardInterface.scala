package model.cardComponent

import model.fieldComponent.fieldImpl.FieldObject
import model.matrixComponent.matrixImpl.Matrix

trait CardInterface {
  val name: String
  val manaCost: Int
  val attValue: Int
  val defenseValue: Int
  val effect: String
  val rarity: String
  var attackCount: Int
  def toMatrix: Matrix[String] = new Matrix[String](FieldObject.standartCardHeight,
    FieldObject.standartCardWidth, " ")
}
