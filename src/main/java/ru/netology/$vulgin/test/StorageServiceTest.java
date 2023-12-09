package ru.netology.$vulgin.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.$vulgin.domain.Operation;
import ru.netology.$vulgin.domain.StorageService;

public class StorageServiceTest {
    @Test
    public void getCustomerTest() {
        int MAX_OPERATION = 300;

        StorageService<Operation> operationStorageService = new StorageService<>(MAX_OPERATION);
        int sum = 100;
        String currency = "RUB";
        String merchant = "Shoko";
        Operation operation = new Operation(sum, currency, merchant);

        operationStorageService.setElement(operation);

        Operation storageOperation = operationStorageService.getElement(0);
        Assertions.assertEquals(sum, storageOperation.getSum());
        Assertions.assertEquals(merchant, storageOperation.getMerchant());
        Assertions.assertEquals(merchant, storageOperation.getMerchant());
    }
}
