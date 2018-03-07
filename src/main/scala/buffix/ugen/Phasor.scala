package buffix.ugen

import buffix.AudioConfig

/**
  * Created by johnmcgill on 3/5/18.
  */
object Phasor {
  val TwoPi = Math.PI * 2
  val Period = TwoPi

  def computeW(pitch: Int, samplingRate: Int): Double = Period * pitch / samplingRate
}