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

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

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

//    @Override
//    public String save(BillRequest billRequest) {
//        Bill bill = billMapper.toEntityByGuest(billRequest);
//        try {
//            billRepository.saveAndFlush(bill);
//        } catch (Exception e) {
//            logger.error("Bill Exception: ", e);
//            throw new EntityException("Bill save failed");
//        }
//        if (emailService.preserve(bill)) {
//            return "Bill save successful and email sent";
//        }
//        return "Bill save successful";
//    }

    @Override
    public String create(BillRequest billRequest) {
        Bill bill = billMapper.toEntityByStaff(billRequest);
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

//    @Override
//    public String updateBillPending(Long id) {
//        Bill bill = this.findById(id);
//        if (bill.getState() == BillState.PENDING)
//            bill.setState(BillState.APPROVED);
//        else
//            throw new EntityException("Bill approve failed");
//        try {
//            billRepository.saveAndFlush(bill);
//        } catch (Exception e) {
//            logger.error("Bill Exception: ", e);
//            throw new EntityException("Bill approve failed");
//        }
//        return "Bill approve successful";
//    }

//    @Override
//    public String updateBillRented(Long id) {
//        Bill bill = this.findById(id);
//        if (bill.getState() == BillState.RENTED) {
//            Long rentTime = bill.getRentTime();
//            Long lateCharge = this.calculateLateCharge(bill);
//            bill.setLateCharge(lateCharge);
//            bill.setBillAmount(rentTime * bill.getCar().getCostPerHour());
//            bill.setCar(carService.updateCarForBillPaid(bill.getCar()));
//            bill.setState(BillState.PAID);
//            try {
//                billRepository.saveAndFlush(bill);
//            } catch (Exception e) {
//                logger.error("Bill Exception: ", e);
//                throw new EntityException("Pay for rental car failed");
//            }
//            if (emailService.sendBillEmailByStaff(bill)) {
//                return "Pay for rental car successful. Bill sending to customer";
//            }
//        } else throw new EntityException("Bill not rented");
//
//        return "Pay for rental car successful";
//    }

//    @Override
//    public String updateBillApproved(Long id, BillRequest billRequest) {
//        Bill bill = this.findById(id);
//        if (bill.getState() == BillState.APPROVED) {
//            billMapper.updateEntityToRentedOrPaid(billRequest, bill);
//            try {
//                bill = billRepository.saveAndFlush(bill);
//            } catch (Exception e) {
//                logger.error("Bill Exception: ", e);
//                throw new EntityException("Rent car failed");
//            }
//        } else
//            throw new EntityException("Bill not approved");
//        carService.updateCarForBillRented(bill.getCar());
//            return "Rent car successful";
//        return null;
//    }

//    @Override
//    public String delete(Long id) {
//        try {
//            billRepository.deleteById(id);
//        } catch (Exception e) {
//            logger.error("Bill Exception: ", e);
//            throw new EntityException("Bill delete fail");
//        }
//        return "Bill delete success";
//    }

//    @Transactional
//    @Override
//    public String deleteBillPending(Long id) {
//        return this.deleteByIdAndState(id, BillState.PENDING);
//    }

//    @Transactional
//    @Override
//    public String deleteBillApproved(Long id) {
//        return this.deleteByIdAndState(id, BillState.APPROVED);
//    }

    private String deleteByIdAndState(Long id, BillState state) {
        if (!billRepository.existsByIdAndState(id, state))
            throw new NotFoundException("Bill not found");
        try {
            billRepository.deleteByIdAndStateIs(id, state);
        } catch (Exception e) {
            logger.error("Bill Exception: ", e);
            throw new EntityException("Bill delete failed");
        }
        return "Bill delete successful";
    }

    @Override
    public Bill findById(Long id) {
        return billRepository.findById(id).orElseThrow(() -> new NotFoundException("Bill not found"));
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

//    @Override
//    public Long getCurrentId() {
//        List<Bill> bills = billRepository.findAll();
//        if (bills.isEmpty())
//            return 1L;
//        else
//            return bills.get(bills.size() - 1).getId() + 1;
//    }

//    private Long calculateLateCharge(Bill bill) {
//        Long rentTime = bill.getRentTime();
//        LocalDateTime endTime = bill.getStartTime().plusHours(rentTime);
//        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
//        long minutes = Duration.between(endTime, now).toMinutes();
//        return minutes > 29 ? (minutes / 30) * Bill.LATE_CHARGE : 0L;
//    }

//    @Override
//    public Long getBillLateChargeById(Long id) {
//        return this.calculateLateCharge(this.findById(id));
//    }

    @Override
    public Long getBillAmountById(Long id) {
        Bill bill = this.findById(id);
        return bill.getRentTime() * bill.getCar().getCostPerHour();
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
