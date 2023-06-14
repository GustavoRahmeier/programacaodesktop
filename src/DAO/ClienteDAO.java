/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import apoio.ConexaoBD;
import apoio.IDAO_T;
import entidades.Cliente;
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
public class ClienteDAO implements IDAO_T<Cliente> {

    ResultSet resultadoQ = null;

    @Override
    public String salvar(Cliente o) {

        try {
            String sql = ""
                    + "INSERT INTO clientes (nome, email, cpf, telefone, cidade_id) VALUES ("
                    + "'" + o.getNome() + "',"
                    + "'" + o.getEmail() + "',"
                    + "'" + o.getCpf() + "',"
                    + "'" + o.getTelefone() + "',"
                    + " " + o.getCidadeId() + " "
                    + ")";

            System.out.println("sql: " + sql);

            ConexaoBD.executeUpdate(sql);

            return null;
        } catch (SQLException e) {
            System.out.println("Erro inserir clientes = " + e);
            return e.toString();
        }

    }

    @Override
    public String atualizar(Cliente o) {
        try {
            String sql = ""
                    + "UPDATE clientes "
                    + "SET "
                    + "nome = '" + o.getNome() + "',"
                    + "email = '" + o.getEmail() + "',"
                    + "cpf = '" + o.getCpf() + "',"
                    + "telefone = '" + o.getTelefone() + "',"
                    + "cidade_id = " + o.getCidadeId() + " "
                    + "WHERE id = " + o.getId();

            System.out.println("sql: " + sql);

            ConexaoBD.executeUpdate(sql);

            return null;
        } catch (SQLException e) {
            System.out.println("Erro atualizar usuario = " + e);
            return e.toString();
        }
    }

    @Override
    public String excluir(int id) {
        try {
            String sql = ""
                    + "UPDATE clientes "
                    + "SET "
                    + "nome = 'null' "
                    + "WHERE id = " + id;

            System.out.println("sql: " + sql);

            ConexaoBD.executeUpdate(sql);

            return null;
        } catch (SQLException e) {
            System.out.println("Erro excluir/inativar clientes = " + e);
            return e.toString();
        }
    }

    @Override
    public ArrayList<Cliente> consultarTodos() {
        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            String sql = ""
                    + "SELECT * FROM usuarios ";

            resultadoQ = ConexaoBD.executeQuery(sql);

            while (resultadoQ.next()) {
                Cliente cliente = new Cliente();

                cliente.setId(resultadoQ.getInt("id"));
                cliente.setNome(resultadoQ.getString("nome"));
                cliente.setEmail(resultadoQ.getString("email"));
                cliente.setCpf(resultadoQ.getString("cpf"));
                cliente.setEmail(resultadoQ.getString("telefone"));
                cliente.setCidadeId(resultadoQ.getInt("cidade_id"));

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro consultar todos usuarios = " + e);
        }

        return clientes;
    }

    @Override
    public ArrayList<Cliente> consultar(String criterio) {
        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            String sql = ""
                    + "SELECT * FROM clientes "
                    + "WHERE  "
                    + "nome ILIKE '%" + criterio + "%' "
                    + "ORDER BY nome";

            resultadoQ = ConexaoBD.executeQuery(sql);

            while (resultadoQ.next()) {
                Cliente cliente = new Cliente();

                cliente.setId(resultadoQ.getInt("id"));
                cliente.setNome(resultadoQ.getString("nome"));
                cliente.setEmail(resultadoQ.getString("email"));
                cliente.setCpf(resultadoQ.getString("cpf"));
                cliente.setEmail(resultadoQ.getString("telefone"));
                cliente.setCidadeId(resultadoQ.getInt("cidade_id"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro consultar todos clientes = " + e);
        }

        return clientes;
    }

    @Override
    public Cliente consultarId(int id) {
        Cliente u = null;

        try {
            String sql = ""
                    + "SELECT * FROM clientes "
                    + "WHERE  "
                    + "id = " + id;

            System.out.println("sql: " + sql);

            resultadoQ = ConexaoBD.executeQuery(sql);

            if (resultadoQ.next()) {
                u = new Cliente();

                u.setId(id);
                u.setNome(resultadoQ.getString("nome"));
                u.setEmail(resultadoQ.getString("email"));
                u.setCpf(resultadoQ.getString("cpf"));
                u.setEmail(resultadoQ.getString("telefone"));
                u.setCidadeId(resultadoQ.getInt("cidade_id"));

            }

        } catch (SQLException e) {
            System.out.println("Erro consultar clientes = " + e);
        }
        return u;
    }

    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;
        int colunas = 6;

        // cabecalho da tabela
        Object[] cabecalho = new Object[colunas];
        cabecalho[0] = "Código";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Email";
        cabecalho[3] = "Cpf";
        cabecalho[4] = "Telefone";
        cabecalho[5] = "cidade_id";

        // cria matriz de acordo com nº de registros da tabela
        try {
            String sql = ""
                    + "SELECT count(*) FROM clientes "
                    + "WHERE  "
                    + "nome ILIKE '%" + criterio + "%'";

            resultadoQ = ConexaoBD.executeQuery(sql);

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][colunas];

        } catch (Exception e) {
            System.out.println("Erro ao consultar clientes: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            String sql = ""
                    + "SELECT * FROM clientes "
                    + "WHERE  "
                    + "nome ILIKE '%" + criterio + "%' "
                    + "ORDER BY nome";

            resultadoQ = ConexaoBD.executeQuery(sql);

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("id");
                dadosTabela[lin][1] = resultadoQ.getString("nome");
                dadosTabela[lin][2] = resultadoQ.getString("email");
                dadosTabela[lin][3] = resultadoQ.getString("cpf");
                dadosTabela[lin][4] = resultadoQ.getString("telefone");
                dadosTabela[lin][5] = resultadoQ.getString("cidade_id");

                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//            }
                lin++;
            }
        } catch (SQLException e) {
            System.out.println("problemas para popular tabela...");
            System.err.println(e);
        }

     tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
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

    
    
    
    
    
    