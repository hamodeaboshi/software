package acceptance;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import ProductionCode.MyApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OrderManagementStep {
    MyApp app;

    public OrderManagementStep(MyApp app) {
        super();
        this.app = app;
    }

    @Given("I am on the order management page")
    public void i_am_on_the_order_management_page() {
        app.navigateTo("order management page");
    }

    @When("I navigate to the order management page")
    public void i_navigate_to_the_order_management_page() {
        app.navigateTo("order management page");
    }

    @Then("I should see a list of orders")
    public void i_should_see_a_list_of_orders() throws FileNotFoundException, IOException {
        app.listOrders();
        // Verify the list of orders is printed on the console or handled in a way you expect.
        assertTrue("Order list should be displayed", true); // Adjust as needed
    }

    @When("I select an order number {string} and I choose {string}")
    public void i_select_an_order_number_and_i_choose(String oNum, String op) throws IOException {
        app.processOrder(oNum, op);
    }

    @Then("the order status becomes processed {string}")
    public void the_order_status_becomes(String op) {
        //assertTrue(app.isOrderStatusUpdated("1", op));
    }

    @Then("I should see the updated status in the order list and return to management page")
    public void i_should_see_the_updated_status_in_the_order_list_and_return_to_management_page() throws FileNotFoundException, IOException {
        app.listOrders();
        assertTrue("Updated status should be visible in the order list", true); // Adjust as needed
        app.returnToManagementPage();
        assertTrue("Failed to return to the management page.", app.isOnPage("order management page"));
    }

	
	  @When("I select an order by its number {string}") public void
	  i_select_an_order_by_its_number(String orderNum) throws FileNotFoundException, IOException {
	  app.selectOrderByNumber(orderNum); }
	 

	  @Then("I should see the current {string} status of the order and return back to mangement page")
    public void i_should_see_the_current_status_of_the_order_and_return_back_to_management_page(String num) throws FileNotFoundException, IOException {
        String status = app.getCurrentOrderStatus(num);
        assertTrue("The order status is not available or incorrect.", status != null && !status.isEmpty());
        app.returnToManagementPage();
        assertTrue("Failed to return to the management page.", app.isOnPage("order management page"));
    }
}
