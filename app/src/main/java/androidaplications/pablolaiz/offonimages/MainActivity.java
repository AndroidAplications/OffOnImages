package androidaplications.pablolaiz.offonimages;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mainLayout;

    private ImageView image;

    private Button des_button;
    private Button act_button;

    private Drawable image_dra;

    private ArrayList<ImageView> _imageViewsList = new ArrayList<ImageView>();

    private ArrayList<ImageInfo> _imageInfos = new ArrayList<ImageInfo>();

    private float _x, _y;

    private int xDelta;
    private int yDelta;

    private boolean updateCoord = true;
    private int __x;
    private int __y;

    private String filename = "info.bin";


    // http://www.coderzheaven.com/2012/07/25/serialization-android-simple-example/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        des_button = (Button) findViewById(R.id.des_button);
        act_button = (Button) findViewById(R.id.act_button);

        mainLayout = (RelativeLayout) findViewById(R.id.activity_main);

        image = (ImageView) findViewById(R.id.image_android);

        image.setOnTouchListener(onTouchListener());

        des_button.setOnClickListener(
            new Button.OnClickListener(){
                public void onClick(View v){
                    des_button.setClickable(false);
                    act_button.setClickable(true);
                    Log.i("des_button","des_button");

                    // Deleting all the images
                    _imageViewsList = getAllImageView();
                    for (int i = 0; i < _imageViewsList.size(); i++){
                        //Creation of image information
                        _imageInfos.add(new ImageInfo(_imageViewsList.get(i)));

                        mainLayout.removeView(_imageViewsList.get(i));
                    }
                }
            }
        );

        act_button.setOnClickListener(
            new Button.OnClickListener(){
                public void onClick(View v){
                    des_button.setClickable(true);
                    act_button.setClickable(false);
                    Log.i("act_button","act_button");

                    if(!_imageInfos.isEmpty()) {
                        for (int i = 0; i < _imageInfos.size(); i++) {
                            ImageView newImage = new ImageView(getApplicationContext());

                            newImage.setImageDrawable(_imageInfos.get(i).get_Drawable());

                            newImage.setLayoutParams(_imageInfos.get(i).get_layoutParams());

                            //newImage.setX(_imageInfos.get(i).get_x() );
                            //newImage.setY(_imageInfos.get(i).get_y() );

                            newImage.setScaleX(_imageInfos.get(i).get_XScale());
                            newImage.setScaleY(_imageInfos.get(i).get_YScale());

                            newImage.setOnTouchListener(onTouchListener());

                            Log.i("act_button_"+i, String.valueOf(_imageInfos.get(i).get_x()));
                            Log.i("act_button_"+i, String.valueOf(_imageInfos.get(i).get_y()));
                            mainLayout.addView(newImage);
                        }

                        _imageInfos.clear();
                    }
                }
            }
        );
    }

    private ArrayList<ImageView> getAllImageView (){
        ArrayList<ImageView> imageViewsListAux = new ArrayList<ImageView>();
        RelativeLayout yourLayout = (RelativeLayout)findViewById(R.id.activity_main);

        for (int i = 0; i < yourLayout.getChildCount(); i++) {
            View subView = yourLayout.getChildAt(i);
            if (subView instanceof ImageView) {
                imageViewsListAux.add((ImageView) subView);
                Log.i("getAllImageView", String.valueOf(i));
            }
        }
        return imageViewsListAux;
    }

    private OnTouchListener onTouchListener() {
        return new OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();
                if (updateCoord){
                    _x = x;
                    _y = y;
                    updateCoord = !updateCoord;
                }
                /*
                Log.i("onTouchListener", "Coordinates: ");
                Log.i("onTouchListener", "x: " + x);
                Log.i("onTouchListener", "y: " + y);
                */
                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        //Log.i("ACTION_DOWN", "In ACTION_DOWN");
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();

                        xDelta = x - lParams.leftMargin;
                        yDelta = y - lParams.topMargin;
                        //Log.i("ACTION_DOWN", "xDelta: " + xDelta);
                        //Log.i("ACTION_DOWN", "yDelta: " + yDelta);
                        break;

                    case MotionEvent.ACTION_UP:
                        //Log.i("ACTION_UP", "In ACTION_UP");
                        Toast.makeText(getApplicationContext(),
                                "Everybody love Pablo!", Toast.LENGTH_SHORT)
                                .show();

                        mainLayout = (RelativeLayout) findViewById(R.id.activity_main);
                        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                        params.leftMargin = __x;
                        params.topMargin = __y;
                        ImageView myImage = new ImageView(getApplicationContext());
                        myImage.setImageResource(R.mipmap.ic_launcher);
                        myImage.setOnTouchListener(onTouchListener());
                        mainLayout.addView(myImage, params);

                        updateCoord = !updateCoord;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        //Log.i("ACTION_MOVE", "In ACTION_MOVE");
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);

                        /**/
                        break;
                }
                mainLayout.invalidate();
                return true;
            }
        };
    }

    private boolean SerializeObject(ArrayList<ImageView> _images){
        // save the object to file
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(_images);
            out.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    private ImagesArray DeserializeObject (String _filename){
        ImagesArray imageViewsListAux = new ImagesArray();
        // read the object from file
        // save the object to file
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(_filename);
            in = new ObjectInputStream(fis);
            imageViewsListAux = (ImagesArray) in.readObject();
            in.close();
        } catch (Exception ex) {

            ex.printStackTrace();
            return null;
        }
        System.out.println(imageViewsListAux);
        return imageViewsListAux;
    }

}
