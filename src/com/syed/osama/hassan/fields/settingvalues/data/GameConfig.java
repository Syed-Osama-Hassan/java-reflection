package com.syed.osama.hassan.fields.settingvalues.data;

public class GameConfig {
    private String gameName;
    private float price;
    private int releaseYear;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "GameConfig{" +
                "gameName='" + gameName + '\'' +
                ", price=" + price +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
