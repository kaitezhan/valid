package com.github.houbb.valid.core.constant;

import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.Optional;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.core.api.constraint.annotation.AllEqualsConstraint;
import com.github.houbb.valid.core.api.constraint.annotation.EnumRangesConstraint;
import com.github.houbb.valid.core.api.constraint.annotation.HasNotNullConstraint;
import com.github.houbb.valid.core.api.constraint.annotation.RangesConstraint;
import com.github.houbb.valid.core.i18n.I18N;

import java.util.HashMap;
import java.util.Map;

/**
 * 约束常量
 * @author binbin.hou
 * @since 0.0.9
 */
public final class ConstraintConst {

    /**
     * 约束消息集合
     * @since 0.0.9
     */
    private static final Map<Class, String> CONSTRAINT_MESSAGE_MAP = new HashMap<>();

    static {
        CONSTRAINT_MESSAGE_MAP.put(AllEqualsConstraint.class, "AllEqualsConstraintMessage");
        CONSTRAINT_MESSAGE_MAP.put(EnumRangesConstraint.class, "EnumRangesConstraintMessage");
        CONSTRAINT_MESSAGE_MAP.put(HasNotNullConstraint.class, "HasNotNullConstraintMessage");
        CONSTRAINT_MESSAGE_MAP.put(RangesConstraint.class, "RangesConstraintMessage");
    }

    private ConstraintConst(){}

    /**
     * 获取消息对应的 i18N
     * @param clazz 类别
     * @return 默认 i18n 消息
     * @since 0.0.9
     */
    public static Optional<String> getMessageI18n(final Class<? extends IConstraint> clazz) {
        String template = CONSTRAINT_MESSAGE_MAP.get(clazz);
        if(StringUtil.isEmpty(template)) {
            return Optional.empty();
        }

        String i18n = I18N.get(template);
        return Optional.of(i18n);
    }


}
