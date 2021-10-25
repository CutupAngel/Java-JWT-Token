package com.chainhaus.mtg.dto;

public class DealDto {

    private Long Id;

    private Double ask;

    private String email;

    private String address;

    private String zipCode;

    private Double capRate;


    private Boolean canReqInfo;
    private Boolean canMessage;
    private Boolean canAddWatch;
    private Boolean canEdit;

    private Boolean canMark;
    private Boolean marked;
    private Long markCount;

    private Boolean addedWatch;
    private Boolean requestedInfo;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Double getCapRate() {
        return capRate;
    }

    public void setCapRate(Double capRate) {
        this.capRate = capRate;
    }

    public Boolean getCanReqInfo() {
        return canReqInfo;
    }

    public void setCanReqInfo(Boolean canReqInfo) {
        this.canReqInfo = canReqInfo;
    }

    public Boolean getCanMessage() {
        return canMessage;
    }

    public void setCanMessage(Boolean canMessage) {
        this.canMessage = canMessage;
    }

    public Boolean getCanAddWatch() {
        return canAddWatch;
    }

    public void setCanAddWatch(Boolean canAddWatch) {
        this.canAddWatch = canAddWatch;
    }

    public Boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Boolean canEdit) {
        this.canEdit = canEdit;
    }

    public Boolean getAddedWatch() {
        return addedWatch;
    }

    public void setAddedWatch(Boolean addedWatch) {
        this.addedWatch = addedWatch;
    }

    public Boolean getRequestedInfo() {
        return requestedInfo;
    }

    public void setRequestedInfo(Boolean requestedInfo) {
        this.requestedInfo = requestedInfo;
    }

    public Boolean getCanMark() {
        return canMark;
    }

    public void setCanMark(Boolean canMark) {
        this.canMark = canMark;
    }

    public Boolean getMarked() {
        return marked;
    }

    public void setMarked(Boolean marked) {
        this.marked = marked;
    }

    public Long getMarkCount() {
        return markCount;
    }

    public void setMarkCount(Long markCount) {
        this.markCount = markCount;
    }
}
