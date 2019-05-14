package whu.software.moss.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.catalina.core.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import whu.software.moss.Config.RedisUtil;
import whu.software.moss.Controller.AccountController;
import whu.software.moss.Dao.AccountRepository;
import whu.software.moss.Entity.Account;
import whu.software.moss.Entity.AccountType;

import javax.annotation.Resource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
@Service
public class AccountService {
    private static final Logger LOG = LoggerFactory.getLogger(AccountService.class);
    @Resource
    private AccountRepository accountRepository;
    @Autowired
    RedisUtil redisUtil;
    private String suffix = "";


    public String verifyLogin(Account account){

        Account aim= JSON.parseObject(redisUtil.get("user: " + account.getUsername(),0),Account.class);
        if(aim==null){
        try {

               aim=accountRepository.findByUsername(account.getUsername());

       }
       catch (Exception e){
           LOG.info(e.getMessage());

       }
        if(aim!=null&&aim.getPassword().equals(account.getPassword())){
            LOG.info(account.getUsername()+"login success");
            redisUtil.set("user: "+account.getUsername(),JSON.toJSONString(account),0);
            JSONObject jsonObject = new JSONObject();



            redisUtil.expire("user: "+account.getUsername(),1000,0);
            return "success";
        }
        else {
            return "failed";
        }
        }
        else{
            LOG.info(aim.getUsername()+" login success");
            return "success";
        }

    }


    public String register(Account account){
        String key="failed";
        if(key.equals(verifyLogin(account))){
            try {
                accountRepository.save(account);
            }
            catch (Exception e){
                LOG.info(e.getMessage());
            }
            redisUtil.set("user: "+account.getUsername(),JSON.toJSONString(account),0);
            redisUtil.lset("userLog: "+account.getUsername(),0,"");
            redisUtil.expire("user: "+account.getUsername(),1000,0);

            LOG.info("user: "+account.getUsername()+"注册成功");
            return "success";
        }
        LOG.info("user: "+account.getUsername()+"用户已经存在");
        return key;

    }
    public String updatePasswd(Account account,String newPasswd){
        String key="success";
        Account aim= null;
        try{
            aim=accountRepository.findByUsername(account.getUsername());

        }catch (Exception e){
            LOG.debug(e.getMessage());
        }
        if(aim.getPassword().equals(account.getPassword())){
            LOG.debug("user: "+account.getUsername()+" 密码更新成功");
            aim.setPassword(newPasswd);
            redisUtil.del("user: "+aim.getUsername());
        }
        else{

            return "failed";
        }
        return key;





    }

    public AccountType accountTypeCovert(String type){
        if(type.equals("admin")){
            return AccountType.ADMIN;
        }
        return AccountType.USER;


    }


}

