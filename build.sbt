lazy val root = (project in file(".")).enablePlugins(PlayScala)

name := "play-testbench"
version := "1.0"
scalaVersion := "2.11.7"
routesGenerator := InjectedRoutesGenerator // needed to be able to use classes (instead of objects) as controllers

libraryDependencies ++= Seq(
  filters,
  jdbc,
  evolutions,
  "com.typesafe.play" %% "anorm" % "2.4.0",
  "org.postgresql" % "postgresql" % "9.4-1205-jdbc42"
)
