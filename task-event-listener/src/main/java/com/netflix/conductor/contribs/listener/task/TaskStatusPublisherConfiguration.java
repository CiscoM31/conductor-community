/*
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.netflix.conductor.contribs.listener.task;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.conductor.common.rest.ConductorRestNotificationProperties;
import com.netflix.conductor.common.rest.RestClientManager;
import com.netflix.conductor.core.dal.ExecutionDAOFacade;
import com.netflix.conductor.core.listener.TaskStatusListener;

@Configuration
@EnableConfigurationProperties(ConductorRestNotificationProperties.class)
@ConditionalOnProperty(name = "conductor.task-status-listener.type", havingValue = "task_publisher")
public class TaskStatusPublisherConfiguration {

    @Bean
    public TaskStatusListener getTaskStatusListener(
            RestClientManager rcm,
            ExecutionDAOFacade executionDAOFacade,
            ConductorRestNotificationProperties config) {

        return new TaskStatusPublisher(rcm, executionDAOFacade, config.getSubscribedTaskStatuses());
    }
}
