val scala3Version = "3.1.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "hearstonemini",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.11",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test"

    jacocoReportSettings := JacocoReportSettings(
      "Jacoco Coverage Report",
      None,
      JacocoThresholds(),
      Seq(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML), // note XML formatter
      "utf-8"
      )
    )
