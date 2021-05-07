package com.iuh.rencar_project.utils.enums;

public enum PaymentType {
	MOMO("Momo"), ZALO_PAY("Zalo Pay");

	private String paymentMethod;

	private PaymentType(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

}
