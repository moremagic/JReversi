package Reversi.learning;

/**
* Q���[�j���O�N���X�ł�
* �ȉ��Q�̋@�\�������܂��B
*
* �P�D��������Q�l���v�Z��DB�ɕۑ����܂�
* �Q�D�Ֆʂ���Q�l��ԋp���܂�
**/

import java.io.*;
import java.util.*;
import Reversi.*;
import Reversi.PCPlayer.*;

public class Q_learning{
	
	/**
	* �����t�@�C������Q�[�������v���C���AQ�l���v�Z����
	* ��������Q�l���i�[����DB�t�@�C�����쐬���܂��B
	**/
	public static void replayGame(String sKifuPath, String qdb_paht){
		try{
			int cnt = 0;
			
			
			BufferedReader r = new BufferedReader(new FileReader(new File(sKifuPath)));
			String sLine = "";
			while((sLine = r.readLine()) != null){
				RePlayPlayerImpl black_p = new RePlayPlayerImpl(Bord._BLACK_STONE_, sLine);
				RePlayPlayerImpl white_p = new RePlayPlayerImpl(Bord._WHITE_STONE_, sLine);
				
				Reversi reversi = new Reversi(black_p, white_p);
				try{
					byte win = reversi.startGame();
					
					
					/**
					//�������炷�ׂĂ̔Ֆʂ͂P�A�t��-�P�_�Ƃ���
					int point_black = (win == Bord._BLACK_STONE_)?1:-1;
					int point_white = (win == Bord._WHITE_STONE_)?1:-1;
					output( calcQ(black_p, point_black), qdb_paht);
					output( calcQ(white_p, point_white), qdb_paht);
					**/
					
					
					//���҂̂ݏo�́B�萔��_���Ƃ���
					if(win == Bord._BLACK_STONE_){
						output( calcQwin(black_p), qdb_paht);
						output( calcQlose(white_p), qdb_paht);
					}else{
						output( calcQwin(white_p), qdb_paht);
						output( calcQlose(black_p), qdb_paht);
					}
					
					
				}catch(Exception err){
					System.err.println("err:" + sLine);
					err.printStackTrace();
				}
				
				//�K�C�_���X
				if( cnt++ % 10 == 0){
					System.out.print(".");
				}
			}
		}catch(Exception err){
			err.printStackTrace();
		}
		
		System.out.println("ok.");
	}
	
	/**
	* DB�o�͂̂��߂̃��[�e�B���e�B
	**/
	public int getQ(byte[][] bordData){
		return 0;
	}

	/**
	* �{�[�h���X�g�o��
	* ���̓_�����{�[�h�ɂ���
	**/
	private static String calcQ(RePlayPlayerImpl p, int qPlus){
		StringBuilder ret = new StringBuilder();
		
		List<byte[][]> bl = p.getBordList();
		for(byte[][] bord_buf:bl){
			for(int i = 0 ; i < bord_buf.length ; i++){
				for(int j = 0 ; j < bord_buf[i].length ; j++){
					ret.append(bord_buf[i][j]);
				}
			}
			ret.append("\t" + qPlus + "\n");
		}
		
		return ret.toString();
	}

	/**
	* �{�[�h���X�g�o��
	* �萔��_���Ƃ����f�[�^���o��
	**/
	private static String calcQwin(RePlayPlayerImpl p){
		StringBuilder ret = new StringBuilder();
		
		int cnt = 0;
		
		List<byte[][]> bl = p.getBordList();
		for(byte[][] bord_buf:bl){
			for(int i = 0 ; i < bord_buf.length ; i++){
				for(int j = 0 ; j < bord_buf[i].length ; j++){
					ret.append(bord_buf[i][j]);
				}
			}
			ret.append("\t" + ++cnt + "\n");
		}
		
		return ret.toString();
	}
	
	/**
	* �{�[�h���X�g�o��
	* �萔��_���Ƃ����f�[�^���o��
	**/
	private static String calcQlose(RePlayPlayerImpl p){
		StringBuilder ret = new StringBuilder();
		
		int cnt = 0;
		
		List<byte[][]> bl = p.getBordList();
		for(byte[][] bord_buf:bl){
			for(int i = 0 ; i < bord_buf.length ; i++){
				for(int j = 0 ; j < bord_buf[i].length ; j++){
					ret.append(bord_buf[i][j]);
				}
			}
			ret.append("\t-" + ++cnt + "\n");
		}
		
		return ret.toString();
	}
	
	/**
	* DB�o�͂̂��߂̃��[�e�B���e�B
	**/
	private static void output(String sDB, String qdb_paht) throws IOException{
		BufferedWriter bw = null;
		try{
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(qdb_paht), true)));
			bw.write(sDB + "\n");
		}finally{
			if(bw != null)bw.close();
		}
	}
	
}