package gui.view;

import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class JoinForm extends JFrame{
	//아래의 객체들의 인스턴스는 스프링이 관리하고
	//개발자는 단지 객체를 스프링으로부터 받을 준비만 하면 된다
	private JComponent t_id;
	private JComponent t_name;
	private JComponent t_email;
	private JComponent bt_regist;
	private LayoutManager flowLayout;
	
	
	//스프링에 의한 setter 주입
	public void setT_id(JComponent t_id) {
		this.t_id = t_id;
	}


	public void setT_name(JComponent t_name) {
		this.t_name = t_name;
	}


	public void setT_email(JComponent t_email) {
		this.t_email = t_email;
	}


	public void setBt_regist(JComponent bt_regist) {
		this.bt_regist = bt_regist;
	}


	public void setFlowLayout(LayoutManager flowLayout) {
		this.flowLayout = flowLayout;
	}
	

	public void create() {
		
		//조립
		setLayout(flowLayout);
		add(t_id);
		add(t_name);
		add(t_email);
		add(bt_regist);
		
		setSize(300, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}



}
