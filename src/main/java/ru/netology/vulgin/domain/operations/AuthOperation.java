package ru.netology.vulgin.domain.operations;

import ru.netology.vulgin.domain.ConsolePrintable;

public class AuthOperation extends BaseOperation implements ConsolePrintable {
    private String id;

    @Override
    public void printToConsole() {
        System.out.println();
    }
}