// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Mathers._
import src.main.scala.model.mana.scala

class HearthstoneminiTest extends WordSpec with Matchers {
  "Mana" when {
    "not set any value " should {
      val emptyMana = Mana(0)
      "have Value 0" in {
        emptyMana.value should be(0)
      }
      "not be set" in {
        emptyMana.isEmpty should be(false)
      }
    }
  }
}