import org.scalatest.FlatSpec
import part102.CafeX
import part102.menuObjectsAndTraits.{Coffee, Customer, Employee, MenuItems}





class CafeXSpec extends FlatSpec {

  "calculateBill" should "not add a tip on to bill when there are only drinks" in {
      val staffName: Employee = new Employee("test Employee", "tester")
      val loyaltyCustomerName: Option[Customer] = None
      val order: List[MenuItems] = List(Coffee, Coffee, Coffee)


    assert(CafeX.calculateBill(order, loyaltyCustomerName, staffName).equals(3.00))
    //assert(CafeX.calculateBill(order, loyaltyCustomerName, staffName) === 3.00)

    }

//  "calculateBill" should "blah" in {
//
//  }

}

