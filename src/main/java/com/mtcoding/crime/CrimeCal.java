package com.mtcoding.crime;

import java.util.List;

public class CrimeCal {

    static Double showCrimePer(List<CrimeInfo> crimes, String regionInput){
        Double totalCrime = 0.0;
        Double regionTotal = 0.0;

        for(CrimeInfo crime : crimes){
            totalCrime += crime.getCount();
            if(crime.getRegion().equals(regionInput)){
                regionTotal += crime.getCount();
            }
        }
        return (regionTotal/totalCrime)*100;
    }
}
