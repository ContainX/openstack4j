# DMS SDK

OTC OpenStack4j Distributed Message Service SDK
- 服务入口: `osclient.messageQueue()`
- 服务类型: `distributed-message` 


## API documentation

请查阅 [官方接口文档](https://docs.otc.t-systems.com/en-us/api/dms/en-us_topic_0036182510.html)

## 队列
### 创建队列
```java
Queue queue = osclient.messageQueue().queue().create("queue-name", "queue-display-name");
```

### 查看所有队列
```java
List<? extends Queue> queues = osclient.messageQueue().queue().list();
```

### 查看指定队列
```java
Queue get = osclient.messageQueue().queue().get("queue-id");
```

### 删除指定队列
```java
ActionResponse delete = osclient.messageQueue().queue().delete("queue-id");
```

## 消费组
### 查看指定队列的所有消费组
```java
List<ConsumerGroup> list = osclient.messageQueue().consumerGroups().list(queue.getId());
```

### 创建消费组
```java
// create multiply consumer groups
List<String> groupNames = Lists.newArrayList("consumer-group-1", "consumer-group-2");
List<ConsumerGroup> groups = osclient.messageQueue().consumerGroups().create("queue-id", groupNames);
```

> or

```java
// create single consumer group
ConsumerGroup group = osclient.messageQueue().consumerGroups().create("queue-id", "consumer-group-name");
```

### 删除指定消费组
```java
// create single consumer group
ActionResponse delete = osclient.messageQueue().consumerGroups().delete("queue-id", "consumer-group-id");
```

## 消息
### 向指定队列发送消息

> send single message

```java
HashMap<String, Object> attributes1 = Maps.newHashMap();
attributes1.put("attr1", 1);
attributes1.put("attr2", false);

// build queue message
QueueMessage message = QueueMessage.builder().body("sdk-unittests").attributes(attributes1).build();

ActionResponse produce = osclient.messageQueue().messages().produce("queue-id", message);
```

> or send multiply messages

```java
List<QueueMessage> messages = Lists.newArrayList();

HashMap<String, Object> attributes1 = Maps.newHashMap();
attributes1.put("attr1", 1);
attributes1.put("attr2", false);
messages.add(QueueMessage.builder().body("sdk-unittests").attributes(attributes1).build());

HashMap<String, Object> attributes2 = Maps.newHashMap();
attributes2.put("attr1", "value1");
attributes2.put("attr2", false);
messages.add(QueueMessage.builder().body(attributes2).attributes(attributes2).build());

ActionResponse produce = osclient.messageQueue().messages().produce("queue-id", messages);
```


### 消费消息
```java
Integer maxMessages = 5;
Integer timeWait = 10;
List<QueueMessageWithHandler> messages = osclient.messageQueue().messages()
											.consume("queue-id", "consumer-group-id", maxMessages, timeWait);
```

### 确认已消费指定消息
```java
Map<String, ConsumeStatus> consumeResult = Maps.newHashMap();
consumeResult.put("message-handler", ConsumeStatus.SUCCESS);

ConsumeConfirmResponse confirmConsuming = osclient.messageQueue().messages()
											.confirmConsuming"queue-id", "consumer-group-id", consumeResult);
```

## 配额
### 查看租户配额
```java
List<Quota> qutoas = osclient.messageQueue().quotas().get();
```
