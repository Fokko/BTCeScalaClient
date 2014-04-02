name := "BTCeScalaClient"

version := "1.0"

scalaVersion := "2.10.2"

resolvers ++= Seq(
    "jBCrypt Repository" at "http://repo1.maven.org/maven2/org/",
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.6.4",
  "org.slf4j" % "slf4j-simple" % "1.6.4",
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.0",
  "org.json4s" %% "json4s-native" % "3.2.5",
  "org.reactivemongo" %% "reactivemongo" % "0.10.0",
  "commons-codec" % "commons-codec" % "1.2"
)