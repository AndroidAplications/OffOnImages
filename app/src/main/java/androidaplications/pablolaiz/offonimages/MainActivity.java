package androidaplications.pablolaiz.offonimages;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        des_button = (Button) findViewById(R.id.des_button);
        act_button = (Button) findViewById(R.id.act_button);

        mainLayout = (RelativeLayout) findViewById(R.id.activity_main);

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
}
