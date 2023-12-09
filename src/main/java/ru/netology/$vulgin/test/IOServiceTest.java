package ru.netology.$vulgin.test;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ru.netology.$vulgin.domain.Customer;
import ru.netology.$vulgin.domain.IOService;
import ru.netology.$vulgin.domain.Operation;
import ru.netology.$vulgin.domain.StorageService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static ru.netology.$vulgin.domain.IOService.readTransactionInput;
import static ru.netology.$vulgin.domain.StorageService.MAX_CUSTOMERS;


public class IOServiceTest {


    @Test
    public void testCheckCustomerExistence() {

        StorageService<Customer> mockCustomers = new StorageService<>(MAX_CUSTOMERS);
        mockCustomers.setElement(0, new Customer(0,"John"));
        System.out.println(anyString());
        int result = IOService.CheckCustomerExistence(mockCustomers, 0);
        assertEquals(0, result);
    }
    @Test
    public void testReadTransactionInput() {

        String data = "100\nUSD\nAmazon\nY\n0\n200\nEUR\nEbay\nN";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));


        List<Map.Entry<Integer, Operation>> result = readTransactionInput(new StorageService<>(MAX_CUSTOMERS));


        assertEquals(1, result.size());
        assertEquals(100, result.get(0).getValue().getSum());
        assertEquals("USD", result.get(0).getValue().getCurrency());
        assertEquals("Amazon", result.get(0).getValue().getMerchant());


        System.setIn(stdin);
    }
    @Test
    public void testCheckCustomerExistence_NewCustomer() {
        StorageService<Customer> customers = new StorageService<>(MAX_CUSTOMERS);
        customers.setElementAtBegin(new Customer(0, "John"));
        customers.setElementAtBegin(new Customer(1, "Alice"));
        String data = "create\nUnit";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        int customerId = 2;
        IOService.CheckCustomerExistence(customers, customerId);
        Assertions.assertEquals(3, customers.size());
        Assertions.assertNotNull(customers.getElement(customerId));
        System.setIn(stdin);
    }

    @Test
    public void testCheckCustomerExistence_ExistingCustomer() {
        StorageService<Customer> customers = new StorageService<>(MAX_CUSTOMERS);
        customers.setElement( new Customer(0, "John"));
        customers.setElement( new Customer(1, "Alice"));
        int customerId = 1;
        int result = IOService.CheckCustomerExistence(customers, customerId);
        Assertions.assertEquals(1, result);
        Assertions.assertNotNull(customers.getElement(customerId));
    }

    @Test
    public void testCheckCustomerExistence_ChangeCustomer() {
        StorageService<Customer> customers = new StorageService<>(MAX_CUSTOMERS);
        customers.setElement( new Customer(0, "John"));
        customers.setElement( new Customer(1, "Alice"));
        String data = "change\n1\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        int customerId = 3;
        int result = IOService.CheckCustomerExistence(customers, customerId);
        Assertions.assertEquals(1, result);
        Assertions.assertEquals(customers.getElement(1),customers.getElement(result));
        System.setIn(stdin);
    }
}