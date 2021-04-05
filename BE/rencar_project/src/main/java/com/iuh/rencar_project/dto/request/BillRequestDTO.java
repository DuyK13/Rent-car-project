///**
// * @author DuyTT10
// * @date Mar 26, 2021
// * @version 1.0
// */
//package com.iuh.rencar_project.dto.request;
//
//import com.iuh.rencar_project.entity.Bill;
//import com.iuh.rencar_project.entity.Course;
//
//import lombok.Data;
//
//@Data
//public class BillRequestDTO {
//
//	private String name;
//	private String phoneNumber;
//	private String email;
//	private String linkFacebook;
//	private String address;
//	private int purchaseType;
//	private Long courseId;
//	
//	public Bill convertBillCreationToEntity(Course course) {
//		Bill bill = new Bill();
//		bill.setName(name);
//		bill.setPhoneNumber(phoneNumber);
//		bill.setEmail(email);
//		bill.setLinkFacebook(linkFacebook);
//		bill.setCourse(course);
//		return bill;
//	}
//}
//
