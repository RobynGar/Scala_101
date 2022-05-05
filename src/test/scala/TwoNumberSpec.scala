import org.scalatest.FlatSpec
import part101.TwoNumbers


class TwoNumberSpec extends FlatSpec{

  "add" should "add numbers" in {
    val nums = new TwoNumbers(1, 2)
    assert(nums.add === 3)	}

  "add" should "add negative numbers" in {
    val nums = new TwoNumbers(-1, -2)
    assert(nums.add === -3)	}

  "add" should "add zero numbers" in {
    val nums = new TwoNumbers(1, 0)
    assert(nums.add === 1)	}

  "subtract" should "subtract numbers" in {
    val nums = new TwoNumbers(1, 2)
    assert(nums.subtract() === -1)	}

  "subtract" should "subtract negative numbers" in {
    val nums = new TwoNumbers(-1, -2)
    assert(nums.subtract() === 1)	}

  "subtract" should "subtract zero" in {
    val nums = new TwoNumbers(1, 0)
    assert(nums.subtract() === 1)	}

  "divide" should "divide numbers" in {
    val nums = new TwoNumbers(2, 1)
    assert(nums.divide() === 2)	}

  "divide" should "divide negative numbers" in {
    val nums = new TwoNumbers(-2, -1)
    assert(nums.divide() === 2)	}

  "divide" should "divide by zero" in {

    assertThrows[ArithmeticException](new TwoNumbers(2, 0).divide())	}

  "multiply" should "multiply numbers" in {
    val nums = new TwoNumbers(2, 2)
    assert(nums.multiply() === 4)	}

  "multiply" should "multiply negative numbers" in {
    val nums = new TwoNumbers(-2, -2)
    assert(nums.multiply() === 4)	}

  "multiply" should "multiply number by zero" in {
    val nums = new TwoNumbers(2, 0)
    assert(nums.multiply() === 0)	}
}
