package com.example.framework.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;

/**
 * function: 自定义支付密码输入框控件
 * describe:详细描述
 * email：yangchaozhi@outlook.com
 *
 * @author vinko on 2017/3/8.
 */

public class PayEditText extends AppCompatEditText {
    public final String TAG = getClass().getSimpleName();
    private Paint paint = null;
    private int width = 0;
    private int height = 0;
    private String text = null;
    private int size = 0;
    private int textSpace = 0;
    private float[] pos;
    private int count = 6;
    private float centerScale = 0.0F;
    private boolean callbackDone = false;
    private InputCompleteCallback inputCompleteCallback = null;
    private StringBuffer oriText;//明文

    public PayEditText(Context context) {
        super(context);
        init();
    }

    public PayEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PayEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= 11) {
            this.setLayerType(1, (Paint) null);
        }
        this.setBackgroundDrawable((Drawable) null);
        oriText = new StringBuffer();
        addTextChangedListener(new EncodeTextWatcher());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.paint = this.getPaint();
        this.width = this.getWidth();
        this.height = this.getHeight();
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setColor(Color.parseColor("#db3764"));
        //画输入框的长方形外框
        canvas.drawRect(1.0F, 2.0F, (float) (this.width - 2), (float) (this.height - 1), this.paint);
        this.paint.setStyle(Paint.Style.FILL);
        this.textSpace = this.width / this.count;

        int i;
        for (i = 0; i < this.count; ++i) {
            //画中间的竖线
            canvas.drawLine((float) (i * this.textSpace), 2.0F, (float) (i * this.textSpace), (float) this.height, this.paint);
        }

        this.paint.setColor(Color.BLUE);
        this.text = this.getText().toString();

        this.size = this.text.length() * 2;
        this.centerScale = this.paint.measureText("0") / 2.0F;
        if (!TextUtils.isEmpty(this.getHint()) && this.size == 0) {
            this.paint.setColor(this.getCurrentHintTextColor());
            //画字体
            canvas.drawText(this.getHint().toString(), this.centerScale * 2.0F, (float) (this.height / 2) + this.centerScale, this.paint);
        } else {
            this.paint.setColor(this.getCurrentTextColor());
            this.pos = new float[this.size];

            for (i = 0; i < this.size; i += 2) {
                this.pos[i] = (float) (this.textSpace / 2 * (1 + i)) - this.centerScale;
                this.pos[i + 1] = (float) (this.height / 2) + this.centerScale;
            }

            canvas.drawPosText(this.text, this.pos, this.paint);
            if (!this.callbackDone && this.size == this.count * 2 && this.inputCompleteCallback != null) {
                (new Handler()).postDelayed(new Runnable() {
                    public void run() {
                        PayEditText.this.inputCompleteCallback.callback();
                    }
                }, 0L);
                this.callbackDone = true;
            }
        }

    }

    public void setInputCompleteCallback(InputCompleteCallback inputCompleteCallback) {
        this.inputCompleteCallback = inputCompleteCallback;
    }

    public interface InputCompleteCallback {
        void callback();
    }

    //获取输入明文
    public String getOriText() {
        return oriText.toString();
    }

    private class EncodeTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            // 获取明文密码
            int oriTextLen = oriText.toString().trim().length();
            int getTextLen = s.length();
            if (oriTextLen == getTextLen - 1) {
                oriText.append(s.charAt(getTextLen - 1));
                s.replace(getTextLen-1, getTextLen, "•");
            } else if (oriTextLen == getTextLen + 1) {
                oriText.delete(oriTextLen-1, oriTextLen);
            } else if (getTextLen == 0) {
                oriText.delete(0, oriTextLen);
            }
        }
    }

    public void setCallbackDone(boolean isDone) {
        this.callbackDone = isDone;
    }
}
