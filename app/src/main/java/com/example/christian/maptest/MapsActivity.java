package com.example.christian.maptest;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.HashMap;
import java.util.Map;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

import static com.example.christian.maptest.R.layout.nav_header_main;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

    private GoogleMap mMap;
    View mapView;
    private Map<Marker, Class> allMarkersMap = new HashMap<Marker, Class>();
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private Button mButtonShow;
    private Button mButtonReset;

    private static final String SHOWCASE_ID = "simple example";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapView = mapFragment.getView();
        mapFragment.getMapAsync(this);

    //mMap.getUiSettings().setMyLocationButtonEnabled(true);


        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);// Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);// Setting toolbar as the ActionBar with setSupportActionBar() call


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        //        .findFragmentById(R.id.map);
        //mapFragment.getMapAsync(this);


        //mMap.setMyLocationEnabled(true);

        //if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

        //    ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
        //            LocationService.MY_PERMISSION_ACCESS_COURSE_LOCATION );
        //}



        //mAdView = (AdView) findViewById(R.id.adView);
        //AdRequest adRequest = new AdRequest.Builder()
        //        .build();
        //mAdView.loadAd(adRequest);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMyLocationEnabled(true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","apphundezone@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Das muss noch verbessert werden:");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Was muss deiner Meinung nach noch verbessert werden?");
                startActivity(Intent.createChooser(emailIntent, "Feedback senden"));
            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","apphundezone@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Giftköder gefunden:");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Wo hast du den Giftköder gefunden? Wie sieht er aus?");
                startActivity(Intent.createChooser(emailIntent, "Giftköder melden"));
            }
        });


        //mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
        //    @Override
        //    public void onInfoWindowClick(Marker marker) {
        //       Intent intent = new Intent(MapsActivity.this,MarkerClickActivity.class);
        //        startActivity(intent);
        //    }
        //});


        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.215184, 16.373053))
                .title("Giftwarnung 30.01.2021")
                .snippet("Blauer Dünger")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.gift_small)));
        //allMarkersMap.put(marker, WelcomeActivity.class);
        //googleMap.setOnInfoWindowClickListener(this);

        LatLng stadtpark = new LatLng(48.203933, 16.380142);
        mMap.addMarker(new MarkerOptions().position(stadtpark).title("Stadtpark, Wienflusspromenade").snippet("Größe: 1.900 m²")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.gift_small))
                );

        LatLng augarten = new LatLng(48.226449, 16.371114);
        mMap.addMarker(new MarkerOptions().position(augarten).title("Augarten, gegenüber Wasnergasse 3").snippet("Größe: 1.161 m²"));

        LatLng augarten2 = new LatLng(48.224520, 16.378882);
        mMap.addMarker(new MarkerOptions().position(augarten2).title("Augarten, Schloßplatz").snippet("Größe: 2.000 m²"));

        LatLng engerthstraße = new LatLng(48.214643, 16.414820);
        mMap.addMarker(new MarkerOptions().position(engerthstraße).title("Parkanlage Engerthstraße").snippet("Größe: 640 m²"));

        LatLng manes = new LatLng(48.213649, 16.377991);
        mMap.addMarker(new MarkerOptions().position(manes).title("Manes-Sperber-Park").snippet("Größe: 320 m²"));

        LatLng max = new LatLng(48.219650, 16.400608);
        mMap.addMarker(new MarkerOptions().position(max).title("Max-Winter-Park (Hundeklo)").snippet("Größe: 460 m²"));

        LatLng mexikoplatz = new LatLng(48.225647, 16.403840);
        mMap.addMarker(new MarkerOptions().position(mexikoplatz).title("Mexikoplatz/Rosenpark").snippet("Größe: 4.650 m²"));

        LatLng parkanlage1 = new LatLng(48.223830, 16.390637);
        mMap.addMarker(new MarkerOptions().position(parkanlage1).title("Parkanlage Nordbahnstraße").snippet("Größe: 265 m²"));

        LatLng parkanlage2 = new LatLng(48.215535, 16.416754);
        mMap.addMarker(new MarkerOptions().position(parkanlage2).title("Parkanlage Offenbachgasse").snippet("Größe: 1.600 m²"));

        LatLng prater1 = new LatLng(48.215121, 16.394945);
        mMap.addMarker(new MarkerOptions().position(prater1).title("Prater - Laufbergerwiese").snippet("Größe: 1.020 m²"));

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(augarten));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(13.0f));


        if (mapView != null &&
                mapView.findViewById(Integer.parseInt("1")) != null) {
            // Get the button view
            View locationButton = ((View) mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
            // and next place it, on bottom right (as Google Maps app)
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
                    locationButton.getLayoutParams();
            // position on right bottom
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            layoutParams.setMargins(0, 0, 30, 30);
        }

    }


    public void onInfoWindowClick(Marker marker) {
        Class cls = allMarkersMap.get(marker);
        Intent intent = new Intent(MapsActivity.this, cls);
        startActivity(intent);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Drücke erneut um Hundezone zu beenden", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
    //    // Inflate the menu; this adds items to the action bar if it is present.
    //    getMenuInflater().inflate(R.menu.main_menu, menu);
    //    return true;
    //}

    //private Bitmap getMarkerBitmapFromView(@DrawableRes int resId) {

    //    View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_custom_marker, null);
    //    //ImageView markerImageView = (ImageView) customMarkerView.findViewById(R.id.profile_image);
        //markerImageView.setImageResource(resId);
    //    customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
    //    customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
    //    customMarkerView.buildDrawingCache();
    //    Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
    //            Bitmap.Config.ARGB_8888);
    //    Canvas canvas = new Canvas(returnedBitmap);
    //    canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
    //    Drawable drawable = customMarkerView.getBackground();
    //    if (drawable != null)
    //        drawable.draw(canvas);
    //    customMarkerView.draw(canvas);
    //    return returnedBitmap;
    //}

    //@Override
    //public void onPause() {
    //    if (mAdView != null) {
    //        mAdView.pause();
    //    }
    //    super.onPause();
    //}

    //@Override
    //public void onResume() {
    //    super.onResume();
    //    if (mAdView != null) {
    //        mAdView.resume();
    //    }
    //}

    //@Override
    //public void onDestroy() {
    //    if (mAdView != null) {
    //        mAdView.destroy();
    //    }
    //    super.onDestroy();
    //}

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
