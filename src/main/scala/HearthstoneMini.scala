package scala

import model._

@main
def run(): Unit = {

    val field = new Field(size = 5, player1 = "Heinrich", player2 = "Peter")
    val karte = new Card("Der Brecher", 2, 4, 3, "Zerstoerung", "rare")

    print("Stage: 1\n" + field)
    val newField = field.placeCardPlayer1(1, 5)
    print("Stage: 2\n" + newField)
    val newField1 = newField.placeCardPlayer1(3, 3)
    print("Stage: 3\n" + newField1)
    val newField2 = newField1.reduceHp(20)
    print("Stage: 4\n" + newField2)
    val newField3 = newField2.drawCardPlayer1()
    val newField4 = newField3.drawCardPlayer1()
    val newField5 = newField4.drawCardPlayer1()
    print("Stage: 5\n" + newField5)
    //TODO: Place card, ohne card parameter. sollte eine von der hand nehmen
    //TODO: player mit auto index
    //TODO: methoden umstellen sodass eine spieler-ID übergebenwerden kann
    //TODO: Friedhof und Deck müssen noch mit aufs Spielfeld
    //TODO: deck mischen implementieren
    //TODO: Tests schreiben
    //TODO: Scalable
}
