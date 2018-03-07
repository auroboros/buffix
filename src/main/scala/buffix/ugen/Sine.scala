package buffix.ugen

/**
  * Created by johnmcgill on 3/6/18.
  */
case class Sine(val initPitch: Int = 440, val initPhi: Double = 0) extends PhasorTransformer{
  override val phiTransform: (Double) => Double = PhaseTransforms.sine
}
