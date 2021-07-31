package com.github.houbb.valid.jsr.api.validator;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.heaven.util.util.Optional;
import com.github.houbb.valid.api.api.constraint.annotation.IAnnotationConstraint;
import com.github.houbb.valid.core.api.validator.DefaultValidator;
import com.github.houbb.valid.jsr.i18n.JsrAtMessageI18N;
import com.github.houbb.valid.jsr.util.JsrAtConstraintMapUtil;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import java.lang.annotation.Annotation;

/**
 * JSR 验证器
 *
 * （1）保证 hibernate-only 和 jsr-only
 * （2）保证二者可以和 {@link DefaultValidator} 进行整合
 * （3）保证性能，避免重复列表循环。
 *
 *  同时保证代码的优雅与可重复使用？？
 *
 * 如何使用
 *
 * 灵活性：
 * <pre>
 *     ValidBs.valid(defaultValidator);
 *     ValidBs.valid(jsrValidator);
 *     ValidBs.valid(hibernateValidator);
 *     ValidBs.valid(validator, jsrValidator, hibernateValidator);
 * </pre>
 *
 * 性能：
 * defaultValidator+jsrValidator=defaultJsrValidator
 *
 * 暂时不考虑性能，后期进行优化。
 *
 * 全量而不缺失：
 * 如何保证每一个实现都是独立的，合并起来又是全量的？
 * 而不是通过一个组合的实现。
 *
 * 这里可以麻烦一点，将所有的 JSR 标准 api 进行实现。
 * @author binbin.hou
 * @since 0.1.1
 * @see com.github.houbb.valid.core.api.validator.DefaultValidator 默认验证器实现
 */
@ThreadSafe
public class JsrValidator extends DefaultValidator {

    /**
     * 获取单例实例
     */
    private static final JsrValidator INSTANCE = new JsrValidator();

    public static JsrValidator getInstance() {
        return INSTANCE;
    }

    /**
     * 获取对应的注解约束信息
     * （1）首先获取 {@link com.github.houbb.valid.api.annotation.constraint.Constraint} 相关实现
     * （2）如果不存在，再看 JSR-303 相关注解信息。
     * @param annotation 注解
     * @return 对应的注解约束实现
     */
    @Override
    protected Optional<IAnnotationConstraint> constraintOptional(Annotation annotation) {
        Optional<IAnnotationConstraint> constraintOptional = super.constraintOptional(annotation);

        // 处理 JSR 相关注解
        if(constraintOptional.isNotPresent()) {
            Constraint constraint = annotation.annotationType().getAnnotation(Constraint.class);
            if(ObjectUtil.isNotNull(constraint)) {
                Class<? extends ConstraintValidator<?, ?>>[] validatedBy = constraint.validatedBy();
                // 暂时不处理自定义的 jsr-303 注解
                if(ArrayUtil.isNotEmpty(validatedBy)) {
                    return constraintOptional;
                }

                // 如果为空，则直接看有没有对应注解实现
                // FIXED 1.6.0 不能使用 MAP，会导致相同的注解属性覆盖。
                IAnnotationConstraint annotationConstraint = JsrAtConstraintMapUtil.get(annotation.annotationType());
                return Optional.ofNullable(annotationConstraint);
            }
        }
        return constraintOptional;
    }

    /**
     * 对注解消息进行 i18n 处理
     * @param annotation 注解
     * @return 处理后的信息
     */
    @Override
    protected String getMessage(Annotation annotation) {
        String message = super.getMessage(annotation);
        return JsrAtMessageI18N.get(message);
    }

}
