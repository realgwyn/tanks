package org.game.tanks.server.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import org.game.tanks.server.core.ServerController;
import org.game.tanks.server.model.PlayerServerModel;
import org.game.tanks.server.view.component.PlayerListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@Component
public class PlayersListPanel extends JPanel {

  @Autowired
  ServerController controller;

  private JPanel playersListPanel;
  private List<PlayerListItem> playerListItems;

  public PlayersListPanel() {
    playerListItems = new ArrayList<>();
    setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec.decode("default:grow"), },
        new RowSpec[] { FormFactory.DEFAULT_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, RowSpec.decode("default:grow"), }));

    JPanel panel = new JPanel();
    panel.setBackground(Color.WHITE);
    add(panel, "1, 1, fill, fill");
    panel
        .setLayout(new FormLayout(
            new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("40px"), FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("160px"), FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("80px"),
                FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("40px"), FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("40px"), FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
            new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

    JLabel lblRank = new JLabel("Rank");
    panel.add(lblRank, "2, 2");

    JLabel lblName = new JLabel("Player Name");
    panel.add(lblName, "4, 2");

    JLabel lblId = new JLabel("Player ID");
    panel.add(lblId, "6, 2");

    JLabel lblKills = new JLabel("Kills");
    panel.add(lblKills, "8, 2");

    JLabel lblDeaths = new JLabel("Deaths");
    panel.add(lblDeaths, "10, 2");

    JLabel lblActions = new JLabel("Actions");
    panel.add(lblActions, "12, 2");

    JSeparator separator = new JSeparator();
    add(separator, "1, 2");

    JScrollPane scrollPane = new JScrollPane();
    add(scrollPane, "1, 3, fill, fill");
    playersListPanel = new JPanel();
    playersListPanel.setBackground(Color.WHITE);
    scrollPane.setViewportView(playersListPanel);
    playersListPanel.setLayout(new FormLayout(new ColumnSpec[] {}, new RowSpec[] {}));

    refreshLayout();
  }

  private void refreshLayout() {
    playersListPanel.removeAll();
    FormLayout layout = new FormLayout(new ColumnSpec[] {}, new RowSpec[] {});
    playersListPanel.setLayout(layout);
    for (PlayerListItem item : playerListItems) {
      layout.appendRow(FormFactory.DEFAULT_ROWSPEC);
      playersListPanel.add(item, "2, " + layout.getRowCount());
      layout.appendRow(FormFactory.RELATED_GAP_ROWSPEC);
    }
    revalidate();
    repaint();
  }

  public void removePlayer(PlayerServerModel model) {
    // playerListItems.removeIf((PlayerListItem o) -> o.getModel().getPlayerId() == model.getPlayerId());
    refreshLayout();
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public void setPlayers(List<PlayerServerModel> players) {
    for (PlayerServerModel player : players) {
      addPlayer(player);
    }
  }

  public void addPlayer(PlayerServerModel model) {
    PlayerListItem playerListItem = new PlayerListItem(model, controller);
    playerListItems.add(playerListItem);
    refreshLayout();
  }

  public void refresh() {
    for (PlayerListItem playerListItem : playerListItems) {
      playerListItem.refresh();
    }
  }

}
