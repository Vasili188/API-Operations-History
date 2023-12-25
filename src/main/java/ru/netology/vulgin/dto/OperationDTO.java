package ru.netology.vulgin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.netology.vulgin.domain.operations.Currency;
import ru.netology.vulgin.domain.operations.OperationCreditType;

@Data
@AllArgsConstructor
public class OperationDTO {
    private final int customerId;
    private final double sum;
    private final Currency currency;
    private final String merchant;
    private final OperationCreditType operationCreditType;
}
