package com.iuh.rencar_project.service;

import com.iuh.rencar_project.dto.request.BillRequest;
import com.iuh.rencar_project.entity.Bill;
import com.iuh.rencar_project.repository.BillRepository;
import com.iuh.rencar_project.service.template.IBillService;
import com.iuh.rencar_project.service.template.ICarService;
import com.iuh.rencar_project.service.template.IEmailService;
import com.iuh.rencar_project.utils.enums.BillState;
import com.iuh.rencar_project.utils.enums.CarState;
import com.iuh.rencar_project.utils.exception.bind.EntityException;
import com.iuh.rencar_project.utils.exception.bind.NotFoundException;
import com.iuh.rencar_project.utils.mapper.IBillMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/15/2021 10:09 AM
 */
@Service
public class BillServiceImpl implements IBillService {

    private static final Logger logger = LogManager.getLogger(BillServiceImpl.class);
    private final BillRepository billRepository;
    private final IBillMapper billMapper;
    private final IEmailService emailService;
    private final ICarService carService;

    @Autowired
    public BillServiceImpl(BillRepository billRepository, IBillMapper billMapper, IEmailService emailService, ICarService carService) {
        this.billRepository = billRepository;
        this.billMapper = billMapper;
        this.emailService = emailService;
        this.carService = carService;
    }

    @Override
    public String create(BillRequest billRequest) {
        Bill bill = billMapper.toEntityByStaff(billRequest);
        bill.setBillAmount(bill.getCar().getCostPerHour() * bill.getRentTime());
        if (bill.getCar().getState() == CarState.UNAVAILABLE)
            throw new EntityException("Car can not rent");
        try {
            billRepository.saveAndFlush(bill);
        } catch (Exception e) {
            logger.error("Bill Exception: ", e);
            throw new EntityException("Bill save failed");
        }
        carService.updateCarForBillRented(bill.getCar());
        return "Bill save successful";
    }


    @Override
    public String updateBillRented(Long id, BillRequest billRequest) {
        Bill bill = this.findById(id);
        billMapper.updateBill(billRequest, bill);
        try {
            bill = billRepository.saveAndFlush(bill);
        } catch (Exception e) {
            logger.error("Bill Exception: ", e);
            throw new EntityException("Pay for rental car failed");
        }
        carService.updateCarForBillPaid(bill.getCar());
        if (emailService.sendBillEmailByStaff(bill)) {
            return "Pay for rental car successful. Bill sending to customer";
        }
        return "Pay for rental car successful";
    }


    @Override
    public Bill findById(Long id) {
        Bill bill = billRepository.findById(id).orElseThrow(() -> new NotFoundException("Bill not found"));
        emailService.sendBillEmailByStaff(bill);
        return bill;
    }

    @Override
    public Page<Bill> findAllPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Order.asc("id")));
        return billRepository.findAll(pageable);
    }

    @Override
    public Page<Bill> findAllPaginatedAndState(int pageNo, int pageSize, BillState state) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Order.asc("id")));
        return billRepository.findAllByStateIs(pageable, state);
    }

    @Override
    public Page<Bill> findAllPaginatedAndStateWithSearch(int pageNo, int pageSize, BillState state, String text) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Order.asc("id")));
        return billRepository.search(text, state, pageable);
    }

    @Override
    public Page<Bill> search(int pageNo, int pageSize, String text) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Order.asc("id")));
        return billRepository.search(text, pageable);
    }

    @Override
    public List<Bill> findAllByMonthAndYear(int month, int year) {
        return billRepository.findAllByMonthAndYear(month, year);
    }
}
