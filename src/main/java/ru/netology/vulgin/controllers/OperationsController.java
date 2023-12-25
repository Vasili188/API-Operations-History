package ru.netology.vulgin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.netology.vulgin.domain.operations.Operation;
import ru.netology.vulgin.dto.OperationDTO;
import ru.netology.vulgin.dto.OperationsGetResponse;
import ru.netology.vulgin.services.AsyncInputOperationService;
import ru.netology.vulgin.services.StatementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/operations/")
public class OperationsController {
    private final StatementService statementService;

    private final AsyncInputOperationService asyncInputOperationService;

    @Autowired
    public OperationsController(StatementService statementService, AsyncInputOperationService asyncInputOperationService) {
        this.statementService = statementService;
        this.asyncInputOperationService = asyncInputOperationService;
    }

    @GetMapping("{customerId}")
    public OperationsGetResponse checkOperationsByCustomerId(@PathVariable("customerId") int customerId){
        List<Operation> operations = statementService.getOperationsFromCustomer(customerId);
        List<OperationDTO> operationDTOS = operations.stream()
                .map(operation ->
                        new OperationDTO(operation.getId(),operation.getSum(), operation.getCurrency(), operation.getMerchant(), operation.getOperationCreditType())).collect(Collectors.toList());
        return new OperationsGetResponse(operationDTOS);
    }

    @PostMapping()
    public OperationDTO addOperation(@RequestBody Operation operation){
        statementService.saveOperation(operation);
        asyncInputOperationService.addOperation(operation);
        return new OperationDTO(operation.getId(),operation.getSum(), operation.getCurrency(), operation.getMerchant(), operation.getOperationCreditType());
    }

    @DeleteMapping("/delete/{operationId}")
    public void deleteOperation(@PathVariable("operationId") int operationId){
        statementService.removeOperationsOnCustomerId(operationId);
    }
}
