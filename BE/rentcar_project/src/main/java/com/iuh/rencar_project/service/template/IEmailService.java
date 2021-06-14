package com.iuh.rencar_project.service.template;

import com.iuh.rencar_project.entity.Bill;
import com.iuh.rencar_project.entity.Reservation;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/28/2021 5:03 PM
 */
public interface IEmailService {

    Boolean reservation(Reservation reservation);

    Boolean sendBillEmailByStaff(Bill bill);
}
