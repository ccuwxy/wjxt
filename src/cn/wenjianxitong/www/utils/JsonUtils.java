package cn.wenjianxitong.www.utils;

import cn.wenjianxitong.www.exception.utils.MalformedJsonException;
import com.google.gson.Gson;

public class JsonUtils{
    public static Gson gson=new Gson();

    public static synchronized String toJson(Object src) {
        return gson.toJson(src);
    }

    public static synchronized <T> T fromJson(String json,Class<T> classOff)throws MalformedJsonException{
        try{
            return gson.fromJson(json, classOff);
        }catch (Exception e){
            throw new MalformedJsonException();
        }
    }
}
