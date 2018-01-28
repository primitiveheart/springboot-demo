package com.springboot.core.util;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by admin on 2018/1/17.
 */
public class ToolUtil {
    /**
     * 判断对象是否为空
     * @param o
     * @return
     */
    public static boolean isEmpty(Object o){
        if(o == null){
            return true;
        }
        if(o instanceof String){
            if(o.toString().trim().equals("")){
                return true;
            }
        }else if(o instanceof List){
            if(((List) o).size() == 0){
                return true;
            }
        }else if(o instanceof Map){
            if(((Map) o).size() == 0){
                return true;
            }
        }else if(o instanceof Set){
            if(((Set) o).size() == 0){
                return true;
            }
        }else if(o instanceof Object[]){
            if(((Object[]) o).length == 0){
                return true;
            }
        }else if(o instanceof int[]){
            if(((int[]) o).length == 0){
                return true;
            }
        }else if(o instanceof long[]){
            if(((long[]) o).length == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * 得到一个临时目录,是获取操作系统的缓存临时目录
     * @return
     */
    public static String getTempPath(){
        return System.getProperty("java.io.tmpdir");
    }
}
