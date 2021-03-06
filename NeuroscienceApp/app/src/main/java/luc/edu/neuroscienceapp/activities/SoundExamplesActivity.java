package luc.edu.neuroscienceapp.activities;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import luc.edu.neuroscienceapp.R;
import luc.edu.neuroscienceapp.adapters.SoundExamplesAdapter;
import luc.edu.neuroscienceapp.entities.Example;
import luc.edu.neuroscienceapp.entities.Global;

public class SoundExamplesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SoundExamplesAdapter adapter;
    private List<Example> examples;
    private Button btHarmonic, btNonHarmonic, btGroups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        initCollapsingToolbar();

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        btHarmonic = (Button) findViewById(R.id.bt_harmonic);
        btNonHarmonic = (Button) findViewById(R.id.bt_nonharmonic);
        btGroups = (Button) findViewById(R.id.bt_groups);


        btHarmonic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btHarmonic.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                btNonHarmonic.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.cardview_light_background));
                btGroups.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.cardview_light_background));

                loadHarmonic();

            }
        });

        btNonHarmonic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btHarmonic.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.cardview_light_background));
                btNonHarmonic.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                btGroups.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.cardview_light_background));

                loadNonHarmonic();

            }
        });

        btGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btHarmonic.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.cardview_light_background));
                btNonHarmonic.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.cardview_light_background));
                btGroups.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));

                loadGroups();
            }
        });

        examples = new ArrayList<>();
        adapter = new SoundExamplesAdapter(getApplicationContext(), examples);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        loadAll();

        try {
            Glide.with(this).load(R.drawable.sound_filters).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);
    }


    private void loadGroups() {
        examples.clear();

        int[] covers = Global.sound_group_thumbnails;
        int[] covers_ica = Global.sound_groups_ica_thumbnails;
        String[] titles = Global.sound_groups_names;
        boolean[] labels = Global.natural_images;

        Example a = null;
        String lab = "";
        for (int i = 0; i < covers.length; i++) {
            lab = Global.SOUND_GROUP;
            a = new Example(titles[i], covers[i], covers_ica[i], i, lab);
            examples.add(a);
        }

        adapter = new SoundExamplesAdapter(getApplicationContext(), examples);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void loadAll() {
        examples.clear();

        int[] covers = Global.sound_thumbnails;
        int[] covers_ica = Global.sound_ica_thumbnails;
        String[] titles = Global.sound_names;
        boolean[] labels = Global.harmonic_sounds;

        Example a = null;
        String lab = "";
        for (int i = 0; i < covers.length; i++) {
            if (labels[i]) {
                lab = "Harmonic Sound";
            } else {
                lab = "Non-harmonic Sound";
            }
            a = new Example(titles[i], covers[i], covers_ica[i], i, lab);
            examples.add(a);
        }

        adapter = new SoundExamplesAdapter(getApplicationContext(), examples);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void loadHarmonic() {
        examples.clear();

        int[] covers = Global.sound_thumbnails;
        int[] covers_ica = Global.sound_ica_thumbnails;
        String[] titles = Global.sound_names;
        boolean[] labels = Global.harmonic_sounds;

        Example a = null;
        String lab = "";
        for (int i = 0; i < covers.length; i++) {
            if (labels[i]) {
                lab = "Harmonic Sound";
                a = new Example(titles[i], covers[i], covers_ica[i], i, lab);
                examples.add(a);
            }
        }

        adapter = new SoundExamplesAdapter(getApplicationContext(), examples);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void loadNonHarmonic() {
        examples.clear();

        int[] covers = Global.sound_thumbnails;
        int[] covers_ica = Global.sound_ica_thumbnails;
        String[] titles = Global.sound_names;
        boolean[] labels = Global.harmonic_sounds;

        Example a = null;
        String lab = "";
        for (int i = 0; i < covers.length; i++) {
            if (!labels[i]) {
                lab = "Non-harmonic Sound";
                a = new Example(titles[i], covers[i], covers_ica[i], i, lab);
                examples.add(a);
            }
        }

        adapter = new SoundExamplesAdapter(getApplicationContext(), examples);
        recyclerView.setAdapter(adapter);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        if (id == R.id.action_about) {
            Intent intentAbout = new Intent(SoundExamplesActivity.this, AboutActivity.class);
            startActivity(intentAbout);
            return true;
        }
        if (id == R.id.action_settings) {
            Intent intentSettings = new Intent(SoundExamplesActivity.this, SettingsActivity.class);
            startActivity(intentSettings);
            return true;
        }
        if (id == R.id.action_info) {
            Intent intent = new Intent(SoundExamplesActivity.this, WelcomeActivity.class);
            intent.putExtra("menu","menu");
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
