package model.playerComponent
import model.matrixComponent.matrixImpl.Matrix
import model.fieldComponent.fieldImpl.FieldObject
import model.playerComponent.playerImpl.Player

trait PlayerInterface {
  def placeCard(handSlot: Int, fieldSlot: Int ): Player
  def drawCard(): Player
  def destroyCard(fieldSlot: Int): Player
  def reduceHp(amount: Int): Player
  def increaseHp(amount: Int): Player
  def reduceMana(amount: Int): Player
  def increaseMana(amount: Int): Player
  def resetAndIncreaseMana(): Player
  def setName(name: String): Player
  def setHpValue(amount: Int): Player
  def setManaValue(amount: Int): Player
  def toMatrix: Matrix[String]

  def renderUnevenId(): Matrix[String]

  def renderEvenId(): Matrix[String] 

  def menueBar(): Matrix[String]
}
