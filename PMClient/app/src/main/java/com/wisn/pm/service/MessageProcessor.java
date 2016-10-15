package com.wisn.pm.service;

import org.java_websocket.handshake.ServerHandshake;

/**
 * <b>Create Date:</b> 2016/10/13<br>
 * <b>Author:</b> Wisn(吴贻顺)<br>
 * <b>Description:</b>
 * <p>
 * <br>
 */

public interface MessageProcessor {
    void onOpen(ServerHandshake handshakedata);
    void onMessage(String message);
    void onClose(int code, String reason, boolean remote);
    void onError(Exception ex) ;
}
