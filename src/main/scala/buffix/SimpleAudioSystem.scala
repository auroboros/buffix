package buffix

/**
  * Created by johnmcgill on 3/6/18.
  */
object SimpleAudioSystem {
  implicit def richBufferToByteArray(richBuffer: IoBuffer): Array[Byte] = richBuffer.buffer

  val lineOut = LineManager.startOutputStream().get
  val lineIn = LineManager.startInputStream().get

  val outBuffer = IoBuffer()

  def write(signalBuffer: SignalBuffer) = {
    signalBuffer.copyToRichBuffer(outBuffer)
    lineOut.write(outBuffer, 0, outBuffer.length)
  }

}
