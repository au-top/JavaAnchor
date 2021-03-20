package org.toroder.JavaAnchor.DataComponent;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;

/**
 * @author autopMateBook
 */
public class ResultPackage {
    static public String create(int code,Object data,String explain){
        final HashMap<String,Object>  newResObj=new HashMap<String,Object>();
        newResObj.put("code",code);
        newResObj.put("data",data);
        newResObj.put("explain",explain);
        return JSON.toJSONString(newResObj);
    }
}
