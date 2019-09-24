package com.eight;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  JFrame a=new JFrame("计算器");
		    JTextField b=new JTextField("请输入需计算内容");
		    JButton c=new JButton("clear");
		    JButton[] d=new JButton[16];
		    String str="123+456-789*0.=/";
		    for(int i=0;i<d.length;i++) {
		    	d[i] =new JButton(str.charAt(i)+"");
		    }	  
		    a.setLayout(new BorderLayout());
		    JPanel p1=new JPanel();
		    JPanel p2=new JPanel();
		    p1.setLayout(new FlowLayout());
		    p1.add(b);
		    p1.add(c);
		    p2.setLayout(new GridLayout(4,4));
		    for(int i=0;i<d.length;i++) {
		    	p2.add(d[i]);
		    }
		    a.add(p1,BorderLayout.NORTH);
		    a.add(p2,BorderLayout.CENTER);
		    a.setVisible(true);
		    a.setSize(300,300);
	}

}
