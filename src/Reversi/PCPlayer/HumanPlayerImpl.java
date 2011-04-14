package Reversi.PCPlayer;


/**
* プレーヤークラス
* Humanプレーヤ
**/

import Reversi.*;
import java.util.*;

public class HumanPlayerImpl implements IPlayer{
	public String getName(){
		return "Humanプレーヤー";
	}
	
	/**
	* 手順を決定します。
	**/
	public int[] calc(byte stone, byte[][] bordData){
		int[] iData = null;
		
		System.out.print("X,Y軸座標を入力し、置く場所を指定してください。>>");
		
		while(true){
			Scanner scan= new Scanner(System.in);
			try{
				iData = Bord.cnvKifu(scan.next());
				if(iData == null)iData = new int[]{-1,-1};
				
				int buf = Bord.countStone(iData[0], iData[1], stone, bordData);
				if(buf > 0){
					break;
				}else{
					System.out.print("その場所には置けません。置く場所を指定してください。>>");
				}
			}catch(Exception err){
				System.out.print("フォーマット異常。置く場所をもう一度入力してください。>>");
			}
		}
		
		
		return iData;
	}
	
	

}