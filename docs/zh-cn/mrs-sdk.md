# Map Reduce SDK

OTC OpenStack4j Map Reduce SDK
- 服务入口: `osclient.sahara()`
- 服务类型: `sahara`


## 数据源接口
### 创建数据源
```java
DataSource build = SaharaDataSource.builder().name("data-source-name").url("/sdk/unittest/input")
		.type(DataSourceType.HDFS).isProtect(true).isPublic(false).description("sdk unittests").build();
DataSource created = osclient.sahara().dataSources().create(build);
```

### 更新数据源
```java
DataSource build = SaharaDataSource.builder().id("to-update-data-source-id").name("sdk-new-name")
		.url("/sdk/unittest/input2").build();
DataSource updated = osclient.sahara().dataSources().update(build);
```


### 查询数据源列表
```java
DataSourceListOptions options = DataSourceListOptions.create().asc("created_at").limit(10);
List<? extends DataSource> list = osclient.sahara().dataSources().list(options);
```

### 查询数据源详情
```java
DataSource dataSource = osclient.sahara().dataSources().get("data-source-id");
```

### 删除数据源
```java
ActionResponse delete = osclient.sahara().dataSources().delete("to-delete-data-source-id");
```

## 集群管理接口
### 创建集群并执行作业
```java
TODO
```

### 扩容集群节点

```java
ActionResponse expand = osclient.sahara().clusters().expand("cluster-id", 3);
```

### 缩减集群节点
```java
// 暂不支持
List<String> includes = Lists.newArrayList("instance-id-1");
List<String> excludes = Lists.newArrayList("instance-id-3");
ActionResponse reduce = osclient.sahara().clusters().reduce("cluster-id", 3, includes, excludes);
```

### 查询集群详情
```java
TODO
```

### 终止集群
```java
ActionResponse delete = osclient.sahara().clusters().delete("cluster-id");
```


## 作业二进制对象
### 创建作业二进制对象
```java
JobBinary build = SaharaJobBinary.builder().name("job-binary-name").url("/sdk/unittest/input")
				.isProtect(true).isPublic(true).description("sdk unittests").build();
JobBinary jobBinary = osclient.sahara().jobBinaries().create(build);
```

### 更新作业二进制对象
```java
JobBinary build = SaharaJobBinary.builder().id("to-updata-job-binary-id").name("sdk-new-name")
				.url("/sdk/unittest/input2").build();
JobBinary updated = osclient.sahara().jobBinaries().update(build);
```

### 查询作业二进制列
```java
JobBinaryListOptions options = JobBinaryListOptions.create().desc("created_at").limit(10);
List<? extends JobBinary> list = osclient.sahara().jobBinaries().list(options);
```

### 查询作业二进制详
```java
JobBinary jobBinary = osclient.sahara().jobBinaries().get("job-binary-id");
```

### 删除作业二进制对象
```java
ActionResponse delete = osclient.sahara().jobBinaries().delete("to-delete-job-binary-id");
```


## 作业对象接口
### 新增作业并执行
```java
# TODO
```

### 创建作业对象
```java
Job build = SaharaJob.builder().name("job-name").type(JobType.MapReduce).description("sdk unittest").isProtect(true)
				.isPublic(true).build();
Job created = osclient.sahara().jobs().create(build);
```

### 更新作业对象
```java
Job build = SaharaJob.builder().id("to-update-job-id").name("sdk-new-name").description("updated desc")
				.type(JobType.Spark).isProtect(false).isPublic(false).build();
Job job = osclient.sahara().jobs().update(build);
```

### 执行作业对象
```java
JobConfig jobConfig = SaharaJobConfig.builder().addConfig("mapred.map.tasks", 1)
		.addConfig("mapred.reduce.tasks", 1).addArg("wordcount").addParam("param1", "value1")
		.addParam("param2", "value2").build();
JobExecution jobExecution = SaharaJobExecution.builder().jobId("to-execute-job-id").clusterId("cluster-id")
		.inputId("input-id").outputId("output-id").isProtect(true).isPublic(true).setJobConfig(jobConfig)
		.build();
JobExecution execution = osclient.sahara().jobExecutions().create(jobExecution);
```

### 查询作业对象列表
```java
JobListOptions options = JobListOptions.create().asc("created_at").limit(10);
List<? extends Job> list = osclient.sahara().jobs().list(options);
```

### 查询作业对象详情
```java
Job job = osclient.sahara().jobs().get("job-id");
```

### 查询作业exe对象列表
```java
JobExeListOptions options = JobExeListOptions.create().page(1).pageSize(20).clusterId("cluster-id")
		.state(JobState.Completed);
List<? extends SaharaJobExe> list = osclient.sahara().jobExes().list(options);
```

### 查询作业exe对象详情
```java
osclient.sahara().jobExes().get("job-exe-id")
```

### 删除作业对象
```java
ActionResponse delete = osclient.sahara().jobs().delete("to-delete-job-id");
```


### 作业执行对象接口
### 查询作业执行对象列表
```java
JobExecutionListOptions options = JobExecutionListOptions.create().desc("created_at").limit(10);
List<? extends JobExecution> list = osclient.sahara().jobExecutions().list(options);
```

### 查询作业执行对象详情
```java
JobExecution execution = osclient.sahara().jobExecutions().get("job-execution-id");
```

### 取消作业执
```java
JobExecution execution = osclient.sahara().jobExecutions().cancel("to-cancel-job-execution-id");
```

### 删除作业执行对象
```java
ActionResponse delete = osclient.sahara().jobExecutions().delete("to-delete-job-execution-id");
```