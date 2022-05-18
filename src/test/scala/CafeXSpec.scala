import org.scalatest.FlatSpec
import part102.CafeX
import part102.menuObjectsAndTraits.{Caviar, CheeseSandwich, Coffee, Customer, Employee, Lobster, MenuItems, SteakSandwich}





class CafeXSpec extends FlatSpec {

  "calculateBill" should "not add a tip on to bill when there are only drinks" in {
      val staffName: Employee = Employee("test Employee", "tester")
      val loyaltyCustomerName: Option[Customer] = None
      val order: List[MenuItems] = List(Coffee, Coffee, Coffee)


    assert(CafeX.calculateBill(order, loyaltyCustomerName, staffName).equals(3.00))
    //assert(CafeX.calculateBill(order, loyaltyCustomerName, staffName) === 3.00)

    }


  "calculateBill" should "add a 10% tip on to bill with cold food on it" in {
    val staffName: Employee = Employee("test Employee", "tester")
    val loyaltyCustomerName: Option[Customer] = None
    val order: List[MenuItems] = List(Coffee, Coffee, Coffee, CheeseSandwich)


    assert(CafeX.calculateBill(order, loyaltyCustomerName, staffName).equals(5.50))

  }

  "calculateBill" should "add a 20% tip on to bill with hot food on it" in {
    val staffName: Employee = Employee("test Employee", "tester")
    val loyaltyCustomerName: Option[Customer] = None
    val order: List[MenuItems] = List(Coffee, SteakSandwich, CheeseSandwich)


    assert(CafeX.calculateBill(order, loyaltyCustomerName, staffName).equals(9.00))

  }

  "calculateBill" should "add a £20 when max 20% tip is reached on bill with hot food" in {
    val staffName: Employee = Employee("test Employee", "tester")
    val loyaltyCustomerName: Option[Customer] = None
    val order: List[MenuItems] = List(Coffee, Caviar, Caviar)


    assert(CafeX.calculateBill(order, loyaltyCustomerName, staffName).equals(221.00))

  }

  "calculateBill" should "add a 25% tip on bill with premium food" in {
    val staffName: Employee = Employee("test Employee", "tester")
    val loyaltyCustomerName: Option[Customer] = None
    val order: List[MenuItems] = List(Coffee, Caviar, Lobster)


    assert(CafeX.calculateBill(order, loyaltyCustomerName, staffName).equals(157.50))

  }

  "calculateBill" should "add a £40 when max 25% tip on bill with premium food is reached" in {
    val staffName: Employee = Employee("test Employee", "tester")
    val loyaltyCustomerName: Option[Customer] = None
    val order: List[MenuItems] = List(Coffee, Caviar, Lobster, Lobster, Lobster)


    assert(CafeX.calculateBill(order, loyaltyCustomerName, staffName).equals(216.00))

  }

  "calculateBill" should "add a 10% tip on bill after min loyalty star discount of 7.5% (from 3 stars) has been removed from total" in {
    val staffName: Employee = Employee("test Employee", "tester")
    val loyaltyCustomerName: Option[Customer] = Some(Customer(true, "Tess", 3))
    val order: List[MenuItems] = List(Coffee, Coffee, Coffee, CheeseSandwich, CheeseSandwich)


    assert(CafeX.calculateBill(order, loyaltyCustomerName, staffName).equals(7.12))

  }
  "calculateBill" should "add a 20% tip on bill after min loyalty star discount of 7.5% (from 3 stars) has been removed from total with hot food" in {
    val staffName: Employee = Employee("test Employee", "tester")
    val loyaltyCustomerName: Option[Customer] = Some(Customer(true, "Tess", 3))
    val order: List[MenuItems] = List(Coffee, CheeseSandwich, SteakSandwich)


    assert(CafeX.calculateBill(order, loyaltyCustomerName, staffName).equals(8.32))

  }

}

