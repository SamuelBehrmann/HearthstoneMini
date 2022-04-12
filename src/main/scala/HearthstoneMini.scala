package scala

import model._
@main
def run(): Unit = {
    val field = new Field(new Matrix[String](60, ""), 5)
    val karte = new Card("Der Brecher", 2, 4,3, "Zerstörung", "rare")
    val player1 = new Player("heinrich", new FieldBar(5), new GameBar())

    print(field.placeCard(player1,0,karte).cardArea.row)

    //vector.printMatrix()

    //TODO: Height and Width müssen variable sein,
    //TODO: Matrix dont print empty rows
    //TODO: Create Empty Card / Placeholder
    //TODO: Matrix Functional Programming
    //TODO: Adjust Field Design to Card design
}