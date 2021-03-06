ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "mini_shopping_cart",
    organization := "com.mini.shop",
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "fastparse" % "2.2.2",
      "pl.newicom.scalexpr" %% "scalexpr" % "0.1.5",
      "org.scalatest" %% "scalatest" % "3.2.10" % Test,
      "org.scalatestplus" %% "mockito-3-4" % "3.2.1.0" % Test
    )
  )

