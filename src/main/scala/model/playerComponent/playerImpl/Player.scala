package model.playerComponent.playerImpl

import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldImpl.{Field, FieldObject}
import model.gamebarComponent.gamebarImpl.Gamebar
import model.playerComponent.PlayerInterface
import model.fieldbarComponent.fieldbarImpl.Fieldbar
import model.fieldComponent.fieldImpl.{Field, FieldObject}
import model.fieldComponent.FieldInterface
import model.matrixComponent.matrixImpl.Matrix

import java.awt.MenuBar

case class Player(name: String = "Player", id: Int,
                  fieldbar: Fieldbar = new Fieldbar(FieldObject.standartSlotNum , None),
                  gamebar: Gamebar  = new Gamebar())
  extends PlayerInterface{
    override def placeCard(handSlot: Int, fieldSlot: Int ): Player = copy(
        fieldbar = fieldbar.placeCard(fieldSlot, gamebar.hand(handSlot)),
        gamebar = gamebar.removeCardFromHand(handSlot))
    override def drawCard(): Player = copy(gamebar = gamebar.drawCard())
    override def destroyCard(fieldSlot: Int): Player = copy(
        fieldbar = fieldbar.removeCard(fieldSlot),
        gamebar = gamebar.addCardToFriedhof(fieldbar.cardArea.row(fieldSlot)))
    override def reduceHp(amount: Int): Player = copy(gamebar = gamebar.reduceHp(amount))
    override def increaseHp(amount: Int): Player = copy(gamebar = gamebar.increaseHp(amount))
    override def reduceMana(amount: Int): Player = copy(gamebar = gamebar.reduceMana(amount))
    override def increaseMana(amount: Int): Player = copy(gamebar = gamebar.increaseMana(amount))
    override def resetAndIncreaseMana(): Player = copy(gamebar = gamebar.resetAndIncreaseMana())
    override def setName(name: String): Player = copy(name = name)
    override def setHpValue(amount: Int): Player = copy(gamebar = gamebar.setHpValue(amount))
    override def setManaValue(amount: Int): Player = copy(gamebar = gamebar.setManaValue(amount))
    override def reduceAttackCount(slotNum: Int): Player = copy(fieldbar = fieldbar.reduceAttackCount(slotNum))
    override def resetAttackCount(): Player = copy(fieldbar = fieldbar.resetAttackCount())
    override def toMatrix: Matrix[String] = if ((id % 2) == 1) then renderUnevenId() else renderEvenId()

    override def renderUnevenId(): Matrix[String] = new Matrix[String](
        FieldObject.standartMenueBarHeight + FieldObject.standartGameBarHeight + FieldObject.standartFieldBarHeight,
        FieldObject.standartFieldWidth,
        " ")
    .updateMatrixWithMatrix(0,0, menueBar())
    .updateMatrixWithMatrix(FieldObject.standartMenueBarHeight, 0, gamebar.toMatrix)
    .updateMatrixWithMatrix(FieldObject.standartGameBarHeight + FieldObject.standartMenueBarHeight, 0,
        fieldbar.toMatrix)
    override def renderEvenId(): Matrix[String] = new Matrix[String](
        FieldObject.standartMenueBarHeight + FieldObject.standartGameBarHeight + FieldObject.standartFieldBarHeight,
        FieldObject.standartFieldWidth, " ")
    .updateMatrixWithMatrix(0, 0, fieldbar.toMatrix)
    .updateMatrixWithMatrix(FieldObject.standartFieldBarHeight, 0, gamebar.toMatrix)
    .updateMatrixWithMatrix(FieldObject.standartFieldBarHeight + FieldObject.standartGameBarHeight,
        0, menueBar())
    override def reduceDefVal(slotNum: Int, amount: Int): Player = copy(
        fieldbar = fieldbar.reduceDefVal(slotNum, amount))
    override def menueBar(): Matrix[String] = new Matrix[String](FieldObject.standartMenueBarHeight,
        FieldObject.standartFieldWidth, " ")
    .updateMatrix(0, 0, List[String]("\u001b[1m" + name + " \u001b[0m" +
      "\u001b[32;1m|\u001b[0;37m" * ((FieldObject.standartFieldWidth - name.length - 1) *
        gamebar.hp.value/gamebar.hp.max).asInstanceOf[Float].floor.asInstanceOf[Int], "-" *
      FieldObject.standartFieldWidth))
}