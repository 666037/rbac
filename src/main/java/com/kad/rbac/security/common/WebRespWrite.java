package com.kad.rbac.security.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kad.rbac.security.util.JsonHelper;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 写入json格式处理结果
 */
public class WebRespWrite {


    public static void writeToWeb(HttpServletResponse response, RespBean respBean) throws IOException {
        response.setContentType("application/json;charset=utf-8");

        ObjectMapper om = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.write(om.writeValueAsString(respBean));
        out.flush();
        out.close();

    }
}
