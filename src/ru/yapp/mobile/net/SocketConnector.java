package ru.yapp.mobile.net;

import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

import ru.yapp.mobile.ScreenCanvas;
import ru.yapp.mobile.browser.BrowserForm;
import ru.yapp.mobile.core.Core3;
import ru.yapp.mobile.messenger.Messenger1;

public final class SocketConnector {
    
    public static boolean isRunning = false;
    
    private static SocketConnection socketConnection;

    public static void init() {
        new ReceivePackets();
        new SendPackets();
    }

    public static void connect() {
        if (socketConnection == null) {
            try {
                socketConnection = (SocketConnection)Connector.open("socket://87.242.76.88:6666", 3);
                ReceivePackets.addDIS(socketConnection.openDataInputStream());
                SendPackets.addDIS(socketConnection.openDataOutputStream());
                Messenger1.a();
                ReceivePackets.socketStoped = false;
                Core3.a = 0;
                isRunning = true;
            } catch (IOException e) {
                e.printStackTrace();
                socketConnection = null;
                ReceivePackets.socketStoped = true;
                ScreenCanvas.boolean2 = true;
                isRunning = false;
            }
        }
        BrowserForm.prepareRender = true;
    }

    public static void reconnect() {
        if (socketConnection != null) {
            try {
                socketConnection.close();
                socketConnection = null;
                isRunning = false;
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        connect();
    }

    public static void disconnect() {
        ReceivePackets.close();
        SendPackets.disconnect();
        try {
            if (socketConnection != null) {
                socketConnection.close();
                isRunning = false;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        socketConnection = null;
    }
}