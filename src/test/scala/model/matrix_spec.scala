package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class MatrixSpec extends AnyWordSpec with Matchers {
   "Matrix" when {
    "empty" should {
        var matrix = new Matrix[Char](20, ' ')
        val eol = sys.props("line.separator")
      "should have size and no Content" in {
          matrix.size should be(20)
      }
      "should allow insert at col, row" in {
        val string: String = "Hello World! #Hello World!"
        val newMa = matrix.createVectorList(0, 0, string)
        
      }
    }
  }
}