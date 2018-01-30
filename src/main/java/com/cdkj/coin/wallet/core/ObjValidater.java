package com.cdkj.coin.wallet.core;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.internal.engine.path.PathImpl;

import com.cdkj.coin.wallet.exception.BizException;

/**
 * Created by tianlei on 2017/十一月/01.
 */
public class ObjValidater {

    static private Validator validator;
    static {

        //
        ValidatorFactory validatorFactory = Validation
            .buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

    }

    // jsr 验证
    static public <T> void validateReq(T req) {

        if (req == null) {

            throw new BizException("xn702000", "req 为null");

        }
        Set<ConstraintViolation<T>> set = validator.validate(req);
        // default is ""
        StringBuilder stringBuilder = new StringBuilder("");
        //
        if (set.size() <= 0) {
            return;
        }

        // 有异常信息
        Iterator<ConstraintViolation<T>> iterator = set.iterator();
        while (iterator.hasNext()) {
            ConstraintViolation<T> constraintViolation = iterator.next();

            // 获取属性名称
            PathImpl path = (PathImpl) constraintViolation.getPropertyPath();
            String propertyName = path.getLeafNode().getName();
            if (propertyName != null) {

                stringBuilder.append("[" + propertyName + "]");
                // stringBuilder.append(":");

            }

            // 获取异常信息
            // 注释的信息
            String msg = constraintViolation.getMessage();
            if (msg != null) {
                stringBuilder.append(msg);
                // stringBuilder.append("-");
                stringBuilder.append(";");
            }

        }

        if (stringBuilder.toString().isEmpty() == false) {
            throw new BizException("xn702000", stringBuilder.toString());
        }
        // TODO：emjio
    }

}
