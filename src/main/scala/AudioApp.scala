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

  val outline = LineManager.startOutputStream().get
  val inline = LineManager.startInputStream().get
//  line.read(bytes, 0, bytes.length)

  println("writing noise in a loop")

  val testBuffer: Array[Byte] = new Array[Byte](AudioConfig.outBufferSize)
  val randgen = new Random()

  1 to Int.MaxValue foreach(_ => {
    randgen.nextBytes(testBuffer)
    outline.write(testBuffer, 0, testBuffer.length)
  })

  private def channelsAreAvailable(lines: Array[Line.Info]): Boolean = scanMaxChannels(lines) > 0

  private def scanMaxChannels(lines: Array[Line.Info]): Int =
    (0 +: lines.map {
      case info: DataLine.Info => scanMaxChannels(info)
      case _ => 0
    }).max

  private def scanMaxChannels(info: DataLine.Info): Int = (0 +: info.getFormats.map(_.getChannels)).max
}