package Reversi.PCPlayer;


/**
* �v���[���[�N���X
* ��p�������_���ɕύX����v���[��
**/

import Reversi.*;
import java.util.*;

public class EstTrialPlayerImpl implements IPlayer{
	public static Random _random = new Random();
	List<IPlayer> mPList = new ArrayList<IPlayer>();
	
	public EstTrialPlayerImpl(){
		mPList.add(new EstMinPlayerImpl());
		mPList.add(new EstMaxPlayerImpl());
		mPList.add(new EstOmiPlayerImpl());
		mPList.add(new EstRandPlayerImpl());
		mPList.add(new PlayerA_Impl());
		mPList.add(new PlayerB_Impl());
		mPList.add(new PlayerC_Impl());
	}
	
	public String getName(){
		return "��p�����_���v���[���[";
	}
	
	/**
	* �菇�����肵�܂��B
	**/
	public int[] calc(byte stone, byte[][] bordData){
		int cnt = gameProgress(bordData);
		
		IPlayer p = null;
		int rnd = _random.nextInt(mPList.size());
		p = mPList.get(rnd);
		
		System.out.println("[���݂̐ݒ�F" + p.getName() + "]" + rnd);
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