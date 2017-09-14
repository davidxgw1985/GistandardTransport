package com.gistandard.transport.register.bean;

/**
 * @author yujie
 * @ClassName PortraitCutBean
 * @Description
 * @Version 1.0
 * @Date 2015-09-21
 */
public class PortraitCutBean {
    /**
     * 剪切图片的起始x轴
     */
    private int startX;

    /**
     * 剪切图片的起始y轴
     */
    private int startY;

    /**
     * 剪切图片的宽度
     */
    private int cutPicWidth;

    /**
     * 剪切图片的高度
     */
    private int cutPicHeight;

    /**
     * 原文件ID
     */
    private int fileId;

    /**
     * 展示的界面上被裁减的图片宽度
     */
    private int showPicWidth;

    /**
     * 展示的界面上被裁减的图片高度
     */
    private int showPicHeight;

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getCutPicWidth() {
        return cutPicWidth;
    }

    public void setCutPicWidth(int cutPicWidth) {
        this.cutPicWidth = cutPicWidth;
    }

    public int getCutPicHeight() {
        return cutPicHeight;
    }

    public void setCutPicHeight(int cutPicHeight) {
        this.cutPicHeight = cutPicHeight;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getShowPicWidth() {
        return showPicWidth;
    }

    public void setShowPicWidth(int showPicWidth) {
        this.showPicWidth = showPicWidth;
    }

    public int getShowPicHeight() {
        return showPicHeight;
    }

    public void setShowPicHeight(int showPicHeight) {
        this.showPicHeight = showPicHeight;
    }
}
