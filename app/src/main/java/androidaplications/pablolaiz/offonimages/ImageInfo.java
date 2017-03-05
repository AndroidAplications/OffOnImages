package androidaplications.pablolaiz.offonimages;

import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Arrays;

/**
 * Created by Pablo Laiz on 04/03/2017.
 */

public class ImageInfo {

    private Drawable _Drawable;
    private float _x, _y;
    private float _XScale, _YScale;
    private ViewGroup.LayoutParams _layoutParams;

    public ImageInfo(ImageView image){
        // Get Image
        _Drawable = image.getDrawable();

        // Get image position
        _x = image.getX();
        _y = image.getY();

        // Get image scale
        _XScale = image.getScaleX();
        _YScale = image.getScaleY();

        _layoutParams = image.getLayoutParams();
    }

    public Drawable get_Drawable() {
        return _Drawable;
    }

    public void set_Drawable(Drawable _Drawable) {
        this._Drawable = _Drawable;
    }

    public float get_x() {
        return _x ;
    }

    public void set_x(float _x) {
        this._x = _x;
    }

    public float get_y() {
        return _y;
    }

    public void set_y(float _y) {
        this._y = _y;
    }

    public float get_XScale() {
        return _XScale;
    }

    public void set_XScale(float _XScale) {
        this._XScale = _XScale;
    }

    public float get_YScale() {
        return _YScale;
    }

    public void set_YScale(float _YScale) {
        this._YScale = _YScale;
    }

    public ViewGroup.LayoutParams get_layoutParams() {
        return _layoutParams;
    }

    public void set_layoutParams(ViewGroup.LayoutParams _layoutParams) {
        this._layoutParams = _layoutParams;
    }

}
