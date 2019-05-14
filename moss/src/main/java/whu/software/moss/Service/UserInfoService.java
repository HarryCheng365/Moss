package whu.software.moss.Service;

import com.alibaba.fastjson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import whu.software.moss.Dao.UserInfoRepository;

import whu.software.moss.Entity.Sex;
import whu.software.moss.Entity.UserInfo;

import javax.annotation.Resource;

@Service
public class UserInfoService {
    private static final Logger LOG = LoggerFactory.getLogger(AccountService.class);
    @Resource
    UserInfoRepository userInfoRepository;

    public String initialUserInfo(String username){
        UserInfo userInfo=new UserInfo(username, Sex.DEFAULT,"",username,"","","");
        try{
            userInfoRepository.save(userInfo);
            LOG.info("用户初始化成功"+userInfo.getUsername());
        }catch (Exception e){
            LOG.debug(e.getMessage());
        }
        return "success";

    }

    public String setUserInfo(JSONObject jsonObject){
        UserInfo userInfo=null;
        try{
           userInfo= userInfoRepository.findByUsername(jsonObject.getString("username"));
            if(userInfo!=null){
                userInfo.setNickname(jsonObject.getString("nickname"));
                userInfo.setDevice(jsonObject.getString("logindevice"));
                userInfo.setEmail(jsonObject.getString("email"));
                userInfo.setPhone(jsonObject.getString("phone"));
                userInfo.setSex(sexConvert(jsonObject.getString("sex")));
                userInfoRepository.save(userInfo);
                LOG.info("更改用户信息成功："+userInfo.getUsername());
                return JSON.toJSONString(userInfo);
            }

        }catch (Exception e){
            LOG.debug(e.getMessage());
        }
        return "failed";


    }
    public Sex sexConvert(String sex){
        if(sex.equals("man")){
            return Sex.MAN;
        }
        else if(sex.equals("woman")){
            return Sex.WOMAN;
        }
        return Sex.DEFAULT;



    }


}
