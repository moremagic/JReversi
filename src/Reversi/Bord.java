package Reversi;

/**
* �Ֆʂ𒊏ۉ�����N���X
*
* �����_�̔Ֆʏ��̕ێ�
* ��͂̂��ߐ΂�ł����Ƃ��̌���
* ���̏ꏊ�ɐ΂��łĂ邩
**/
public class Bord{
	/** �Ֆʂ̃T�C�Y **/
	public static final int x_size = 8;//X�T�C�Y
	public static final int y_size = 8;//Y�T�C�Y
	
	/** ������萔 **/
	public static final byte _NON_STONE_   = 0; //�΂��Ȃ�
	public static final byte _BLACK_STONE_ = 1; //��
	public static final byte _WHITE_STONE_ = 2; //��
	public static final byte _DEBUG_STONE_ = 3; //�f�o�b�N�X�g�[��
	
	/** ���݂̔Ֆʃf�[�^ **/
	private byte[][] m_nowData = new byte[x_size][y_size];
	
	/**
	* �ʏ�̃R���X�g���N�^
	**/
	public Bord(){
		///*
		m_nowData[3][3] = _WHITE_STONE_;
		m_nowData[4][3] = _BLACK_STONE_;
		m_nowData[3][4] = _BLACK_STONE_;
		m_nowData[4][4] = _WHITE_STONE_;
		//*/

		/*
		//6��6�X�^�[�g
		m_nowData[2][2] = _WHITE_STONE_;
		m_nowData[3][2] = _BLACK_STONE_;
		m_nowData[2][3] = _BLACK_STONE_;
		m_nowData[3][3] = _WHITE_STONE_;
		*/
		
		/*
		//4��4�X�^�[�g
		m_nowData[1][1] = _WHITE_STONE_;
		m_nowData[1][2] = _BLACK_STONE_;
		m_nowData[2][1] = _BLACK_STONE_;
		m_nowData[2][2] = _WHITE_STONE_;
		*/
	}
	
	/**
	* �Ֆʂ̏�Ԃ��w�肵�ď�����
	*
	* ��DEBUG�ATEST�p�Ƃ��č쐬
	* ���񎟌��z����f�B�[�v�R�s�[����
	*/
	public Bord(byte[][] data){
		m_nowData = deepCopy(data);
	}
	
	/**
	* �Ֆʂ̏�Ԃ��w��
	* ���񎟌��z����f�B�[�v�R�s�[����
	*/
	private void setData(byte[][] data){
		m_nowData = deepCopy(data);
	}
	
	/**
	* ���݂̃f�[�^��񎟌��z��̌`�ŕԋp����
	* ���f�B�[�v�R�s�[���Ȃ��Ă͂Ȃ�Ȃ�
	**/
	public byte[][] getData(){
		return deepCopy(m_nowData);
	}
	
	/**
	* �w�肵���ꏊ�ɐ΂�u�����\�b�h
	* �΂�u����ꍇ�̓f�[�^���f�����X�V��True��ԋp����
	**/
	public boolean setStone(int x, int y, byte stone){
		byte[][] oldData = this.getData();
		this.setData(calcStone(x, y, stone, oldData));
		
		return !getData().equals(oldData);
	}
	
	/**
	* �w�肵���ꏊ�ɐ΂�u�����ꍇ�̃f�[�^���f���쐬
	**/
	public static byte[][] calcStone(int x, int y, byte stone, byte[][] bordData){
		boolean b = false;//�΂��u����t���O
		byte[][] retData = deepCopy(bordData);
		
		//���̏ꏊ�ɐ΂����łɑ��݂��Ă���ꍇ
		if(bordData[x][y] != _NON_STONE_){
			return retData;
		}
		
		//X�������ւ̂Ђ�����Ԃ�
		for(int i = x+1 ; i < x_size ; i++){
			if(retData[i][y] != _NON_STONE_){
				if(retData[i][y] == stone){
					for(int j = i ; j >= (x+1) ; j--){
						retData[j][y] = stone;
						b = true;
					}
					break;
				}
			}else{
				break;
			}
		}
		
		
		for(int i = x-1 ; i >= 0 ; i--){
			if(retData[i][y] != _NON_STONE_){
				if(retData[i][y] == stone){
					for(int j = i ; j <= (x-1) ; j++){
						retData[j][y] = stone;
						b = true;
					}
					break;
				}
			}else{
				break;
			}
		}
		
		//Y�������ւ̂Ђ�����Ԃ�
		for(int i = y+1 ; i < y_size ; i++){
			if(retData[x][i] != _NON_STONE_){
				if(retData[x][i] == stone){
					for(int j = i ; j >= (y+1) ; j--){
						retData[x][j] = stone;
						b = true;
					}
					break;
				}
			}else{
				break;
			}
		}
		for(int i = y-1 ; i >= 0 ; i--){
			if(retData[x][i] != _NON_STONE_){
				if(retData[x][i] == stone){
					for(int j = i ; j <= (y-1) ; j++){
						retData[x][j] = stone;
						b = true;
					}
					break;
				}
			}else{
				break;
			}
		}
		
		//�΂ߎ������ւ̂Ђ�����Ԃ�
		//�E��
		for(int i = x+1, j = y+1 ; i < x_size && j < y_size ; i++,j++){
			if(retData[i][j] != _NON_STONE_){
				if(retData[i][j] == stone){
					for(int k = i, l = j ; k >= (x+1) && l >= (y+1) ; k--,l--){
						retData[k][l] = stone;
						b = true;
					}
					break;
				}
			}else{
				break;
			}
		}
		
		//�E��
		for(int i = x+1, j = y-1 ; i < x_size && j >= 0 ; i++,j--){
			if(retData[i][j] != _NON_STONE_){
				if(retData[i][j] == stone){
					for(int k = i, l = j ; k >= (x+1) && l <= (y-1) ; k--,l++){
						retData[k][l] = stone;
						b = true;
					}
					break;
				}
			}else{
				break;
			}
		}
		
		//����
		for(int i = x-1, j = y-1 ; i >= 0 && j >= 0 ; i--,j--){
			if(retData[i][j] != _NON_STONE_){
				if(retData[i][j] == stone){
					for(int k = i, l = j ; k <= (x-1) && l <= (y-1) ; k++,l++){
						retData[k][l] = stone;
						b = true;
					}
					break;
				}
			}else{
				break;
			}
		}
		
		//����
		for(int i = x-1, j = y+1 ; i >= 0 && j < y_size ; i--,j++){
			if(retData[i][j] != _NON_STONE_){
				if(retData[i][j] == stone){
					for(int k = i, l = j ; k <= (x-1) && l >= (y+1) ; k++,l--){
						retData[k][l] = stone;
						b = true;
					}
					break;
				}
			}else{
				break;
			}
		}
		
		if(b)retData[x][y] = stone;
		return retData;
	}
	
