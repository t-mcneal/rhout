package com.rhout.backend.requests;

import com.rhout.backend.requests.gmaps.GmapsRequest;

public class GoogleApiRequest<T>  {
    private GmapsRequest<T> gmapsRequest;
    private DataObject<T> dataObject;

    public GoogleApiRequest(GmapsRequest<T> request, DataObject<T> dataObject) {
        this.gmapsRequest = request;
        this.dataObject = dataObject;
    }

    public T[] getData() {
        return dataObject.getData();
    }

    public void execute() {
        dataObject.setData(gmapsRequest.execute());
    }
}
