package ru.netology.vulgin.services;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.vulgin.OperationHistoryApiApplicationTest;
import ru.netology.vulgin.domain.operations.Currency;
import ru.netology.vulgin.domain.operations.OperationCreditType;
import ru.netology.vulgin.domain.operations.Operation;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AsyncInputOperationServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private AsyncInputOperationService asyncInputOperationService;

    private final Operation operation = new Operation(OperationCreditType.DEBIT, 6000, Currency.RUB, "JetBrains", 1);

    @Test
    @Order(1)
    public void testStartProcessing_ShouldStartThreadForProcessingQueue() {
        asyncInputOperationService.startProcessing();
        assertTrue(asyncInputOperationService.getOperations().isEmpty());
    }

    @Test
    @Order(2)
    public void getOperationsTest(){
        asyncInputOperationService.addOperation(operation);
        Queue<Operation> operations = asyncInputOperationService.getOperations();
        assertEquals("[Operations{operationCreditType=DEBIT, sum=6000.0, currency=RUB, merchant=JetBrains'}]", operations.toString());
    }

    @Test
    @Order(3)
    public void addOperationTest(){
        int beforeAdd = asyncInputOperationService.getOperations().size();
        asyncInputOperationService.addOperation(operation);
        int AfterAdd = asyncInputOperationService.getOperations().size();
        assertEquals(AfterAdd-beforeAdd, 1);
    }
}
