package Reversi.util;

/**
 * �t�@�C���\�[�g������v���O����(����)
 **/

import java.io.*;
import java.util.*;

public class FileSort{
	private static final int _BUF_SIZE_ = 50000;
	private static String _FILE_ENC_ = "SJIS";
	
	public static void main(String[] argv){
		try{
			File f = mageDB(new File(argv[0]));
			copy(f, new File(argv[0] + ".uniqdb"));
		}catch(IOException err){
			err.printStackTrace();
		}
	}
	
	
	/**
	 * ����ȃt�@�C�����}�[�W�\�[�g���܂�
	 **/
	public static boolean sort(File in , File out){
		boolean ret = false;
		
		try{
			BufferedWriter br = null;
			try{
				br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out), _FILE_ENC_));
				File[] tmps = createSortFiles(in);
				
				File fBuf = null;
				for(File f:tmps){
					if(fBuf == null){
						fBuf = f;
					}else{
						File buf = sortMarge(fBuf, f);
						deleteFile(f);
						deleteFile(fBuf);
						
						fBuf = buf;
					}
				}
				
				copy(fBuf, out);
				deleteFile(fBuf);
				
				ret = true;
			}finally{
				if(br != null)br.close();
			}
		}catch(IOException err){
			err.printStackTrace();
		}
		return ret;
	}
	
	/**
	* �m���ȃt�@�C���폜
	**/
	public static void deleteFile(File f){
		int cnt = 0;
		//�m���ȃt�@�C���폜
		while(f.exists()){
			System.out.println("\t" + f.getName() + " [" + ++cnt + "]");
			f.delete();
		}
		System.out.println(f.getName() + "�폜����");
	}
	
	/**
	* FileCopy
	**/
	public static void copy(File in, File out){
		try{
			BufferedInputStream bin = null;
			BufferedOutputStream bout = null;
			try{
				bin  = new BufferedInputStream(new FileInputStream(in));
				bout = new BufferedOutputStream(new FileOutputStream(out));
				
				int cnt = 0;
				byte[] buf = new byte[512];
				while((cnt = bin.read(buf, 0, buf.length)) != -1){
					bout.write(buf, 0, cnt);
				}
				
			}finally{
				if(bin != null)bin.close();
				if(bout != null)bout.close();
			}
		}catch(Exception err){
			err.printStackTrace();
		}
	}
	
	/**
	 * in1, in2�t�@�C�����}�[�W�\�[�g����
	 * �t�@�C���ɏ����o���܂�
	 **/
	private static File sortMarge(File in1 , File in2) throws IOException{
		File f = createTempFile();
		
		BufferedWriter bw  = null;
		BufferedReader br1 = null;
		BufferedReader br2 = null;
		try{
			bw  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), _FILE_ENC_));
			br1 = new BufferedReader(new InputStreamReader(new FileInputStream(in1), _FILE_ENC_));
			br2 = new BufferedReader(new InputStreamReader(new FileInputStream(in2), _FILE_ENC_));
			
			String line1 = null;
			String line2 = null;
			
			while(true){
				//�f�[�^�Ǎ�
				if(line1 == null)line1 = br1.readLine();
				if(line2 == null)line2 = br2.readLine();

				//�f�[�^�I�[
				if(line1 == null && line2 == null){
					break;
				}

				//�}�[�W�\�[�g
				if(line1 != null && line2 != null){
					int icmp = line1.compareTo(line2);
					if(icmp > 0){
						bw.write(line2 + "\n");
						line2 = null;
					}else{
						bw.write(line1 + "\n");
						line1 = null;
					}
				}else if(line1 != null){
					bw.write(line1 + "\n");
					line1 = null;
				}else if(line2 != null){
					bw.write(line2 + "\n");
					line2 = null;
				}
			}
		}finally{
			if(bw  != null)bw.close();
			if(br1 != null)br1.close();
			if(br2 != null)br2.close();
		}
		
		return f;
	}
	
	/**
	 * ���̓t�@�C������ꎞ�t�@�C���Q���쐬���܂�
	 **/
	private static File[] createSortFiles(File in) throws IOException{
		List<File> retList = new ArrayList<File>();
		
		BufferedReader br = null;
		try{
			br = new BufferedReader(new InputStreamReader(new FileInputStream(in), _FILE_ENC_));
			
			int cnt = 0;
			String[] buf = new String[_BUF_SIZE_];
			
			String line = "";
			while((line = br.readLine()) != null){
				buf[cnt++] = line;
				
				if(_BUF_SIZE_ <= cnt){
					retList.add( writeTmp(buf, 0, buf.length) );
					cnt = 0;
				}
			}
			retList.add( writeTmp(buf, 0, cnt) );
		}finally{
			if(br != null)br.close();
		}
		
		return retList.toArray(new File[0]);
	}
	
	/**
	* �v���O�����I�����ɏ�����
	* �ꎞ�t�@�C�������o�����\�b�h
	**/
	private static File createTempFile(){
		File f = new File(System.getProperty("java.io.tmpdir"), "~sort" + System.nanoTime() + ".tmp");
		f.deleteOnExit();
System.out.println("create_tmp-> " + f.getPath());
		return f;
	}
	
	/**
	* Sort�p�ꎞ�t�@�C���Q���쐬���܂�
	* �w�肵��String[]��Sort���ď�������tmpFile���쐬���܂�
	**/
	private static File writeTmp(String[] lines, int startIdx, int endIdx) throws IOException{
		File f = createTempFile();
		BufferedWriter bw = null;
		try{
			String[] wLines = new String[endIdx-startIdx];
			System.arraycopy(lines, startIdx, wLines, 0, wLines.length);
			
			
			Arrays.sort(wLines);
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), _FILE_ENC_));
			for(int i = 0 ; i < wLines.length ; i++){
				bw.write(wLines[i] + "\n");
			}
		}finally{
			if(bw != null)bw.close();
		}
		
		return f;
	}
	
	/**
	 * Sort��A�d���s�������܂�
	 **/
	public static File uniq(File in) throws IOException{
		File fsort = createTempFile();
		File funiq = createTempFile();
		sort(in, fsort);
		
		BufferedWriter bw  = null;
		BufferedReader br = null;
		try{
			bw  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(funiq), _FILE_ENC_));
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fsort), _FILE_ENC_));
			
			
			String line_buf = "";
			String line = "";
			while((line = br.readLine()) != null){
				if(!line_buf.equals(line)){
					bw.write(line + "\n");
				}
				line_buf = line;
			}
		}finally{
			if(bw  != null)bw.close();
			if(br != null)br.close();
		}
		
		return funiq;
	}
	
	/**
	 * Sort��A�d���s�������܂�
	 **/
	public static File mageDB(File db) throws IOException{
		File fsort = createTempFile();
		File funiq = createTempFile();
		sort(db, fsort);
		
		BufferedWriter bw  = null;
		BufferedReader br = null;
		try{
			bw  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(funiq), _FILE_ENC_));
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fsort), _FILE_ENC_));
			
			
			String bordData_buf = null;
			int q_buf = 0;
			
			String line = "";
			while((line = br.readLine()) != null){
				if(line.split("\t").length != 2)continue;
				
				String bordData = line.split("\t")[0];
				int q = Integer.parseInt(line.split("\t")[1]);
				
				if(bordData_buf == null){
					//�ŏ��̃f�[�^�̏ꍇ�͉������Ȃ�
				}else if(bordData_buf.equals(bordData)){
					//Q�l�̉��Z
					q_buf += q;
				}else{
					bw.write(bordData_buf + "\t" + q_buf + "\n");
					bordData_buf = null;
					q_buf = 0;
				}
				
				//��O�̍s���o�b�N�A�b�v
				bordData_buf = bordData;
				q_buf = q;
			}
			
			if(bordData_buf != null){
				bw.write(bordData_buf + "\t" + q_buf + "\n");
			}
		}finally{
			if(bw  != null)bw.close();
			if(br != null)br.close();
		}
		
		return funiq;
	}
}
