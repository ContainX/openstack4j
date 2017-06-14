# DNS SDk

HuaWei OpenStack4j DNS SDK, entry point is: `osclient.as()`

## API document
Not provided for now.

## SDK document

### initial SDK client
You can find how to initial SDK client in the [quickstart](huawei-sdk?id=_2-build-v3-client) page .

### List AutoScaling Groups
```java
List<? extends ScalingGroup> list = osclient.autoScaling().groups().list();
```

