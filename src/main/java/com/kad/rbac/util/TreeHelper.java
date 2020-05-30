package com.kad.rbac.util;

import com.kad.rbac.model.common.TreeVO;

import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TreeHelper {



    public static Collection<?extends TreeVO>ConvertToTree(Collection<?extends TreeVO>OrigionData )
    {
        Collection<?extends TreeVO> rootTreeData=OrigionData.stream().filter(u->u.pid==0).collect(Collectors.toList());
       return  ChildToTree(OrigionData,rootTreeData);
    }
    public static Collection<?extends TreeVO>ChildToTree(Collection<?extends TreeVO>OrigionData,Collection<?extends TreeVO>ChildData)
    {

        for(TreeVO obj :ChildData){
            {
                Collection<?extends TreeVO>childData=OrigionData.stream().filter(u->u.pid==obj.id).collect(Collectors.toList());
                obj.children =ChildToTree(OrigionData,childData);
            }
        }

      return  ChildData;

    }

    /**
     * 深拷贝
     * @param src
     * @param <T>
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }

}
