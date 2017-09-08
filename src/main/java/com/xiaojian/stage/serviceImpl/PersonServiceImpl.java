package com.xiaojian.stage.serviceImpl;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.xiaojian.stage.entity.Person;
import com.xiaojian.stage.service.IPersonService;
import com.xiaojian.stage.util.HttpUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by xiaojian on 2017/8/29.
 */
@Service
public class PersonServiceImpl implements IPersonService {

    private static Logger logger = Logger.getLogger(PersonServiceImpl.class);
    @Override
    public Person getPerson(String data) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"id\":\"").append(data).append("\"}");
        String responseData = HttpUtil.getData(sb.toString(),"personUrl","GET");
        logger.info("response-----------"+responseData);
        Person person = null;
        try{
           person = JSONObject.parseObject(responseData,Person.class);
        }catch (JSONException e){
            e.printStackTrace();
        }

        return person;
    }
}
