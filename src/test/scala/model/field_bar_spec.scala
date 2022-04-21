package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FieldBarSpec extends AnyWordSpec with Matchers {
   "A HearthstoneMini Field" when {
        val eol = sys.props("line.separator")
        "filled with Card" should {
            val fieldBar1 = new FieldBar(5, new Card("Test", 1, 1,1,"kann zaubern", "rare"))
            val fieldBar2 = new FieldBar(5, new Card("Test2", 1, 1,1,"kann zaubern", "rare"))
            "be able to print said card" in {
                //fieldBar1.completeField
                //fieldBar1.matrix.printMatrix()
                val newfield = fieldBar1.placeCard(1,new Card("Test", 1, 1,1,"kann zaubern", "rare"))
                val ndfield = newfield.placeCard(2,new Card("yolo", 1, 1,1,"nein", "legend"))
                print(ndfield.completeField())
                fieldBar1.completeField(10) should be("+----------+----------+----------+----------+----------+" + eol)
            }
        }
    }
}