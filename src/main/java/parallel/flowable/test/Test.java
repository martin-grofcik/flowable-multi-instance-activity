package parallel.flowable.test;

import java.util.List;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration().setJdbcUrl("jdbc:h2:mem:flowable1")
            .setJdbcUsername("sa")
            .setJdbcPassword("")
            .setJdbcDriver("org.h2.Driver")
            .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
//            .setAsyncExecutor(new org.flowable.job.service.impl.asyncexecutor.DefaultAsyncJobExecutor());

        ProcessEngine processEngine = cfg.buildProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
            .addClasspathResource("diagrams/main-process.bpmn")
            .deploy();

        Deployment deployment2 = repositoryService.createDeployment()
            .addClasspathResource("diagrams/sub-process.bpmn")
            .deploy();

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
            .deploymentId(deployment.getId())
            .singleResult();
        System.out.println("Found process definition : " + processDefinition.getKey());

        ProcessDefinition processDefinition2 = repositoryService.createProcessDefinitionQuery()
            .deploymentId(deployment2.getId())
            .singleResult();
        System.out.println("Found process definition : " + processDefinition2.getKey());

        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("mainProcess");
        System.out.println("Process started " + processInstance + " ended " + processInstance.isEnded());
        while (!processInstance.isEnded()) {
        	Thread.sleep(2000);
            List<Execution> processInstanceExecutions = processEngine.getRuntimeService()
                .createExecutionQuery()
                .processInstanceId(processInstance.getProcessInstanceId())
                .list();

            System.out.println("You have: " + processInstanceExecutions.size() + " executions");
            for (Execution execution : processInstanceExecutions) {
                System.out.println(execution.getActivityId());
            }
        }

        Thread.sleep(100000);
    }
}
