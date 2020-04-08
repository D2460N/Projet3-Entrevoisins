package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity.BUNDLE_NEIGHBOUR;

public class NeighbourDetailsActivity<fabListener> extends AppCompatActivity {
    private FloatingActionButton mFavoriteFab;

    Neighbour myNeighbour;

    @BindView(R.id.my_neighbour_name)
    TextView myNeighbourName;

    @BindView(R.id.neighbour_avatar)
    ImageView myNeighbourAvatarUrl;

    @BindView(R.id.cardview_location)
    TextView myNeighbourAdress;

    @BindView(R.id.cardview_phone_number)
    TextView myNeighbourPhone;

    @BindView(R.id.details_user_cardview)
    TextView myNeighbourAboutme;

    @BindView(R.id.neighbour_favorite_btn)
    FloatingActionButton myNeighbourFavoriteButton;
    private NeighbourApiService mApiService;

    @BindView(R.id.image_details_back_arrow)
    ImageButton BackArrowButton;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();
        myNeighbour = (Neighbour) getIntent().getSerializableExtra(BUNDLE_NEIGHBOUR);
        myNeighbourName.setText(myNeighbour.getName());
        myNeighbourAdress.setText(myNeighbour.getAddress());
        myNeighbourPhone.setText(myNeighbour.getPhoneNumber());
        myNeighbourAboutme.setText(myNeighbour.getAboutMe());

        Glide.with(this).load(myNeighbour.getAvatarUrl()).into(myNeighbourAvatarUrl);
        setStarColor();
        myNeighbourFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiService.toggleFavoriteNeighbour(myNeighbour);
                myNeighbour.setFavorite(!myNeighbour.isFavorite());
                setStarColor();






            }
        });
        ImageButton backArrow = findViewById(R.id.image_details_back_arrow);
        backArrow.setOnClickListener(backListener);
    }
    private void setStarColor() {
        if (myNeighbour.isFavorite()){
            myNeighbourFavoriteButton.setImageResource(R.drawable.ic_star_white_24dp);
        }
        else {myNeighbourFavoriteButton.setImageResource(R.drawable.ic_star_border_white_24dp);}
    }

    View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
}
