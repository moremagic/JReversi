package Reversi.PCPlayer;


/**
* �v���[���[�N���X
* �ŏ��̐΂���Ɏ�葱����v���[��
**/

import Reversi.*;
import java.util.*;

public class EstMinPlayerImpl implements IPlayer{
	public static Random _random = new Random();
	
	public String getName(){
		return "Min�v���[���[";
	}
	
	/**
	* �菇�����肵�܂��B
	**/
	public int[] calc(byte stone, byte[][] bordData){
		return minCalc(stone, bordData);
	}
	
	/**
	* ������̐��ŕ]�����܂��B
	*
	* ���ł��Ⴂ�_�����̗p���܂��B
	* �����_���L�����ꍇ�̓����_���ɍ̗p���܂��B
	**/
	public int[] minCalc(byte stone, byte[][] bordData){
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
					}else if( cnt >= buf ){
						int rnd = _random.nextInt(2)-1;
						if(ret[0] != -1 && ret[1] != -1 && rnd % 2 == 0){
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