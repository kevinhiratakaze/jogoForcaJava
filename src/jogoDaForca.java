import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Random;


public class jogoDaForca{
	private static final String[] PALAVRAS = {"java", "programação", "youtube", "video"};
	private static final int TENTATIVAS = 6;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		
		
		// agora primeiro vamos escolher a palavra aleatória
		String palavra = PALAVRAS[random.nextInt(PALAVRAS.length)];
		
		// nestas variaveis vamos adicionar as letras informadas pelo usuário
		Set<Character> letrasCorretas = new HashSet<>();
		Set<Character> letrasErradas = new HashSet<>();
		
		int tentativasRestantes = TENTATIVAS;
		
		System.out.println("Bem-vindo ao novo Jogo da Forca, desta vez em Java!");
		System.out.println("A palavra escolhida tem " + palavra.length() + " letras.");
		System.out.println("Boa sorte!");
		
		
		while (tentativasRestantes > 0) {
			
			System.out.println("\nPalavra: " + exibirPalavra(palavra, letrasCorretas));
			System.out.println("Letras erradas: " + letrasErradas);
			
			if (tentativasRestantes == 1) {
				System.out.println("Você tem somente mais 1(uma) tentativa.");
			} else {
				System.out.println("Você ainda tem " + tentativasRestantes + " restantes.");
			}
			
			
			//solicita a letra a ser verificada
			System.out.println("Digite uma letra: ");
			String entrada = scanner.next().toLowerCase();
			
			
			// verificar se ele digitou a letra e somente uma
			if (entrada.length() != 1 || !Character.isLetter(entrada.charAt(0))) {
				System.out.println("Por favor digite somente uma letra");
				continue;
			}
			
			char tentativa = entrada.charAt(0);
			
			
			// Verifica se a letra já foi digitada uma vez
			if (letrasCorretas.contains(tentativa) | letrasErradas.contains(tentativa)) {
				System.out.println("Essa letra já selecionada, informe uma nova letra.");
				continue;
			}
			
			
			// Agora vamos verificar se a letra informada está presente na palavra ou não
			if (palavra.contains(String.valueOf(tentativa))) {
				letrasCorretas.add(tentativa);
				System.out.println("Nice! A letra '" + tentativa + "' é uma letra da palavra!");
			} else {
				letrasErradas.add(tentativa);
				tentativasRestantes--;
				System.out.println("Ooooh noooo! A letra '" + tentativa + "' não está presente na palavra!");
			}
			
			// verificar se a palavra foi completamente adivinhada
			if (todasLetrasAdivinhadas(palavra, letrasCorretas)) {
				System.out.println("\nShow de bola! Você acertou a palavra correta, sendo ela: " + palavra);
				break;
			}
		}
		
		
		// Caso não tenha adivinhado a palavra correta
		if (tentativasRestantes == 0) {
			System.out.println("\nQue pena! Você não adivinhou a palavra: " + palavra);
		}
		
		
		scanner.close();
		
	}

	
	
	private static boolean todasLetrasAdivinhadas(String palavra, Set<Character> letrasCorretas) {
		
		for (char letra : palavra.toCharArray()) {
			if (!letrasCorretas.contains(letra)) {
				return false;				
			}
		}
		
		return true;
	}

	
	private static String exibirPalavra(String palavra, Set<Character> letrasCorretas) {
		StringBuilder exibicao = new StringBuilder();
		
		for (char letra : palavra.toCharArray()) {
			if (letrasCorretas.contains(letra)) {
				exibicao.append(letra);
			} else {
				exibicao.append("_");
			}
		}
		return exibicao.toString();
	}
}