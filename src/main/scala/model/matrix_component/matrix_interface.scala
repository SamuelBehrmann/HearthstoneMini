package model.matrix_component

import model.matrix_component.matrixImpl.Matrix

trait MatrixInterface {
  val rowSize: Int
  val colSize: Int

  def updateMatrix(rowStart: Int, colStart: Int, stringList: List[String]): Matrix[String]
  def updateMatrixWithMatrix(rowStart: Int, colStart: Int, insertMatrix: Matrix[String]): Matrix[String]
  def toAString(): String
}
