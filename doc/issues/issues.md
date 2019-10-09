# 剩下的还未执行的 issues

# each

# message-i18n

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

# AbstractConstraint

优化改进，使得其更加适合开发者实现自己的代码。


