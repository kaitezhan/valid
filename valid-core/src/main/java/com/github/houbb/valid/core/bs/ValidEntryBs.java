package com.github.houbb.valid.core.bs;

import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.lang.reflect.ReflectAnnotationUtil;
import com.github.houbb.heaven.util.lang.reflect.ReflectFieldUtil;
import com.github.houbb.heaven.util.util.Optional;
import com.github.houbb.valid.api.annotation.constraint.Constraint;
import com.github.houbb.valid.api.api.constraint.annotation.IAnnotationConstraint;
import com.github.houbb.valid.core.constant.AnnotationConst;
import com.github.houbb.valid.core.model.ValidEntry;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

/**
 * @see com.github.houbb.valid.core.model.ValidEntry 验证明细
 * @author binbin.hou
 * @since 0.0.9
 */
class ValidEntryBs {

    /**
     * 验证组
     * @since 0.0.9
     */
    private final Class[] validGroup;

    ValidEntryBs(Class[] validGroup) {
        this.validGroup = validGroup;
    }

    public static ValidEntryBs newInstance(final Class... validGroup) {
        return new ValidEntryBs(validGroup);
    }

    /**
     * 构建验证明细列表
     * @return 验证明细列表
     * @since 0.0.9
     */
    List<ValidEntry> buildValidEntryList(final Object object) {
        if(ObjectUtil.isNull(object)) {
            return Collections.emptyList();
        }

        List<ValidEntry> validEntryList = Guavas.newArrayList();

        List<Field> fieldList = ClassUtil.getAllFieldList(object.getClass());
        for(Field field : fieldList) {
            List<ValidEntry> fieldValidEntry = buildValidEntryList(fieldList, field, object);
            validEntryList.addAll(fieldValidEntry);
        }

        return validEntryList;
    }

    /**
     * 构建验证明细列表
     * TODO: 为了简化，暂时不考虑 {@link com.github.houbb.valid.api.annotation.condition.Condition} 的支持。
     *
     * @param fieldList 字段列表
     * @param field 字段信息
     * @param instance 实例
     * @return 验证明细列表
     * @since 0.0.9
     */
    @SuppressWarnings("unchecked")
    private List<ValidEntry> buildValidEntryList(final List<Field> fieldList,
                                                        final Field field,
                                                        final Object instance) {
        List<ValidEntry> validEntryList = Guavas.newArrayList();

        Annotation[] annotations = field.getAnnotations();

        for(Annotation annotation : annotations) {
            Optional<IAnnotationConstraint> constraintOptional = constraintOptional(annotation);
            if(constraintOptional.isNotPresent()) {
                continue;
            }

            // 构建上下文
            IAnnotationConstraint annotationConstraint = constraintOptional.get();
            annotationConstraint.init(annotation);
            final Object fieldValue = ReflectFieldUtil.getValue(field, instance);

            ValidEntry validEntry = ValidEntry.newInstance()
                    .message(getMessage(annotation))
                    .group(getGroup(annotation))
                    .constraint(annotationConstraint)
                    .validGroup(validGroup)
                    .fieldList(fieldList)
                    .instance(instance)
                    .value(fieldValue);

            validEntryList.add(validEntry);

            // TODO: 是否需要进行明细验证
        }
        return validEntryList;
    }

    /**
     * 获取注解对应的 message 信息
     * @param annotation 注解
     * @return message() 方法对应的值。
     * @since 0.0.9
     */
    private String getMessage(final Annotation annotation) {
        return ReflectAnnotationUtil.getValueStr(annotation, AnnotationConst.MESSAGE);
    }

    /**
     * 获取注解对应的 message 信息
     *
     * @param annotation 注解
     * @return message() 方法对应的值。
     * @since 0.0.9
     */
    private Class[] getGroup(final Annotation annotation) {
        Object object = ReflectAnnotationUtil.getValue(annotation, AnnotationConst.GROUP);

        if(ObjectUtil.isNull(object)) {
            return null;
        }

        return (Class[]) object;
    }


    /**
     * 获取对应的注解约束信息
     * @param annotation 注解
     * @return 约束实现类
     * @since 0.0.9
     */
    private Optional<IAnnotationConstraint> constraintOptional(final Annotation annotation) {
        Constraint constraint = annotation.annotationType().getAnnotation(Constraint.class);
        if (ObjectUtil.isNotNull(constraint)) {
            Class<? extends IAnnotationConstraint> clazz = constraint.value();
            return Optional.of(ClassUtil.newInstance(clazz));
        }

        return Optional.empty();
    }

}
