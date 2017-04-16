package com.example.christian.maptest;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<Album> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //assert navigationView != null;
        //navigationView.setNavigationItemSelectedListener(this);

        //initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter(this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    //private void initCollapsingToolbar() {
    //   final CollapsingToolbarLayout collapsingToolbar =
    //           (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
    //    collapsingToolbar.setTitle(" ");
    //    AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
    //    appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
      //  appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
    //        boolean isShow = false;
  //          int scrollRange = -1;
//
    //        @Override
    //        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
    //            if (scrollRange == -1) {
    //               scrollRange = appBarLayout.getTotalScrollRange();
    //            }
    //            if (scrollRange + verticalOffset == 0) {
    //                collapsingToolbar.setTitle(getString(R.string.app_name));
    //                isShow = true;
    //            } else if (isShow) {
    //                collapsingToolbar.setTitle(" ");
    //               isShow = false;
    //            }
    //       }
    //    });
    //}

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11};

        Album a = new Album("Franz-Josefs-Kai 39", 2000, covers[0]);
        albumList.add(a);

        a = new Album("Stadtpark, Wienflusspromenade", 1900, covers[1]);
        albumList.add(a);

        a = new Album("Augarten, gegenüber Wasnergasse 3", 1161, covers[1]);
        albumList.add(a);

        a = new Album("Augarten, Schloßplatz", 2000, covers[1]);
        albumList.add(a);

        a = new Album("Parkanlage Engerthstraße", 640, covers[1]);
        albumList.add(a);

        a = new Album("Manes-Sperber-Park", 320, covers[1]);
        albumList.add(a);

        a = new Album("Max-Winter-Park (Hundeklo)", 460, covers[1]);
        albumList.add(a);

        a = new Album("Mexikoplatz/Rosenpark", 4650, covers[1]);
        albumList.add(a);

        a = new Album("Parkanlage Nordbahnstraße", 265, covers[1]);
        albumList.add(a);

        a = new Album("Parkanlage Offenbachgasse", 1600, covers[1]);
        albumList.add(a);

        a = new Album("Prater - Laufbergerwiese", 1020, covers[1]);
        albumList.add(a);

        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}