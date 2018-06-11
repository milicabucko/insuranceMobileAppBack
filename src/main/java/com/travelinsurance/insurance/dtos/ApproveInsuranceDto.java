package com.travelinsurance.insurance.dtos;

import com.travelinsurance.insurance.models.User;

public class ApproveInsuranceDto {

    private Long id;
    private User seller;
    private boolean approve;

    public ApproveInsuranceDto() {
    }

    public ApproveInsuranceDto(Long id, User seller, boolean approve) {
        this.id = id;
        this.seller = seller;
        this.approve = approve;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }
}
