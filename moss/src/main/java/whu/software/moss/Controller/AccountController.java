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
import whu.software.moss.Service.AccountService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;
    @Resource
    private AccountService birthdayService;

    private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

    @RequestMapping(value="/",method= RequestMethod.GET)
    public ModelAndView index(){
        return new ModelAndView("index");
    }

//    @RequestMapping(value="/",method= RequestMethod.POST)
//    public ModelAndView index2(String string){
//        birthdayService.handleMessage(string);
//        return new ModelAndView("index");
//    }



}



