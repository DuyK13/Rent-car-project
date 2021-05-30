package com.iuh.rencar_project.dto.response;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @datetime 5/30/2021 12:03 PM
 */
public class ChargeResponse {
    private Long billAmount;
    private Long lateCharge;

    public ChargeResponse(Long billAmount, Long lateCharge) {
        this.billAmount = billAmount;
        this.lateCharge = lateCharge;
    }

    public Long getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(Long billAmount) {
        this.billAmount = billAmount;
    }

    public Long getLateCharge() {
        return lateCharge;
    }

    public void setLateCharge(Long lateCharge) {
        this.lateCharge = lateCharge;
    }
}
