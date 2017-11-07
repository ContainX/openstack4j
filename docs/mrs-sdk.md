# Map Reduce SDK

OTC OpenStack4j Map Reduce SDK
- Service Entry: `osclient.mrs()`
- Service Type: `sahara`


## DataSource
### Create Data Source
```java
DataSource build = MapReduceDataSource.builder().name("data-source-name").url("/sdk/unittest/input")
		.type(DataSourceType.HDFS).isProtect(true).isPublic(false).description("sdk unittests").build();
DataSource created = osclient.mrs().dataSources().create(build);
```

### Update Data Source
```java
DataSource build = MapReduceDataSource.builder().id("to-update-data-source-id").name("sdk-new-name")
		.url("/sdk/unittest/input2").build();
DataSource updated = osclient.mrs().dataSources().update(build);
```


### List Data Source
```java
DataSourceListOptions options = DataSourceListOptions.create().asc("created_at").limit(10);
List<? extends DataSource> list = osclient.mrs().dataSources().list(options);
```

### Get Data Source
```java
DataSource dataSource = osclient.mrs().dataSources().get("data-source-id");
```

### Delete Data Source
```java
ActionResponse delete = osclient.mrs().dataSources().delete("to-delete-data-source-id");
```

## Cluster
### Create Cluster And Run Job
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

### Expand Cluster Node
```java
ActionResponse expand = osclient.mrs().clusters().expand("cluster-id", 3);
```

### Reduce Cluster Node
```java
// Not support for now
List<String> includes = Lists.newArrayList("instance-id-1");
List<String> excludes = Lists.newArrayList("instance-id-3");
ActionResponse reduce = osclient.mrs().clusters().reduce("cluster-id", 3, includes, excludes);
```

### Get Cluster
```java
MapReduceCluster2 cluster = osclient.mrs().clusters2().get("0f4ab6b7-a723-4b6c-b326-f8a5711d365a");
```

### Terminate Cluster
```java
ActionResponse delete = osclient.mrs().clusters().delete("cluster-id");
```


## Job Binary
### Create Job Binary
```java
JobBinary build = MapReduceJobBinary.builder().name("job-binary-name").url("/sdk/unittest/input")
				.isProtect(true).isPublic(true).description("sdk unittests").build();
JobBinary jobBinary = osclient.mrs().jobBinaries().create(build);
```

### Update Job Binary
```java
JobBinary build = MapReduceJobBinary.builder().id("to-updata-job-binary-id").name("sdk-new-name")
				.url("/sdk/unittest/input2").build();
JobBinary updated = osclient.mrs().jobBinaries().update(build);
```

### List Job Binary
```java
JobBinaryListOptions options = JobBinaryListOptions.create().desc("created_at").limit(10);
List<? extends JobBinary> list = osclient.mrs().jobBinaries().list(options);
```

### Get Job Binary
```java
JobBinary jobBinary = osclient.mrs().jobBinaries().get("job-binary-id");
```

### Delete Job Binary
```java
ActionResponse delete = osclient.mrs().jobBinaries().delete("to-delete-job-binary-id");
```


## Job
### Create Job And Execute
```java
MapReduceJobExeCreate jobExeCreate = MapReduceJobExeCreate.builder().jobType(JobType.Spark).jobName("sdk-unittests")
				.clusterId("cluster-id").jarPath("s3a://sdk/jar.jar").arguments("wordcount").input("s3a://sdk/input")
				.output("s3a://sdk/output").jobLog("s3a://sdk/log").fileAction("export").hql("hql")
				.hiveScriptPath("s3a://sdk/script.hql").isProtected(true).isPublic(false).build();
MapReduceJobExe exe = osclient.mrs().jobExes().create(jobExeCreate);
```

### Create Job
```java
Job build = MapReduceJob.builder().name("job-name").type(JobType.MapReduce).description("sdk unittest").isProtect(true)
				.isPublic(true).build();
Job created = osclient.mrs().jobs().create(build);
```

### Update Job
```java
Job build = MapReduceJob.builder().id("to-update-job-id").name("sdk-new-name").description("updated desc")
				.type(JobType.Spark).isProtect(false).isPublic(false).build();
Job job = osclient.mrs().jobs().update(build);
```

### Execute Job
```java
JobConfig jobConfig = MapReduceJobConfig.builder().addConfig("mapred.map.tasks", 1)
		.addConfig("mapred.reduce.tasks", 1).addArg("wordcount").addParam("param1", "value1")
		.addParam("param2", "value2").build();
JobExecution jobExecution = MapReduceJobExecution.builder().jobId("to-execute-job-id").clusterId("cluster-id")
		.inputId("input-id").outputId("output-id").isProtect(true).isPublic(true).setJobConfig(jobConfig)
		.build();
JobExecution execution = osclient.mrs().jobExecutions().create(jobExecution);
```

### List Job
```java
JobListOptions options = JobListOptions.create().asc("created_at").limit(10);
List<? extends Job> list = osclient.mrs().jobs().list(options);
```

### Get Job
```java
Job job = osclient.mrs().jobs().get("job-id");
```

### List Job exe
```java
JobExeListOptions options = JobExeListOptions.create().page(1).pageSize(20).clusterId("cluster-id")
		.state(JobState.Completed);
List<? extends MapReduceJobExe> list = osclient.mrs().jobExes().list(options);
```

### Get Job exe
```java
osclient.mrs().jobExes().get("job-exe-id")
```

### Delete Job
```java
ActionResponse delete = osclient.mrs().jobs().delete("to-delete-job-id");
```


## Job Execution
### List Job Execution
```java
JobExecutionListOptions options = JobExecutionListOptions.create().desc("created_at").limit(10);
List<? extends JobExecution> list = osclient.mrs().jobExecutions().list(options);
```

### Get Job Execution
```java
JobExecution execution = osclient.mrs().jobExecutions().get("job-execution-id");
```

### Cancel Job Execution
```java
JobExecution execution = osclient.mrs().jobExecutions().cancel("to-cancel-job-execution-id");
```

### Delete Job Execution
```java
ActionResponse delete = osclient.mrs().jobExecutions().delete("to-delete-job-execution-id");
```