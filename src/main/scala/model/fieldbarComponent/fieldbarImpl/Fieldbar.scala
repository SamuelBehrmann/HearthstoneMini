package model.fieldbarComponent.fieldbarImpl

import model.cardareaComponent.CardAreaInterface
import model.cardareaComponent.cardareaImpl.Cardarea
import model.cardComponent.cardImpl.{Card, EmptyCard}
import model.fieldbarComponent.FieldbarInterface
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldImpl.{Field, FieldObject}
import model.matrixComponent.matrixImpl.Matrix

import scala.quoted.FromExpr.NoneFromExpr

case class Fieldbar(cardArea: CardAreaInterface = new Cardarea[Option[Card]](FieldObject.standartSlotNum, None),
                    matrix: Matrix[String] = new Matrix[String](FieldObject.standartFieldBarHeight,
                        FieldObject.standartFieldWidth, " "))
  extends FieldbarInterface:
    def this(size: Int, filling: Option[Card]) = this(cardArea = new Cardarea(size, filling),
        new Matrix[String](FieldObject.standartFieldBarHeight, FieldObject.standartSlotWidth * size, " "))
    val size: Int = cardArea.size
    def placeCard(slot: Int, card: Card): Fieldbar = copy(cardArea = cardArea.replaceSlot(slot, Some(card)),
        matrix = matrix.updateMatrixWithMatrix(0, slot * FieldObject.standartSlotWidth + 1, card.toMatrix))
    def removeCard(slot: Int): Fieldbar = copy(cardArea = cardArea.replaceSlot(slot, None),
        matrix = matrix.updateMatrixWithMatrix(0, slot * FieldObject.standartSlotWidth + 1,
            EmptyCard().toMatrix))

    def toMatrix: Matrix[String] = matrix
    .updateMatrix(5,0, List[String]("-" * FieldObject.standartFieldWidth))

