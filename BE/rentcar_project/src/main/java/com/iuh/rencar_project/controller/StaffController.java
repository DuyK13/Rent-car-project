package com.iuh.rencar_project.controller;

import com.iuh.rencar_project.dto.request.BillRequest;
import com.iuh.rencar_project.dto.response.*;
import com.iuh.rencar_project.service.template.IBillService;
import com.iuh.rencar_project.service.template.ICarService;
import com.iuh.rencar_project.service.template.ICategoryService;
import com.iuh.rencar_project.service.template.IReservationService;
import com.iuh.rencar_project.utils.enums.BillState;
import com.iuh.rencar_project.utils.mapper.IBillMapper;
import com.iuh.rencar_project.utils.mapper.ICarMapper;
import com.iuh.rencar_project.utils.mapper.ICategoryMapper;
import com.iuh.rencar_project.utils.mapper.IReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final IBillService billService;

    private final ICarService carService;

    private final ICategoryService categoryService;

    private final IReservationService reservationService;

    private final IBillMapper billMapper;

    private final ICategoryMapper categoryMapper;

    private final ICarMapper carMapper;

    private final IReservationMapper reservationMapper;

    @Autowired
    public StaffController(IBillService billService, ICarService carService, ICategoryService categoryService, IReservationService reservationService, IBillMapper billMapper, ICategoryMapper categoryMapper, ICarMapper carMapper, IReservationMapper reservationMapper) {
        this.billService = billService;
        this.carService = carService;
        this.categoryService = categoryService;
        this.reservationService = reservationService;
        this.billMapper = billMapper;
        this.categoryMapper = categoryMapper;
        this.carMapper = carMapper;
        this.reservationMapper = reservationMapper;
    }
    // ======================================
    // ================= CAR ================
    // ======================================

    @GetMapping("/cars")
    public ResponseEntity<?> getCarEnable() {
        List<CarResponse> carResponseList = carService.findAllEnable().stream().map(carMapper::toResponse).collect(Collectors.toList());
        return new ResponseEntity<>(carResponseList, HttpStatus.OK);
    }

    // ======================================
    // ============ CATEGORY ================
    // ======================================

    @GetMapping("/categories")
    public ResponseEntity<?> getCategoriesEnable() {
        List<CategoryResponse> categoryResponseList = categoryService.findAllEnable().stream().map(categoryMapper::toResponse).collect(Collectors.toList());
        return new ResponseEntity<>(categoryResponseList, HttpStatus.OK);
    }

    // ======================================
    // ================ BILL ================
    // ======================================

    @PostMapping("/bills")
    public ResponseEntity<?> saveBill(@RequestBody BillRequest billRequest) {
        return new ResponseEntity<>(new MessageResponse(billService.create(billRequest)), HttpStatus.OK);
    }

//    @PutMapping("/bills/{id}/pending")
//    public ResponseEntity<?> updateBillPending(@PathVariable(name = "id") Long id) {
//        return new ResponseEntity<>(new MessageResponse(billService.updateBillPending(id)), HttpStatus.OK);
//    }

//    @DeleteMapping("/bills/{id}/pending")
//    public ResponseEntity<?> deleteBillPending(@PathVariable(name = "id") Long id) {
//        return new ResponseEntity<>(new MessageResponse(billService.deleteBillPending(id)), HttpStatus.OK);
//    }

//    @PutMapping("/bills/{id}/approved")
//    public ResponseEntity<?> updateBillApproved(@PathVariable(name = "id") Long id, @RequestBody BillRequest billRequest) {
//        return new ResponseEntity<>(new MessageResponse(billService.updateBillApproved(id, billRequest)), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/bills/{id}/approved")
//    public ResponseEntity<?> deleteBillApproved(@PathVariable(name = "id") Long id) {
//        return new ResponseEntity<>(new MessageResponse(billService.deleteBillApproved(id)), HttpStatus.OK);
//    }

//    @PutMapping("/bills/{id}/rented")
//    public ResponseEntity<?> updateBillRented(@PathVariable(name = "id") Long id) {
//        return new ResponseEntity<>(new MessageResponse(billService.updateBillRented(id)), HttpStatus.OK);
//    }

    @GetMapping("/bills/{id}")
    public ResponseEntity<?> getBillById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(billMapper.toResponse(billService.findById(id)), HttpStatus.OK);
    }

//    @GetMapping("/bills/{id}/calculate-charge")
//    public ResponseEntity<?> getBillChargeById(@PathVariable(name = "id") Long id) {
//        Long amount = billService.getBillAmountById(id);
//        Long lateCharge = billService.getBillLateChargeById(id);
//        return new ResponseEntity<>(new ChargeResponse(amount, lateCharge), HttpStatus.OK);
//    }

    @GetMapping("/bills")
    public ResponseEntity<?> getBillPaginated(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo, @RequestParam(name = "pageSize", defaultValue = "5") int pageSize, @RequestParam(name = "state") String state, @RequestParam(name = "search", required = false) Optional<String> text) {
        Page<BillResponse> pageCourseResponse = text.map(s -> billService.findAllPaginatedAndStateWithSearch(pageNo, pageSize, Enum.valueOf(BillState.class, state.toUpperCase()), s)
                .map(billMapper::toResponse)).orElseGet(() -> billService.findAllPaginatedAndState(pageNo, pageSize, Enum.valueOf(BillState.class, state.toUpperCase()))
                .map(billMapper::toResponse));
        PageResponse<BillResponse> pageResult;
        pageResult = new PageResponse<>(pageCourseResponse.getContent(),
                pageCourseResponse.getTotalPages(), pageCourseResponse.getNumber());
        return new ResponseEntity<>(pageResult.getContent().size() > 0 ? pageResult : new MessageResponse("Nothing to show"), HttpStatus.OK);
    }

    // ======================================
    // ============= RESERVATION ============
    // ======================================

    @PutMapping("reservation/{id}/approved")
    public ResponseEntity<MessageResponse> approvedReservation(@PathVariable Long id) {
        return new ResponseEntity<>(new MessageResponse(reservationService.approvedReservation(id)), HttpStatus.OK);
    }

    @PutMapping("reservation/{id}/cancel")
    public ResponseEntity<MessageResponse> cancelReservation(@PathVariable Long id) {
        return new ResponseEntity<>(new MessageResponse(reservationService.cancelReservation(id)), HttpStatus.OK);
    }

    @GetMapping("reservation")
    public ResponseEntity<PageResponse<ReservationResponse>> getPage(@RequestParam(name = "pageNo") int pageNo, @RequestParam(name = "pageSize") int pageSize, @RequestParam(name = "state", defaultValue = "") String state, @RequestParam(name = "search", defaultValue = "") String search) {
        Page<ReservationResponse> responsePage = reservationService.getPageByState(state.toUpperCase(), search, pageNo, pageSize).map(reservationMapper::toResponse);
        PageResponse<ReservationResponse> reservationResponsePageResponse = new PageResponse<>(responsePage.getContent(), responsePage.getTotalPages(), responsePage.getNumber());
        return new ResponseEntity<>(reservationResponsePageResponse, HttpStatus.OK);
    }

}
