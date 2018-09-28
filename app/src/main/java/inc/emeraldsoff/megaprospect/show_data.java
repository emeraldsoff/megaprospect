package inc.emeraldsoff.megaprospect;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class show_data extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onStart() {
        super.onStart();
        int SPLASH_DISPLAY_LENGTH = 0;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private Context mcontext;
    ResideMenu resideMenu;
    TextView app_title_bar;
    private ResideMenuItem Emeraldsoff_MegaProspect, Home, Search_Client, Add_Client, Setting, Share, About_us, Go_Pro;
    LinearLayout search_view;
    SearchView search;
    RecyclerView id_recycleview;
    ScrollView scrollview;
//    FirebaseFirestore fdb = FirebaseFirestore.getInstance();
//    private cli_adapter cliadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_data);
        app_title_bar = findViewById(R.id.app_title_bar);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Harlow.ttf");
        app_title_bar.setTypeface(typeface);
        mcontext = this;
        search_view = findViewById(R.id.search_view);
        search = findViewById(R.id.search);
        Button menu = findViewById(R.id.menu);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals(null) || newText.equals("")) {
                    setuprecycle();
//                    cliadapter.startListening();
                } else {
                    setUpRecycleview(newText.toUpperCase());
//                    cliadapter.startListening();
                }
                return false;
            }
        });
        setuprecycle();
        setUpMenu();
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });

    }

    private void setUpRecycleview(String q) {

    }

    private void setuprecycle() {

    }
    @Override
    protected void onStop() {
        super.onStop();
//        cliadapter.stopListening();
    }
    private void setUpMenu() {
        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menubackgroundc);
        resideMenu.attachToActivity(this);
        //valid scale factor is between 0.0f and 1.0f. left menu'width is 150dip.
        resideMenu.setScaleValue(0.6f);
        createMenuItems();
        Menuitemlisten();
        addMenuItems_inMenu();
    }
    public void createMenuItems() {
        // create menu items;
        Emeraldsoff_MegaProspect = new ResideMenuItem(this, R.drawable.prospect_icon, "Mega Prospects");
        Go_Pro = new ResideMenuItem(this, R.drawable.ic_whatshot_yellow_24dp, "Go Premium");
        Home = new ResideMenuItem(this, R.drawable.round_home_24, "Home");
        Add_Client = new ResideMenuItem(this, R.drawable.round_person_add_24, "Add Client Details");
        Search_Client = new ResideMenuItem(this, R.drawable.baseline_search_24, "Search Client Details");
//        Edit_Client = new ResideMenuItem(this, R.drawable.round_edit_24, "Edit Client Details");
//        Delete_Client = new ResideMenuItem(this, R.drawable.round_delete_sweep_24, "Delete Client");
        Setting = new ResideMenuItem(this, R.drawable.round_settings_24, "Settings");
        Share = new ResideMenuItem(this, R.drawable.round_share_24, "Share");
        About_us = new ResideMenuItem(this, R.drawable.round_info_24, "About Us");
    }
    public void Menuitemlisten() {
        Home.setOnClickListener(this);
        Add_Client.setOnClickListener(this);
        Search_Client.setOnClickListener(this);
        Go_Pro.setOnClickListener(this);
//        Edit_Client.setOnClickListener(this);
//        Delete_Client.setOnClickListener(this);
        Setting.setOnClickListener(this);
        Share.setOnClickListener(this);
        About_us.setOnClickListener(this);
//        resideMenu.closeMenu();
    }
    public void onClick(View view) {
        if (view == Home) {
            startActivity(new Intent(show_data.this, MainActivity.class));
            finish();
        } else if (view == Add_Client) {
            startActivity(new Intent(show_data.this, addclient_activity.class));
            finish();
        }
        else if (view==Search_Client){
            startActivity(new Intent(show_data.this, show_data.class));
            finish();
        }
//        else if (view==Edit_Client){
//            startActivity(new Intent(show_data.this, MainActivity.class));
//        finish();
//        }
//        else if (view==Delete_Client){
//            startActivity(new Intent(show_data.this, MainActivity.class));
//        finish();
//        }
//        else if (view == Setting) {
//            startActivity(new Intent(show_data.this, settingsActivity.class));
//            finish();
//        }
////        else if (view == Share) {
////            startActivity(new Intent(show_data.this, MainActivity.class));
////        finish();
////        }
//        else if (view == About_us) {
//            startActivity(new Intent(show_data.this, aboutusActivity.class));
//            finish();
//        }
        resideMenu.closeMenu();
    }

    public void addMenuItems_inMenu() {
        resideMenu.addMenuItem(Emeraldsoff_MegaProspect, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(Go_Pro, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(Home, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(Add_Client, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(Search_Client, ResideMenu.DIRECTION_LEFT);
//        resideMenu.addMenuItem(Edit_Client, ResideMenu.DIRECTION_LEFT);
//        resideMenu.addMenuItem(Delete_Client, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(Setting, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(Share, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(About_us, ResideMenu.DIRECTION_LEFT);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            Toast.makeText(mcontext, "Menu Open", Toast.LENGTH_LONG).show();
        }

        @Override
        public void closeMenu() {
            Toast.makeText(mcontext, "Menu Close", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(show_data.this, MainActivity.class));
        finish();
    }

}
