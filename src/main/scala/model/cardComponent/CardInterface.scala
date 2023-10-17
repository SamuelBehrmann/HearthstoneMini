package hearthstoneMini
package model.cardComponent

import model.cardComponent.cardImpl.Card
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
  def reduceHP(amount: Int): CardInterface
  def reduceAttackCount(): CardInterface
  def resetAttackCount(): CardInterface
  def toMatrix: Matrix[String] = new Matrix[String](FieldObject.standartCardHeight,
    FieldObject.standartCardWidth, " ")
}
