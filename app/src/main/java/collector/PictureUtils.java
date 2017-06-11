package collector;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by fanmiaomiao on 2017/6/11.
 */

public class PictureUtils {
    public static Bitmap getBitmap(String path, int destWidth ,int destHeight){
        if(path!=null&&!path.equals("")) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);

            float srcWidth = options.outWidth;
            float srcHeight = options.outHeight;

            int inSampleSize = 1;
            if (srcHeight > destHeight || srcWidth > destWidth) {
                if (srcWidth > srcHeight) {
                    inSampleSize = Math.round(srcHeight / srcWidth);
                } else {
                    inSampleSize = Math.round(srcWidth / srcHeight);
                }
            }
            options = new BitmapFactory.Options();
            options.inSampleSize = inSampleSize;

            return BitmapFactory.decodeFile(path, options);
        }
        return null;
    }
}
