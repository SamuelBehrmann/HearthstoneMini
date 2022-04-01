import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import model.Healthpoints

class HpTest extends AnyWordSpec with Matchers {
   "Hp " when {
    "not set any value" should {
      val emptyHp = Healthpoints(0)
      "have Value 0" in {
        emptyHp.value should be(0)
      }
      "be empty" in {
        emptyHp.isEmpty() should be(true)
      }
    }
  }
}