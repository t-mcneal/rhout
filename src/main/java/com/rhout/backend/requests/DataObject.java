package com.rhout.backend.requests;

public class DataObject<T> {
    private T[] data;

    public DataObject() {}

    public DataObject(T[] data) {
        this.data = data;
    }

    public void setData(T[] newData) {
        data = newData;
    }

    public T[] getData() {
        return data;
    }
}
