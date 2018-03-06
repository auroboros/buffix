package buffix

/**
  * Created by johnmcgill on 3/5/18.
  */
case class SignalBuffer(val size: Int) {
  val doubleBuffer = new Array[Double](size)

  def foreachSample(processor: Int => Unit) = doubleBuffer.indices foreach processor

  def copyToRichBuffer(outFormatBuffer: IoBuffer): Unit =
    doubleBuffer.indices foreach (index => outFormatBuffer.putSample(index, doubleBuffer(index)))
}
