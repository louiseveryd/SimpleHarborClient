package org.harbor.client.client.op;

import org.harbor.client.client.model.OverallHealthStatus;

/**
 * @author lr
 * @date 2021/2/26
 */
public interface Healths {
    OverallHealthStatus get();
}
