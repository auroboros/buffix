package buffix.ugen

/**
  * Created by johnmcgill on 3/6/18.
  */
case class Sine(initPitch: Int = DefaultFreq, initPhi: Double = DefaultPhase) extends PhasorTransformer {
  def phiTransform = PhaseTransforms.sine
}

case class Square(initPitch: Int = DefaultFreq, initPhi: Double = DefaultPhase) extends PhasorTransformer {
  def phiTransform = PhaseTransforms.square
}

case class Sawtooth(initPitch: Int = DefaultFreq, initPhi: Double = DefaultPhase) extends PhasorTransformer {
  def phiTransform = PhaseTransforms.sawtooth
}