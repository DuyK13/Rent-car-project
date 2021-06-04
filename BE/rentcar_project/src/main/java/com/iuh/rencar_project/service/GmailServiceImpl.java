package com.iuh.rencar_project.service;

import com.iuh.rencar_project.entity.Bill;
import com.iuh.rencar_project.entity.Car;
import com.iuh.rencar_project.service.template.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/28/2021 5:00 PM
 */
@Service
public class GmailServiceImpl implements IEmailService {

    private static final Logger logger = LoggerFactory.getLogger(GmailServiceImpl.class);

    private final String URL = "http://localhost:8081";

    private final String PATH = "/api/guest/bills/";

    private final JavaMailSender mailSender;

    @Autowired
    public GmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public Boolean sendBillEmailByStaff(Bill bill) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh giờ mm phút");
        Long id = bill.getId();
        Long billAmount = bill.getBillAmount();
        Long lateCharge = bill.getLateCharge();
        Car car = bill.getCar();
        Long rentTime = bill.getRentTime();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            String htmlMsg = "<div>\n" +
                    "                           <div style=\"width: 750px\">\n" +
                    "                             <img src='https://tran-the-duy-208.s3-ap-southeast-1.amazonaws.com/logo.png' width='250' height='250'\n" +
                    "                              style='display: block; margin-left: auto; margin-right: auto;'>\n" +
                    "                           </div>\n" +
                    "                           <p>Thân mến " + bill.getFullname() + ",</p>\n" +
                    "                       <p>Cám ơn vì đã sử dụng dịch vụ của chúng tôi.</p> \n" +
                    "                           <p>Đây là thông tin hoá đơn thuê xe của bạn</p>\n" +
                    "                           <p><span>ĐƠN HÀNG: #" + bill.getId() + "</span></p>\n" +
                    "                              <caption>CHI TIẾT ĐƠN HÀNG</caption>\n" +
                    "                            <table style=\"border: 1px solid black;width: 50%; border-collapse: collapse; text-align: center\">\n" +
                    "                              <thead>\n" +
                    "                                <tr>\n" +
//                    "                                  <th style=\"border: 1px solid black;padding: 8px;\">Loại</th>\n" +
                    "                                  <th style=\"border: 1px solid black;padding: 8px;\">Ngày giờ thuê</th>\n" +
                    "                                  <th style=\"border: 1px solid black;padding: 8px;\">Số giờ thuê</th>\n" +
                    "                                  <th style=\"border: 1px solid black;padding: 8px;\">Ngày giờ trả</th>\n" +
                    "                                  <th style=\"border: 1px solid black;padding: 8px;\">Xe</th>\n" +
                    "                                  <th style=\"border: 1px solid black;padding: 8px;\">Biển số</th>\n" +
                    "                                  <th style=\"border: 1px solid black;padding: 8px;\">Phí thuê<sup>*</sup></th>\n" +
                    "                                  <th style=\"border: 1px solid black;padding: 8px;\">Thành tiền</th>\n" +
                    "                                  <th style=\"border: 1px solid black;padding: 8px;\">Phí trễ hạn<sup>**</sup></th>\n" +
                    "                                  <th style=\"border: 1px solid black;padding: 8px;\">Tổng tiền</th> \n" +
                    "                                </tr>\n" +
                    "                              </thead>\n" +
                    "                              <tbody>\n" +
                    "                                <tr>\n" +
//                    "                                  <td style=\"border: 1px solid black;padding: 8px;\">type</td>\n" +
                    "                                  <td style=\"border: 1px solid black;padding: 8px;\">" + df.format(bill.getStartTime()) + "</td>\n" +
                    "                                  <td style=\"border: 1px solid black;padding: 8px;\">" + rentTime + "</td>\n" +
                    "                                  <td style=\"border: 1px solid black;padding: 8px;\">" + df.format(bill.getStartTime().plusHours(rentTime)) + "</td>\n" +
                    "                                  <td style=\"border: 1px solid black;padding: 8px;\">" + car.getName() + " " + bill.getCar().getManufacturingYear() + "</td>\n" +
                    "                                  <td style=\"border: 1px solid black;padding: 8px;\">" + car.getLicensePlate() + "</td>\n" +
                    "                                  <td style=\"border: 1px solid black;padding: 8px;\">" + car.getCostPerHour() + "</td>\n" +
                    "                                  <td style=\"border: 1px solid black;padding: 8px;\">" + billAmount + "</td>\n" +
                    "                                  <td style=\"border: 1px solid black;padding: 8px;\">" + lateCharge + "</td>\n" +
                    "                                  <td style=\"border: 1px solid black;padding: 8px;\">" + (billAmount + lateCharge) + "</td> \n" +
                    "                                </tr>\n" +
                    "                              </tbody>\n" +
                    "                            </table>\n" +
                    "                            <br/>\n" +
                    "                           <p>Nếu bạn có bất cứ câu hỏi nào, đừng ngần ngại liên lạc với chúng tôi tại: <b>thuexevynguyen@gmail.com</b></p>\n" +
                    "                       </div>" +
                    "                           <p><sup>*</sup>: tính theo giờ</p>\n" +
                    "                           <p><sup>**</sup>: cứ 30 phút trễ sẽ tăng phí trễ hạn</p>";
            message.setContent(htmlMsg, "text/html; charset=UTF-8");
            helper.setTo(bill.getEmail());
            helper.setSubject("[Thuê Xe Vỹ Nguyên] - Đơn đặt trước #" + id);
        } catch (MessagingException e) {
            logger.error("Email Exception: ", e);
            return false;
        }
        this.mailSender.send(message);
        return true;
    }

    @Override
    public Boolean sendBillEmailByGuest(Bill bill) {
        Long id = bill.getId();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            String htmlMsg = "<div>\n" +
                    "                           <div style=\"width: 750px\">\n" +
                    "                             <img src='https://tran-the-duy-208.s3-ap-southeast-1.amazonaws.com/logo.png' width='250' height='250'\n" +
                    "                              style='display: block; margin-left: auto; margin-right: auto;'>\n" +
                    "                           </div>\n" +
                    "        <p>Thân mến " + bill.getFullname() + ",</p>" +
                    "        <p>Bạn đã đặt lịch hẹn thành công,</p>" +
                    "        <p>Cám ơn vì đã sử dụng dịch vụ của chúng tôi.</p>" +
                    "        <p>Nếu bạn có bất cứ câu hỏi nào, đừng ngần ngại liên lạc với chúng tôi tại: <b>thuexevynguyen@gmail.com</b></p>" +
                    "    </div>";
            message.setContent(htmlMsg, "text/html; charset=UTF-8");
            helper.setTo(bill.getEmail());
            helper.setSubject("[Thuê Xe Vỹ Nguyên] - Thông báo đặt trước thành công");
        } catch (MessagingException e) {
            logger.error("Email Exception: ", e);
            return false;
        }
        this.mailSender.send(message);
        return true;
    }
}
