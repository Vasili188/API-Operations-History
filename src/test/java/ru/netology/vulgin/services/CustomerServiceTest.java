package ru.netology.vulgin.services;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.vulgin.OperationHistoryApiApplicationTest;
import ru.netology.vulgin.domain.Customer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerServiceTest extends OperationHistoryApiApplicationTest {

    @Autowired
    CustomerService customerService;

    @Test
    @Order(1)
    public void getCustomersTest(){
        List<Customer> customers = customerService.getStorage();
        Customer customer1 = customers.get(0);
        Customer customer2 = customers.get(1);
        assertEquals(0, customer1.getId());
        assertEquals("Spring", customer1.getName());
        assertEquals(1, customer2.getId());
        assertEquals("Boot", customer2.getName());
        assertEquals(2,customers.size());
    }

    @Test
    @Order(2)
    public void addCustomerTest(){
        int newCustomerId = 2;
        customerService.addCustomer(newCustomerId, "Zadevalov");
        List<Customer> customers = customerService.getStorage();
        Customer customer = customers.get(newCustomerId);
        assertEquals("Zadevalov", customer.getName());
    }
}
