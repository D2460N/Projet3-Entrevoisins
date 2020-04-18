package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class DeleteFavNeighbourEvent {


    public Neighbour neighbour;


    public DeleteFavNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
