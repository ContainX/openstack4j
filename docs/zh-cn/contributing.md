# 贡献代码

本节文档的目标读者是那些想要在该SDK基础上进行开发或者代码修订的开发者。如果你只是想获取如何使用该SDK的文档，请查阅 [快速开始](zh-cn/quickstart.md)。

在开始进入开发前，我们假定你已经足够了解以下的知识点：

- java
- maven
- jackson
- HTTP transport
- OpenStack REST API 以及 各个 Service间的关系



## 项目结构

```
project
   |- docs           		// 文档目录，基于docsify生成
   |- core      	 		// SDK主项目
   |- connectors      	 	// SDK HTTP Connectors
	 |- httpclient			// 基于httpclient的connector
	 |- http-connector		// 基于JDK1.8 http的connector
	 |- jersey2				// 基于 jersey2 connector
	 |- okhttp				// 基于 okhttp connector
	 |- resteasy			// 基于 resteasy connector
   |- core-test				// 测试用例
   |- distribution			// 用于打包默认openstack4j项目（默认使用resteasy）
   |- sample				// 本次新增的SDK的示例项目
```

如上面所示，如果只是想开发或者修改某个SDK的话，只需要关注 `core` 和 `core-test`，其他的模块正常不会修改到。


## Core模块

```
com.huawei.openstack4j
	  |- api				// 所有服务的接口定义
	  |- common				// 忽略
	  |- core.transport		// HTTP transport抽象（包含了请求异常情况的处理）
	  |- model				// 服务相关的Model接口定义
	  |- openstack			// 所有服务接口对应的 OpenStack版本的实现
```

Core模块中包含了实现SDK的所有逻辑，他主要功能可以分为三块

- 处理OpenStack认证，我们将会使用 `v3`认证，并自动在请求API的时候，添加认证Token
- 抽象了 HTTP transport，将HTTP传输的逻辑从具体的实现库中剥离（使得我们可以自由的更换connector）。并且封装了适合OpenStack接口规范的各类HTTP请求的方法。（GET/POST/..etc）
- 定义了所有SDK的 Service接口，Model以及其OpenStack实现

实际开发的时候，关注点放在 `openstack`包下的SDK具体实现上。

> api 和 model包里的接口设计，个人觉得是过渡设计了（因为这个项目不可能还会添加除了OpenStack外的其他实现），使得开发过程中会平白多出很大的工作量，而且代码也不友好。本次开发的SDK，移除了原有的Builder体系，使用 lombok 替代了，减少了一半以上的代码量。

## 开发例子

本节我们以开发一个名为  Fake 的新服务的SDK为例，来说明整个开发流程。

** 1. 注册新服务类型 **

在 com.huawei.openstack4j.api.types.ServiceType 中新增服务类型

```
public enum ServiceType {
  ....,
  Fake("fake", "fake");
}
```

** 2. 创建服务的接口 **

- 在com.huawei.openstack4j.api下新增包 `fake`，用于存放所有的 Fake Service相关的接口
- 新建Fake服务主接口，主接口用于区分服务下的各个子模块，比如Fake服务下有 resource1, resource2 两个模块

```java
// com.huawei.openstack4j.api.fake.FakeService.class
public interface FakeService extends RestService {

	/**
	 *  获取到 resource1 对应的Service实现
	 */
	FakeResource1Service resource1();
	
	/**
	 *  获取到 resource2 对应的Service实现
	 */
	FakeResource2Service resource2();

}
```

- 创建各个子模块对应的Service接口

```java
// com.huawei.openstack4j.api.fake.FakeResource1Service.class
public interface FakeResource1Service extends RestService {

	public List<? extends Resource1> list();
	
	public Resource1 get(String resourceId);
	
	public Resource1 create(Resource1Create model);
	
	public ActionResponse delete(String resourceId);
	
}

// com.huawei.openstack4j.api.fake.FakeResource2Service.class
public interface FakeResource2Service extends RestService {
	// 同上
}
```

?> 实际开发的时候，可能接口会根据实际情况，会更复杂，更友好一些。

** 3. 创建各个接口需要用到的实体类的接口 **

?> 在com.huawei.openstack4j.model下新增包 `fake`，用于存放所有的 Fake Service相关的Model类的接口

第二点中用到了诸多的模型对象，我们需要为他们创建对应的接口。openstack4j为Model类提供了两个基类

- 一个是 ModelEntity，就是一个空接口，作用仅仅是标记一个类是 Model类
- 另一个是ResouceEntity，里面包含了资源公用的id和name属性，正常OpenStack的资源都会有这两个属性

