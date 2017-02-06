package com.project.erc.energyestimator;

        import android.app.Fragment;
        import android.content.res.Configuration;
        import android.graphics.Color;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v4.app.FragmentTransaction;
        import android.support.v4.app.ListFragment;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.CompoundButton;
        import android.widget.Toast;

        import com.mikepenz.crossfader.Crossfader;
        import com.mikepenz.crossfader.util.UIUtils;
       // import com.mikepenz.fontawesome_typeface_library.FontAwesome;
        //import com.mikepenz.google_material_typeface_library.GoogleMaterial;
        import com.mikepenz.iconics.IconicsDrawable;
        import com.mikepenz.materialdrawer.AccountHeader;
        import com.mikepenz.materialdrawer.AccountHeaderBuilder;
        import com.mikepenz.materialdrawer.Drawer;
        import com.mikepenz.materialdrawer.DrawerBuilder;
        import com.mikepenz.materialdrawer.MiniDrawer;
       // import com.mikepenz.materialdrawer.app.utils.CrossfadeWrapper;
        //import com.mikepenz.materialdrawer.app.utils.SystemUtils;
        import com.mikepenz.materialdrawer.holder.BadgeStyle;
        import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
        import com.mikepenz.materialdrawer.model.DividerDrawerItem;
        import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
        import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
        import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
        import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
        import com.mikepenz.materialdrawer.model.SectionDrawerItem;
        import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
        import com.mikepenz.materialdrawer.model.ToggleDrawerItem;
        import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
        import com.mikepenz.materialdrawer.model.interfaces.IProfile;
        import com.mikepenz.materialdrawer.model.interfaces.Nameable;
        //import com.mikepenz.octicons_typeface_library.Octicons;

public class Trial extends AppCompatActivity {
    private static final int PROFILE_SETTING = 1;

    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private MiniDrawer miniResult = null;
    private Crossfader crossFader;
    private static final String PERSISTENT_VARIABLE_BUNDLE_KEY = "persistentVariable";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_drawer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        DrawerBuilder builder = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("GENERAL").withIcon(R.drawable.general).withIdentifier(1),
                        new PrimaryDrawerItem().withName("LIGHTING").withIcon(R.drawable.lighting).withIdentifier(2),
                        new PrimaryDrawerItem().withName("CONTROLS").withIcon(R.drawable.controls).withIdentifier(3),
                        new PrimaryDrawerItem().withName("HEATING").withIcon(R.drawable.heating).withIdentifier(4),
                        new PrimaryDrawerItem().withName("COOLING").withIcon(R.drawable.cooling).withIdentifier(5),
                        new PrimaryDrawerItem().withName("VENTILLATION").withIcon(R.drawable.ventillation).withIdentifier(6),
                       // new PrimaryDrawerItem().withName("ENVELOPE").withIcon(R.drawable.envelope).withIdentifier(7),
                        new PrimaryDrawerItem().withName("KITCHEN").withIcon(R.drawable.kitchen).withIdentifier(8),
                        new PrimaryDrawerItem().withName("DOMESTIC HOT WATER").withIcon(R.drawable.hw).withIdentifier(9),
                        new PrimaryDrawerItem().withName("PUMPS & FANS").withIcon(R.drawable.pumps).withIdentifier(10),
                        new PrimaryDrawerItem().withName("APPLIANCES").withIcon(R.drawable.appliances).withIdentifier(11)
                ) // add the items we want to use with our Drawer
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem instanceof Nameable) {
                            Toast.makeText(Trial.this, ((Nameable) drawerItem).getName().getText(Trial.this), Toast.LENGTH_SHORT).show();
                            String fragmentName = ((Nameable)drawerItem).getName().toString();
                            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                            switch (fragmentName)
                            {
                                case "LIGHTING":
                                    if(getSupportFragmentManager().findFragmentByTag("lighting")!=null)
                                    {
                                        fragmentTransaction.show(getSupportFragmentManager().findFragmentByTag("lighting"));
                                        break;
                                    }
                                    else
                                    {
                                        fragmentTransaction.replace(R.id.mainFrame,new LightingFragment(),"lighting");
                                        break;

                                    }
                                case "HEATING" :
                                    if(getSupportFragmentManager().findFragmentByTag("heating")!=null)
                                    {
                                        fragmentTransaction.show(getSupportFragmentManager().findFragmentByTag("heating"));
                                        break;
                                    }
                                    else
                                    {
                                        fragmentTransaction.replace(R.id.mainFrame,new HeatingFragment(),"heating");
                                        break;

                                    }

                            }
                            fragmentTransaction.commit();
                            Log.i("msg",fragmentName);
                        }
                        //IMPORTANT notify the MiniDrawer about the onItemClick
                        return miniResult.onItemClick(drawerItem);
                    }
                })
                .withSavedInstance(savedInstanceState);



        // build only the view of the Drawer (don't inflate it automatically in our layout which is done with .build())
        result = builder.buildView();
        // create the MiniDrawer and deinfe the drawer and header to be used (it will automatically use the items from them)
        miniResult = new MiniDrawer().withDrawer(result);//.withAccountHeader(headerResult);

        //get the widths in px for the first and second panel
        int firstWidth = (int) UIUtils.convertDpToPixel(270, this);
        int secondWidth = (int) UIUtils.convertDpToPixel(72, this);

        //create and build our crossfader (see the MiniDrawer is also builded in here, as the build method returns the view to be used in the crossfader)
        //the crossfader library can be found here: https://github.com/mikepenz/Crossfader
        crossFader = new Crossfader()
                .withContent(findViewById(R.id.crossfade_content))
                .withFirst(result.getSlider(), firstWidth)
                .withSecond(miniResult.build(this), secondWidth)
                .withSavedInstance(savedInstanceState)
                .build();

        //define the crossfader to be used with the miniDrawer. This is required to be able to automatically toggle open / close
        //miniResult.withCrossFader(new CrossfadeWrapper(crossFader));

        //define a shadow (this is only for normal LTR layouts if you have a RTL app you need to define the other one
        crossFader.getCrossFadeSlidingPaneLayout().setShadowResourceLeft(R.drawable.material_drawer_shadow_left);
    }

    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            if (drawerItem instanceof Nameable) {
                Log.i("material-drawer", "DrawerItem: " + ((Nameable) drawerItem).getName() + " - toggleChecked: " + isChecked);
            } else {
                Log.i("material-drawer", "toggleChecked: " + isChecked);
            }
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        //add the values which need to be saved from the crossFader to the bundle
        outState = crossFader.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
      /*  if (SystemUtils.getScreenOrientation() == Configuration.ORIENTATION_LANDSCAPE) {
            inflater.inflate(R.menu.embedded, menu);
            menu.findItem(R.id.menu_1).setIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_sort).color(Color.WHITE).actionBar());
        }*/
        return true;
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle the click on the back arrow click
        /*switch (item.getItemId()) {
            case R.id.menu_1:
                crossFader.crossFade();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }*/
        return false;
    }
}
