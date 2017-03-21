package essyselves.bme3110.gatech.nystagmus;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.os.Handler;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.camera2.CameraManager;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity implements SurfaceHolder.Callback{
    CameraManager cameraManager;
    String[] cameraIdList;
    Surface toPlacePicOn;
    SurfaceHolder surfaceHolder;
    List<Surface> surfaces;
    int numCameras;
    int currentCam;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        // quick debug check
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.cameraView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        try {
            cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            cameraIdList = cameraManager.getCameraIdList();
            for (String cam : cameraIdList) {
                Log.d("camera", cam);
            }}
            catch (CameraAccessException e) {e.printStackTrace();}


    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    public void surfaceCreated(final SurfaceHolder surfaceHolder) {
        toPlacePicOn = surfaceHolder.getSurface();
        Log.d("Surface was Created","yep");
        //set up surface view for app
        try {
            numCameras = cameraIdList.length;
            currentCam = 1 % numCameras;
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // DONE: Consider callin
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                String[] appCompatPermissParam = {Manifest.permission.CAMERA};
                ActivityCompat.requestPermissions(this, appCompatPermissParam, PackageManager.PERMISSION_GRANTED);
                return;
            }
            cameraManager.openCamera(cameraIdList[currentCam], new CameraDevice.StateCallback() {

                @Override
                public void onOpened(@NonNull final CameraDevice cameraDevice) {
                    SurfaceView cameraView = (SurfaceView) findViewById(R.id.cameraView);
                    surfaces = new ArrayList<Surface>();
                    toPlacePicOn = cameraView.getHolder().getSurface();
                    surfaces.add(toPlacePicOn);
                    Log.d("surface", "abandoned yet? line 63");
                    //Do a full camera capture
                    try {
                        cameraDevice.createCaptureSession(surfaces, new CameraCaptureSession.StateCallback() {
                            @Override
                            public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                                try {
                                    CaptureRequest.Builder b = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
                                    Log.d("Surface check","do we throw error yet? if not, then surface is not abandoned");
                                    b.addTarget(toPlacePicOn);
                                    cameraCaptureSession.setRepeatingRequest(b.build(), new CameraCaptureSession.CaptureCallback() {}, new Handler());
                                } catch (CameraAccessException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {

                            }
                        }, new Handler());
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onDisconnected(@NonNull CameraDevice cameraDevice) {

                }

                @Override
                public void onClosed(@NonNull CameraDevice camera) {
                }

                @Override
                public void onError(@NonNull CameraDevice cameraDevice, int i) {

                }
            }, new Handler());
        } catch (CameraAccessException ce) {
            Log.e("CAMARA EXCEPTION",ce.getMessage());
        }

        Button changeCamera = (Button) findViewById(R.id.changeCameraButton);
        changeCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Make it so we actually change cameras
                currentCam = (currentCam + 1) % numCameras;
            }
        });
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
