/**
 * @author DuyTT10
 * @date Mar 26, 2021
 * @version 1.0
 */
package com.iuh.rencarproject.dto.request;

import com.iuh.rencarproject.entity.Bill;
import com.iuh.rencarproject.entity.Course;

import lombok.Data;

@Data
public class BillRequestDTO {

	private String name;
	private String phoneNumber;
	private String email;
	private String linkFacebook;
	private String address;
	private int purchaseType;
	private Long courseId;
	
	public Bill convertBillCreationToEntity(Course course) {
		Bill bill = new Bill();
		bill.setName(name);
		bill.setPhoneNumber(phoneNumber);
		bill.setEmail(email);
		bill.setLinkFacebook(linkFacebook);
		bill.setCourse(course);
		return bill;
	}
}

