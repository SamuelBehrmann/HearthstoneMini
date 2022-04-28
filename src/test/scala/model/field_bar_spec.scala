package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FieldBarSpec extends AnyWordSpec with Matchers {
  "A HearthstoneMini fieldbar" when {
    val eol = sys.props("line.separator")
    "filled with Card" should {
      val fieldBar1 = new FieldBar(5, new Card("Test", 1, 1, 1, "kann zaubern", "rare"))
      val fieldBar2 = new FieldBar(5, new Card("Test2", 1, 1, 1, "kann zaubern", "rare"))
      "be able to print said card" in {
        //fieldBar1.completeField
        //fieldBar1.matrix.printMatrix()
        val newfield = fieldBar1.placeCard(1, new Card("Test", 1, 1, 1, "kann zaubern", "rare"))
        val ndfield = newfield.placeCard(2, new Card("yolo", 1, 1, 1, "nein", "legend"))
        fieldBar1.cardArea.row.length should be(5)

      }
      "when card is removed" in {
        fieldBar1.removeCard(1).cardArea.slot(1).toMatrix().toAString() should be ((" " * Field.standartCardWidth + "\n") * Field.standartCardHeight)
        fieldBar2.removeCard(2).cardArea.slot(2).toMatrix().toAString() should be ((" " * Field.standartCardWidth + "\n") * Field.standartCardHeight)
      }
    }
    "fieldBar to matrix" in {
      val fieldBarM = new FieldBar().toMatrix().toAString() should be ((" " * Field.standartFieldWidth + "\n") * (Field.standartFieldBarHeight - 1)
          + "-" * Field.standartFieldWidth + "\n")
    }
  }
}