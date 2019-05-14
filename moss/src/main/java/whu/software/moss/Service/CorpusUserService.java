package whu.software.moss.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whu.software.moss.Config.RedisUtil;
import whu.software.moss.Dao.CorpusUserRepository;
import whu.software.moss.Entity.CorpusUser;

@Service
public class CorpusUserService {
    private static final Logger LOG = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    CorpusUserRepository corpusUserRepository;
    @Autowired
    RedisUtil redisUtil;

    public CorpusUser saveCorpusUser(String id, String name, String iconurl){
        CorpusUser corpusUser = null;
        JSONObject value=null;


       value= JSON.parseObject(redisUtil.get("corpusUser: "+id,1));

       if(value!=null){
           return new CorpusUser(value.getString("id"),value.getString("name"),value.getString("iconurl"));
       }
        try{
            if(corpusUserRepository.existsById(id)){
                corpusUser=corpusUserRepository.findByCorpusId(id);
                LOG.info(id+"对象已经存在");
            }
            else{
                corpusUser=corpusUserRepository.save(new CorpusUser(id,name,iconurl));
            }
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id",id);
            jsonObject.put("name",name);
            jsonObject.put("iconurl",iconurl);

            redisUtil.set("corpusUser: "+id,jsonObject.toJSONString(),1);
            redisUtil.expire("corpusUser: "+id,1000,0);
            return corpusUser;

        }catch (Exception e){

            LOG.error(e.getMessage());
        }
        LOG.info(corpusUser.getCorpusId()+"新建成功");
        return corpusUser;

    }

}
