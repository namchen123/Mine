package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import model.BangXepHang;
import model.MineModel;
import controller.MineController;
import dao.BxhDAO;

public class MineView extends JFrame implements ActionListener{
	private MineModel khoitaomin;
	private JButton[][] button;
	private boolean[][] click;
	private JPanel panelinit;
	private JPanel panelmine;
	private JPanel panelmenu;
	private JPanel panelmain;
	private JPanel paneldifficulty;
	private JPanel panelbxh;
	private JPanel panelrules;
	private JLabel labeltime1;
	private JLabel labeltime2;
	private JLabel labelflag;
	private int count;
	
	private CardLayout cl;
	private int i, j;
	private int size;
	private int mine;
	private Timer timer;
	private boolean isRunning;
	public MineView() {
		Menu();
		GameSetup();
		HighScore();
		Rules();
		View();
	}
	
	public void View() {
	    this.setTitle("Minesweeper");
	    this.setSize(900,800);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    cl = new CardLayout();
	    panelmain = new JPanel(cl);

	    panelmain.add(panelmenu,"menu");
	    panelmain.add(paneldifficulty,"choose");
	    panelmain.add(panelbxh,"bxh");
	    panelmain.add(panelrules,"rules");
	    setContentPane(panelmain);
	    setPreferredSize(new Dimension(400, 300));
	    setLocationRelativeTo(null);
	    setVisible(true);
	}
	
	public void Menu() {
		panelmenu = new JPanel();
		panelmenu.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelmenu);
		panelmenu.setLayout(null);
		
