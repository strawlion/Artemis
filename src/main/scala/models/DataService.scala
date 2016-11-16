package models

import com.google.inject.Singleton
import org.apache.spark.sql.{SparkSession, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}



object DataService {
  val spark = SparkSession
              .builder()
              .appName("Artemis")
              .master("local")
              .getOrCreate()
}
