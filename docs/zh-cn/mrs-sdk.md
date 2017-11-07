# Map Reduce SDK

OTC OpenStack4j Map Reduce SDK
- 服务入口: `osclient.mrs()`
- 服务类型: `sahara`

## API接口文档

请查阅 [官方接口文档](https://docs.otc.t-systems.com/en-us/api/mrs/en-us_topic_0037324628.html)


## 数据源接口
### 创建数据源
```java
DataSource build = MapReduceDataSource.builder().name("data-source-name").url("/sdk/unittest/input")
		.type(DataSourceType.HDFS).isProtect(true).isPublic(false).description("sdk unittests").build();
DataSource created = osclient.mrs().dataSources().create(build);
```

### 更新数据源
```java
DataSource build = MapReduceDataSource.builder().id("to-update-data-source-id").name("sdk-new-name")
		.url("/sdk/unittest/input2").build();
DataSource updated = osclient.mrs().dataSources().update(build);
```


### 查询数据源列表
```java
DataSourceListOptions options = DataSourceListOptions.create().asc("created_at").limit(10);
List<? extends DataSource> list = osclient.mrs().dataSources().list(options);
```

### 查询数据源详情
```java
DataSource dataSource = osclient.mrs().dataSources().get("data-source-id");
```

### 删除数据源
```java
ActionResponse delete = osclient.mrs().dataSources().delete("to-delete-data-source-id");
```

## 集群管理接口
### 创建集群并执行作业
```java
MapReduceComponent component = MapReduceComponent.builder().id("MRS 1.3.0_001").name("Hadoop").version("").desc("")
		.build();
MapReduceClusterCreate cluster = MapReduceClusterCreate.builder().dataCenter("eu-de").masterNodeNum(2)
		.masterNodeSize("c2.2xlarge.linux.mrs").coreNodeNum(3).coreNodeSize("c2.2xlarge.linux.mrs")
		.name("newcluster").availablilityZoneId("eu-de-01").vpcName("vpc1").vpcId("vpc-id").subnetName("subnet")
		.subnetId("subnet-id").version(ClusterVersion.MRS12).type(ClusterType.Stream).volumeSize(100)
		.volumeType(VolumeType.SSD).keypair("keypair").safeMode(0).components(Lists.newArrayList(component))
		.build();

// initial job exe create model
MapReduceJobExeCreate jobExe = MapReduceJobExeCreate.builder().jobType(JobType.MapReduce).jobName("sdk")
		.jarPath("s3a://sdk/sdk.jar").arguments("wordcount").input("s3a://input/").output("s3a://output/")
		.jobLog("s3a://log/").fileAction("").hql("").hiveScriptPath("").shutdownCluster(false)
		.submitJobOnceClusterRun(true).build();

MapReduceClusterCreateResult result = osclient.mrs().clusters2().createAndRunJob(cluster, jobExe);
```

### 扩容集群节点

```java
ActionResponse expand = osclient.mrs().clusters().expand("cluster-id", 3);
```

### 缩减集群节点
```java
// 暂不支持
List<String> includes = Lists.newArrayList("instance-id-1");
List<String> excludes = Lists.newArrayList("instance-id-3");
ActionResponse reduce = osclient.mrs().clusters().reduce("cluster-id", 3, includes, excludes);
```

### 查询集群详情
```java
MapReduceCluster2 cluster = osclient.mrs().clusters2().get("0f4ab6b7-a723-4b6c-b326-f8a5711d365a");
```

### 终止集群
```java
ActionResponse delete = osclient.mrs().clusters().delete("cluster-id");
```


## 作业二进制对象
### 创建作业二进制对象
```java
JobBinary build = MapReduceJobBinary.builder().name("job-binary-name").url("/sdk/unittest/input")
				.isProtect(true).isPublic(true).description("sdk unittests").build();
JobBinary jobBinary = osclient.mrs().jobBinaries().create(build);
```

### 更新作业二进制对象
```java
JobBinary build = MapReduceJobBinary.builder().id("to-updata-job-binary-id").name("sdk-new-name")
				.url("/sdk/unittest/input2").build();
JobBinary updated = osclient.mrs().jobBinaries().update(build);
```

### 查询作业二进制列
```java
JobBinaryListOptions options = JobBinaryListOptions.create().desc("created_at").limit(10);
List<? extends JobBinary> list = osclient.mrs().jobBinaries().list(options);
```

### 查询作业二进制详
```java
JobBinary jobBinary = osclient.mrs().jobBinaries().get("job-binary-id");
```

### 删除作业二进制对象
```java
ActionResponse delete = osclient.mrs().jobBinaries().delete("to-delete-job-binary-id");
```


## 作业对象接口
### 新增作业并执行
```java
MapReduceJobExeCreate jobExeCreate = MapReduceJobExeCreate.builder().jobType(JobType.Spark).jobName("sdk-unittests")
				.clusterId("cluster-id").jarPath("s3a://sdk/jar.jar").arguments("wordcount").input("s3a://sdk/input")
				.output("s3a://sdk/output").jobLog("s3a://sdk/log").fileAction("export").hql("hql")
				.hiveScriptPath("s3a://sdk/script.hql").isProtected(true).isPublic(false).build();
MapReduceJobExe exe = osclient.mrs().jobExes().create(jobExeCreate);
```

### 创建作业对象
```java
Job build = MapReduceJob.builder().name("job-name").type(JobType.MapReduce).description("sdk unittest").isProtect(true)
				.isPublic(true).build();
Job created = osclient.mrs().jobs().create(build);
```

### 更新作业对象
```java
Job build = MapReduceJob.builder().id("to-update-job-id").name("sdk-new-name").description("updated desc")
				.type(JobType.Spark).isProtect(false).isPublic(false).build();
Job job = osclient.mrs().jobs().update(build);
```

### 执行作业对象
```java
JobConfig jobConfig = MapReduceJobConfig.builder().addConfig("mapred.map.tasks", 1)
		.addConfig("mapred.reduce.tasks", 1).addArg("wordcount").addParam("param1", "value1")
		.addParam("param2", "value2").build();
JobExecution jobExecution = MapReduceJobExecution.builder().jobId("to-execute-job-id").clusterId("cluster-id")
		.inputId("input-id").outputId("output-id").isProtect(true).isPublic(true).setJobConfig(jobConfig)
		.build();
JobExecution execution = osclient.mrs().jobExecutions().create(jobExecution);
```

### 查询作业对象列表
```java
JobListOptions options = JobListOptions.create().asc("created_at").limit(10);
List<? extends Job> list = osclient.mrs().jobs().list(options);
```

### 查询作业对象详情
```java
Job job = osclient.mrs().jobs().get("job-id");
```

### 查询作业exe对象列表
```java
JobExeListOptions options = JobExeListOptions.create().page(1).pageSize(20).clusterId("cluster-id")
		.state(JobState.Completed);
List<? extends MapReduceJobExe> list = osclient.mrs().jobExes().list(options);
```

### 查询作业exe对象详情
```java
osclient.mrs().jobExes().get("job-exe-id")
```

### 删除作业对象
```java
ActionResponse delete = osclient.mrs().jobs().delete("to-delete-job-id");
```


## 作业执行对象接口
### 查询作业执行对象列表
```java
JobExecutionListOptions options = JobExecutionListOptions.create().desc("created_at").limit(10);
List<? extends JobExecution> list = osclient.mrs().jobExecutions().list(options);
```

### 查询作业执行对象详情
```java
JobExecution execution = osclient.mrs().jobExecutions().get("job-execution-id");
```

### 取消作业执
```java
JobExecution execution = osclient.mrs().jobExecutions().cancel("to-cancel-job-execution-id");
```

### 删除作业执行对象
```java
ActionResponse delete = osclient.mrs().jobExecutions().delete("to-delete-job-execution-id");
```