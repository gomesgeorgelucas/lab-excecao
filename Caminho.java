package br.edu.ufam.icomp.lab_excecoes;

import java.util.Arrays;

public class Caminho {
	private Coordenada[] caminho;
	private int tamanho;

	public Caminho(int maxTam) {
		this.caminho = new Coordenada[maxTam];
		this.tamanho = 0;
	}

	public int tamanho() {
		return (int) Arrays.asList(this.caminho).stream().filter(n -> n != null).count();
	}

	public void addCoordenada(Coordenada coordenada)
			throws TamanhoMaximoExcedidoException, DistanciaEntrePontosExcedidaException {

		if (this.tamanho() > 0) {
			if (this.tamanho == this.caminho.length) {
				throw new TamanhoMaximoExcedidoException();
			}

			if (this.caminho[this.tamanho() - 1].distancia(coordenada) > 15) {
				throw new DistanciaEntrePontosExcedidaException();
			}
		}

		this.caminho[this.tamanho()] = coordenada;
		this.tamanho++;
	}

	public void reset() {
		this.caminho = null;
		this.tamanho = 0;
	}

	@Override
	public String toString() {
		return String.format("Dados do caminho:\n" + "  - Quantidade de pontos: %d\n" + "  - Pontos:\n" + "    %s",
				this.tamanho, Arrays.asList(this.caminho).stream().filter(n -> n != null).map(m -> m.toString())
						.reduce("", (acc, e) -> acc + "-> " + e));
	}

}
