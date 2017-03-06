package androidaplications.pablolaiz.offonimages;

import java.util.ArrayList;

/**
 * Created by Pablo Laiz on 06/03/2017.
 */

public class ImagesArray implements java.io.Serializable {
    private ArrayList<ImageInfo> _imageInfos ;

    public ImagesArray() {
        this._imageInfos = new ArrayList<ImageInfo>();
    }

    public ArrayList<ImageInfo> get_imageInfos() {
        return _imageInfos;
    }

    public void set_imageInfos(ArrayList<ImageInfo> _imageInfos) {
        this._imageInfos = _imageInfos;
    }
}
