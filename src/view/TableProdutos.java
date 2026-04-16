package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.ProdutosDAO;
import model.Produtos;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TableProdutos extends javax.swing.JFrame {

    private JFrame telaAtual;
    
    
    public TableProdutos(JFrame telaAtual) {
        this.telaAtual = telaAtual;
        
        initComponents();
        listarProdutos();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        modelo = new javax.swing.table.DefaultTableModel();
        listaProdutos = new javax.swing.JTable(modelo);
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        id_produto_venda = new javax.swing.JTextPane();
        btnExcluir = new javax.swing.JButton();
        btnVoltarCadastrar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();

        setTitle("Listagem de Produtos");
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);


        
        
        modelo = new DefaultTableModel(
                new Object[][] {},
                new String[] { "ID", "Produto", "Preço"}
        );
        
        listaProdutos.setModel(modelo);
        jScrollPane1.setViewportView(listaProdutos);

        jLabel2.setText("Excluir ou atualizar produto (ID):");

        jScrollPane2.setViewportView(id_produto_venda);

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(evt -> btnExcluirActionPerformed(evt));

        btnAtualizar.setText("Atualizar produto");
        btnAtualizar.addActionListener(evt -> btnAtualizarActionPerformed(evt));

        btnVoltarCadastrar.setText("Cadastrar produto novo");
        btnVoltarCadastrar.addActionListener(evt -> btnVoltarActionPerformed(evt));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(20)
        					.addComponent(jLabel2))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(39)
        					.addComponent(btnVoltarCadastrar)))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(10)
        					.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
        					.addGap(10)
        					.addComponent(btnExcluir)
        					.addGap(2))
        				.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
        					.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
        					.addGap(49))))
        		.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
        			.addGap(20)
        			.addGroup(layout.createParallelGroup(Alignment.CENTER)
        				.addComponent(jLabel2)
        				.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnExcluir))
        			.addGap(19)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnVoltarCadastrar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {
    	
        try {
        	int id = Integer.parseInt(id_produto_venda.getText());
        	
       	 if((id_produto_venda.getText() == null || id_produto_venda.getText().trim().isEmpty())) {
 		 	JOptionPane.showMessageDialog(null, "Campo vazio ou inválido, escolha qual produto, deseja excluir!");
       	 }else {
        	
            if(ProdutosDAO.buscarPorId(id) != null) {
                ProdutosDAO.deletar(id);
                JOptionPane.showMessageDialog(null, "Produto excluido!");
                listarProdutos();
            }else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado!");
                listarProdutos();
            }
             
       	 } 
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!");
        }
        
    }

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {
    	try {
    	 if((id_produto_venda == null || id_produto_venda.getText().trim().isEmpty())) {
    		 	JOptionPane.showMessageDialog(null, "Campo vazio ou inválido, escolha qual produto, deseja editar!");
    	 }else {
    		   	new TelaAtualizarProdutos(id_produto_venda.getText().trim());
    		   	dispose(); 
    	 }
    	}catch(NumberFormatException ex){
    		JOptionPane.showMessageDialog(null, "Campo inválido");
    	}

    }

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {
    	dispose();
		new TelaCadastroProdutos();		
    }

    private void listarProdutos() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) listaProdutos.getModel();
            modelo.setRowCount(0);

            if(ProdutosDAO.listar().size() > -1) {
            	for (Produtos p : ProdutosDAO.listar()) {
            		modelo.addRow(new Object[]{
            				p.getId(),	
            				p.getNome(),
            				p.getPreco()
                });
            	}
            }else {
        		modelo.addRow(new Object[]{	
        				"-",
        				"-",
        				"Nenhum registro encontrado no momento"
        		});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar!");
        }
    }

    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnVoltarCadastrar;
    private javax.swing.JTextPane id_produto_venda;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable listaProdutos;
    private javax.swing.table.DefaultTableModel modelo;
}
