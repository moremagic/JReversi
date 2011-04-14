package Reversi.PCPlayer;


/**
* プレーヤークラス
* ランダムに石を常に取り続けるプレーヤ
**/

import Reversi.*;
import java.util.*;

public class EstRandPlayerImpl implements IPlayer{
	public static Random _random = new Random(System.nanoTime());
	
	public String getName(){
		return "ランダムプレーヤー";
	}
	
	/**
	* 手順を決定します。
	**/
	public int[] calc(byte stone, byte[][] bordData){
		//_random.setSeed(System.nanoTime());
		return randCalc(stone, bordData);
	}
	
	/**
	* 取得できる場所をランダムに選択します。
	**/
	public int[] randCalc(byte stone, byte[][] bordData){
		int cnt = -1;
		int[] ret = {-1, -1};
		
		for(int i = 0 ; i < bordData.length ; i++){
			for(int j = 0 ; j < bordData[i].length ; j++){
				int buf = Bord.countStone(i, j, stone, bordData);
				if( buf > 0 ){
					if( cnt == -1 ){
						cnt = buf;
						ret[0] = i;
						ret[1] = j;
					}else{
						int rnd = _random.nextInt(2)-1;
						if(rnd % 2 == 0){
							continue;
						}else{
							cnt = buf;
							ret[0] = i;
							ret[1] = j;
						}
					}
				}
			}
		}
		
		return ret;
	}
	

}