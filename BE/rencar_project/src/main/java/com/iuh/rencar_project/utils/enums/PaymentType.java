package com.iuh.rencar_project.utils.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PaymentType {
	MOMO("Momo"), ZALO_PAY("Zalo Pay");

	private String paymentMethod;

}
