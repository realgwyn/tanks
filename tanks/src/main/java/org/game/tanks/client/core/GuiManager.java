package org.game.tanks.client.core;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.game.tanks.client.gui.widgets.ChatWindow;
import org.game.tanks.client.gui.widgets.ConsoleWindow;
import org.game.tanks.client.gui.widgets.Focusable;
import org.game.tanks.client.gui.widgets.GuiComponent;
import org.game.tanks.client.gui.widgets.HudWindow;
import org.game.tanks.client.gui.widgets.InGameChatWindow;
import org.game.tanks.client.gui.widgets.InGameConsoleWindow;
import org.game.tanks.client.gui.widgets.InGameMenuWindow;
import org.game.tanks.client.gui.widgets.PlayerStatsWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class GuiManager implements Focusable {

  private List<GuiComponent> components;

  @Autowired
  GameDisplay display;
  @Autowired
  PlayerInput playerInput;
  @Autowired
  ApplicationContext ctx;
  @Autowired
  GameEngine engine;

  @Autowired
  ChatWindow chatWindow;
  @Autowired
  ConsoleWindow consoleWindow;
  @Autowired
  HudWindow hudWindow;
  @Autowired
  InGameChatWindow inGameChatWindow;
  @Autowired
  InGameConsoleWindow inGameConsoleWindow;
  @Autowired
  InGameMenuWindow inGameMenuWindow;
  @Autowired
  PlayerStatsWindow playerStatsWindow;

  GuiComponent focusedGuiComponent = null;

  @PostConstruct
  public void init() {
    playerInput.setInputListener(this);
    components = new ArrayList<>();
    components.add(chatWindow);
    components.add(consoleWindow);
    components.add(inGameChatWindow);
    components.add(inGameConsoleWindow);
    components.add(inGameMenuWindow);
    components.add(playerStatsWindow);
  }

  public void draw() {
    for (GuiComponent component : components) {
      if (component.isVisible()) {
        component.draw(display.getGraphics());
      }
    }
    // Draw focused Component in front of all other components
    if (focusedGuiComponent != null) {
      focusedGuiComponent.draw(display.getGraphics());
    }
  }

  public void update() {
    // XXX: Update this every frame
    // Hud should load player positions on the map, ammo, hp, fps, money etc

    // XXX: update this every 250ms or more
    // Chat window should load new chat messages
    // Console window should load server messages
    // TeamStats windows should be updated with new data, other player pings, kills, deaths, team etc
  }

  public void registerGuiComponent(GuiComponent component) {
    components.add(component);
  }

  public void showComponent(GuiComponent component) {
    component.setVisible(true);
  }

  public void hideComponent(GuiComponent component) {
    component.setVisible(false);
    if (focusedGuiComponent.getId().equals(component.getId())) {
      focusedGuiComponent = null;
    }
  }

  public void hideAllComponents() {
    for (GuiComponent comp : components) {
      comp.setVisible(true);
    }
    focusedGuiComponent = null;
  }

  private GuiComponent requestFocusAt(int x, int y) {
    if (focusedGuiComponent != null) {
      if (focusedGuiComponent.contains(x, y)) {
        return focusedGuiComponent;
      } else {
        focusedGuiComponent.onFocusLost();
      }
    }
    // Iterate from the end because this is the order of rendering from back to front - focus on component most in front
    for (int i = components.size() - 1; i >= 0; i--) {
      GuiComponent comp = components.get(i);
      if (comp.isVisible() && comp.contains(x, y)) {
        focusedGuiComponent = comp;
        focusedGuiComponent.onFocus();
        return focusedGuiComponent;
      }
    }
    focusedGuiComponent = null;
    return focusedGuiComponent;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (focusedGuiComponent != null) {
      focusedGuiComponent.keyPressed(e);
    } else {
      engine.getCurrentState().keyPressed(e);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (focusedGuiComponent != null) {
      focusedGuiComponent.keyReleased(e);
    } else {
      engine.getCurrentState().keyReleased(e);
    }
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    if (focusedGuiComponent != null) {
      focusedGuiComponent.mouseMoved(e);
    } else {
      engine.getCurrentState().mouseMoved(e);
    }
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    if (focusedGuiComponent != null) {
      focusedGuiComponent.mouseDragged(e);
    } else {
      engine.getCurrentState().mouseDragged(e);
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    focusedGuiComponent = requestFocusAt(e.getX(), e.getY());
    if (focusedGuiComponent != null) {
      focusedGuiComponent.mouseReleased(e);
    } else {
      engine.getCurrentState().mouseReleased(e);
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (focusedGuiComponent != null) {
      focusedGuiComponent.mouseReleased(e);
    } else {
      engine.getCurrentState().mouseReleased(e);
    }
  }

  @Override
  public void onFocusLost() {
    // TODO Auto-generated method stub
  }

  @Override
  public void onFocus() {
    // TODO Auto-generated method stub
  }

  public void showMainMenuComponents() {
    hideAllComponents();

  }

  public void showInGameComponents() {
    hideAllComponents();

  }

}
