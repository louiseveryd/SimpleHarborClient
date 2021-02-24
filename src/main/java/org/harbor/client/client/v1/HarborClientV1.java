package org.harbor.client.client.v1;


import org.harbor.client.client.v1.op.ProjectHandler;
import org.harbor.client.client.v1.op.Projects;

/**
 * @author lr
 * @date 2021/2/5
 */
public interface HarborClientV1 {

    String API_BASE = "/api/v2.0";

    Projects projects();

    ProjectHandler project(String projectName);


}
