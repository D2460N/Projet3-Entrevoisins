package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class ShowNeighbourDetailsEvent {

    /**
     * Neighbour to show
     */
    public Neighbour neighbour;

    /**
     * Constructor.k
     *
     * @param neighbour
     */
    public ShowNeighbourDetailsEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
