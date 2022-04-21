package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec with Matchers {
   "A Field" when {
    "empty" should {
      "be created using nothing" in {
          val field1 = new Field(5)
          print(field1)
          field1.fieldBarP1.size should be(5)
      }
    }
  }
}