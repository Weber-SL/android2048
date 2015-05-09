package com.android2048;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

public class GameView extends GridLayout {

	// 存放卡片
	Card[][] cards = new Card[4][4];
	Random rd = new Random();
	// 存放空白卡片
	List<Point> blankCard = new ArrayList<Point>();

	public GameView(Context context) {
		super(context);
		initGame();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGame();
	}

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initGame();
	}

	private void initGame() {

		// 设置列数
		setColumnCount(4);

		setBackgroundColor(0xffBDAD9E);

		setOnTouchListener(new OnTouchListener() {

			// 触摸屏幕时坐标
			private float startX, startY;
			// 偏移值
			private float offsetX, offsetY;

			public boolean onTouch(View v, MotionEvent e) {

				switch (e.getAction()) {
				case MotionEvent.ACTION_DOWN:// 触摸
					startX = e.getX();
					startY = e.getY();

					break;
				case MotionEvent.ACTION_UP:// 手指离开

					offsetX = e.getX() - startX;
					offsetY = e.getY() - startY;
					// 比较绝对值
					if (Math.abs(offsetX) > Math.abs(offsetY)) {// 水平方向

						if (offsetX < -3) {// 向左移动 误差值3-5
							moveLeft();
						} else if (offsetX > 3) {
							moveRight();
						}

					} else {// 垂直方向

						if (offsetY < -3) {// 向上移动
							moveUp();
						} else if (offsetY > 3) {
							moveDown();
						}

					}

					break;
				}

				return true;
			}
		});
	}

	private void moveLeft() {

	}

	protected void moveDown() {
		// TODO Auto-generated method stub

	}

	protected void moveUp() {
		// TODO Auto-generated method stub

	}

	protected void moveRight() {
		// TODO Auto-generated method stub

	}

	// 添加卡片到游戏区域
	private void addCard(int width, int height) {
		Card card = null;
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				card = new Card(getContext());
				//card.setNum(22);
				// card.setBackgroundResource(R.drawable.corners);
				// 添加卡片到游戏区域
				addView(card, width, height);
				cards[x][y] = card;
			}
		}
	}

	// 自动调用，控件进行初始化或屏幕大小发生改变
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {

		super.onSizeChanged(w, h, oldw, oldh);

		int cardWidth = (Math.min(w, h) - 30) / 4;
		addCard(cardWidth, cardWidth);
		startGame();

	}

	// 在空白卡片里面产生随机数、
	private void addRandom() {
		
		blankCard.clear();
		// 找到空白卡片
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cards[x][y].getNum() <= 0) {
					// 得到空白卡片坐标
					Point p = new Point(x, y);
					blankCard.add(p);
				}
			}
		}

		//从空白卡片中随机取出一张产生新数字
		int num = rd.nextInt(blankCard.size());
		Point point = blankCard.remove(num);

		int number = rd.nextInt(3) > 0 ? 2 : 4;
		cards[point.x][point.y].setNum(number);
	}
	
	//游戏开始
	private void startGame() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				cards[x][y].setNum(0);
			}
		}
		addRandom();
	}

}
