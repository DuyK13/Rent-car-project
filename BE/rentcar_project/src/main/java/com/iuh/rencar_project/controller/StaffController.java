package com.iuh.rencar_project.controller;

import com.iuh.rencar_project.dto.request.BillRequest;
import com.iuh.rencar_project.dto.response.*;
import com.iuh.rencar_project.service.template.IBillService;
import com.iuh.rencar_project.service.template.ICarService;
import com.iuh.rencar_project.service.template.ICategoryService;
import com.iuh.rencar_project.service.template.ICourseService;
import com.iuh.rencar_project.utils.enums.BillState;
import com.iuh.rencar_project.utils.mapper.IBillMapper;
import com.iuh.rencar_project.utils.mapper.ICategoryMapper;
import com.iuh.rencar_project.utils.mapper.ICourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    private final ICourseService courseService;

    private final IBillMapper billMapper;

    private final ICourseMapper courseMapper;

    private final ICategoryMapper categoryMapper;

    @Autowired
    public StaffController(IBillService billService, ICarService carService, ICategoryService categoryService, ICourseService courseService, IBillMapper billMapper, ICourseMapper courseMapper, ICategoryMapper categoryMapper) {
        this.billService = billService;
        this.carService = carService;
        this.categoryService = categoryService;
        this.courseService = courseService;
        this.billMapper = billMapper;
        this.courseMapper = courseMapper;
        this.categoryMapper = categoryMapper;
    }

    // ======================================
    // ================ COURSE ==============
    // ======================================

    @GetMapping("/courses")
    public ResponseEntity<?> getCoursesEnable() {
        List<CourseResponse> courseResponseList = courseService.findAllEnable().stream().map(courseMapper::toResponse).collect(Collectors.toList());
        return new ResponseEntity<>(courseResponseList, HttpStatus.OK);
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

    /**
     * WORK
     *
     * @param billRequest
     * @return
     */
    @PostMapping("/bills")
    public ResponseEntity<?> saveBill(@RequestBody BillRequest billRequest) {
        return new ResponseEntity<>(new MessageResponse(billService.saveByStaff(billRequest)), HttpStatus.OK);
    }

    /**
     * WORK
     *
     * @param id
     * @return
     */
    @PutMapping("/bills/{id}/pending")
    public ResponseEntity<?> updateBillPending(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(billService.updateBillPending(id)), HttpStatus.OK);
    }

    /**
     * WORK
     *
     * @param id
     * @return
     */
    @DeleteMapping("/bills/{id}/pending")
    public ResponseEntity<?> deleteBillPending(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(billService.deleteBillPending(id)), HttpStatus.OK);
    }

    /**
     * WORK
     *
     * @param id
     * @param billRequest
     * @return
     */
    @PutMapping("/bills/{id}/approved")
    public ResponseEntity<?> updateBillApproved(@PathVariable(name = "id") Long id, @RequestBody BillRequest billRequest) {
        return new ResponseEntity<>(new MessageResponse(billService.updateBillApproved(id, billRequest)), HttpStatus.OK);
    }

    /**
     * WORD
     *
     * @param id
     * @return
     */
    @DeleteMapping("/bills/{id}/approved")
    public ResponseEntity<?> deleteBillApproved(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(billService.deleteBillApproved(id)), HttpStatus.OK);
    }

    /**
     * WORK
     *
     * @param id
     * @return
     */
    @PutMapping("/bills/{id}/rented")
    public ResponseEntity<?> updateBillRented(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(billService.updateBillRented(id)), HttpStatus.OK);
    }

    /**
     * WORK
     *
     * @param id
     * @return
     */
    @GetMapping("/bills/{id}")
    public ResponseEntity<?> getBillById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(billMapper.toResponse(billService.findById(id)), HttpStatus.OK);
    }

    /**
     * WORK
     *
     * @param id
     * @return
     */
    @GetMapping("/bills/{id}/calculate-charge")
    public ResponseEntity<?> getBillChargeById(@PathVariable(name = "id") Long id) {
        Long amount = billService.getBillAmountById(id);
        Long lateCharge = billService.getBillLateChargeById(id);
        return new ResponseEntity<>(new ChargeResponse(amount, lateCharge), HttpStatus.OK);
    }

    /**
     * WORK
     *
     * @param pageNo
     * @param pageSize
     * @param state
     * @return
     */
    @GetMapping("/bills")
    public ResponseEntity<?> getBillPaginated(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo, @RequestParam(name = "pageSize", defaultValue = "5") int pageSize, @RequestParam(name = "state", defaultValue = "pending") String state) {
        Page<BillResponse> pageCourseResponse = null;
        if (state.equalsIgnoreCase(BillState.PENDING.name()))
            pageCourseResponse = billService.findAllPaginatedAndState(pageNo, pageSize, BillState.PENDING)
                    .map(billMapper::toResponse);
        else if (state.equalsIgnoreCase(BillState.APPROVED.name()))
            pageCourseResponse = billService.findAllPaginatedAndState(pageNo, pageSize, BillState.APPROVED)
                    .map(billMapper::toResponse);
        else if (state.equalsIgnoreCase(BillState.RENTED.name()))
            pageCourseResponse = billService.findAllPaginatedAndState(pageNo, pageSize, BillState.RENTED)
                    .map(billMapper::toResponse);
        PageResponse<BillResponse> pageResult = null;
        if (pageCourseResponse != null)
            pageResult = new PageResponse<>(pageCourseResponse.getContent(),
                    pageCourseResponse.getTotalPages(), pageCourseResponse.getNumber());
        return new ResponseEntity<>((pageResult != null && pageResult.getContent().size() > 0) ? pageResult : new MessageResponse("Nothing to show"), HttpStatus.OK);
    }
}
