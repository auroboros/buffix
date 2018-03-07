package buffix

/**
  * Created by johnmcgill on 3/6/18.
  */
package object ugen {
  val Sine: (Int, Double) => PhasorTransformer = PhasorTransformer(PhaseTransforms.sine)
  val Square: (Int, Double) => PhasorTransformer = PhasorTransformer(PhaseTransforms.square)
  val Sawtooth: (Int, Double) => PhasorTransformer = PhasorTransformer(PhaseTransforms.sawtooth)
}