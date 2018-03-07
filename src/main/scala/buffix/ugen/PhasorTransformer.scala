package buffix.ugen

import buffix.{AudioConfig, SignalBuffer}

/**
  * Created by johnmcgill on 3/4/18.
  */
trait PhasorTransformer {

  val initPitch: Int
  val initPhi: Double
  def phiTransform: (Double) => Double

  // should SR be passed in? Or at least like this its overridable...
  val samplingRate = AudioConfig.samplingRate

  val pitch = initPitch
  val w = Phasor.computeW(pitch, samplingRate)
  var phi = initPhi

  val signalBuffer = SignalBuffer(AudioConfig.outBufferSize / 2)

  def nextBuffer() = {
    signalBuffer foreachSample (index => {
      signalBuffer.doubleBuffer(index) = phiTransform(phi)
      phi = (phi + w) % Phasor.Period
    })
  }
}