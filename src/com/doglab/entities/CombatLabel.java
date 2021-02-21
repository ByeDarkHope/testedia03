package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.doglab.main.Game;

public class CombatLabel extends Label{

	private int inLocal = 0;
	
	public AddButton addB = new AddButton(0, 0, 0, 0, 0, null, 0, 0, 0, 0) {
			
			public void actionPerformed(){
				for(int i = 0; i < Game.entities.size(); i++) {
					Entity e = Game.entities.get(i);
					if(e instanceof CombatLabel) {
						labels = ((CombatLabel) e).getGunArrayList();
						labelsAmount++;
						GunLabel gunLabel = new GunLabel(labelX, labelY+((this.labelH+5)*labelsAmount), 
								this.labelW, this.labelH, 0, null, this.labelY+this.labelH+5);
						labels.add(gunLabel);
						((CombatLabel) e).setGunArrayList(labels);
						return;
					}
				}
			}
			
	};
	
	public Roller roller;
	private int firstYRoller;
	private ArrayList<GunLabel> gunLabels;
	private int initY;
	
	public int labelX, labelY, labelW, labelH;
	
	public CombatLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		gunLabels = new ArrayList<GunLabel>();
		TextLabel combate = new TextLabel(getX()+270, getY()+30, 75, 19, 0, null, new Font("sitka banner", Font.BOLD, 21), 
				new Color(0xFFE8EDEB), "Combate", 1);
		TextLabel nome = new TextLabel(getX()+60, getY()+65, 30, 11, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Nome", 0);
		TextLabel tipo = new TextLabel(getX()+140, getY()+65, 30, 11, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Tipo", 0);
		TextLabel dano = new TextLabel(getX()+230, getY()+65, 30, 11, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Dano", 0);
		TextLabel munAtual = new TextLabel(getX()+320, getY()+65, 60, 11, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Mun. Atual", 0);
		TextLabel munMaxima = new TextLabel(getX()+390, getY()+65, 53, 11, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Mun. M�x.", 0);
		TextLabel ataque = new TextLabel(getX()+455, getY()+65, 37, 11, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Ataque", 0);
		TextLabel alcance = new TextLabel(getX()+505, getY()+65, 40, 11, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Alcance", 0);
		TextLabel defeito = new TextLabel(getX()+560, getY()+65, 40, 11, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Defeito", 0);
		TextLabel area = new TextLabel(getX()+610, getY()+65, 28, 11, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Area", 0);
		
		initY = getY()+50;
		addB.setX(getX()+getWidth()-35);
		addB.setY(getY()+10);
		addB.setWidth(25);
		addB.setHeight(25);
		addB.setSprite(Game.spr_entities.getSprite(76, 206, 25, 25));
		this.labelX = getX()+5;
		this.labelY = initY;
		this.labelW = getWidth()-10;
		this.labelH = 20;
		addB.labelX = this.labelX;
		addB.labelY = this.labelY;
		addB.labelW = this.labelW;
		addB.labelH = this.labelH;
		addB.speed = 0;
		
		roller = new Roller(getX()+getWidth()-5, getY(), 5, 15, 10, null, true, getX()+getWidth()-5, getY(), 5, getHeight());
		firstYRoller = roller.getY();
		labels.add(roller);
		labels.add(combate);
		labels.add(addB);
		labels.add(nome);
		labels.add(tipo);
		labels.add(dano);
		labels.add(munAtual);
		labels.add(munMaxima);
		labels.add(ataque);
		labels.add(alcance);
		labels.add(defeito);
		labels.add(area);
		
	}

	public void tick() {
		super.tick();
		if(tick) {
			if(current) {
				inLocal = this.size;
			}else {
				inLocal = 0;
			}

			for(int i = 0; i < gunLabels.size(); i++) {
				GunLabel l = gunLabels.get(i);
				if(l.getY()+l.getHeight() < getY()+getHeight() && l.getY()+l.inLocal+inLocal>getY()+74+inLocal*2) {
					if(i>0) {
						GunLabel l2 = gunLabels.get(i-1);
						if(l2.inLocal == 0) {
							l.tick();
						}
					}else {
						l.tick();
					}
				}
			}
			
			for(int i = 0; i < gunLabels.size(); i++) {
				GunLabel l = gunLabels.get(i);
				int times = i+1;
				int height = l.getHeight()+5-l.inLocal*2;
				int calc = initY-l.inLocal+height*times;
				l.setY((calc) + ((firstYRoller - roller.getY())*roller.step));
			}
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(new Color(0xFF424242));
		g.drawLine(getX()+inLocal+15, getY()+inLocal-Game.roller.getY()*Game.roller.step+50, getX()+getWidth()-inLocal-15, getY()+inLocal+50-Game.roller.getY()*Game.roller.step);
		g.drawLine(getX()+inLocal+15, getY()+inLocal-Game.roller.getY()*Game.roller.step+75, getX()+getWidth()-inLocal-15, getY()+inLocal+75-Game.roller.getY()*Game.roller.step);
		for(int i = 0; i < gunLabels.size(); i++) {
			GunLabel l = gunLabels.get(i);
			if(l.getY()+l.getHeight() < getY()+getHeight() && l.getY()+l.inLocal+inLocal>getY()+74+inLocal*2) {
				if(i>0) {
					GunLabel l2 = gunLabels.get(i-1);
					if(l2.inLocal == 0) {
						l.render(g);
					}
				}else {
					l.render(g);
				}
			}
		}
	}

	public ArrayList<GunLabel> getGunArrayList() {
		return gunLabels;
	}
	
	public void setGunArrayList(ArrayList<GunLabel> gunLabels) {
		this.gunLabels = gunLabels;
	}
	
}
