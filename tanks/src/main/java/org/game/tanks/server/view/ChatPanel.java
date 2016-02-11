package org.game.tanks.server.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.game.tanks.server.core.ServerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@Component
public class ChatPanel extends JPanel {

  @Autowired
  ServerController serverController;

  private JTextField textField;

  public ChatPanel() {
    setLayout(new FormLayout(
        new ColumnSpec[] { FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
        new RowSpec[] { RowSpec.decode("default:grow"), FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

    JScrollPane scrollPane = new JScrollPane();
    add(scrollPane, "1, 1, 3, 1, fill, fill");

    JTextArea textArea = new JTextArea();
    scrollPane.setViewportView(textArea);

    JButton btnSend = new JButton("Send");
    btnSend.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        sendChatMessage(textField.getText());
      }
    });
    add(btnSend, "1, 3");

    textField = new JTextField();
    textField.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        sendChatMessage(textField.getText());
      }
    });
    add(textField, "3, 3, fill, default");
    textField.setColumns(10);
  }

  private void sendChatMessage(String message) {
    serverController.sendChatMessage(message);
  }

}
