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
                fieldBar1.slots()
                fieldBar1.matrix.printMatrix()
                fieldBar1.slots() should be("+----------+----------+----------+----------+----------+" + eol)
            }
        }
    }
}