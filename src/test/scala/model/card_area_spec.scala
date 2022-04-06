package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CardAreaSpec extends AnyWordSpec with Matchers {
   "A Cardarea is a Vector with x amount of fields" when {
    "empty" should {
      "be created by using a size and a samlpe cell" in {
          val cardArea = new CardArea(5, "s")
          cardArea.size should be(5)
      }
      "for test purposes only be created with a Vector" in {
        val testCardArea = CardArea(Vector("s"))
        testCardArea.size should be(1)
      }
    }
    "filled" should {
        val cardArea = new CardArea(5, "s")
        "allow accessing the slots" in {
          cardArea.slot(0) should be("s")
        }
        "allow replacing a slot with another String- returns a new CardArea" in {
          val replacedCardArea = cardArea.replaceSlot(1, "works")
          replacedCardArea.slot(1) should be("works")
        }
    }
  }
}