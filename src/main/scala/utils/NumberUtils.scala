package utils

object NumberUtils {
  def toLong(s: String): Option[Long] = {
    try {
      Option(s.toLong)
    } catch {
      case e: Exception => null
    }
  }

  def toFloat(s: String): Option[Float] = {
    try {
      Option(s.toFloat)
    } catch {
      case e: Exception => null
    }
  }
}
