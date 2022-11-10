import Erp.Models.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("usuario@email.com", "senha123");
        Estoque.getUsuarios().add(usuario);

        Scanner sc = new Scanner(System.in);
        int op1, op2, opMarketplace;

        Produto produtoTeste = new Produto();
        Cliente clienteTeste = new Cliente();
        Categoria categoriaTeste = new Categoria();
        Anuncio anuncioTeste = new Anuncio();

        produtoTeste.testeProdutos();
        clienteTeste.testeClientes();
        categoriaTeste.testeCategoria();
        anuncioTeste.testeAnuncio();

        System.out.println("*----------Menu inicial----------*");
        System.out.println("| Atendente ou cliente?          |");
        System.out.println("| 1) Atendente                   |");
        System.out.println("| 2) Cliente                     |");
        System.out.println("| 0) Sair                        |");
        System.out.println("*--------------------------------*");

        op1 = sc.nextInt();

        switch(op1)
        {
            case 0: {
                System.out.println("Finalizando o sistema...");
                System.exit(0);
            }

            case 1: {
                Atendente atendente = new Atendente();
                System.out.println("Digite a senha para acessar como atendente: ");
                if(!sc.next().equals(atendente.getPassword())){
                    System.out.println("ERRO: Senha incorreta!");
                    break;
                }

                do {
                    //menu atendente
                    System.out.println("*-------------Menu---------------*");
                    System.out.println("| Digite uma das opções abaixo:  |");
                    System.out.println("| 1) Cadastrar Cliente           |"); // Feito função e testado
                    System.out.println("| 2) Cadastrar Produto           |"); // Feito função e testado
                    System.out.println("| 3) Cadastrar Categoria         |"); // Feito função e testado
                    System.out.println("| 4) Cadastrar Marketplace       |"); // Feito função e testado
                    System.out.println("| 5) Cadastrar Anuncio           |"); // Feito função e testado
                    System.out.println("| 6) Fechar a compra             |"); // Feito função e testado
                    System.out.println("| 7) Adicionar ao Carrinho       |"); // Feito função e testado
                    System.out.println("| 8) Visualizar Produtos         |"); // Feito função e testado
                    System.out.println("| 9) Visualizar Categorias       |"); // Feito função e testado
                    System.out.println("| 10) Visualizar Carrinho        |"); // Feito função e testado
                    System.out.println("| 11) Visualizar Anuncios        |"); // Feito função e testado
                    System.out.println("| 12) Visualizar Vendas          |"); // Feito função e testado
                    System.out.println("| 0) Sair                        |");
                    System.out.println("*--------------------------------*");

                    op2 = sc.nextInt();

                    Marketplace lojaFisica = new Marketplace(0, "Loja Fisica", null);

                    switch (op2) {
                        case 0:
                        {
                            System.exit(0);
                            break;
                        }

                        case 1: {
                            Cliente cliente = new Cliente();
                            cliente.cadastrarCliente(sc);

                            Estoque.listarClientes();
                            break;
                        }
                        case 2: {
                            Produto produto = new Produto();
                            produto.cadastrarProduto(sc);
                            Estoque.cadastrarProduto(produto);
                            break;
                        }

                        case 3: {
                            Categoria cat = new Categoria();
                            cat.cadastrarCategoria(sc);
                            break;
                        }

                        case 4: {
                            Marketplace mp = new Marketplace();
                            mp.cadastrarMarketplace(sc);
                            break;
                        }

                        case 5: {
                            Anuncio anuncio = new Anuncio();
                            anuncio.cadastrarAnuncio(sc);
                        }
                        case 6: {
                            System.out.println("Digite o CPF do cliente: ");
                            String cpf = sc.next();

                            Cliente c = new Cliente();
                            c = c.validaCliente(cpf);

                            Carrinho carVenda = Carrinho.verificaCarrinho(c, lojaFisica);

                            if(carVenda == null) {
                                System.out.println("O carrinho esta vazio");
                                break;
                            }

                            System.out.println("Digite a forma de pagamento (0 - CARTAO / 1 - DINHEIRO): ");

                            Venda v = new Venda();
                            v.gerarVenda(carVenda, sc.nextInt(), sc);
                        }
                        case 7: {
                            Cliente cliente = new Cliente();
                            String cpfCliente;

                            System.out.println("Digite o cpf do cliente: ");
                            cpfCliente = sc.next();
                            cliente = cliente.validaCliente(cpfCliente);

                            if (cliente != null) //Se encontrar um cliente
                            {
                                System.out.println(cliente.toString());
                                Estoque.listarProdutos();
                                int op;
                                do{
                                    int qtde = 0;
                                    String sku;

                                    System.out.println("Deseja adicionar um item ao carrinho?");
                                    System.out.println("0 = Não\n1 = Sim");
                                    op = sc.nextInt();

                                    switch (op)
                                    {
                                        case 1:
                                        {
                                            Carrinho car = new Carrinho();
                                            ItemCarrinho ic = new ItemCarrinho();
                                            Produto produto = new Produto();

                                            //adicionar item carrinho
                                            boolean achou = false;

                                            while(!achou){
                                                // verificar se sku existe
                                                System.out.println("\nDigite o SKU do produto a ser adicionado ao carrinho: ");
                                                sku = sc.next();
                                                produto = produto.validaProduto(sku);
                                                if(produto != null)
                                                {
                                                    System.out.println(produto);
                                                    if(produto.getQtde() > 0){
                                                        achou = true;
                                                    }
                                                }
                                            }

                                            boolean qtdeDisp = false; //verificar se a quantidade é válida
                                            while(!qtdeDisp) {
                                                System.out.println("Digite a quantidade que deseja adicionar ao carrinho: ");
                                                qtde = sc.nextInt();
                                                qtdeDisp = produto.verificaQtdeDisponivel(produto, qtde);
                                            }

                                            ic = ic.cadastrarItemCarrinho(produto, qtde);
                                            car.adicionarAoCarrinho(ic, cliente, lojaFisica);
                                            break;
                                        }
                                        case 0:
                                        {
                                            //ir para o pagamento
                                            break;
                                        }
                                        default: {
                                            System.out.println("ERRO: Opcao invalida!\nSelecione uma das opcoes");
                                            break;
                                        }
                                    }
                                } while(op != 0);
                            }

                            break;
                        }

                        case 8:
                        {
                            Estoque.listarProdutos();
                            break;
                        }

                        case 9:
                        {
                            Estoque.listarCategorias();
                            break;
                        }

                        case 10:
                        {
                            Carrinho car1 = new Carrinho();
                            Cliente cli = new Cliente();

                            System.out.println("Digite o cpf do cliente: ");
                            String cpf = sc.next();

                            cli = cli.validaCliente(cpf);
                            if(cli == null){
                                System.out.println("Cliente nao existe");
                            } else {
                                car1 = car1.verificaCarrinho(cli, lojaFisica);
                                if (car1 != null){
                                    System.out.println(car1);
                                    car1.listarItensCarrinho(car1);
                                }
                            }
                            break;
                        }

                        case 11:
                            Estoque.listarAnuncios();
                            break;
                        case 12:
                            System.out.println("Digite o cpf do cliente: ");
                            String cpf = sc.next();
                            Estoque.listarVendasPorCliente(cpf);
                            break;
                        default: {
                            System.out.println("ERRO: Opção inválida!\nSelecione uma das opções");
                            break;
                        }
                    }
                } while(op2 != 0);
                break;
            } //Fim menu Atendente

            case 2: //menu cliente
            {
                Marketplace mp = new Marketplace();
                Usuario logado = new Usuario();

                mp.testeMarketPlaces();

                System.out.println("*----------Marketplaces----------*");
                mp.listarMarketplaces();
                System.out.println("*--------------------------------*");

                opMarketplace = sc.nextInt();
                mp.setId(opMarketplace);

                int op3 = 0;

                // LOGIN
                System.out.println("*-------------Menu---------------*");
                System.out.println("| Digite uma das opções abaixo:  |");
                System.out.println("| 1) Cadastrar                   |");
                System.out.println("| 2) Fazer login                 |");
                System.out.println("| 0) Sair                        |");
                System.out.println("*--------------------------------*");

                op3 = sc.nextInt();
                switch (op3) {
                    case 0: {
                        System.exit(0);
                        break;
                    }
                    case 1: {
                        Cliente cliente = new Cliente();
                        cliente.cadastrarCliente(sc);
                        break;
                    }
                    case 2: {
                        Usuario u = new Usuario();
                        System.out.println("Digite o email: ");
                        String email = sc.next();

                        System.out.println("Digite a senha: ");
                        String senha = sc.next();

                        logado = u.efetuarLogin(email, senha);

                        if(logado == null) {
                            System.out.println("O email ou senha esta incorreto!");
                            break;
                        }

                        System.out.println("Login feito com sucesso!");
                        break;
                    }
                    default: {
                        System.out.println("ERRO: Opção inválida!\nSelecione uma das opções");
                        break;
                    }
                }
                    if(op3 == 0 || logado == null) break;

                int opMenuCliente = 0;

                Cliente cli1 = new Cliente();

                System.out.println("Insira seu cpf:");
                String cpf = sc.next();
                cli1 = cli1.validaCliente(cpf);
                do {
                    //menu cliente
                    System.out.println("*-------------Menu---------------*");
                    System.out.println("| Digite uma das opções abaixo:  |");
                    System.out.println("| 1) Visualizar Anuncios         |"); // Função feita e testada
                    System.out.println("| 2) Visualizar Carrinho         |"); //
                    System.out.println("| 3) Adicionar ao Carrinho       |"); // Função feita e testado
                    System.out.println("| 4) Alterar Marketplace         |");
                    System.out.println("| 5) Finalizar compra            |");
                    System.out.println("| 0) Sair                        |");
                    System.out.println("*--------------------------------*");

                    opMenuCliente = sc.nextInt();

                    Marketplace market = new Marketplace();

                    market = market.verificarMarketplaces(mp.getId());

                    if (cli1 == null) break;

                    switch (opMenuCliente){
                        case 1:
                        {
                            market.listarAnuncios();
                            break;
                        }

                        case 2:
                        {
                            Carrinho car = new Carrinho();
                            car.verificaCarrinho(cli1, market);
                            car.listarItensCarrinho(car);
                            break;
                        }

                        case 3:
                        {
                            Estoque.listarProdutos();
                            int op;
                            do {
                                int qtde = 0;
                                String sku;

                                System.out.println("Deseja adicionar um item ao carrinho?");
                                System.out.println("0 = Não\n1 = Sim");
                                op = sc.nextInt();

                                switch (op)
                                {
                                    case 1:
                                    {
                                        Carrinho car = new Carrinho();
                                        car = car.verificaCarrinho(cli1, market);
                                        ItemCarrinho ic = new ItemCarrinho();
                                        Produto produto = new Produto();

                                        //adicionar item carrinho
                                        boolean achou = false;

                                        while(!achou) {
                                            // verificar se sku existe
                                            System.out.println("\nDigite o SKU do produto a ser adicionado ao carrinho: ");
                                            sku = sc.next();
                                            produto = produto.validaProduto(sku);
                                            if(produto != null)
                                            {
                                                System.out.println(produto);
                                                if(produto.getQtde() > 0){
                                                    achou = true;
                                                }
                                            }
                                        }

                                        boolean qtdeDisp = false; //verificar se a quantidade é válida

                                        while(!qtdeDisp) {
                                            System.out.println("Digite a quantidade que deseja adicionar ao carrinho: ");
                                            qtde = sc.nextInt();
                                            qtdeDisp = produto.verificaQtdeDisponivel(produto, qtde);
                                        }

                                        ic = ic.cadastrarItemCarrinho(produto, qtde);
                                        car.adicionarAoCarrinho(ic, cli1, market);
                                        break;
                                    }
                                    case 0:
                                        break;
                                    default: {
                                        System.out.println("ERRO: Opcao invalida!\nSelecione uma das opcoes");
                                        break;
                                    }
                                }
                            } while(op != 0);

                        }
                    }

                } while(opMenuCliente != 0);

                break;
            }
            default: {
                System.out.println("ERRO: Opção inválida!\nSelecione uma das opções");
                break;
            }
        }
    }

    public static int login(Scanner sc, Usuario logado){
        int op2 = 0;



        return op2;
    }
}
