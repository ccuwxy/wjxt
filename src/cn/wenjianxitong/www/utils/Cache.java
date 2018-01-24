package cn.wenjianxitong.www.utils;

import redis.clients.jedis.Jedis;

public class Cache {
    private String sId;
    private String sUserName;
    private String iType;
    private String iAuthority;
    private String iStatus;
    private String sToken;
    private String lLoginTime;
    public Cache(){
        Jedis jedis = new Jedis("localhost");
        sId = jedis.get("sID");
        sUserName = jedis.get("sUserName");
        iType = jedis.get("iType");
        iAuthority = jedis.get("iAuthority");
        iStatus = jedis.get("iStatus");
        sToken = jedis.get("sToken");
        lLoginTime = jedis.get("lLoginTime");
    }
    public String getID(){
        return sId;
    }
    public String getUserName(){
        return sUserName;
    }
    public String getType(){
        return iType;
    }
    public String getStatus(){
        return iStatus;
    }
    public String getAuthority(){
        return iAuthority;
    }
    public String getToken(){
        return sToken;
    }
    public String getLoginTime(){
        return lLoginTime;
    }


}
