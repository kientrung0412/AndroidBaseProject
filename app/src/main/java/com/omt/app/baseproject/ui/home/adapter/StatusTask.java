package com.omt.app.baseproject.ui.home.adapter;

public enum StatusTask {

    OPEN(1, "Mở"),
    DONE(2, "Hoàn thành"),
    CLOSE(3, "Đã đóng"),
    LATE(4, "Trễ hạn");

    private final int status;
    private final String nameStatus;

    StatusTask(int i, String s) {
        this.status = i;
        this.nameStatus = s;
    }

    static StatusTask valueOf(Integer integer) {
        if (integer != null) {
            for (StatusTask value : values()) {
                if (Integer.valueOf(value.status).equals(integer))
                    return value;
            }
        }
        return OPEN;
    }

    public int getStatus() {
        return status;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    @Override
    public String toString() {
        return nameStatus;
    }
}
