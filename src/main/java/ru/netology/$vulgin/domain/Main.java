package ru.netology.$vulgin.domain;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private final static int MAX_OPERATION = 300;
    private final static int MAX_CUSTOMERS = 100;

    private static final Operation[] operations = new Operation[MAX_OPERATION];
    private static  final Customer[] customers = new Customer[MAX_OPERATION];
    private static final int[][] statement = new int[MAX_CUSTOMERS][MAX_OPERATION/MAX_CUSTOMERS];
    private static final int[] customer_operations_count = new int[MAX_CUSTOMERS];


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        inputCustomer(scanner);
        inputOperation(scanner);

        System.out.println("Do you want known all operation of certain customer? Y/N");
        String answer = scanner.nextLine();
        if (answer.equals("N"))
        {
            System.out.println("Ok, than get all information: ");
        }
        else if (answer.equals("Y")) {
            System.out.println("Please, write customer id: ");
            int customerId = scanner.nextInt();
            System.out.println("There are all his operations: ");
            System.out.println(Arrays.toString(getOperations(customerId)));
        }
        else {
            System.out.println("Sorry, Error command.");
        }

        System.out.println();
        System.out.println(" Customers: " + Arrays.deepToString(customers));
        System.out.println(" Operations: " + Arrays.toString(operations));
        System.out.println(" Statement: " + Arrays.deepToString(statement));
        System.out.println(" Operations count: " + Arrays.toString(customer_operations_count));
    }
    private static void inputCustomer(Scanner scanner)
    {
        int customersCount = 0;
        while (true)
        {
            System.out.println("Customer name: ");
            String name = scanner.nextLine();

            System.out.println("Do you want to enter next Customer? Y/N");
            String answer = scanner.nextLine();

            if (answer.equals("N"))
            {
                break;
            }
            else if (customersCount == MAX_CUSTOMERS)
            {
                break;
            }

            if (customers[customersCount] != null)
            {
                while (customers[customersCount] != null)
                    customersCount++;
                Customer customer = new Customer(customersCount ,name);
                customers[customersCount] = customer;
            }
            Customer customer = new Customer(customersCount ,name);
            customers[customersCount] = customer;
            customersCount++;
        }
    }

    private static void inputOperation(Scanner scanner)
    {
        int operationId = 0;
        while (true)
        {
            int sum = 0;
            System.out.println("Sum: ");
            try{
                sum =  scanner.nextInt();
            }
            catch (Exception e){
                System.out.println("Sum not a number.");
                scanner.nextLine();
                break;
            }
            scanner.nextLine();

            System.out.println("Currency: ");
            String currency = scanner.nextLine();

            System.out.println("Merchant: ");
            String merchant = scanner.nextLine();

            System.out.println("Do you want to enter next Operation? Y/N");
            String answer = scanner.nextLine();

            if (answer.equals("N"))
            {
                break;
            }

            ImplementCustomerToOperation(scanner, operationId, sum, currency, merchant);
            operationId++;
        }
    }

    private static void ImplementCustomerToOperation(Scanner scanner, int operationId, int sum, String currency, String merchant) {
        if (operationId == MAX_OPERATION)
        {
            System.out.println("Transaction limit reached!");
            throw new ArrayIndexOutOfBoundsException();
        }

        Operation operation = new Operation(operationId, sum, currency, merchant);
        operations[operationId] = operation;

        System.out.println("ID of the customer who owns the operation: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        if (customers[customerId] == null)
        {
            System.out.println("There is no client with this id!");
            System.out.println("If you want to create it, write create, if you want to change it, then write change");
            String answer = scanner.nextLine();

            if (answer.equals("create")){
                System.out.println("Customer name: ");
                String name = scanner.nextLine();
                customers[customerId] = new Customer(customerId ,name);
                System.out.println("User created!");
            }
            else if (answer.equals("change")) {
                while (customers[customerId] == null) {
                    System.out.println("Enter the correct id: ");
                    customerId = scanner.nextInt();
                    scanner.nextLine();
                }
                System.out.println("You have entered an existing id! Complete the next transaction!");
            }
        }
        int operationCountForClient = customer_operations_count[customerId];;
        if (operationCountForClient == MAX_OPERATION/MAX_CUSTOMERS)
            try {
                throw new CustomerOperationOutOfBoundException(customerId,operationId);
            } catch (CustomerOperationOutOfBoundException e) {
                throw new RuntimeException(e);
            }
        statement[customerId][operationCountForClient] = operationId;

        customer_operations_count[customerId]++;
    }

    private static Operation[] getOperations(int customerId)
    {
        Operation[] customerOperations = new Operation[customer_operations_count[customerId]];
        for (int j = 0; j < customer_operations_count[customerId]; j++)
            customerOperations[j] = operations[statement[customerId][j]];
        return customerOperations;
    }
}