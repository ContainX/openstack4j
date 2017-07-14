# KMS SDK

OTC OpenStack4j DNS SDK
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

## 数据加解密
### 创建随机数

### 创建数据密钥
### 创建不含明文数据密钥
### 加密数据密钥
### 解密数据密钥
### 查询实例数
### 查询配额
### 修改密钥别名
### 修改密钥描述
### 创建授权

