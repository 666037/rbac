package com.kad.rbac;

import com.kad.rbac.model.common.TreeVO;
import com.kad.rbac.model.entity.po.BaseDO;
import com.kad.rbac.model.entity.po.CompanyDO;
import com.kad.rbac.util.JsonHelper;
import com.kad.rbac.util.DateHelper;
import com.kad.rbac.util.TreeHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class RbacApplicationTests {

    @Test
    void contextLoads() throws IOException, ClassNotFoundException {
        TreeHelper treeHelper=new TreeHelper();
        List<TreeVO>list=new ArrayList<TreeVO>() ;
        TreeVO t1=new TreeVO();
        TreeVO t2=new TreeVO();
        TreeVO t3=new TreeVO();
        TreeVO t4=new TreeVO();
        TreeVO t5=new TreeVO();
        TreeVO t6=new TreeVO();
        t1.id=1;
        t1.pid=0;
        t2.id=2;
        t2.pid=1;
        t3.id=3;
        t3.pid=2;
        t4.id=4;
        t4.pid=1;
        t5.pid=0;
        t5.id=5;
        t6.pid=5;
        t6.id=6;
        list.add(t1); list.add(t2); list.add(t3); list.add(t4); list.add(t5); list.add(t6);
//        List<TreeVO>list2=new ArrayList<TreeVO>() ;
//        list2.add(t1);
//        list2.add(t5);
        System.out.println(JsonHelper.serialize(treeHelper.ConvertToTree(list)));
    }
    @Test
    public void encoder() throws ParseException {
//        String password = "xuanbu";
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
//        String enPassword = encoder.encode(password);
       // SqlGenerateFactory.getFiledName(new CompanyDO());
       // CompanyDO companyDO= new CompanyDO(){{ setCmpId(1);setCmpName("111");setCmpName("2222");}};
        System.out.println(DateHelper.DateFormat(new Date()));

     //  System.out.println(JsonHelper.serialize(new BaseDO()));
    }

}
