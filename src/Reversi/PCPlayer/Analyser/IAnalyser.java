package Reversi.PCPlayer.Analyser;


/**
* �ǖʉ�̓N���X
* ���݂̃Q�[������͂��A�_��������C���^�[�t�F�[�X
**/

public interface IAnalyser{

	/**
	* �Ֆʃf�[�^���猻�݂̃X�R�A���Z�o���܂�
	**/
	public int getScore(byte stone, byte[][] bordData);
}
