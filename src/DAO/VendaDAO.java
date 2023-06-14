/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import apoio.ConexaoBD;
import apoio.IDAO_T;
import entidades.Venda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author gusta
 */
public class VendaDAO implements IDAO_T<Venda> {
    
    ResultSet resultadoQ = null;
    
    @Override
    public String salvar(Venda o) {
        
        try {
            String sql = ""
                    + "INSERT INTO clientes (nome, email, cpf, telefone, cidade_id) VALUES ("
                    + "'" + o.getData() + "',"
                    + "'" + o.getObservacao() + "',"
                    + "'" + o.getEnderecoEntrega() + "',"
                    + "'" + o.getQuantidade() + "',"
                    + "'" + o.getValor() + "',"
                    + "'" + o.getUsuarioId() + "',"
                    + "'" + o.getClienteId() + "',"
                    + " " + o.getFormaPagamentoId() + " "
                    + ")";
            
            System.out.println("sql: " + sql);
            
            ConexaoBD.executeUpdate(sql);
            
            return null;
        } catch (SQLException e) {
            System.out.println("Erro inserir vendas = " + e);
            return e.toString();
        }
        
    }
    
    public String atualizar(Venda o) {
        try {
            String sql = ""
                    + "UPDATE venda "
                    + "SET "
                    + "nome = '" + o.getData() + "',"
                    + "email = '" + o.getObservacao() + "',"
                    + "endereco_entrega = '" + o.getEnderecoEntrega() + "',"
                    + "quantidade = " + o.getQuantidade() + " ,"
                    + "valor = " + o.getValor() + " ,"
                    + "usuario_id = " + o.getUsuarioId() + " ,"
                    + "cliente_id = " + o.getClienteId() + " ,"
                    + "forma_pagamento_id = " + o.getFormaPagamentoId() + " "
                    + "WHERE id = " + o.getId();
            
            System.out.println("sql: " + sql);
            
            ConexaoBD.executeUpdate(sql);
            
            return null;
        } catch (SQLException e) {
            System.out.println("Erro atualizar venda = " + e);
            return e.toString();
        }
    }
    
    @Override
    public String excluir(int id) {
        try {
            String sql = ""
                    + "DELETE FROM venda "
                    + "WHERE id = " + id;
            
            System.out.println("sql: " + sql);
            
            ConexaoBD.executeUpdate(sql);
            
            return null;
        } catch (SQLException e) {
            System.out.println("Erro excluir/inativar venda = " + e);
            return e.toString();
        }
    }
    
    @Override
    public ArrayList<Venda> consultarTodos() {
        ArrayList<Venda> vendas = new ArrayList<>();
        
        try {
            String sql = ""
                    + "SELECT * FROM venda ";
            
            resultadoQ = ConexaoBD.executeQuery(sql);
            
            while (resultadoQ.next()) {
                Venda venda = new Venda();
                
                venda.setId(resultadoQ.getInt("id"));
                venda.setData(resultadoQ.getString("data"));
                venda.setObservacao(resultadoQ.getString("observacao"));
                venda.setEnderecoEntrega(resultadoQ.getString("endereco_entrega"));
                venda.setQuantidade(resultadoQ.getDouble("quantidade"));
                venda.setValor(resultadoQ.getDouble("valor"));
                venda.setUsuarioId(resultadoQ.getInt("usuario_id"));
                venda.setClienteId(resultadoQ.getInt("cliente_id"));
                venda.setFormaPagamentoId(resultadoQ.getInt("forma_pagamento_id"));
                
                vendas.add(venda);
            }
        } catch (SQLException e) {
            System.out.println("Erro consultar todos venda = " + e);
        }
        
        return vendas;
    }
    
    @Override
     public ArrayList<Venda> consultar(String criterio) {
        ArrayList<Venda> vendas = new ArrayList<>();

        try {
            String sql = ""
                    + "SELECT * FROM venda"
                    + "WHERE  "
                    + "descricao ILIKE '%" + criterio + "%' "
                    + "ORDER BY ";

            resultadoQ = ConexaoBD.executeQuery(sql);

            while (resultadoQ.next()) {
                Venda venda = new Venda();

                
                venda.setId(resultadoQ.getInt("id"));
                venda.setData(resultadoQ.getString("data"));
                venda.setObservacao(resultadoQ.getString("observacao"));
                venda.setEnderecoEntrega(resultadoQ.getString("endereco_entrega"));
                venda.setQuantidade(resultadoQ.getDouble("quantidade"));
                venda.setValor(resultadoQ.getDouble("valor"));
                venda.setUsuarioId(resultadoQ.getInt("usuario_id"));
                venda.setClienteId(resultadoQ.getInt("cliente_id"));
                venda.setFormaPagamentoId(resultadoQ.getInt("forma_pagamento_id"));


                vendas.add(venda);
            }
        } catch (SQLException e) {
            System.out.println("Erro consultar todos estados = " + e);
        }

        return vendas;
    }

