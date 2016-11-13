name := "Artemis"

version := "1.0"

scalaVersion := "2.10.6"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  "Twitter Maven" at "https://maven.twttr.com"
)

libraryDependencies ++= Seq(
  "org.apache.spark"  %% "spark-core"              % "2.0.1",
  "org.apache.spark"  %% "spark-sql"               % "2.0.1" excludeAll(ExclusionRule(organization = "org.slf4j")),
  "com.databricks"    %% "spark-csv"               % "1.5.0" excludeAll(ExclusionRule(organization = "org.slf4j")),
  "com.twitter.finatra" %% "finatra-http"            % "2.1.6" excludeAll(ExclusionRule(organization = "org.slf4j"))
)