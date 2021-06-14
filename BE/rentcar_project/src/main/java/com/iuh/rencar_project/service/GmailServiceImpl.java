package com.iuh.rencar_project.service;

import com.iuh.rencar_project.entity.Bill;
import com.iuh.rencar_project.entity.Car;
import com.iuh.rencar_project.entity.Reservation;
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
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Locale;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/28/2021 5:00 PM
 */
@Service
public class GmailServiceImpl implements IEmailService {

    private static final Logger logger = LoggerFactory.getLogger(GmailServiceImpl.class);

    private final JavaMailSender mailSender;

    @Autowired
    public GmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public Boolean reservation(Reservation reservation) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm");
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            String htmlMsg = "<div>\n" +
                    "                           <div style=\"width: 750px\">\n" +
                    "                             <img src='https://tran-the-duy-208.s3-ap-southeast-1.amazonaws.com/logo.png' width='250' height='250'\n" +
                    "                              style='display: block; margin-left: auto; margin-right: auto;'>\n" +
                    "                           </div>\n" +
                    "        <p>Thân mến " + reservation.getFullName() + ",</p>" +
                    "        <p>Bạn đã gửi đơn đặt trước vào lúc " + formatter.format(reservation.getPickupDate()) + ". Vui lòng đợi nhân viên của chúng tôi gọi điện xác nhận</p>" +
                    "        <p>=====================================================================================</p>" +
                    "        <p>Cám ơn vì đã sử dụng dịch vụ của chúng tôi.</p>" +
                    "        <p>Nếu bạn có bất cứ câu hỏi nào, đừng ngần ngại liên lạc với chúng tôi tại: <b>thuexevynguyen@gmail.com</b></p>" +
                    "    </div>";
            message.setContent(htmlMsg, "text/html; charset=UTF-8");
            helper.setTo(reservation.getEmail());
            helper.setSubject("[Thuê Xe Vỹ Nguyên] - Thông báo đặt trước thành công");
        } catch (MessagingException e) {
            logger.error("Email Exception: ", e);
            return false;
        }
        this.mailSender.send(message);
        return true;
    }

    @Override
    public Boolean sendBillEmailByStaff(Bill bill) {
        Locale vn = new Locale("vi", "VN");
        Locale.setDefault(vn);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm");
        Currency vnd = Currency.getInstance(vn);
        NumberFormat format = NumberFormat.getCurrencyInstance(vn);
        Long id = bill.getId();
        Long billAmount = bill.getBillAmount();
        Long charges = bill.getCharges();
        String note = bill.getNote();
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
                    "                              <caption style=\"width: 100%\">CHI TIẾT ĐƠN HÀNG</caption>\n" +
                    "                            <table style=\"border: 1px solid black;width: 100%; border-collapse: collapse; text-align: center\">\n" +
                    "                              <thead>\n" +
                    "                                <tr>\n" +
//                    "                                  <th style=\"border: 1px solid black;padding: 8px;\">Loại</th>\n" +
                    "                                  <th style=\"border: 1px solid black;padding: 8px;\">Ngày giờ thuê</th>\n" +
                    "                                  <th style=\"border: 1px solid black;padding: 8px;\">Số giờ thuê</th>\n" +
                    "                                  <th style=\"border: 1px solid black;padding: 8px;\">Ngày giờ trả</th>\n" +
                    "                                  <th style=\"border: 1px solid black;padding: 8px;\">Xe</th>\n" +
                    "                                  <th style=\"border: 1px solid black;padding: 8px;\">Biển số</th>\n" +
                    "                                  <th style=\"border: 1px solid black;padding: 8px;\">Phí thuê</th>\n" +
                    "                                  <th style=\"border: 1px solid black;padding: 8px;\">Thành tiền</th>\n" +
                    "                                  <th style=\"border: 1px solid black;padding: 8px;\">Phí phát sinh<sup>*</sup></th>\n" +
                    "                                  <th style=\"border: 1px solid black;padding: 8px;\">Ghi chú</th>\n" +
                    "                                  <th style=\"border: 1px solid black;padding: 8px;\">Tổng tiền</th> \n" +
                    "                                </tr>\n" +
                    "                              </thead>\n" +
                    "                              <tbody>\n" +
                    "                                <tr>\n" +
//                    "                                  <td style=\"border: 1px solid black;padding: 8px;\">type</td>\n" +
                    "                                  <td style=\"border: 1px solid black;padding: 8px;\">" + formatter.format(bill.getStartTime()) + "</td>\n" +
                    "                                  <td style=\"border: 1px solid black;padding: 8px;\">" + rentTime + "</td>\n" +
                    "                                  <td style=\"border: 1px solid black;padding: 8px;\">" + formatter.format(bill.getStartTime().plusHours(rentTime)) + "</td>\n" +
                    "                                  <td style=\"border: 1px solid black;padding: 8px;\">" + car.getName() + " " + bill.getCar().getManufacturingYear() + "</td>\n" +
                    "                                  <td style=\"border: 1px solid black;padding: 8px;\">" + car.getLicensePlate() + "</td>\n" +
                    "                                  <td style=\"border: 1px solid black;padding: 8px;\">" + format.format(car.getCostPerHour()) + "/giờ</td>\n" +
                    "                                  <td style=\"border: 1px solid black;padding: 8px;\">" + format.format(billAmount) + "</td>\n" +
                    "                                  <td style=\"border: 1px solid black;padding: 8px;\">" + format.format(charges) + "</td>\n" +
                    "                                  <td style=\"border: 1px solid black;padding: 8px;\">" + note + "</td>\n" +
                    "                                  <td style=\"border: 1px solid black;padding: 8px;\">" + format.format(billAmount + charges) + "</td> \n" +
                    "                                </tr>\n" +
                    "                              </tbody>\n" +
                    "                            </table>\n" +
                    "                            <br/>\n" +
                    "                           <p>Nếu bạn có bất cứ câu hỏi nào, đừng ngần ngại liên lạc với chúng tôi tại: <b>thuexevynguyen@gmail.com</b></p>\n" +
                    "                       </div>" +
                    "                           <p><h3>Lưu ý:</h3>\n" +
                    "                           <p>&emsp;<b><sup>*</sup></b>: chi tiết phí phát sinh nằm ở phần ghi chú</p>";
            message.setContent(htmlMsg, "text/html; charset=UTF-8");
            helper.setTo(bill.getEmail());
            helper.setSubject("[Thuê Xe Vỹ Nguyên] - Đơn #" + id);
        } catch (MessagingException e) {
            logger.error("Email Exception: ", e);
            return false;
        }
        this.mailSender.send(message);
        return true;
    }

}
