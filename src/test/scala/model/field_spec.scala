package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec with Matchers {
  "A Field" when {
    "created" should {
      val field = new Field(slotNum = 5, players = List[Player](Player(id = 1).resetAndIncreaseMana(), Player(id = 2)))
      val field1 = new Field(5)
      "be created with empty constructor" in {
        val field0 = Field()
        field0.slotNum should be(5)
      }
      "be created using default fieldsize 5 and 2 player names" in {
        field.matrix.colSize should be(Field.standartFieldWidth)
      }
      "have a Card in slot 1 after placed 1 card in slot 1 from hand" in {
        field.placeCard(0, 0).players(0).gamebar.hand.length should be(3)
        field.placeCard(0, 0).players(0).fieldbar.cardArea.row(0) shouldBe an[Card]
      }
      "have no Card in slot 1 when remove a card in slot 1" in {
        val field1 = field.placeCard(0, 0)
        field1.destroyCard(0, 0).players(0).fieldbar.cardArea.row(0) shouldBe an[EmptyCard]
      }
      "have 5 cards in hand after drawing 1 form deck" in {
        field.drawCard().players(0).gamebar.hand.length should be(5)
      }
      "have 10 Hp when reduced by 20" in {
        field.reduceHp(0, 20).players(0).gamebar.hp.value should be(10)
      }
      "have 50 Hp when increased by 20" in {
        field.increaseHp(20).players(0).gamebar.hp.value should be(50)
      }
      "have 0 Mana when reduced by 10" in {
        field.reduceMana(10).players(0).gamebar.mana.value should be(0)
      }
      "have 1 Mana when increased" in {
        field.increaseMana(20).players(1).gamebar.mana.value should be(1)
      }
      "switch the active player" in {
        val fieldAfterMove = field.switchPlayer()
        fieldAfterMove.players should be(field.players.reverse)
        val fieldAfter2ndMove = fieldAfterMove.switchPlayer()
        fieldAfter2ndMove.players(0).gamebar.mana.value should be(3)
      }
      "return the active player" in {
        field.getActivePlayer() should be(field.players(0))
      }
      "return player with id 1" in {
        field.getPlayerById(1) should be(field.players(0))
      }
      "have a Matrix representation" in {
        field1.toMatrix().toAString() should be(
          "-------------------------------------------------------------------------------------\n" +
            "\u001b[1mPlayer " + "\u001b[0m" + "\u001b[32;1m|\u001b[0;37m"
            * ((Field.standartFieldWidth - field.players(0).name.length - 1) * field.players(0).gamebar.hp.value / field.players(0).gamebar.hp.max).asInstanceOf[Float].floor.asInstanceOf[Int] +
            "\n-------------------------------------------------------------------------------------\n" +
            "\u001b[32mHP: 30 \u001b[0;34mMana: 1\u001b[0;37m                                                   \u001b[0;31mDeck: 4  Friedhof: 0\u001b[0;37m\n " +
            "Brecher (2)      Brecher (2)      Brecher (2)      Brecher (2)                      \n " +
            "atk: 3           atk: 3           atk: 3           atk: 3                           \n " +
            "def: 4           def: 4           def: 4           def: 4                           \n " +
            "Truemmer         Truemmer         Truemmer         Truemmer                         \n " +
            "Legende          Legende          Legende          Legende                          \n" +
            "-------------------------------------------------------------------------------------\n" +
            field.players(0).fieldbar.toMatrix().toAString() +
            field.players(1).fieldbar.toMatrix().toAString() +
            "\u001b[32mHP: 30 \u001b[0;34mMana: 1\u001b[0;37m                                                   \u001b[0;31mDeck: 4  Friedhof: 0\u001b[0;37m\n " +
            "Brecher (2)      Brecher (2)      Brecher (2)      Brecher (2)                      \n " +
            "atk: 3           atk: 3           atk: 3           atk: 3                           \n " +
            "def: 4           def: 4           def: 4           def: 4                           \n " +
            "Truemmer         Truemmer         Truemmer         Truemmer                         \n " +
            "Legende          Legende          Legende          Legende                          \n" +
            "-------------------------------------------------------------------------------------\n" +
            "\u001b[1mPlayer " + "\u001b[0m" + "\u001b[32;1m|\u001b[0;37m"
            * ((Field.standartFieldWidth - field.players(1).name.length - 1) * field.players(1).gamebar.hp.value / field.players(1).gamebar.hp.max).asInstanceOf[Float].floor.asInstanceOf[Int] + "\n" +
            "-------------------------------------------------------------------------------------\n"
        )
      }
      "have reset and increased mana" in {
        val fieldAfterMove = field1.resetAndIncreaseMana()
        fieldAfterMove.players(0).gamebar.mana.value should be(2)
        fieldAfterMove.players(0).gamebar.mana.max should be(2)
        fieldAfterMove.players(1).gamebar.mana.value should be(2)
        fieldAfterMove.players(1).gamebar.mana.max should be(2)
      }
    }
     "when hp value is set" in {
       Field().setHpValues(34).players(0).gamebar.hp.value should be (34)
     }
    "when mana value is set" in {
      Field().setManaValues(45).players(1).gamebar.mana.value should be (45)
    }
  }
}