	/**
	* �΂�u���ꏊ�����邩�ǂ�������������
	* �΂�u���Ȃ��ꍇ��True��ԋp�B�u����ꍇ��False��ԋp����
	**/
	public boolean isPass(byte stone){
		byte[][] data = this.getData();
		for(int i = 0 ; i < data.length ; i++){
			for(int j = 0 ; j < data[i].length ; j++){
				if( countStone(i, j, stone, data) > 0){
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	* �w�肵���ꏊ�ɐ΂�u�����ꍇ�̎擾�ΐ����J�E���g���郁�\�b�h
	* ���u�����΂̓J�E���g���Ȃ�
	* ���f�[�^���f���̍X�V�͍s��Ȃ�
	**/
	public static int countStone(int x, int y, byte stone, byte[][] oldData){
		byte[][] newData = calcStone(x, y, stone, oldData);
		
		//�قȂ�}�X�����J�E���g
		int cnt = 0;
		for(int i = 0 ; i < newData.length ; i++){
			for(int j = 0 ; j < newData[i].length ; j++){
				if(newData[i][j] != oldData[i][j])
					cnt++;
			}
		}
		
		return cnt-1;
	}
	
	/**
	* �񎟌��z��̃f�B�[�v�R�s�[���[�e�B���e�B
	**/
	public static byte[][] deepCopy(byte[][] data){
		byte[][] ret = new byte[data.length][data[0].length];
		for(int i = 0 ; i < data.length ; i++){
			for(int j = 0 ; j < data[i].length ; j++){
				ret[i][j] = data[i][j];
			}
		}
		
		return ret;
	}
	
	/**
	* �����t�H�[�}�b�g�ϊ�
	* X�����W�{Y�����W ���� �I�Z�������`���ɕϊ����郆�[�e�B���e�B
	**/
	public static String cnvKifu(int x, int y){
		return ((char)(x+97) + Integer.toString(y+1));
	}
	
	/**
	* �����t�H�[�}�b�g�ϊ�
	* �I�Z�������`�� ���� X�����W�{Y�����W �ɕϊ����郆�[�e�B���e�B
	*
	* ��[a-z][0-9] �͈͓��ŕϊ��\�ł��B
	* ���ϊ��s�\�ȏꍇnull��ԋp���܂��B
	**/
	public static int[] cnvKifu(String s){
		int[] ret = null;
		try{
			ret = new int[]{s.charAt(0)-97 , new Integer(s.charAt(1)-'0')-1};
		}catch(Exception err){}
		
		return ret;
	}
	
	/**
	* �Ֆʕ\��
	**/
	public String toString(){
		String s = " ";
		//X���C���f�N�X�Fa�`h
		for(int x = 0 ; x < x_size ; x++){
			s += " " + (char)(x+97) + " ";
		}
		s += "\n";
		
		for(int y = 0 ; y < y_size ; y++){
			s += " ";
			for(int x = 0 ; x < x_size ; x++){
				s += "+--";
			}
			s += "+\n";
			s += y+1;//Y���C���f�N�X�F�P�`�W
			for(int x = 0 ; x < x_size ; x++){
				if(m_nowData[x][y] == _BLACK_STONE_){
					s += "|��";
				}else if(m_nowData[x][y] == _WHITE_STONE_){
					s += "|��";
				}else if(m_nowData[x][y] == _DEBUG_STONE_){
					s += "|��";
				}else{
					s += "|  ";
				}
			}
			s += "|\n";
		}
		
		s += " ";
		for(int x = 0 ; x < x_size ; x++){
			s += "+--";
		}
		
		s += "+\n";
		return s;
	}
	
	
	
}