val scala3Version = "3.1.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "hearstonemini",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "scalactic" % "3.2.10"
    libraryDependencies += "org.scalameta" %% "scalactic" % "3.2.10" % "test"
  )
