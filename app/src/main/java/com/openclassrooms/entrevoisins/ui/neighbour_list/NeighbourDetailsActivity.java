package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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


import static com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity.BUNDLE_NEIGHBOUR;

public class NeighbourDetailsActivity extends AppCompatActivity {


    Neighbour myNeighbour;

    @BindView(R.id.cardview_name)
    TextView myNeighbourName;

    @BindView(R.id.neighbour_avatar)
    ImageView myNeighbourAvatarUrl;

    @BindView(R.id.cardview_location)
    TextView myNeighbourAdress;

    @BindView(R.id.cardview_phone_number)
    TextView myNeighbourPhone;

    @BindView(R.id.cardview_network_adress)
    TextView myNeighbourWebsite;

    @BindView(R.id.details_user_cardview)
    TextView myNeighbourAboutme;

    @BindView(R.id.neighbour_favorite_btn)
    FloatingActionButton myNeighbourFavoriteButton;
    private NeighbourApiService mApiService;

    @BindView(R.id.toolbar)
    Toolbar myToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();
        myNeighbour = (Neighbour) getIntent().getSerializableExtra(BUNDLE_NEIGHBOUR);
        myToolbar.setTitle(myNeighbour.getName());
        myNeighbourName.setText(myNeighbour.getName());
        myNeighbourAdress.setText(myNeighbour.getAddress());
        myNeighbourPhone.setText(myNeighbour.getPhoneNumber());
        myNeighbourWebsite.setText(myNeighbour.getWebsite());
        myNeighbourAboutme.setText(myNeighbour.getAboutMe());
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    }

    private void setStarColor() {
        if (myNeighbour.isFavorite()) {
            myNeighbourFavoriteButton.setImageResource(R.drawable.ic_star_white_24dp);
        } else {
            myNeighbourFavoriteButton.setImageResource(R.drawable.ic_star_border_white_24dp);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
