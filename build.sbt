name := """video-watcher"""
organization := "com.devraccoon"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala, DebianPlugin)

maintainer in Linux := "Dmytro Mykhailov <dmytro.mykhailov@devraccoon.com>"
packageSummary in Linux := "Simple play application to play video files from a folder"
packageDescription := "Web application for playing video files from folder."

scalaVersion := "2.13.1"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.devraccoon.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.devraccoon.binders._"
