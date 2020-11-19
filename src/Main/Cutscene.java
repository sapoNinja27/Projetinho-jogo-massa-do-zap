package Main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Entidades.Pupkin_Boss;
import Entidades.light_npc;
import Entidades.roux_npc;
import Entidades.soldier_npc;

public class Cutscene {
	int cont=0;
	boolean button;
	boolean flash;
	float op=0f;
	String dialog =" ",dialog2 =" ",dialog3 =" ";
	int avatarL=0,avatarC=0;
	int pisca=0,maxPisca=20;
	boolean boxright, boxleft;
	BufferedImage icon[][]= new BufferedImage[30][10];
	BufferedImage buttonimg;
	public static int right_dir = 0,left_dir = 1;
	private int frames = 0,maxFrames = 2;
	private static boolean[] passo=new boolean[100];
	private static boolean[] cena = new boolean[100];
	private static boolean cutscene=false;
	private static boolean next;
	private static int opc=0;
	private static int maxOpc=20;
	public static void CenaStart(int id) {
		cena[id]=true;	
		cutscene=true;
		passo[0]=true;
	}
	public void esvaziar() {
		next=false;
		boxright=false;
		avatarC=0;
		boxleft=false;
		frames=0;
		dialog="";
		dialog2="";
		dialog3="";
	}
	public static void next() {
		next=true;
	}
	public static void up() {
		opc--;
		if(opc<0) {
			opc=maxOpc;
		}
	}
	public static void down() {
		opc++;
		if(opc>maxOpc) {
			opc=0;
		}
	}
	public static boolean CcRun() {
		return cutscene;
//		return false;
	}
	public void tick() {
		pisca++;
		if(pisca==maxPisca) {
			if(button) {
				button=false;
			}else {
				button=true;
			}
			
			pisca=0;
		}
		buttonimg=Game.spritesheet.getSprite(Game.TILE_SIZE*5, 18*Game.TILE_SIZE, 10, 10);
		for(int i=0;i<30;i++) {
			for(int j=0;j<10;j++) {
				icon[i][j]=Game.icones.getSprite(Game.TILE_SIZE*(i), Game.TILE_SIZE*j, Game.TILE_SIZE, Game.TILE_SIZE);
			}
		}
		if(cena[0]) {
			if(passo[0]) {
//				Game.player.parado=true;
//				Game.player.camx=350;
//				Game.player.mov_das_cena=-70;
				light_npc.dir=left_dir;
				light_npc.setPos(-100,77);
				light_npc.index=4;
				
				Pupkin_Boss.dir=left_dir;
				Pupkin_Boss.parado=true;
				Pupkin_Boss.setPos(0,0);
				
				roux_npc.dir=right_dir;
				roux_npc.setPos(-170,4);
				roux_npc.parado=true;
				
				soldier_npc.dir=left_dir;
				soldier_npc.setPos(-50,0);
				soldier_npc.parado=true;
				if(next) {
					esvaziar();
					cont=760;
				}
				frames++;
				if(frames==2) {
					cont+=5;
					frames=0;
					Game.player.setX(cont);
					if(cont>=760) {
						cont=760;
						passo[0]=false;
						passo[1]=true;
					}
				}
			}
			if(passo[1]) {
				boxright=true;
				avatarL=16;
				dialog="Boa novato";
				if(next) {
					esvaziar();
					passo[1]=false;
					passo[2]=true;
				}
			}
			if(passo[2]) {
				boxright=true;
				avatarL=16;
				dialog="Agora s� falta a garota";
				if(next) {
					esvaziar();
					passo[2]=false;
					passo[3]=true;
				}
			}
			if(passo[3]) {
				frames++;
				if(frames==8) {
					frames=0;
					if(light_npc.index==4) {
						light_npc.index=0;
					}else if(light_npc.index==0) {
						light_npc.index=2;
					}else if(light_npc.index==2) {
						light_npc.index=0;
					}
				}
				boxright=true;
				avatarL=10;
				avatarC=4;
				dialog="Roux, fuja...";
				if(next) {
					esvaziar();
					passo[3]=false;
					passo[4]=true;
				}
				
			}
			if(passo[4]) {
				frames++;
				if(frames==8) {
					frames=0;
					if(light_npc.index==4) {
						light_npc.index=0;
					}else if(light_npc.index==0) {
						light_npc.index=2;
					}else if(light_npc.index==2) {
						light_npc.index=0;
					}
				}
				boxright=true;
				avatarL=17;
				dialog="Quieto";
				if(next) {
					esvaziar();
					soldier_npc.index=9;
					passo[4]=false;
					passo[5]=true;
				}
			}
			if(passo[5]) {
				soldier_npc.parado=false;
				soldier_npc.setPos(-50-(soldier_npc.index*2),0);
				frames++;
				if(frames==10) {
					frames=0;
					soldier_npc.index++;
					if(soldier_npc.index>=16) {
						soldier_npc.index=17;
						passo[5]=false;
						passo[6]=true;
					}
				}
				if(next) {
					esvaziar();
					soldier_npc.setPos(-80,0);
					soldier_npc.index=16;
				}
			}
			if(passo[6]) {
				soldier_npc.setPos(-80,0);
				frames++;
				if(frames==10) {
					frames=0;
					soldier_npc.index++;
					if(soldier_npc.index>=20) {
						soldier_npc.index=0;
						soldier_npc.parado=true;
						light_npc.index=4;
						passo[6]=false;
						passo[7]=true;
					}
				}
				if(next) {
					esvaziar();
					soldier_npc.index=20;
				}
			}
			if(passo[7]) {
				boxright=true;
				avatarL=3;
				avatarC=2;
				dialog="Light";
				if(next) {
					esvaziar();
					passo[7]=false;
					passo[8]=true;
				}
			}
			if(passo[8]) {
				boxright=true;
				avatarL=3;
				avatarC=3;
				dialog="Eu vou.. Agh";
				if(next) {
					esvaziar();
					soldier_npc.index=4;
					soldier_npc.parado=false;
					passo[8]=false;
					passo[9]=true;
				}
			}
			if(passo[9]) {
				roux_npc.index=10;
				roux_npc.parado=false;
				frames++;
				if(frames==10) {
					frames=0;
					soldier_npc.index++;
					soldier_npc.setPos(-80, 0);
					if(soldier_npc.index>=8) {
						soldier_npc.index=7;
						passo[9]=false;
						passo[10]=true;
					}
				}
				if(next) {
					esvaziar();
					soldier_npc.index=8;
				}
			}
			if(passo[10]) {
				boxright=true;
				avatarL=17;
				dialog="N�o vai";
				if(next) {
					esvaziar();
					passo[10]=false;
					passo[11]=true;
				}
			}
			if(passo[11]) {
				boxright=true;
				avatarL=16;
				dialog="Ela esta paralizada";
				if(next) {
					esvaziar();
					passo[11]=false;
					passo[12]=true;
				}
			}
			if(passo[12]) {
				boxright=true;
				avatarL=16;
				avatarC=1;
				dialog="Mate";
				if(next) {
					esvaziar();
					passo[12]=false;
					passo[13]=true;
				}
			}
			if(passo[13]) {
				flash=true;
				frames++;
				if(frames>=3) {
					op+=0.1f;
					if(op>=1f) {
						Game.player.visivel=true;
						Game.player.depth=5;
						op=0f;
						flash=false;
						passo[13]=false;
						passo[14]=true;
					}
					if(next) {
						esvaziar();
						Game.player.visivel=true;
						Game.player.depth=5;
						op=0f;
						flash=false;
						passo[13]=false;
						passo[14]=true;
					}
				}
				
			}
			if(passo[14]) {
				Game.player.index=28;
				soldier_npc.index=7;
				Game.player.setX(900);
				frames++;
				if(frames>=20) {
					frames=0;
					Game.player.index=29;
					soldier_npc.index=8;
					passo[14]=false;
					passo[15]=true;
				}
				if(next) {
					esvaziar();
					Game.player.index=29;
					soldier_npc.index=8;
					passo[14]=false;
					passo[15]=true;
				}
			}
			if(passo[15]) {
				boxleft=true;
				avatarL=0;
				avatarC=1;
				dialog="Cuidado onde aponta isso ai";
				dialog2="amig�o";
				if(next) {
					esvaziar();
					cont=0;
					roux_npc.index=4;
					roux_npc.dir=left_dir;
					passo[15]=false;
					passo[16]=true;
				}
			}
			if(passo[16]) {
				roux_npc.setPos(-170-cont,4);
				cont+=3;
				frames++;
				if(frames>=6) {
					roux_npc.index++;
					frames=0;
					
					if(cont>=600) {
						cont=0;
						passo[16]=false;
						passo[17]=true;
					}
					if(roux_npc.index>=10) {
						roux_npc.index=4;
					}
				}
				if(next) {
					esvaziar();
					cont=600;
				}
			}
			if(passo[17]) {
				boxright=true;
				avatarL=17;
				avatarC=0;
				dialog="";
				if(next) {
					esvaziar();
					passo[17]=false;
					passo[18]=true;
				}
			}
			if(passo[18]) {
				frames++;
				if(frames>=15) {
					soldier_npc.index--;
					if(soldier_npc.index==7) {
						Game.player.index=28;
					}
					if(next) {
						esvaziar();
						soldier_npc.index=4;					
					}
					if(soldier_npc.index==4) {
						soldier_npc.parado=true;
						Game.player.index=24;
						passo[18]=false;
						passo[19]=true;
					}
				}
			}
			if(passo[19]) {
				Game.player.depth=6;
				frames++;
				if(frames>=14) {
					frames=0;
					Game.player.index++;
					if(Game.player.index==27) {
						soldier_npc.parado=false;
						passo[19]=false;
						passo[20]=true;	
					}
				}
				if(next) {
					esvaziar();
					Game.player.index=27;
				}
			}
			if(passo[20]) {
				boxright=true;
				dialog="Estou pensando";
				if(next) {
					esvaziar();
					passo[20]=false;
					passo[21]=true;
				}
				
			}
			if(passo[21]) {
				boxright=true;
				dialog="Qual era seu plano depois";
				dialog2="que descobrisse que n�o";
				dialog3="pode me machucar?";
				if(next) {
					esvaziar();
					passo[21]=false;
					passo[22]=true;
				}
			}
			if(passo[22]) {
				boxright=true;
				dialog="HAHAHAHAHAHA";
				if(next) {
					esvaziar();
					Game.player.index=25;
					passo[22]=false;
					passo[23]=true;
				}
			}
			if(passo[23]) {
				frames++;
				if(frames>=25) {
					frames=0;
					Game.player.index--;
					if(Game.player.index==24) {
						Game.player.parado=true;
						passo[23]=false;
						passo[24]=true;
					}
				}
				if(next) {
					esvaziar();
					Game.player.parado=true;
					passo[23]=false;
					passo[24]=true;
				}
			}
			if(passo[24]) {
				avatarL=0;
				boxleft=true;
				dialog="Eu..";
				if(next) {
					esvaziar();
					Pupkin_Boss.index=4;
					Pupkin_Boss.parado=false;
					passo[24]=false;
					passo[25]=true;
				}
			}
			if(passo[25]) {
				frames++;
				if(frames>=10) {
					frames=0;
					Pupkin_Boss.index++;
					if(Pupkin_Boss.index==8) {
						cont=0;
						passo[25]=false;
						passo[26]=true;
					}
				}
				if(next) {
					esvaziar();
					Pupkin_Boss.index=8;
				}
			}
			if(passo[26]) {
				frames++;
				if(frames>=6) {
					frames=0;
					cont++;
					Pupkin_Boss.aceso[cont]=true;
					if(cont<4) {
						for(int i=0; i<4;i++) {
							Pupkin_Boss.aceso[i]=false;
						}
						cont=0;
						Game.player.setX(880);
						Game.player.setY(330);
						Game.player.index=32;
						Game.player.parado=false;
						passo[26]=false;
						passo[27]=true;
					}
				}
				if(next) {
					esvaziar();
					Game.player.index=29;
				}
			}
			


		
			
			
			
			
			
			
			
			
			
		}
	}
	
	
	public void render(Graphics g) {
		int offX=300;
		int offY=280;
		int offX2=608;
		int offY2=280;
		g.setFont(new Font("arial", Font.BOLD, 13));
		Graphics2D g2 = (Graphics2D) g;
		

		if(boxleft) {
			Rectangle borda= new Rectangle(offX-Game.TILE_SIZE*3,offY, Game.TILE_SIZE*3, Game.TILE_SIZE-1);
			g.setColor(Color.white);
			g.fillRect(offX-Game.TILE_SIZE*4,offY, Game.TILE_SIZE, Game.TILE_SIZE);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			g.fillRect(offX-Game.TILE_SIZE*3,offY, Game.TILE_SIZE*3, Game.TILE_SIZE-1);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
			g.setColor(Color.black);
			g2.draw(borda);
		}
		if(boxright) {
			Rectangle borda2= new Rectangle(offX2-Game.TILE_SIZE*3,offY2, Game.TILE_SIZE*3, Game.TILE_SIZE-1);
			g.setColor(Color.white);
			g.fillRect(offX2,offY2, Game.TILE_SIZE, Game.TILE_SIZE);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			g.fillRect(offX2-Game.TILE_SIZE*3,offY2, Game.TILE_SIZE*3, Game.TILE_SIZE-1);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
			g.setColor(Color.black);
			g2.draw(borda2);
		}
		if(boxleft) {
			g.drawString(dialog,offX-15-Game.TILE_SIZE*3+20,offY+20);
			g.drawString(dialog2,offX-15-Game.TILE_SIZE*3+20,offY+40);
			g.drawString(dialog3,offX-15-Game.TILE_SIZE*3+20,offY+60);
			g.drawImage(icon[avatarL][avatarC],offX-Game.TILE_SIZE*4,offY,(int)(Game.TILE_SIZE),Game.TILE_SIZE, null);
			if(button) {
				g.drawImage(buttonimg,offX-20,offY+45,10,10, null);
			}
		}
		if(boxright) {
			g.drawString(dialog,offX2-15-Game.TILE_SIZE*3+20,offY2+20);
			g.drawString(dialog2,offX2-15-Game.TILE_SIZE*3+20,offY2+40);
			g.drawString(dialog3,offX2-15-Game.TILE_SIZE*3+20,offY2+60);
			g.drawImage(icon[avatarL][avatarC],offX2,offY2,(int)(Game.TILE_SIZE),Game.TILE_SIZE, null);
			if(button) {
				g.drawImage(buttonimg,offX2-20,offY2+45,10,10, null);
			}
		}
		
		if(flash) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
			g.setColor(Color.white);
			g.fillRect(0, 0, 3000, 30000);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
		
		
		
	}
}