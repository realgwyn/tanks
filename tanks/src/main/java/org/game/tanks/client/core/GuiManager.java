package org.game.tanks.client.core;

import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import org.game.tanks.client.gui.widgets.GuiComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuiManager {

  ConcurrentLinkedQueue<GuiComponent> components;
  GuiComponent focusedComponent;

  @Autowired
  GameDisplay display;

  @PostConstruct
  public void init() {
    components = new ConcurrentLinkedQueue<>();
  }

  public void draw() {
    GuiComponent focusedComponent = null;
    for (GuiComponent component : components) {
      if (component.isVisible()) {
        component.draw();
      }
      if (component.isFocused()) {
        focusedComponent = component;
      }
    }
    // Draw focused Component in front of all other components
    if (focusedComponent != null) {
      focusedComponent.draw();
    }
  }

  public void showComponent(GuiComponent component) {
    for (GuiComponent comp : components) {
      comp.setFocused(false);
    }
    component.setVisible(true);
    component.setFocused(true);
    components.add(component);
  }

  public void removeComponent(GuiComponent component) {
    components.remove(component);
  }

  public void removeAllComponents() {
    components.clear();
  }

}
