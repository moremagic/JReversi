package Reversi.PCPlayer;

/**
* �ǖʉ�̓C���^�[�t�F�[�X
* ���ׂĂ̎����菇�쐬�v���O�����͂��̃C���^�[�t�F�[�X����������K�v������܂��B
**/

public interface IPlayer{
	/**
	* �v���[���[�l�[����ԋp���܂�
	**/
	public String getName();
	
	/**
	* �菇�����肵�܂��B
	*
	* �Ԃ�l�Fint{X���W,Y���W}
	**/
	public int[] calc(byte stone, byte[][] bordData);
}