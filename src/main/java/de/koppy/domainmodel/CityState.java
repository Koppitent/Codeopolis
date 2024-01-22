package de.koppy.domainmodel;

public interface CityState {

    int getPriceperacre();
    String getStatus();
    boolean kaufen(int input);
    boolean expandDepot(int input);
    boolean verkaufen(int input);
    boolean ernähren(int input);
    boolean pflanzen(int input);
    int getSizefarmland();

}
