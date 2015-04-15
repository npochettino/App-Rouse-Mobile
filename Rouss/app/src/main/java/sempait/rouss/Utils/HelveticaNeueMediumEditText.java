package sempait.rouss.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class HelveticaNeueMediumEditText extends EditText {

	Typeface neueLightFont;

	public HelveticaNeueMediumEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		neueLightFont = Typeface.createFromAsset(context.getAssets(), "HelveticaNeueLTStd-Md.otf");
		this.setTypeface(neueLightFont);
	}

	public HelveticaNeueMediumEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setTypeface(neueLightFont);
	}

	public HelveticaNeueMediumEditText(Context context) {
		super(context);
		this.setTypeface(neueLightFont);
	}

}
