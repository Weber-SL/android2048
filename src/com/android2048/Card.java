package com.android2048;

import android.content.Context;
import android.text.TextPaint;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout {

	private int num;
	TextView label;

	public Card(Context context) {
		super(context);
		label = new TextView(context);
		label.setTextSize(32);

		TextPaint tp = label.getPaint();
		tp.setFakeBoldText(true);

		// label.setBackgroundResource(R.drawable.corners);
		label.setBackgroundColor(0xffEEE4DA);
		label.setGravity(Gravity.CENTER);

		LayoutParams params = new LayoutParams(-1, -1);// -1�������Ԫ�أ�-2���ݰ���
		params.setMargins(30, 30, 0, 0);
		addView(label, params);
	}

	public void setNum(int num) {
		this.num = num;
		// R�ļ���Դid
		if (num <= 0) {
			label.setText("");
		} else {
			label.setText(num + "");
		}
	}

	public int getNum() {
		return num;
	}

}
