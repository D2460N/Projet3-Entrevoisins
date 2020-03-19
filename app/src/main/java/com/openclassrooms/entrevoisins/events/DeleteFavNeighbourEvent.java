package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class DeleteFavNeighbourEvent {


        public Neighbour isFavorite;


        public DeleteFavNeighbourEvent(Neighbour neighbour) {
            this.isFavorite = neighbour;
        }
}
