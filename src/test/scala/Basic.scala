
import org.specs2.mutable._

  class MeasureSpecs extends Specification {
  	import measure._
  	"Measure.getTime" should {
  		"return tuple with result and time" in {
  			val tuple = Measure.getTime({ 10 * 2 })
  			(tuple._1 must_==20 )
  		}
  	}
  }

  class TimesSpecs extends Specification {
  	import measure._
  	import measure.Times._

  	"IntTimes" should {
  		"execute block multiple times" in {
  			val list = new collection.mutable.ListBuffer[Int]()
  			Times.IntTimes(123).times({ list += 1})
  			list.size must_== 123
  		}

  		"allow 3.times syntax" in {
  			val list = new collection.mutable.ListBuffer[Int]()
  			3 times { list += 1 }
  			list.size must_== 3
  		}
  	}
  }