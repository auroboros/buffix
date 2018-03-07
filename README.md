# buffix
_buffer fixation:_ Prior auroboros libraries were focused on finding some inherent language structure for signal (mainly audio) processing using higher-kinded types and immutable data. As such they were very wasteful in terms of memory (and, in turn, CPU) and not nearly as optimized as audio libraries that focus on re-populating pre-allocated buffers.

This library is that alternative, much more conservative approach. It begins with a more traditional model of audio processing units containing a data buffer each and focusing on adding light syntactic sugar to make development simpler, but may evolve to extreme space optimization (only using dedicated buffers at "split" points in the signal processing path where the current buffer can't be overwritten).

An additional goal of this library will be to break out math / IO / other "library structure agnostic" utilities so that they can be easily understood and re-used. In other JVM audio libraries these components have seemed to be somewhat incomprehensible or inextricably tied to the library code structure in a way that makes them difficult to re-use. I'm not sure there is a real reason for this other than too much adherence to old-school OO paradigm instead of lightweight convenience methods. For example, why should selecting a line out be obfuscated in the internals of some audio system wrapper? Even if there is a wrapper object with a reference to a pre-selected line out, the iterator logic can easily live outside of this object so it can be used to aggregate other object configurations. Or, at the very least, having the logic isolated will at least make it readable (an example) for those trying to learn.