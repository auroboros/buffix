package buffix.ugen

import buffix.AudioConfig

/**
  * Created by johnmcgill on 3/5/18.
  */
object Phasor {
  val Period = Math.PI * 2

  def computeW(pitch: Int, samplingRate: Int): Double = Period * pitch / samplingRate
}