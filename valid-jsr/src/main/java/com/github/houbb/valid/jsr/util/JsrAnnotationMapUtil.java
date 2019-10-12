package com.github.houbb.valid.jsr.util;

import com.github.houbb.valid.api.api.constraint.annotation.IAnnotationConstraint;
import com.github.houbb.valid.jsr.constraint.annotation.*;

import javax.validation.Constraint;
import javax.validation.constraints.*;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 *  注解与约束关系
 * @see Constraint#validatedBy() 属性。
 * @author binbin.hou
 * @since 0.1.1
 */
public final class JsrAnnotationMapUtil {

    private JsrAnnotationMapUtil() {
    }

    private static final Map<Class<? extends Annotation>, IAnnotationConstraint> MAP;

    static {
        MAP = new HashMap<>(16);
        MAP.put(Null.class, new AtNullConstraint());
        MAP.put(NotNull.class, new AtNotNullConstraint());
        MAP.put(AssertTrue.class, new AtAssertTrueConstraint());
        MAP.put(AssertFalse.class, new AtAssertFalseConstraint());
        MAP.put(Past.class, new AtPastConstraint());
        MAP.put(Future.class, new AtFutureConstraint());
        MAP.put(Pattern.class, new AtPatternConstraint());
        MAP.put(Size.class, new AtSizeConstraint());
        MAP.put(Min.class, new AtMinConstraint());
        MAP.put(Max.class, new AtMaxConstraint());
        MAP.put(DecimalMax.class, new AtDecimalMaxConstraint());
        MAP.put(DecimalMin.class, new AtDecimalMinConstraint());
        MAP.put(Digits.class, new AtDigitsConstraint());
    }

    /**
     * 获取对应的内置注解实现类
     * @param clazz 注解类信息
     * @return 注解实现
     * @since 0.1.1
     */
    public static IAnnotationConstraint getAnnotationConstraint(final Class<? extends Annotation> clazz) {
        return MAP.get(clazz);
    }

}
