package Reversi.PCPlayer;


/**
* �v���[���[�N���X
* �œK�Ȑ΂���Ɏ�葱����v���[��
**/

import Reversi.*;
import Reversi.PCPlayer.Analyser.*;
import java.util.*;

public class PlayerC_Impl implements IPlayer, IAnalyser{
	public static Random _random = new Random();
	
	public String getName(){
		return "�œK���v���[���[(���[�t�m�[�h�݂̂����v������Ɏg�p)";
	}
	
	/**
	* �Ֆʃf�[�^���猻�݂̃X�R�A���Z�o���܂�
	**/
	public int getScore(byte stone, byte[][] bordData){
		int score = 0;
		//�P���Ȑΐ����Z
		//score = AnalyserUtil.calcScoreA(stone, bordData);
		
		//�΂̍���
		score += AnalyserUtil.calcScoreF(stone, bordData);
		
		//�p�Ίp�}�X�������ꍇ�̃X�R�A���Z
		//score -= (AnalyserUtil.calcScoreE(stone, bordData) * 200);
		
		//�p�΂������ꍇ�̃X�R�A���Z
		score += AnalyserUtil.calcScoreC(stone, bordData)*100;
		
		
		/*
		//����\���ɂ��X�R�A���Z
		int score_b = AnalyserUtil.calcScoreB(stone, bordData);
		if(score_b != 0){
			score += (score_b * 10);
		}else{
			score -= 100;
		}
		*/
		
		return score;
	}
	

	/**
	* �c���[�m�[�h����̃X�R�A�Z�o�p�ċA�֐�
	* �E���΂̏ꍇ�{�X�R�A
	* �E���΂̏ꍇ�|�X�R�A
	**/
	public static int getResult(PhaseNode node, byte stone){
		//���[�t�m�[�h�݂̂����v������Ɏg�p����o�[�W����
		int retScore = 0;
		if(node.getNodes().length != 0){
			for(PhaseNode buf : node.getNodes()){
				retScore += getResult(buf, stone);
			}
		}else{
			int s = 0;
			if(stone == node.getStone()){
				s += node.getScore();
			}else{
				s -= node.getScore();
			}
			return s;
		}
		return retScore;
	}
	
	/**
	* �菇�����肵�܂��B
	**/
	public int[] calc(byte stone, byte[][] bordData){
		
		PhaseNode n = PhaseNode.createNode(stone, bordData, this);
		PhaseNode result = new PhaseNode(-1, -1, Bord._NON_STONE_, Integer.MIN_VALUE);
		
		for(PhaseNode buf : n.getNodes()){
			if( getResult(result, stone) < getResult(buf, stone) ){
				result = buf;
			}
		}
		
		System.out.println("[score:" + getResult(result, stone) + "]");
		return new int[]{result.getX(), result.getY()};
	}
}