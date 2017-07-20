# SMN SDK

OTC OpenStack4j Simple Message Notification SDK
- 服务入口: `osclient.notification()`
- 服务类型: `notification`


## API接口文档

请查阅 [官方接口文档](https://docs.otc.t-systems.com/en-us/api/smn/en-us_topic_0036017316.html)
  
##  Topic
### 创建Topic
```java
Topic topic = osclient.notification().topics().create("topic-name", "display-name");
```

### 更新Topic
```java
String topicUrn = "topic-urn";
String displayName = "new-display-name";
TracableRequest updated = osclient.notification().topics().updateDisplayName(topicUrn, displayName);
```

### 删除Topic
```java
String topicUrn = "topic-urn";
TracableRequest request = osclient.notification().topics().delete(topicUrn);
```

### 查询Topic列表
```java
List<? extends Topic> topics = osclient.notification().topics().list(100, 0);
```

### 查询Topic详情
```java
String topicUrn = "topic-urn";
Topic get = osclient.notification().topics().get(topicUrn);
```

### 查询Topic属性
```java
String topicUrn = "topic-urn";
TopicAttributes attrs = osclient.notification().topics().getTopicAttributes(topicUrn);
System.out.println(attrs.getIntroduction());
```

### 查询Topic单个属性
```java
String topicUrn = "topic-urn";
String attr = osclient.notification().topics().getTopicAttribute(topicUrn, TopicAttributeName.Introduction);
```

### 更新Topic属性
```java
String topicUrn = "topic-urn";
TracableRequest request = osclient.notification().topics()
							.updateTopicAttribute(topicUrn, TopicAttributeName.Introduction, "sdk-unittest");
```

### 删除指定名称的Topic属性
```java
String topicUrn = "topic-urn";
TracableRequest request = osclient.notification().topics()
							.deleteTopicAttribute(topicUrn, TopicAttributeName.Introduction);
```

### 删除所有Topic属性
```java
String topicUrn = "topic-urn";
TracableRequest request = osclient.notification().topics().deleteTopicAttributes(topicUrn);
```


## 订阅
### 查询订阅者列表
```java
List<? extends Subscription> subscriptions = osclient.notification().subscriptions().list(100, 0);
```

### 查询指定主题的订阅者列表
```java
List<? extends Subscription> subscriptions = osclient.notification().subscriptions().listByTopic("topic-urn", 100, 0);
```

### 订阅
```java
SubscriptionCreate subscribe = SubscriptionCreate.builder().topicUrn("topic-urn").endpoint("xx@xx.com")
				.protocol(Protocol.EMAIL).remark("sdk-unittest").build();
Subscription subscription = osclient.notification().subscriptions().subscribe(subscribe);
```

### 取消订阅
```java
TracableRequest unsubscribe = osclient.notification().subscriptions().unsubscribe("subscription-urn");
```

## 消息模板
### 创建消息模板
```java
MessageTemplateCreate create = MessageTemplateCreate.builder().name("template-name").protocol(Protocol.EMAIL)
				.locale("zh-cn").content("content").build();
MessageTemplate	template = osclient.notification().messageTemplates().create(create);
```

### 更新消息模板
```java
TracableRequest update = osclient.notification().messageTemplates().updateContent("message-template-id",
				"Hello, {user}");
```

### 删除消息模板
```java
TracableRequest delete = osclient.notification().messageTemplates().delete("message-template-id");
```

### 查询消息模板列表
```java
MessageTemplateListOptions options = MessageTemplateListOptions.create().limit(1).offset(0).name("template-name")
				.protocol(Protocol.EMAIL);
List<? extends MessageTemplate> templates = osclient.notification().messageTemplates().list(options);
```

### 查询消息模板详情
```java
MessageTemplate get = osclient.notification().messageTemplates().get("message-template-id");
```

## 消息发布
### 消息发布
```java
MessageIdResponse message = osclient.notification().messages().publish("topic-urn", "subject", "message-content");
```

### 使用消息结构体方式的消息发布
```java
StructuredMessage structuredMessage = StructuredMessage.builder().subject("hello")
				.defaultMessage("hello, there").emailMessage("hello, email").build();
MessageIdResponse message = osclient.notification().messages().publish("topic-urn", structuredMessage);
```

### 使用消息模板方式的消息发布
```java
Map<String, Object> tagReplacer = Maps.newHashMap();
tagReplacer.put("user", "tag-user");
TemplatedMessage templatedMessage = TemplatedMessage.builder().messageTemplateName("template-name").subject("hello")
		.tags(tagReplacer).build();
MessageIdResponse message = osclient.notification().messages().publish("topic-urn", templatedMessage);
```

##  短信直发
```java
MessageIdResponse message = osclient.notification().sms().send("15659767757", "Hello, sms", null);
```