		JButton btnNewGame = new JButton("New game");
		btnNewGame.setFont(new Font("Elephant", Font.PLAIN, 20));
		btnNewGame.setForeground(Color.BLACK);
		btnNewGame.setFocusable(false);
		btnNewGame.setBackground(new Color(140,231,138));
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panelmain, "choose");
			}
		});
		btnNewGame.setBounds(350, 180, 200, 100);
		panelmenu.add(btnNewGame);
		
		JButton btnHighScore = new JButton("High score");
		btnHighScore.setFont(new Font("Elephant", Font.PLAIN, 20));
		btnHighScore.setForeground(Color.BLACK);
		btnHighScore.setFocusable(false);
		btnHighScore.setBackground(new Color(140,231,138));
		btnHighScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panelmain, "bxh");
			}
		});
		btnHighScore.setBounds(350, 300, 200, 100);
		panelmenu.add(btnHighScore);
		
		JButton btnRules = new JButton("Rules");
		btnRules.setFont(new Font("Elephant", Font.PLAIN, 20));
		btnRules.setForeground(Color.BLACK);
		btnRules.setFocusable(false);
		btnRules.setBackground(new Color(140,231,138));
		btnRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panelmain, "rules");
			}
		});
		btnRules.setBounds(350, 420, 200, 100);
		panelmenu.add(btnRules);
		JLabel picLabel = new JLabel(new ImageIcon("D:\\javaworkspace\\domin\\src\\view\\anhdomin.png"));
		picLabel.setSize(900, 800);
		panelmenu.add(picLabel);
	}
	
	public void Rules() {
		panelrules = new JPanel();
		panelrules.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelrules);
		panelrules.setLayout(null);
		JTextPane txtpnTargetTrongD = new JTextPane();
		txtpnTargetTrongD.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtpnTargetTrongD.setText("+Target\r\nTrong Dò mìn, người chơi phải mở được tất cả các ô không có mìn trên một bảng ô vuông, đồng thời không được kích nổ bất cứ một quả mìn nào. Trò chơi được xếp hạng bằng thời gian hoàn thành, vì vậy việc hoàn thành trò chơi trong thời gian sớm nhất cũng là một mục tiêu quan trọng đối với người chơi đã thành thạo.\r\n+Rules\r\nNgười chơi khởi đầu với một bảng ô vuông trống thể hiện. Click chuột vào một ô vuông trong bảng. Nếu không may trúng phải ô có mìn (điều này thường xảy ra với người mới chơi) thì trò chơi kết thúc.  Ngược lại, nếu ô đó không có mìn, một vùng các ô sẽ được mở ra cùng với những con số. Số trên một ô là lượng mìn trong 8 ô nằm quanh với ô đó.\r\nNếu chắc chắn một ô có mìn, người chơi có thể đánh dấu vào ô đó với hình lá cờ bằng cách click chuột phải.  Nếu những ô lân cận của một ô đã có đủ số mìn mà vẫn còn các ô trống khác thì những ô đó không có mìn.\r\nTrò chơi kết thúc với phần thắng dành cho người chơi nếu mở được tất cả các ô không có mìn.\r\n");
		txtpnTargetTrongD.setBounds(100, 150, 700, 500);
		JButton btnMain = new JButton("Back");
		btnMain.setFont(new Font("Elephant", Font.PLAIN, 16));
		btnMain.setForeground(Color.BLACK);
		btnMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panelmain, "menu");
			}
		});
		btnMain.setBounds(5, 5, 100, 50);
		btnMain.setBackground(Color.LIGHT_GRAY);
		panelrules.add(btnMain);
		panelrules.add(txtpnTargetTrongD);
		JLabel picLabel = new JLabel(new ImageIcon("D:\\javaworkspace\\domin\\src\\view\\anhdomin.png"));
		picLabel.setSize(900, 800);
		panelrules.add(picLabel);
	}
	
	public void HighScore() {
		BxhDAO b= new BxhDAO();
		ArrayList<BangXepHang> a = new ArrayList<>();
		panelbxh = new JPanel();
		JLabel picLabel = new JLabel(new ImageIcon("D:\\javaworkspace\\domin\\src\\view\\imagerank.png"));
		picLabel.setSize(900, 800);
		panelbxh.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelbxh);
		panelbxh.setLayout(null);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panelmain, "menu");
			}
		});
		btnNewButton.setBounds(5, 5, 100, 50);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Elephant", Font.PLAIN, 16));
		btnNewButton.setForeground(Color.BLACK);
		panelbxh.add(btnNewButton);
		a= b.layds();
		DefaultTableModel model = new DefaultTableModel(new Object[] {"Rank","Name","Level","Time"}, 0);
		JTable table = new JTable(model);
		int count=0;
		model.addRow(new Object[] {"Rank","Name","Level","Time"});
		for (BangXepHang bangXepHang : a) {
			count++;
			model.addRow(new Object[]{count,bangXepHang.getName(), bangXepHang.getLevel(), bangXepHang.getTime()});
		}
		table.setBounds(140, 174, 615, 650);
		table.setFont(new Font("Elephant", Font.PLAIN, 15));
		table.setBackground(new Color(220,200,140,1));
		panelbxh.add(table);
		panelbxh.add(picLabel);
	}

	public void GameSetup() {
		paneldifficulty = new JPanel();
		paneldifficulty.setBackground(new Color(0, 255, 255));
		paneldifficulty.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(paneldifficulty);
		paneldifficulty.setLayout(null);
		
		JButton btnNewGame = new JButton("Easy");
		btnNewGame.setFont(new Font("Elephant", Font.PLAIN, 16));
		btnNewGame.setForeground(Color.BLACK);
		btnNewGame.setBackground(Color.GREEN);
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				init(9,10);
				panelmain.setLayout(cl);
				panelmain.add(panelinit,"mine");
			    cl.show(panelmain, "mine");
			}
		});
		btnNewGame.setBounds(171, 347, 138, 70);
		paneldifficulty.add(btnNewGame);
		JButton btnHighScore = new JButton("Normal");
		btnHighScore.setFont(new Font("Elephant", Font.PLAIN, 15));
		btnHighScore.setForeground(Color.BLACK);
		btnHighScore.setBackground(Color.YELLOW);
		btnHighScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				init(15,40);
				panelmain.setLayout(cl);
				panelmain.add(panelinit,"mine");
			    cl.show(panelmain, "mine");
			}
		});
		btnHighScore.setBounds(381, 347, 138, 70);
		paneldifficulty.add(btnHighScore);
		
		JButton btnRules = new JButton("Hard");
		btnRules.setFont(new Font("Elephant", Font.PLAIN, 15));
		btnRules.setForeground(Color.BLACK);
		btnRules.setBackground(Color.RED);
		btnRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				init(25,70);
				panelmain.setLayout(cl);
				panelmain.add(panelinit,"mine");
			    cl.show(panelmain, "mine");
			}
		});
		btnRules.setBounds(597, 347, 128, 70);
		paneldifficulty.add(btnRules);
		
		JLabel lblNewLabel = new JLabel("Choose game diffuclty");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 35));
		lblNewLabel.setBounds(250, 262, 600, 100);
		paneldifficulty.add(lblNewLabel);
		JLabel picLabel = new JLabel(new ImageIcon("D:\\javaworkspace\\domin\\src\\view\\anhdomin2.png"));
		picLabel.setSize(900, 800);
		paneldifficulty.add(picLabel);
	}
	

	public void init(int size,int mine) {
		startStopwatch();
		this.size = size;
		this.mine = mine;
		MineController ac = new MineController(this);
		JPanel panelnorth= new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
		labeltime1 = new JLabel("Time:");
		labeltime2 = new JLabel("00:00:00");
		labelflag = new JLabel("            Remaining Flags: "+mine);
		this.labelflag.addMouseListener(ac);
		labeltime1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labeltime2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelflag.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labeltime2.setBackground(Color.red);
		panelnorth.add(labeltime1);
		panelnorth.add(labeltime2);
		panelnorth.add(labelflag);
		panelnorth.setPreferredSize(new Dimension(800,25));
		panelinit = new JPanel(new BorderLayout());
		panelinit.add(panelnorth,BorderLayout.NORTH);
		
		panelmine= new JPanel();
		khoitaomin = new MineModel(size,mine);
		button = new JButton[size][size];
		click = new boolean[size][size];
		panelmine.setLayout(new GridLayout(size,size));
		panelinit.add(panelmine,BorderLayout.CENTER);
		for (i = 0; i < size; i++) {
			for (j = 0; j < size; j++) {
				button[i][j] = new JButton();
				button[i][j].setPreferredSize(new Dimension(50,50));
				button[i][j].setBackground(new Color(153,255,255));
				button[i][j].setBorder(BorderFactory.createLoweredBevelBorder());
				button[i][j].setFocusable(false);
				panelmine.add(button[i][j]);
				this.click[i][j] = true;
			}
		}

		for (i = 0; i < size; i++) {
			for (j = 0; j < size; j++) {
				button[i][j].addActionListener(ac);
				button[i][j].addMouseListener(ac);
			}
		}
	}
	
	public void startStopwatch() {
	    try {
	        final AtomicInteger minutes = new AtomicInteger(0);
	        final AtomicInteger seconds = new AtomicInteger(0);
	        timer = new Timer(1000, new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (isRunning) {
	                    timer.stop();
	                    int currentSeconds = seconds.getAndIncrement();
	                    if (currentSeconds == 59) {
	                        seconds.set(0);
	                        int currentMinutes = minutes.getAndIncrement();
	                    }
	                    String time = String.format("00:%02d:%02d", minutes.get(), seconds.get());
	                    if (labeltime2 != null) {
	                        labeltime2.setText(time);
	                    } else {
	                        throw new NullPointerException("Labeltime is null");
	                    }
	                    timer.start();
	                }
	            }
	        });
	        isRunning = true;
	        timer.start();
	    } catch (NullPointerException ex) {
	        ex.printStackTrace();
	    }
	}

	public void setButtonValues(int i, int j) {
		int value = khoitaomin.getField()[i][j];
		if (value == -1) {
			button[i][j].setText("X");
			button[i][j].setBackground(Color.RED);
		} else {
			button[i][j].setText(Integer.toString(value));
			button[i][j].setBackground(Color.GREEN);
		}
		this.click[i][j] = false;
	}

	public void loang(int row, int col) {
	    if (click[row][col]) {
	        click[row][col] = false;
	        int value = khoitaomin.getField()[row][col];
	        if (value == 0) {
	            button[row][col].setBackground(Color.GRAY);
	            for (int r = row - 1; r <= row + 1; r++) {
	                for (int c = col - 1; c <= col + 1; c++) {
		                if (c >= 0 && c < khoitaomin.getField()[0].length && r >= 0 && r < khoitaomin.getField()[0].length) {
		                    if (click[r][c]) {
		                        loang(r, c);
		                    }
		                }
		            }
	            }
	        } else {
	            setButtonValues(row, col);
	            Mine(row, col);
	        }
	    }
	}
	
	public void  Mine(int row,int col) {
		int value= khoitaomin.getField()[row][col];
		if(value==-1) {
			count++;
			if(count==1) {
			Loss();
			}
		}else {
			if(Check()) {
				timer.stop();
				String name = JOptionPane.showInputDialog(null, "Nhập tên của bạn:");
				String level = null;
				if(size==9) {
					level = "Easy";
				}else if(size==15) {
					level = "Normal";
				}else if(size==25) {
					level = "Hard";
				}
				BangXepHang bxh = new BangXepHang(name,labeltime2.getText(),level);
				BxhDAO.getInstance().them(bxh);
				int option= JOptionPane.showConfirmDialog(null,"You won,do you want to play again","",JOptionPane.YES_NO_OPTION);
				if(option==0) {
					this.dispose();
					new MineView();
				} else {
					System.exit(1);
				}
			}
		}
	}
	
	public void Loss() {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(khoitaomin.getField()[i][j]==-1) {
					button[i][j].setText("X");
					button[i][j].setBackground(getForeground().RED);
				}
			}
		}
		timer.stop();
		if(count==1) {
		int option= JOptionPane.showConfirmDialog(null,"You loss, do you want to play again","Play again",JOptionPane.YES_NO_OPTION);
		if(option==0) {
			this.dispose();
			new MineView();
		} else {
			System.exit(1);
		}
		}
		System.out.println(count);
	}
	
	public boolean Check() {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(click[i][j]==true && khoitaomin.getField()[i][j]!=-1) {
					return false;
				}
			}
		}
		return true;
	}
	public void RemainingFlag() {
		int flag= mine;
		flag--;
		labelflag.setText("Remaining flag: "+ flag);
	}

	public JButton[][] getButton() {
		return button;
	}

	public void setButton(JButton[][] button) {
		this.button = button;
	}

	public void Flag(JButton button, int i, int j) {
		button.setText("F");
	}

	public void RemoveFlag(JButton button, int i, int j) {
		button.setText("");
	}

	public void RemoveFlag(JButton button) {
		button.setText("");
	}

	public MineModel getKhoitaomin() {
		return khoitaomin;
	}

	public void setKhoitaomin(MineModel khoitaomin) {
		this.khoitaomin = khoitaomin;
	}

	public int getsize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public JLabel getLabelflag() {
		return labelflag;
	}

	public void setLabelflag(JLabel labelflag) {
		this.labelflag = labelflag;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

