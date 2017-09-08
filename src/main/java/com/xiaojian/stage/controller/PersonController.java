package com.xiaojian.stage.controller;

import com.xiaojian.stage.entity.Person;
import com.xiaojian.stage.service.IPersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Created by xiaojian on 2017/9/5.
 */
@Controller
public class PersonController {
    @Resource
    private IPersonService personService;
    @RequestMapping(value = "/me/person",produces = "text/html;charset=UTF-8",method = RequestMethod.GET)
    public String getPerson(@RequestParam("value") String data, Model model){
        Person person = personService.getPerson(data);
        if (person!=null){
            model.addAttribute("p",person);
            return "index";
        }else{
            return "error";
        }

    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
