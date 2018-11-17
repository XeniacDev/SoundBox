package ir.imuon.soundbox;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;


import ir.imuon.soundbox.fragments.HomeFragment;
import ir.imuon.soundbox.fragments.MoreFragment;
import ir.imuon.soundbox.fragments.RadioFragment;
import ir.imuon.soundbox.fragments.SearchFragment;
import ir.imuon.soundbox.fragments.ShazamFragment;

public class MainActivity extends AppCompatActivity {

    public static final String DEVICE_INFORMATION = "Device model: " +
            Build.MODEL + "\n Android version: " + Build.VERSION.RELEASE;
    public static String APP_VERSION;

    private static final int REQUEST_PERMISSION = 1001;
    private boolean permissionGranted;

    private CoordinatorLayout coordinatorLayout;
    private AHBottomNavigation ahBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout = findViewById(R.id.coordinator_main);

        bottomNavigation();
        checkPermission();

        if (!permissionGranted) {
            checkPermission();
            return;
        } else {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fl_main, new HomeFragment(), "home_fragment").commit();
            setTitle("Home");
        }

        try {
            APP_VERSION = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void checkPermission() {

        if (!isExternalStorageReadable() || !isExternalStorageWritable()) {
            Snackbar.make(coordinatorLayout, "This app only works on devices with usable external storage",
                    Snackbar.LENGTH_LONG).show();
            permissionGranted = false;
        }

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED) {
//                Log.v("revoke", "Permission is revoked");
                Toast.makeText(this, "You must grant permission to use the entire app",
                        Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
                permissionGranted = false;
            } else {
//                Log.v("2", "Permission is granted");
                permissionGranted = true;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
//            Log.v("2", "Permission is granted");
            permissionGranted = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionGranted = true;
//                    Log.v("grant", "Permission is granted");
                    ahBottomNavigation.setCurrentItem(0);
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.fl_main, new HomeFragment(), "home_fragment").commit();
                    setTitle("Home");
                } else {
                    permissionGranted = false;
                    Toast.makeText(this, "You must grant permission to use the entire app",
                            Toast.LENGTH_SHORT).show();
                }
        }
    }

    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state));
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    // BottomNavigation
    private void bottomNavigation() {
        ahBottomNavigation = findViewById(R.id.bottom_navigation);

        // Create items
        AHBottomNavigationItem home = new AHBottomNavigationItem
                (R.string.bn_home, R.drawable.ic_bottom_navigation__home_white, R.color.colorWhite);
        AHBottomNavigationItem search = new AHBottomNavigationItem
                (R.string.bn_search, R.drawable.ic_bottom_navigation__search_white, R.color.colorWhite);
        AHBottomNavigationItem radio = new AHBottomNavigationItem
                (R.string.bn_radio, R.drawable.ic_bottom_navigation__radio_white, R.color.colorWhite);
        AHBottomNavigationItem shazam = new AHBottomNavigationItem
                (R.string.bn_shazam, R.drawable.ic_bottom_navigation__shazam_white, R.color.colorWhite);
        AHBottomNavigationItem more = new AHBottomNavigationItem
                (R.string.bn_more, R.drawable.ic_bottom_navigation__more_white, R.color.colorWhite);

        // Add items
        ahBottomNavigation.addItem(home);
        ahBottomNavigation.addItem(search);
        ahBottomNavigation.addItem(radio);
        ahBottomNavigation.addItem(shazam);
        ahBottomNavigation.addItem(more);

        // Set listeners
        ahBottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {
            switch (position) {
                case 0:
                    if (!permissionGranted) {
                        checkPermission();
                        return false;
                    } else {
                        getSupportFragmentManager().beginTransaction().
                                replace(R.id.fl_main, new HomeFragment(), "home_fragment").commit();
                        setTitle(R.string.bn_home);
                    }
                    return true;
                case 1:
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.fl_main, new SearchFragment(), "Search_Fragment").commit();
                    setTitle(R.string.bn_search);
                    return true;
                case 2:
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.fl_main, new RadioFragment(), "Search_Fragment").commit();
                    setTitle(R.string.bn_radio);
                    return true;
                case 3:
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.fl_main, new ShazamFragment(), "Shazam_Fragment").commit();
                    setTitle(R.string.bn_shazam);
                    return true;
                case 4:
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.fl_main, new MoreFragment(), "Shazam_Fragment").commit();
                    setTitle(R.string.bn_more);
                    return true;
            }
            return wasSelected;
        });

        // Set colors
        ahBottomNavigation.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        ahBottomNavigation.setAccentColor(ContextCompat.getColor(this, R.color.colorGreen));
        ahBottomNavigation.setItemDisableColor(ContextCompat.getColor(this, R.color.colorPrimary));
        ahBottomNavigation.setNotificationBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));

        // Disable the translation inside the CoordinatorLayout
        ahBottomNavigation.setBehaviorTranslationEnabled(false);

        // Force to tint the drawable
        ahBottomNavigation.setForceTint(true);

        // Manage titles
        ahBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        // Add or remove notification for each item
//        ahBottomNavigation.setNotification("1", 4);

    }
}
