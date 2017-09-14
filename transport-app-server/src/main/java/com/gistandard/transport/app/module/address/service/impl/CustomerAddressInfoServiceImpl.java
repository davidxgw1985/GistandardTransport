package com.gistandard.transport.app.module.address.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.gistandard.transport.gps.service.GpsService;
import com.gistandard.transport.system.common.bean.QueryAddressReq;
import com.gistandard.transport.system.webservice.client.gps.GiPoint;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gistandard.transport.app.module.address.bean.AddressRes;
import com.gistandard.transport.app.module.address.service.CustomerAddressInfoService;
import com.gistandard.transport.base.entity.bean.MobileAddressInfo;
import com.gistandard.transport.base.entity.dao.MobileAddressInfoDao;
import com.gistandard.transport.base.exception.CustomerBizException;
import com.gistandard.transport.system.common.address.dao.CustomerMobileAddressInfoDao;
import com.gistandard.transport.system.common.bean.AddressQueryRes;
import com.gistandard.transport.system.common.bean.AddressReq;

/**
 * @author kongxm
 * @ClassName AddressServiceImpl
 * @Description 地址管理业务实现
 * @Version 1.0
 * @Date 2016/1/26
 */
@Service
public class CustomerAddressInfoServiceImpl implements CustomerAddressInfoService {

    @Autowired
    private CustomerMobileAddressInfoDao customerMobileAddressInfoDao;

    @Autowired
    private MobileAddressInfoDao mobileAddressInfoDao;
    @Autowired
    private GpsService gpsService;

    @Override
    @Transactional(rollbackFor = CustomerBizException.class)
    public AddressRes addAddress(AddressReq addressreq) throws CustomerBizException {
        checkParam(addressreq);
        AddressQueryRes addressQueryRes = new AddressQueryRes();
        addressQueryRes.setCreateTime(new Date());
        addressQueryRes.setStatus(1);
        try {
            BeanUtils.copyProperties(addressQueryRes, addressreq);
            // mobileAddressInfo.setDefaultAddress(address.isDefaultAddress() ?
            // 1 : 0);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new CustomerBizException(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new CustomerBizException(e);
        }
        addressQueryRes.setAccountId(addressreq.getLoginAccountId());
        if (addressQueryRes.getCounty() != null && "0".equals(addressQueryRes.getCounty())) {
            addressQueryRes.setCounty(null);
        }

        if (addressreq.isDefaultAddress()) {
            List<AddressQueryRes> addressQueryRess = customerMobileAddressInfoDao.queryByCondition(addressreq);
            for (AddressQueryRes address : addressQueryRess) {
                if (address.getDefaultAddress() == 1) { // 默认地址
                    if (addressreq.getId() != null && (address.getId().compareTo(addressreq.getId()) == 0)) {
                        continue;
                    }
                    address.setDefaultAddress(0);
                    mobileAddressInfoDao.updateByPrimaryKey(address);
                }
            }
        }
        if (addressreq.getId() == null || addressreq.getId() == 0) {
            if (!StringUtil.isEmpty(addressQueryRes.getAddress()) && (addressQueryRes.getAddressLatitude() == null) || addressQueryRes.getAddressLongitude() == null) {
                //需要查询地址
                StringBuffer sb = new StringBuffer();
                sb.append(addressQueryRes.getProvinceName())
                        .append(addressQueryRes.getCityName())
                        .append(addressQueryRes.getCountyName())
                        .append(addressQueryRes.getAddress());
                GiPoint giPoint = gpsService.getGiPointByNearBy(sb.toString(), addressQueryRes.getDetailAddress());
                addressQueryRes.setAddressLongitude(new BigDecimal(giPoint.getLongitude()));
                addressQueryRes.setAddressLatitude(new BigDecimal(giPoint.getLatitude()));
            }
            int size = mobileAddressInfoDao.insert(addressQueryRes);
            if (size <= 0) {
                throw new CustomerBizException("增加失败");
            }
        } else { // update
            int size = mobileAddressInfoDao.updateByPrimaryKey(addressQueryRes);
            if (size <= 0) {
                throw new CustomerBizException("修改失败");
            }
        }
        AddressRes addressRes = new AddressRes();
        addressRes.setId(addressQueryRes.getId());
        return addressRes;
    }

