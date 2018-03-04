import javax.sound.sampled.DataLine.Info
import javax.sound.sampled.{Line, DataLine, Mixer, AudioSystem}

/**
  * Created by johnmcgill on 3/3/18.
  */
object AudioApp extends App {
  val mixerInfos = AudioSystem.getMixerInfo

  mixerInfos.foreach(println)

  val mixers = mixerInfos.map(AudioSystem.getMixer)

  val defaultInputDevice = mixers.find(mixer => channelsAreAvailable(mixer.getTargetLineInfo))
  val defaultOutputDevice = mixers.find(mixer => channelsAreAvailable(mixer.getSourceLineInfo))
}

private def channelsAreAvailable(lines: Array[Line.Info]): Boolean = scanMaxChannels(lines) > 0

private def scanMaxChannels(lines: Array[Line.Info]): Int =
  (0 +: lines.map {
    case info: DataLine.Info => scanMaxChannels(info)
    case _ => 0
  }).max

private def scanMaxChannels(info: DataLine.Info): Int = (0 +: info.getFormats.map(_.getChannels)).max