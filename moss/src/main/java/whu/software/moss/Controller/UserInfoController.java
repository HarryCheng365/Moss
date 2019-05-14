package whu.software.moss.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import whu.software.moss.Dao.UserInfoRepository;
import whu.software.moss.Service.UserInfoService;

import javax.annotation.Resource;

@Controller
@CrossOrigin
@RequestMapping("/userinfo")
public class UserInfoController {
    @Resource
    UserInfoService userInfoService;

    @RequestMapping(value="/modify", method = RequestMethod.POST)
    public String addUserInfo(@RequestParam String string){

        return userInfoService.setUserInfo(JSON.parseObject(string));
    }


}
