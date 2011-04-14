package Reversi;

import java.io.*;
import java.util.*;
import Reversi.util.*;
import Reversi.PCPlayer.*;
import Reversi.learning.*;
import Reversi.PCPlayer.Analyser.*;

/**
* Reversi
* �E�Q�[�����̊Ǘ��N���X
* �E���ׂẴQ�[���������̃N���X����擾�ł���
**/

public class Reversi{
	public static final String _KIFU_PATH_ = "kifu/kifu.log";
	
	private int m_iCnt = 0;					//�Q�[���J�E���g
	private IPlayer m_WhitePlayer = null;	//���v���C���[
	private IPlayer m_BlackPlayer = null;	//���v���C���[
	private Bord m_Bord = null;				//�Q�[���Ֆ�
	private String m_sLog = "";				//����������
	
	/**
	* �R���X�g���N�^
	**/
	public Reversi(IPlayer bp, IPlayer wp){
		m_iCnt = 0;					//�Q�[���J�E���g
		m_BlackPlayer = bp;			//���v���C���[
		m_WhitePlayer = wp;			//���v���C���[
		m_Bord = new Bord();		//�Q�[���Ֆʏ�����
		m_sLog = "";				//�����̏�����
	}
	
	/**
	* main!!
	**/
	public static void main(String[] args){
		if(args.length == 0){
			for(int i = 0 ; i < 1 ; i++){
				Reversi reversi = new Reversi(new PlayerA_Impl(), new HumanPlayerImpl());
				reversi.startGame(true);
			}
		}else{
			if(args.length >= 2 && args[0].equals("-lerning")){
				for(int i = 0 ; i < Integer.parseInt(args[1]) ; i++){
					Reversi reversi = new Reversi(new EstRandPlayerImpl(), new EstRandPlayerImpl());
					reversi.startGame(true);
				}
			}else if(args.length >= 2 && args[0].equals("-study")){
				//�ߊw�K��h�����߂Ƀ��j�[�N�Ȋ����݂̂��w�K�̑ΏۂƂ��܂��B
				try{
					String sKifuPath = FileSort.uniq(new File(args[1])).getPath();
					Q_learning.replayGame(sKifuPath, args[2]);
					
					System.out.println(args[2] + " �������݊���");
					
					File f = FileSort.mageDB(new File(args[2]));
					FileSort.copy(f, new File(args[2] + ".uniqdb"));
				}catch(IOException err){
					err.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	* �Q�[���̎��s���\�b�h
	* ���s��ԋp���܂�
	**/
	public byte startGame(){
		return startGame(false);
	}
	
	/**
	* �Q�[���̎��s���\�b�h
	* ���s��ԋp���܂�
	*
	* @param output �������o�͂���ꍇ�A��ʕ\�����s���ꍇ True
	**/
	public byte startGame(boolean output){
		while(true){
			byte stone = Bord._NON_STONE_;
			String sStone = "[NULL]";
			IPlayer player = null;
			
			if(m_iCnt%2 == 0){
				stone = Bord._BLACK_STONE_;
				player = m_BlackPlayer;
				sStone = "��";
			}else if(m_iCnt%2 == 1){
				stone = Bord._WHITE_STONE_;
				player = m_WhitePlayer;
				sStone = "��";
			}
			
			
			showOutput(sStone + "[" + player.getName() +  "]�̃^�[���B\n", output);
			if(!m_Bord.isPass(stone)){
				int[] data = player.calc(stone, m_Bord.getData());
				m_Bord.setStone(data[0], data[1], stone);
				m_sLog += Bord.cnvKifu(data[0], data[1]);
				
				showOutput(">> [" + Bord.cnvKifu(data[0], data[1]) + "]\n", output);
				showOutput(m_Bord.toString() + "\n", output);
			}else{
				showOutput("Pass!!\n", output);
			}
			
			//�u���ꏊ���Ȃ��Ȃ����ꍇ�A�Q�[���I�[�o�[
			if(m_Bord.isPass(Bord._WHITE_STONE_) && m_Bord.isPass(Bord._BLACK_STONE_)){
				break;
			}
			m_iCnt++;
		}
		
		//�����o��
		if(output){
			try{
				outLogger(m_sLog);
			}catch(IOException err){
				err.printStackTrace();
			}
		}
		
		byte win_stone = showResult(m_Bord); //������
		if(win_stone == Bord._WHITE_STONE_){
			showOutput("�����̏����ł��B("+ m_iCnt + "��)\n", output);
		}else if(win_stone == Bord._BLACK_STONE_){
			showOutput("�����̏����ł��B("+ m_iCnt + "��)\n", output);
		}else{
			showOutput("�����������ł��B("+ m_iCnt + "��)\n", output);
		}
		return win_stone;
	}
	
	/**
	* ���ʏƉ�
	* ��ԑ����΂�D���Ƃ��A�ԋp���܂�
	* �����������̏ꍇ�A_NON_STONE_��ԋp���܂��B
	**/
	public static byte showResult(Bord gameBord){
		int cnt_b = 0;
		int cnt_w = 0;
		
		byte[][] data = gameBord.getData();
		for(int i = 0 ; i < data.length ; i++){
			for(int j = 0 ; j < data[i].length ; j++){
				if(data[i][j] == Bord._WHITE_STONE_){
					cnt_w++;
				}else if(data[i][j] == Bord._BLACK_STONE_){
					cnt_b++;
				}
			}
		}
		
		/**
		System.out.println("������");
		System.out.println("\t���@�F�@" + cnt_b + "��");
		System.out.println("\t���@�F�@" + cnt_w + "��");
		**/
		
		if(cnt_w > cnt_b){
			return Bord._WHITE_STONE_;
		}else if(cnt_b > cnt_w){
			return Bord._BLACK_STONE_;
		}else{
			return Bord._NON_STONE_;
		}
	}
	
	
	/**
	* ��ʏo�͂̂��߂̃��[�e�B���e�B
	**/
	public static void showOutput(String msg, boolean b){
		if(b){
			System.out.print(msg);
		}
	}
	
	/**
	* �����o�͂̂��߂̃��[�e�B���e�B
	**/
	public static void outLogger(String sKifu) throws IOException{
		BufferedWriter bw = null;
		try{
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(_KIFU_PATH_), true)));
			bw.write(sKifu + "\n");
		}finally{
			if(bw != null)bw.close();
		}
	}
	
	
}