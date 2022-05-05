import org.scalatest.FlatSpec
import part101.FizzPractical

class FizzPracticalSpec extends FlatSpec{
  "fizzbuzz" should "modulus by 3 should fizz" in {
    val modulus = new FizzPractical
    assert(modulus.fizzBuzz(9) === "9 Fizz")	}

  "fizzbuzz" should "modulus by 5 should buzz" in {
    val modulus = new FizzPractical
    assert(modulus.fizzBuzz(20) === "20 Buzz")	}

  "fizzbuzz" should "modulus by 3 and 5 should fizzbuzz" in {
    val modulus = new FizzPractical
    assert(modulus.fizzBuzz(15) === "15 FizzBuzz")	}

  "fizzbuzz" should "not modulus of 3 or 5 should not fizzbuzz" in {
    val modulus = new FizzPractical
    assert(modulus.fizzBuzz(101) === "no fizz buzz for you")	}

  "fizzbuzz" should " 0 not modulus of 3 or 5 should not fizzbuzz" in {
    val modulus = new FizzPractical
    assert(modulus.fizzBuzz(0) === "no fizz buzz for you")	}
}
