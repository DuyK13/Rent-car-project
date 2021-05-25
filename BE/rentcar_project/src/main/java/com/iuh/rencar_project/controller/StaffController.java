package com.iuh.rencar_project.controller;

import com.iuh.rencar_project.dto.request.BillRequest;
import com.iuh.rencar_project.dto.response.BillResponse;
import com.iuh.rencar_project.dto.response.CarResponse;
import com.iuh.rencar_project.dto.response.MessageResponse;
import com.iuh.rencar_project.dto.response.PageResponse;
import com.iuh.rencar_project.entity.Car;
import com.iuh.rencar_project.entity.Category;
import com.iuh.rencar_project.service.template.IBillService;
import com.iuh.rencar_project.service.template.ICarService;
import com.iuh.rencar_project.service.template.ICategoryService;
import com.iuh.rencar_project.utils.mapper.IBillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/24/2021 9:35 AM
 */

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final IBillService billService;

    private final ICarService carService;

    private final ICategoryService categoryService;

    private final IBillMapper billMapper;

    @Autowired
    public StaffController(IBillService billService, ICarService carService, ICategoryService categoryService, IBillMapper billMapper) {
        this.billService = billService;
        this.carService = carService;
        this.categoryService = categoryService;
        this.billMapper = billMapper;
    }

    // ======================================
    // ================ BILL ================
    // ======================================

    @PostMapping("/bills")
    public ResponseEntity<?> saveBill(@RequestBody BillRequest billRequest) {
        return new ResponseEntity<>(new MessageResponse(billService.saveByStaff(billRequest)), HttpStatus.OK);
    }

    @GetMapping("/bills/{id}")
    public ResponseEntity<?> getBillById(@PathVariable(name = "id") Long id) {
        BillResponse billResponse = billMapper.toResponse(billService.findById(id));
        CarResponse carResponse = billResponse.getCar();
        Car car = carService.findById(carResponse.getId());
        Category category = categoryService.findByCar(car);
        carResponse.setCategoryName(category.getName());
        billResponse.setCar(carResponse);
        return new ResponseEntity<>(billResponse, HttpStatus.OK);
    }

    @PutMapping("/bills/{id}/pre-order")
    public ResponseEntity<?> updateBillPreOrder(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(billService.updateBillPreOrder(id)), HttpStatus.OK);
    }

    @PutMapping("/bills/{id}/pending-payment")
    public ResponseEntity<?> updateBillPendingPayment(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(billService.updateBillPendingPayment(id)), HttpStatus.OK);
    }

    @GetMapping("/bills/pre-order")
    public ResponseEntity<?> getBillPaginatedPreOrder(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo) {
//        Page<BillResponse> pageBillResponse = billService.findAllPaginated(pageNo)
//                .map(billMapper::toResponse);     
//        PageResponse<BillResponse> pageResult = new PageResponse<>(pageBillResponse.getContent(),
//                pageBillResponse.getTotalPages(), pageBillResponse.getNumber());
//        return new ResponseEntity<>(pageResult, HttpStatus.OK);
        return null;
    }

    @GetMapping("/bills/pending-payment")
    public ResponseEntity<?> getBillPaginatedPendingPayment(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo) {
//        Page<BillResponse> pageBillResponse = billService.findAllPaginated(pageNo)
//                .map(billMapper::toResponse);
//        PageResponse<BillResponse> pageResult = new PageResponse<>(pageBillResponse.getContent(),
//                pageBillResponse.getTotalPages(), pageBillResponse.getNumber());
//        return new ResponseEntity<>(pageResult, HttpStatus.OK);
        return null;
    }
}
