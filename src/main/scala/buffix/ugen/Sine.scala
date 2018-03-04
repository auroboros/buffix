package buffix.ugen

import java.nio.{ByteOrder, ByteBuffer}

import buffix.{RichBuffer, AudioConfig}

/**
  * Created by johnmcgill on 3/4/18.
  */
case class Sine(initPitch: Int = 440, initPhi: Double = 0) {

  val pitch = initPitch
  val w = Phasor.computeW(pitch)
  var phi = initPhi

  val buffer = RichBuffer()

  def nextBuffer() = {
    buffer foreachSample (index => {
      buffer.putSample(index, Math.sin(phi))
      phi += w
    })
  }
}

object Phasor {
  def computeW(pitch: Int) = 2 * Math.PI * pitch / AudioConfig.samplingRate
}