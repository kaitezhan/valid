# 项目介绍

java 开发中，参数校验是非常常见的需求。

如今 java 最流行的 hibernate-validator 框架，但是有些场景是无法满足的。

比如：

1. 验证新密码和确认密码是否相同。(同一对象下的不同属性之间关系)

2. 当一个属性值满足某个条件时，才进行其他值的参数校验。

3. 多个属性值，至少有一个不能为 null

其实，在对于多个字段的关联关系处理时，hibernate-validator 就会比较弱。

本项目结合原有的优点，进行这一点的功能强化。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/valid/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/valid)
[![Build Status](https://www.travis-ci.org/houbb/valid.svg?branch=master)](https://www.travis-ci.org/houbb/valid?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/houbb/valid/badge.svg?branch=master)](https://coveralls.io/github/houbb/valid?branch=master)

> [变更日志](doc/CHANGE_LOG.md)
