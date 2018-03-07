package buffix.ugen

/**
  * These functions accept the phase (of a phasor presumably) and translate that to an output value
  *
  * Created by johnmcgill on 3/5/18.
  */
object PhaseTransforms {

  def sine(phi: Double): Double = Math.sin(phi)

  def square(phi: Double): Double = if (phi < Math.PI) -1 else 1

  def sawtooth(phi: Double): Double = (phi / Math.PI) - 1
}
