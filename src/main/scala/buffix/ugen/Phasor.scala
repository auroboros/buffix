package buffix.ugen

import buffix.AudioConfig

/**
  * Created by johnmcgill on 3/5/18.
  */
object Phasor {
  def computeW(pitch: Int): Double = 2 * Math.PI * pitch / AudioConfig.samplingRate
}