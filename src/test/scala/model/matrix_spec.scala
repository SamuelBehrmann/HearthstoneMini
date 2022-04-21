package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MatrixSpec extends AnyWordSpec with Matchers {
   "Matrix" when {
    "empty" should {
      var matrix = new Matrix[String](20, 60, "S")
      val eol = sys.props("line.separator")
      "should have size and no Content" in {
        matrix.rowSize should be(20)
      }
      "should allow insert at col, row" in {
        val string: String = "Hello World!#Hello World!"
        val newMa = matrix.updateMatrix(0, 0, string.split("#").toList)
      }
      "should allow insert Matrix at col, row" in {
        val matrixToInsert: Matrix[String] = new Matrix[String](5,5, "|")
        val newMa = matrix.updateMatrixWithMatrix(15, 55, matrixToInsert)
        newMa.printMatrix()
      }
    }
  }
}