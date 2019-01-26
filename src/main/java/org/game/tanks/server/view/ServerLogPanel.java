package org.game.tanks.server.view;


import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

import org.springframework.stereotype.Component;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@Component
public class ServerLogPanel extends JPanel {

  private JTextArea textArea;

  public ServerLogPanel() {
    setBackground(UIManager.getColor("Button.background"));
    setLayout(new FormLayout(
        new ColumnSpec[] { FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
            FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
            FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
        new RowSpec[] { FormFactory.DEFAULT_ROWSPEC, RowSpec.decode("default:grow"), FormFactory.RELATED_GAP_ROWSPEC,
            FormFactory.DEFAULT_ROWSPEC, }));

    JButton btnCopy = new JButton("Copy");
    btnCopy.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        StringSelection strSel = new StringSelection(textArea.getText());
        clipboard.setContents(strSel, null);
      }
    });

    JScrollPane scrollPane = new JScrollPane();
    add(scrollPane, "1, 2, 9, 1, fill, fill");

    textArea = new JTextArea();
    ((DefaultCaret) textArea.getCaret()).setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
    scrollPane.setViewportView(textArea);

    JLabel lblServerLog = new JLabel("Server Log");
    add(lblServerLog, "1, 1");
    btnCopy.setBackground(UIManager.getColor("Button.background"));
    add(btnCopy, "1, 4");

    final JToggleButton tglbtn = new JToggleButton("Lock");
    tglbtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (tglbtn.isSelected()) {
          ((DefaultCaret) textArea.getCaret()).setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        } else {
          ((DefaultCaret) textArea.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        }

      }
    });
    tglbtn.setBackground(UIManager.getColor("Button.background"));
    add(tglbtn, "3, 4");

    JButton btnClear = new JButton("Clear");
    btnClear.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        textArea.setText("");
      }
    });
    btnClear.setBackground(UIManager.getColor("Button.background"));
    add(btnClear, "7, 4");


  }

  public void setText(String text) {
    textArea.setText(text);
  }

  public void appendText(String text) {
    textArea.append(text);
  }

}
