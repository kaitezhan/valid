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

# 创作目的

## hibernate-validator 无法满足的场景

如今 java 最流行的 hibernate-validator 框架，但是有些场景是无法满足的。

比如：

1. 验证新密码和确认密码是否相同。(同一对象下的不同属性之间关系)

2. 当一个属性值满足某个条件时，才进行其他值的参数校验。

3. 多个属性值，至少有一个不能为 null

其实，在对于多个字段的关联关系处理时，hibernate-validator 就会比较弱。

本项目结合原有的优点，进行这一点的功能强化。

## 自定义缺乏灵活性

hibernate-validator 在使用中，自定义约束实现是基于注解的，针对单个属性校验不够灵活。

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
    <artifactId>valid-core</artifactId>
    <version>${project.version}</version>
</dependency>
```

## 例子

我们继承 `AbstractStrictConstraint`，实现了一个验证值不为 null 的约束。

```java
public void simpleTest() {
    ValidBs.on(null, new AbstractStrictConstraint() {
        @Override
        protected boolean pass(IConstraintContext context, Object value) {
            return value != null;;
        }
    }).result().print();
}
```

- 日志信息

```
DefaultResult{pass=false, notPassList=[DefaultConstraintResult{pass=false, message='值 <null> 不是预期值', value=null, constraint='', expectValue=''}], allList=null}
```

# 使用内置的约束类

## 内置约束类

在开始的例子中，我们自己定义了一个值非空约束类。

在 valid-jsr 模块中，提供了与标准注解对应的约束实现类。

便于用户使用和组合拓展。

## maven 引入

我们可以引入如下：

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>valid-core</artifactId>
    <version>${project.version}</version>
</dependency>
```

## 使用内置约束

我们直接利用 jsr 内置的约束类，改写我们的代码：

```java
public void notNullNotPassTest() {
    IResult result = ValidBs.on(null, JsrConstraints.notNullConstraint())
            .valid()
            .result()
            .print();
    Assert.assertFalse(result.pass());
}
```

对应日志输出为：

```
DefaultResult{pass=false, notPassList=[DefaultConstraintResult{pass=false, message='预期值为 <not null>，实际值为 <null>', value=null, constraint='NotNullConstraint', expectValue='not null'}], allList=null}
```

# ValidBs 引导类说明

## 支持方法如下

```java
public void allConfigTest() {
    ValidBs.on("123", JsrConstraints.notNullConstraint())
            .fail(Fails.failOver())
            .group()
            .valid(DefaultValidator.getInstance())
            .result(ResultHandlers.simple())
            .print();
}
```

## on(Object,IValidatorEntry ... validatorEntries) 指定约束明细

Object 可以是对象，也可以是普通的值。

validatorEntries 为对应的验证约束列表。

- 例子

```java

```

## on(Object, IConstraint... constraints) 指定约束

Object 可以是对象，也可以是普通的值。

constraints 为对应的约束列表，为默认的约束验证提供便利性。

IConstraint 相关创建工具类 `Constraints`、`JsrConstraints`

## fail(IFail)

可以指定失败时的处理策略，支持用户自定义失败策略。

| 实现 | 说明 |
|:---|:---|
| failOver | 失败后继续验证，直到验证完所有属性 |
| failFast | 失败后快速返回 |

## group() 支持分组验证

有时候我们希望，只验证指定某一分组的约束。

可以通过 group() 属性指定，与 IConstraint 中的 group() 属性匹配的约束才会被执行。

## valid() 支持验证策略

默认为 DefaultValidator，为 valid-api 的实现验证。

如果你希望使用 jsr-303 注解，可以使用 `JsrValidator`。

## result() 支持验证结果处理

默认为 simple() 的简单结果处理。

可以指定为 detail() 进行详细结果处理查看。

# IConstraint 约束接口详解

# IValidatorEntry 验证明细接口详解

# IFail 失败策略接口详解

# IValidator 验证策略接口详解

# IResultHandler 结果处理策略接口详解

# valid-core 核心约束

## 自定义

# valid-core 核心注解

## 自定义

# valid-jsr 标准约束

# valid-jsr 标准注解

# 分组验证

# i18n 支持

