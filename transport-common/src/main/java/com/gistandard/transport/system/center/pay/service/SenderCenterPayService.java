package com.gistandard.transport.system.center.pay.service;

import com.gistandard.transport.system.center.pay.bean.SafePayInfoRes;

/**
 * Created by m on 2016/10/8.
 */
public interface SenderCenterPayService {
    SafePayInfoRes safePayInfo(String busiBookNo);
}
