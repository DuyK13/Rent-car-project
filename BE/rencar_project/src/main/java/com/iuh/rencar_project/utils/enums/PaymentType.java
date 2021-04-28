package com.iuh.rencar_project.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PaymentType {
	MOMO("Momo"), ZALO_PAY("Zalo Pay");

	private String paymentMethod;

}
