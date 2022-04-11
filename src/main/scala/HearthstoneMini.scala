package scala

//import model.FieldBar
import model._
@main
def run(): Unit = {
    val field = new Field(5)
    val matrix = new Matrix[String](60, " ")
    val gameBar = new GameBar()
    var vector = matrix.updateMatrix(0, 0, field.printField().split("#").toList)

    vector.printMatrix()

    //TODO: Height and Width müssen variable sein, 
    //TODO: Matrix dont print empty rows
    //TODO: Create Empty Card / Placeholder
    //TODO: Matrix Functional Programming
    //TODO: Adjust Field Design to Card design
}