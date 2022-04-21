package model

case class Matrix[T](rows: Vector[Vector[T]]):
  def this(rowSize: Int, colSize: Int, filling: T) = this(Vector.tabulate(rowSize, colSize) { (row, col) => filling })
  val rowSize: Int = rows.size
  val colSize: Int = rows.length
  def cell(row: Int, col: Int): T = rows(row)(col)
  def row(row: Int) = rows(row)
  def fill(filling: T): Matrix[T] = copy(Vector.tabulate(rowSize, colSize) { (row, col) => filling })
  def replaceCell(row: Int, col: Int, cell: T): Matrix[T] = copy(rows.updated(row, rows(row).updated(col, cell)))

  def updateMatrix(rowStart: Int, colStart: Int, stringList: List[String]): Matrix[T] = copy(rows.patch(rowStart, stringList.map((string) =>
    rows(rowStart + stringList.indexOf(string)).patch(colStart, string.toVector.asInstanceOf[IterableOnce[T]], string.length)), stringList.length))

  def updateMatrixWithMatrix(rowStart: Int, colStart: Int, insertMatrix: Matrix[T]): Matrix[T] = copy(rows.patch(rowStart, insertMatrix.rows.map((vector) =>
    rows(rowStart + insertMatrix.rows.indexOf(vector)).patch(colStart, vector.asInstanceOf[IterableOnce[T]], vector.length)), insertMatrix.rows.length))

  def printMatrix() = rows.map((f) => {f.map((char) => print(char)); print("\n")})

  override def toString() = rows.toString