package com.gistandard.transport.system.frame.batch;

import com.gistandard.transport.system.frame.batch.bean.BaseBatchResult;
import com.gistandard.transport.system.frame.batch.bean.BaseFaildBean;
import com.gistandard.transport.system.frame.batch.bean.BatchRequest;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by m on 2016/12/14.
 */
public class BatchFramer {
    public static <T, S> BaseBatchResult<S> eachCheck(final List<Interceptor> list, final T req, final Completor<T, S> completor){
        return eachCheck(list, req, DefaultError.getInstance(), completor);
    }

    public static <T, S> BaseBatchResult<S> eachCheck(final List<Interceptor> list, final T req, final Error error, final Completor<T, S> completor) {
        final List<T> reqs = new ArrayList<>();
        reqs.add(req);
        final BaseBatchResult<S> res = new BaseBatchResult();
        Observable.from(reqs)
                .subscribe(
                        new Action1<T>() {
                            @Override
                            public void call(T request) {
                                List<BaseFaildBean> faildBeans = new ArrayList<>();
                                BaseBatchResult<S> result = null;
                                for (Interceptor interceptor : list) {
                                    result = interceptor.filter(request);
                                    faildBeans.addAll(result.getFaildBeans());
                                    if (result.getRetCode() == 0) {
                                        //可以进入异常处理组件
                                        continue;
                                    }
                                }
                                res.setSuccessBeans(result.getSuccessBeans());
                                res.setFaildBeans(faildBeans);
                                res.setRetCode(result.getRetCode());
                                res.setRetMsg(result.getRetMsg());
                                res.setReqId(result.getReqId());
                            }
                        },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                if (error != null) {
                                    error.error();
                                }
                            }
                        },
                        new Action0() {
                            @Override
                            public void call() {
                                if (res.getSuccessBeans().size() > 0) {
                                    BaseBatchResult<S> result = completor.complete(req, res);
                                    res.setSuccessBeans(result.getSuccessBeans());
                                    res.setFaildBeans(result.getFaildBeans());
                                    res.setRetCode(result.getRetCode());
                                    res.setRetMsg(result.getRetMsg());
                                    res.setReqId(result.getReqId());
                                }
                            }
                        }
                );
        return res;
    }

    public static <T> void batchCheck(final List<Interceptor> list, final List<BatchRequest<T>> reqs, final Error error, final Completor completor) {
        final List<BaseFaildBean> faildBeans = new ArrayList<>();
        final List<BatchRequest<T>> requests = new ArrayList<>();
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        for (int i = 0; i < 5; i++) {
                            subscriber.onNext(i);
                        }
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onCompleted() {

            }
        });
    }
}