package org.spaceappschallenge.spacemission.roadmap.mgf.service;

import org.spaceappschallenge.spacemission.roadmap.mgf.model.Mission;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by thoughtworker on 4/12/14.
 */
public class DataSource {
    private List<Mission> missionList = new ArrayList<Mission>();



    public List<Mission> getMissions(String category){
        return makeFakeMissions(category);
    }

    private List<Mission> makeFakeMissions(String category) {

        MissionEventDate missionEventDate = new MissionEventDate();
        missionEventDate.start = new Date(1967, 1, 27, 18, 31, 05);
        missionEventDate.end = new Date(1967, 1, 27, 18, 31, 05);

        Mission missionOne = new Mission();
        missionOne.title = category + " Apollo 1 Tragedy";
        missionOne.url = "http://www.nasa.gov/mission_pages/apollo/missions/apollo1.html";
        missionOne.image = "http://www.nasa.gov/sites/default/files/images/338807main_01-lg.jpg";
        missionOne.description = "Jan. 27, 1967, tragedy struck on the launch pad at Cape Kennedy during a preflight test for Apollo 204 (AS-204). The mission was to be the first crewed flight of Apollo, and was scheduled to launch Feb. 21, 1967. Astronauts Virgil Grissom, Edward White and Roger Chaffee lost their lives when a fire swept through the command module, or CM.\n" +
                "\n" +
                "The exhaustive investigation of the fire and extensive reworking of the Apollo command modules postponed crewed launches until NASA officials cleared them for flight. Saturn IB schedules were suspended for nearly a year, and the launch vehicle that finally bore the designation AS-204 carried a lunar module, or LM, as the payload, instead of a CM. The missions of AS-201 and AS-202 with Apollo spacecraft aboard had been unofficially known as Apollo 1 and Apollo 2 missions. AS-203 carried only the aerodynamic nose cone.\n" +
                "\n" +
                "In the spring of 1967, NASA's Associate Administrator for Manned Space Flight, Dr. George E. Mueller, announced that the mission originally scheduled for Grissom, White and Chaffee would be known as Apollo 1, and said that the first Saturn V launch, scheduled for November 1967, would be known as Apollo 4. The eventual launch of AS-204 became known as the Apollo 5 mission. No missions or flights were ever designated Apollo 2 or 3.\n" +
                "\n" +
                "The second launch of a Saturn V took place on schedule in the early morning of April 4, 1968. Known as AS-502, or Apollo 6, the flight was a success, though two first-stage engines shut down prematurely, and the third-stage engine failed to reignite after reaching orbit.";
        missionOne.launchDate = missionEventDate;

        missionList.add(missionOne);

        MissionEventDate missionEventDateTwo = new MissionEventDate();
        missionEventDateTwo.start = new Date(1968, 10, 11, 11, 02, 45);
        missionEventDateTwo.end = new Date(1968, 10, 11, 11, 02, 45);

        Mission missionTwo = new Mission();
        missionTwo.title = category + " Apollo 7";
        missionTwo.url = "http://www.nasa.gov/mission_pages/apollo/missions/apollo7.html";
        missionTwo.image = "http://www.nasa.gov/images/content/338804main_07-lg.jpg";
        missionTwo.description = "The primary objectives for the Apollo 7 engineering test flight were simple: Demonstrate command and service module, or CSM, and crew performance; demonstrate crew, space vehicle and mission support facilities performance during a crewed CSM mission; and demonstrate CSM rendezvous capability.";
        missionTwo.launchDate = missionEventDateTwo;

        missionList.add(missionTwo);

        return missionList;
    }
}