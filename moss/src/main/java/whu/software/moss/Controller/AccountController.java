package whu.software.moss.Controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import whu.software.moss.Dao.AccountRepository;
import whu.software.moss.Entity.Account;

import whu.software.moss.Entity.AccountType;
import whu.software.moss.Service.AccountService;
import whu.software.moss.Service.UserInfoService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;
    @Resource
    private AccountService accountService;
    @Resource
    private UserInfoService userInfoService;

    private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

    @RequestMapping(value="/",method= RequestMethod.GET)
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @RequestMapping(value="/login",method= RequestMethod.POST)
    public String login(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("type") String type){

      return accountService.verifyLogin(new Account(username,accountService.accountTypeCovert(type),password));
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String register(@RequestParam("username") String username,@RequestParam("password") String password){

        String key=accountService.register(new Account(username, AccountType.USER,password));
        if(key.equals("success")){
            userInfoService.initialUserInfo(username);
        }
        return key;


    }



}



