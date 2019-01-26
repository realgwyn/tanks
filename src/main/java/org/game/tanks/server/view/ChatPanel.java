package org.game.tanks.server.view;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

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
  private JTextArea textArea;
  private SimpleDateFormat dateFormat;

  public ChatPanel() {
    setLayout(new FormLayout(
        new ColumnSpec[] { FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
        new RowSpec[] { RowSpec.decode("default:grow"), FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

    JScrollPane scrollPane = new JScrollPane();
    add(scrollPane, "1, 1, 3, 1, fill, fill");

    textArea = new JTextArea();
    textArea.setFont(new Font("Courier", Font.PLAIN, 10));
    scrollPane.setViewportView(textArea);

    dateFormat = new SimpleDateFormat("hh:mm:ss");

    JButton btnSend = new JButton("Send");
    btnSend.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (!textField.getText().trim().isEmpty()) {
          sendChatMessage(textField.getText());
          textArea.append(dateFormat.format(new Date()) + " > " + textField.getText() + "\n");
          textField.setText("");
        }
      }
    });
    add(btnSend, "1, 3");

    textField = new JTextField();
    textField.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          if (!textField.getText().trim().isEmpty()) {
            sendChatMessage(textField.getText());
            textArea.append(dateFormat.format(new Date()) + " > " + textField.getText() + "\n");
            textField.setText("");
          }
        }
      }
    });
    add(textField, "3, 3, fill, default");
    textField.setColumns(10);
  }

  private void sendChatMessage(String message) {
    serverController.sendChatMessage(message);
  }

}
