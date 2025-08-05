package tela_Login;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexaoMySQL.Conexao;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Tela_Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtfUsuario;
	private JPasswordField pfSenha;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Tela_Login frame = new Tela_Login();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Tela_Login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 290, 297);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuário");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsuario.setBackground(new Color(0, 0, 0));
		lblUsuario.setBounds(10, 77, 89, 27);
		contentPane.add(lblUsuario);
		
		JLabel lblNewLabel = new JLabel("Tela de Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 254, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSenha.setBackground(Color.BLACK);
		lblSenha.setBounds(10, 135, 89, 27);
		contentPane.add(lblSenha);
		
		txtfUsuario = new JTextField();
		txtfUsuario.setBounds(109, 82, 155, 20);
		contentPane.add(txtfUsuario);
		txtfUsuario.setColumns(10);
		
		pfSenha = new JPasswordField();
		pfSenha.setBounds(109, 140, 155, 20);
		contentPane.add(pfSenha);
		
		JButton btnLogin = new JButton("Entrar");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					// Criando objeto de conexão com o servidor
					Connection con = Conexao.conexao_servidor();
					
					// Inserindo os comandos que o sistema fará com o banco de dados
					String sql = "select * from tb_dados_senhas where usuario=? and senha=?";
					
					// Preparando os dados
					PreparedStatement stmt = con.prepareStatement(sql);
					
					// Informando sistema quais são os objetos que serão passados como parâmetro para verificação no banco
					// Parâmetro 1
					stmt.setString(1, txtfUsuario.getText());
					
					// Parâmetro 2
					stmt.setString(2, new String (pfSenha.getPassword()));
					
					/**
					 * stmt irá preparar a informação e executar a consulta dos resultados no banco
					 * essa consulta ficará armazenada em rs
					 * 
					 */
					ResultSet rs = stmt.executeQuery();
					
					/*
					 * Como estamos procurando um resultado no banco (se o par1 e par2 são verdadeiros)
					 * vamos criar uma função if/else para avaliar esse resultado.
					 * obs: JOptionPane é utilizada para aparecer um pop-up com o resultado.
					 */
					if(rs.next()){
						JOptionPane.showMessageDialog(null, "Esse usuário existe.");
					} else {
						JOptionPane.showMessageDialog(null, "Esse usuário não existe");
					}
					
					// Fechando o stmt e con para questões de segurança
					stmt.close();
					con.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setBounds(160, 192, 104, 27);
		contentPane.add(btnLogin);
	}
}






