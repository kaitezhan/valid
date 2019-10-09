# 项目介绍

java 开发中，参数校验是非常常见的需求。

但是 hibernate-validator 在使用过程中，依然会存在一些问题。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/valid/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/valid)
[![Build Status](https://www.travis-ci.org/houbb/valid.svg?branch=master)](https://www.travis-ci.org/houbb/valid?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/houbb/valid/badge.svg?branch=master)](https://coveralls.io/github/houbb/valid?branch=master)

## 变更日志

> [变更日志](doc/CHANGELOG.md)

# 创作目的

## hibernate-validator 无法满足的场景

如今 java 最流行的 hibernate-validator 框架，但是有些场景是无法满足的。

比如：

1. 验证新密码和确认密码是否相同。(同一对象下的不同属性之间关系)

2. 当一个属性值满足某个条件时，才进行其他值的参数校验。

3. 多个属性值，至少有一个不能为 null

其实，在对于多个字段的关联关系处理时，hibernate-validator 就会比较弱。

本项目结合原有的优点，进行这一点的功能强化。

## 性能没有做到极致

hibernate-validator 基于运行时注解实现，便利性确实非常棒，但是性能并没有达到最高境界。

本项目主要想通过 java 编译时注解，解决性能问题。

## 注解的冗余性

hibernate-validator 的注解在设计之初没有进行单独的分离。

所以本框架将 hibernate-validator 中的注解单独提取出来，统一放在中央仓库，便于开发者只引入注解相关。

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

基于 fluent-api 的验证实现。

## 例子

```java
public void simpleTest() {
    ValidBs.newInstance().on(null, new AbstractStrictConstraint() {
        @Override
        protected boolean pass(IConstraintContext context, Object value) {
            return ObjectUtil.isNotNull(value);
        }
    }).result().print();
}
```

- 日志信息

```
DefaultResult{pass=false, notPassList=[DefaultConstraintResult{pass=false, message='Value <null> is not expected.', value=null, constraint='', expectValue=''}], allList=null}
```
