package measure

object Measure {

	/**
	 * Prints how long given block of code was executed and return it result
	 */
	def printTime[A](a: => A) = {
    	val start = System.nanoTime
    	val result = a
    	val micros = (System.nanoTime - start) / 1000
    	println(micros + " microseconds")	
    	result
	}

	/**
	 * Returns a tuple with block execution result and execution time in microseconds
	 */
	def getTime[A](a: => A): (A, Long) = {
		val start = System.nanoTime
		val result = a
		val micros = (System.nanoTime - start) / 1000
		(result, micros)
	}
}

object Times {
	case class IntTimes(count: Int) {
		def times[A](a : => A) {
			for(i <- 1 to count) {
				val r = a
			}
		}
	}

	implicit def intWithTimes(i: Int) = IntTimes(i)
}