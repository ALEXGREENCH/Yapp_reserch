package ru.yapp.mobile.net;

import java.io.DataInputStream;
import java.io.IOException;

import ru.yapp.mobile.ScreenCanvas;
import ru.yapp.mobile.Yapp;
import ru.yapp.mobile.book.BookUtil;
import ru.yapp.mobile.browser.BrowserForm;
import ru.yapp.mobile.core.Core3;

public final class ReceivePackets implements Runnable {

    public static boolean reconnectFlag = false;
    public static boolean socketStoped = false;
    private static boolean initObject = false;
    private static DataInputStream dataInputStream;

    public ReceivePackets() {
        initObject = true;
        socketStoped = false;
        (new Thread(this)).start();
    }

    public static void addDIS(DataInputStream dis) {
        if (dataInputStream != null) {
            try {
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        dataInputStream = dis;
    }

    private static void receiveCommand() throws IOException {
        short commandByShort = dataInputStream.readShort();
        Yapp.Log("RECEIVE COMMAND->" + commandByShort);
        if (commandByShort > 0) {
            int var1 = dataInputStream.readInt();
            byte[] byteArr;
            int allReceiveKbData = var1 / 1024;
            int downloadKbData = 0;
            int readBookData;
            int var10;
            if (commandByShort == 66) {
                byteArr = new byte[var1];
                if (allReceiveKbData == 0) {
                    allReceiveKbData = 1;
                }

                if (BrowserForm.bool1 && Core3.a == 0) {
                    BrowserForm.a = "0/" + allReceiveKbData + "кб";
                    BrowserForm.prepareRender = true;
                }
                while (downloadKbData < var1) {
                    var10 = 1024;
                    if (var1 - downloadKbData < 1024) {
                        var10 = var1 - downloadKbData;
                    }

                    readBookData = dataInputStream.read(byteArr, downloadKbData, var10);
                    downloadKbData += readBookData;
                    if (BrowserForm.bool1 && Core3.a == 0) {
                        BrowserForm.a = downloadKbData / 1024 + "/" + allReceiveKbData + "кб";
                        BrowserForm.prepareRender = true;
                    }
                }

                if (BrowserForm.bool1 && Core3.a == 0) {
                    BrowserForm.a = downloadKbData / 1024 + "/" + allReceiveKbData + "кб";
                    BrowserForm.prepareRender = true;
                }

                Core3.parsingReceivedPackets(commandByShort, byteArr);
                return;
            }

            if (commandByShort == 704) {
                Yapp.Log("SYSTEM->" + dataInputStream.readUTF());
                allReceiveKbData = dataInputStream.readInt();
                Yapp.Log("BOOK SIZE->" + allReceiveKbData);
                byteArr = new byte[allReceiveKbData];
                if ((downloadKbData = allReceiveKbData / 1024) == 0) {
                    downloadKbData = 1;
                }

                BookUtil.downloadBookInfo = "0/" + downloadKbData + "кб";
                BookUtil.a = true;
                readBookData = 0;

                for (boolean i = false; readBookData < allReceiveKbData; BookUtil.a = true) {
                    int var7 = 1024;
                    if (allReceiveKbData - readBookData < 1024) {
                        var7 = allReceiveKbData - readBookData;
                    }

                    var10 = dataInputStream.read(byteArr, readBookData, var7);
                    readBookData += var10;
                    BookUtil.downloadBookInfo = readBookData / 1024 + "/" + downloadKbData + "кб";
                }

                BookUtil.downloadBookInfo = readBookData / 1024 + "/" + downloadKbData + "кб";

                try {
                    BookUtil.bookLoaded(byteArr);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                BookUtil.downloadBookInfo = "";
                BookUtil.a = true;
                return;
            }

            byteArr = new byte[var1];
            dataInputStream.readFully(byteArr);
            Core3.parsingReceivedPackets(commandByShort, byteArr);
        }
    }

    public static void close() {
        try {
            if (dataInputStream != null) {
                dataInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        dataInputStream = null;
    }

    public final void run() {
        while (initObject) {
            if (reconnectFlag && dataInputStream == null) {
                NetworkUtil.connect();
                reconnectFlag = false;
            }

            if (dataInputStream != null) {
                try {
                    receiveCommand();
                } catch (IOException ioe) {
                    socketStoped = true;
                    ScreenCanvas.boolean2 = true;
                    NetworkUtil.isRunning = false;
                }
            }

            if (socketStoped) {
                NetworkUtil.reconnect();
            }

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
