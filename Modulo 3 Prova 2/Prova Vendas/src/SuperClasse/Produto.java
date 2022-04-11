package SuperClasse;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import Interface.Funcao;

public class Produto implements Funcao {

    Scanner leia = new Scanner(System.in);
    static final String RESET = "\u001B[0m";
    static final String AMARELO = "\u001B[33m";

    private String nome;
    double valor;
    private int qtdEstoque;
    private int codigo;


    
   public List<Produto> livraria = new ArrayList<Produto>();

    public Produto(String nome,  double valor, int codigo, int qtdEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.qtdEstoque = qtdEstoque;
    }

    public Produto() {

    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
            this.nome = nome;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(String valor) {
        try {
            this.valor = Double.valueOf(valor);
        } catch (InputMismatchException e) {
           System.out.println("Não é possível usar letras no valor do livro!");
           System.out.print("\nPressione enter para sair!");
           leia.nextLine();
        }
    }
    public int getQtdEstoque() {
        return qtdEstoque;
    }
    public void setQtdEstoque(String qtdEstoque) {
        try {
            this.qtdEstoque = Integer.valueOf(qtdEstoque);
 
        } catch (InputMismatchException e) {
           System.out.println("Não é possível usar letras para a quantidade do estoque!");
           System.out.print("\nPressione enter para sair!");
           leia.nextLine();
        }
    }
    

    @Override
    public void incluir(Produto L) {
       livraria.add(L);
    }
    
    @Override
    public void buscar(String nome){
    // livraria.stream()
    // .filter(d -> nome.equals(d.getNome()))
    // .forEach(System.out::print);
    //  leia.nextLine();

        // System.out.println("Buscar Livro: ");
        // String buscador = leia.nextLine();
        boolean encontrado = false;
        for (Produto j : livraria) {
            
            if (nome.equals(j.getNome())) { 
            System.out.println("\nLivro encontrado!" );
            System.out.println(j.toString());
                encontrado = true;
                System.out.print("\nPressione enter para voltar ao menu!");
                leia.nextLine();
                break;
            }
        }
        if (encontrado == false) {
            System.out.println("\nLivro não encontrado!");
            System.out.print("\nPressione enter para voltar ao menu!");
            leia.nextLine();
        }

    }

    @Override
    public void salvos(){

    livraria.add(new Produto("Harry Potter", 200, 1, 40));
    livraria.add(new Produto("Senhor dos Anéis", 549, 2, 33));
    livraria.add(new Produto("The Witcher", 234, 3, 0));
    livraria.add(new Produto("a", 600, 4, 100));
    

    }

    @Override
    public void mostrarlista(){
    System.out.println(AMARELO + "---------- BIBLIOTECA---------" + RESET);
    livraria.stream()
    .forEach(System.out::print);

    }


    public void listar() {
        if (livraria.isEmpty()) {
            System.out.println("Lista vazia!");
        } else {
            
        System.out.println(AMARELO + "---------- BIBLIOTCA ---------" + RESET);
        livraria.stream()
        .forEach(System.out::print);
        
        DoubleSummaryStatistics relatorio = livraria.stream()
        .collect(Collectors.summarizingDouble(Produto::getValor));

        System.out.println(AMARELO + "\n------- INFORMAÇÕES ADICIONAIS --------\n\n" + RESET);
        System.out.print("| O maior valor é R$: " + relatorio.getMax() + "\n");
        System.out.print("| O menor valor é R$: " + relatorio.getMin() + "\n");
        System.out.printf("| A media dos valores é R$: %.2f", relatorio.getAverage(), "\n");
        
        }
    }
            // double maximo, minimo, mediapreco, soma = 0;
            // Produto caro = new Produto();
            // Produto barato = new Produto();
            
    //         maximo = livraria.get(0).getValor();
    //         minimo = livraria.get(0).getValor();

    //         for (Produto k : livraria) {
    //             if ( k.getValor() >= maximo) {
    //                     maximo = k.getValor();
    //                     caro = new Produto(k.getNome(), k.getValor(), k.getQtdEstoque(), k.getCodigo());
    //                 }
    //             if ( k.getValor() <= minimo) {
    //                     minimo = k.getValor();
    //                     barato= new Produto(k.getNome(), k.getValor(), k.getQtdEstoque(), k.getCodigo());
    //                 }
    //             soma += k.getValor();
    //             }
    //         mediapreco = soma/livraria.size();
    //         System.out.println("\n------- INFORMAÇÕES ADICIONAIS --------\n");
    //         System.out.println("\nO menor valor é: " + barato.toString());
    //         System.out.println("\nO maior valor é: " + caro.toString());
    //         System.out.printf("\nA média dos preços é R$: %.2f", mediapreco);

    //     leia.nextLine();

    // }

    public int codigo() {
        int codigo = livraria.size() + 1;
        System.out.println("\nCódigo do livro: " + codigo);
        return codigo;
    }

    public void buscarproduto(String nomevendido) {
    }

    public void setQtdEstoque(int i) {
    }

    public void clear() {
        livraria.clear();
    }

    public void remover(String nomedigitado) {
            livraria.removeIf(p -> nomedigitado.equals(p.getNome()));
    }

    // public double valor() {
        
    //    try {
    //     System.out.print("\nValor do livro: ");
    //     double valor = leia.nextDouble();     
    //    } catch (Exception e) {
    //     System.out.println("Não é possível este valor, tente novamente!");
        
    //    }
    //    return valor;
    // }
    @Override
    public String toString() {
        return "\n\nLIVRO:\n| Nome: " + nome + "\n| Valor R$: " + valor + "\n| Em estoque: " + qtdEstoque + "\n| Código: "
                + codigo + "";
    }

}
