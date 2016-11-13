import java.text.SimpleDateFormat

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SQLContext
import utils.CsvUtils

import scala.io.Source

object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
    import org.apache.log4j.Logger
    import org.apache.log4j.Level
    Logger.getLogger("org").setLevel(Level.WARN)
    Logger.getLogger("akka").setLevel(Level.WARN)

    val sc = createContext
//
//    /** initialize loading of data */
//    val (medication, labResult, diagnostic) = loadRddRawData(sqlContext)
//    val (candidateMedication, candidateLab, candidateDiagnostic) = loadLocalRawData
  }

  /**
   * load the sets of string for filtering of medication
   * lab result and diagnostics
   *
   * @return
   */
  def loadLocalRawData: (Set[String], Set[String], Set[String]) = {
    val candidateMedication = Source.fromFile("data/med_filter.txt").getLines().map(_.toLowerCase).toSet[String]
    val candidateLab = Source.fromFile("data/lab_filter.txt").getLines().map(_.toLowerCase).toSet[String]
    val candidateDiagnostic = Source.fromFile("data/icd9_filter.txt").getLines().map(_.toLowerCase).toSet[String]
    (candidateMedication, candidateLab, candidateDiagnostic)
  }


  def loadRddRawData(sqlContext: SQLContext): (RDD[Any], RDD[Any], RDD[Any]) = {
    /** You may need to use this date format. */
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX")

    /** load data using Spark SQL into three RDDs and return them
      * Hint: You can utilize edu.gatech.cse8803.ioutils.CSVUtils and SQLContext.
      *
      * Notes:Refer to model/models.scala for the shape of Medication, LabResult, Diagnostic data type.
      *       Be careful when you deal with String and numbers in String type.
      *       Ignore lab results with missing (empty or NaN) values when these are read in.
      *       For dates, use Date_Resulted for labResults and Order_Date for medication.
      * */

    /** TODO: implement your own code here and remove existing placeholder code below */

    CsvUtils.loadCSVAsTable(sqlContext,"data/medication_orders_INPUT.csv","MedicationTable")
    CsvUtils.loadCSVAsTable(sqlContext,"data/lab_results_INPUT.csv","LabTable")
    CsvUtils.loadCSVAsTable(sqlContext,"data/encounter_INPUT.csv","IDTable")
    CsvUtils.loadCSVAsTable(sqlContext,"data/encounter_dx_INPUT.csv","DiagTable")

//    val medication: RDD[Medication] = sqlContext.sql("SELECT Member_ID AS patientID, Order_Date AS date, Drug_Name AS medicine FROM MedicationTable")
//      .map(row => Medication(row(0).asInstanceOf[String], dateFormat.parse(row(1).asInstanceOf[String]), row(2).asInstanceOf[String].toLowerCase))
//
//    val labResult: RDD[LabResult] = sqlContext.sql("SELECT Member_ID AS patientID, Date_Resulted AS date, Result_Name AS testName, Numeric_Result as value FROM LabTable WHERE Numeric_Result!=''")
//      .map(row => LabResult(row(0).asInstanceOf[String], dateFormat.parse(row(1).asInstanceOf[String]), row(2).asInstanceOf[String].toLowerCase, row(3).asInstanceOf[String].filterNot(",".toSet).toDouble))
//
//    val diagnostic: RDD[Diagnostic] = sqlContext.sql("SELECT IDTable.Member_ID AS patientID, IDTable.Encounter_DateTime AS date, DiagTable.code AS code FROM IDTable INNER JOIN DiagTable ON IDTable.Encounter_ID= DiagTable.Encounter_ID")
//      .map(row => Diagnostic(row(0).asInstanceOf[String], dateFormat.parse(row(1).asInstanceOf[String]), row(2).asInstanceOf[String].toLowerCase))

    (medication, labResult, diagnostic)
  }

  def createContext(appName: String, masterUrl: String): SparkContext = {
    val conf = new SparkConf().setAppName(appName).setMaster(masterUrl)
    new SparkContext(conf)
  }

  def createContext(appName: String): SparkContext = createContext(appName, "local")

  def createContext: SparkContext = createContext("Artemis", "local")
}