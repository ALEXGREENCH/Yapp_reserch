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
    private static final Vector vectorData = new Vector();

    public SendPackets() {
        vectorData.removeAllElements();
        isRunning = true;
        (new Thread(this)).start();
    }

    public static void addDOS(DataOutputStream dos) {
        if (dataOutputStream != null) {
            try {
                dataOutputStream.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        dataOutputStream = dos;
    }

    private static void send() {
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

    public static void addByteArrData(byte[] bites) {
        vectorData.addElement(bites);
    }

    public static void send(byte[] bites) {
        if (!NetworkUtil.isRunning) {
            ReceivePackets.reconnectFlag = true;
        }

        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bas);

        try {
            dos.writeInt(StaticData.connectID);
            dos.writeUTF(StaticData.connectString);
            dos.write(bites);
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
        // проверка каждую секунду наличия данных для отправки
        // и собственно сама отпрака
        while (isRunning) {
            if (dataOutputStream != null) {
                send();
            }
            try {
                //noinspection BusyWait
                Thread.sleep(1000L);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

    }
}
