package com.ahu.guide;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

/**
 * 
 * @author lzz
 * ������
 * ����ͼƬ,��ȡ�ļ���
 *
 */
public class AhuGuideUtil {
	
    private GuideUtil(){}

    /**
     * 
     * @param path
     * @return
     * ����ͼƬ
     */
    public static Image getImage(String path) {
        URL u = GuideUtil.class.getClassLoader().getResource(path);
        BufferedImage img = null;

        try {
            img = ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }
    
    /**
     * 
     * @param path
     * @return
     * ����ڵ���Ϣ
     */
    public static Map<Integer,Vertex> getVertex(String path) {
    	Map<Integer,Vertex> map = new HashMap<Integer, Vertex>();
    	BufferedReader fileIn = null;    	
    	String lineStr;
    	
    	try {
    		fileIn = new BufferedReader(new FileReader(path));
			while ((lineStr = fileIn.readLine()) != null) {//ÿ�ζ�ȡһ��
				Vertex vex = new Vertex();
				String[] everyNum = lineStr.split(" ");
				vex.num = Integer.parseInt(everyNum[0]);//data.txt�е�һ�е�Ԫ��Ϊ���
				vex.x = Integer.parseInt(everyNum[1]);//data.txt�еڶ��е�Ԫ��Ϊx���������
				vex.y = Integer.parseInt(everyNum[2]);//data.txt�е����е�Ԫ��ΪY���������
				//data.txt��������У���ʾ���Ϊnum�Ľڵ㵽������Ľڵ�(ֱ����ͨ�Ľڵ�),�Լ�����
				vex.pointNum = new HashMap<Integer, Integer>();//pointNum������ͨ�Ľڵ�(��ı�ź͵��ýڵ�ľ���)
			//	System.out.println("everyNum.length"+everyNum.length);
				for (int i = 3;i < everyNum.length;i+=2) {
				//	System.out.println(everyNum[i]+"value"+Integer.valueOf(everyNum[i+1]));
					vex.pointNum.put(Integer.valueOf(everyNum[i]), Integer.valueOf(everyNum[i+1]));//keyΪ������Ľڵ㣬valueΪ����
				}
				map.put(vex.num, vex);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	
		return map;
		
    }
    
    public static void setVertex(Map<Integer,Vertex> map,String outPath) {
    	BufferedWriter outFile = null;
    	Set<Integer> vertexNum = map.keySet();
    	
    	try {
			outFile = new BufferedWriter(new FileWriter(outPath));
			for (Integer v:vertexNum) {
				Vertex tmpVex = map.get(v);
				outFile.write(v + " " + tmpVex.x + " " + tmpVex.y);
				Map<Integer,Integer> tmpLength = tmpVex.pointNum;
				Set<Integer> pointNum = tmpLength.keySet();
				for (Integer i:pointNum) {
					outFile.write(" " + i + " " + tmpLength.get(i));
				}
				outFile.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
    
    public static Map<String,String> getView(String path) //��ȡ�������Ϣ�Լ�����
    {
    	Map<String,String> result = new HashMap<String, String>();
    	BufferedReader fileIn = null;
    	
    	try {
			fileIn = new BufferedReader(new FileReader(path));
			String all,first,second;
			String[] tmp;
			while ((all = fileIn.readLine()) != null) //��ȡÿһ�е�����
			{
				tmp = all.split(" ", 2);
				first = tmp[0];//view.txt��һ�б�ʾ��������
			//	System.out.println(first);
				second = tmp[1];//view.txt�ڶ��к͵����б�ʾ������ţ��Լ��������
				//System.out.println(second);
				result.put(first, second);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fileIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	
		return result;
    }
    
    public static void setView(Map<String,String> view,String outPath) {
    	BufferedWriter outFile = null;
    	Set<String> viewName = view.keySet();
    	
    	try {
			outFile = new BufferedWriter(new FileWriter(outPath));
			for (String v:viewName) {
				outFile.write(v + " " + view.get(v));
				outFile.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
    
}