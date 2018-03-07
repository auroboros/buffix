package buffix

import buffix.ugen.Sawtooth
import buffix.SimpleAudioSystem.write
/**
  * Created by johnmcgill on 3/3/18.
  */
object AudioApp extends App {
  val sineGen = Sawtooth()

  1 to Int.MaxValue foreach(_ => {
    sineGen.nextBuffer()
    write(sineGen.signalBuffer)
  })
}