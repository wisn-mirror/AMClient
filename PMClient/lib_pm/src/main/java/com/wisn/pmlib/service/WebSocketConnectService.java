package com.wisn.pmlib.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import org.java_websocket.handshake.ServerHandshake;

/**
 * <b>Create Date:</b> 2016/9/30<br>
 * <b>Author:</b> Wisn(吴贻顺)<br>
 * <b>Description:</b>
 * <p>用于webSocket 建立长连接使用
 * <br>
 */

public class WebSocketConnectService extends Service {
    private MessageBindler messageBindler;
    /* DraftInfo[] draftInfos = {new DraftInfo("WebSocket协议Draft_17", new Draft_17()), new DraftInfo
             ("WebSocket协议Draft_10", new Draft_10()), new DraftInfo("WebSocket协议Draft_76", new Draft_76()), new
             DraftInfo("WebSocket协议Draft_75", new Draft_75())};// 所有连接协议*/


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messageBindler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        messageBindler = new MessageBindler();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    public  class  MessageBindler  extends Binder  implements  MessageProcessor{

        @Override
        public void onOpen(ServerHandshake handshakedata) {

        }

        @Override
        public void onMessage(String message) {

        }

        @Override
        public void onClose(int code, String reason, boolean remote) {

        }

        @Override
        public void onError(Exception ex) {

        }
    }

}
