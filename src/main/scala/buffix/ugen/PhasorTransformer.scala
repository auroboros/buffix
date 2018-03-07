package buffix.ugen

import buffix.{AudioConfig, SignalBuffer}

/**
  * Created by johnmcgill on 3/4/18.
  */
trait PhasorTransformer {

  val initPitch: Int
  val initPhi: Double
  def phiTransform: (Double) => Double

  val pitch = initPitch
  val w = Phasor.computeW(pitch)
  var phi = initPhi

  val signalBuffer = SignalBuffer(AudioConfig.outBufferSize / 2)

  def nextBuffer() = {
    signalBuffer foreachSample (index => {
      signalBuffer.doubleBuffer(index) = phiTransform(phi)
      phi += w
      phi = phi % (2 * Math.PI)
    })
  }
}