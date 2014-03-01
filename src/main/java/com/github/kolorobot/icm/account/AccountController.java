package com.github.kolorobot.icm.account;

import com.github.kolorobot.icm.support.web.MessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("account")
@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
class AccountController {

    private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountRepository userRepository;

    @Autowired
    private UserService userService;

    @ModelAttribute("page")
    public String module() {
        return "account";
    }

    @RequestMapping(value = "current", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Account account(UserDetails userDetails) {
        LOG.info(userDetails.toString());
        return userRepository.findByEmail(userDetails.getUsername());
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute(new AccountForm());
        return "account/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute AccountForm accountForm, Errors errors, RedirectAttributes ra, Model model) {
        if (errors.hasErrors()) {
            MessageHelper.addErrorAttribute(model, "account.create.failed");
            return "account/create";
        }
        Account employeeAccount = accountForm.createEmployeeAccount();
        userService.createAccount(employeeAccount);
        LOG.info("Created new employee account with id={}", employeeAccount.getId());
        MessageHelper.addSuccessAttribute(ra, "account.create.success");
        return "redirect:/account/list";
    }

    @RequestMapping("/list")
    public String list(@RequestParam(required = false) String role, Model model) {
        if (!isRoleValid(role)) {
            throw new IllegalArgumentException("Invalid role");
        }
        model.addAttribute("accounts", getAccounts(role));
        return "account/list";
    }

    private List<Account> getAccounts(String role) {
        if (StringUtils.isEmpty(role)) {
            return userRepository.findAll();
        }
        return userRepository.findAllByRole(role);
    }

    private boolean isRoleValid(String role) {
        if (StringUtils.isEmpty(role)) {
            return true;
        }
        if (Account.ROLE_USER.equals(role) || Account.ROLE_EMPLOYEE.equals(role)) {
            return true;
        }
        return false;
    }

    @RequestMapping("/{accountId}/delete")
    public String delete(@PathVariable Long accountId, Model model, RedirectAttributes ra) {
        Account account = userRepository.findOne(accountId);
        userRepository.delete(account.getId());
        MessageHelper.addSuccessAttribute(ra, "account.delete.success");
        return "redirect:/account/list";
    }
}
