package scala

//import model.FieldBar
import model._
@main
def run(): Unit = {
    val field = new Field(5)
    val matrix = new Matrix[Char](58, ' ')
    matrix.insertMatrix(0, 0, field.toString()).printMatrix()
    //TODO: Height and Width müssen variable sein, 
    //TODO: Matrix def if row is empty() 
    //TODO:
}