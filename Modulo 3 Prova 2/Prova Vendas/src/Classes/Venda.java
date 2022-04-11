package Classes;

import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import SuperClasse.Produto;

public class Venda extends Produto {
    
    private LocalDate dataVenda;
    private String produtoVendido;
    private int qtdVendida;
    private int dia = 1;
    private double valortotal;
    private double valorv = getValor();

    static final String RESET = "\u001B[0m";
    static final String AMARELO = "\u001B[33m";

    Scanner leia = new Scanner(System.in);

    private List <Venda> carrinho = new ArrayList<Venda>();
    DateTimeFormatter dataBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Venda(String produtoVendido, LocalDate dataVenda, int qtdVendida, double valortotal, double valorv ) {
        this.produtoVendido = produtoVendido;
        this.dataVenda = dataVenda;
        this.qtdVendida = qtdVendida;
        this.valortotal = valortotal;
        this.valorv = valorv;

    }

    public Venda() {
    }

    public double getValorv(double valor) {
        return valorv;
    }

    public void setValorv(double valorv) {
        this.valorv = valorv;
    }


    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getProdutoVendido() {
        return produtoVendido;
    }

    public void setProdutoVendido(String produtoVendido) {
        this.produtoVendido = produtoVendido;
    }

    public int getQtdVendida() {
        return qtdVendida;
    }

    public void setQtdVendida(int qtdVendida) {
        this.qtdVendida = qtdVendida;
    }

    public List<Venda> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<Venda> carrinho) {
        this.carrinho = carrinho;
    }
    public void incluirvenda(Venda v) {
        carrinho.add(v);
     }

    public void realizarvenda(Venda venda, Produto livro){
        Produto nomevendido = new Produto();
        Venda v = new Venda();
        Boolean encontrei = false;

        System.out.println(AMARELO +"\n\n--------- REALIZAR VENDA ---------\n\n" + RESET);
        System.out.print("Informe o nome do livro vendido: ");
        nomevendido.setNome(leia.nextLine());
        String livrobuscado = nomevendido.getNome();

        for (Produto i: livro.livraria) {
            if (i.getNome().equals(livrobuscado)) {
                nomevendido = i;
                encontrei = true;
                break;
            }

        }
       if (encontrei == false) {
           System.out.println(AMARELO +"O livro: ''" + livrobuscado + "'' não existe!" + RESET);
           System.out.print("\nPressione enter para voltar ao menu!");
           leia.nextLine();
       } else {
            System.out.print("Informe a quantidade vendida: ");
        try {
            v.setQtdVendida(leia.nextInt());

        } catch (Exception e) {
            System.out.println("Digitalização inválida, tente novamente!");
            System.out.print("\nPressione enter para voltar ao menu!");
            leia.nextLine();
        } 
        analiseEstoque(nomevendido, v.getQtdVendida());
        v.setValortotal(nomevendido.getValor()*v.getQtdVendida());
        
       }
       try {
        if (dia <= 9) {
            v = new Venda(nomevendido.getNome(), LocalDate.parse("2022-03-0" + dia), v.getQtdVendida(), v.getValortotal(), nomevendido.getValor());
            venda.incluirvenda(v);
            dia++;
            System.out.println("Na data " + v.getDataVenda() + " A venda foi realizada com sucesso!");
        }
        if (dia >= 10 && dia <= 30) {
            v = new Venda(nomevendido.getNome(), LocalDate.parse("2022-03-" + dia), v.getQtdVendida(), v.getValortotal(), nomevendido.getValor());
            venda.incluirvenda(v);
            dia+= 1;
            System.out.println("Na data " + String.valueOf(dataBR.format(v.getDataVenda()) + "A venda foi realizada com sucesso!"));

        }else if(dia > 31) {
            System.out.println("Esta data não existe!");
        }
           
       } catch (Exception e) {
           System.out.println("Não é possível reconhecer a data, tente novamente!");
       }
       leia.nextLine();
    }

    public Produto analiseEstoque(Produto livro, int qtdVendida){
        
        if (livro.getQtdEstoque() < qtdVendida || livro.getQtdEstoque() <= 0){
            System.out.println("Não é possível realizar a venda!");
        }else{
            int quantidade = livro.getQtdEstoque();
           livro.setQtdEstoque(String.valueOf(quantidade - qtdVendida));
        }
        return livro;

    }
    
    public void obterdados(){
        // carrinho.add(new Venda("produtoVendido", LocalDate.parse("2022-03-01"), 7));
        // carrinho.add(new Venda("produtoVendido2", LocalDate.parse("2022-03-02"), 5));
        // carrinho.add(new Venda("produtoVendido3", LocalDate.parse("2022-03-03"), 2));
        // carrinho.add(new Venda("produtoVendido4", LocalDate.parse("2022-03-04"), 8));
        // carrinho.add(new Venda("produtoVendido5", LocalDate.parse("2022-03-05"), 1));
    }
    // public void mostrarvendas(){
       
    //     if (carrinho.isEmpty()) {
    //         System.out.println("Não há um relátório de vendas!");
    //     } else {
            
    //     System.out.println(AMARELO + "---------- RELATÒRIO DE VENDAS ---------" + RESET);
            
    //     }
    // }

    public void relatorio(LocalDate inicio, LocalDate fim){
        try {

           DateTimeFormatter dataBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            List<Venda> coletar = carrinho.stream()
            .filter(c -> c.getDataVenda().compareTo(inicio) >= 0 && c.getDataVenda().compareTo(fim) <= 0)
            .collect(Collectors.toList());


            double somatorio = 0;

            for (Venda m : coletar) {
                somatorio += getValor();
            }
            double media = somatorio/coletar.size();

            // DoubleSummaryStatistics mediav = coletar.stream()
            // .collect(Collectors.summarizingDouble(Produto::getValor));

            System.out.printf("\n| Data início: %s", String.valueOf(dataBR.format(inicio)));
             System.out.printf("\n| Data fim: %s", String.valueOf(dataBR.format(fim)));
    
            System.out.println(AMARELO + "\n\n------- INFORMAÇÕES ADICIONAIS --------\n\n" + RESET);
            System.out.printf("| A media dos valores do período " + String.valueOf(dataBR.format(inicio)) + 
            " ao período " +  String.valueOf(dataBR.format(fim)) + " foi de R$: %.2f", media, "\n");

            coletar.forEach(c -> System.out.print(c.toString()));
            
        } catch (Exception e) {
            System.out.println("Ação impossível de executar!");
        }
    }

    @Override
    public String toString() {
        return "\n\nVENDA DO LIVRO:\n| Nome: " + produtoVendido + "\n| Quantidade: "
         + qtdVendida + "\n| Data: " + String.valueOf(dataBR.format(dataVenda)) + "\n| Valor: " + getValor() + "\n| Valor Total: " + getValortotal();
    }


}
