package utils

import org.apache.spark.sql._
import com.databricks.spark.csv.CsvContext

object CsvUtils {

    def loadCSVAsTable(sqlContext: SQLContext, path: String, tableName: String): DataFrame = {
      val data = sqlContext.csvFile(path)
      data.registerTempTable(tableName)
      data
    }

    def loadCSVAsTable(sqlContext: SQLContext, path: String): DataFrame = {
      loadCSVAsTable(sqlContext, path, inferTableNameFromPath(path))
    }

    private val pattern = "(\\w+)(\\.csv)?$".r.unanchored
    def inferTableNameFromPath(path: String) = path match {
      case pattern(filename, extension) => filename
      case _ => path
    }

}
