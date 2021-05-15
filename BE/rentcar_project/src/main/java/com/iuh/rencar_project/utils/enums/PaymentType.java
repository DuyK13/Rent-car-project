package com.iuh.rencar_project.utils.enums;

public enum PaymentType {
	MOMO("Momo"), ZALO_PAY("Zalo Pay");

	private final String paymentMethod;

	PaymentType(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

}
