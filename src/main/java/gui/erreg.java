package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Bezero;
import domain.Erabiltzaile;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class erreg extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField izenaField;
	private JTextField abizenaField;
	private JTextField adinaField;
	private JTextField postaField;
	private JPasswordField pasahitzaField;
	private JLabel erroreLB;
	private JPasswordField passKonpField;
	private JTextField postaKonpField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					erreg frame = new erreg();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public erreg() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel izena = new JLabel("Izena");
		izena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		izena.setBounds(29, 21, 46, 14);
		contentPane.add(izena);
		
		JLabel abizena = new JLabel("Abizena");
		abizena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		abizena.setBounds(29, 51, 71, 14);
		contentPane.add(abizena);
		
		JLabel adina = new JLabel("Adina");
		adina.setFont(new Font("Tahoma", Font.PLAIN, 16));
		adina.setBounds(29, 81, 46, 14);
		contentPane.add(adina);
		
		JLabel posta = new JLabel("Posta");
		posta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		posta.setBounds(29, 111, 46, 14);
		contentPane.add(posta);
		
		JLabel pasahitza = new JLabel("Pasahitza");
		pasahitza.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pasahitza.setBounds(29, 163, 71, 14);
		contentPane.add(pasahitza);
		
		izenaField = new JTextField();
		izenaField.setBounds(207, 18, 135, 20);
		contentPane.add(izenaField);
		izenaField.setColumns(10);
		
		abizenaField = new JTextField();
		abizenaField.setColumns(10);
		abizenaField.setBounds(207, 48, 135, 20);
		contentPane.add(abizenaField);
		
		adinaField = new JTextField();
		adinaField.setColumns(10);
		adinaField.setBounds(207, 78, 135, 20);
		contentPane.add(adinaField);
		
		postaField = new JTextField();
		postaField.setColumns(10);
		postaField.setBounds(207, 108, 176, 20);
		contentPane.add(postaField);
		
		JButton erregistratuBt = new JButton("erregistratu");
		erregistratuBt.setBounds(156, 232, 125, 31);
		contentPane.add(erregistratuBt);
		erregistratuBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				erreg_actionPerformed(e);
			}
		});
		
		erroreLB = new JLabel("");
		erroreLB.setBounds(92, 279, 362, 20);
		contentPane.add(erroreLB);
		
		pasahitzaField = new JPasswordField();
		pasahitzaField.setBounds(207, 160, 135, 20);
		contentPane.add(pasahitzaField);
		
		passKonpField = new JPasswordField();
		passKonpField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passKonpField.setBounds(207, 193, 135, 20);
		contentPane.add(passKonpField);
		
		JLabel pasahitzaKonpLB = new JLabel("Pasahitza errepikatu");
		pasahitzaKonpLB.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pasahitzaKonpLB.setBounds(29, 193, 163, 20);
		contentPane.add(pasahitzaKonpLB);
		
		JLabel postaKonpLB = new JLabel("Posta errepikatu");
		postaKonpLB.setFont(new Font("Tahoma", Font.PLAIN, 16));
		postaKonpLB.setBounds(29, 136, 125, 14);
		contentPane.add(postaKonpLB);
		
		postaKonpField = new JTextField();
		postaKonpField.setBounds(207, 133, 176, 20);
		contentPane.add(postaKonpField);
		postaKonpField.setColumns(10);
	}
	private void erreg_actionPerformed(ActionEvent e) {
		
		BLFacade erregistroa = MainGUI.getBusinessLogic();
		erroreLB.setText("");
		
		String pass = new String(pasahitzaField.getPassword());
		String passKonp = new String(passKonpField.getPassword());
		Erabiltzaile newUser = new Bezero(izenaField.getText(), abizenaField.getText(), Integer.valueOf(adinaField.getText()), postaField.getText(), pass);
		
		int zenb;
		if(postaField.getText().contains("@mail.com") ==false || postaField.getText().contentEquals(postaKonpField.getText())== false) {
			erroreLB.setText("Posta-elektronikoa ez dago ondo");
		}
		else if(izenaField.getText().isEmpty()) {
			erroreLB.setText("Izena beharrezkoa da");
		}
		else if(abizenaField.getText().isEmpty()) {
			erroreLB.setText("Abizena beharrezkoa da");
		}
		else if(Integer.valueOf(adinaField.getText())<=18) {
			erroreLB.setText("Adin nagusia izan behar zara erregistratzeko");
		}
		else if(pass.length()<7) {
			erroreLB.setText("");
			erroreLB.setText("Pasahitzak gutxienez 8 karaktere izan behar ditu");
		}
		else if(pass.contentEquals(passKonp)==false){
			erroreLB.setText("Pasahitzak ez dira berdinak");
		 }
		
		else {
			zenb= erregistroa.storeUser(newUser);
			if (zenb==0) {
				login frame = new login();
				frame.setVisible(true);
				this.setVisible(false);
			}
			else if(zenb==1) {
				erroreLB.setText("Posta existitzen da");
			}
		}					
	}
}
