package Reversi.PCPlayer.Analyser;


/**
* 局面管理ノード
* ゲーム解析の為の補助クラス
**/
import java.util.*;
import Reversi.*;

public class PhaseNode{
	private byte m_stone = Bord._NON_STONE_;//石の種類
	private int m_score = -1;//得点
	private int m_x = -1;
	private int m_y = -1;
	
	private PhaseNode m_parent = null;
	private List<PhaseNode> m_leaf = new ArrayList<PhaseNode>();
	
	public static long count = 0;//DEBUG
	
	/**
	* 解析ツリーの作成(トップノード)
	**/
	public static final PhaseNode createNode(byte stone, byte[][] bordData, IAnalyser analyser){
		count = 0;
		PhaseNode retNode = new PhaseNode(-1, -1, Bord._NON_STONE_, Integer.MIN_VALUE);
		createNode(stone, bordData, retNode, 0, analyser);
		
		return retNode;
	}
	
	/**
	* 解析ツリーの作成
	* ・ツリーの深さ制御は後で考える
	**/
	private static final void createNode(byte stone, byte[][] bordData, PhaseNode parent, int gen, IAnalyser analyser){
		byte nextStone = (stone == Bord._WHITE_STONE_)?Bord._BLACK_STONE_:Bord._WHITE_STONE_;
		
		for(int i = 0 ; i < bordData.length ; i++){
			for(int j = 0 ; j < bordData[i].length ; j++){
				int cnt = Bord.countStone(i, j, stone, bordData);
				if(cnt > 0){
					byte[][] n_data = Bord.calcStone(i, j, stone, bordData);
					PhaseNode n = new PhaseNode(i, j, stone, analyser.getScore(stone, n_data));
					parent.addNode(n);
					n.setParent(parent);
					
					//System.out.println("createNode! >> " + getScore(stone, n_data) + "( " + i + ", " + j + ")[" + ++count + "]");
					if(gen < 10)
						createNode(nextStone, n_data, n, ++gen, analyser);
				}
			}
		}
	}
	
	/**
	* コンストラクタ
	**/
	public PhaseNode(int x, int y, byte stone, int score){
		setLocation(x, y);
		m_stone = stone;
		m_score = score;
	}
	
	/**
	* 座標の設定
	**/
	private void setLocation(int x, int y){
		m_x = x;
		m_y = y;
	}
	
	/**
	* 葉の追加
	**/
	public void addNode(PhaseNode n){
		m_leaf.add(n);
	}
	
	/**
	* 葉の取得
	**/
	public PhaseNode[] getNodes(){
		return m_leaf.toArray(new PhaseNode[0]);
	}
	
	/**
	* 親の設定
	**/
	public void setParent(PhaseNode n){
		m_parent = n;
	}
	
	/**
	* 親の取得
	**/
	public PhaseNode getParent(){
		return m_parent;
	}
	
	/**
	* 石種別の取得
	**/
	public byte getStone(){
		return m_stone;
	}
	
	/**
	* スコアの取得
	**/
	public int getScore(){
		return m_score;
	}
	
	/**
	* Xの取得
	**/
	public int getX(){
		return m_x;
	}
	
	/**
	* Yの取得
	**/
	public int getY(){
		return m_y;
	}
}
