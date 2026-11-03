import java.util.ArrayList;
import java.util.Scanner;

class Produto {
    private String nome;
    private double preco;
    private int quantidade;

    public Produto(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void atualizar(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public void exibir() {
        System.out.printf("Produto: %s | Preço: R$ %.2f | Quantidade: %d%n",
                nome, preco, quantidade);
    }
}

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Produto> produtos = new ArrayList<>();

    private static void cadastrarProduto() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine().trim();

        double preco = lerDouble("Preço: ");
        int quantidade = lerInteiro("Quantidade: ");

        if (nome.isEmpty() || preco < 0 || quantidade < 0) {
            System.out.println("Dados inválidos. Verifique as informações.");
            return;
        }

        produtos.add(new Produto(nome, preco, quantidade));
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("\n=== PRODUTOS ===");
        for (int i = 0; i < produtos.size(); i++) {
            System.out.print((i + 1) + " - ");
            produtos.get(i).exibir();
        }
    }

    private static void atualizarProduto() {
        listarProdutos();
        if (produtos.isEmpty()) return;

        int indice = lerInteiro("Número do produto que deseja atualizar: ") - 1;

        if (indice < 0 || indice >= produtos.size()) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine().trim();
        double preco = lerDouble("Novo preço: ");
        int quantidade = lerInteiro("Nova quantidade: ");

        if (nome.isEmpty() || preco < 0 || quantidade < 0) {
            System.out.println("Dados inválidos.");
            return;
        }

        produtos.get(indice).atualizar(nome, preco, quantidade);
        System.out.println("Produto atualizado!");
    }

    private static void excluirProduto() {
        listarProdutos();
        if (produtos.isEmpty()) return;

        int indice = lerInteiro("Número do produto que deseja excluir: ") - 1;

        if (indice < 0 || indice >= produtos.size()) {
            System.out.println("Produto não encontrado.");
            return;
        }

        produtos.remove(indice);
        System.out.println("Produto excluído!");
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Double.parseDouble(scanner.nextLine().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor numérico válido.");
            }
        }
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n=== CADASTRO DE PRODUTOS ===");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Atualizar produto");
            System.out.println("4 - Excluir produto");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    atualizarProduto();
                    break;
                case 4:
                    excluirProduto();
                    break;
                case 0:
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
