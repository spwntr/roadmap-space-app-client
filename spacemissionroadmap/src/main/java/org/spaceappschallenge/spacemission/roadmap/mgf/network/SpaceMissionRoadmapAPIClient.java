package org.spaceappschallenge.spacemission.roadmap.mgf.network;

import org.spaceappschallenge.spacemission.roadmap.mgf.model.Mission;
import org.spaceappschallenge.spacemission.roadmap.mgf.model.Missions;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by lfeliu on 4/12/14.
 */
public interface SpaceMissionRoadmapAPIClient {

    @POST("/client_registration")
    public void sendRegistrationId(@Query("registration_id") String registration_id, Callback<Response> responseCallback);
    @GET("/missions/nasa")
    public void getCurrentMissionData(Callback<Missions> responseCallback);
    @GET("/past")
    public void getPastMissionData(Callback<List<Mission>> responseCallback);

}
