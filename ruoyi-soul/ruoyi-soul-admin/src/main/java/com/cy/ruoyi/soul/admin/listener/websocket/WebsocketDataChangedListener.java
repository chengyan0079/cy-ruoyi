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

package com.cy.ruoyi.soul.admin.listener.websocket;

import com.cy.ruoyi.soul.admin.listener.AbstractDataChangedListener;
import com.cy.ruoyi.soul.admin.listener.DataChangedListener;
import org.dromara.soul.common.dto.*;
import org.dromara.soul.common.enums.ConfigGroupEnum;
import org.dromara.soul.common.enums.DataEventTypeEnum;
import org.dromara.soul.common.utils.GsonUtils;

import java.util.List;

/**
 * The type Websocket data changed listener.
 *
 * @author xiaoyu(Myth)
 * @author huangxiaofeng
 * @since 2.0.0
 */
public class WebsocketDataChangedListener extends AbstractDataChangedListener implements DataChangedListener {

    @Override
    public void onPluginChanged(final List<PluginData> pluginDataList, final DataEventTypeEnum eventType) {
        WebsocketData<PluginData> websocketData =
                new WebsocketData<>(ConfigGroupEnum.PLUGIN.name(), eventType.name(), pluginDataList);
        WebsocketCollector.send(GsonUtils.getInstance().toJson(websocketData), eventType);
    }

    @Override
    public void onSelectorChanged(final List<SelectorData> selectorDataList, final DataEventTypeEnum eventType) {
        WebsocketData<SelectorData> websocketData =
                new WebsocketData<>(ConfigGroupEnum.SELECTOR.name(), eventType.name(), selectorDataList);
        WebsocketCollector.send(GsonUtils.getInstance().toJson(websocketData), eventType);
    }

    @Override
    public void onRuleChanged(final List<RuleData> ruleDataList, final DataEventTypeEnum eventType) {
        WebsocketData<RuleData> configData =
                new WebsocketData<>(ConfigGroupEnum.RULE.name(), eventType.name(), ruleDataList);
        WebsocketCollector.send(GsonUtils.getInstance().toJson(configData), eventType);
    }

    @Override
    public void onAppAuthChanged(final List<AppAuthData> appAuthDataList, final DataEventTypeEnum eventType) {
        WebsocketData<AppAuthData> configData =
                new WebsocketData<>(ConfigGroupEnum.APP_AUTH.name(), eventType.name(), appAuthDataList);
        WebsocketCollector.send(GsonUtils.getInstance().toJson(configData), eventType);
    }

    @Override
    public void onMetaDataChanged(final List<MetaData> metaDataList, final DataEventTypeEnum eventType) {
        WebsocketData<MetaData> configData =
                new WebsocketData<>(ConfigGroupEnum.META_DATA.name(), eventType.name(), metaDataList);
        WebsocketCollector.send(GsonUtils.getInstance().toJson(configData), eventType);
    }

}
