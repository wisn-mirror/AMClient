package com.wisn.pm.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
/**
 * <b>Create Date:</b> 2016/9/30<br>
 * <b>Author:</b> Wisn(吴贻顺)<br>
 * <b>Description:</b>
 * <p>用于webSocket 建立长连接使用
 * <br>
 */

public class WebSocketConnectService extends Service {
    private Draft draft = new Draft_10();// 连接协议

    /* DraftInfo[] draftInfos = {new DraftInfo("WebSocket协议Draft_17", new Draft_17()), new DraftInfo
             ("WebSocket协议Draft_10", new Draft_10()), new DraftInfo("WebSocket协议Draft_76", new Draft_76()), new
             DraftInfo("WebSocket协议Draft_75", new Draft_75())};// 所有连接协议*/
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private void client() {
        try {
            String address = null;
            Log.e("wlf", "连接地址：" + address);
            WebSocketClient client = new WebSocketClient(new URI(address), draft) {
                @Override
                public void onOpen(final ServerHandshake serverHandshakeData) {

                }

                @Override
                public void onMessage(final String message) {

                }

                @Override
                public void onClose(final int code, final String reason, final boolean remote) {

                }

                @Override
                public void onError(final Exception e) {

                }
            };
            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
