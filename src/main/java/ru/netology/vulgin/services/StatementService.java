package ru.netology.vulgin.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.netology.vulgin.domain.Customer;
import ru.netology.vulgin.domain.operations.Currency;
import ru.netology.vulgin.domain.operations.Operation;
import ru.netology.vulgin.domain.operations.OperationCreditType;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Getter
@AllArgsConstructor
public class StatementService {

    private final Map<Integer, List<Operation>> storage = new HashMap<>();

    @PostConstruct
    public void initStorage() {
        List<Operation> initialList = new ArrayList<>();
        initialList.add(new Operation(OperationCreditType.DEBIT, 2500, Currency.RUB, "OZON", 1));
        storage.put(1,initialList);
    }

    public String getStringStorage(){
        return storage.toString();
    }

    public void saveOperation(Operation operation){
        List<Operation> operations = storage.get(operation.getCustomerId());
        if (operations == null){
            List<Operation> customerOperations = new ArrayList<>();
            customerOperations.add(operation);
            storage.put(operation.getCustomerId(), customerOperations);
        } else {
            operations.add(operation);
        }
    }

    public List<Operation> getOperationsFromCustomer(int customerId) {
        return storage.get(customerId);
    }
    public void removeOperationsOnCustomerId(int id){
        for(int i: storage.keySet()){
            if (i == id){
                storage.remove(i);
            }
        }
    }
}