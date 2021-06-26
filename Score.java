import java.awt.*;

/**
	Esta classe representa um placar no jogo. A classe princial do jogo (Pong)
	instancia dois objeto deste tipo, cada um responsável por gerenciar a pontuação
	de um player, quando a execução é iniciada.
*/

public class Score {

	/**
		Construtor da classe Score.

		@param playerId uma string que identifica o player ao qual este placar está associado.
	*/
	private int pontuacao;
	private String placar,Id;
	public Score(String playerId){
		this.pontuacao = 0;
		this.Id = playerId;
		this.placar =this.Id+" - "+Integer.toString(this.pontuacao);
		
	}

	/**
		Método de desenho do placar.
	*/

	public void draw(){
		if(this.Id=="Player 1")GameLib.drawText(this.placar, 70, GameLib.ALIGN_LEFT);
		if(this.Id=="Player 2")GameLib.drawText(this.placar, 70, GameLib.ALIGN_RIGHT);	
	}

	/**
		Método que incrementa em 1 unidade a contagem de pontos.
	*/

	public void inc(){
		this.pontuacao += 1;
	}

	/**
		Método que devolve a contagem de pontos mantida pelo placar.

		@return o valor inteiro referente ao total de pontos.
	*/

	public int getScore(){
		
		return this.pontuacao;
	}
}
