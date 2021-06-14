package com.iuh.rencar_project.service.template;

import com.iuh.rencar_project.dto.request.BillRequest;
import com.iuh.rencar_project.entity.Bill;
import com.iuh.rencar_project.utils.enums.BillState;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/15/2021 10:06 AM
 */
public interface IBillService {

    String create(BillRequest billRequest);

    Bill findById(Long id);

    Page<Bill> findAllPaginated(int pageNo, int pageSize);

    String updateBillRented(Long id, BillRequest billRequest);

    Page<Bill> findAllPaginatedAndState(int pageNo, int pageSize, BillState state);

    Page<Bill> findAllPaginatedAndStateWithSearch(int pageNo, int pageSize, BillState state, String text);

    Page<Bill> search(int pageNo, int pageSize, String text);

    List<Bill> findAllByMonthAndYear(int month, int year);
}
