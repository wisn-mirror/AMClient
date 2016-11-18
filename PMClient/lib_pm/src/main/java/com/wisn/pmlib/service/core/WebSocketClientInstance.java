package com.wisn.pmlib.service.core;


import com.wisn.pmlib.service.MessageProcessor;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;

/**
 * <b>Create Date:</b> 2016/10/14<br>
 * <b>Author:</b> Wisn(吴贻顺)<br>
 * <b>Description:</b>
 * <p>
 * <br>
 */

public class WebSocketClientInstance  extends WebSocketClient {
    private MessageProcessor messageProcessor;
    public void  setMessageProcessor( MessageProcessor  messageProcessor){
        this.messageProcessor=messageProcessor;
    }
    public WebSocketClientInstance(URI serverURI) {
        super(serverURI);
    }

    public WebSocketClientInstance(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public WebSocketClientInstance(URI serverUri, Draft draft, Map<String, String> headers, int connecttimeout) {
        super(serverUri, draft, headers, connecttimeout);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        if(messageProcessor!=null){
            messageProcessor.onOpen(handshakedata);
        }
    }

    @Override
    public void onMessage(String message) {
        if(messageProcessor!=null){
        messageProcessor.onMessage(message);

        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        if(messageProcessor!=null){
            messageProcessor.onClose(code,reason,remote);

        }
    }

    @Override
    public void onError(Exception ex) {
        if(messageProcessor!=null){
            messageProcessor.onError(ex);

        }
    }
}
