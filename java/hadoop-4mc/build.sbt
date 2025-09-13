ThisBuild / organization := "com.fing.fourmc"
ThisBuild / version      := "3.0.0"
ThisBuild / scalaVersion := "2.12.20"

lazy val root = (project in file("."))
  .settings(
    name := "hadoop-4mc",
    // Java-only project: don't pull scala-library
    autoScalaLibrary := false,
    crossPaths := false,

    // Java compiler settings
    Compile / javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-encoding", "UTF-8"),
    Test / javacOptions ++= (Compile / javacOptions).value,

    // Dependencies (match Maven pom.xml)
    libraryDependencies ++= Seq(
      "commons-lang" % "commons-lang" % "2.4",
      "commons-logging" % "commons-logging" % "1.1.3",
      "org.apache.commons" % "commons-lang3" % "3.9",
      // Hadoop provided at runtime
      "org.apache.hadoop" % "hadoop-client" % "1.1.2" % Provided,
      // Tests
      "junit" % "junit" % "4.13.1" % Test
    ),

    // Publishing
    publishMavenStyle := true,
    pomIncludeRepository := { _ => false },
    licenses += ("BSD-2-Clause", url("https://opensource.org/licenses/BSD-2-Clause")),
    homepage := Some(url("https://github.com/fingltd/4mc"))
  )

