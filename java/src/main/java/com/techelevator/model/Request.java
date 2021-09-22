package com.techelevator.model;

public class Request {

    private long requestId;
    private long playDateId;
    private long statusId;
    private long mateId;

    public Request(){

    }

    public Request(long requestId, long playDateId, long statusId, long mateId) {
        this.requestId = requestId;
        this.playDateId = playDateId;
        this.statusId = statusId;
        this.mateId = mateId;
    }

    public long getRequestId() {
        return requestId;
    }

    public long getPlayDateId() {
        return playDateId;
    }

    public long getStatusId() {
        return statusId;
    }

    public long getMateId() {
        return mateId;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", playDateId=" + playDateId +
                ", statusId=" + statusId +
                ", mateId=" + mateId +
                '}';
    }
}
