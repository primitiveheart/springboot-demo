package com.springboot.core.base.tips;

/**
 * Created by admin on 2018/1/16.
 */
public class SuccessTip extends Tip{
    public SuccessTip(){
        super.code = 200;
        super.message = "操作成功";
    }
}
