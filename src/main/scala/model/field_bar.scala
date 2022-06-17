package model

import model.field_component.fieldImpl.{Field, FieldObject}
import model.field_component.FieldInterface

import scala.quoted.FromExpr.NoneFromExpr

case class FieldBar(cardArea: CardArea[Option] = new CardArea[Option](FieldObject.standartSlotNum, None), matrix: Matrix[String] = new Matrix[String](FieldObject.standartFieldBarHeight, FieldObject.standartFieldWidth, " ")):
    def this(size: Int, filling: Option[Card]) = this(cardArea = new CardArea(size, filling), new Matrix[String](FieldObject.standartFieldBarHeight, FieldObject.standartSlotWidth * size, " "))
    val size = cardArea.size
    def placeCard(slot: Int, card: Card): FieldBar = copy(cardArea = cardArea.replaceSlot(slot, Some(card)), matrix = matrix.updateMatrixWithMatrix(0, slot * FieldObject.standartSlotWidth + 1, card.toMatrix()))
    def removeCard(slot: Int): FieldBar = copy(cardArea = cardArea.replaceSlot(slot, None), matrix = matrix.updateMatrixWithMatrix(0, slot * FieldObject.standartSlotWidth + 1, EmptyCard().toMatrix()))

    def toMatrix(): Matrix[String] = matrix
    .updateMatrix(5,0, List[String]("-" * FieldObject.standartFieldWidth))

