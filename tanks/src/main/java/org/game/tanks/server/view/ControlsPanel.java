package org.game.tanks.server.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.game.tanks.server.core.ServerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

@Component
public class ControlsPanel extends JPanel {

  @Autowired
  private ServerController serverController;
  private JTextField txtServerName;
  private JTextField txtAddress;
  private JTextField txtTcpPort;
  private JTextField txtUdpPort;

  public ControlsPanel() {
    setLayout(new FormLayout(
        new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("80px"), FormSpecs.RELATED_GAP_COLSPEC,
            ColumnSpec.decode("100px"), },
        new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
            FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
            FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
            RowSpec.decode("20px"), }));

    JLabel lblServerName = new JLabel("Server Name");
    add(lblServerName, "2, 2, right, default");

    txtServerName = new JTextField();
    add(txtServerName, "4, 2, fill, default");
    txtServerName.setColumns(10);

    JLabel lblAddress = new JLabel("IP address");
    add(lblAddress, "2, 4, right, default");

    txtAddress = new JTextField();
    add(txtAddress, "4, 4, fill, default");
    txtAddress.setColumns(10);

    JLabel lblTcpPort = new JLabel("TCP port");
    add(lblTcpPort, "2, 6, right, default");

    txtTcpPort = new JTextField();
    add(txtTcpPort, "4, 6, fill, default");
    txtTcpPort.setColumns(10);

    JLabel lblUdpPort = new JLabel("UDP port");
    add(lblUdpPort, "2, 8, right, default");

    txtUdpPort = new JTextField();
    add(txtUdpPort, "4, 8, fill, default");
    txtUdpPort.setColumns(10);

    JButton btnStart = new JButton("Start");
    btnStart.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        serverController.startServer(txtServerName.getText(), txtAddress.getText(), txtTcpPort.getText(), txtUdpPort.getText());
      }
    });
    add(btnStart, "2, 10");

    JButton btnStop = new JButton("Stop");
    btnStop.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        serverController.stopServer();
      }
    });
    add(btnStop, "4, 10");

    JLabel lblStatus = new JLabel("Status: stopped");
    lblStatus.setHorizontalAlignment(SwingConstants.LEFT);
    lblStatus.setVerticalAlignment(SwingConstants.TOP);
    add(lblStatus, "2, 12, 3, 1");
  }

}
