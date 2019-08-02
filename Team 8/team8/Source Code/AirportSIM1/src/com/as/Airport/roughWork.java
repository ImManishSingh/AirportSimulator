package com.as.Airport;

import com.as.Aircraft.Aircraft;

import java.util.LinkedList;



//***************************************************************************************************************************//

public class roughWork {

    public static void doChange(int numberOfGates)
    {
        LinkedList<Aircraft> aircraftLinkedList = Resources.getAircraftLinkedList();
        LinkedList<Runway> runwayLinkedList = Resources.runwayLinkedList;
        LinkedList<Gate> gateLinkedList = Resources.gateLinkedList;
        for (int flightLinkedListCounter = 0; flightLinkedListCounter < aircraftLinkedList.size(); flightLinkedListCounter++)
        {

            for (int x=0;x<10; x++){}
            Aircraft ac= aircraftLinkedList.get(flightLinkedListCounter);
            String state=ac.state;
            if (ac.state.equals("z"))
            {
                if (Resources.s_count < Resources.getFlightBuffer())
                {
                    ac.state = "s";
                }
            }
            // This loop will change the state of flight from s to r'.
            if (ac.state.equals("s"))
            {
                boolean s_state_flag = false;

                // The variable j denotes the number of runways.
                for (int runwayIterator = 0; runwayIterator < Runway.getNoOfRunways(); runwayIterator++)
                {
                    // This loop will check the priority of the fight for the runway.
                    for (int outerIterator = 0; outerIterator < Resources.getAircraftLinkedList().size(); outerIterator++)
                    {
                        for (int innerIterator = 0; innerIterator < Resources.getAircraftLinkedList().size() && innerIterator != outerIterator; innerIterator++)
                        {
                            if (Resources.getAircraftLinkedList().get(outerIterator).sWaitTime >= Resources.getAircraftLinkedList().get(innerIterator).sWaitTime
                                    && Resources.getAircraftLinkedList().get(outerIterator).sWaitTime >= Resources.getAircraftLinkedList().get(innerIterator).eWaitTime
                                    && runwayLinkedList.get(runwayIterator).status == true)
                            {
                                ac.state = "r'";
                                ac.RunwayNumber = runwayLinkedList.get(runwayIterator).runwayNumber;
                                Resources.s_count--;
                                ac.sfwt += ac.sWaitTime;

                                ac.sWaitTime = 0;
                                runwayLinkedList.get(runwayIterator).status = false;
                                s_state_flag = true;
                                break;

                            }
                        }

                    }
                }
                if (s_state_flag == false)
                    ac.sWaitTime++;
            }
            // This loop will change the state of flight from r' to a.

            if (ac.state.equals("r'"))
            {
                ac.rdstatetimer++;
                // The variable j denotes the number of runways.
                for (int j = 0; j < Runway.getNoOfRunways(); j++)
                {
                    if (runwayLinkedList.get(j).status == false)
                    {
                        runwayLinkedList.get(j).rdt++;
                    }
                    if (runwayLinkedList.get(j).rdt == ac.at.runwayTime)
                    {
                        runwayLinkedList.get(j).status = true;
                        runwayLinkedList.get(j).rdt = 0;
                    }
                }
                if (ac.rdstatetimer == ac.at.runwayTime)
                {
                    ac.state = "a";
                    runwayLinkedList.get(ac.RunwayNumber).status = true;
                }
            }
            // This loop will change the state of flight from a to b.
            else if (ac.state.equals("a"))
            {
                ac.astatetimer++;
                if (ac.astatetimer == Resources.taxiTime) {
                    ac.state = "b";
                }
            }
            // This loop will change the state of flight from b to c.
            else if (ac.state.equals("b"))
            {
                boolean b_flag = false;
                // The j variable denotes the number of gates
                int j;
                for (j = 0; j < numberOfGates; j++)
                {
                    if (gateLinkedList.get(j).status == true)
                    {
                        ac.state = "c";
                        ac.gateNo = j;
                        gateLinkedList.get(j).status = false;
                        b_flag = true;
                        break;
                    }
                }
                if (b_flag == false)
                    ac.bWaitTime++;
            }
            // This loop will change the state of flight from c to d.
            if (ac.state.equals("c"))
            {
                ac.cstatetimer++;
                if (ac.cstatetimer == ac.at.boardingTime)
                {
                    ac.state = "d";
                    gateLinkedList.get(ac.gateNo).status = true;
                }
            }
            // This loop will change the state of flight from d to e.
            if (ac.state.equals("d"))
            {
                ac.dstatetimer++;
                if (ac.dstatetimer == Resources.taxiTime)
                    ac.state = "e";
            }
            // This loop will change the state of flight from e to r.
            else if (ac.state.equals("e"))
            {
                boolean e_flag = false;
                // The j variable denotes the number of runways.
                for (int runwayIterator = 0; runwayIterator < Runway.getNoOfRunways(); runwayIterator++)
                {
                    // This loop will check the priority of the fight for the runway.
                    for (int outerIterator = 0; outerIterator < Resources.getAircraftLinkedList().size(); outerIterator++)
                    {
                        for (int innerItaretor = 0; innerItaretor < Resources.getAircraftLinkedList().size() && innerItaretor != outerIterator; innerItaretor++)
                        {
                            if (Resources.getAircraftLinkedList().get(outerIterator).eWaitTime >= Resources.getAircraftLinkedList().get(innerItaretor).sWaitTime
                                    &&Resources.getAircraftLinkedList().get(outerIterator).eWaitTime >= Resources.getAircraftLinkedList().get(innerItaretor).eWaitTime)
                            {
                                if (runwayLinkedList.get(runwayIterator).status == true)
                                {
                                    ac.state = "r";
                                    ac.RunwayNumber = runwayLinkedList.get(runwayIterator).runwayNumber;
                                    runwayLinkedList.get(runwayIterator).status = false;
                                    e_flag = true;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (e_flag == false)
                    ac.eWaitTime++;
            }
            // This loop will change the state of flight from r to x.
            if (ac.state.equals("r"))
            {
                ac.rstatetimer++;
                if (ac.rstatetimer == ac.at.runwayTime)
                {
                    ac.state = "x";
                    runwayLinkedList.get(ac.RunwayNumber).status = true;
                    ac.efwt += ac.eWaitTime;
                    ac.eWaitTime = 0;

                    ac.cstatetimer = 0;
                    ac.astatetimer = 0;
                    ac.dstatetimer = 0;
                    ac.rstatetimer = 0;
                    ac.rdstatetimer = 0;

                }
            }

        }
    }

}


