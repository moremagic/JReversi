package Reversi;

public class BordTest{
	public static void main(String[] args){
		{System.out.println("■Xテスト+Yテスト(マイナス方向)");
			byte[][] d = new byte[Bord.x_size][Bord.y_size];
			d[7][0] = Bord._BLACK_STONE_;
			d[7][1] = Bord._WHITE_STONE_;
			d[7][2] = Bord._WHITE_STONE_;
			d[7][3] = Bord._WHITE_STONE_;
			d[7][4] = Bord._WHITE_STONE_;
			d[7][5] = Bord._WHITE_STONE_;
			d[7][6] = Bord._WHITE_STONE_;

			d[0][7] = Bord._BLACK_STONE_;
			d[1][7] = Bord._WHITE_STONE_;
			d[2][7] = Bord._WHITE_STONE_;
			d[3][7] = Bord._WHITE_STONE_;
			d[4][7] = Bord._WHITE_STONE_;
			d[5][7] = Bord._WHITE_STONE_;
			d[6][7] = Bord._WHITE_STONE_;
			
			Bord b = new Bord(d);
			System.out.println(b.toString());
			
			b.setStone(7,7,Bord._BLACK_STONE_);
			System.out.println(b.toString());
		}
		
		{System.out.println("■Xテスト+Yテスト(プラス方向)");
			byte[][] d = new byte[Bord.x_size][Bord.y_size];
			d[0][1] = Bord._WHITE_STONE_;
			d[0][2] = Bord._WHITE_STONE_;
			d[0][3] = Bord._WHITE_STONE_;
			d[0][4] = Bord._WHITE_STONE_;
			d[0][5] = Bord._WHITE_STONE_;
			d[0][6] = Bord._WHITE_STONE_;
			d[0][7] = Bord._BLACK_STONE_;

			d[1][0] = Bord._WHITE_STONE_;
			d[2][0] = Bord._WHITE_STONE_;
			d[3][0] = Bord._WHITE_STONE_;
			d[4][0] = Bord._WHITE_STONE_;
			d[5][0] = Bord._WHITE_STONE_;
			d[6][0] = Bord._WHITE_STONE_;
			d[7][0] = Bord._BLACK_STONE_;
			
			Bord b = new Bord(d);
			System.out.println(b.toString());
			
			b.setStone(0,0,Bord._BLACK_STONE_);
			System.out.println(b.toString());
		}
		
		{System.out.println("■斜め(プラス方向)");
			byte[][] d = new byte[Bord.x_size][Bord.y_size];
			d[1][1] = Bord._WHITE_STONE_;
			d[2][2] = Bord._WHITE_STONE_;
			d[3][3] = Bord._WHITE_STONE_;
			d[4][4] = Bord._WHITE_STONE_;
			d[5][5] = Bord._WHITE_STONE_;
			d[6][6] = Bord._WHITE_STONE_;
			d[7][7] = Bord._BLACK_STONE_;

			d[6][1] = Bord._WHITE_STONE_;
			d[5][2] = Bord._WHITE_STONE_;
			d[4][3] = Bord._WHITE_STONE_;
			d[3][4] = Bord._WHITE_STONE_;
			d[2][5] = Bord._WHITE_STONE_;
			d[1][6] = Bord._WHITE_STONE_;
			d[0][7] = Bord._BLACK_STONE_;
			
			Bord b = new Bord(d);
			System.out.println(b.toString());
			
			b.setStone(0,0,Bord._BLACK_STONE_);
			System.out.println(b.toString());
			
			b.setStone(7,0,Bord._BLACK_STONE_);
			System.out.println(b.toString());
		}
		
		{System.out.println("■斜め(マイナス方向)");
			byte[][] d = new byte[Bord.x_size][Bord.y_size];
			d[0][0] = Bord._BLACK_STONE_;
			d[1][1] = Bord._WHITE_STONE_;
			d[2][2] = Bord._WHITE_STONE_;
			d[3][3] = Bord._WHITE_STONE_;
			d[4][4] = Bord._WHITE_STONE_;
			d[5][5] = Bord._WHITE_STONE_;
			d[6][6] = Bord._WHITE_STONE_;
			
			d[7][0] = Bord._BLACK_STONE_;
			d[6][1] = Bord._WHITE_STONE_;
			d[5][2] = Bord._WHITE_STONE_;
			d[4][3] = Bord._WHITE_STONE_;
			d[3][4] = Bord._WHITE_STONE_;
			d[2][5] = Bord._WHITE_STONE_;
			d[1][6] = Bord._WHITE_STONE_;
			
			Bord b = new Bord(d);
			System.out.println(b.toString());
			
			b.setStone(7,7,Bord._BLACK_STONE_);
			System.out.println(b.toString());
			
			b.setStone(0,7,Bord._BLACK_STONE_);
			System.out.println(b.toString());
		}
		
		{System.out.println("■Xテスト+Yテスト");
			byte[][] d = new byte[Bord.x_size][Bord.y_size];
			d[1][0] = Bord._WHITE_STONE_;
			d[2][0] = Bord._WHITE_STONE_;
			d[3][0] = Bord._BLACK_STONE_;
			d[4][0] = Bord._WHITE_STONE_;
			d[5][0] = Bord._BLACK_STONE_;
			d[6][0] = Bord._WHITE_STONE_;

			d[0][1] = Bord._WHITE_STONE_;
			d[0][2] = Bord._WHITE_STONE_;
			d[0][3] = Bord._BLACK_STONE_;
			d[0][4] = Bord._WHITE_STONE_;
			d[0][5] = Bord._BLACK_STONE_;
			d[0][6] = Bord._WHITE_STONE_;
			
			Bord b = new Bord(d);
			System.out.println(b.toString());
			
			b.setStone(0,0,Bord._BLACK_STONE_);
			System.out.println(b.toString());
			
			b.setStone(7,0,Bord._BLACK_STONE_);
			System.out.println(b.toString());
			
			b.setStone(0,7,Bord._BLACK_STONE_);
			System.out.println(b.toString());
		}
		
		{System.out.println("■左下方向テスト");
			byte[][] d = new byte[Bord.x_size][Bord.y_size];
			d[0][7] = Bord._BLACK_STONE_;
			d[1][6] = Bord._BLACK_STONE_;
			d[2][5] = Bord._WHITE_STONE_;
			d[3][4] = Bord._BLACK_STONE_;
			d[4][3] = Bord._BLACK_STONE_;
			
			Bord b = new Bord(d);
			System.out.println(b.toString());
			
			b.setStone(5,2,Bord._WHITE_STONE_);
			System.out.println(b.toString());
		}
		
		{System.out.println("■左上方向テスト");
			byte[][] d = new byte[Bord.x_size][Bord.y_size];
			d[1][0] = Bord._WHITE_STONE_;
			d[2][1] = Bord._BLACK_STONE_;
			d[3][2] = Bord._WHITE_STONE_;
			d[4][3] = Bord._WHITE_STONE_;
			d[5][4] = Bord._WHITE_STONE_;
			
			Bord b = new Bord(d);
			System.out.println(b.toString());
			
			b.setStone(6,5,Bord._BLACK_STONE_);
			System.out.println(b.toString());
		}
		{System.out.println("■右上方向テスト");
			byte[][] d = new byte[Bord.x_size][Bord.y_size];
			d[7][0] = Bord._BLACK_STONE_;
			d[6][1] = Bord._WHITE_STONE_;
			d[5][2] = Bord._BLACK_STONE_;
			d[4][3] = Bord._BLACK_STONE_;
			d[3][4] = Bord._BLACK_STONE_;
			
			Bord b = new Bord(d);
			System.out.println(b.toString());
			
			b.setStone(2,5,Bord._WHITE_STONE_);
			System.out.println(b.toString());
		}
		{System.out.println("■右下方向テスト");
			byte[][] d = new byte[Bord.x_size][Bord.y_size];
			d[2][2] = Bord._WHITE_STONE_;
			d[3][3] = Bord._WHITE_STONE_;
			d[4][4] = Bord._WHITE_STONE_;
			d[5][5] = Bord._BLACK_STONE_;
			d[6][6] = Bord._WHITE_STONE_;
			
			Bord b = new Bord(d);
			System.out.println(b.toString());
			
			b.setStone(1,1,Bord._BLACK_STONE_);
			System.out.println(b.toString());
		}
		
		{System.out.println("■おけない場合");
			Bord b = new Bord();
			System.out.println(b.toString());
			
			b.setStone(1,1,Bord._BLACK_STONE_);
			System.out.println(b.toString());
		}
		
		{System.out.println("■DeepCopyテスト");
			byte[][] d = new byte[Bord.x_size][Bord.y_size];
			d[2][2] = Bord._WHITE_STONE_;
			d[3][3] = Bord._WHITE_STONE_;
			
			Bord b = new Bord(d);
			System.out.println(b.toString());
			
			d[0][0] = Bord._DEBUG_STONE_;
			System.out.println(b.toString());
		}
		
		{System.out.println("■isPass テスト");
			byte[][] d = new byte[Bord.x_size][Bord.y_size];
			d[0][0] = Bord._WHITE_STONE_;
			d[0][1] = Bord._BLACK_STONE_;
			d[0][2] = Bord._BLACK_STONE_;
			d[0][3] = Bord._BLACK_STONE_;
			d[0][4] = Bord._BLACK_STONE_;
			d[0][5] = Bord._BLACK_STONE_;
			d[0][6] = Bord._BLACK_STONE_;
			d[0][7] = Bord._BLACK_STONE_;
			
			d[1][0] = Bord._WHITE_STONE_;
			d[1][1] = Bord._WHITE_STONE_;
			d[1][2] = Bord._WHITE_STONE_;
			d[1][3] = Bord._WHITE_STONE_;
			d[1][4] = Bord._WHITE_STONE_;
			d[1][5] = Bord._WHITE_STONE_;
			d[1][6] = Bord._WHITE_STONE_;
			d[1][7] = Bord._WHITE_STONE_;
			
			d[2][0] = Bord._NON_STONE_;
			d[2][1] = Bord._WHITE_STONE_;
			d[2][2] = Bord._WHITE_STONE_;
			d[2][3] = Bord._WHITE_STONE_;
			d[2][4] = Bord._WHITE_STONE_;
			d[2][5] = Bord._WHITE_STONE_;
			d[2][6] = Bord._WHITE_STONE_;
			d[2][7] = Bord._WHITE_STONE_;
			
			d[3][0] = Bord._BLACK_STONE_;
			d[3][1] = Bord._WHITE_STONE_;
			d[3][2] = Bord._WHITE_STONE_;
			d[3][3] = Bord._WHITE_STONE_;
			d[3][4] = Bord._WHITE_STONE_;
			d[3][5] = Bord._WHITE_STONE_;
			d[3][6] = Bord._WHITE_STONE_;
			d[3][7] = Bord._WHITE_STONE_;
			
			d[4][0] = Bord._BLACK_STONE_;
			d[4][1] = Bord._WHITE_STONE_;
			d[4][2] = Bord._WHITE_STONE_;
			d[4][3] = Bord._WHITE_STONE_;
			d[4][4] = Bord._WHITE_STONE_;
			d[4][5] = Bord._WHITE_STONE_;
			d[4][6] = Bord._WHITE_STONE_;
			d[4][7] = Bord._WHITE_STONE_;
			
			d[5][0] = Bord._NON_STONE_;
			d[5][1] = Bord._WHITE_STONE_;
			d[5][2] = Bord._WHITE_STONE_;
			d[5][3] = Bord._WHITE_STONE_;
			d[5][4] = Bord._WHITE_STONE_;
			d[5][5] = Bord._WHITE_STONE_;
			d[5][6] = Bord._WHITE_STONE_;
			d[5][7] = Bord._NON_STONE_;
			
			d[6][0] = Bord._NON_STONE_;
			d[6][1] = Bord._WHITE_STONE_;
			d[6][2] = Bord._NON_STONE_;
			d[6][3] = Bord._BLACK_STONE_;
			d[6][4] = Bord._NON_STONE_;
			d[6][5] = Bord._WHITE_STONE_;
			d[6][6] = Bord._NON_STONE_;
			d[6][7] = Bord._NON_STONE_;
			
			d[6][0] = Bord._NON_STONE_;
			d[6][1] = Bord._NON_STONE_;
			d[6][2] = Bord._NON_STONE_;
			d[6][3] = Bord._NON_STONE_;
			d[6][4] = Bord._NON_STONE_;
			d[6][5] = Bord._NON_STONE_;
			d[6][6] = Bord._NON_STONE_;
			d[6][7] = Bord._NON_STONE_;

			Bord b = new Bord(d);
			System.out.println(b.toString());
			System.out.println(b.isPass(Bord._BLACK_STONE_));
			
			b.setStone(6,1,Bord._BLACK_STONE_);
			System.out.println(b.toString());
		}
	}
}