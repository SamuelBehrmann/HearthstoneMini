package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec with Matchers {
   "A Field" when {
    "empty" should {
      "be created using nothing" in {
          val field = new Field(new Matrix[String](20,60,""),5)
          //print(field)
          field.matrix.rowSize should be(20)
      }
    }
  }
}