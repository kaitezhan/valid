package com.github.houbb.valid.core.bs;

import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.api.api.constraint.IConstraintResult;
import com.github.houbb.valid.api.api.fail.IFail;
import com.github.houbb.valid.api.api.result.IResult;
import com.github.houbb.valid.api.api.result.IResultHandler;
import com.github.houbb.valid.api.api.validator.IValidator;
import com.github.houbb.valid.api.api.validator.IValidatorContext;
import com.github.houbb.valid.api.api.validator.IValidatorEntry;
import com.github.houbb.valid.core.api.fail.Fails;
import com.github.houbb.valid.core.api.result.ResultHandlers;
import com.github.houbb.valid.core.api.validator.DefaultValidator;
import com.github.houbb.valid.core.api.validator.context.DefaultValidatorContext;
import com.github.houbb.valid.core.api.validator.entry.DefaultValidatorEntry;
import com.github.houbb.valid.core.api.validator.entry.ValidatorEntryFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * valid 引导类

 * <pre>
 * ValidBs
 * .on(value)
 * .result()
 * .print();
 * </pre>
 *
 * 所有的校验使用 lazy 模式，在 result() 调用时才进行调用。
 *
 * @author binbin.hou
 * @since 0.0.2
 */
public final class ValidBs {

    private ValidBs(){}

    /**
     * 失败模式
     * （1）默认为快速失败
     * @since 0.0.7
     */
    private IFail fail = Fails.failFast();

    /**
     * 验证组信息
     * （1）如果不指定，则说明验证所有约束条件
     * （2）如果指定列表，则只验证符合当前 group 的约束条件。
     * @since 0.1.0
     */
    private Class[] group;

    /**
     * 用户自定义验证列表
     * @since 0.1.0
     */
    private List<IValidatorEntry> validatorEntries;

    /**
     * 对象信息
     * @since 0.1.0
     */
    private Object value;

    /**
     * 约束验证结果列表
     * @since 0.1.0
     */
    private List<IConstraintResult> constraintResults;

    /**
     * 是否已经执行验证
     * @since 0.1.0
     */
    private volatile boolean validated = false;

    /**
     * 指定验证的相关信息
     * （1）可以验证对象，也可以验证属性
     * （2）如果验证为 bean，会同时验证指定约束规则和注解相关约束规则。
     * @param value 对象信息
     * @param validatorEntries 验证信息明细
     * @return this
     * @since 0.1.0
     */
    public static ValidBs on(final Object value,
                             final IValidatorEntry ... validatorEntries) {
        List<IValidatorEntry> validatorEntryList = ArrayUtil.arrayToList(validatorEntries);
        return on(value, validatorEntryList);
    }

    /**
     * 指定验证的相关信息
     * （1）可以验证对象，也可以验证属性
     * （2）如果验证为 bean，会同时验证指定约束规则和注解相关约束规则。
     * @param value 对象信息
     * @param validatorEntries 验证信息明细
     * @return this
     * @since 0.1.0
     */
    private static ValidBs on(final Object value,
                             final Collection<? extends IValidatorEntry> validatorEntries) {
        ValidBs validBs = new ValidBs();
        validBs.validated = false;
        validBs.value = value;
        validBs.validatorEntries = buildValidatorEntries(value, validatorEntries);
        return validBs;
    }

    /**
     * 指定验证的相关约束信息
     * （1）可以验证对象，也可以验证属性
     * （2）如果验证为 bean，会同时验证指定约束规则和注解相关约束规则。
     *
     * 这里必须至少指定一个 constraint，否则都没有参数时，二者会混淆。
     * @param value 对象信息
     * @param constraint 约束实现
     * @param constraints 约束实现列表
     * @return this
     * @since 0.1.0
     */
    public static ValidBs on(final Object value,
                             final IConstraint constraint,
                             final IConstraint... constraints) {
        List<IConstraint> constraintList = ArrayUtil.arrayToList(constraints);
        constraintList.add(constraint);

        List<IValidatorEntry> validatorEntryList = CollectionUtil.toList(constraintList,
                new IHandler<IConstraint, IValidatorEntry>() {
                    @Override
                    public IValidatorEntry handle(IConstraint constraint) {
                        return ValidatorEntryFactory.of(constraint);
                    }
                });

        return on(value, validatorEntryList);
    }

    /**
     * 构建对应的验证明细列表
     * @param validatorEntryList 验证明细列表
     * @param value 元素信息
     * @return 列表
     * @since 0.1.0
     */
    private static List<IValidatorEntry> buildValidatorEntries(final Object value,
                                                               final Collection<? extends IValidatorEntry> validatorEntryList) {
        if(CollectionUtil.isEmpty(validatorEntryList)) {
            return Collections.emptyList();
        }

        return CollectionUtil.toList(validatorEntryList, new IHandler<IValidatorEntry, IValidatorEntry>() {
            @Override
            public IValidatorEntry handle(IValidatorEntry validatorEntry) {
                ((DefaultValidatorEntry)validatorEntry).value(value);
                return validatorEntry;
            }
        });
    }

    /**
     * 指定失败类型
     * @param fail 失败类型实现
     * @return this
     * @since 0.0.3
     */
    public ValidBs fail(final IFail fail) {
        ArgUtil.notNull(fail, "fail");
        this.fail = fail;
        return this;
    }

    /**
     * 设置验证组列表信息
     * （1）理论上这里可以为空
     * （2）允许用户多次反复定义。
     * @param group 验证组信息，禁止为空。
     * @return this
     * @since 0.0.5
     */
    public ValidBs group(Class ... group) {
        this.group = group;
        return this;
    }

    /**
     * 验证
     * （1）指定验证的验证器实现
     * （2）处理的结果保留在 result 结果中。
     * （3）设置是否验证标志为 true
     * @param validators 验证器
     * @return this
     * @since 0.1.0
     */
    public ValidBs valid(final IValidator... validators) {
        // 验证上下文构建
        IValidatorContext context = DefaultValidatorContext.newInstance()
                .fail(fail)
                .group(group)
                .value(value)
                .validatorEntries(validatorEntries);

        // 执行
        if(ArrayUtil.isEmpty(validators)) {
            this.constraintResults = Instances.singleton(DefaultValidator.class).valid(context);
        } else {
            this.constraintResults = Guavas.newArrayList();
            // 循环调用
            for(IValidator validator : validators) {
                List<IConstraintResult> resultList = validator.valid(context);
                if(CollectionUtil.isNotEmpty(resultList)) {
                    this.constraintResults.addAll(resultList);
                }
            }
        }

        this.validated = true;
        return this;
    }

    /**
     * 对信息进行校验
     * （1）结合 {@link #fail} 失败模式
     * （2）如果没有验证，则使用默认验证方式，验证一次。
     * @param resultHandler 结果处理方式
     * @return 结果
     * @since 0.0.2
     * @param <T> 自定义处理结果泛型
     */
    public <T> T result(final IResultHandler<T> resultHandler) {
        ArgUtil.notNull(resultHandler, "resultHandler");

        // 对结果进行处理
        if(!validated) {
            this.valid();
        }
        return resultHandler.handle(this.constraintResults);
    }

    /**
     * 获取结果
     * @return 结果
     * @since 0.0.2
     */
    public IResult result() {
        return this.result(ResultHandlers.simple());
    }

}
