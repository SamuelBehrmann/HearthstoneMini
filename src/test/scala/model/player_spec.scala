package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerSpec extends AnyWordSpec with Matchers {
  "A Player" when {
    val player1 = new Player(id = 1)
    val player2 = new Player(id = 2)
    "when created player with ID = 1" should {
      "look like this" in {
        player1.toMatrix().toAString() should be ("\u001b[1mPlayer " + "\u001b[0m" + "\u001b[32;1m|\u001b[0;37m"
            * ((Field.standartFieldWidth - player1.name.length - 1) * player1.gamebar.hp.value/100).asInstanceOf[Float].floor.asInstanceOf[Int]  +
          "\n-------------------------------------------------------------------------------------\n" +
          "\u001b[32mHP: 100 \u001b[0;34mMana: 10\u001b[0;37m                                                 \u001b[0;31mDeck: 4  Friedhof: 0\u001b[0;37m\n " +
          "Brecher (2)      Brecher (2)      Brecher (2)      Brecher (2)                      \n " +
          "atk: 3           atk: 3           atk: 3           atk: 3                           \n " +
          "def: 4           def: 4           def: 4           def: 4                           \n " +
          "Truemmer         Truemmer         Truemmer         Truemmer                         \n " +
          "Legende          Legende          Legende          Legende                          \n"  +
          "-------------------------------------------------------------------------------------\n" +
          player1.fieldbar.toMatrix().toAString() )
      }
    }
    "when created player with ID = 2" should {
      "look like this" in {
        player2.toMatrix().toAString() should be (player2.fieldbar.toMatrix().toAString() +
          "\u001b[32mHP: 100 \u001b[0;34mMana: 10\u001b[0;37m                                                 \u001b[0;31mDeck: 4  Friedhof: 0\u001b[0;37m\n " +
          "Brecher (2)      Brecher (2)      Brecher (2)      Brecher (2)                      \n " +
          "atk: 3           atk: 3           atk: 3           atk: 3                           \n " +
          "def: 4           def: 4           def: 4           def: 4                           \n " +
          "Truemmer         Truemmer         Truemmer         Truemmer                         \n " +
          "Legende          Legende          Legende          Legende                          \n"  +
          "-------------------------------------------------------------------------------------\n" +
          "\u001b[1mPlayer " + "\u001b[0m" + "\u001b[32;1m|\u001b[0;37m"
          * ((Field.standartFieldWidth - player2.name.length - 1) * player2.gamebar.hp.value/100).asInstanceOf[Float].floor.asInstanceOf[Int] + "\n" +
          "-------------------------------------------------------------------------------------\n")

      }
    }
    "when placing a card" in {
      player1.placeCard(2,2).fieldbar.cardArea.row(2) shouldBe an [Card]
    }
    "when drawing a card" in {
      player1.drawCard().gamebar.hand.length.intValue should be (5)
    }
    "when destroying a card" in {
      player1.placeCard(2,2).destroyCard(2).gamebar.friedhof.length should be (1)
    }
    "when reducing hp" in {
      player1.reduceHp(20).gamebar.hp.value should be (80)
    }
    "when increasing hp" in {
      player1.increaseHp(20).gamebar.hp.value should be (120)
    }
    "when reducing mana" in {
      player1.reduceMana(10).gamebar.mana.value should be (0)
    }
    "when increasing mana" in {
      player1.increaseMana(50).gamebar.mana.value should be (60)
    }
  }
}