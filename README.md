# 项目介绍

java 开发中，参数校验是非常常见的需求。

但是 hibernate-validator 在使用过程中，依然会存在一些问题。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/valid/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/valid)
[![Build Status](https://www.travis-ci.org/houbb/valid.svg?branch=master)](https://www.travis-ci.org/houbb/valid?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/houbb/valid/badge.svg?branch=master)](https://coveralls.io/github/houbb/valid?branch=master)

## 变更日志

> [变更日志](doc/CHANGELOG.md)

## 特性

- 支持 fluent-validation

- 支持 jsr-303 注解

- 支持 i18n

- 支持用户自定义策略

- 支持用户自定义注解

- 支持针对属性的校验

- 支持过程式编程与注解式编程

- 支持指定校验生效的条件

## 开源地址

> [valid](https://github.com/houbb/valid)

# 创作目的

## hibernate-validator 无法满足的场景

如今 java 最流行的 hibernate-validator 框架，但是有些场景是无法满足的。

比如：

1. 验证新密码和确认密码是否相同。(同一对象下的不同属性之间关系)

2. 当一个属性值满足某个条件时，才进行其他值的参数校验。

3. 多个属性值，至少有一个不能为 null

其实，在对于多个字段的关联关系处理时，hibernate-validator 就会比较弱。

本项目结合原有的优点，进行这一点的功能强化。

## validation-api 过于复杂

validation-api 提供了丰富的特性定义，也同时带来了一个问题。

实现起来，特别复杂。

然而我们实际使用中，常常不需要这么复杂的实现。

valid-api 提供了一套简化很多的 api，便于用户自行实现。

## 自定义缺乏灵活性

hibernate-validator 在使用中，自定义约束实现是基于注解的，针对单个属性校验不够灵活。

本项目中，将属性校验约束和注解约束区分开，便于复用和拓展。

## 过程式编程 vs 注解式编程

hibernate-validator 核心支持的是注解式编程，基于 bean 的校验。

一个问题是针对属性校验不灵活，有时候针对 bean 的校验，还是要自己写判断。

本项目支持 fluent-api 进行过程式编程，同时支持注解式编程。

尽可能兼顾灵活性与便利性。

# 项目模块说明

| 模块名称 | 说明 |
|:---|:---|
| valid-api | 核心 api 及注解定义 |
| valid-core | 针对 valid-api 的核心实现 |
| valid-jsr | 针对 JSR-303 标准注解的实现 |
| valid-test | 测试代码模块 |

## 依赖说明

valid-core 默认引入 valid-api

valid-jsr 默认引入 valid-core

# 快速开始

## 准备工作

JDK1.7+

Maven 3.X+

## maven 引入

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>valid-jsr</artifactId>
    <version>0.2.2</version>
</dependency>
```

## 工具类

`ValidHelper` 校验工具类提供了最简单的使用方式。

failFast 快速失败结果;  failOver 全部验证后返回结果。

```java
User user = new User();
user.sex("what").password("old").password2("new");

ValidHelper.failOverThrow(user);
```

会抛出 ValidRuntimeException 异常，异常的信息如下：

```
name: 值 <null> 不是预期值,password: 值 <old> 不是预期值,sex: 值 <what> 不是预期值
```

其中 User 的定义如下：

```java
public class User {

    /**
     * 名称
     */
    @HasNotNull({"nickName"})
    private String name;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 原始密码
     */
    @AllEquals("password2")
    private String password;

    /**
     * 新密码
     */
    private String password2;

    /**
     * 性别
     */
    @Ranges({"boy", "girl"})
    private String sex;

    /**
     * 失败类型枚举
     */
    @EnumRanges(FailTypeEnum.class)
    private String failType;

    //Getter and Setter
}
```

## 例子

我们直接利用 jsr 内置的约束类：

```java
public void helloValidTest() {
    IResult result = ValidBs.on(null, JsrConstraints.notNullConstraint())
            .result()
            .print();
    Assert.assertFalse(result.pass());
}
```

对应日志输出为：

```
DefaultResult{pass=false, notPassList=[DefaultConstraintResult{pass=false, message='预期值为 <not null>，实际值为 <null>', value=null, constraint='NotNullConstraint', expectValue='not null'}], allList=null}
```

## 方法初步说明

ValidBs 用来进行验证的引导类，上述的写法等价于如下：

```java
public void helloValidAllConfigTest() {
    IResult result = ValidBs.on(null, JsrConstraints.notNullConstraint())
            .fail(Fails.failFast())
            .group()
            .valid(DefaultValidator.getInstance())
            .result()
            .print();
    Assert.assertFalse(result.pass());
}
```

### on(Object value, IConstraint... constraints) 指定约束

Object 可以是对象，也可以是普通的值。

constraints 为对应的约束列表，为默认的约束验证提供便利性。

IConstraint 相关创建工具类 `Constraints`、`JsrConstraints`

### fail(IFail fail)

可以指定失败时的处理策略，支持用户自定义失败策略。

| 实现 | 说明 |
|:---|:---|
| failOver | 失败后继续验证，直到验证完所有属性 |
| failFast | 失败后快速返回 |

### group(Class[] classes) 支持分组验证

有时候我们希望，只验证指定某一分组的约束。

可以通过 group() 属性指定，与 IConstraint 中的 group() 属性匹配的约束才会被执行。

### valid(IValidator validator) 支持验证策略

默认为 DefaultValidator，为 valid-api 的实现验证。

如果你希望使用 jsr-303 注解，可以使用 `JsrValidator`。

支持自定义验证策略。

### result(IResultHandler resultHandler) 验证结果处理

默认为 simple() 的简单结果处理。

可以指定为 detail() 进行详细结果处理查看。

支持用户自定义结果处理策略。

### IResult 内置方法

simple()/detail() 处理的结果为 IResult 实现类。

IResult 支持如下方法：

- print()

对结果进行打印，主要便于调试。

- throwEx()

对于参数的校验，一般都是基于异常结合 spring aop来处理的。

throwsEx 会在验证不通过时，抛出 ValidRuntimeException 异常，对应 message 为提示消息。

```java
@Test(expected = ValidRuntimeException.class)
public void resultThrowsExTest() {
    ValidBs.on(null, notNullValidatorEntry())
            .valid()
            .result()
            .throwsEx();
}
```

# 内置的属性约束

上面我们对 ValidBs 有了一个整体的了解，下面来看一看系统内置的属性约束有哪些。

每个属性约束都有对应注解。

针对单个属性，直接使用属性约束即可，灵活快捷。

针对 bean 校验，可以结合注解实现，类似于 hibernate-validator。

## valid-core

核心内置属性约束实现。

### enumRangesConstraint

枚举类指定范围约束

- 创建方式

参见工具类 `Constraints#enumRangesConstraint`

```java
/**
 * 枚举范围内约束
 * （1）当前值必须在枚举类对应枚举的 toString() 列表中。
 * @param enumClass 枚举类，不可为空
 * @return 约束类
 * @since 0.1.1
 * @see com.github.houbb.valid.core.annotation.constraint.EnumRanges 枚举类指定范围注解
 */
public static IConstraint enumRangesConstraint(final Class<? extends Enum> enumClass)
```

- 测试案例

参见测试类 EnumsRangesConstraintTest

```java
IResult result = ValidBs.on("DEFINE", Constraints.enumRangesConstraint(FailTypeEnum.class))
    .result();

Assert.assertFalse(result.pass());
```

- 说明

FailTypeEnum 是 valid-api 内置的枚举类，枚举值为 FAIL_FAST/FAIL_OVER。

只有属性值在枚举值范围内，验证才会通过。

### rangesConstraint

指定属性范围内约束

- 创建方式

参见工具类 `Constraints#rangesConstraint`

```java

 * 值在指定范围内约束
 * （1）这里为了和注解保持一致性，暂时只支持 String
 * @param strings 对象范围
 * @return 约束类
 * @since 0.1.1
 * @see com.github.houbb.valid.core.annotation.constraint.Ranges String 指定范围内注解
 */
public static IConstraint rangesConstraint(String ... strings)
```

- 测试案例

参见测试类 RangesConstraintTest

```java
IResult result = ValidBs.on("DEFINE", Constraints.rangesConstraint("FAIL_OVER",
        "FAIL_FAST"))
    .result();

Assert.assertFalse(result.pass());
```

- 说明

这个相对于枚举值，更加灵活一些。

可以根据自己的需要，指定属性的范围。

## valid-jsr

valid-jsr 中内置注解，和 jsr-303 标准一一对应，此处不再赘述。

创建方式见工具类 `JsrConstraints`，测试代码见 xxxConstraintTest。

对应列表如下：

| 属性约束 | 注解 | 简介 |
|:---|:---|:---|
| AssertFalseConstraint | @AssertFalse | 指定值必须为 false |
| AssertTrueConstraint | @AssertTrue | 指定值必须为 true |
| MinConstraint | @Min | 指定值必须大于等于最小值 |
| MaxConstraint | @Max | 指定值必须小于等于最大值 |
| DecimalMinConstraint | @DecimalMin | 指定金额必须大于等于最小值 |
| DecimalMaxConstraint | @DecimalMax | 指定金额必须小于等于最大值 |
| DigitsConstraint | @Digits | 指定值位数必须符合要求 |
| FutureConstraint | @Future | 指定日期必须在未来 |
| PastConstraint | @Past | 指定日期必须在过去 |
| PatternConstraint | @Pattern | 指定值必须满足正则表达式 |
| SizeConstraint | @Size | 指定值必须在指定大小内 |

# 自定义约束实现

## 需求

实际业务需求的是不断变化的，内置的属性约束常常无法满足我们的实际需求。

我们可以通过自定义属性，来实现自己的需求。

## 例子

参见类 DefineConstraintTest


### 自定义 notNullConstraint

notNullConstraint 对于 null 值是严格的。

所以继承自 `AbstractStrictConstraint`，如下：

```java
IResult result = ValidBs.on(null, new AbstractStrictConstraint() {
    @Override
    protected boolean pass(IConstraintContext context, Object value) {
        return value != null;
    }
}).result();

Assert.assertFalse(result.pass());
```

### 自定义 assertTrueConstraint

在 jsr-303 标准中，除却 `@NotNull` 对于 null 值都是非严格校验的。

继承自 `AbstractConstraint` 即可，如下：

```java
IConstraint assertTrueConstraint = new AbstractConstraint<Boolean>() {
    @Override
    protected boolean pass(IConstraintContext context, Boolean value) {
        return false;
    }
};

IResult nullValid = ValidBs.on(null, assertTrueConstraint)
        .result();
Assert.assertTrue(nullValid.pass());

IResult falseValid = ValidBs.on(false, assertTrueConstraint)
        .result();
Assert.assertFalse(falseValid.pass());
```

# core 模块注解验证

## 内置注解

| 注解  | 说明 |
|:----|:-----|
| @AllEquals | 当前字段及指定字段值必须全部相等 |
| @HasNotNull | 当前字段及指定字段值至少有一个不为 null |
| @EnumRanges | 当前字段值必须在枚举属性范围内 |
| @Ranges | 当前字段值必须在指定属性范围内 |

## 测试对象

- User.java

```java
public class User {

    /**
     * 名称
     */
    @HasNotNull({"nickName"})
    private String name;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 原始密码
     */
    @AllEquals("password2")
    private String password;

    /**
     * 新密码
     */
    private String password2;

    /**
     * 性别
     */
    @Ranges({"boy", "girl"})
    private String sex;

    /**
     * 失败类型枚举
     */
    @EnumRanges(FailTypeEnum.class)
    private String failType;

    //fluent getter & setter
}
```

我们限制 name/nickName 至少有一个不为空，password/password2 值要一致。

以及限定了 sex 的范围值和 failType 的枚举值。

## 测试代码

```java
User user = new User();
user.sex("what").password("old").password2("new")
    .failType("DEFINE");

IResult result = ValidBs.on(user)
        .fail(Fails.failOver())
        .result()
        .print();

Assert.assertFalse(result.pass());
```

- 日志

```
DefaultResult{pass=false, notPassList=[DefaultConstraintResult{pass=false, message='值 <null> 不是预期值', value=null, constraint='HasNotNullConstraint', expectValue=''}, DefaultConstraintResult{pass=false, message='值 <old> 不是预期值', value=old, constraint='AllEqualsConstraint', expectValue=''}, DefaultConstraintResult{pass=false, message='值 <what> 不是预期值', value=what, constraint='RangesConstraint', expectValue=''}, DefaultConstraintResult{pass=false, message='值 <DEFINE> 不是预期值', value=DEFINE, constraint='EnumRangesConstraint', expectValue=''}], allList=null}
```

# jsr 模块注解验证

## 注解

与 jsr-303 注解标准保持一致。

## 对象定义

为了演示，简单定义如下：

- JsrUser.java

```java
public class JsrUser {

    @Null
    private Object nullVal;

    @NotNull
    private String notNullVal;

    @AssertFalse
    private boolean assertFalse;

    @AssertTrue
    private boolean assertTrue;

    @Pattern(regexp = "[123456]{2}")
    private String pattern;

    @Size(min = 2, max = 5)
    private String size;

    @DecimalMax("12.22")
    private BigDecimal decimalMax;

    @DecimalMin("1.22")
    private BigDecimal decimalMin;

    @Min(10)
    private long min;

    @Max(10)
    private long max;

    @Past
    private Date past;

    @Future
    private Date future;

    @Digits(integer = 2, fraction = 4)
    private Long digits;

    //fluent getter and setter
}
```

## 测试代码

参见测试类 `ValidBsJsrBeanTest`

```java
public void beanFailTest() {
    Date future = DateUtil.getFormatDate("90190101", DateUtil.PURE_DATE_FORMAT);
    Date past = DateUtil.getFormatDate("20190101", DateUtil.PURE_DATE_FORMAT);

    JsrUser jsrUser = new JsrUser();
    jsrUser.assertFalse(true)
            .assertTrue(false)
            .decimalMin(new BigDecimal("1"))
            .decimalMax(new BigDecimal("55.55"))
            .min(5)
            .max(20)
            .digits(333333L)
            .future(past)
            .past(future)
            .nullVal("123")
            .notNullVal(null)
            .pattern("asdfasdf")
            .size("22222222222222222222");

    IResult result = ValidBs.on(jsrUser)
            .fail(Fails.failOver())
            .valid(JsrValidator.getInstance())
            .result()
            .print();

    Assert.assertFalse(result.pass());
}
```

- 日志

```
DefaultResult{pass=false, notPassList=[DefaultConstraintResult{pass=false, message='值必须为空', value=123, constraint='NullConstraint', expectValue='null'}, DefaultConstraintResult{pass=false, message='值必须为非空', value=null, constraint='NotNullConstraint', expectValue='not null'}, DefaultConstraintResult{pass=false, message='值必须为假', value=true, constraint='AssertFalseConstraint', expectValue='false'}, DefaultConstraintResult{pass=false, message='值必须为真', value=false, constraint='AssertTrueConstraint', expectValue='true'}, DefaultConstraintResult{pass=false, message='值必须满足正则表达式', value=asdfasdf, constraint='PatternConstraint', expectValue='必须匹配正则表达式 [123456]{2}'}, DefaultConstraintResult{pass=false, message='值必须为在指定范围内', value=22222222222222222222, constraint='SizeConstraint', expectValue='大小必须在范围内 [2, 5]'}, DefaultConstraintResult{pass=false, message='值必须小于金额最大值', value=55.55, constraint='DecimalMaxConstraint', expectValue='小于等于 12.22'}, DefaultConstraintResult{pass=false, message='值必须大于金额最小值', value=1, constraint='DecimalMinConstraint', expectValue='大于等于 1.22'}, DefaultConstraintResult{pass=false, message='值必须大于最小值', value=5, constraint='MinConstraint', expectValue='大于等于 10'}, DefaultConstraintResult{pass=false, message='值必须小于最大值', value=20, constraint='MaxConstraint', expectValue='小于等于 10'}, DefaultConstraintResult{pass=false, message='时间必须在过去', value=Fri Jan 01 00:00:00 CST 9019, constraint='PastConstraint', expectValue='小于等于 Sun Oct 13 12:12:07 CST 2019'}, DefaultConstraintResult{pass=false, message='时间必须在未来', value=Tue Jan 01 00:00:00 CST 2019, constraint='FutureConstraint', expectValue='大于等于 Sun Oct 13 12:12:07 CST 2019'}, DefaultConstraintResult{pass=false, message='值必须满足位数', value=333333, constraint='DigitsConstraint', expectValue='整数位数 [2], 小数位数 [4]'}], allList=null}
```

# @Valid 递归属性验证

## 需求

有时候我们一个对象中，会引入其他子对象。

我们希望对子对象也进行相关属性的验证，这时候就可以使用 `@Valid` 注解。

该注解为 jsr-303 标准注解。

## 对象定义

```java
public class ValidUser {

    /**
     * 子节点
     */
    @Valid
    private User user;

    //fluent setter & getter

}
```

## 测试代码

参见测试类 `ValidBsValidBeanTest`

```java
public void beanFailTest() {
    User user = new User();
    user.sex("default").password("old").password2("new")
            .failType("DEFINE");

    ValidUser validUser = new ValidUser();
    validUser.user(user);

    IResult result = ValidBs.on(validUser)
            .fail(Fails.failOver())
            .result()
            .print();

    Assert.assertFalse(result.pass());
}
```

- 日志信息

```
DefaultResult{pass=false, notPassList=[DefaultConstraintResult{pass=false, message='值 <null> 不是预期值', value=null, constraint='HasNotNullConstraint', expectValue=''}, DefaultConstraintResult{pass=false, message='值 <old> 不是预期值', value=old, constraint='AllEqualsConstraint', expectValue=''}, DefaultConstraintResult{pass=false, message='值 <default> 不是预期值', value=default, constraint='RangesConstraint', expectValue=''}, DefaultConstraintResult{pass=false, message='值 <DEFINE> 不是预期值', value=DEFINE, constraint='EnumRangesConstraint', expectValue=''}], allList=null}
```

## 自引用问题

有时候我们可能会引用自身，这个也做了测试，是符合预期的。

参见 `ValidBsSelfValidBeanTest`

# i18n 支持

## 需求

不同国家对于语言的要求肯定也不同。

本项目目前支持中文/英文国际化支持，默认以当前地区编码为准，如果不存在，则使用英文。

感觉其他语言，暂时使用中没有用到。（个人也不会，错了也不知道。暂时不添加）

## 指定为英文

测试代码参加 `ValidBsI18NTest`

```java
public void i18nEnTest() {
    Locale.setDefault(Locale.ENGLISH);
    IResult result = ValidBs.on(null, JsrConstraints.notNullConstraint())
            .result()
            .print();

    Assert.assertEquals("Expect is <not null>, but actual is <null>.", result.notPassList().get(0).message());
}
```

## 指定为中文

```java
public void i18nZhTest() {
    Locale.setDefault(Locale.CHINESE);
    IResult result = ValidBs.on(null, JsrConstraints.notNullConstraint())
            .result()
            .print();

    Assert.assertEquals("预期值为 <not null>，实际值为 <null>", result.notPassList().get(0).message());
}
```

# IFail 失败策略接口详解

## 需求

对于不符合约束条件的处理方式，主要有以下两种：

- failFast

快速失败。遇到一个约束不符合条件，直接返回。

优点：耗时较短。

- failOver

全部验证，将所有的属性都验证一遍。

优点：可以一次性获得所有失败信息。

## 创建方式

参见工具类 `Fails`，返回的实例为单例，且线程安全。

## 测试代码

参见测试类 `ValidBsFailTest`

### failFast

我们指定要求属性值长度最小为3，且必须满足正则表达式。

```java
IResult result = ValidBs.on("12", JsrConstraints.sizeConstraintMin(3),
        JsrConstraints.patternConstraint("[678]{3}"))
        .fail(Fails.failFast())
        .result()
        .print();

Assert.assertEquals(1, result.notPassList().size());
```

- 日志

采用快速失败模式，只有一个失败验证结果。

```
DefaultResult{pass=false, notPassList=[DefaultConstraintResult{pass=false, message='预期值为 <必须匹配正则表达式 [678]{3}>，实际值为 <12>', value=12, constraint='PatternConstraint', expectValue='必须匹配正则表达式 [678]{3}'}], allList=null}
```

### failOver

保持其他部分不变，我们调整下失败处理策略。

```java
IResult result = ValidBs.on("12", JsrConstraints.sizeConstraintMin(3),
        JsrConstraints.patternConstraint("[678]{3}"))
        .fail(Fails.failOver())
        .result()
        .print();

Assert.assertEquals(2, result.notPassList().size());
```

- 日志

此时失败处理结果为2，日志如下：

```
DefaultResult{pass=false, notPassList=[DefaultConstraintResult{pass=false, message='预期值为 <必须匹配正则表达式 [678]{3}>，实际值为 <12>', value=12, constraint='PatternConstraint', expectValue='必须匹配正则表达式 [678]{3}'}, DefaultConstraintResult{pass=false, message='预期值为 <大小必须在范围内 [3, 2147483647]>，实际值为 <2>', value=12, constraint='SizeConstraint', expectValue='大小必须在范围内 [3, 2147483647]'}], allList=null}
```

# IValidator 验证策略接口详解

## 需求

为了便于集成不同框架的测试验证，本框架支持 IValidator。

同时也允许用户自定义自己的实现方式。

## 默认验证器策略-DefaultValidator

指定 valid 对应的验证器，通过 `ValidBs.valid(IValidator)` 方法指定。

默认为 DefaultValidator。

该验证策略，支持符合 valid-api 的内置注解，及用户自定义注解。

## JSR-303 验证器策略-JsrValidator

JsrValidator 支持 jsr-303 标准注解，及 valid-api 标准的相关注解实现和约束实现。

- 使用方式

通过 valid 方法指定即可。

```java
IResult result = ValidBs.on(jsrUser)
                .valid(JsrValidator.getInstance())
                .result()
                .print();
```

## 自定义验证器策略

如果你想添加自己的实现，直接实现 IValidator，并且在 valid() 中指定即可。

可以参考 DefaultValidator，建议继承自 `AbstractValidator`。

# IResultHandler 结果处理策略接口详解

## 需求

对于验证的结果，不同的场景，需求也各不相同。

你可能有如下需求：

（1）输出验证失败的信息

（2）输出所有验证信息

（3）针对验证失败的信息抛出异常

（4）对验证结果进行自定义处理。

为了满足上述需求，提供了如下的接口，及内置默认实现。

## 接口

```java
public interface IResultHandler<T> {

    /**
     * 对约束结果进行统一处理
     * @param constraintResultList 约束结果列表
     * @return 结果
     */
    T handle(final List<IConstraintResult> constraintResultList);

}
```

如果你想自定义处理方式，实现此接口。

并在 `ValidBs.result(IResultHandler)` 方法中指定使用即可。

## 简单实现

- 说明

仅仅对没有通过测试的验证结果进行保留。

- 测试代码

参见测试代码 `ValidBsResultHandlerTest`

```java
ValidBs.on("12", JsrConstraints.sizeConstraintMin(2))
        .result(ResultHandlers.simple())
        .print();
```

- 日志

```
DefaultResult{pass=true, notPassList=[], allList=null}
```

## 详细实现

- 说明

保留所有验证结果信息，包含通过验证测试的明细信息。

- 测试代码

参见测试代码 `ValidBsResultHandlerTest`

```java
ValidBs.on("12", JsrConstraints.sizeConstraintMin(2))
        .result(ResultHandlers.detail())
        .print();
```

- 测试日志

```
DefaultResult{pass=true, notPassList=[], allList=[DefaultConstraintResult{pass=true, message='null', value=12, constraint='SizeConstraint', expectValue='null'}]}
```

# IResult 结果接口详解

## 说明

IResult 为验证结果处理的内置实现接口。

拥有以下常见方法：

| 方法 | 说明 |
|:---|:---|
| pass() | 是否通过验证 |
| notPassList() | 未通过验证的列表 |
| allList() | 所有验证的列表 |
| print() | 控台输出验证结果 |
| throwsEx() | 针对未通过验证的信息抛出 ValidRuntimeException |

## 测试代码

```java
@Test(expected = ValidRuntimeException.class)
public void methodsTest() {
    IResult result = ValidBs.on("12", JsrConstraints.sizeConstraintMin(3))
            .result(ResultHandlers.detail())
            .print()
            .throwsEx();

    Assert.assertFalse(result.pass());
    Assert.assertEquals(1, result.notPassList().size());
    Assert.assertEquals(1, result.allList().size());
}
```

- 日志

```
DefaultResult{pass=false, notPassList=[DefaultConstraintResult{pass=false, message='预期值为 <大小必须在范围内 [3, 2147483647]>，实际值为 <2>', value=12, constraint='SizeConstraint', expectValue='大小必须在范围内 [3, 2147483647]'}], allList=[DefaultConstraintResult{pass=false, message='预期值为 <大小必须在范围内 [3, 2147483647]>，实际值为 <2>', value=12, constraint='SizeConstraint', expectValue='大小必须在范围内 [3, 2147483647]'}]}
```

# IConstraint 约束接口详解

## 需求

Hibernate-validator 主要是基于注解的 Bean 验证，所以将注解和实现耦合在了一起。

Valid 作为一个 fluent-api 验证框架，支持过程式编程，所以将针对属性验证的约束独立出来，便于复用。

## 接口说明

```java
public interface IConstraint {

    /**
     * 触发约束规则
     * @param context 上下文
     * @return 结果
     * @since 0.0.3
     */
    IConstraintResult constraint(final IConstraintContext context);

}
```

## 自定义说明

前面的例子已经演示了如何自定义实现。

直接实现上述接口也可以，建议继承 `AbstractConstraint` 等内置的各种约束抽象类。

# IValidEntry 验证明细接口详解

## 说明

当我们将 IConstraint 独立出来时，同时有下面的一些问题：

（1）如何指定对应 message

（2）如何指定约束生效条件 condition

（3）如何指定约束的分组信息 group

IValidEntry 接口就是为了解决这些问题，在 IConstraint 的基础之上进行一系列的功能增强。

## 使用方式

测试代码，参见类 `ValidBsValidEntryTest`

```java
IValidEntry validEntry = ValidEntry.of(JsrConstraints.notNullConstraint());

IResult result = ValidBs.on(null, validEntry)
    .result()
    .print();

Assert.assertFalse(result.pass());
```

## message() 自定义提示消息

我们可以自定义改约束条件的提示消息。

```java
final IValidEntry validEntry = ValidEntry.of(JsrConstraints.notNullConstraint())
        .message("自定义：指定值不能为空");

IResult result = ValidBs.on(null, validEntry)
        .valid()
        .result();

Assert.assertEquals("自定义：指定值不能为空", result.notPassList().get(0).message());
```

## group() 分组验证

### 需求

有时候我们希望只验证某一种分组的约束条件。

### 测试代码

按照如下方式制定，只有当 ValidEntry 的 group 信息与 ValidBs.group() 符合时，才会被执行。

```java
final IValidEntry firstEntry = ValidEntry.of(JsrConstraints.sizeConstraint(5, 10))
        .group(String.class);

final IValidEntry otherEntry = ValidEntry.of(JsrConstraints.sizeConstraint(3, 20))
        .group(Integer.class);

IResult result = ValidBs
        .on("12", firstEntry, otherEntry)
        .fail(Fails.failOver())
        .group(String.class)
        .result();

Assert.assertEquals(1, result.notPassList().size());
```

### condition 拓展

其实可以 group() 只是 condition 的一个特例。

后续将实现 ICondition 接口的相关内置支持，和 `@Condition` 注解的相关支持。

# 自定义注解

## 需求

说到 hibernate-validator，个人觉得最灵魂的设计就是支持用户自定义注解了。

注解使得使用便利，自定义注解同时保证了灵活性。

下面来看看，如何实现自定义注解。

## 核心设计理念

你可以认为内置注解也是一种自定义注解。

本框架的所有实现理念都是如此，可以认为所有的内置实现，都是可以被替换的。

## @AllEquals 注解解析

我们以 `@AllEquals` 注解为例，

### 注解内容

```java
@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(AtAllEqualsConstraint.class)
public @interface AllEquals {

    /**
     * 当前字段及其指定的字段 全部相等
     * 1. 字段类型及其他字段相同
     * @return 指定的字段列表
     */
    String[] value();

    /**
     * 提示消息
     * @return 错误提示
     */
    String message() default "";

    /**
     * 分组信息
     * @return 分组类
     * @since 0.1.2
     */
    Class[] group() default {};

}
```

其中 group()/message() 和 IValidEntry 中的方法一一对应。

当然你设计的注解中如果没有这两个方法也没关系，建议提供这两个属性。

## 注解与约束的关系

`@Constraint(AtAllEqualsConstraint.class)` 这个注解指定了当前注解与对应的约束实现，是最核心的部分。

### @Constraint 注解

```java
@Inherited
@Documented
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Constraint {

    /**
     * 约束条件实现类
     * @return 实现类 class
     */
    Class<? extends IAnnotationConstraint> value();

}
```

### IAnnotationConstraint 接口

这个就是注解相关的约束接口，内容如下：

```java
/**
 * 注解约束规则接口
 * 注意：所有的实现类都需要提供无参构造函数。
 * @author binbin.hou
 * @since 0.0.9
 */
public interface IAnnotationConstraint<A extends Annotation> extends IConstraint {

    /**
     * 初始化映射关系
     * @param annotation 注解信息
     * @since 0.0.9
     */
    void initialize(A annotation);

}
```

# 后期特性

- 丰富 IConstraintResult 特性

- 优化 IResult 使用体验

- @Condition 注解支持和 ICondition 的支持。

- 集成  hibernate-validator 校验

# 参考项目

## JSR 标准

[JSR 380](https://www.jcp.org/en/jsr/detail?id=380)

[JSR 303](https://beanvalidation.org/1.0/spec/)

[bean validation 2.0](https://beanvalidation.org/2.0/)

# ROAD-MAP

- [ ] springboot 整合

- [ ] 更多注解

phone

idNo

银行卡

- [ ] i18N 對應的描述信息
