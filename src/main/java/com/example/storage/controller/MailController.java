package com.example.storage.controller;

import com.example.storage.model.dto.MailDto;
import com.example.storage.service.MailService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import static com.example.storage.constants.MailConstants.*;

@Controller
@RequestMapping("/contact_form")
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping
    public ModelAndView showContactForm() {
        ModelAndView modelAndView = new ModelAndView("contact");
        modelAndView.addObject("mailDto", new MailDto());
        System.out.println("GET HERE");
        return modelAndView;
    }

    @PostMapping("/submit")
    public ModelAndView submitContactForm(@Valid MailDto mailDto) throws IOException {
        System.out.println("POST HERE");
        ModelAndView modelAndView = new ModelAndView("contact");
        modelAndView.addObject("message", "Thanks for message!");

        mailService.sendEmail(mailDto.getEmail(),
                mailService.buildMsgForUser(mailDto.getFirstName(), mailDto.getLastName()), EMAIL_SUBJECT);
        mailService.sendEmail(ADMIN_EMAIL, mailService.buildMsgForAdmin(mailDto.getFirstName(),
                mailDto.getLastName(), mailDto.getEmail(), mailDto.getMessage()), "from_server");

        return modelAndView;
    }


}
