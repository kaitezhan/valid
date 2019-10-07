package com.github.houbb.valid.core.api.constraint.chain;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.support.pipeline.impl.DefaultPipeline;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.valid.api.api.constraint.IConstraint;
import com.github.houbb.valid.api.api.constraint.IConstraintContext;
import com.github.houbb.valid.api.api.constraint.IConstraintResult;
import com.github.houbb.valid.api.constant.enums.FailTypeEnum;
import com.github.houbb.valid.core.api.constraint.AbstractConstraint;
import com.github.houbb.valid.core.constant.ContextAttrKeyConst;

import java.util.List;

/**
 * 抽象约束链实现
 * @author binbin.hou
 * @since 0.0.4
 */
@ThreadSafe
public abstract class AbstractConstraintChain extends AbstractConstraint {

    @Override
    protected boolean pass(IConstraintContext context, Object value) {
        boolean passFlag = true;

        Pipeline<IConstraint> pipeline = new DefaultPipeline<>();
        this.init(pipeline, context);

        //执行
        final List<IConstraint> constraintList = pipeline.list();
        // 预期结果列表
        List<String> expectValueList = Guavas.newArrayList();

        for(IConstraint constraint : constraintList) {
            // 失败模式
            IConstraintResult constraintResult = constraint.constraint(context);

            if(!constraintResult.pass()) {
                // TODO: 这里可以优化，添加预期值属性。
                expectValueList.add(constraintResult.message());

                passFlag = false;
            }
            // 快速失败模式
            if(FailTypeEnum.FAIL_FAST.equals(context.failType())
                && !constraintResult.pass()) {
                break;
            }
        }
        // 将构建好的信息放在 context
        context.putAttr(ContextAttrKeyConst.SYS_CONSTRAINT_CTX_EXPECT_VALUE,
                StringUtil.join(expectValueList, PunctuationConst.OR));

        // 返回结果
        return passFlag;
    }

    @Override
    protected String expectValue(IConstraintContext context) {
        // 将不符合的验证结果，联合之后放在这里
        return (String) context.getAttr(ContextAttrKeyConst.SYS_CONSTRAINT_CTX_EXPECT_VALUE);
    }

    /**
     * 初始化监听器列表
     * @param pipeline 泳道
     * @param context 重试信息
     * @since 0.0.4
     */
    protected abstract void init(final Pipeline<IConstraint> pipeline,
                                 final IConstraintContext context);

}
