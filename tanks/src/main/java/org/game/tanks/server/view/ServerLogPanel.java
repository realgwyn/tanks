package org.game.tanks.server.view;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;

import org.springframework.stereotype.Component;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

@Component
public class ServerLogPanel extends JPanel {

  private JTextArea textArea;

  public ServerLogPanel() {
    setBackground(UIManager.getColor("Button.background"));
    setLayout(new FormLayout(
        new ColumnSpec[] { FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
            FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
            FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
        new RowSpec[] { FormSpecs.DEFAULT_ROWSPEC, RowSpec.decode("default:grow"), }));

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

    JLabel lblServerLog = new JLabel("Server Log");
    add(lblServerLog, "1, 1");
    btnCopy.setBackground(UIManager.getColor("Button.background"));
    add(btnCopy, "3, 1");

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
    add(tglbtn, "5, 1");

    JButton btnClear = new JButton("Clear");
    btnClear.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        textArea.setText("");
      }
    });
    btnClear.setBackground(UIManager.getColor("Button.background"));
    add(btnClear, "7, 1");

    JScrollPane scrollPane = new JScrollPane();
    add(scrollPane, "1, 2, 9, 1, fill, fill");

    textArea = new JTextArea();
    ((DefaultCaret) textArea.getCaret()).setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
    scrollPane.setViewportView(textArea);
  }

  public void setText(String text) {
    textArea.setText(text);
  }

  public void appendText(String text) {
    textArea.append(text);
  }

}
