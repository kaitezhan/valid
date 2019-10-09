# 剩下的还未执行的 issues

## each

针对每一个

# 拆分 issues

将 ValidBs 中的所有实现进行细化拆分。

归结为 spi 实现。

## failType

失败模式分为以下三种：

（1）fail-fast

失败后直接返回-break

（2）fail-over

失败后继续执行，直到所有的都执行一遍。

（2）fail-throws

如果遇到异常，直接抛出异常，暂时不实现。

# message

## 抽象

对于 message 本身进行抽象。

```java
IMessage(ctx) {

    langs(); // 支持的语言列表

    message(lang);  //获取对应 lang 的消息

    default();  // 默认语言是什么。

}
```

支持 i18n 自定义。

## String 的支持

string 消息默认为当地 Lang

## message 的拆分

message = expectValue + actualValue

后者可以认为是2个具体的 message 实现。

## constraint

顺序：直接按照添加的顺序

## result

结果处理

## condition

### ValidGroup

认为是 condition 的一个特例。

可以支持以下两种模式：

contains：包含即可

equals：全部相等

