package whu.software.moss.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whu.software.moss.Config.RedisUtil;
import whu.software.moss.Dao.MemorandumRepository;
import whu.software.moss.Entity.Memorandum;
import whu.software.moss.Entity.UserInfo;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MemorandumService {
    private static final Logger LOG = LoggerFactory.getLogger(MemorandumService.class);
    @Autowired
    MemorandumRepository memorandumRepository;

    @Autowired
    RedisUtil redisUtil;

    public String createMemorandum(String headline, String content, UserInfo userInfo){
        Memorandum memorandum = new Memorandum(headline,content,userInfo);
        try{
           memorandum= memorandumRepository.save(memorandum);
            LOG.info(redisUtil.get(getRedisKey(memorandum),0));
            redisUtil.rpush(getRedisKey(memorandum), getRedisValue(memorandum));

        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
        LOG.info(JSON.toJSONString(memorandum));
        return JSON.toJSONString(memorandum);


    }

    public Memorandum getMemorandum(String key){
        Memorandum memorandum = null;
        JSONObject jsonObject=JSONObject.parseObject(key);
        try{
            int temp=jsonObject.getInteger("memorandum");
            memorandum=memorandumRepository.findById(temp);
        }catch (Exception e){
            LOG.error(e.getMessage());

        }
        return memorandum;

    }

    public Memorandum updateMemorandum(Integer id,String headline,String content){
        Memorandum memorandum=null;
        try{
            int tempInt=id;
            memorandum =memorandumRepository.findById(tempInt);
            LOG.info("找到了"+memorandum.getId()+memorandum.getHeadline()+memorandum.getUserInfo().getUsername());
            memorandum.setHeadline(headline);
            memorandum.setContent(content);
            Date temp = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            memorandum.setDate(sdf.format(temp));
            memorandum=memorandumRepository.save(memorandum);
            redisUtil.lrem(getRedisKey(memorandum),1,getRedisValue(memorandum));
            redisUtil.rpush(getRedisKey(memorandum),getRedisValue(memorandum));

        }catch (Exception e){
            LOG.error(e.getMessage());
        }
        return memorandum;


    }

    public String deleteMemorandum(Integer id){
        Memorandum memorandum=null;
        try{

            int temp=id;
            memorandum = memorandumRepository.findById(temp);
           memorandumRepository.deleteById(id);
           LOG.info("成功删除: "+memorandum.getUserInfo().getUsername()+memorandum.getId());
           redisUtil.lrem(getRedisKey(memorandum),1,getRedisValue(memorandum));
        }
        catch (Exception e){
            LOG.error(e.getMessage());
        }
        return JSON.toJSONString(memorandum);
    }


    private String getRedisKey(Memorandum memorandum){
        return "userLog: "+memorandum.getUserInfo().getUsername();

    }
    private String getRedisValue(Memorandum memorandum){
        return JSON.toJSONString("\"memorandum\":"+memorandum.getId());
    }







}
