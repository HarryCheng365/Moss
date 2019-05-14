package whu.software.moss.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import whu.software.moss.Service.CorpusUtil;

@RestController
@CrossOrigin
@RequestMapping("/corpus")
public class CorpusController {
    @Autowired
    CorpusUtil corpusUtil;


    private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);


    @RequestMapping(value = "/create",method= RequestMethod.POST)
    public String handleCorpus(@RequestParam("filepath") String filepath){
        return corpusUtil.initialize(filepath);
    }
}
