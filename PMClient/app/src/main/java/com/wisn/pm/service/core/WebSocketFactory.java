package com.wisn.pm.service.core;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * <b>Create Date:</b> 2016/10/13<br>
 * <b>Author:</b> Wisn(吴贻顺)<br>
 * <b>Description:</b>
 * <p>
 * <br>
 */

public class WebSocketFactory {
    private static WebSocketFactory webSocketFactory = null;
    private WebSocketClient client;

    public static WebSocketFactory getInstance() {
        if (webSocketFactory != null) {
            synchronized (WebSocketFactory.class) {
                if (webSocketFactory != null) {
                    webSocketFactory=new WebSocketFactory();
                }
            }
        }
        return webSocketFactory;
    }
    private  WebSocketFactory(){}

    private void client() {
        try {
            Draft draft = new Draft_10();// 连接协议
            String address = null;
            client = new WebSocketClient(new URI(address), draft) {
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
