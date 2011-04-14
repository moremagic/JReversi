package Reversi.PCPlayer;


/**
* �v���[���[�N���X
* �œK�Ȑ΂���Ɏ�葱����v���[��
**/

import Reversi.*;
import Reversi.PCPlayer.Analyser.*;
import java.util.*;

public class PlayerA_Impl implements IPlayer, IAnalyser{
	public static Random _random = new Random();
	
	public String getName(){
		return "�œK���v���[���[(�c���[�̑S�m�[�h�����v���Ĕ���Ɏg�p)";
	}
	
	/**
	* �Ֆʃf�[�^���猻�݂̃X�R�A���Z�o���܂�
	**/
	public int getScore(byte stone, byte[][] bordData){
		int score = 0;
		//�ΐ�
		score += AnalyserUtil.calcScoreA(stone, bordData);
		return score;
	}
	
	/**
	* �c���[�m�[�h����̃X�R�A�Z�o�p�ċA�֐�
	* �E���΂̏ꍇ�{�X�R�A
	* �E���΂̏ꍇ�|�X�R�A
	**/
	public static int getResult(PhaseNode node, byte stone){
		//�c���[�̑S�m�[�h�����v���Ĕ���Ɏg�p����
		int retScore = 0;
		if(stone == node.getStone()){
			retScore += node.getScore();
		}else{
			retScore -= node.getScore();
		}
		
		for(PhaseNode buf : node.getNodes()){
			if(buf.getNodes().length == 0){
				retScore = getResult(buf, stone);
			}else{
				getResult(buf, stone);
			}
		}
		return retScore;
	}
	
	/**
	* �菇�����肵�܂��B
	**/
	public int[] calc(byte stone, byte[][] bordData){
		
		PhaseNode n = PhaseNode.createNode(stone, bordData, this);
		
		int score = 0;
		PhaseNode result = null;
		
		for(PhaseNode buf : n.getNodes()){
			if( result == null || score < getResult(buf, stone) ){
				result = buf;
			}
		}
		
		System.out.println("[score:" + getResult(result, stone) + "]");
		return new int[]{result.getX(), result.getY()};
	}
}