package com.iuh.rencar_project.service.template;

import com.iuh.rencar_project.entity.Bill;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/28/2021 5:03 PM
 */
public interface IEmailService {

    Boolean preserve(Bill bill);

    Boolean sendBillEmailByStaff(Bill bill);
}
