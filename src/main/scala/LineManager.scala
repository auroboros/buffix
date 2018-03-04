import javax.sound.sampled._

/**
  * Created by johnmcgill on 3/3/18.
  */
object LineManager {
  private val format = AudioConfig.format

  def startOutputStream(): Option[SourceDataLine] = {

    val info: DataLine.Info = new DataLine.Info(classOf[SourceDataLine], format)
    if (!AudioSystem.isLineSupported(info)) {
      println("JavaSoundOutputStream - not supported." + format)
      None
    } else {
      val line = getDataLine(info).asInstanceOf[SourceDataLine]
      line.open(format, AudioConfig.outBufferSize)
      println("Output buffer size = " + AudioConfig.outBufferSize + " bytes.")
      line.start()
      Some(line)
    }
  }

  def startInputStream(): Option[TargetDataLine] = {
    val info: DataLine.Info = new DataLine.Info(classOf[TargetDataLine], format)
    if (!AudioSystem.isLineSupported(info)) {
      println("JavaSoundInputStream - not supported." + format)
      None
    } else {
      val line = getDataLine(info).asInstanceOf[TargetDataLine]
      line.open(format, AudioConfig.inBufferSize)
      println("Input buffer size = " + AudioConfig.inBufferSize + " bytes.")
      line.start()
      Some(line)
    }
  }

  @throws(classOf[LineUnavailableException])
  private def getDataLine(info: DataLine.Info, mixer: Option[Mixer] = None): Line =
    mixer.map(_.getLine(info)).orElse(Some(AudioSystem.getLine(info))).get
}
