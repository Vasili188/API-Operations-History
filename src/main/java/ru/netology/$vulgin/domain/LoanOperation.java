package ru.netology.$vulgin.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoanOperation extends Operation implements ConsolePrintable {
    private int loanId;

    public LoanOperation(int id, int sum, String currency, String merchant, int loanId) {
        super(id, sum, currency, merchant);
        this.loanId = loanId;
    }
    @Override
    public String toString() {
        return "id= " + super.getId() +
                " Sum: " + super.getSum() +
                " Currency: " + super.getCurrency() +
                " Merchant: " + super.getMerchant() +
                " LoanId: " + loanId;
    }

    @Override
    public void printToConsole() {
        System.out.println("id= " + super.getId() +
                " Sum: " + super.getSum() +
                " Currency: " + super.getCurrency() +
                " Merchant: " + super.getMerchant() +
                " LoanId: " + loanId);
    }
}