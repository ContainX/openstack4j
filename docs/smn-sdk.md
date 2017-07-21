# SMN SDK

OTC OpenStack4j Simple Message Notification SDK
- Service Entry: `osclient.notification()`
- Service Type: `notification`


## API Documentation

Refer: [Official API documentation](https://docs.otc.t-systems.com/en-us/api/smn/en-us_topic_0036017316.html)
  
##  Topic
### Create Topic
```java
Topic topic = osclient.notification().topics().create("topic-name", "display-name");
```

### Update Topic
```java
String topicUrn = "topic-urn";
String displayName = "new-display-name";
TracableRequest updated = osclient.notification().topics().updateDisplayName(topicUrn, displayName);
```

### Delete Topic
```java
String topicUrn = "topic-urn";
TracableRequest request = osclient.notification().topics().delete(topicUrn);
```

### List Topic
```java
List<? extends Topic> topics = osclient.notification().topics().list(100, 0);
```

### Get Topic
```java
String topicUrn = "topic-urn";
Topic get = osclient.notification().topics().get(topicUrn);
```

### List Topic Attribute
```java
String topicUrn = "topic-urn";
TopicAttributes attrs = osclient.notification().topics().getTopicAttributes(topicUrn);
System.out.println(attrs.getIntroduction());
```

### Get Topic Attribute
```java
String topicUrn = "topic-urn";
String attr = osclient.notification().topics().getTopicAttribute(topicUrn, TopicAttributeName.Introduction);
```

### Update Topic Attribute
```java
String topicUrn = "topic-urn";
TracableRequest request = osclient.notification().topics()
							.updateTopicAttribute(topicUrn, TopicAttributeName.Introduction, "sdk-unittest");
```

### Delete Topic Attribute
```java
String topicUrn = "topic-urn";
TracableRequest request = osclient.notification().topics()
							.deleteTopicAttribute(topicUrn, TopicAttributeName.Introduction);
```

### Delete Topic Attributes
```java
String topicUrn = "topic-urn";
TracableRequest request = osclient.notification().topics().deleteTopicAttributes(topicUrn);
```


## Subscription
### List Subscription
```java
List<? extends Subscription> subscriptions = osclient.notification().subscriptions().list(100, 0);
```

### List Subscription of topic
```java
List<? extends Subscription> subscriptions = osclient.notification().subscriptions().listByTopic("topic-urn", 100, 0);
```

### Subscribe
```java
SubscriptionCreate subscribe = SubscriptionCreate.builder().topicUrn("topic-urn").endpoint("xx@xx.com")
				.protocol(Protocol.EMAIL).remark("sdk-unittest").build();
Subscription subscription = osclient.notification().subscriptions().subscribe(subscribe);
```

### Unsubscribe
```java
TracableRequest unsubscribe = osclient.notification().subscriptions().unsubscribe("subscription-urn");
```

## Message Template
### Create Message Template
```java
MessageTemplateCreate create = MessageTemplateCreate.builder().name("template-name").protocol(Protocol.EMAIL)
				.locale("zh-cn").content("content").build();
MessageTemplate	template = osclient.notification().messageTemplates().create(create);
```

### Update Message Template
```java
TracableRequest update = osclient.notification().messageTemplates().updateContent("message-template-id",
				"Hello, {user}");
```

### Delete Message Template
```java
TracableRequest delete = osclient.notification().messageTemplates().delete("message-template-id");
```

### List Message Template
```java
MessageTemplateListOptions options = MessageTemplateListOptions.create().limit(1).offset(0).name("template-name")
				.protocol(Protocol.EMAIL);
List<? extends MessageTemplate> templates = osclient.notification().messageTemplates().list(options);
```

### Get Message Template
```java
MessageTemplate get = osclient.notification().messageTemplates().get("message-template-id");
```

## Message
### Publish Message
```java
MessageIdResponse message = osclient.notification().messages().publish("topic-urn", "subject", "message-content");
```

### Publish Structured Message
```java
StructuredMessage structuredMessage = StructuredMessage.builder().subject("hello")
				.defaultMessage("hello, there").emailMessage("hello, email").build();
MessageIdResponse message = osclient.notification().messages().publish("topic-urn", structuredMessage);
```

### Publish Templated Message
```java
Map<String, Object> tagReplacer = Maps.newHashMap();
tagReplacer.put("user", "tag-user");
TemplatedMessage templatedMessage = TemplatedMessage.builder().messageTemplateName("template-name").subject("hello")
		.tags(tagReplacer).build();
MessageIdResponse message = osclient.notification().messages().publish("topic-urn", templatedMessage);
```

##  SMS
### Send SMS
```java
MessageIdResponse message = osclient.notification().sms().send("15659767757", "Hello, sms", null);
```

