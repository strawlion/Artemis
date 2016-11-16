package utils

import java.nio.file.{Paths, Files}

import org.apache.spark.rdd.RDD
import org.apache.spark.sql._

object CsvUtils {

    def getRdd(sparkSession: SparkSession, path: String): RDD[Row] = {
      getDataframe(sparkSession, path).rdd
    }

    def getDataframe(sparkSession: SparkSession, path: String): Dataset[Row] = {
      sparkSession.read
        .format("csv")
        .option("header", "true")
        .option("nullValue", "")
        .csv(path)
    }

  def writeParquetFromCsv(sparkSession: SparkSession, csvPath: String, parquetPath: String, overwrite: Boolean): Dataset[Row] = {
    val shouldWriteFile = !Files.exists(Paths.get(parquetPath)) || overwrite
    if (shouldWriteFile) {
      getDataframe(sparkSession, csvPath).write.parquet(parquetPath)
    }
    sparkSession.read
      .parquet(parquetPath)
  }

}
