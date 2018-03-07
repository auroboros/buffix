package buffix.ugen

import buffix.{AudioConfig, SignalBuffer}

/**
  * Created by johnmcgill on 3/4/18.
  */
class PhasorTransformer(val initPitch: Int, val initPhi: Double, val phiTransform: (Double) => Double) {

  val pitch = initPitch
  val w = Phasor.computeW(pitch)
  var phi = initPhi

  val signalBuffer = SignalBuffer(AudioConfig.outBufferSize / 2)

  def nextBuffer() = {
    signalBuffer foreachSample (index => {
      signalBuffer.doubleBuffer(index) = phiTransform(phi)
      phi += w
    })
  }
}

object PhasorTransformer {
  def apply(phiTransform: (Double) => Double)(initPitch: Int = 440, initPhi: Double = 0) = new PhasorTransformer(initPitch, initPhi, phiTransform)
}