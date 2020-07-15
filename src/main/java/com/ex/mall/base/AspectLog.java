package com.ex.mall.base;

import com.alibaba.fastjson.JSONObject;
import com.ex.mall.base.anno.AspectAnno;
import com.ex.mall.base.utils.DateUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
* @Package: com.ex.mall.base
* @ClassName: AspectLog
* @Description: 切面处理日志
* @Author: mbm
* @date: 2020/7/7 22:30
* @Version: 1.0
*/
@Aspect
@Component
public class AspectLog {

    private static Logger logger = LoggerFactory.getLogger(AspectLog.class);

    @Around("@annotation(com.ex.mall.base.anno.AspectAnno)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result;

        // 获取签名
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 获取方法信息
        Method method = signature.getMethod();
        // 获取方法上的注解信息
        AspectAnno anno = method.getAnnotation(AspectAnno.class);
        // 获取注解上的参数信息
        String desc = anno.desc();
        // 方法开始时间
        Date beginTime  = DateUtil.parseDateToStampDate(new Date());
        long timeBegin = beginTime.getTime();
        // 执行方法
        result = point.proceed();
        // 方法结束时间
        Date endTime = DateUtil.parseDateToStampDate(new Date());
        long timeEnd = endTime.getTime();
        // 保存日志
        try {
            saveLog(point, desc, beginTime,endTime,(timeBegin - timeEnd));
        }catch (Exception e){
            logger.error("切面类 执行异常:{}",e);
        }

        return result;
    }

    private void saveLog(ProceedingJoinPoint point,String desc, Date beginTime,Date endTime,long executeTime){
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 获取方法名
        String classAndMethodName = point.getTarget().getClass().getName() + "." + signature.getName() + "()";
        // 请求的参数
        Object[] args = point.getArgs();
        String params = null;
        if(args.length > 0){
            params = JSONObject.toJSONString(args[0]);
        }

        // 日志信息记录
        logger.info("请求方法:"+ desc + classAndMethodName + ",开始执行: {}", beginTime);
        logger.info("请求方法" + desc + classAndMethodName + ",请求参数: {}", params);
        logger.info("请求方法" + desc + classAndMethodName + ",执行完毕: {}", endTime);
        logger.info("请求方法" + desc + classAndMethodName + ",执行时长: {}", formatTime(executeTime));

    }

    /**
     * 时间计算格式转换
     * TODO 提到公用工具类
     * @param time
     * @return
     */
    private static String formatTime(Long time) {
        int dayNum = 1000 * 60 * 60 * 24;
        int hourNum = 1000 * 60 * 60;
        int minNum = 1000 * 60;
        int secondNum = 1000;
        if (time / dayNum > 1) {
            return time / dayNum + "天:" + time % dayNum / hourNum + "小时:" + time % hourNum / minNum + "分钟:" + time % minNum / secondNum + "秒:" + time % secondNum + "毫秒";
        } else if (time / hourNum > 1) {
            return time / hourNum + "小时:" + time % hourNum / minNum + "分钟:" + time % minNum / secondNum + "秒:" + time % secondNum + "毫秒";
        } else if (time / minNum > 1) {
            return time / minNum + "分钟:" + time % minNum / secondNum + "秒:" + time % secondNum + "毫秒";
        } else if (time / secondNum > 1) {
            return time / secondNum + "秒:" + time % secondNum + "毫秒";
        }
        return time + "毫秒";
    }


}
