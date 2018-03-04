package buffix

import java.nio.ByteBuffer
import java.util.Random
import javax.sound.sampled.{AudioSystem, DataLine, Line}

import buffix.ugen.Sine

/**
  * Created by johnmcgill on 3/3/18.
  */
object AudioApp extends App {
  implicit def richBufferToByteArray(richBuffer: RichBuffer): Array[Byte] = richBuffer.buffer

  val mixerInfos = AudioSystem.getMixerInfo

  mixerInfos.foreach(println)

  val mixers = mixerInfos.map(AudioSystem.getMixer)

  val defaultInputDevice = mixers.find(mixer => channelsAreAvailable(mixer.getTargetLineInfo))
  val defaultOutputDevice = mixers.find(mixer => channelsAreAvailable(mixer.getSourceLineInfo))

  val lineOut = LineManager.startOutputStream().get
  val lineIn = LineManager.startInputStream().get

  println("writing noise in a loop")

  val sineGen = Sine()

  1 to Int.MaxValue foreach(_ => {
    sineGen.nextBuffer()
    lineOut.write(sineGen.buffer, 0, sineGen.buffer.length)
  })

  private def channelsAreAvailable(lines: Array[Line.Info]): Boolean = scanMaxChannels(lines) > 0

  private def scanMaxChannels(lines: Array[Line.Info]): Int =
    (0 +: lines.map {
      case info: DataLine.Info => scanMaxChannels(info)
      case _ => 0
    }).max

  private def scanMaxChannels(info: DataLine.Info): Int = (0 +: info.getFormats.map(_.getChannels)).max
}
