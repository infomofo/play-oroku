name := """play-oroku"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "org.jsoup" % "jsoup" % "1.8.1",
  "com.typesafe.scala-logging"  %%  "scala-logging" % "3.1.0"
)
