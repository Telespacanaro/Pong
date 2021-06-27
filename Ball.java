import java.awt.*;
import java.lang.Math;

/**
	Esta classe representa a bola usada no jogo. A classe princial do jogo (Pong)
	instancia um objeto deste tipo quando a execução é iniciada.
*/

public class Ball {

	/**
		Construtor da classe Ball. Observe que quem invoca o construtor desta classe define a velocidade da bola 
		(em pixels por millisegundo), mas não define a direção deste movimento. A direção do movimento é determinada 
		aleatóriamente pelo construtor.

		@param cx coordenada x da posição inicial da bola (centro do retangulo que a representa).
		@param cy coordenada y da posição inicial da bola (centro do retangulo que a representa).
		@param width largura do retangulo que representa a bola.
		@param height altura do retangulo que representa a bola.
		@param color cor da bola.
		@param speed velocidade da bola (em pixels por millisegundo).
	*/
	private double cx, cy, width, height, speed, vx, vy, angulo;
	private Color cor;
	public Ball(double cx, double cy, double width, double height, Color color, double speed){
		this.cx = cx;
		this.cy = cy;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.cor = color;
		int aleatorio = 1 + (int) (Math.random()*3); /** gera um inteiro aleatorio entre 1 e 4 */
		if(aleatorio==1) angulo = 0.52359877559;	/** A partir do numero aleatorio*/	
		if(aleatorio==2) angulo = 2.61799387799;	/** gera o angulo(30,150,210,330 graus) de saida da bola.*/
		if(aleatorio==3) angulo = 3.66519142919;	/** Os angulos foram escolhidos em funcao da*/
		if(aleatorio==4) angulo = 5.75958653158;	/** jogabilidade*/
		this.vx = speed*Math.cos(angulo);
		this.vy = speed*Math.sin(angulo);
	}


	/**
		Método chamado sempre que a bola precisa ser (re)desenhada.
	*/

	public void draw(){

		GameLib.setColor(this.cor);
		GameLib.fillRect(this.cx, this.cy, this.width, this.height);
	}

	/**
		Método chamado quando o estado (posição) da bola precisa ser atualizado.
		
		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/

	public void update(long delta){
		this.cx += this.vx*delta;
		this.cy += this.vy*delta;
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com um jogador.
	
		@param playerId uma string cujo conteúdo identifica um dos jogadores.
	*/

	public void onPlayerCollision(String playerId){
		this.vx = -(this.vx);
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com uma parede.

		@param wallId uma string cujo conteúdo identifica uma das paredes da quadra.
	*/

	public void onWallCollision(String wallId){
		if(wallId=="Bottom"){
			this.vy = -(this.vy);
		}
		if(wallId=="Top"){
			this.vy = -(this.vy);
		}
		if(wallId == "Left"){
			this.vx = -(this.vx);
			
		}
		if(wallId == "Right"){
			this.vx = -(this.vx);
			
		}
		
	}

	/**
		Método que verifica se houve colisão da bola com uma parede.

		@param wall referência para uma instância de Wall contra a qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/
	
	public boolean checkCollision(Wall wall){

		if(wall.getId()=="Bottom"){
			if(wall.getCy()-this.cy<=((this.height/2)+(wall.getHeight()/2))) return true;/**checa se houve colisao no eixo X */
		}
		if(wall.getId()=="Top"){
			if(this.cy-wall.getCy()<=((this.height/2)+(wall.getHeight()/2))) return true;
		}
		if(wall.getId()=="Left"){
			if(this.cx-wall.getCx()<=((this.width/2)+(wall.getWidth()/2))) return true;
		}
		if(wall.getId()=="Right"){
			if(wall.getCx()-this.cx<=((this.width/2)+(wall.getWidth()/2))) return true;
		}
		return false;
	}

	/**
		Método que verifica se houve colisão da bola com um jogador.

		@param player referência para uma instância de Player contra o qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/	

	public boolean checkCollision(Player player){
		
		if(this.cx<=(player.getCx()+((player.getWidth())/2)) && this.cx>=(player.getCx()-((player.getWidth())/2))){
			if(this.cy<=(player.getCy()+((player.getHeight())/2)) && this.cy>=(player.getCy()-((player.getHeight())/2))) return true;
		}
		return false;
	}

	/**
		Método que devolve a coordenada x do centro do retângulo que representa a bola.
		@return o valor double da coordenada x.
	*/
	
	public double getCx(){

		return this.cx;
	}

	/**
		Método que devolve a coordenada y do centro do retângulo que representa a bola.
		@return o valor double da coordenada y.
	*/

	public double getCy(){

		return this.cy;
	}

	/**
		Método que devolve a velocidade da bola.
		@return o valor double da velocidade.

	*/

	public double getSpeed(){

		return this.speed;
	}

}
