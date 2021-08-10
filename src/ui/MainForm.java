package ui;

import business.ContactBusiness;
import entity.ContactEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm extends JFrame {
    private JPanel rootPanel;
    private JButton buttonNewContact;
    private JButton buttonRemove;
    private JTable tableContacts;
    private JLabel labelContactCount;

    private ContactBusiness mContactBusiness;

    public MainForm() {
        setContentPane(rootPanel);
        setSize(500, 250);
        setVisible(true);

        //Faz o panel aparecer no meio da tela
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mContactBusiness = new ContactBusiness();

        setListeners();

        loadContacts();
    }

    private void loadContacts() {
       List<ContactEntity> contactList = mContactBusiness.getList();

       //Criando uma tabela sem dados com a coluna nome e telefone
       String[] columnNames = {"Nome", "Telefone"};
       DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);

       for(ContactEntity i: contactList) {
           Object[] obj = new Object[2];

           obj[0] = i.getName();
           obj[1] = i.getPhone();

           model.addRow(obj);
       }

       tableContacts.clearSelection();
       tableContacts.setModel(model);

       labelContactCount.setText(mContactBusiness.getContactCountDescription());

    }

    private void setListeners() {
        buttonNewContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ContactForm();
                dispose(); //responsável por fechar o formulário
            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
