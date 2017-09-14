package com.gistandard.transport.app.dubbo.wechat.bean;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: WeChatPlatFormOutModWrap
 * @Date: 2017/8/14
 * @Description:
 */
public class WeChatPlatFormOutModWrap implements Serializable{

    private static final long serialVersionUID = -3134542479614194834L;

    private WeChatPlatFormOutModel dataTCKD;

    private WeChatPlatFormOutModel dataTCZS;

    public WeChatPlatFormOutModel getDataTCKD() {
        return dataTCKD;
    }

    public void setDataTCKD(WeChatPlatFormOutModel dataTCKD) {
        this.dataTCKD = dataTCKD;
    }

    public WeChatPlatFormOutModel getDataTCZS() {
        return dataTCZS;
    }

    public void setDataTCZS(WeChatPlatFormOutModel dataTCZS) {
        this.dataTCZS = dataTCZS;
    }
}
