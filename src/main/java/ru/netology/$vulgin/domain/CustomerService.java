package ru.netology.$vulgin.domain;

import java.util.List;

import static ru.netology.$vulgin.domain.StorageService.MAX_CUSTOMERS;

public class CustomerService {
    public static void inputCustomers(List<String> list, StorageService<Customer> customers) {
        int customersCount = 0;
        while (true) {
            String customerName;
            if (list.size() <= customersCount)
                break;
            else
                customerName = list.get(customersCount);

            if (customersCount == MAX_CUSTOMERS) {
                System.out.println("Достигнут лимит доступных пользователей");
                break;
            }

            if (customers.isIndexValid(customersCount)) {
                while (customers.isIndexValid(customersCount))
                    customersCount++;
                Customer customer = new Customer(customersCount, customerName);
                customers.setElement(customersCount, customer);
            }
            Customer customer = new Customer(customersCount, customerName);
            customers.setElement(customersCount, customer);
            customersCount++;
        }
    }
}