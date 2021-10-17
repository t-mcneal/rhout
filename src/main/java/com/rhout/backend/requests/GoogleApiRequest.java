package com.rhout.backend.requests;

import com.rhout.backend.requests.gmaps.GmapsRequest;

public class GoogleApiRequest<T>  {
    private GmapsRequest<T> gmapsRequest;
    private DataObject<T> dataObject;

    public GoogleApiRequest() {}

    public GoogleApiRequest(GmapsRequest<T> request, DataObject<T> dataObject) {
        this.gmapsRequest = request;
        this.dataObject = dataObject;
    }

    public void setGmapsRequest(GmapsRequest<T> newGmapsRequest) {
        gmapsRequest = newGmapsRequest;
    }

    public void setDataObject(DataObject<T> newDataObject) {
        dataObject = newDataObject;
    }

    public T[] getData() {
        return dataObject.getData();
    }

    public void execute() {
        dataObject.setData(gmapsRequest.execute());
    }


}
