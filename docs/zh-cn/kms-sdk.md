# KMS SDK

OTC OpenStack4j KMS SDK
- 服务入口: `osclient.keyManagement()`
- 服务类型: `key-management`


## API接口文档

请查阅 [官方接口文档](https://docs.otc.t-systems.com/en-us/api/kms/en-us_topic_0038437596.html)

    
## 密钥管理服务
### 创建密钥

```java
KeyCreate create = KeyCreate.builder().alias("alias").description("desc")
					.realm("eu-de").sequence("sequence_xxxxxxxxxx").build();
Key key = osclient.keyManagement().keys().create(create);
```

### 启用密钥
```java
Key enabled = osclient.keyManagement().keys().enable("key-id", "sequence");
```

### 禁用密钥
```java
Key disabled = osclient.keyManagement().keys().disable("key-id", "sequence");
```

### 计划删除密钥
```java
Key delete = osclient.keyManagement().keys().scheduleDeletion("key-id", 10, "sequence");
```

### 取消计划删除密钥
```java
Key cancel = osclient.keyManagement().keys().cancelDeletion("key-id", "sequence");
```

### 查询密钥列表
```java
KeyListOptions options = KeyListOptions.create().limit(10).marker("last-key-id").sequence("sequence");
Keys keys = osclient.keyManagement().keys().list(options);
List<String> keyIdList = keys.get();
```

### 查询密钥信息
```java
Key key = osclient.keyManagement().keys().get("key-id", "sequence");
```

### 查询实例数
```java
Integer keyCreatedAmount = osclient.keyManagement().keys().getKeyCreatedAmount();
```

### 查询配额
```java
List<Quota> quotas = osclient.keyManagement().keys().quotas();
for (Quota quota : quotas) {
	if (quota.getType().equals(ResourceType.CMK)) {
		// do what u want to
	}
}
```


## 数据加解密
### 创建随机数
```java
String randomString = osclient.keyManagement().crypto().generateRandomString("sequence");
```

### 创建数据密钥
```java
HashMap<String, Object> encryptionContext = Maps.newHashMap();
encryptionContext.put("Key1", "value1");
encryptionContext.put("Key2", "value2");
DEK dek = osclient.keyManagement().crypto().createDEK("key-id", encryptionContext, "sequence");
```

### 创建不含明文数据密钥
```java
HashMap<String, Object> encryptionContext = Maps.newHashMap();
encryptionContext.put("Key1", "value1");
encryptionContext.put("Key2", "value2");
DEK dek = osclient.keyManagement().crypto().createDEKWithoutPlaintext("key-id", encryptionContext, "sequence");
```

### 加密数据密钥
```java
HashMap<String, Object> context = Maps.newHashMap();
context.put("Key1", "value1");
context.put("Key2", "value2");

// 这边的plain-text不需要自行添加 sha-256 hash值，SDK会自动帮你生成hash
String plainText = "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
EncryptDEK encrypt = EncryptDEK.builder().keyId("key-id").plainText(plainText).encryptionContext(context)
		.build();
EncryptedDEK encryptedDEK = osclient.keyManagement().crypto().encryptDEK(encrypt);
```

### 解密数据密钥

```java
HashMap<String, Object> context = Maps.newHashMap();
context.put("Key1", "value1");
context.put("Key2", "value2");

// some-cipher-text 来自上一节中的 加密数据密钥
DecryptDEK decrypt = DecryptDEK.builder().keyId(keyId).cipherText("some-cipher-text")
		.encryptionContext(context).build();

DecryptedDEK decryptDEK = osclient.keyManagement().crypto().decryptDEK(decrypt);
```
