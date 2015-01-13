package jne.com.utils;

import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import jne.com.order.R;

public class AutoBuildBackgroundDrawable extends LayerDrawable {

    // The color filter to apply when the button is pressed
    protected ColorFilter _pressedFilter = new LightingColorFilter(PressUtil.getColor(R.color.hover_gray_color), 1);
    // Alpha value when the button is disabled
    protected int _disabledAlpha = 100;

    public AutoBuildBackgroundDrawable(Drawable drawable) {
        super(new Drawable[] { drawable });
    }

    @Override
    protected boolean onStateChange(int[] states) {
        boolean enabled = false;
        boolean pressed = false;

        for (int state : states) {
            if (state == android.R.attr.state_enabled)
                enabled = true;
            else if (state == android.R.attr.state_pressed)
                pressed = true;
        }
        mutate();
        if (enabled && pressed) {
            setColorFilter(_pressedFilter);
        } else if (!enabled) {
            setColorFilter(null);
            setAlpha(_disabledAlpha);
        } else {
            setColorFilter(null);
        }

        invalidateSelf();

        return super.onStateChange(states);
    }

    @Override
    public boolean isStateful() {
        return true;
    }
}