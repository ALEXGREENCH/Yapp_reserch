package ru.yapp.mobile.net;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;

import ru.yapp.mobile.Yapp;
import ru.yapp.mobile.core.StaticData;
import ru.yapp.mobile.ScreenCanvas;

public final class SendPackets implements Runnable {

    private static boolean isRunning = false;
    private static DataOutputStream dataOutputStream;
    private static Vector vectorData = new Vector();

    public SendPackets() {
        vectorData.removeAllElements();
        isRunning = true;
        (new Thread(this)).start();
    }

    public static void addDIS(DataOutputStream dos) {
        if (dataOutputStream != null) {
            try {
                dataOutputStream.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        dataOutputStream = dos;
    }

    private static void b() {
        if (vectorData.size() > 0) {
            byte[] byteArr = (byte[]) ((byte[]) vectorData.elementAt(0));

            try {
                if (dataOutputStream != null) {
                    dataOutputStream.write(byteArr);
                    dataOutputStream.flush();
                    vectorData.removeElementAt(0);
                }
            } catch (IOException ioe) {
                ScreenCanvas.boolean2 = true;
                ReceivePackets.socketStoped = true;
                NetworkUtil.isRunning = false;
            }

            Yapp.Log("SENDED - >" + byteArr.length);
        }

    }

    public static void addByteArrData(byte[] data) {
        vectorData.addElement(data);
    }

    public static void b(byte[] var0) {
        if (!NetworkUtil.isRunning) {
            ReceivePackets.a = true;
        }

        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bas);

        try {
            dos.writeInt(StaticData.connectID);
            dos.writeUTF(StaticData.a);
            dos.write(var0);
            dos.flush();
            bas.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        addByteArrData(bas.toByteArray());
    }

    public static void disconnect() {
        try {
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        dataOutputStream = null;
    }

    public final void run() {
        while (isRunning) {
            if (dataOutputStream != null) {
                b();
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

    }
}
