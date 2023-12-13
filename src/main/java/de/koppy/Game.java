package de.koppy;

public class Game {

    private City city;

    public Game(String cityname) {
        this.city = new City(cityname);
    }

    public City getCity() {
        return city;
    }

    public void resetCity(String name) {
        this.city = new City(name);
    }
}
