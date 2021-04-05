///**
// * @author DuyTT10
// * @date Mar 26, 2021
// * @version 1.0
// */
//package com.iuh.rencar_project.controller;
//
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.iuh.rencarproject.dto.request.BillRequestDTO;
//import com.iuh.rencarproject.dto.response.AppResponse;
//import com.iuh.rencarproject.dto.response.BillResponseDTO;
//import com.iuh.rencarproject.dto.response.ErrorResponse;
//import com.iuh.rencarproject.dto.response.SuccessResponse;
//import com.iuh.rencarproject.entity.Bill;
//import com.iuh.rencarproject.service.template.IBillService;
//
//@RequestMapping("/bills")
//@RestController
//@PropertySource("classpath:error.properties")
//public class BillController {
//
//	@Autowired
//	private Environment env;
//
//	@Autowired
//	IBillService billService;
//
//	/**
//	 * Create bill
//	 * @param billRequestDTO
//	 * @param errors
//	 * @return
//	 */
//	@PostMapping
//	public ResponseEntity<AppResponse> createBill(@Valid @RequestBody BillRequestDTO billRequestDTO,
//			BindingResult errors) {
//		if (errors.hasErrors())
//			return new ResponseEntity<>(new ErrorResponse(errors.getFieldError().getDefaultMessage()), HttpStatus.OK);
//		Bill bill = billService.createBill(billRequestDTO);
//		if (bill != null)
//			return new ResponseEntity<>(new SuccessResponse<BillResponseDTO>(env.getProperty("error.bill.101"),
//					new BillResponseDTO(bill)), HttpStatus.OK);
//		return new ResponseEntity<>(new ErrorResponse(env.getProperty("error.bill.100")), HttpStatus.OK);
//	}
//	
//	/**
//	 * get Bill
//	 * @param billId
//	 * @return
//	 */
//	@GetMapping(value = "/{id}")
//	public ResponseEntity<AppResponse> getBill(@PathVariable("id") Long billId) {
//		Bill bill = billService.findById(billId);
//		if (bill != null)
//			return new ResponseEntity<>(new SuccessResponse<BillResponseDTO>(new BillResponseDTO(bill)), HttpStatus.OK);
//		return new ResponseEntity<>(new ErrorResponse(env.getProperty("error.bill.102")), HttpStatus.NOT_FOUND);
//	}
//	
//	@GetMapping()
//	public ResponseEntity<AppResponse> getUsers() {
//		return new ResponseEntity<>(new SuccessResponse<List<BillResponseDTO>>(billService.findAll()), HttpStatus.OK);
//	}
//}
