# DMS SDK

OTC OpenStack4j Distributed Message Service SDK
- Service Entry: `osclient.messageQueue()`
- Service Type: `distributed-message` 


## API documentation

Refer: [Official API documentation](https://docs.otc.t-systems.com/en-us/api/dms/en-us_topic_0036182510.html)

## Queue
### Create Queue
```java
Queue queue = osclient.messageQueue().queue().create("queue-name", "queue-display-name");
```

### List Queue
```java
List<? extends Queue> queues = osclient.messageQueue().queue().list();
```

### Get Queue
```java
Queue get = osclient.messageQueue().queue().get("queue-id");
```

### Delete Queue
```java
ActionResponse delete = osclient.messageQueue().queue().delete("queue-id");
```

## Consumer Group
### List Consumer Group of a Queue
```java
List<ConsumerGroup> list = osclient.messageQueue().consumerGroups().list(queue.getId());
```

### Create Consumer Group
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

### List Consumer Group
```java
// create single consumer group
ActionResponse delete = osclient.messageQueue().consumerGroups().delete("queue-id", "consumer-group-id");
```

## Message
### Produce message

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


### Consume Message
```java
Integer maxMessages = 5;
Integer timeWait = 10;
List<QueueMessageWithHandler> messages = osclient.messageQueue().messages()
											.consume("queue-id", "consumer-group-id", maxMessages, timeWait);
```

### Confirm Consuming
```java
Map<String, ConsumeStatus> consumeResult = Maps.newHashMap();
consumeResult.put("message-handler", ConsumeStatus.SUCCESS);

ConsumeConfirmResponse confirmConsuming = osclient.messageQueue().messages()
											.confirmConsuming"queue-id", "consumer-group-id", consumeResult);
```

## Quota
### Get quota of current tenant
```java
List<Quota> qutoas = osclient.messageQueue().quotas().get();
```
