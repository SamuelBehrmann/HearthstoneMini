// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import model.Mana

class ManaTest extends AnyWordSpec with Matchers {
  "Mana" when {
    "not set any value " should {
      val emptyMana = Mana()
      "have Value 0" in {
        emptyMana.value should be(0)
      }
      "not be set" in {
        emptyMana.isEmpty() should be(true)
      }
    }
  }
}