```java
// com.huawei.openstack4j.model.fake.Resource1Create.class
public interface Resource1Create extends ResouceEntity {
	
	/**
	 * @return resource description
	 */
	String getDescription();
}


// com.huawei.openstack4j.model.fake.Resource1.class
public interface Resource1 extends Resource1Create {
	
	/**
	 * @return resource status
	 */
	String getStatus();
}

// com.huawei.openstack4j.model.fake.Resource2.class
public interface Resource2 extends ModelEntity {

	/**
	 * @return the id of this entity
	 */
	String getResource2Id();
	
	/**
	 * @return the name of this entity
	 */
	String getResource2Name();
	
}

```


?> 这次开发的服务中，部分资源命名比较奇怪，可以直接继承ModelEntity。也可以使用 jackson的 @JsonProperty 来做一次转化，建议使用第二种。

** 4. 实现Model接口类 **

- model实现类的位置， com.huawei.openstack4j.openstack.fake.domain
- Model类接口实现，我们使用了 [lombok](https://projectlombok.org/) 来自动生成 
	- getter 方法
	- chain builder 方法
	- toString (google guava式的toString)
	- 无参构造函数
	- 所有参数的构造函数
- 注意FakeResource1中的 FakeResource1List类，是用于接收查询列表方法的返回值
- 注意各个jackson的annotation，都是用于处理服务端数据的映射。这个要根据实际情况设置，这边就不对jackson做详细展开了，具体请参考jackson官方文档

```java

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FakeResource1Create implements Resource1Create {

	private static final long serialVersionUID = -8356492591040412347L;
	
	@JsonProperty("resource1_id")
	private String id;
	
	@JsonProperty("resource1_name")
	private String name;
	
	private String description;
	
}

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("resource")
public class FakeResource1 extends FakeResource1Create implements Resource1 {

	private static final long serialVersionUID = -8356492591040412347L;
	
	private String status;
	
	public static class FakeResource1List extends ListResult<FakeResource1> {

		private static final long serialVersionUID = -1873594481944340934L;
		
		@JsonProperty("resources")
		private List<FakeResource1> resources;

		@Override
		protected List<FakeResource1> value() {
			return resources;
		}
	}
}


// ....
// 同理实现其他的Model类接口
```



** 5. 实现上面定义的各个接口类 **

- service实现类的位置， com.huawei.openstack4j.openstack.fake.internal
- Fake Service Impl 基类，唯一的作用就是用于申明service实现所属的 ServiceType，其他所有的 接口实现类都继承该类

```java
/**
 * This class is extended by every Fake Service. It is necessary to determine
 * the correct endpoint (url) for Fake Service calls.
 * 
 */
public class BaseFakeServices extends BaseOpenStackService {

	protected BaseAutoScalingServices() {
		super(ServiceType.Fake);
	}

}
```

- 实现 FakeService 接口


public class FakeServiceImpl extends BaseFakeServices implements FakeService {

	/**
	 *  获取到 resource1 对应的Service实现
	 */
	FakeResource1Service resource1() {
		return Apis.get(FakeResource1Service.class);
	}
	
	/**
	 *  获取到 resource2 对应的Service实现
	 */
	FakeResource2Service resource2() {
		return Apis.get(FakeResource1Service.class);
	}

}

- 实现 FakeResource1Service接口

```java
public class FakeResource1ServiceImple extends BaseFakeServices implements FakeResource1Service {

	public List<? extends Resource1> list() {
		return get(FakeResource1List.class, uri("/fake")).execute().getList();
	}
	
	public Resource1 get(String resourceId) {
		return get(FakeResource1.class, uri("/fake/%s", groupId)).execute();
	}
	
	public Resource1 create(Resource1Create model) {
		return post(FakeResource1Create.class, uri("/fake")).entity(model).execute();
	}
	
	public ActionResponse delete(String resourceId) {
		return deleteWithResponse(uri("/fake/%s", resourceId)).execute();
	}
	
}
```

** 6. 注册各个Service **

所有的Service都需要在DefaultAPIProvider中进行注册，这样才能在 Apis.get中获取到

```java
class DefaultAPIProvider#initialize() {
    // 绑定服务实现
    bind(FakeService.class, FakeServiceImpl.class);
    bind(FakeResource1Service.class, FakeResource1ServiceImple.class);
    // 其他Services...
}
```

** 7. 将Service添加到Client中 **

将服务注册到 OSClient中，其实这边也可以直接注册到 OSClientV3中，因为目前来看我们以后都只会使用 v3版本，不会再有v2版本的实现。

``` java
class OSClient {
  FakeService fake();
}

// 服务实现
public static class OSClientSessionV3 extends OSClientSession<OSClientSessionV3, OSClientV3> implements OSClientV3 {
      public FakeService fake() {
            return Apis.get(FakeService.class);
        }
}
```


