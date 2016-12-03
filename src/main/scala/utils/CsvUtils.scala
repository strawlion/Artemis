package utils

import java.nio.file.{Paths, Files}

import org.apache.spark.rdd.RDD
import org.apache.spark.sql._

object CsvUtils {

    import org.apache.spark.sql.functions._
    private val getPartitionId = udf(PartitionUtils.getPartitionId)

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
    val dataframe = if (shouldWriteFile) getDataframe(sparkSession, csvPath) else null
    writeParquetFromDataFrame(sparkSession, dataframe, parquetPath, overwrite)
  }

  def writeParquetFromDataFrame(sparkSession: SparkSession, dataframe: Dataset[Row], parquetPath: String, overwrite: Boolean): Dataset[Row] = {
    val shouldWriteFile = !Files.exists(Paths.get(parquetPath)) || overwrite
    if (shouldWriteFile) {
      dataframe
        .sort("person_id")
        .withColumn("partition_id", getPartitionId(col("person_id")))
        .write
        .partitionBy("partition_id")
        .parquet(parquetPath)
    }

    sparkSession.read
      .option("spark.sql.parquet.cacheMetadata", true)
      .parquet(parquetPath)
  }


}
