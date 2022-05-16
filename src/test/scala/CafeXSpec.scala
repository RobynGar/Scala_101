import org.scalatest.FlatSpec
import part102.CafeX.{Customer, Employee, MenuItem, calculateBill}



class CafeXSpec extends FlatSpec {

  "calculateBill" should "not add a tip on to bill when there are only drinks" in {
      val staffName: Employee = ???
      val loyaltyCustomerName: Option[Customer] = ???
      val order: List[MenuItem] = ???

    assert(calculateBill(staffName, loyaltyCustomerName, order).equals(""))
    }

  "calculateBill" should "blah" in {

  }

}

