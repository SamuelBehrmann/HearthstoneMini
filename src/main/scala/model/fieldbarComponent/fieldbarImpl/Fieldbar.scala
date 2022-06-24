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
    override def placeCard(slot: Int, card: Card): Fieldbar = copy(cardArea = cardArea.replaceSlot(slot, Some(card)))
    override def removeCard(slot: Int): Fieldbar = copy(cardArea = cardArea.replaceSlot(slot, None))
    override def reduceDefVal(slotNum: Int, amount: Int): Fieldbar = copy(cardArea = cardArea
      .reduceDefVal(slotNum, amount))
    override def resetAttackCount(): Fieldbar = copy(cardArea = cardArea.resetAttackCount())
    override def reduceAttackCount(slotNum: Int): Fieldbar = copy(cardArea = cardArea.reduceAttackCount(slotNum))
    override def toMatrix: Matrix[String] = {
        var old = matrix
        cardArea.row.zipWithIndex.foreach((card, index) => card.fold({})((card) =>
            old = old.updateMatrixWithMatrix(0, index * FieldObject.standartSlotWidth + 1, card.toMatrix)))
        old.updateMatrix(5,0, List[String]("-" * FieldObject.standartFieldWidth))
    }

