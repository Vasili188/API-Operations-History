package ru.netology.$vulgin.test;

import org.junit.Test;
import ru.netology.$vulgin.domain.exception.CustomerOperationOutOfBoundException;
import ru.netology.$vulgin.domain.StatementService;
import static org.junit.Assert.*;
public class StatementServiceTest {
    @Test
    public void testUpdateStatement() {
        // Подготавливаем данные для теста
        int operationId = 0;
        int MAX_OPERATION = 10;
        int[] customerOperationsCount = new int[1];
        int MAX_CUSTOMERS = 3;
        int[][] statement = new int[MAX_CUSTOMERS][MAX_OPERATION];
        int customerId = 0;
        StatementService.updateStatement(operationId, customerOperationsCount, statement, customerId);
        assertEquals(1, customerOperationsCount[customerId]);
        assertEquals(operationId, statement[customerId][0]);
        for (int i = 1; i < MAX_OPERATION / MAX_CUSTOMERS; i++) {
            StatementService.updateStatement(++operationId, customerOperationsCount, statement, customerId);
        }
        try {
            StatementService.updateStatement(++operationId, customerOperationsCount, statement, customerId);
            fail("Expected CustomerOperationOutOfBoundException was not thrown");
        } catch (RuntimeException e) {
            assertTrue(e.getCause() instanceof CustomerOperationOutOfBoundException);
        }
    }
}