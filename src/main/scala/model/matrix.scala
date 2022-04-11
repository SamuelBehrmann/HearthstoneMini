package model

import scala.compiletime.ops.string

case class Matrix[T](rows: Vector[Vector[T]]):
  def this(size: Int, filling: T) = this(Vector.tabulate(size, size) { (row, col) => filling })
  val size: Int = rows.size
  def cell(row: Int, col: Int): T = rows(row)(col)
  def row(row: Int) = rows(row)
  def fill(filling: T): Matrix[T] = copy(Vector.tabulate(size, size) { (row, col) => filling })
  def replaceCell(row: Int, col: Int, cell: T): Matrix[T] = copy(rows.updated(row, rows(row).updated(col, cell)))
  
  def updateMatrix(rowStart: Int, colStart: Int, stringList: List[String]): Matrix[T] = copy(rows.patch(rowStart, stringList.map((string) => rows(rowStart + stringList.indexOf(string)).patch(colStart, string.toVector.asInstanceOf[IterableOnce[T]], string.length)), stringList.length))

  def printMatrix() = rows.map((f) => {f.map((char) => print(char)); print("\n")})
