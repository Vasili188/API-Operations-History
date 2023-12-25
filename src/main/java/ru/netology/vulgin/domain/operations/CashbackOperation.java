package ru.netology.vulgin.domain.operations;

import ru.netology.vulgin.domain.ConsolePrintable;



public final class CashbackOperation extends Operation implements ConsolePrintable {
    private int cashbackAmount;

    public void printToConsole(){
        super.printToConsole();
        System.out.println("Cashback Amount " + cashbackAmount);
    }

    public CashbackOperation(int cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
    }

    public CashbackOperation(OperationCreditType operationCreditType, double sum, Currency currency, String merchant, int customerId, int cashbackAmount) {
        super(operationCreditType, sum, currency, merchant, customerId);
        this.cashbackAmount = cashbackAmount;
    }

    public int getCashbackAmount() {
        return cashbackAmount;
    }

    @Override
    public String toString() {
        return "CashbackOperation{" +
                "cashbackAmount=" + cashbackAmount +
                '}';
    }

    public void setCashbackAmount(int cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
    }
}