package de.koppy.domainmodel;

import de.koppy.domainmodel.Plants.GetreideSorten;

public interface CityState {

    int getPriceperacre();
    String getStatus();
    boolean kaufen(int input);
    boolean expandDepot(int input);
    boolean verkaufen(int input);
    boolean ernähren(int input);
    boolean pflanzen(int input, GetreideSorten sorte);
    int getSizefarmland();

}