    private void checkParam(AddressReq address) throws CustomerBizException {
        // //1是自己的收地址，2别人的地址
        if (address.getAddressType() == null || (address.getAddressType() != 1 && address.getAddressType() != 2)) {
            throw new CustomerBizException("地址类型不正确");
        }
        if (StringUtils.isEmpty(address.getName())) {
            throw new CustomerBizException("姓名不可为空");
        }
        if (address.getCountry() == null) {
            throw new CustomerBizException("国家代码不能为空");
        }
        if (address.getProvince() == null) {
            throw new CustomerBizException("省份代码不能为空");
        }
        if (address.getCity() == null) {
            throw new CustomerBizException("市代码不能为空");
        }
        // if (address.getCounty() == null) {
        // throw new CustomerBizException("区代码不能为空");
        // }
//		if (StringUtils.isEmpty(address.getAddress())) {
//			throw new CustomerBizException("收货地址不能为空");
//		}
        // if (StringUtils.isEmpty(address.getDetailAddress())) {
        // throw new CustomerBizException("详细地址不能为空");
        // }
        if (address.getTelephone() == null && address.getTelephone().length() != 11) {
            throw new CustomerBizException("手机号码不正确");
        }
        // 检查经纬度
//        checkAddressLongitudeAndLatitude(address);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Integer[] addrIds) {
        int totalSize = 0;
        for (int i = 0; i < addrIds.length; i++) {
            MobileAddressInfo mobileAddressInfo = new MobileAddressInfo();
            mobileAddressInfo.setId(addrIds[i]);
            mobileAddressInfo.setStatus(0);// 设置为失效
            int size = mobileAddressInfoDao.updateByPrimaryKeySelective(mobileAddressInfo);
            totalSize += size;
        }
        return totalSize;
    }

    @Override
    public int update(AddressReq address) throws CustomerBizException {
        if (address.getId() == null) {
            throw new CustomerBizException("更新地址主键不能为空");
        }
//        checkAddressLongitudeAndLatitude(address);
        MobileAddressInfo mobileAddressInfo = new MobileAddressInfo();
        try {
            BeanUtils.copyProperties(mobileAddressInfo, address);
            // mobileAddressInfo.setDefaultAddress(address.isDefaultAddress() ?
            // 1 : 0);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new CustomerBizException(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new CustomerBizException(e);
        }
        return mobileAddressInfoDao.updateByPrimaryKeySelective(mobileAddressInfo);
    }

    private void checkAddressLongitudeAndLatitude(AddressReq address) throws CustomerBizException {
        if (address.getAddressLongitude() == null || address.getAddressLongitude().compareTo(new BigDecimal(0)) == 0) {
            throw new CustomerBizException("经度不能为空");
        }
        if (address.getAddressLatitude() == null || address.getAddressLatitude().compareTo(new BigDecimal(0)) == 0) {
            throw new CustomerBizException("纬度不能为空");
        }
    }

    @Override
    public List<AddressQueryRes> queryByAccountId(QueryAddressReq req) throws CustomerBizException {
        List<AddressQueryRes> addressList = customerMobileAddressInfoDao.queryByAccountId(req);
        return addressList;
    }

    @Override
    @Transactional
    public void defaultAddress(AddressReq req) throws CustomerBizException {
        List<AddressQueryRes> addressQueryRess = customerMobileAddressInfoDao.queryByCondition(req);
        for (AddressQueryRes address : addressQueryRess) {
            if (address.getDefaultAddress() == 1) { // 默认地址
                address.setDefaultAddress(0);
                mobileAddressInfoDao.updateByPrimaryKey(address);
            }
            if (address.getId().compareTo(req.getId()) == 0) {
                address.setDefaultAddress(1);
                mobileAddressInfoDao.updateByPrimaryKey(address);
            }
        }
    }

    public Integer recordCount(QueryAddressReq req) {
        return customerMobileAddressInfoDao.totalCount(req);
    }

    @Override
    public AddressQueryRes queryByPrimaryKey(Integer id) {
        return customerMobileAddressInfoDao.queryByPrimaryKey(id);
    }

}
