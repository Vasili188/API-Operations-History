package ru.netology.$vulgin.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CashbackOperation extends Operation implements ConsolePrintable{
    private int cashbackAmount;

    public CashbackOperation(int id, int sum, String currency, String merchant, int cashbackAmount) {
        super(id, sum, currency, merchant);
        this.cashbackAmount = cashbackAmount;
    }

    @Override
    public void printToConsole() {
        System.out.println("id= " + super.getId() +
                " Sum: " + super.getSum() +
                " Currency: " + super.getCurrency() +
                " Merchant: " + super.getMerchant() +
                " CashbackAmount: " + cashbackAmount);
    }

    @Override
    public String toString(){
        return "id= " + super.getId() +
                " Sum: " + super.getSum() +
                " Currency: " + super.getCurrency() +
                " Merchant: " + super.getMerchant() +
                " CashbackAmount: " + cashbackAmount;
    }

}