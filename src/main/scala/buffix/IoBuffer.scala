package buffix

import java.nio.ByteBuffer

/**
  * Created by johnmcgill on 3/4/18.
  */
case class IoBuffer() {
  val MAX_16_BIT = Short.MaxValue

  val buffer: Array[Byte] = new Array[Byte](AudioConfig.inBufferSize)
  val wrappedBuffer = ByteBuffer.wrap(buffer)
  val shortBuffer = wrappedBuffer.asShortBuffer()
  val capacityInSamples = wrappedBuffer.asShortBuffer().capacity()

  def putSample(index: Int, sample: Double) = shortBuffer.put(index, (MAX_16_BIT * sample).toShort)

  def foreachSample(processor: Int => Unit) = 0 until capacityInSamples foreach processor
}
