package model
import util.control.Breaks._

case class Matrix[T](rows: Vector[Vector[T]]):
  def this(size: Int, filling: T) = this(Vector.tabulate(size, size) { (row, col) => filling })
  val size: Int = rows.size
  def cell(row: Int, col: Int): T = rows(row)(col)
  def row(row: Int) = rows(row)
  def fill(filling: T): Matrix[T] = copy(Vector.tabulate(size, size) { (row, col) => filling })
  def replaceCell(row: Int, col: Int, cell: T): Matrix[T] = copy(rows.updated(row, rows(row).updated(col, cell)))

  def insertMatrix(rowStart: Int, colStart: Int, string: String): Matrix[Char] = {
    var matrix = new Matrix(this.size, ' ')
    val charArray = string.toCharArray()
    var row: Int = rowStart
    var col: Int = colStart
    for ( i <- 0 to (charArray.length - 1)) {
        breakable {
            if(charArray(i) == '#') {
                row += 1
                col = colStart
                break
            }
            matrix = matrix.replaceCell(row, col, charArray(i))
            col += 1
        }
    }
    return matrix
  }
