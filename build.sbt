name := "scala_test"

scalaVersion in ThisBuild := "2.11.8"

lazy val root = project.in(file("."))
  .aggregate(testJVM, testJS)

lazy val test = crossProject.in(file("."))
  .jsSettings(
    libraryDependencies += "com.github.japgolly.scalajs-react" %%% "core" % "0.11.1",
    jsDependencies ++= Seq(
      "org.webjars.bower" % "react" % "15.0.2"
        / "react-with-addons.js"
        minified "react-with-addons.min.js"
        commonJSName "React",

      "org.webjars.bower" % "react" % "15.0.2"
        / "react-dom.js"
        minified "react-dom.min.js"
        dependsOn "react-with-addons.js"
        commonJSName "ReactDOM",

      "org.webjars.bower" % "react" % "15.0.2"
        / "react-dom-server.js"
        minified "react-dom-server.min.js"
        dependsOn "react-dom.js"
        commonJSName "ReactDOMServer")
  )
  .jvmSettings(
    version := "1.0",
    libraryDependencies += "com.typesafe.akka" %% "akka-http-core" % "2.4.4",
    libraryDependencies += "com.typesafe.akka" %% "akka-http-experimental" % "2.4.4"
  )

lazy val testJVM = test.jvm
lazy val testJS = test.js