    @Override
  public Venda consultarId(int id) {
        Venda u = null;

        try {
            String sql = ""
                    + "SELECT * FROM venda "
                    + "WHERE  "
                    + "id = " + id;

            System.out.println("sql: " + sql);

            resultadoQ = ConexaoBD.executeQuery(sql);

            if (resultadoQ.next()) {
                u = new Venda();

                u.setId(id);
                u.setData(resultadoQ.getString("data"));
                u.setObservacao(resultadoQ.getString("observacao"));
                u.setEnderecoEntrega(resultadoQ.getString("endereco_entrega"));
                u.setQuantidade(resultadoQ.getDouble("quantidade"));
                u.setValor(resultadoQ.getDouble("valor"));
                u.setUsuarioId(resultadoQ.getInt("usuario_id"));
                u.setClienteId(resultadoQ.getInt("cliente_id"));
                u.setFormaPagamentoId(resultadoQ.getInt("forma_pagamento_id"));
            }

        } catch (SQLException e) {
            System.out.println("Erro consultar venda = " + e);
        }
        return u;
    }
  
public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;
        int colunas = 9;

        // cabecalho da tabela
        Object[] cabecalho = new Object[colunas];
        cabecalho[0] = "id";
        cabecalho[1] = "data";
        cabecalho[2] = "observacao";
        cabecalho[3] = "endereco_entrega";
        cabecalho[4] = "quantidade";
        cabecalho[5] = "valor";
        cabecalho[6] = "usuario_id";
        cabecalho[7] = "cliente_id";
        cabecalho[8] = "forma_pagamento_id";

        // cria matriz de acordo com nº de registros da tabela
        try {
            String sql = ""
                    + "SELECT count(*) FROM venda "
                    + "WHERE  "
                    + "data ILIKE '%" + criterio + "%'";

            resultadoQ = ConexaoBD.executeQuery(sql);

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][colunas];

        } catch (Exception e) {
            System.out.println("Erro ao consultar venda: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            String sql = ""
                    + "SELECT * FROM venda "
                    + "WHERE  "
                    + "data ILIKE '%" + criterio + "%' "
                    + "ORDER BY data";

            resultadoQ = ConexaoBD.executeQuery(sql);

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("id");
                dadosTabela[lin][1] = resultadoQ.getString("data");
                dadosTabela[lin][2] = resultadoQ.getInt("observacao");
                dadosTabela[lin][3] = resultadoQ.getInt("endereco_entrega");
                dadosTabela[lin][4] = resultadoQ.getInt("quantidade");
                dadosTabela[lin][5] = resultadoQ.getInt("valor");
                dadosTabela[lin][6] = resultadoQ.getInt("usuario_id");
                dadosTabela[lin][7] = resultadoQ.getInt("cliente_id");
                dadosTabela[lin][8] = resultadoQ.getInt("forma_pagamento_id");

                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (SQLException e) {
            System.out.println("problemas para popular tabela...");
            System.err.println(e);
        }

        // configuracoes adicionais no componente tabela
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                return false;
                /*  
                 if (column == 3) {  // apenas a coluna 3 sera editavel
                 return true;
                 } else {
                 return false;
                 }
                 */
            }

            // alteracao no metodo que determina a coluna em que o objeto ImageIcon devera aparecer
            @Override
            public Class getColumnClass(int column) {

                if (column == 2) {
//                    return ImageIcon.class;
                }
                return Object.class;
            }
        });

        // permite seleção de apenas uma linha da tabela
        tabela.setSelectionMode(0);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            column = tabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(17);
                    break;
                case 1:
                    column.setPreferredWidth(140);
                    break;
//                case 2:
//                    column.setPreferredWidth(14);
//                    break;
            }
        }
        // renderizacao das linhas da tabela = mudar a cor
//        jTable1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value,
//                    boolean isSelected, boolean hasFocus, int row, int column) {
//                super.getTableCellRendererComponent(table, value, isSelected,
//                        hasFocus, row, column);
//                if (row % 2 == 0) {
//                    setBackground(Color.GREEN);
//                } else {
//                    setBackground(Color.LIGHT_GRAY);
//                }
//                return this;
//            }
//        });
    }
  
}
