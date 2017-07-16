# KMS SDK

OTC OpenStack4j Key Management SDK
- Service Entry: `osclient.keyManagement()`
- Service Type: `key-management`


## API documentation

Refer: [Official API documentation](https://docs.otc.t-systems.com/en-us/api/kms/en-us_topic_0038437596.html)
    
## Master Key Management
### Create Master Key

```java
KeyCreate create = KeyCreate.builder().alias("alias").description("desc")
					.realm("eu-de").sequence("sequence_xxxxxxxxxx").build();
Key key = osclient.keyManagement().keys().create(create);
```

### Enable Master Key
```java
Key enabled = osclient.keyManagement().keys().enable("key-id", "sequence");
```

### Disable Master Key
```java
Key disabled = osclient.keyManagement().keys().disable("key-id", "sequence");
```

### Scheduled Deletion of Master Key
```java
Key delete = osclient.keyManagement().keys().scheduleDeletion("key-id", 10, "sequence");
```

### Cancel Deletion of Master Key
```java
Key cancel = osclient.keyManagement().keys().cancelDeletion("key-id", "sequence");
```

### List Master Key
```java
KeyListOptions options = KeyListOptions.create().limit(10).marker("last-key-id").sequence("sequence");
Keys keys = osclient.keyManagement().keys().list(options);
List<String> keyIdList = keys.get();
```

### Get Master Key
```java
Key key = osclient.keyManagement().keys().get("key-id", "sequence");
```

### Get User Created Master Key Amount
```java
Integer keyCreatedAmount = osclient.keyManagement().keys().getKeyCreatedAmount();
```

### Query Quotas
```java
List<Quota> quotas = osclient.keyManagement().keys().quotas();
for (Quota quota : quotas) {
	if (quota.getType().equals(ResourceType.CMK)) {
		// do what u want to
	}
}
```


## Cryptop
### Generate Random String
```java
String randomString = osclient.keyManagement().crypto().generateRandomString("sequence");
```

### Create DEK
```java
HashMap<String, Object> encryptionContext = Maps.newHashMap();
encryptionContext.put("Key1", "value1");
encryptionContext.put("Key2", "value2");
DEK dek = osclient.keyManagement().crypto().createDEK("key-id", encryptionContext, "sequence");
```

### Create DEK without plain-text
```java
HashMap<String, Object> encryptionContext = Maps.newHashMap();
encryptionContext.put("Key1", "value1");
encryptionContext.put("Key2", "value2");
DEK dek = osclient.keyManagement().crypto().createDEKWithoutPlaintext("key-id", encryptionContext, "sequence");
```

### Encrypt DEK
```java
HashMap<String, Object> context = Maps.newHashMap();
context.put("Key1", "value1");
context.put("Key2", "value2");

// plain-text is in 128 bit HEX format, no sha-256 hash required(will be auto added) 
String plainText = "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
EncryptDEK encrypt = EncryptDEK.builder().keyId("key-id").plainText(plainText).encryptionContext(context)
		.build();
EncryptedDEK encryptedDEK = osclient.keyManagement().crypto().encryptDEK(encrypt);
```

### Decrypt DEK

```java
HashMap<String, Object> context = Maps.newHashMap();
context.put("Key1", "value1");
context.put("Key2", "value2");

// some-cipher-text come from "Encrypt DEK" API
DecryptDEK decrypt = DecryptDEK.builder().keyId(keyId).cipherText("some-cipher-text")
		.encryptionContext(context).build();

DecryptedDEK decryptDEK = osclient.keyManagement().crypto().decryptDEK(decrypt);
```
