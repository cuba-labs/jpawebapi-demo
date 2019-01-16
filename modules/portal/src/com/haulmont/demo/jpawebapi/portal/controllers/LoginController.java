package com.haulmont.demo.jpawebapi.portal.controllers;

import com.haulmont.cuba.core.global.PasswordEncryption;
import com.haulmont.cuba.portal.service.PortalAuthenticationService;
import com.haulmont.demo.jpawebapi.portal.command.LoginUserCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;


@Controller
public class LoginController {

    @Inject
    protected PortalAuthenticationService portalAuthenticationService;

    @Inject
    protected PasswordEncryption passwordEncryption;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model) {
        final LoginUserCommand loginUserCommand = new LoginUserCommand();
        model.addAttribute(loginUserCommand);

        return "login";
    }
}