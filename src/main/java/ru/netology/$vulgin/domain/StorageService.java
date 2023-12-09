package ru.netology.$vulgin.domain;

import lombok.Data;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
@Data
public class StorageService<T>{

    public final static int MAX_OPERATION = 300;
    public final static int MAX_CUSTOMERS = 100;
    private List<T> storage;
    private  final static int OPERATIONS_PER_CUSTOMER = MAX_OPERATION / MAX_CUSTOMERS;
    @Getter
    private final static  int[][] statement = new int[MAX_CUSTOMERS][OPERATIONS_PER_CUSTOMER];

    public StorageService(int listCapacity) {
        this.storage = new ArrayList<>(listCapacity);
    }

    public T getElement(int position) {
        return storage.get(position);
    }

    public boolean isIndexValid(int index) {
        return index >= 0 && index < storage.size();
    }

    public void setElement(int position, T element) {
        storage.add(position, element);
    }
    public void setElementAtBegin(T element){
        storage.add(element);
    }

    public void setElement(T element) {
        storage.add(element);
    }

    public int size(){
        return storage.size();
    }
}