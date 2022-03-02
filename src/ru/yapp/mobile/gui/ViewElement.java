package ru.yapp.mobile.gui;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public abstract class ViewElement {
    public byte[] biteArr1 = null;
    public byte[] byteArr2 = null;
    public Image image = null;
    public int j = 0;
    public int k = 0;
    public int l = 0;
    public int imgW = 0;
    public int imgH = 0;
    public int style = 0;
    public String text = null;
    public boolean boolean1 = false;

    public abstract void a(Graphics g);

    public abstract void b(Graphics g);
}
