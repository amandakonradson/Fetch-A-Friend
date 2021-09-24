package com.techelevator.model;

import java.sql.Time;
import java.util.Date;

public class PlayDate {

    private long playDateId;
    private long hostPetId;
    private long matePetId;
    private long locationId;
    private Date meetingDate;
    private Time startTime;
    private long duration;
    private String mateDescription;
    private String mateSize;
    private long statusId;

    public PlayDate(){

    }

    public PlayDate(long playDateId, long hostPetId, long matePetId, long locationId, Date meetingDate, Time startTime, long duration, String mateDescription, String mateSize, long statusId) {
        this.playDateId = playDateId;
        this.hostPetId = hostPetId;
        this.matePetId = matePetId;
        this.locationId = locationId;
        this.meetingDate = meetingDate;
        this.startTime = startTime;
        this.duration = duration;
        this.mateDescription = mateDescription;
        this.mateSize = mateSize;
        this.statusId = statusId;
    }

    public long getPlayDateId() {
        return playDateId;
    }

    public void setPlayDateId(long playDateId) {
        this.playDateId = playDateId;
    }

    public long getHostPetId() {
        return hostPetId;
    }

    public void setHostPetId(long hostPetId) {
        this.hostPetId = hostPetId;
    }

    public long getMatePetId() {
        return matePetId;
    }

    public void setMatePetId(long matePetId) {
        this.matePetId = matePetId;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getMateDescription() {
        return mateDescription;
    }

    public void setMateDescription(String mateDescription) {
        this.mateDescription = mateDescription;
    }

    public String getMateSize() {
        return mateSize;
    }

    public void setMateSize(String mateSize) {
        this.mateSize = mateSize;
    }

    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "PlayDate{" +
                "playDateId=" + playDateId +
                ", hostPetId=" + hostPetId +
                ", matePetId=" + matePetId +
                ", locationId=" + locationId +
                ", meetingDate=" + meetingDate +
                ", startTime=" + startTime +
                ", duration=" + duration +
                ", mateDescription='" + mateDescription + '\'' +
                ", mateSize='" + mateSize + '\'' +
                ", statusId=" + statusId +
                '}';
    }
}
