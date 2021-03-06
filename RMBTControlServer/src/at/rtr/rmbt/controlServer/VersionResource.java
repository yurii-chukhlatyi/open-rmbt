/*******************************************************************************
 * Copyright 2013-2015 alladin-IT GmbH
 * Copyright 2013-2015 Rundfunk und Telekom Regulierungs-GmbH (RTR-GmbH)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package at.rtr.rmbt.controlServer;


import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Parameter;
import org.restlet.resource.Get;
import org.restlet.util.Series;

import at.rtr.rmbt.shared.RevisionHelper;

public class VersionResource extends ServerResource
{
    @Get("json")
    public String request(final String entity)
    {
        addAllowOrigin();
        try
        {
            final JSONObject answer = new JSONObject();
            answer.put("version", RevisionHelper.getVerboseRevision());
            answer.put("system_UUID", getSetting("system_UUID",""));
            final Series<Parameter> ctxParams = getContext().getParameters();
            final String hostId = ctxParams.getFirstValue("HOST_ID");
            if (hostId != null)
                answer.put("host",hostId);
            
            return answer.toString();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
