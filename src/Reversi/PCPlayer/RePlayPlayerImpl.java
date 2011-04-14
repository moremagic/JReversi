package Reversi.PCPlayer;


/**
* ���v���C�N���X
*
* �@�B�w�K�f�[�^�쐬�p�̊�������菇���Ȃ���N���X�ł��B
* �菇���o���Ă����f�[�^���܂Ƃ߂�@�\�������܂�
**/

import Reversi.*;
import Reversi.learning.*;
import java.util.*;

public class RePlayPlayerImpl implements IBordList{
	private byte m_stone = Bord._NON_STONE_;
	private String m_sKifu = "";
	
	//�{�[�h�f�[�^
	private ArrayList<byte[][]> m_bordList = new ArrayList<byte[][]>();
	
	public RePlayPlayerImpl(byte stone, String sKifu){
		m_stone = stone;
		m_sKifu = sKifu;
	}
	
	public String getName(){
		String name = "?";
		if(m_stone == Bord._WHITE_STONE_){
			name = "��";
		}else if(m_stone == Bord._BLACK_STONE_){
			name = "��";
		}else{
			name = "??";
		}
		
		return "�Đ��v���[���[[" + m_stone + "]";
	}
	
	/**
	* �菇�����肵�܂��B
	**/
	public int[] calc(byte stone, byte[][] bordData){
		int idx = gameProgress(bordData) * 2;
		String s = m_sKifu.substring(idx, idx+2);
		
		m_bordList.add(bordData);
		return Bord.cnvKifu(s);
	}
	
	/**
	* �Q�[���i�s�x��]�����܂�
	**/
	public int gameProgress(byte[][] bordData){
		int cnt = -4;//������Ԃ�4�}�X�g�p���Ă��邽��
		
		for(int i = 0 ; i < bordData.length ; i++){
			for(int j = 0 ; j < bordData[i].length ; j++){
				if(bordData[i][j] != Bord._NON_STONE_){
					cnt++;
				}
			}
		}
		
		return cnt;
	}
	
	
	/**
	* �{�[�h�f�[�^�̕ԋp
	**/
	public List<byte[][]> getBordList(){
		return m_bordList;
	}
	
}