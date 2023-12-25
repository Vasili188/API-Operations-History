package ru.netology.vulgin.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.vulgin.OperationHistoryApiApplicationTest;
import ru.netology.vulgin.domain.operations.Currency;
import ru.netology.vulgin.domain.operations.OperationCreditType;
import ru.netology.vulgin.domain.operations.Operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StatementServiceTest extends OperationHistoryApiApplicationTest {

    @Autowired
    StatementService statementService;

    @BeforeEach
    public void resetStorage() {
        statementService.initStorage();
    }

    @Test
    @Order(1)
    public void getOperations(){
        String operations = statementService.getStringStorage();
        assertEquals("{1=[Operations{operationCreditType=DEBIT, sum=2500.0, currency=RUB, merchant=OZON'}]}", operations);
    }

    @Test
    @Order(2)
    public void getCustomerOperations(){
        int customerId = 1;
        String operations = statementService.getOperationsFromCustomer(customerId).toString();
        assertEquals("[Operations{operationCreditType=DEBIT, sum=2500.0, currency=RUB, merchant=OZON'}]", operations);
    }

    @Test
    @Order(3)
    public void removeOperationTest(){
        statementService.removeOperationsOnCustomerId(1);
        assertEquals("{}", statementService.getStringStorage());
    }

    @Test
    @Order(4)
    public void saveOperationsTest(){
        Operation operation = new Operation(OperationCreditType.CREDIT, 60, Currency.USD, "Amazon", 0);
        statementService.saveOperation(operation);
        String operations = statementService.getStringStorage();
        assertEquals("{0=[Operations{operationCreditType=CREDIT, sum=60.0, currency=USD, merchant=Amazon'}], 1=[Operations{operationCreditType=DEBIT, sum=2500.0, currency=RUB, merchant=OZON'}]}", operations);
    }


}
