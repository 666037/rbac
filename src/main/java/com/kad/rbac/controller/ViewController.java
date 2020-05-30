
package com.kad.rbac.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
public class ViewController {
    private final static String ERROR_PATH = "/error";
    @RequestMapping("view/{url}.html")
    public String prekit(@PathVariable("url") String url){
        return "modules/prekit/" + url;
    }
    @RequestMapping("view/{module}/{url}.html")
    public String prekit(@PathVariable("module") String module,@PathVariable("url") String url){
        return "modules/prekit/"+module+"/" + url;
    }

    @RequestMapping("extend/{url}.html")
    public String extend(@PathVariable("url") String url){
        return "modules/extend/" + url;
    }

    @RequestMapping("extend/{module}/{url}.html")
    public String extend(@PathVariable("module") String module,@PathVariable("url") String url){
        return "modules/extend/"+module+"/" + url;
    }

    @RequestMapping("error/{url}.html")
    public String error(@PathVariable("url") String url){
        return "common/error/" + url;
    }

//    @RequestMapping(value = ERROR_PATH, produces = "text/html")
//    public String sysError(){
//        return "common/error/400.html" ;
//    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("login1")
    public String login1(){
        return "login1";
    }

    @GetMapping({"/", "index"})
    public String index(){
        return "index";
    }
    @GetMapping(value = "/test")
    public String test(){
        Object j=null;
        j.toString();
        return "rbac/index";
    }
    @GetMapping(value = "/home")
    public String home() {
        return "home";
    }

    @RequestMapping("404")
    public String notFound(){
        return "404";
    }
    @RequestMapping("403")
    public String forbidden(){
        return "403";
    }
}
