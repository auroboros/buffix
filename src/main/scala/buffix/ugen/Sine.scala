package buffix.ugen

import buffix.{AudioConfig, SignalBuffer}

/**
  * Created by johnmcgill on 3/4/18.
  */
case class Sine(initPitch: Int = 440, initPhi: Double = 0) {

  val pitch = initPitch
  val w = Phasor.computeW(pitch)
  var phi = initPhi

  val signalBuffer = SignalBuffer(AudioConfig.outBufferSize / 2)

  def nextBuffer() = {
    signalBuffer foreachSample (index => {
      signalBuffer.doubleBuffer(index) = Math.sin(phi)
      phi += w
    })
  }
}

object Phasor {
  def computeW(pitch: Int) = 2 * Math.PI * pitch / AudioConfig.samplingRate
}