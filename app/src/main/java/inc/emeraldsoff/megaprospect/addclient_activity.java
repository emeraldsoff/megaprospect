package inc.emeraldsoff.megaprospect;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static android.content.ContentValues.TAG;

//import inc.frontlooksoftwares.datakeeper.R;


public class addclient_activity extends AppCompatActivity implements View.OnClickListener {
//    public class addclient_activity extends Activity {
Calendar myCalendar = Calendar.getInstance();
    SimpleDateFormat fullFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.US);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.US);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.US);

    Date now = new Date();
    //    String nowdate = dayformat.format(now);
    String month = monthFormat.format(now);


    ResideMenu resideMenu;
    TextView app_title_bar;
    private Context mcontext;
    private ResideMenuItem Emeraldsoff_MegaProspect, Home, Search_Client, Add_Client, Setting, Share, About_us, Go_Pro;
//    FirebaseAuth.AuthStateListener mAuthlistener;
//    FirebaseAuth mAuth;
    String userid;

    @Override
    protected void onStart() {
        super.onStart();
//        mAuth = FirebaseAuth.getInstance();
        int SPLASH_DISPLAY_LENGTH = 0;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Logger.d("Start splash screen");
//                mAuthlistener = new FirebaseAuth.AuthStateListener() {
//                    @Override
//                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                        FirebaseUser user = firebaseAuth.getCurrentUser();
//                        if (user != null) {
//                            userid = mAuth.getUid();
////                            Toast.makeText(addclient_activity.this, "Welcome " + userid, Toast.LENGTH_LONG).show();
//                        } else {
//                            startActivity(new Intent(addclient_activity.this, loginactivity.class));
//                            finish();
//                        }
//                    }
//                };
//                //add listener
//                mAuth.addAuthStateListener(mAuthlistener);
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private ConstraintLayout add_cust;
    private Button save;

    private EditText clientname_e, spous, child;
    private EditText adri_road_house, adrii_vlg_area, adriii_city, adriv_po, adrv_pin, adrvi_dist, adrvii_state, adrviii_country;
    private EditText std_e, mob, smob, tele, email, not;
    private EditText ann_dd, ann_mm, ann_yyyy, bda_dd, bda_mm, bda_yyyy, qualifi;

    private RadioGroup gender_grp,occu_grp;
    private RadioButton male, female, unspecified,sel,gov,stud,unemp,hom,otr,serv;

    String client_name, spouse, children;
    String address_i, address_ii, city, country, post_office, areapin, dist, state;
    String std, mobile_no, smobile_no, telephoneno, emailid;
    String gender, date, app_userid;
    String g;
    String anni_dd, anni_mm, anni_yyyy, bday_dd, bday_mm, bday_yyyy, qualification;
    String occu,occupation, note, bday_code, anni_code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addclient_activity);
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
        save = findViewById(R.id.save_data);

        add_cust = findViewById(R.id.add_cust);

        clientname_e = findViewById(R.id.clname);
        spous = findViewById(R.id.spouse);
        child = findViewById(R.id.children);

        bda_dd = findViewById(R.id.bday_dd);
        bda_mm = findViewById(R.id.bday_mm);
        bda_yyyy = findViewById(R.id.bday_yyyy);
        ann_dd = findViewById(R.id.anni_dd);
        ann_mm = findViewById(R.id.anni_mm);
        ann_yyyy = findViewById(R.id.anni_yyyy);
        qualifi = findViewById(R.id.qualifi);

        adri_road_house = findViewById(R.id.addressi);
        adrii_vlg_area = findViewById(R.id.addressii);
        adriii_city = findViewById(R.id.addressiii);
        adriv_po = findViewById(R.id.addressiv);
        adrv_pin = findViewById(R.id.addressv);
        adrvi_dist = findViewById(R.id.addressvi);
        adrvii_state = findViewById(R.id.addressvii);
        adrviii_country = findViewById(R.id.addressviii);

        std_e = findViewById(R.id.std_code);
        mob = findViewById(R.id.mob_no);
        smob = findViewById(R.id.sec_mob_no);
        tele = findViewById(R.id.telephone_no);
        email = findViewById(R.id.email_id);
        not = findViewById(R.id.note);

        gender_grp = findViewById(R.id.gender_select);
        occu_grp = findViewById(R.id.occu_grp);
        sel = findViewById(R.id.self);
        gov = findViewById(R.id.govtservice);
        stud = findViewById(R.id.student);
        unemp = findViewById(R.id.unemployed);
        hom = findViewById(R.id.home);
        otr = findViewById(R.id.other);
        serv = findViewById(R.id.service);
        male = findViewById(R.id.g_male);
        female = findViewById(R.id.g_female);
        unspecified = findViewById(R.id.g_unspecified);


        final DatePickerDialog.OnDateSetListener dateget1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                annidate();
            }
        };
        final DatePickerDialog.OnDateSetListener dateget2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                bdadate();
            }
        };

        ann_dd.setOnClickListener(new View.OnClickListener() {
            Calendar myCalendar = Calendar.getInstance();

            @Override
            public void onClick(View v) {
                new DatePickerDialog(addclient_activity.this, dateget1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        ann_mm.setOnClickListener(new View.OnClickListener() {
            Calendar myCalendar = Calendar.getInstance();

            @Override
            public void onClick(View v) {
                new DatePickerDialog(addclient_activity.this, dateget1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        ann_yyyy.setOnClickListener(new View.OnClickListener() {
            Calendar myCalendar = Calendar.getInstance();

            @Override
            public void onClick(View v) {
                new DatePickerDialog(addclient_activity.this, dateget1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        bda_dd.setOnClickListener(new View.OnClickListener() {
            Calendar myCalendar = Calendar.getInstance();

            @Override
            public void onClick(View v) {
                new DatePickerDialog(addclient_activity.this, dateget2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        bda_mm.setOnClickListener(new View.OnClickListener() {
            Calendar myCalendar = Calendar.getInstance();

            @Override
            public void onClick(View v) {
                new DatePickerDialog(addclient_activity.this, dateget2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        bda_yyyy.setOnClickListener(new View.OnClickListener() {
            Calendar myCalendar = Calendar.getInstance();

            @Override
            public void onClick(View v) {
                new DatePickerDialog(addclient_activity.this, dateget2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        occu_grp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.unemployed:
                        occu = unemp.getText().toString();
                        break;
                    case R.id.student:
                        occu = stud.getText().toString();
                        break;
                    case R.id.home:
                        occu = hom.getText().toString();
                        break;
                    case R.id.self:
                        occu = sel.getText().toString();
                        break;
                    case R.id.govtservice:
                        occu = gov.getText().toString();
                        break;
                    case R.id.service:
                        occu = serv.getText().toString();
                        break;
                    case R.id.other:
                        occu = otr.getText().toString();
                        break;
                }
            }
        });

        gender_grp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.g_male:
                        g = male.getText().toString();
                        break;
                    case R.id.g_female:
                        g = female.getText().toString();
                        break;
                    case R.id.g_unspecified:
                        g = unspecified.getText().toString();
                        break;
//                    default:
//                        g = male.getText().toString();
//                        break;
                }

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    data_allocation();
                    if (!validateInputs(client_name, children, spouse, gender,
                            address_i, address_ii, city, post_office, areapin, dist,
                            state, country, std, mobile_no, smobile_no,
                            telephoneno, emailid, note, date, app_userid, anni_dd, anni_mm, anni_yyyy, bday_dd, bday_mm, bday_yyyy,
                            qualification, occupation, bday_code, anni_code)) {

                        Map<String, Object> client = new HashMap<>();
                        client.put("client_name", client_name);
                        client.put("spouse", spouse);
                        client.put("children", children);
                        client.put("gender", gender);
                        client.put("address_i", address_i);
                        client.put("address_ii", address_ii);
                        client.put("city", city);
                        client.put("post_office", post_office);
                        client.put("areapin", areapin);
                        client.put("dist", dist);
                        client.put("state", state);
                        client.put("country", country);
                        client.put("std", std);
                        client.put("mobile_no", mobile_no);
                        client.put("smobile_no", smobile_no);
                        client.put("telephoneno", telephoneno);
                        client.put("emailid", emailid);
                        client.put("anni_dd", anni_dd);
                        client.put("anni_mm", anni_mm);
                        client.put("anni_yyyy", anni_yyyy);
                        client.put("bday_dd", bday_dd);
                        client.put("bday_mm", bday_mm);
                        client.put("note", note);
                        client.put("bday_yyyy", bday_yyyy);
                        client.put("bday_code", bday_code);
                        client.put("anni_code", anni_code);
                        client.put("qualification", qualification);
                        client.put("occupation", occupation);
                        client.put("date", date);
                        client.put("app_userid", app_userid);
                        String collection = "prospect" + "/" + app_userid;
                    }
                } catch (Exception e) {
                    Toast.makeText(addclient_activity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void annidate() {
//        String dd,mm,yyyy;
//        dd=dayFormat.format(c.getTime());
//        mm=monthFormat.format(c.getTime());
//        yyyy=yearFormat.format(c.getTime());
        ann_dd.setText(dayFormat.format(myCalendar.getTime()));
        ann_mm.setText(monthFormat.format(myCalendar.getTime()));
        ann_yyyy.setText(yearFormat.format(myCalendar.getTime()));
    }

    private void bdadate() {
        bda_dd.setText(dayFormat.format(myCalendar.getTime()));
        bda_mm.setText(monthFormat.format(myCalendar.getTime()));
        bda_yyyy.setText(yearFormat.format(myCalendar.getTime()));
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void checkConnection() {
        if (isOnline()) {
            Toast.makeText(addclient_activity.this, "You are connected to Internet", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(addclient_activity.this, "You are not connected to Internet", Toast.LENGTH_SHORT).show();
        }
    }
    private void data_allocation() {
        client_name = clientname_e.getText().toString().toUpperCase().trim();
        spouse = spous.getText().toString().trim();
        children = child.getText().toString().trim();
        gender = g;
        bday_dd = bda_dd.getText().toString().trim();
        bday_mm = bda_mm.getText().toString().trim();
        bday_yyyy = bda_yyyy.getText().toString().trim();
        bday_code = bday_mm+bday_dd;
        anni_dd = ann_dd.getText().toString().trim();
        anni_mm = ann_mm.getText().toString().trim();
        anni_yyyy = ann_yyyy.getText().toString().trim();
        anni_code = anni_mm+anni_dd;
        address_i = adri_road_house.getText().toString().trim();
        address_ii = adrii_vlg_area.getText().toString().trim();
        city = adriii_city.getText().toString().trim();
        post_office = adriv_po.getText().toString().trim();
        areapin = adrv_pin.getText().toString().trim();
        dist = adrvi_dist.getText().toString().trim();
        state = adrvii_state.getText().toString().trim();
        country = adrviii_country.getText().toString().trim();
        std = std_e.getText().toString().trim();
        mobile_no = mob.getText().toString().trim();
        smobile_no = smob.getText().toString().trim();
        telephoneno = tele.getText().toString().trim();
        emailid = email.getText().toString().trim();
        note = not.getText().toString().trim();
        qualification = qualifi.getText().toString().trim();
        occupation = occu;
        app_userid = userid;
        date = com.google.firebase.Timestamp.now().toDate().toString();
    }
    private boolean validateInputs(String client_name, String spouse, String children, String gender,
                                   String address_i, String address_ii, String city, String post_office, String areapin, String dist,
                                   String state, String country, String std, String mobile_no, String smobile_no,
                                   String telephoneno, String emailid, String note, String date, String app_userid,
                                   String anni_dd, String anni_mm, String anni_yyyy, String bday_dd, String bday_mm, String bday_yyyy,
                                   String qualification, String occupation, String anni_code, String bday_code) {
        if (client_name.isEmpty()) {
            clientname_e.setError("Name Required..!!");
            clientname_e.requestFocus();
            return true;
        }
        else if (mobile_no.isEmpty()){
            mob.setError("Mobile Number Required..!!");
            mob.requestFocus();
            return true;
        }
        return false;
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
//        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Harlow.ttf");
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
            startActivity(new Intent(addclient_activity.this, MainActivity.class));
            finish();
        } else if (view == Add_Client) {
            startActivity(new Intent(addclient_activity.this, addclient_activity.class));
            finish();
        }
        else if (view==Search_Client){
            startActivity(new Intent(addclient_activity.this, show_data.class));
            finish();
        }
//        else if (view==Edit_Client){
//            startActivity(new Intent(show_data.this, MainActivity.class));
//            finish();
//        }
//        else if (view==Delete_Client){
//            startActivity(new Intent(show_data.this, MainActivity.class));
//            finish();
//        }
//        else if (view == Setting) {
//            startActivity(new Intent(addclient_activity.this, settingsActivity.class));
//            finish();
//        }
//        else if (view == Share) {
//            startActivity(new Intent(show_data.this, MainActivity.class));
//            finish();
//        }
//        else if (view == About_us) {
//            startActivity(new Intent(addclient_activity.this, aboutusActivity.class));
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
        startActivity(new Intent(addclient_activity.this, MainActivity.class));
        finish();
    }
}
