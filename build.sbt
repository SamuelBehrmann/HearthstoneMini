import sbt.Keys.libraryDependencies

val scala3Version = "3.1.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "hearstonemini",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.11",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test",
    libraryDependencies += "com.lihaoyi" %% "upickle" % "1.6.0",
    libraryDependencies += "org.json4s" %% "json4s-jackson" % "4.0.5",
    // SBT
    libraryDependencies += "com.lihaoyi" %% "os-lib" % "0.8.1"

  )

