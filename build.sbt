name := """mars-rover-server"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.12"

libraryDependencies ++= Seq(guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "6.0.0-RC2" % Test,
  "org.scalatest" %% "scalatest" % "3.2.15" % Test,
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
  "org.scalatestplus" %% "mockito-3-4" % "3.2.10.0" % Test,
  "com.typesafe.play" %% "play" % "2.8.18",
  // Database
  "com.h2database" % "h2" % "2.1.214",
  "org.postgresql" % "postgresql" % "42.5.4"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
