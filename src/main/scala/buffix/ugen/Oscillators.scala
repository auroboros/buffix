package buffix.ugen

/**
  * Created by johnmcgill on 3/6/18.
  */
case class Sine(initPitch: Int = 444, initPhi: Double = 0) extends PhasorTransformer {
  def phiTransform = PhaseTransforms.sine
}

case class Square(initPitch: Int = 444, initPhi: Double = 0) extends PhasorTransformer {
  def phiTransform = PhaseTransforms.square
}

case class Sawtooth(initPitch: Int = 444, initPhi: Double = 0) extends PhasorTransformer {
  def phiTransform = PhaseTransforms.sawtooth
}