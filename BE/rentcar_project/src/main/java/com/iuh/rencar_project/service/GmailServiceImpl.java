package com.iuh.rencar_project.service;

import com.iuh.rencar_project.entity.Bill;
import com.iuh.rencar_project.service.template.IEmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/28/2021 5:00 PM
 */
@Service
public class GmailServiceImpl implements IEmailService {

    private static final Logger logger = LogManager.getLogger(GmailServiceImpl.class);

    private final String URL = "http://localhost:8081";

    private final String PATH = "/api/guest/bills/";

    private final JavaMailSender mailSender;

    @Autowired
    public GmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public Boolean sendBillEmailByStaff(Bill bill) {
        Long id = bill.getId();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            String htmlMsg = "<div style=\"width: 500px;\">" +
                    "        <img src='https://tran-the-duy-208.s3-ap-southeast-1.amazonaws.com/logo.png' width='250' height='250'" +
                    "            style='display: block; margin-left: auto; margin-right: auto;'>" +
                    "        <div>Đơn : #" + id + "</div>" +
                    "        <p>Thân mến " + bill.getFullname() + ",</p>" +
                    "        <p>Cám ơn vì đã sử dụng dịch vụ của chúng tôi.</p>" +
                    "        <p>Đây là đường dẫn tới đơn đặt trước của bạn: <a href='" + URL + PATH + bill.getSlug() + "'>Bill #" + id + "</a></p>" +
                    "        <p>Nếu bạn có bất cứ câu hỏi nào, đừng ngần ngại liên lạc với chúng tôi tại: <b>thuexevynguyen@gmail.com</b></p>" +
                    "    </div>";
            message.setContent(htmlMsg, "text/html; charset=UTF-8");
            helper.setTo(bill.getEmail());
            helper.setSubject("[Thuê Xe Vỹ Nguyên] - Đơn đặt trước #" + id  );
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
            String htmlMsg = "<div style=\"width: 500px;\">" +
                    "        <img src='https://tran-the-duy-208.s3-ap-southeast-1.amazonaws.com/logo.png' width='250' height='250'" +
                    "            style='display: block; margin-left: auto; margin-right: auto;'>" +
                    "        <p>Thân mến " + bill.getFullname() + ",</p>" +
                    "        <p>Bạn đã đặt lịch hẹn thành công,</p>" +
                    "        <p>Cám ơn vì đã sử dụng dịch vụ của chúng tôi.</p>" +
                    "        <p>Nếu bạn có bất cứ câu hỏi nào, đừng ngần ngại liên lạc với chúng tôi tại: <b>thuexevynguyen@gmail.com</b></p>" +
                    "    </div>";
            message.setContent(htmlMsg, "text/html; charset=UTF-8");
            helper.setTo(bill.getEmail());
            helper.setSubject("[Thuê Xe Vỹ Nguyên] - Thông báo đặt lịch hẹn thành công");
        } catch (MessagingException e) {
            logger.error("Email Exception: ", e);
            return false;
        }
        this.mailSender.send(message);
        return true;
    }
}
