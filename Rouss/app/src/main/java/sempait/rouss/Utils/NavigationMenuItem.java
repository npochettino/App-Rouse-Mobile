package sempait.rouss.Utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import sempait.rouss.R;

/**
 * Created by martin on 28/03/15.
 */
public class NavigationMenuItem extends TextView {

    Typeface neueLightFont;
    Context mContext;

    public NavigationMenuItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

//        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "HelveticaNeue-Light.otf"));
        this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NavigationMenuItem);

        for (int i = 0; i < a.getIndexCount(); ++i) {

            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.NavigationMenuItem_selected:

                    setStatusSelected(a.getBoolean(attr, false));

                    break;
            }
        }
        a.recycle();
    }

    public NavigationMenuItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setTypeface(neueLightFont);
    }

    public NavigationMenuItem(Context context) {
        super(context);
        this.setTypeface(neueLightFont);
    }

    public void setStatusSelected(Boolean selected) {

        int gray_text = mContext.getResources().getColor(R.color.gray_text);
        int blue_text = mContext.getResources().getColor(R.color.light_blue);

        this.setTextColor(selected ? blue_text : gray_text);

        int gray_background = mContext.getResources().getColor(R.color.gray_background_dark);
        int gray_background_selected = mContext.getResources().getColor(R.color.gray_background_dark_extra);

        this.setBackgroundColor(selected ? gray_background_selected : gray_background);
    }
}
