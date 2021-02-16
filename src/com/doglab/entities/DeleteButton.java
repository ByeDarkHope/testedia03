package com.doglab.entities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.doglab.main.Game;

public class DeleteButton extends Button{
	
	private int minY;
	
	public DeleteButton(double x, double y, int width, int height, double speed, BufferedImage sprite, int minY) {
		super(x, y, width, height, speed, sprite);
		this.minY = minY;
	}
	
	private void labelSorter(ArrayList<Label> list, int beginning) {
		for(int i = beginning; i < list.size(); i++) {
			Label l = list.get(i);
			if(l.getY() > minY) {
				l.setY(l.getY()-l.height-5);
			}
		}
	}
	
	public void actionPerformed() {
		Game.mouseController.resetPosition();
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof CombatLabel) {
				for(int ii = 0; ii < ((CombatLabel) e).addB.labels.size(); ii++) {
					GunLabel l = (GunLabel)((CombatLabel) e).addB.labels.get(ii);
					if(l.deleteB.equals(this)){
						int id = 0;
						id = ((CombatLabel)e).addB.labels.indexOf(l);
						((CombatLabel)e).addB.labels.remove(l);
						((CombatLabel)e).addB.labelsAmount--;
						labelSorter(((CombatLabel)e).addB.labels, id);
					}
				}
				return;
			}
		}
	}
	
}