import java.util.Random
import javax.sound.sampled._

/**
  * Created by johnmcgill on 3/3/18.
  */
object AudioApp extends App {
  val mixerInfos = AudioSystem.getMixerInfo

  mixerInfos.foreach(println)

  val mixers = mixerInfos.map(AudioSystem.getMixer)

  val defaultInputDevice = mixers.find(mixer => channelsAreAvailable(mixer.getTargetLineInfo))
  val defaultOutputDevice = mixers.find(mixer => channelsAreAvailable(mixer.getSourceLineInfo))

// REPLACING FOLLOWING 3 LINES...

//  private val audioDevice: AudioDeviceManager = new JavaSoundAudioDevice

//  val audioOutput: AudioDeviceOutputStream = audioDevice.createOutputStream(audioDevice.getDefaultOutputDeviceID, config.samplingRate, config.nOutChannels)
//  val audioInput: AudioDeviceInputStream = audioDevice.createInputStream(audioDevice.getDefaultInputDeviceID, config.samplingRate, config.nOutChannels)

// WITH...
  val maybeOutline = startOutputStream()

  maybeOutline.foreach(outline => {
    println("writing noise in a loop")

    val testBuffer: Array[Byte] = new Array[Byte](AudioConfig.bufferSize)
    val randgen = new Random()

    1 to Int.MaxValue foreach(_ => {
      randgen.nextBytes(testBuffer)
      outline.write(testBuffer, 0, AudioConfig.bufferSize)
    })
  })

  // TODO: startInputStream()

  private def startOutputStream(): Option[SourceDataLine] = {

    val format = AudioConfig.format

    val info: DataLine.Info = new DataLine.Info(classOf[SourceDataLine], format)
    if (!AudioSystem.isLineSupported(info)) {
      println("JavaSoundOutputStream - not supported." + format)
      None
    } else {
      val line = getDataLine(info).asInstanceOf[SourceDataLine]
      line.open(format, AudioConfig.bufferSize)
      println("Output buffer size = " + AudioConfig.bufferSize + " bytes.")
      line.start()
      Some(line)
    }
  }

  @throws(classOf[LineUnavailableException])
  private def getDataLine(info: DataLine.Info, mixer: Option[Mixer] = None): Line =
    mixer.map(_.getLine(info)).orElse(Some(AudioSystem.getLine(info))).get

  private def channelsAreAvailable(lines: Array[Line.Info]): Boolean = scanMaxChannels(lines) > 0

  private def scanMaxChannels(lines: Array[Line.Info]): Int =
    (0 +: lines.map {
      case info: DataLine.Info => scanMaxChannels(info)
      case _ => 0
    }).max

  private def scanMaxChannels(info: DataLine.Info): Int = (0 +: info.getFormats.map(_.getChannels)).max
}