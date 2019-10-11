//package com.github.houbb.valid.core.api.validator.entry;
//
//import com.github.houbb.valid.api.api.condition.ICondition;
//import com.github.houbb.valid.api.api.constraint.IConstraint;
//import com.github.houbb.valid.api.api.constraint.IConstraintContext;
//import com.github.houbb.valid.api.api.validator.IValidatorEntry;
//import com.github.houbb.valid.core.api.constraint.AbstractConstraint;
//
//import java.lang.reflect.Field;
//import java.util.List;
//
///**
// * 抽象的验证明细
// * @author binbin.hou
// * @since 0.1.0
// */
//public abstract class AbstractValidatorEntry
//        extends AbstractConstraint implements IValidatorEntry {
//
//    /**
//     * 内置的实现
//     */
//    private DefaultValidatorEntry validatorEntry = new DefaultValidatorEntry();
//
//
//    @Override
//    public Object value() {
//        return validatorEntry.value();
//    }
//
//
//    @Override
//    public ICondition condition() {
//        return validatorEntry.condition();
//    }
//
//    @Override
//    public String message() {
//        return validatorEntry.message();
//    }
//
//    @Override
//    public Class[] group() {
//        return validatorEntry.group();
//    }
//
//    @Override
//    public Object instance() {
//        return validatorEntry.instance();
//    }
//
//    @Override
//    public List<Field> fieldList() {
//        return validatorEntry.fieldList();
//    }
//
//    @Override
//    protected boolean pass(IConstraintContext context, Object value) {
//        return false;
//    }
//}
