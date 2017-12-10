package com.erezults;

/**
 * Created by xsd on 26.08.2017 with love.
 * :)
 *
 * @author xsd
 * @author alekseysavin.com
 * @version 0.1
 */

public class Result {
    private float bodyMass;
    private float fatPer;
    private float bonesMass;
    private float waterPer;
    private int bodyType;
    private int basaltMetabol;
    private int bioAge;
    private int basaltMetabo2;
    private int bioAge2;
    private float visciralFat;

    public Result(float bodyMass, float fatPer, float bonesMass, float waterPer, int bodyType, int basaltMetabol, int bioAge, int basaltMetabo2, int bioAge2, float visciralFat) {
        this.bodyMass = bodyMass;
        this.fatPer = fatPer;
        this.bonesMass = bonesMass;
        this.waterPer = waterPer;
        this.bodyType = bodyType;
        this.basaltMetabol = basaltMetabol;
        this.bioAge = bioAge;
        this.basaltMetabo2 = basaltMetabo2;
        this.bioAge2 = bioAge2;
        this.visciralFat = visciralFat;
    }

    public float getBodyMass() {
        return bodyMass;
    }

    public float getFatPer() {
        return fatPer;
    }

    public float getBonesMass() {
        return bonesMass;
    }

    public float getWaterPer() {
        return waterPer;
    }

    public int getBodyType() {
        return bodyType;
    }

    public int getBasaltMetabol() {
        return basaltMetabol;
    }

    public int getBioAge() {
        return bioAge;
    }

    public int getBasaltMetabo2() {
        return basaltMetabo2;
    }

    public int getBioAge2() {
        return bioAge2;
    }

    public float getVisciralFat() {
        return visciralFat;
    }

    @Override
    public String toString() {
        return "Result{" +
                "bodyMass=" + bodyMass +
                ", fatPer=" + fatPer +
                ", bonesMass=" + bonesMass +
                ", waterPer=" + waterPer +
                ", bodyType=" + bodyType +
                ", basaltMetabol=" + basaltMetabol +
                ", bioAge=" + bioAge +
                ", basaltMetabo2=" + basaltMetabo2 +
                ", bioAge2=" + bioAge2 +
                ", visciralFat=" + visciralFat +
                '}';
    }
}

