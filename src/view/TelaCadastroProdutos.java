package view;

import java.awt.EventQueue;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.ProdutosDAO;
import model.Produtos;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;


public class TelaCadastroProdutos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtProdutos;
	private JTextField txtPreco;
	private JButton btnCadastrar;
	private JButton btnListar;


	    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroProdutos frame = new TelaCadastroProdutos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	@SuppressWarnings("unchecked")
	public TelaCadastroProdutos() {
		initComponent();    
	}
	
	private void initComponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		   

		        setTitle("Cadastro de Produtos");
		        setSize(400, 279);
		        getContentPane().setLayout(new GridLayout(4, 2));

		        getContentPane().add(new JLabel("Produto:"));
		        txtProdutos = new JTextField();

		        getContentPane().add(txtProdutos);

		        getContentPane().add(new JLabel("Preço:"));
		        txtPreco = new JTextField();
		        txtPreco.addKeyListener(new java.awt.event.KeyAdapter() {
		            public void keyTyped(java.awt.event.KeyEvent evt) {
		                char c = evt.getKeyChar();

		                if (!Character.isDigit(c)) {
		                    evt.consume(); 
		                }
		            }
		        });
		        getContentPane().add(txtPreco);

		        
		        
		        btnListar = new JButton("Listar produtos");
		        btnListar.addActionListener(e -> {
		        		new TableProdutos(this);
		        		dispose();
		        	
		        });

		        getContentPane().add(btnListar);

		        
		        btnCadastrar = new JButton("Cadastrar");
		        btnCadastrar.addActionListener(e -> {
		        	String produto = txtProdutos.getText();
		        	String preco = txtPreco.getText();
		        	
		        	if(validarDados(produto, preco)) {
		        		Produtos prd = new Produtos();
		        	
		        		prd.setNome(produto);
		        		prd.setPreco(Double.parseDouble(preco));
		        		
		        		ProdutosDAO.inserir(prd);
		        		
		        		JOptionPane.showMessageDialog(null, "Produto cadastrado!");
		        		new TableProdutos(this);
		        		dispose();
		        	}
		        });
		        getContentPane().add(btnCadastrar);
		        setVisible(true);
	}

	public boolean validarDados(String produto, String preco) {
		try{
			if ((produto == null || produto.trim().isEmpty()) 
					|| (preco == null || preco.trim().isEmpty())  
					|| (preco.contains(" ")) || (preco.contains(" "))) {
				JOptionPane.showMessageDialog(null, "Campos vazios");
				return false;
			}else {
				return true;
			}
		}catch(NullPointerException ex) {
			JOptionPane.showMessageDialog(null, "Campos inválidos");
			return false;
		}	
	}
}
