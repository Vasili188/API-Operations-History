package ru.netology.$vulgin.domain;

import java.util.List;
import java.util.Map;

import static ru.netology.$vulgin.domain.StorageService.MAX_OPERATION;

public class OperationService {

    public static Operation[] getOperations(int customerId, int[] customer_operations_count, StorageService<Operation> operations, int[][] statement)
    {
        Operation[] customerOperations = new Operation[customer_operations_count[customerId]];
        for (int j = 0; j < customer_operations_count[customerId]; j++)
            customerOperations[j] = operations.getElement(statement[customerId][j]);
        return customerOperations;
    }

    public static void inputOperations(List<Map.Entry<Integer, Operation>> list, StorageService<Operation> operations, StorageService<Customer> customers, int[] customer_operations_count, int[][] statement) {
        int operationId = 0;
        while (operationId != list.size()) {
            var operationEntry = list.get(operationId);
            ImplementCustomerToOperation(operationEntry.getKey(), operationId, operationEntry.getValue(), operations, customers, customer_operations_count, statement);
            operationId++;
        }
    }

    private static void ImplementCustomerToOperation(int customerId, int operationId, Operation operation,
                                                     StorageService<Operation> operations,
                                                     StorageService<Customer> customers, int[] customer_operations_count, int[][] statement) {

        if (operationId == MAX_OPERATION)
        {
            System.out.println("Transaction limit reached!");
            throw new ArrayIndexOutOfBoundsException();
        }
        operations.setElement(operationId, operation);
        customerId = IOService.CheckCustomerExistence(customers, customerId);
        StatementService.updateStatement(operationId, customer_operations_count, statement, customerId);
    }
}