/**
 * @author DuyTT10
 * @date Mar 26, 2021
 * @version 1.0
 */
package com.iuh.rencarproject.dto.response;

import com.iuh.rencarproject.entity.Bill;
import com.iuh.rencarproject.entity.Course;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BillResponseDTO {
	private Long id;

	private String name;

	private String phoneNumber;

	private String email;

	private String linkFacebook;

	private int purchaseType;

	private Course course;
	
	public BillResponseDTO(Bill bill) {
		this(bill.getId(), bill.getName(), bill.getPhoneNumber(), bill.getEmail(), bill.getLinkFacebook(), bill.getPurchaseType(), bill.getCourse());
	}
}

