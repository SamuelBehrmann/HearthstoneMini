package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CardAreaSpec extends AnyWordSpec with Matchers {
   "A Cardarea is a Vector with x amount of slots" when {
    "empty" should {
      "be created by using a size and a samlpe cell" in {
          val cardArea = new CardArea(5, EmptyCard())
          cardArea.size should be(5)
      }
      "for test purposes only be created with a Vector" in {
        val testCardArea = CardArea(Vector(EmptyCard()))
        testCardArea.size should be(1)
      }
    }
    "filled" should {
        val cardArea = new CardArea[CardType](5, EmptyCard())
        "allow accessing the slots" in {
          cardArea.slot(0) shouldBe a [EmptyCard]
        }
        "allow replacing a slot with another String- returns a new CardArea" in {
          val replacedCardArea = cardArea.replaceSlot(1, Card("test", 2, 2, 2, "Schmettern", "rare"))
          replacedCardArea.slot(1) shouldBe a [Card]
        }
    }
  }
}