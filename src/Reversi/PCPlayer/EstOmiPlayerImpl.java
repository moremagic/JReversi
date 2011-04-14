package Reversi.PCPlayer;


/**
* �v���[���[�N���X
* �i�s�x�ɂ���Đ�p��ύX����v���[��
**/

import Reversi.*;
import java.util.*;

public class EstOmiPlayerImpl implements IPlayer{
	IPlayer mA = new EstMinPlayerImpl();
	IPlayer mB = new EstMaxPlayerImpl();
	IPlayer mC = new PlayerA_Impl();
	
	public String getName(){
		return "Omi�v���[���[";
	}
	
	/**
	* �菇�����肵�܂��B
	**/
	public int[] calc(byte stone, byte[][] bordData){
		int cnt = gameProgress(bordData);
		
		IPlayer p = null;
		if(cnt < 10){
			p = mA;
		}else if(cnt < 20){
			p = mA;
		}else if(cnt < 30){
			p = mB;
		}else if(cnt < 40){
			p = mB;
		}else if(cnt < 50){
			p = mC;
		}else{
			p = mC;
		}
		
		System.out.println("[���݂̐ݒ�F" + p.getName() + "]");
		return p.calc(stone, bordData);
	}
	
	
	
	/**
	* �Q�[���i�s�x��]�����܂�
	**/
	public int gameProgress(byte[][] bordData){
		int cnt = 0;
		
		for(int i = 0 ; i < bordData.length ; i++){
			for(int j = 0 ; j < bordData[i].length ; j++){
				if(bordData[i][j] != Bord._NON_STONE_){
					cnt++;
				}
			}
		}
		
		return cnt;
	}
}