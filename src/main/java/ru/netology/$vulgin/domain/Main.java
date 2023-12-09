package ru.netology.$vulgin.domain;

import java.util.Scanner;

import static ru.netology.$vulgin.domain.StorageService.MAX_CUSTOMERS;
import static ru.netology.$vulgin.domain.StorageService.MAX_OPERATION;

public class Main {

    static StorageService<Customer> customerStorageService = new StorageService<>(MAX_CUSTOMERS);
    static StorageService<Operation> operationStorageService = new StorageService<>(MAX_OPERATION);
    private static final int[] customer_operations_count = new int[MAX_CUSTOMERS];


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerService.inputCustomers(IOService.customerSystemInput(), customerStorageService);
        OperationService.inputOperations(IOService.readTransactionInput(operationStorageService), operationStorageService, customerStorageService, customer_operations_count, StorageService.getStatement());
        IOService.CheckCustomerAllOperations(scanner, customer_operations_count, operationStorageService, StorageService.getStatement());
        IOService.getFinalOutput(customerStorageService, operationStorageService, StorageService.getStatement(), customer_operations_count);
    }
}