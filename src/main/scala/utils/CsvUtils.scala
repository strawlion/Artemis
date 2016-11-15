package utils

import org.apache.spark.rdd.RDD
import org.apache.spark.sql._

object CsvUtils {

    def getRdd(sparkSession: SparkSession, path: String): RDD[Row] = {
      sparkSession.read
        .format("csv")
        .option("header", "true")
        .option("nullValue", "")
        .csv(path)
        .rdd
    }
  def getDataframe(sparkSession: SparkSession, path: String): Dataset[Row] = {
    sparkSession.read
      .format("csv")
      .option("header", "true")
      .option("nullValue", "")
      .csv(path)
  }
//
//    def loadCSVAsTable(sqlContext: SQLContext, path: String): DataFrame = {
//      loadCSVAsTable(sqlContext, path, inferTableNameFromPath(path))
//    }
//
//    private val pattern = "(\\w+)(\\.csv)?$".r.unanchored
//    def inferTableNameFromPath(path: String) = path match {
//      case pattern(filename, extension) => filename
//      case _ => path
//    }

}
