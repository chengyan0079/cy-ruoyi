/*
 *   Licensed to the Apache Software Foundation (ASF) under one or more
 *   contributor license agreements.  See the NOTICE file distributed with
 *   this work for additional information regarding copyright ownership.
 *   The ASF licenses this file to You under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.cy.ruoyi.soul.admin.listener;

import org.dromara.soul.common.dto.*;
import org.dromara.soul.common.enums.DataEventTypeEnum;

import java.util.List;

/**
 * Event listener, used to send notification of event changes,
 * used to support HTTP, websocket, zookeeper and other event notifications.
 *
 * @author huangxiaofeng
 * @author xiaoyu
 */
public interface DataChangedListener {

    /**
     * invoke this method when AppAuth was received.
     *
     * @param changed   the changed
     * @param eventType the event type
     */
    default void onAppAuthChanged(List<AppAuthData> changed, DataEventTypeEnum eventType) {
    }


    /**
     * invoke this method when Plugin was received.
     *
     * @param changed   the changed
     * @param eventType the event type
     */
    default void onPluginChanged(List<PluginData> changed, DataEventTypeEnum eventType) {
    }


    /**
     * invoke this method when Selector was received.
     *
     * @param changed   the changed
     * @param eventType the event type
     */
    default void onSelectorChanged(List<SelectorData> changed, DataEventTypeEnum eventType) {
    }

    /**
     * On meta data changed.
     *
     * @param changed   the changed
     * @param eventType the event type
     */
    default void onMetaDataChanged(List<MetaData> changed, DataEventTypeEnum eventType) {

    }


    /**
     * invoke this method when Rule was received.
     *
     * @param changed   the changed
     * @param eventType the event type
     */
    default void onRuleChanged(List<RuleData> changed, DataEventTypeEnum eventType) {
    }

}
