package org.spaceappschallenge.spacemission.roadmap.mgf.network;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by lfeliu on 4/12/14.
 */
public interface SpaceMissionRoadmapAPIClient {

    @POST("/registration")
    public void sendRegistrationId(@Query("registration_id") String registrationId, Callback<Response> responseCallback);
    @GET("/current")
    public void getCurrentMissionData(Callback<Response> responseCallback);
    @GET("/historical")
    public void getHistoricalMissionData(Callback<Response> responseCallback);

}
