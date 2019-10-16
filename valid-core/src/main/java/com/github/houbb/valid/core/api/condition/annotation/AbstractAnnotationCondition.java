package com.github.houbb.valid.core.api.condition.annotation;

import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.valid.api.api.condition.ICondition;
import com.github.houbb.valid.api.api.condition.IConditionContext;
import com.github.houbb.valid.api.api.condition.annotation.IAnnotationCondition;

import java.lang.annotation.Annotation;

/**
 * 抽象注解条件接口
 * @author binbin.hou
 * @since 0.1.3
 */
public abstract class AbstractAnnotationCondition<A extends Annotation> implements IAnnotationCondition<A> {

    /**
     * 注解信息
     */
    private A annotation;

    /**
     * 构建条件实现类
     * @param annotation 注解信息
     * @return 条件实现
     * @since 0.1.3
     */
    protected abstract ICondition buildCondition(final A annotation);

    /**
     * 构建注解提示名称
     * @return 提示名称
     * @since 0.1.3
     */
    protected String annotationName() {
        return "annotation";
    }

    @Override
    public void initialize(A annotation) {
        ArgUtil.notNull(annotation, annotationName());
        this.annotation = annotation;
    }

    @Override
    public boolean condition(IConditionContext conditionContext) {
        ICondition condition = buildCondition(annotation);
        return condition.condition(conditionContext);
    }

}
