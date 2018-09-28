package inc.emeraldsoff.megaprospect;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class show_data_vivid extends AppCompatActivity implements View.OnClickListener{

    private Context mcontext;
    ResideMenu resideMenu;
    TextView app_title_bar;

    private ResideMenuItem Emeraldsoff_MegaProspect, Home, Search_Client, Add_Client, Setting, Share, About_us, Go_Pro;
    //    private int ;
    String userid;


    @Override
    protected void onStart() {
        super.onStart();

        int SPLASH_DISPLAY_LENGTH = 0;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Logger.d("Start splash screen");

            }
        }, SPLASH_DISPLAY_LENGTH);
    }


    private TextView clientname_e, spous, child;
    private TextView adri_road_house, adrii_vlg_area, adriii_city, adriv_po, adrv_pin, adrvi_dist, adrvii_state, adrviii_country;
    private TextView isd, std_e, mob, smob, tele, email, not;
    private TextView ann, bda, gender, occupation, qualifi;

    private Button edit, delete;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_data_vivid);
        app_title_bar = findViewById(R.id.app_title_bar);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Harlow.ttf");
        app_title_bar.setTypeface(typeface);
        mcontext = this;

        Button menu = findViewById(R.id.menu);
        setUpMenu();
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        edit = findViewById(R.id.edit);
        delete = findViewById(R.id.del);
        clientname_e = findViewById(R.id.clname);
        spous = findViewById(R.id.spouse);
        child = findViewById(R.id.children);

        bda = findViewById(R.id.bday_1);
        ann = findViewById(R.id.anni_1);

        adri_road_house = findViewById(R.id.addressi);
        adrii_vlg_area = findViewById(R.id.addressii);
        adriii_city = findViewById(R.id.addressiii);
        adriv_po = findViewById(R.id.addressiv);
        adrv_pin = findViewById(R.id.addressv);
        adrvi_dist = findViewById(R.id.addressvi);
        adrvii_state = findViewById(R.id.addressvii);
        adrviii_country = findViewById(R.id.addressviii);

        qualifi = findViewById(R.id.qualifi);
        std_e = findViewById(R.id.std_code);
        mob = findViewById(R.id.mob_no);
        smob = findViewById(R.id.sec_mob_no);
        tele = findViewById(R.id.telephone_no);
        email = findViewById(R.id.email_id);
        not = findViewById(R.id.note);

        gender = findViewById(R.id.gender_select);
        occupation = findViewById(R.id.occu_grp);

    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
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
        Home = new ResideMenuItem(this, R.drawable.round_home_24, "Home");
        Add_Client = new ResideMenuItem(this, R.drawable.round_person_add_24, "Add Client Details");
        Search_Client = new ResideMenuItem(this, R.drawable.baseline_search_24, "Search Client Details");
        Go_Pro = new ResideMenuItem(this, R.drawable.ic_whatshot_yellow_24dp, "Go Premium");
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
    }

    public void onClick(View view) {
        if (view == Home) {
            startActivity(new Intent(show_data_vivid.this, MainActivity.class));
            finish();
        }
        else if (view == Add_Client) {
            startActivity(new Intent(show_data_vivid.this, addclient_activity.class));
            finish();
        }
        else if (view==Search_Client){
            startActivity(new Intent(show_data_vivid.this, show_data.class));
            finish();
        }
//        else if (view == Go_Pro) {
//            startActivity(new Intent(show_data_vivid.this, addclient_activity.class));
//            finish();
//        }
//        else if (view==Edit_Client){
//            startActivity(new Intent(show_data.this, MainActivity.class));
//        finish();
//        }
//        else if (view==Delete_Client){
//            startActivity(new Intent(show_data.this, MainActivity.class));
//        finish();
//        }
//        else if (view == Setting) {
//            startActivity(new Intent(show_data_vivid.this, settingsActivity.class));
//            finish();
//        }
//        else if (view == Share) {
//            startActivity(new Intent(show_data.this, MainActivity.class));
//        finish();
//        }
//        else if (view == About_us) {
//            startActivity(new Intent(show_data_vivid.this, aboutusActivity.class));
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
//        Toast.makeText(this,"startActivity(new Intent(show_data_vivid.this, show_data.class));",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(show_data_vivid.this, show_data.class));
        finish();
    }
}
