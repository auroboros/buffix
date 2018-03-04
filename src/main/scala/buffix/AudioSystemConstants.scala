package buffix

/**
  * Created by johnmcgill on 3/3/18.
  */
object AudioSystemConstants {
  val BYTES_PER_SAMPLE: Int = 2

  val suggestedOutputLatency: Double = 0.040
  val suggestedInputLatency: Double = 0.100

  //if(System.getProperty("os.name").contains("Windows")) {
  //  this.suggestedOutputLatency = 0.08D;
}
