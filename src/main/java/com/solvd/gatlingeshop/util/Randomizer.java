package com.solvd.gatlingeshop.util;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

public class Randomizer {

    public static int getInt(int min, int max) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int r = random.nextInt(min, max);
        return r;
    }

    public static int getIntFromSeq(List<Integer> seq) {
        int result = seq.get(new Random().nextInt(seq.size()));
        return result;
    }

    public static String getName() {
        String[] names = {"Perf", "Max", "Unit", "Test"};
        return names[getInt(0, names.length)];
    }

    public static String getEmail() {
        String[] emails = {"note@ukr.net", "min@gmail.com", "mops@load.com", "now@wold.org"};
        return emails[getInt(0, emails.length)];
    }

    public static String getAddress() {
        String[] addresses = {"Kiev Ukraine", "Rome Italy", "Madrid Spain", "Minsk Belarus"};
        return addresses[getInt(0, addresses.length)];
    }

}

