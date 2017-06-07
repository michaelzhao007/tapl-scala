enablePlugins(ScalaJSPlugin)

name := "tapl-scala"
scalaVersion := "2.12.2"

scalaJSUseMainModuleInitializer := true

libraryDependencies += "org.scala-lang.modules" %%% "scala-parser-combinators" % "1.0.7-SNAPSHOT"

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.2"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
