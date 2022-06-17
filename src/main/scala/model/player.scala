package model

import model.field_bar_component.fieldBarImpl.FieldBar
import model.field_component.fieldImpl.{Field, FieldObject}
import model.field_component.FieldInterface
import model.matrix_component.matrixImpl.Matrix

import java.awt.MenuBar

case class Player(name: String = "Player", id: Int, fieldbar: FieldBar = new FieldBar(FieldObject.standartSlotNum , None), gamebar: GameBar  = new GameBar()) {
    def placeCard(handSlot: Int, fieldSlot: Int ): Player = copy(fieldbar = fieldbar.placeCard(fieldSlot, gamebar.hand(handSlot)), gamebar = gamebar.removeCardFromHand(handSlot))
    def drawCard(): Player = copy(gamebar = gamebar.drawCard())
    def destroyCard(fieldSlot: Int): Player = copy(fieldbar = fieldbar.removeCard(fieldSlot), gamebar = gamebar.addCardToFriedhof(fieldbar.cardArea.row(fieldSlot)))
    def reduceHp(amount: Int): Player = copy(gamebar = gamebar.reduceHp(amount))
    def increaseHp(amount: Int): Player = copy(gamebar = gamebar.increaseHp(amount))
    def reduceMana(amount: Int): Player = copy(gamebar = gamebar.reduceMana(amount))
    def increaseMana(amount: Int): Player = copy(gamebar = gamebar.increaseMana(amount))
    def resetAndIncreaseMana(): Player = copy(gamebar = gamebar.resetAndIncreaseMana())
    def setName(name: String): Player = copy(name = name)
    def setHpValue(amount: Int): Player = copy(gamebar = gamebar.setHpValue(amount))
    def setManaValue(amount: Int): Player = copy(gamebar = gamebar.setManaValue(amount))
    def toMatrix(): Matrix[String] = if ((id % 2) == 1) then renderUnevenId() else renderEvenId()

    def renderUnevenId(): Matrix[String] = new Matrix[String](FieldObject.standartMenueBarHeight + FieldObject.standartGameBarHeight + FieldObject.standartFieldBarHeight, FieldObject.standartFieldWidth, " ")
    .updateMatrixWithMatrix(0,0, menueBar())
    .updateMatrixWithMatrix(FieldObject.standartMenueBarHeight, 0, gamebar.toMatrix())
    .updateMatrixWithMatrix(FieldObject.standartGameBarHeight + FieldObject.standartMenueBarHeight, 0, fieldbar.toMatrix())

    def renderEvenId(): Matrix[String] = new Matrix[String](FieldObject.standartMenueBarHeight + FieldObject.standartGameBarHeight + FieldObject.standartFieldBarHeight, FieldObject.standartFieldWidth, " ")
    .updateMatrixWithMatrix(0, 0, fieldbar.toMatrix())
    .updateMatrixWithMatrix(FieldObject.standartFieldBarHeight, 0, gamebar.toMatrix())
    .updateMatrixWithMatrix(FieldObject.standartFieldBarHeight + FieldObject.standartGameBarHeight, 0, menueBar())

    def menueBar(): Matrix[String] = new Matrix[String](FieldObject.standartMenueBarHeight, FieldObject.standartFieldWidth, " ")
    .updateMatrix(0, 0, List[String]("\u001b[1m" + name + " \u001b[0m" + "\u001b[32;1m|\u001b[0;37m" * ((FieldObject.standartFieldWidth - name.length - 1) * gamebar.hp.value/gamebar.hp.max).asInstanceOf[Float].floor.asInstanceOf[Int], "-" * FieldObject.standartFieldWidth))
}