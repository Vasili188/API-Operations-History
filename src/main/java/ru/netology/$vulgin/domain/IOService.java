package ru.netology.$vulgin.domain;

import java.util.*;
public class IOService {

    private static final Scanner scanner = new Scanner(System.in);

    public static void getFinalOutput(StorageService<Customer> customers, StorageService<Operation> operations, int[][] statement, int[] customer_operations_count){
        System.out.println();
        System.out.println(" Customers: " + Arrays.deepToString(customers.getStorage().toArray(new Customer[0])));
        System.out.println(" Operations: " + Arrays.toString(operations.getStorage().toArray(new Operation[0])));
        System.out.println(" Statement: " + Arrays.deepToString(statement));
        System.out.println(" Operations count: " + Arrays.toString(customer_operations_count));
    }
    public static List<Map.Entry<Integer, Operation>> readTransactionInput(StorageService<Operation> operations){
        var count = operations.size();
        List<Map.Entry<Integer, Operation>> list = new ArrayList<>();
        String currency;
        String merchant;
        String answer;
        int sum = 0;
        System.out.println("Start to input operations:");
        while(true)
        {
            System.out.println("Sum: ");
            try {
                sum = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Sum not a number.");
                scanner.nextLine();
            }
            scanner.nextLine();
            System.out.println("Currency: ");
            currency = scanner.nextLine();

            System.out.println("Merchant: ");
            merchant = scanner.nextLine();

            System.out.println("Do you want to enter next Operation? Y/N");
            answer = scanner.nextLine();
            if (Objects.equals(answer, "N"))
            {
                break;
            }
            int clientId = getOperationOwner();
            list.add(Map.entry(clientId, new Operation(count,sum,currency,merchant)));
            count++;
        }
        return list;
    }
    public static int CheckCustomerExistence(StorageService<Customer> customers, int customerId) {
        if (!customers.isIndexValid(customerId))
        {
            System.out.println("There is no client with ID: " + customerId + "!");
            System.out.println("If you want to create it, write 'create', if you want to change it, then write 'change'");
            String answer = scanner.nextLine();

            if (answer.equals("create")){
                System.out.println("Customer name: ");
                String name = scanner.nextLine();
                customers.setElement(customerId,new Customer(customerId,name));
                System.out.println("User created!");
            }
            else if (answer.equals("change")) {
                while (!customers.isIndexValid(customerId)) {
                    System.out.println("Enter the correct id: ");
                    customerId = scanner.nextInt();
                    scanner.nextLine();
                }
                System.out.println("You have entered an existing id! Complete the next transaction!");
            }
            else {
                System.out.println("This is not id, please, change it");
                while (!customers.isIndexValid(customerId)) {
                    System.out.println("Enter the correct id: ");
                    customerId = scanner.nextInt();
                    scanner.nextLine();
                }
                System.out.println("You have entered an existing id! Complete the next transaction!");
            }
        }
        return customerId;
    }
    public static void CheckCustomerAllOperations(Scanner scanner, int[] customer_operations_count, StorageService<Operation> operations, int[][] statement) {
        System.out.println("Do you want known all operation of certain customer? Y/N");
        String answer = scanner.nextLine();
        if (answer.equals("N")) {
            System.out.println("Ok, than get all information: ");
        } else if (answer.equals("Y")) {
            System.out.println("Please, write customer id: ");
            int customerId = scanner.nextInt();
            System.out.println("There are all his operations: ");
            System.out.println(Arrays.toString(OperationService.getOperations(customerId, customer_operations_count, operations, statement)));
        } else {
            System.out.println("Sorry, Error command.");
        }
    }
    public static int getOperationOwner(){
        System.out.println("ID of the customer who owns the operations: ");
        var b = scanner.nextInt();
        scanner.nextLine();
        return b;

    }
    public static List<String> customerSystemInput(){
        ArrayList<String> list = new ArrayList<>();
        while(true){
            System.out.println("Customer name: ");
            String name = scanner.nextLine();
            System.out.println("Do you want to enter next Customer? Y/N");
            String answer = scanner.nextLine();
            if (Objects.equals(answer, "N"))
                break;
            list.add(name);
        }
        return list;
    }
}
