package ru.netology.$vulgin.test;

import org.junit.Test;
import ru.netology.$vulgin.domain.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class OperationServiceTest {
    int MAX_OPERATION = 300;
    int MAX_CUSTOMERS = 100;

    @Test
    public void testGetOperations() {
        StorageService<Operation> mockOperations = new StorageService<>(MAX_OPERATION);

        mockOperations.setElement(new Operation(0,100,"USD", "AMAZON"));
        mockOperations.setElement(new Operation(1,150,"RUB", "Diswork"));
        int customerId = 0;
        int[] customerOperationsCount = new int[] {2};
        int[][] statement = new int[2][2];
        statement[0][0] = 0;
        statement[0][1] = 1;


        Operation[] result = OperationService.getOperations(customerId, customerOperationsCount, mockOperations, statement);

        assertEquals(2, result.length);
        assertEquals(100, result[0].getSum());
        assertEquals(150, result[1].getSum());
    }

    @Test
    public void testInputOperation() {

        StorageService<Operation> operations = new StorageService<>(MAX_OPERATION);
        StorageService<Customer> customers = new StorageService<>(MAX_CUSTOMERS);
        customers.setElement(0, new Customer(0, "John"));
        int[] customer_operations_count = new int[1];
        int[][] statement = new int[2][2];

        List<Map.Entry<Integer, Operation>> list = new ArrayList<>();
        list.add(Map.entry(0, new Operation(100,"USD", "Amazon")));
        list.add(Map.entry(0,new Operation(100,"RUB", "WildBerries")));

        OperationService.inputOperations(list, operations, customers, customer_operations_count, statement);

        assertEquals(new Operation(100,"USD","Amazon"), operations.getElement(0));
        assertEquals(new Operation(100,"RUB","WildBerries"), operations.getElement(1));
        assertEquals(customer_operations_count[0], 2);
        assertEquals(statement[0][0], 0);
        assertEquals(statement[0][1], 1);

    }
}