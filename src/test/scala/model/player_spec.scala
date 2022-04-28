package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class player_spec extends AnyWordSpec with Matchers {
  "A Player" when {
    val player = new Player(id = 1)
    "created" should {
      "look like this" in {
        player.toMatrix().toAString() should be ("\u001b[1mPlayer " + "\u001b[0m" + "\u001b[32;1m|\u001b[0;37m"
            * ((Field.standartFieldWidth - player.name.length - 1) * player.gamebar.hp.value/100).asInstanceOf[Float].floor.asInstanceOf[Int]  +
          "\n-------------------------------------------------------------------------------------\n" +
          "\u001b[32mHP: 100 \u001b[0;34mMana: 10\u001b[0;37m                                                 \u001b[0;31mDeck: 4  Friedhof: 0\u001b[0;37m\n " +
          "Brecher (2)      Brecher (2)      Brecher (2)      Brecher (2)                      \n " +
          "atk: 3           atk: 3           atk: 3           atk: 3                           \n " +
          "def: 4           def: 4           def: 4           def: 4                           \n " +
          "Truemmer         Truemmer         Truemmer         Truemmer                         \n " +
          "Legende          Legende          Legende          Legende                          \n"  +
          "-------------------------------------------------------------------------------------\n" +
          player.fieldbar.toMatrix().toAString() )
      }

    }
  }
}