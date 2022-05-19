package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FieldBarSpec extends AnyWordSpec with Matchers {
  "A HearthstoneMini fieldbar" when {
    "filled with Card" should {
      val fieldBar1 = new FieldBar(5, new EmptyCard())
      val fieldBar2 = FieldBar()
      "be able to place cards" in {
        val newfield = fieldBar1.placeCard(1, new Card("Test", 1, 1, 1, "kann zaubern", "rare"))
        newfield.cardArea.row(1) shouldBe a[Card]
      }
      "when card is removed" in {
        fieldBar1.removeCard(1).cardArea.slot(1).toMatrix().toAString() should be ((" " * Field.standartCardWidth + "\n") * Field.standartCardHeight)
      }
    }
    "fieldBar to matrix" in {
      val fieldBarM = FieldBar().toMatrix().toAString() should be ((" " * Field.standartFieldWidth + "\n") * (Field.standartFieldBarHeight - 1)
          + "-" * Field.standartFieldWidth + "\n")
    }
  }
}