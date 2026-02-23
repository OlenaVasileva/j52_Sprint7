package edu.praktikum.sprint7.generator;

import edu.praktikum.sprint7.model.Courier;

import static edu.praktikum.sprint7.random.Utils.randomString;

public class CourierGenerator {
    public static Courier randomCourier() {
        return new Courier(randomString(), randomString(), randomString());
    }
}
