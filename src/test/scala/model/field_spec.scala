package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class FieldSpec extends AnyWordSpec with Matchers {
    val eol = sys.props("line.separator")
   "A Field" when {
    "created" should {
      val field1 = new Field(5, "PlayerA", "PlayerB")
      "be created using size and two player names" in {
          field1.fieldBarP1.size should be(5)
      }
      "when placing a card for player one" in {
          field1.placeCardP1(2,"1").fieldBarP1.cardArea.slot(1) should be("1")
      }
      "when placing a card for player two" in {
        field1.placeCardP2(2,"1").fieldBarP2.cardArea.slot(1) should be("1")
      }
    }
    "empty" should {
      val emptyField = new Field()
      "created using nothing" in {
        emptyField.fieldBarP1.size should be(5)
        emptyField.fieldBarP2.size should be(5)
        emptyField.menuBarP1 should be ("PlayerA")
        emptyField.menuBarP2 should be ("PlayerB")
        emptyField.gameBarP1.hand should be (List("1", "2", "3", "4", "5"))
        emptyField.gameBarP2.hand should be (List("6", "7", "8", "9", "10"))

      }
      "printed field should look like" in {
        print(emptyField.printField())
        emptyField.printField() should be("+-----+-----+-----+-----+-----+" + eol +
          "\u001b[31mPlayerA" + eol +
          "\u001b[32m100 \u001b[34m100 \u001b[30mHand: |1| |2| |3| |4| |5|" + eol +
          "\u001b[30mdeck" + eol + "\u001b[31m" + "+-----+-----+-----+-----+-----+" + eol +
          "|     |     |     |     |     |" + eol +
          "+-----+-----+-----+-----+-----+" + eol +
          eol + "\u001b[34m" + "+-----+-----+-----+-----+-----+" + eol +
          "|     |     |     |     |     |" + eol +
          "+-----+-----+-----+-----+-----+" + eol +
          "\u001b[32m100 \u001b[34m100 \u001b[30mHand: |6| |7| |8| |9| |10|" + eol +
          "\u001b[30mdeck" + eol +
          "\u001b[34m" + "PlayerB" + eol +
          "\u001b[37m" + "+-----+-----+-----+-----+-----+" + eol)
      }
    }
  }
}