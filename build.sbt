ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8"
libraryDependencies += "org.scalamock" %% "scalamock" % "5.1.0" % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
libraryDependencies += "io.github.cquiroz" %% "scala-java-time" % "2.2.2"


lazy val root = (project in file("."))
  .settings(
    name := "Scala_101"
  )
