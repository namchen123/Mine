package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import view.MineView;

public class MineController implements ActionListener, MouseListener {
	private MineView mineView;

	public MineController(MineView mineView) {
		super();
		this.mineView = mineView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int count = 0;
		int flag = 0;
		JButton button = (JButton) e.getSource();
		if (SwingUtilities.isRightMouseButton(e)) {
			if (button.getText().equals("") && button.getBackground().equals(Color.GRAY) == false) {
				button.setText("F");
				button.setBackground(Color.YELLOW);
			} else if (button.getText().equals("F")) {
				button.setText("");
				button.setBackground(new Color(153, 255, 255));
				;
			}
			String flag1;
			for (int i = 0; i < mineView.getsize(); i++) {
				for (int j = 0; j < mineView.getsize(); j++) {
					if (mineView.getButton()[i][j].getText().equals("F")) {
						count++;
						flag = mineView.getKhoitaomin().getMine() - count;
						flag1 = String.valueOf(flag);
						mineView.getLabelflag().setText("Remaining Flags: " + flag1);
					}
				}
			}
			if (count > mineView.getKhoitaomin().getMine()) {
				mineView.RemoveFlag(button);
				button.setBackground(new Color(153, 255, 255));
				;
			}
		}
		System.out.println(count + " " + flag);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton) e.getSource();
		for (int i = 0; i < mineView.getsize(); i++) {
			for (int j = 0; j < mineView.getsize(); j++) {
				if (button == this.mineView.getButton()[i][j] && this.mineView.getButton()[i][j].getText().equals("")) {
					mineView.loang(i, j);
				}
				if (this.mineView.getButton()[i][j].getText().equals("F")) {
					mineView.getButton()[i][j].setText("F");
				}
			}
		}
	}

}
