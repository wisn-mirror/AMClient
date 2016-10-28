package com.wisn.pmlib.service.core;

import com.wisn.pm.service.MessageProcessor;

import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_10;

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
    private WebSocketClientInstance client;
    public static WebSocketFactory getInstance() {
        if (webSocketFactory == null) {
            synchronized (WebSocketFactory.class) {
                if (webSocketFactory != null) {
                    webSocketFactory = new WebSocketFactory();
                }
            }
        }
        return webSocketFactory;
    }

    private WebSocketFactory() {
    }


    private WebSocketClientInstance clientServer(String address, MessageProcessor messageProcessor) {
        if(client!=null)
            return client;
        try {
            Draft draft = new Draft_10();// 连接协议
            client = new WebSocketClientInstance(new URI(address), draft);
            client.setMessageProcessor(messageProcessor);
            client.connect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }
}
