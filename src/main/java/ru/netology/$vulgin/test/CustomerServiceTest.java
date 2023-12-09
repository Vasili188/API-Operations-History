package ru.netology.$vulgin.test;

import org.junit.Test;
import ru.netology.$vulgin.domain.Customer;
import ru.netology.$vulgin.domain.CustomerService;
import ru.netology.$vulgin.domain.StorageService;


import java.util.List;

import static org.junit.Assert.*;

public class CustomerServiceTest {

    @Test
    public void testInputCustomer() {
        int MAX_CUSTOMERS = 100;
        StorageService<Customer> mockCustomers = new StorageService<>(MAX_CUSTOMERS);

        CustomerService.inputCustomers(List.of("John", "Jane"), mockCustomers);

        assertEquals(2, mockCustomers.getStorage().size());
        assertEquals("John", mockCustomers.getElement(0).getName());
        assertEquals("Jane", mockCustomers.getElement(1).getName());
    }
}