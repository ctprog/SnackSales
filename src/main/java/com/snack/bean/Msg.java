package com.snack.bean;

import java.util.HashMap;
import java.util.Map;


public class Msg {
    //状态码 100-成功  200-失败
    private int code;
    //提示信息
    private String msg;
    //用户要返回给浏览器的数据
    private Map<String, Object> extend = new HashMap<String, Object>();

    public static com.snack.bean.Msg success(){
        com.snack.bean.Msg result = new com.snack.bean.Msg();
        result.setCode(100);
        result.setMsg("success!");
        return result;
    }

    public static com.snack.bean.Msg fail(){
        com.snack.bean.Msg result = new com.snack.bean.Msg();
        result.setCode(200);
        result.setMsg("fail!");
        return result;
    }

    public com.snack.bean.Msg add(String key, Object value){
        this.getExtend().put(key,value);
        return this;
    }

    public Msg() {
    }

    public Msg(int code, String msg, Map<String, Object> extend) {
        this.code = code;
        this.msg = msg;
        this.extend = extend;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", extend=" + extend +
                '}';
    }
}
