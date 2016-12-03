package utils

object PartitionUtils {
  val getPartitionId = (personId: Long) => (personId - 1) / 100
}
