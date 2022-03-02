package ru.yapp.mobile;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import ru.yapp.mobile.book.Book;
import ru.yapp.mobile.browser.BrowserForm;
import ru.yapp.mobile.browser.BrowserRender;
import ru.yapp.mobile.core.ResUI;
import ru.yapp.mobile.core.StaticData;
import ru.yapp.mobile.core.Audio;
import ru.yapp.mobile.core.DB;
import ru.yapp.mobile.net.SocketConnector;

public class Yapp extends MIDlet {
    public static Yapp yappMidlet;
    public static Display display;
    public static Audio audio;
   
    private static boolean isVisible = false;


    protected void startApp() throws MIDletStateChangeException {
        Log("FREE MEMORY ->" + Runtime.getRuntime().freeMemory());
        display = Display.getDisplay(this);
        yappMidlet = this;
        BrowserForm.bool1 = false;
        if (!isVisible) {
            ResUI.createImageSplash();
            ScreenCanvas screenCanvas = new ScreenCanvas();
            display.setCurrent(screenCanvas);
            try {
                Thread.sleep(200L);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

            int time1 = (int)System.currentTimeMillis();
            int time2 = (int)System.currentTimeMillis();

            while(time2 - time1 < 5000) {
                time2 = (int)System.currentTimeMillis();
                StaticData.screenHeight = screenCanvas.getHeight();
                StaticData.screenWidth = screenCanvas.getWidth();
                if (StaticData.screenHeight != 0) {
                    break;
                }
            }

            try {
                ResUI.init();
            } catch (Exception e) {
                e.printStackTrace();
            }
         
            try {
                DB.init();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Book.init();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            try {
                audio = new Audio();
                audio.init();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            SocketConnector.init();
            
            //Browser2.renderAuthScreen(new byte[]{1, 0, 99, 0, 0, 0, 0}, "Yapp! beta 1.7");
            BrowserRender.renderScreenDemo();
            ScreenCanvas.boolean2 = true;
            BrowserForm.prepareRender = true;
            ScreenCanvas.screenMode = 1;
        }
      
        Log("MIDlet started");
        Log("FREE MEMORY ->" + Runtime.getRuntime().freeMemory());
    }

    protected void pauseApp() {
        isVisible = true;
    }

    protected void destroyApp(boolean var1) throws MIDletStateChangeException {
        this.midletDestroy();
    }

    public static void Log(String msg) {
        System.out.println(msg);
    }

    public final void midletDestroy() {
        BrowserForm.prepareRender = true;
        ScreenCanvas.screenMode = 0;
        ScreenCanvas.textDialog = "Завершение работы приложения...";
        ScreenCanvas.boolean2 = true;
        Book.saveBookInBD();
        try {
            DB.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SocketConnector.disconnect();
        ScreenCanvas.close();
      
        this.notifyDestroyed();
        Log("MIDlet destroed");
    }
}
