package org.glamgaze.customer.controller;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;
import org.glamgaze.library.dto.CustomerDto;
import org.glamgaze.library.model.Customer;
import org.glamgaze.library.service.CustomerService;
import org.glamgaze.library.service.WalletService;
import org.glamgaze.library.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class ReferalController {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    CustomerService customerService;
    @Autowired
    WalletService walletService;
    @PostMapping("/sendReferal")
    public String processReferalCode(HttpServletRequest request, Model model,Principal principal) throws MessagingException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        String token = RandomString.make(5);
        String username=principal.getName();
        customerService.updateReferalCodeToken(token, username);
        String referalLink = Utility.getSiteURL(request) + "/referal_link?token=" + token;
        sendEmail(email, referalLink);
        model.addAttribute("message", "We have sent a referal link to  email. Please check.");
        return "redirect:/dashboard";
    }
    @GetMapping("/referal_link")
    public String showReferalLogin(@Param(value = "token") String token, Model model) {
        CustomerDto customerDto=new CustomerDto();
        model.addAttribute("token", token);
        model.addAttribute("users",customerDto);
        return "Referral-register";
    }
    @PostMapping("/registration1")
    public String showRegisterReferalUser(@ModelAttribute("users") CustomerDto customerDto,
                                          HttpServletRequest request) {
        String token = request.getParameter("token");

        Optional<List<Customer>> optionalCustomerList = customerService.getByReferalToken(token);

        if (optionalCustomerList.isPresent() && !optionalCustomerList.get().isEmpty()) {
            List<Customer> customerList = optionalCustomerList.get();
            Customer customer = customerList.get(0);
            Customer customerByEmail = customerService.findByEmail(customerDto.getEmail());
            if (customerByEmail != null) {
                return "redirect:/referal_link?exist&token=" + token;
            }
            walletService.addWalletToReferalEarn(customer.getId());
            customerService.save(customerDto);


            return "redirect:/referal_link?success";
        } else {

            return "redirect:/referal_link?customerNotFound";
        }
    }
    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("reachfaizal08@gmail.com", "GlamGaze");
        helper.setTo(recipientEmail);
        String subject = "Here's the link to login GlamGaze";
        String content = "<p>Hello,</p>"
                + "<p>You have requested to Login, GlamGaze.</p>"
                + "<p>Click the link below to log-in and signup page:</p>"
                + "<p><a href=\"" + link + "\">GlamGaze</a></p>"
                + "<br>"
                + "<p>Ignore this email if you already have account, "
                + "or you have not made the request.</p>";
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }
}

