package sempait.rouss.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by martin on 28/03/15.
 */
public class HelveticaNeueLightTextView extends TextView {

    Typeface neueLightFont;

    public HelveticaNeueLightTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        neueLightFont = Typeface.createFromAsset(context.getAssets(), "HelveticaNeue-Light.otf");
        this.setTypeface(neueLightFont);
    }

    public HelveticaNeueLightTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setTypeface(neueLightFont);
    }

    public HelveticaNeueLightTextView(Context context) {
        super(context);
        this.setTypeface(neueLightFont);
    }


}
