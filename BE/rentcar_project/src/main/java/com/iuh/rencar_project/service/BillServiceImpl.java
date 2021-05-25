package com.iuh.rencar_project.service;

import com.iuh.rencar_project.dto.request.BillRequest;
import com.iuh.rencar_project.entity.Bill;
import com.iuh.rencar_project.repository.BillRepository;
import com.iuh.rencar_project.service.template.IBillService;
import com.iuh.rencar_project.utils.enums.BillState;
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
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public BillServiceImpl(BillRepository billRepository, IBillMapper billMapper, PasswordEncoder passwordEncoder) {
        this.billRepository = billRepository;
        this.billMapper = billMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String save(BillRequest billRequest) {
        try {
            Bill bill = billMapper.toEntity(billRequest);
            bill.setSlug(passwordEncoder.encode(this.getCurrentId() + "").replace("//", ""));
            billRepository.saveAndFlush(bill);
        } catch (Exception e) {
            logger.error("Bill Exception: ", e);
            throw new EntityException("Bill save fail");
        }
        return "Bill save success";
    }

    @Override
    public String saveByStaff(BillRequest billRequest) {
        try {
            Bill bill = billMapper.toEntity(billRequest);
            bill.setSlug(passwordEncoder.encode(this.getCurrentId() + "").replace("//", ""));
            bill.setState(BillState.Pending_Payment);
            billRepository.saveAndFlush(bill);
        } catch (Exception e) {
            logger.error("Bill Exception: ", e);
            throw new EntityException("Bill save fail");
        }
        return "Bill save success";
    }

    @Override
    public String updateBillPreOrder(Long id) {
        Bill bill = this.findById(id);
        String billId = "#" + bill.getId();
        try {
            if (bill.getState().compareTo(BillState.Pre_Order) == 0)
                bill.setState(BillState.Pending_Payment);
            billRepository.saveAndFlush(bill);
        } catch (Exception e) {
            logger.error("Bill Exception: ", e);
            throw new EntityException("Bill " + billId + " confirm pre order failed");
        }
        return "Bill " + billId + " confirm pre order successful";
    }

    @Override
    public String updateBillPendingPayment(Long id) {
        Bill bill = this.findById(id);
        String billId = "#" + bill.getId();
        try {
            if (bill.getState().compareTo(BillState.Pending_Payment) == 0)
                bill.setState(BillState.Payed);
            billRepository.saveAndFlush(bill);
        } catch (Exception e) {
            logger.error("Bill Exception: ", e);
            throw new EntityException("Bill " + billId + " confirm pay failed");
        }
        return "Bill " + billId + " confirm pay successful";
    }

    @Override
    public String delete(Long id) {
        Bill bill = this.findById(id);
        String billId = "#" + bill.getId();
        try {
            billRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Bill Exception: ", e);
            throw new EntityException("Bill " + billId + " delete fail");
        }
        return "Bill " + billId + " delete success";
    }

    @Override
    public Bill findById(Long id) {
        return billRepository.findById(id).orElseThrow(() -> new NotFoundException("Bill #" + id + " not found"));
    }

    @Override
    public Bill findBySlug(String var) {
        return billRepository.findBySlug(var).orElseThrow(() -> new NotFoundException("Bill with slug" + var + " not found"));
    }

    @Override
    public Page<Bill> findAllPaginated(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by(Sort.Order.asc("id")));
        return billRepository.findAll(pageable);
    }

    private Long getCurrentId() {
        List<Bill> bills = billRepository.findAll();
        if (bills.isEmpty())
            return 1L;
        else
            return bills.get(bills.size() - 1).getId() + 1;

    }
}
