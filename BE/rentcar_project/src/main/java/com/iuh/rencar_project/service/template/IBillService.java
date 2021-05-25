package com.iuh.rencar_project.service.template;

import com.iuh.rencar_project.dto.request.BillRequest;
import com.iuh.rencar_project.entity.Bill;
import org.springframework.data.domain.Page;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/15/2021 10:06 AM
 */
public interface IBillService {
    String save(BillRequest billRequest);

    String saveByStaff(BillRequest billRequest);

    String updateBillPreOrder(Long id);

    String updateBillPendingPayment(Long id);

    String delete(Long id);


    Bill findById(Long id);

    Bill findBySlug(String var);

    Page<Bill> findAllPaginated(int pageNo);
}
