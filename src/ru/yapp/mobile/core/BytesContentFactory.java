package ru.yapp.mobile.core;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class BytesContentFactory {

    private short type = 0;

    private ByteArrayOutputStream byteArrayOutputStream;
    private DataOutputStream dataOutputStream;

    public BytesContentFactory() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        dataOutputStream = new DataOutputStream(byteArrayOutputStream);
    }

    public final void addUtfString(String str) {
        try {
            dataOutputStream.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final void addLong(long longData) {
        try {
            dataOutputStream.writeLong(longData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final void addInt(int intData) {
        try {
            dataOutputStream.writeInt(intData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final void addByte(byte byteData) {
        try {
            dataOutputStream.writeByte(byteData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final byte[] bytesArray() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        byte[] bitesFromBAOS = this.byteArrayOutputStream.toByteArray();

        try {
            dos.writeInt(StaticData.connectID);
            dos.writeUTF(StaticData.connectString);
            dos.writeShort(this.type);
            dos.writeInt(bitesFromBAOS.length);
            dos.write(bitesFromBAOS);
            dos.flush();
            baos.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return baos.toByteArray();
    }

    public final void setType(short type) {
        this.type = type;
    }
}
