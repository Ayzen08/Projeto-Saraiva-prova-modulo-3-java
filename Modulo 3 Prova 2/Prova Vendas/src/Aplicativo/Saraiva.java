package Aplicativo;
import java.time.LocalDate;
import java.util.Scanner;
import SuperClasse.Produto;
import Classes.*;

public class Saraiva {
     static final String RESET = "\u001B[0m";
     static final String AMARELO = "\u001B[33m";
    public static void main(String[] args) throws Exception {

    Scanner leia = new Scanner(System.in);
    Produto livro = new Produto();
    Venda venda = new Venda();
    livro.salvos();
    boolean ciclo = true;

    do{

        menu();
        String opcao = leia.nextLine();
        LimparTela();

        switch (opcao) {
            case "1": // 1 – Incluir produto

            System.out.println(AMARELO + " ------- INCLUIR OU REMOVER LIVROS -------" + RESET);
            System.out.println("|1| Incluir livro!");
            System.out.println("|2| Remover livro(s)!");
            System.out.print("Opção desejada: ");
            String opcao2 = leia.nextLine();
            
                    switch (opcao2) {
                        case "1":
                            System.out.println(AMARELO + "\n\n------- CADASTRO DO LIVRO -------" + RESET);
                            System.out.print("\nNome: ");
                            String nome = leia.nextLine();
                            
                            System.out.print("\nValor do livro: ");
                            double valor = leia.nextDouble(); 
                                if (valor <= 0) {
                            System.out.println("O valor do produto não pode ser gratuito ou com valor negativo+!");
                            leia.nextLine();
                                    break;
                            }    
                            int codigo = livro.codigo(); 
                            
                            System.out.print("\nQuantidade deste livro em estoque: ");
                            int quantidade = leia.nextInt();
                                if (valor <= 0) {
                                System.out.println("O valor do produto não pode ser grátis!");
                                leia.nextLine();
                                break;
                            }    
                            leia.nextLine();
                            Produto book = new Produto(nome, valor, codigo, quantidade);
                
                            livro.incluir(book);
                            System.out.println("\nLivro cadastrado!");
                            System.out.println("Pressione enter para voltar ao menu!");
                            leia.nextLine();
                                break;
                        case "2":
                        System.out.println(AMARELO + "\n\n------- REMOVER LIVROS -------" + RESET);
                        System.out.println("|1| Remover livro!");
                        System.out.println("|2| Remover todos os livros!");
                        System.out.print("Opção desejada: ");

                            String opcao3 = leia.nextLine();
                            switch (opcao3) {
                                case "1":
                                System.out.println(AMARELO + "\n\n------- REMOVER LIVRO -------" + RESET);
                                System.out.print("\nDigite o nome do livro que deseja remover: ");
                                String nomeremover = leia.nextLine();
                                livro.remover(nomeremover);
                                System.out.println("\nLivro removido!");
                                System.out.println("Pressione enter para voltar ao menu!");
                                leia.nextLine();

                                    break;
                            
                                case "2":
                                System.out.println(AMARELO + "\n\n------- REMOVER TODOS OS LIVROS -------" + RESET);
                                System.out.println("Lista limpa!");
                                System.out.println("Pressione enter para voltar ao menu!");
                                leia.nextLine();
                                livro.clear();
                                    break;
                            
                                default:
                                System.out.print("Esta opção não existe, ");
                                Thread.sleep(1000);
                                System.out.print("tente novamente!");
                    
                                leia.nextLine();
                                    break;
                            }
                            break;
                    
                        default: 
                        System.out.print("Esta opção não existe, ");
                        Thread.sleep(1000);
                        System.out.print("tente novamente!");
            
                        leia.nextLine();
                            break;
            }
                break;

            case "2":
            
            System.out.println(AMARELO + "--------- CONSULTAR LIVRO -------- " + RESET);
            System.out.print("\nBuscar livro: ");
            livro.buscar(leia.nextLine());

            System.out.print("\nPressione enter para voltar ao menu!");
            leia.nextLine();

            break;

            case "3": // 3 – Listagem de produtos
            livro.listar();
            System.out.print("\nPressione enter para voltar ao menu!");
            leia.nextLine();
            break;

            case "4": // 4 – Vendas por período – detalhado
            System.out.println(AMARELO + "\n -------- RELATÓRIO DE VENDAS --------" + RESET);
            System.out.print("Dia de início: ");
            String iniciostring = leia.nextLine();
            System.out.print("Dia final: ");
            String finalstring = leia.nextLine();

                try {
                venda.relatorio(LocalDate.parse("2022-03-" + iniciostring),
                LocalDate.parse("2022-03-" + finalstring));
                    
                } catch (Exception e) {
                  System.out.println("Não foi possível completar a ação");
                }


            System.out.print("\n\nPressione enter para voltar ao menu!");
            leia.nextLine();
            break;

            case "5": //5 Realizar Venda
            venda.realizarvenda(venda, livro);
        //     Produto nomevendido = new Produto();
        //     Venda v = new Venda();
        //     Boolean encontrei = false;

        //     System.out.println(AMARELO + "\n\n--------- REALIZAR VENDA ---------\n\n" + RESET);
        //     System.out.print("Informe o nome do livro vendido: ");
        //     nomevendido.setNome(leia.nextLine());
        //     String livrobuscado = nomevendido.getNome();

        //     for (Produto i: livro.livraria) {
        //         if (i.getNome().equals(nomevendido.getNome())) {
        //             nomevendido = i;
        //             encontrei = true;
        //             break;
        //         }

        //     }
        //    if (encontrei == false) {
        //        System.out.println("O livro" + livrobuscado + " não existe!");
        //        System.out.print("\nPressione enter para voltar ao menu!");
        //        leia.nextLine();
        //        break;
        //    } else {
        //     System.out.print("Informe a quantidade vendida: ");
        //     try {
        //         venda.setQtdVendida(leia.nextInt());
        //     } catch (Exception e) {
        //         System.out.println("Digitalização inválida, tente novamente!");
        //         System.out.print("\nPressione enter para voltar ao menu!");
        //         leia.nextLine();
        //         break;
        //     } 
        //     venda.analiseEstoque(nomevendido, venda.getQtdVendida());

        //     try {
        //         int dia = 1;
        //         if (dia <= 9) {
        //             v = new Venda(LocalDate.parse("2022-04-0" +dia), nomevendido, venda.getQtdVendida());
        //             venda.incluir(v);
        //              dia++;
        //         }
        //         if (dia >= 10 && dia <= 30) {
        //             v = new Venda(LocalDate.parse("2022-04-" +dia), nomevendido, venda.getQtdVendida());
        //             venda.incluir(v);
        //               dia++;
    
        //         }else if(dia > 31) {
        //             System.out.println("Esta data não existe!");
        //             System.out.print("\nPressione enter para voltar ao menu!");
        //             leia.nextLine();
        //         }
                   
        //        } catch (Exception e) {
        //            System.out.println("Não é possível reconhecer a data, tente novamente!");
        //            System.out.print("\nPressione enter para voltar ao menu!");
        //            leia.nextLine();
        //    }

        //    }
        System.out.print("\nPressione enter para voltar ao menu!");
        leia.nextLine();
            break;
            
            case "6": // 6 – Sair

            System.out.print("Fim do programa ");
            Thread.sleep(700);
            System.out.print(" :'(");
            limpo();
            ciclo = false;
            break;
            
            default:
            System.out.print("Esta opção não existe, ");
            Thread.sleep(700);
            System.out.print("tente novamente!");

            leia.nextLine();
                break;
        }
    } while (ciclo == true);        


        leia.close();

    }
        public static void LimparTela(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static void menu(){
        LimparTela();
        System.err.println(AMARELO + "-------- BEM-VINDO A SARAIVA! ---------" + RESET);
        System.out.println("|1| Incluir ou Remover livro");
        System.out.println("|2| Consultar livro");
        System.out.println("|3| Biblioteca");
        System.out.println("|4| Relatório");
        System.out.println("|5| Realizar Venda");
        System.out.println("|6| Sair");
        System.out.print("Opção desejada: ");
        
    }

    public static void limpo(){
        for (int i = 0; i < 10; i++) {
            System.out.println(" "); {
                
            }
        }
    }
  


}
