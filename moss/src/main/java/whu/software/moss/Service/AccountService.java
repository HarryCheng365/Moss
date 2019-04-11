package whu.software.moss.Service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import whu.software.moss.Dao.AccountRepository;
import whu.software.moss.Entity.Account;

import javax.annotation.Resource;

@Service
public class AccountService {
    @Resource
    private AccountRepository accountRepository;
    private String suffix = "";

    public String handleMessage(String string){
        JSONObject jsonObject=JSONObject.parseObject(string);

        Account entity = new Account(jsonObject.getString("message"),jsonObject.getString("name"));
        Account entity1= accountRepository.save(entity);
        return "success";


    }

}

