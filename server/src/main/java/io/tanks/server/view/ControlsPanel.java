package io.tanks.server.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.Beans;

import javax.annotation.PostConstruct;
import javax.swing.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import io.tanks.server.cfg.GameplayConfig;
import io.tanks.server.core.ServerController;

@Component
public class ControlsPanel extends JPanel {

  @Autowired
  private ServerController serverController;
  @Autowired
  private GameplayConfig gameplayConfig;
  
  private JTextField txtServerName;
  private JTextField txtAddress;
  private JTextField txtTcpPort;
  private JTextField txtUdpPort;
  private JLabel lblStatus;
  private String[] mapNames;

  public ControlsPanel() {
    if (Beans.isDesignTime()) {
      initComponents();
    }
  }

  @PostConstruct
  public void init() {
    initComponents();
  }

  private void initComponents() {
    setLayout(new FormLayout(
        new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("80px"), FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode("100px"), FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("110px"), },
        new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
            FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
            FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
            FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("20px"), }));

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
        int tcp;
        int udp;
        try {
          tcp = Integer.parseInt(txtTcpPort.getText());
          udp = Integer.parseInt(txtUdpPort.getText());
        } catch (Exception ex) {
          System.out.println("Invalid port value " + ex.getMessage());
          ex.printStackTrace();
          System.out.println();
          return;
        }

        serverController.startServer(txtServerName.getText(), tcp, udp);
      }
    });

    JButton btnRestartRound = new JButton("Restart Round");
    btnRestartRound.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        serverController.restartRound();
      }
    });
    add(btnRestartRound, "6, 8");
    add(btnStart, "2, 10");

    JButton btnStop = new JButton("Stop");
    btnStop.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        serverController.stopServer();
      }
    });
    add(btnStop, "4, 10");

    JComboBox<String> cbxMapName = new JComboBox<>();
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(gameplayConfig.getMapNames().toArray(new String[0]));
    cbxMapName.setModel(model);
    add(cbxMapName, "6, 2");
    cbxMapName.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        serverController.changeMap((String) cbxMapName.getSelectedItem());
      }
    });

    JButton btnRestartMatch = new JButton("Restart Match");
    btnRestartMatch.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        serverController.restartMatch();
      }
    });
    add(btnRestartMatch, "6, 10");

    lblStatus = new JLabel("Status: stopped");
    lblStatus.setHorizontalAlignment(SwingConstants.LEFT);
    lblStatus.setVerticalAlignment(SwingConstants.TOP);
    add(lblStatus, "2, 12, 3, 1");
  }

  public void setServerName(String string) {
    txtServerName.setText(string);
  }

  public void setNetworkAddress(String networkAddress) {
    txtAddress.setText(networkAddress);
  }

  public void setTcpPort(int port) {
    txtTcpPort.setText(Integer.toString(port));
  }

  public void setUdpPort(int port) {
    txtUdpPort.setText(Integer.toString(port));
  }

  public String getTxtServerName() {
    return txtServerName.getText();
  }

  public String getTxtAddress() {
    return txtAddress.getText();
  }

  public String getTxtTcpPort() {
    return txtTcpPort.getText();
  }

  public String getTxtUdpPort() {
    return txtUdpPort.getText();
  }

  public void setStatus(String string) {
    lblStatus.setText(string);
  }

}
