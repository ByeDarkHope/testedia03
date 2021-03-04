package com.doglab.entities;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ReadmeLabel extends Label{

	public ReadmeLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		Desktop desk = Desktop.getDesktop(); 
	    try {
	    	desk.browse(new URI("https://discord.gg/sajZFsU556"));
	    	desk.browse(new URI("https://twitter.com/jvdutrag"));
	    	desk.browse(new URI("https://twitter.com/Dogoso4"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} 
	}
	
	public void tick() {
		order = 10;
	}
	
	 private void drawString(Graphics g, String text, int x, int y) {
	        for (String line : text.split("\n"))
	            g.drawString(line, x, y += g.getFontMetrics().getHeight());
	 }
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(new Color(0xFFE8EDEB));
		g.setFont(new Font("sitka banner", Font.BOLD, 51));
		g.drawString("LEIA-ME", getX()+200, getY()+65);
		g.drawLine(getX()+15, getY()+70, getX()+getWidth()-15, getY()+70);
		g.setFont(new Font("sitka banner", Font.BOLD, 18));
		String s = "A Dog Lab apenas representa a recria��o em Java da ficha originalmente\n"
				 + "criada pelo @jvdutrag(Twitter) para uso exclusivo do streamer Cellbit\n"
				 + "em sua s�rie original de RPG \"Ordem Paranormal Desconjura��o\", por\n"
				 + "isso antes de mim, todos os respectivos cr�ditos a esse grande\n"
				 + "programador.\n"
				 + "\n"
				 + "ATEN��O: N�O ALTERE EM NENHUMA POSSIBILIDADE SEU ARQUIVO \n"
				 + "\"info.txt\"gerado pelo app, isso pode ocasionar em futuros crash's\n"
				 + "e bugs que s� poderam ser corrigidos ao excluir o seu pr�prio arquivo\n"
				 + "\"info.txt\" al�m de ocasionar na perda de todos os dados alterados em\n"
				 + "sua ficha.\n"
				 + "\n"
				 + "A op��o de ativa��o do \"COD System\" � nada mais nada menos que deixar\n"
				 + "a sua ficha totalmente automatizada para o sistema de RPG de Call Of \n"
				 + "Ctchulu, impossibilitando com que alguns valores sejam alterados, j� que\n"
				 + "os mesmos s�o calculados automaticamente usando o sistema de COD, como a \n"
				 + "vida m�xima, movimento, entre outros.\n"
				 + "\n"
				 + "Aos amigos programadores que quiserem analisar o c�digo para estudo\n"
				 + "ou at� mesmo para modifica��o, sinta-sem livres para visitar meu github\n"
				 + "disponibilizado no meu Twitter(@Dogoso4), essa ficha tem o c�digo aberto\n"
				 + "a todos que se sentirem inspirados a modifica-lo ou analisa-lo e foi criada\n"
				 + "em parceria com o servidor do discord \"Ordem da Realidade\".\n"
				 + "\n";
		drawString(g, s, getX()+30, getY()+80);
		g.setColor(new Color(0xFF424242));
		g.drawString("(clique na tela)", getX()+240, getY()+getHeight()-10);
	}

}
