package model

import model.card_component.cardImpl.Card
import model.field_bar_component.fieldBarImpl.FieldBar
import model.field_component.fieldImpl.FieldInterface
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FieldBarSpec extends AnyWordSpec with Matchers {
  "A HearthstoneMini fieldbar" when {
    "filled with Card" should {
      val fieldBar1 = new FieldBar(5, None)
      val fieldBar2 = FieldBar()
      "be able to place cards" in {
        val newfield = fieldBar1.placeCard(1, new Card("Test", 1, 1, 1, "kann zaubern", "rare"))
        newfield.cardArea.row(1).get shouldBe a[Card]
      }
      "when card is removed" in {
        fieldBar1.removeCard(1).cardArea.slot(1).isDefined should be (false)
      }
    }
    "fieldBar to matrix" in {
      val fieldBarM = FieldBar().toMatrix().toAString() should be ((" " * FieldInterface.standartFieldWidth + "\n") * (FieldInterface.standartFieldBarHeight - 1)
          + "-" * FieldInterface.standartFieldWidth + "\n")
    }
  }
}