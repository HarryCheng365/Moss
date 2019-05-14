package whu.software.moss.Service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.util.concurrent.RejectedExecutionHandlers;
import org.apache.catalina.core.ApplicationContext;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import whu.software.moss.Dao.CorpusRepository;
import whu.software.moss.Dao.CorpusUserRepository;
import whu.software.moss.Entity.Account;
import whu.software.moss.Entity.AccountType;
import whu.software.moss.Entity.Corpus;
import whu.software.moss.Entity.CorpusUser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


@Component
public class CorpusUtil {
    private static final Logger LOG = LoggerFactory.getLogger(CorpusUtil.class);
    @Autowired
    CorpusRepository corpusRepository;
    @Autowired
    CorpusUserService corpusUserService;

    public String initialize(String filename) {
        ArrayList<String> list = new ArrayList<>();

        try {


            File file = new File("/Users/Haoyu/Desktop/moss/src/main/resources/static/corpus.txt");
            FileReader fr = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fr);
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                list.add(str);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
            return "failed";

        }
        return extract(list);


    }

    public String extract(ArrayList<String> list) {

        for (String temp : list) {
            CorpusUser corpusUser=null;
            LOG.info("当前要处理的是"+temp);
            String[] strs = temp.split(",");
            try {
                corpusUser = corpusUserService.saveCorpusUser(strs[0], strs[0], "");
            } catch (Exception e) {
                LOG.error(e.getMessage());
                return "failed";
            }
            if (corpusUser != null) {
                corpusRepository.save(new Corpus(strs[1], strs[2], strs[3], strs[4],corpusUser));
            }
        }
        LOG.info("新导入Corpus保存成功");
        return "success";

    }
}







