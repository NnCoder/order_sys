package com.order.util;

import com.order.bean.WSBean;
import net.sf.json.JSONObject;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Tao on 2017/12/15.
 */
@ServerEndpoint("/informSocket/{userid}/{userType}")
public class InformSocketUtil{
    //采用线程安全Map
    private static ConcurrentMap<Integer,Session> customers;
    private static ConcurrentMap<Integer,Session> shops;
    static{
        customers = new ConcurrentHashMap<>();
        shops = new ConcurrentHashMap<>();
    }

    @OnOpen
    public void onOpen(@PathParam("userid")int userid,@PathParam("userType") int userType, Session session){
        //userType=1 顾客 userType=2 店家
        if(userType==1){
            customers.put(userid,session);
        }else {
            shops.put(userid,session);
        }
        session.getAsyncRemote().sendText("欢迎登录订餐系统~祝您用餐愉快!");
    }

    @OnMessage
    public void onMessage(String message){
        //userType=1 顾客 userType=2 店家
        JSONObject jsonObject = JSONObject.fromObject(message);
        int userid = (int) jsonObject.get("to");
        int userType = (int) jsonObject.get("toType");
        String msg = (String) jsonObject.get("message");
        Session session = null;
        if(userType==1){
           session = customers.get(userid);
        }else{
            session = shops.get(userid);
        }
        if(session!=null){
            session.getAsyncRemote().sendText(msg);
        }
    }
    @OnClose
    public void onClose(@PathParam("userid")int userid,@PathParam("userType")int userType){
        if(userType==1){
            customers.remove(userid);
        }else{
            shops.remove(userid);
        }
    }
    @OnError
    public void onError(@PathParam("userid")int userid,@PathParam("userType")int userType,Session session, Throwable error){
        if(userType==1){
            customers.remove(userid);
        }else{
            shops.remove(userid);
        }
        error.printStackTrace();
    }
}
