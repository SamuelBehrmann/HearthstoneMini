package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

//val Card: Card = new Card("test", 1, 1, 1, "normal", "zauber")

class field_bar_spec extends AnyWordSpec with Matchers {
   "A HearthstoneMini Field" when {
        val eol = sys.props("line.separator")
        "filled with Card" should {
            val field1 = new FieldBar(5, "Card()")
            val field2 = new FieldBar(5, "Card()")
            "have a bar as String of Form '+-----+-----+-----+-----+'" in {
                field1.bar() should be("+----------+----------+----------+----------+----------+" + eol)
            }
            "be scalable" in {
                field1.bar(1,1) should be("+------+" + eol)
                field1.bar(2,1) should be("+-------+" + eol)
                field1.bar(1,2) should be("+------+------+" + eol)
            }
            "have slots of Form '|  Card()  |  Card()  |  Card()  |  Card()  |  Card()  |'" in {
                field1.slots(5) should be("|  Card()  |  Card()  |  Card()  |  Card()  |  Card()  |" + eol)
            }
            "have a mesh of Form 'bar eol slots eol bar'" in {
                field2.completeField(5) should be("+----------+----------+----------+----------+----------+" +
                 eol + "|  Card()  |  Card()  |  Card()  |  Card()  |  Card()  |" +
                 eol + "+----------+----------+----------+----------+----------+" + eol)
            }
        }
        "filled with empty" should {
            val field3 = new FieldBar(5, "      ")
            "be empty initially" in {
                field3.completeField() should be("+----------+----------+----------+----------+----------+" +
                 eol + "|          |          |          |          |          |" +
                 eol + "+----------+----------+----------+----------+----------+" + eol)
            }
            "have 'Card()' in first & second Slot after 2 moves" in {
                val moveField3 = field3.placeCard(1, "Card()").placeCard(0, "Card()")
                moveField3.completeField() should be("+----------+----------+----------+----------+----------+" +
                 eol + "|  Card()  |  Card()  |          |          |          |" +
                 eol + "+----------+----------+----------+----------+----------+" + eol)
            }
             "have 'Empty' in first & 'Card()' in second Slot after removing 1 slot card" in {
                val moveField3 = field3.placeCard(1, "Card()").placeCard(0, "Card()")
                val removedField3 = moveField3.removeCard(0)
                removedField3.completeField() should be("+----------+----------+----------+----------+----------+" +
                 eol + "|          |  Card()  |          |          |          |" +
                 eol + "+----------+----------+----------+----------+----------+" + eol)
            }
        }
  }
}