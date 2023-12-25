package ru.netology.vulgin.domain.operations;

import ru.netology.vulgin.domain.ConsolePrintable;



public class LoanOperation extends Operation implements ConsolePrintable {
    private int loanId;

    @Override
    public void printToConsole(){
        super.printToConsole();
        System.out.println("Loan Id " + loanId);
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public LoanOperation(int loanId) {
        this.loanId = loanId;
    }

    public LoanOperation(OperationCreditType operationCreditType, double sum, Currency currency, String merchant, int customerId, int loanId) {
        super(operationCreditType, sum, currency, merchant, customerId);
        this.loanId = loanId;
    }

    @Override
    public String toString() {
        return "LoanOperation{" +
                "loanId=" + loanId +
                '}';
    }
}