package inc.emeraldsoff.megaprospect;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //public class MainActivity extends Activity {
    //    implements View.OnClickListener
    private Context mcontext;
    ResideMenu resideMenu;
    TextView app_title_bar;

    private ResideMenuItem Emeraldsoff_MegaProspect, Home, Search_Client, Add_Client, Setting, Share, About_us, Go_Pro;
//    private int ;

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

    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private final long ONE_DAY = 24 * 60 * 60 * 1000;
    Date now = new Date();
    String dateString = formatter.format(now);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app_title_bar = findViewById(R.id.app_title_bar);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Harlow.ttf");
        app_title_bar.setTypeface(typeface);
        mcontext = this;
        Button menu = findViewById(R.id.menu);
        setUpMenu();

        Date now = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_YEAR, +10);
        Date newDate = calendar.getTime();
        String date = formatter.format(newDate);

        Toast.makeText(this, "Date +10: " + date, Toast.LENGTH_LONG).show();

//        setUpRecycleview();

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
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
    }

    public void onClick(View view) {
        if (view == Home) {
            startActivity(new Intent(MainActivity.this, MainActivity.class));
            finish();
        } else if (view == Add_Client) {
            startActivity(new Intent(MainActivity.this, addclient_activity.class));
            finish();
        }
        else if (view==Search_Client){
            startActivity(new Intent(MainActivity.this, show_data.class));
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
//            startActivity(new Intent(MainActivity.this, settingsActivity.class));
//            finish();
//        }
////        else if (view == Share) {
////            startActivity(new Intent(show_data.this, MainActivity.class));
////        finish();
////        }
//        else if (view == About_us) {
//            startActivity(new Intent(MainActivity.this, aboutusActivity.class));
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
        new AlertDialog.Builder(this).setIcon(R.drawable.ic_warning_pink_24dp).setTitle("Exit")
                .setMessage("Are you sure to exit?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("no", null).show();
    }
}
