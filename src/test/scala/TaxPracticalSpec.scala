import org.scalatest.FlatSpec

class TaxPracticalSpec extends FlatSpec{

  "calculateTax" should "calculate 40% tax on amounts greater than or equal to 100,000" in {
  val tax = new TaxPractical
  assert(tax.calculateTax(100000) === 40000)	}

  "calculateTax" should "calculate 40% tax on amounts greater than or equal to 100,000 where result is a decimal" in {
    val tax = new TaxPractical
    assert(tax.calculateTax(1000002) === 400001)	}

  "calculateTax" should "calculate 20% tax on amounts greater than or equal to 50,000 but less than 100,000" in {
    val tax = new TaxPractical
    assert(tax.calculateTax(60000) === 12000)	}

  "calculateTax" should "calculate 15% tax on amounts greater than or equal to 10,000 but less than 50,000" in {
    val tax = new TaxPractical
    assert(tax.calculateTax(15000) === 2250)	}

  "calculateTax" should "calculate 10% tax on amounts less than 10,000" in {
    val tax = new TaxPractical
    assert(tax.calculateTax(500) === 50)	}
}
