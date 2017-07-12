# Map Reduce SDK

OTC OpenStack4j Map Reduce SDK
- Service Entry: `osclient.sahara()`
- Service Type: `sahara`


## DataSource
### Create Data Source
```java
DataSource build = SaharaDataSource.builder().name("data-source-name").url("/sdk/unittest/input")
		.type(DataSourceType.HDFS).isProtect(true).isPublic(false).description("sdk unittests").build();
DataSource created = osclient.sahara().dataSources().create(build);
```

### Update Data Source
```java
DataSource build = SaharaDataSource.builder().id("to-update-data-source-id").name("sdk-new-name")
		.url("/sdk/unittest/input2").build();
DataSource updated = osclient.sahara().dataSources().update(build);
```


### List Data Source
```java
DataSourceListOptions options = DataSourceListOptions.create().asc("created_at").limit(10);
List<? extends DataSource> list = osclient.sahara().dataSources().list(options);
```

### Get Data Source
```java
DataSource dataSource = osclient.sahara().dataSources().get("data-source-id");
```

### Delete Data Source
```java
ActionResponse delete = osclient.sahara().dataSources().delete("to-delete-data-source-id");
```

## Cluster
### Create Cluster And Run Job
```java
SaharaComponent component = SaharaComponent.builder().id("MRS 1.3.0_001").name("Hadoop").version("").desc("")
		.build();
SaharaClusterCreate cluster = SaharaClusterCreate.builder().dataCenter("eu-de").masterNodeNum(2)
		.masterNodeSize("c2.2xlarge.linux.mrs").coreNodeNum(3).coreNodeSize("c2.2xlarge.linux.mrs")
		.name("newcluster").availablilityZoneId("eu-de-01").vpcName("vpc1").vpcId("vpc-id").subnetName("subnet")
		.subnetId("subnet-id").version(ClusterVersion.MRS12).type(ClusterType.Stream).volumeSize(100)
		.volumeType(VolumeType.SSD).keypair("keypair").safeMode(0).components(Lists.newArrayList(component))
		.build();

// initial job exe create model
SaharaJobExeCreate jobExe = SaharaJobExeCreate.builder().jobType(JobType.MapReduce).jobName("sdk")
		.jarPath("s3a://sdk/sdk.jar").arguments("wordcount").input("s3a://input/").output("s3a://output/")
		.jobLog("s3a://log/").fileAction("").hql("").hiveScriptPath("").shutdownCluster(false)
		.submitJobOnceClusterRun(true).build();

SaharaClusterCreateResult result = osclient.sahara().clusters2().createAndRunJob(cluster, jobExe);
```

### Expand Cluster Node
```java
ActionResponse expand = osclient.sahara().clusters().expand("cluster-id", 3);
```

### Reduce Cluster Node
```java
// Not support for now
List<String> includes = Lists.newArrayList("instance-id-1");
List<String> excludes = Lists.newArrayList("instance-id-3");
ActionResponse reduce = osclient.sahara().clusters().reduce("cluster-id", 3, includes, excludes);
```

### Get Cluster
```java
SaharaCluster2 cluster = osclient.sahara().clusters2().get("0f4ab6b7-a723-4b6c-b326-f8a5711d365a");
```

### Terminate Cluster
```java
ActionResponse delete = osclient.sahara().clusters().delete("cluster-id");
```


## Job Binary
### Create Job Binary
```java
JobBinary build = SaharaJobBinary.builder().name("job-binary-name").url("/sdk/unittest/input")
				.isProtect(true).isPublic(true).description("sdk unittests").build();
JobBinary jobBinary = osclient.sahara().jobBinaries().create(build);
```

### Update Job Binary
```java
JobBinary build = SaharaJobBinary.builder().id("to-updata-job-binary-id").name("sdk-new-name")
				.url("/sdk/unittest/input2").build();
JobBinary updated = osclient.sahara().jobBinaries().update(build);
```

### List Job Binary
```java
JobBinaryListOptions options = JobBinaryListOptions.create().desc("created_at").limit(10);
List<? extends JobBinary> list = osclient.sahara().jobBinaries().list(options);
```

### Get Job Binary
```java
JobBinary jobBinary = osclient.sahara().jobBinaries().get("job-binary-id");
```

### Delete Job Binary
```java
ActionResponse delete = osclient.sahara().jobBinaries().delete("to-delete-job-binary-id");
```


## Job
### Create Job And Execute
```java
SaharaJobExeCreate jobExeCreate = SaharaJobExeCreate.builder().jobType(JobType.Spark).jobName("sdk-unittests")
				.clusterId("cluster-id").jarPath("s3a://sdk/jar.jar").arguments("wordcount").input("s3a://sdk/input")
				.output("s3a://sdk/output").jobLog("s3a://sdk/log").fileAction("export").hql("hql")
				.hiveScriptPath("s3a://sdk/script.hql").isProtected(true).isPublic(false).build();
SaharaJobExe exe = osclient.sahara().jobExes().create(jobExeCreate);
```

### Create Job
```java
Job build = SaharaJob.builder().name("job-name").type(JobType.MapReduce).description("sdk unittest").isProtect(true)
				.isPublic(true).build();
Job created = osclient.sahara().jobs().create(build);
```

### Update Job
```java
Job build = SaharaJob.builder().id("to-update-job-id").name("sdk-new-name").description("updated desc")
				.type(JobType.Spark).isProtect(false).isPublic(false).build();
Job job = osclient.sahara().jobs().update(build);
```

### Execute Job
```java
JobConfig jobConfig = SaharaJobConfig.builder().addConfig("mapred.map.tasks", 1)
		.addConfig("mapred.reduce.tasks", 1).addArg("wordcount").addParam("param1", "value1")
		.addParam("param2", "value2").build();
JobExecution jobExecution = SaharaJobExecution.builder().jobId("to-execute-job-id").clusterId("cluster-id")
		.inputId("input-id").outputId("output-id").isProtect(true).isPublic(true).setJobConfig(jobConfig)
		.build();
JobExecution execution = osclient.sahara().jobExecutions().create(jobExecution);
```

### List Job
```java
JobListOptions options = JobListOptions.create().asc("created_at").limit(10);
List<? extends Job> list = osclient.sahara().jobs().list(options);
```

### Get Job
```java
Job job = osclient.sahara().jobs().get("job-id");
```

### List Job exe
```java
JobExeListOptions options = JobExeListOptions.create().page(1).pageSize(20).clusterId("cluster-id")
		.state(JobState.Completed);
List<? extends SaharaJobExe> list = osclient.sahara().jobExes().list(options);
```

### Get Job exe
```java
osclient.sahara().jobExes().get("job-exe-id")
```

### Delete Job
```java
ActionResponse delete = osclient.sahara().jobs().delete("to-delete-job-id");
```


## Job Execution
### List Job Execution
```java
JobExecutionListOptions options = JobExecutionListOptions.create().desc("created_at").limit(10);
List<? extends JobExecution> list = osclient.sahara().jobExecutions().list(options);
```

### Get Job Execution
```java
JobExecution execution = osclient.sahara().jobExecutions().get("job-execution-id");
```

### Cancel Job Execution
```java
JobExecution execution = osclient.sahara().jobExecutions().cancel("to-cancel-job-execution-id");
```

### Delete Job Execution
```java
ActionResponse delete = osclient.sahara().jobExecutions().delete("to-delete-job-execution-id");
```