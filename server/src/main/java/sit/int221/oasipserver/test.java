package sit.int221.oasipserver;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class test {
    public static void main(String[] args) {
        Instant e1 = Instant.parse("2022-05-23T06:59:00Z");
        Integer e1D = 20;
        Instant e2 = Instant.parse("2022-05-23T06:30:00Z");
        Integer e2D = 30;
//        System.out.println(Instant.parse("2022-05-23T06:30:00Z").compareTo(Instant.parse("2022-05-24T06:30:00Z")));
//        System.out.println(Instant.parse("2022-05-23T06:30:00Z").compareTo(Instant.parse("2022-05-22T06:30:00Z")));
//        System.out.println(Instant.parse("2022-05-23T06:00:00Z").plus(30, ChronoUnit.MINUTES));
//        System.out.println(Instant.parse("2022-05-23T06:30:00Z").isBefore(Instant.parse("2022-05-23T06:00:00Z").plus(30, ChronoUnit.MINUTES)));
//        System.out.println(Instant.parse("2022-05-23T06:29:00Z").isBefore(Instant.parse("2022-05-23T06:00:00Z").plus(30, ChronoUnit.MINUTES)));
//        System.out.println(Instant.parse("2022-05-23T06:30:00Z").equals(Instant.parse("2022-05-23T06:00:00Z").plus(30, ChronoUnit.MINUTES)));

//        System.out.println(e1.isBefore(e2));
        System.out.println(e1.isAfter(e2) || e1.equals(e2));
//        System.out.println(e1.equals(e2));
        System.out.println("-");
        System.out.println(e1.isBefore(e2.plus(e2D, ChronoUnit.MINUTES)));
//        System.out.println(e1.isAfter(e2.plus(e1D, ChronoUnit.MINUTES)));
//        System.out.println(e1.equals(e2.plus(e1D, ChronoUnit.MINUTES)));
        Boolean isSTBetween = (e1.isAfter(e2) || e1.equals(e2)) && e1.isBefore(e2.plus(e2D, ChronoUnit.MINUTES));
        Boolean isETBetween = e1.plus(e1D, ChronoUnit.MINUTES).isAfter(e2) && e1.plus(e1D, ChronoUnit.MINUTES).isBefore(e2.plus(e2D, ChronoUnit.MINUTES));
        System.out.println("------");
        System.out.println(e1.plus(e1D, ChronoUnit.MINUTES).isAfter(e2));
//        System.out.println(e1.isAfter(e2));
//        System.out.println(e1.equals(e2));
        System.out.println("-");
        System.out.println(e1.plus(e1D, ChronoUnit.MINUTES).isBefore(e2.plus(e2D, ChronoUnit.MINUTES)));
//        System.out.println(e1.isAfter(e2.plus(e1D, ChronoUnit.MINUTES)));
//        System.out.println(e1.equals(e2.plus(e1D, ChronoUnit.MINUTES)));
        System.out.println();
        System.out.println("Ans: "+ (isSTBetween || isETBetween));
//        System.out.println(e1.plus(e1D, ChronoUnit.MINUTES).isBefore(e2));
//        System.out.println(e1.plus(e1D, ChronoUnit.MINUTES).isAfter(e2));
//        System.out.println(e1.plus(e1D, ChronoUnit.MINUTES).equals(e2));
//        System.out.println("------");
//        System.out.println(e1.plus(e1D, ChronoUnit.MINUTES).isBefore(e2));
//        System.out.println(e1.plus(e1D, ChronoUnit.MINUTES).equals(e2));

    }
//    public Boolean checkBetween()
}
