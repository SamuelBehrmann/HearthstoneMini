import play.api.libs.json._
import java.io.{File, FileInputStream}
import model._

val stream = new FileInputStream("src/main/scala/model/jsonStuff/cards.json")
val json: JsValue = Json.parse(stream)
val allCards = json.as[List[CardType]]
val deck = allCards.filter(_.isInstanceOf[Card]).take(30)

print(deck.toString)