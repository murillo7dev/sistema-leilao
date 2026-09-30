import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class vendasVIEW extends JFrame {
    
    private JTable tabelaVendas;
    
    public vendasVIEW() {
        setTitle("Produtos Vendidos");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JLabel titulo = new JLabel("Produtos Vendidos", SwingConstants.CENTER);
        titulo.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
        
        tabelaVendas = new JTable();
        JScrollPane scroll = new JScrollPane(tabelaVendas);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> this.dispose());
        
        JPanel painelInferior = new JPanel();
        painelInferior.add(btnVoltar);
        
        setLayout(new BorderLayout(10, 10));
        add(titulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);
        
        listarVendidos();
    }
    
    private void listarVendidos() {
        ProdutosDAO produtosdao = new ProdutosDAO();
        DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Nome", "Valor", "Status"}
        );
        
        ArrayList<ProdutosDTO> listagem = produtosdao.listarProdutosVendidos();
        
        for (ProdutosDTO produto : listagem) {
            model.addRow(new Object[]{
                produto.getId(),
                produto.getNome(),
                produto.getValor(),
                produto.getStatus()
            });
        }
        
        tabelaVendas.setModel(model);
    }
}