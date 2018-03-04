import javax.sound.sampled.AudioFormat

/**
  * Created by johnmcgill on 3/3/18.
  */
object AudioConfig {
  val samplingRate = 44100 // TODO
  val nChannels = 2 // TODO
  val USE_BIG_ENDIAN = false // TODO

  val format = new AudioFormat(samplingRate, 16, nChannels, true, USE_BIG_ENDIAN)

  val inBufferSize: Int = calculateBufferSize(AudioSystemConstants.suggestedInputLatency, AudioConfig.format)
  val outBufferSize: Int = calculateBufferSize(AudioSystemConstants.suggestedOutputLatency, AudioConfig.format)

  private def calculateBufferSize(suggestedOutputLatency: Double, format: AudioFormat): Int = {
    val numFrames: Int = (suggestedOutputLatency * format.getFrameRate).toInt
    val numBytes: Int = numFrames * AudioConfig.nChannels * AudioSystemConstants.BYTES_PER_SAMPLE

    numBytes
  }
}
