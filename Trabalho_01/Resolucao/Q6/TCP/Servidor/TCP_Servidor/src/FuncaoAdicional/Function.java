package FuncaoAdicional;

import java.util.Random;

public class Function {
    public String RandomYoutubeMusic(){
        String link = "";
        Random random = new Random();        
        switch(random.nextInt(15)){
            case 0:
                link="https://www.youtube.com/watch?v=DtJzRErAJ3Q";//Metallica - Enter Sandman
                break;
            case 1:
                link="https://www.youtube.com/watch?v=5BmEGm-mraE";//Creedence Clearwater Revival: Bad Moon Rising
                break;
            case 2:
                link="https://www.youtube.com/watch?v=egMWlD3fLJ8";//Steppenwolf - Born To Be Wild 
                break;
            case 3:
                link="https://www.youtube.com/watch?v=IaXzcTrY0Vg";//Manowar - Warriors Of The World
                break;
            case 4:
                link="https://www.youtube.com/watch?v=IoyToHOWSV8";//Blind Guardian - Nightfall
                break;
            case 5:
                link="https://www.youtube.com/watch?v=J51LPlP-s9o";//Iron Maiden - Hallowed Be Thy Name
                break;
            case 6:
                link="https://www.youtube.com/watch?v=p1SlBlB5pzU";//SABATON - Sparta
                break;
            case 7:
                link="https://www.youtube.com/watch?v=55OJ17cHeJA";//Amon Amarth - The Way of Vikings
                break;
            case 8:
                link="https://www.youtube.com/watch?v=NeQM1c-XCDc";//Rammstein - Deutschland
                break;
            case 9:
                link="https://www.youtube.com/watch?v=jmIW71l5rck";//The White Buffalo - House Of Pain
                break;
            case 10:
                link="https://www.youtube.com/watch?v=T80B7s7ekGo";//Blackberry Smoke - One Horse Town
                break;
            case 11:
                link="https://www.youtube.com/watch?v=wYVPHcT1CsA";//KORPIKLAANI - Henkselipoika
                break;
            case 12:
                link="https://www.youtube.com/watch?v=-w2m-TeLi6I";//ELUVEITIE - The Call Of The Mountains
                break;
            case 13:
                link="https://www.youtube.com/watch?v=RXP7JCdV2jI";//Bobaflex - Bury me with my guns on
                break;
            case 14:
                link="https://www.youtube.com/watch?v=9d8SzG4FPyM";//Dropkick Murphys - Rose Tattoo
                break;            
        }
        return link;
    }
}
