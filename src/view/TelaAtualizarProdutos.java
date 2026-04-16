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
import java.awt.Font;


public class TelaAtualizarProdutos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtProdutos;
	private JTextField txtPreco;
	private JButton btnAtualizar;
	private JButton btnVoltarListar;
	private JTextField txtId;
	
	

	public TelaAtualizarProdutos(String id) {
		
		initComponents(id);  
	}

	@SuppressWarnings("unchecked")
	private void initComponents(String id) {

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		   

		        setTitle("Atualização de Produtos");
		        setSize(400, 279);
		        getContentPane().setLayout(new GridLayout(5, 2));
		        
		        getContentPane().add(new JLabel("ID:"));
		        txtId = new JTextField();
		        txtId.setFont(new Font("Tahoma", Font.BOLD, 11));
		        getContentPane().add(txtId);
		        txtId.setText(id);
		        txtId.setEditable(false);
		        txtId.setEnabled(false);
		        
		        
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

		        
		        
		        btnVoltarListar = new JButton("Voltar");
		        btnVoltarListar.addActionListener(e -> {
		        		new TableProdutos(this);
		        		dispose(); 	
		        });
		        getContentPane().add(btnVoltarListar);
		        
		        btnAtualizar = new JButton("Atualizar");
		        btnAtualizar.addActionListener(e -> {
		        	String produto = txtProdutos.getText();
		        	String preco = txtPreco.getText();
		        	String idList = id;
		        	if(validarDados(idList, produto, preco)) {
		        		Produtos prd = new Produtos();
		        		prd.setId(Integer.parseInt(idList));
		        		prd.setNome(produto);
		        		prd.setPreco(Double.parseDouble(preco));
		        		ProdutosDAO.atualizar(prd);
		        		JOptionPane.showMessageDialog(null, "Produto do ID (" + id + ") atualizado na tabela!");
		        		new TableProdutos(this);
		        		dispose();
		        	}
		        });
		        getContentPane().add(btnAtualizar);
		        
		        setVisible(true);
		
		
	}
	public boolean validarDados(String id, String produto, String preco) {
		try{
			if(ProdutosDAO.buscarPorId(Integer.parseInt(id)) == null) {
				JOptionPane.showMessageDialog(null, "Impossível atualizar, produto não encontrado");
				return false;
			}
				if ((produto == null || produto.trim().isEmpty()) 
					|| (preco == null || preco.trim().isEmpty())  
					|| (preco.contains(" ")) || (preco.contains(" "))) {
					
						JOptionPane.showMessageDialog(null, "Campos vazios ou com espaços");
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