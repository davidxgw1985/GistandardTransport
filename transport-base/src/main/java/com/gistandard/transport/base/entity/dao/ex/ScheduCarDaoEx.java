package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ScheduCar;
import org.apache.ibatis.annotations.Param;

/**
 * Created by m on 2016/9/30.
 */
@MyBatisRepository
public interface ScheduCarDaoEx {
    ScheduCar selectByScheducarno(@Param("scheducarno")String scheducarno);

    int acceptOrder(ScheduCar record);
}
