package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MatrixSpec extends AnyWordSpec with Matchers {
   "Matrix" when {
    "empty" should {
      var matrix = new Matrix[String](20, 60, "|")
      val eol = sys.props("line.separator")
      "should have size and no Content" in {
        matrix.rowSize should be(20)
      }
      "should allow insert at col, row" in {
        val string: String = "Hello World!#Hello World!"
        val newMa = matrix.updateMatrix(0, 0, string.split("#").toList)
      }
      "should allow insert Matrix at col, row" in {
        val matrixToInsert: Matrix[String] = new Matrix[String](4,10, "|")
        val card = new Card("test", 1,1,1, "non","rare")
        val newMa = matrix.updateMatrixWithMatrix(0, 0, card.toMatrix())
        newMa.printMatrix()
      }
    }
  }
}