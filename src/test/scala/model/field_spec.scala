package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec with Matchers {
   "A Field" when {
    "created" should {
      val field = new Field(5, "Player", "Player")
      "be created using default fieldsize 5 and 2 player names" in {
          field.matrix.colSize should be(Field.standartFieldWidth)
      }
      "have a Card in slot 1 after placed 1 card in slot 1 from hand" in {
          field.placeCard(0, 0, 0).players(0).gamebar.hand.length should be(3)
          field.placeCard(0, 0, 0).players(0).fieldbar.cardArea.row(0) shouldBe an [Card]
      }
      "have no Card in slot 1 when remove a card in slot 1" in {
          val field1 = field.placeCard(0, 0, 0)
          field1.destroyCard(0, 0).players(0).fieldbar.cardArea.row(0) shouldBe an [EmptyCard]
      }
      "have 5 cards in hand after drawing 1 form deck" in {
        field.drawCard(0).players(0).gamebar.hand.length should be (5)
      }
      "have 80 Hp when reduced by 20" in {
          field.reduceHp(0, 20).players(0).gamebar.hp.value should be(80)
      }
      "have 120 Hp when increased by 20" in {
          field.increaseHp(0, 20).players(0).gamebar.hp.value should be(120)
      }
      "have 0 Mana when reduced by 10" in {
          field.reduceMana(0, 10).players(0).gamebar.mana.value should be(0)
      }
      "have 30 Mana when increased by 20" in {
          field.increaseMana(0, 20).players(0).gamebar.mana.value should be(30)
      }
      "have a Matrix representation" in {
        field.toMatrix().toAString() should be (
          "-------------------------------------------------------------------------------------\n" +
          "\u001b[1mPlayer " + "\u001b[0m" + "\u001b[32;1m|\u001b[0;37m"
            * ((Field.standartFieldWidth - field.players(0).name.length - 1) * field.players(0).gamebar.hp.value/100).asInstanceOf[Float].floor.asInstanceOf[Int]  +
          "\n-------------------------------------------------------------------------------------\n" +
          "\u001b[32mHP: 100 \u001b[0;34mMana: 10\u001b[0;37m                                                 \u001b[0;31mDeck: 4  Friedhof: 0\u001b[0;37m\n " +
          "Brecher (2)      Brecher (2)      Brecher (2)      Brecher (2)                      \n " +
          "atk: 3           atk: 3           atk: 3           atk: 3                           \n " +
          "def: 4           def: 4           def: 4           def: 4                           \n " +
          "Truemmer         Truemmer         Truemmer         Truemmer                         \n " +
          "Legende          Legende          Legende          Legende                          \n"  +
          "-------------------------------------------------------------------------------------\n" +
          field.players(0).fieldbar.toMatrix().toAString() +
          field.players(1).fieldbar.toMatrix().toAString() +
          "\u001b[32mHP: 100 \u001b[0;34mMana: 10\u001b[0;37m                                                 \u001b[0;31mDeck: 4  Friedhof: 0\u001b[0;37m\n " +
          "Brecher (2)      Brecher (2)      Brecher (2)      Brecher (2)                      \n " +
          "atk: 3           atk: 3           atk: 3           atk: 3                           \n " +
          "def: 4           def: 4           def: 4           def: 4                           \n " +
          "Truemmer         Truemmer         Truemmer         Truemmer                         \n " +
          "Legende          Legende          Legende          Legende                          \n"  +
          "-------------------------------------------------------------------------------------\n" +
          "\u001b[1mPlayer " + "\u001b[0m" + "\u001b[32;1m|\u001b[0;37m"
          * ((Field.standartFieldWidth - field.players(1).name.length - 1) * field.players(1).gamebar.hp.value/100).asInstanceOf[Float].floor.asInstanceOf[Int] + "\n" +
          "-------------------------------------------------------------------------------------\n"
        )

      }
    }
  }
}