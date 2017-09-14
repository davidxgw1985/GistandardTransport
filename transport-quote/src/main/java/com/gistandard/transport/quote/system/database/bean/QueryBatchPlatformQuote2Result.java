package com.gistandard.transport.quote.system.database.bean;

import com.gistandard.transport.system.webservice.client.calcWebService.PlatformQuote;

import java.util.List;
import java.util.Map;

/**
 * Created by m on 2016/12/13.
 */
public class QueryBatchPlatformQuote2Result {
    private List<CheckAssignOrderforbatchFailed> faileds;
    private Map<String, PlatformQuote> platformQuoteMap;

    public List<CheckAssignOrderforbatchFailed> getFaileds() {
        return faileds;
    }

    public void setFaileds(List<CheckAssignOrderforbatchFailed> faileds) {
        this.faileds = faileds;
    }

    public Map<String, PlatformQuote> getPlatformQuoteMap() {
        return platformQuoteMap;
    }

    public void setPlatformQuoteMap(Map<String, PlatformQuote> platformQuoteMap) {
        this.platformQuoteMap = platformQuoteMap;
    }
}
