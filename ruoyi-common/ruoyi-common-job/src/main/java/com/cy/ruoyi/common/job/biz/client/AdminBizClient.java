package com.cy.ruoyi.common.job.biz.client;

import com.cy.ruoyi.common.job.biz.AdminBiz;
import com.cy.ruoyi.common.job.biz.model.HandleCallbackParam;
import com.cy.ruoyi.common.job.biz.model.RegistryParam;
import com.cy.ruoyi.common.job.biz.model.ReturnT;
import com.cy.ruoyi.common.job.util.XxlJobRemotingUtil;

import java.util.List;

/**
 * admin api test
 *
 * @author xuxueli 2017-07-28 22:14:52
 */
public class AdminBizClient implements AdminBiz {

    public AdminBizClient() {
    }
    public AdminBizClient(String addressUrl, String accessToken) {
        this.addressUrl = addressUrl;
        this.accessToken = accessToken;

        // valid
        if (!this.addressUrl.endsWith("/")) {
            this.addressUrl = this.addressUrl + "/";
        }
    }

    private String addressUrl ;
    private String accessToken;


    @Override
    public ReturnT<String> callback(List<HandleCallbackParam> callbackParamList) {
        return XxlJobRemotingUtil.postBody(addressUrl+"api/callback", accessToken, callbackParamList, 3);
    }

    @Override
    public ReturnT<String> registry(RegistryParam registryParam) {
        return XxlJobRemotingUtil.postBody(addressUrl + "api/registry", accessToken, registryParam, 3);
    }

    @Override
    public ReturnT<String> registryRemove(RegistryParam registryParam) {
        return XxlJobRemotingUtil.postBody(addressUrl + "api/registryRemove", accessToken, registryParam, 3);
    }
}
