package model;

public class MineModel {
	private int[][] field;
	private int mine;

	public MineModel(int size, int mine) {
		this.field = new int[size][size];
		this.mine = mine;
		khoitao();
		khoitaomin();
		khoitaomang();
	}

	public MineModel(int[][] field, int mine) {
		this.field = field;
		this.mine = mine;
	}

	public void khoitao() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				field[i][j] = 0;
			}
		}
	}

	public void khoitaomin() {
		int count = 0;
		while (count < mine) {
			int randomrows = (int) (Math.random() * field.length);
			int randomcols = (int) (Math.random() * field.length);
			if(field[randomrows][randomcols] == -1) {
			count--;
			}
			field[randomrows][randomcols] = -1;
			count++;
		}
	}

	public void kiemtrabom(int row, int col) {
		int count = 0;
		for (int rows = row - 1; rows <= row + 1; rows++) {
			for (int cols = col - 1; cols <= col + 1; cols++) {
				if (rows >= 0 && rows < field.length && cols >= 0 && cols < field[0].length) {
					if (field[rows][cols] == -1) {
						count++;
					}
				}
			}
		}
		if (field[row][col] == -1) {
			field[row][col] = -1;
		} else {
			field[row][col] = count;
		}
	}

	public void khoitaomang() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				kiemtrabom(i, j);
			}
		}
	}

	public void in() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				System.out.print(field[i][j] + "\t");
			}
			System.out.print("\n");
		}
	}
	
	public void in2() {
		int max=-2;
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if(max<field[i][j]) {
				max=field[i][j];
				}
		}
		}
		System.out.print("Nhung vi tri max la:");
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if(field[i][j]==max) {
					System.out.println(i+" "+j);
				}
			}
		}
	}

	public int[][] getField() {
		return field;
	}

	public void setField(int[][] field) {
		this.field = field;
	}

	public int getMine() {
		return mine;
	}

	public void setMine(int mine) {
		this.mine = mine;
	}

}
