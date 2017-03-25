package essyselves.bme3110.gatech.nystagmus;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import essyselves.bme3110.gatech.nystagmus.Presenter.GifHolder;


public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Fragment gifHolder = new GifHolder();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_holder, gifHolder);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
