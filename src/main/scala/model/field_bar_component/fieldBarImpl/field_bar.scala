package model.field_bar_component.fieldBarImpl

import model.card_area_component.CardAreaInterface
import model.card_area_component.cardAreaImpl.CardArea
import model.card_component.cardImpl.{Card, EmptyCard}
import model.field_bar_component.FieldBarInterface
import model.field_component.FieldInterface
import model.field_component.fieldImpl.{Field, FieldObject}
import model.matrix_component.matrixImpl.Matrix

import scala.quoted.FromExpr.NoneFromExpr

case class FieldBar(cardArea: CardAreaInterface = new CardArea[Option[Card]](FieldObject.standartSlotNum, None), matrix: Matrix[String] = new Matrix[String](FieldObject.standartFieldBarHeight, FieldObject.standartFieldWidth, " ")) extends FieldBarInterface:
    def this(size: Int, filling: Option[Card]) = this(cardArea = new CardArea(size, filling), new Matrix[String](FieldObject.standartFieldBarHeight, FieldObject.standartSlotWidth * size, " "))
    val size = cardArea.size
    def placeCard(slot: Int, card: Card): FieldBar = copy(cardArea = cardArea.replaceSlot(slot, Some(card)), matrix = matrix.updateMatrixWithMatrix(0, slot * FieldObject.standartSlotWidth + 1, card.toMatrix()))
    def removeCard(slot: Int): FieldBar = copy(cardArea = cardArea.replaceSlot(slot, None), matrix = matrix.updateMatrixWithMatrix(0, slot * FieldObject.standartSlotWidth + 1, EmptyCard().toMatrix()))

    def toMatrix(): Matrix[String] = matrix
    .updateMatrix(5,0, List[String]("-" * FieldObject.standartFieldWidth))

