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

	// ��ſ�Ƭ
	Card[][] cards = new Card[4][4];
	Random rd = new Random();
	// ��ſհ׿�Ƭ
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

		// ��������
		setColumnCount(4);

		setBackgroundColor(0xffBDAD9E);

		setOnTouchListener(new OnTouchListener() {

			// ������Ļʱ����
			private float startX, startY;
			// ƫ��ֵ
			private float offsetX, offsetY;

			public boolean onTouch(View v, MotionEvent e) {

				switch (e.getAction()) {
				case MotionEvent.ACTION_DOWN:// ����
					startX = e.getX();
					startY = e.getY();

					break;
				case MotionEvent.ACTION_UP:// ��ָ�뿪

					offsetX = e.getX() - startX;
					offsetY = e.getY() - startY;
					// �ȽϾ���ֵ
					if (Math.abs(offsetX) > Math.abs(offsetY)) {// ˮƽ����

						if (offsetX < -3) {// �����ƶ� ���ֵ3-5
							moveLeft();
						} else if (offsetX > 3) {
							moveRight();
						}

					} else {// ��ֱ����

						if (offsetY < -3) {// �����ƶ�
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

	// ��ӿ�Ƭ����Ϸ����
	private void addCard(int width, int height) {
		Card card = null;
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				card = new Card(getContext());
				//card.setNum(22);
				// card.setBackgroundResource(R.drawable.corners);
				// ��ӿ�Ƭ����Ϸ����
				addView(card, width, height);
				cards[x][y] = card;
			}
		}
	}

	// �Զ����ã��ؼ����г�ʼ������Ļ��С�����ı�
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {

		super.onSizeChanged(w, h, oldw, oldh);

		int cardWidth = (Math.min(w, h) - 30) / 4;
		addCard(cardWidth, cardWidth);
		startGame();

	}

	// �ڿհ׿�Ƭ��������������
	private void addRandom() {
		
		blankCard.clear();
		// �ҵ��հ׿�Ƭ
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cards[x][y].getNum() <= 0) {
					// �õ��հ׿�Ƭ����
					Point p = new Point(x, y);
					blankCard.add(p);
				}
			}
		}

		//�ӿհ׿�Ƭ�����ȡ��һ�Ų���������
		int num = rd.nextInt(blankCard.size());
		Point point = blankCard.remove(num);

		int number = rd.nextInt(3) > 0 ? 2 : 4;
		cards[point.x][point.y].setNum(number);
	}
	
	//��Ϸ��ʼ
	private void startGame() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				cards[x][y].setNum(0);
			}
		}
		addRandom();
	}

}
