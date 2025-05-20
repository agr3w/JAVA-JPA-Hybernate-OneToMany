package app;

import entity.Departamento;
import entity.Funcionario;
import repository.DepartamentoRepository;
import repository.FuncionarioRepository;

import java.util.List;
import java.util.Scanner;

public class AppFuncionarioDepartamento {

    public static void main(String[] args) {

        DepartamentoRepository departamentoRepository = new DepartamentoRepository();
        FuncionarioRepository funcionarioRepository = new FuncionarioRepository();

        Scanner scanner = new Scanner(System.in);

        boolean sair = false; 

        while (!sair) {
            System.out.println("\n==================== MENU ====================");
            System.out.println("1 - adicionar departamento e funncionario");
            System.out.println("2 - Mostrar todos os departamentos e funcionarios");
            System.out.println("3 - sair");
            System.out.print("escolha uma opçao: ");
            
            int opcao = Integer.parseInt(scanner.nextLine());  

            switch (opcao) {
                case 1:
                    adicionarDepartamentoEFuncionario(departamentoRepository, funcionarioRepository, scanner);
                    break;
                case 2:
                    visualizarDepartamentosEFuncionarios(departamentoRepository);
                    break;
                case 3:
                    System.out.println("Saindo...");
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }

    private static void adicionarDepartamentoEFuncionario(DepartamentoRepository departamentoRepository, 
                                                           FuncionarioRepository funcionarioRepository, 
                                                           Scanner scanner) {
        // criando o Departamento
        System.out.print("Digite o nome do departamento: ");
        String nomeDepartamento = scanner.nextLine(); 
        Departamento departamento = new Departamento();
        departamento.setNome(nomeDepartamento);
        departamentoRepository.inserir(departamento);  

        System.out.print("Quantos funcionários devemos cadastrar no departamento '" + nomeDepartamento + "'? ");
        int qtdFuncionarios = Integer.parseInt(scanner.nextLine()); //quantidade de loops vai ser feita

        for (int i = 1; i <= qtdFuncionarios; i++) {
            System.out.print("Digite o nome do funcionário " + i + ": ");
            String nomeFuncionario = scanner.nextLine(); // lemos o nome do funcinnario
            Funcionario funcionario = new Funcionario();
            funcionario.setNome(nomeFuncionario);
            funcionario.setDepartamento(departamento);
            funcionarioRepository.inserir(funcionario);  // salva o funcionario

            departamento.getFuncionarios().add(funcionario); //adiciona cada funcionario na lista que temos na entidade departamento
        }

        departamentoRepository.atualizar(departamento); //da um merge em tudo
        System.out.println("Departamento e Funcionários adicionados com sucesso!");
    }

    private static void visualizarDepartamentosEFuncionarios(DepartamentoRepository departamentoRepository) {
        System.out.println("\n==================== Departamentos ====================");

        List<Departamento> departamentos = departamentoRepository.listarTodos();

        if (departamentos.isEmpty()) {
            System.out.println("Nenhum departamento encontrado.");
        } else {
            for (Departamento departamento : departamentos) {
                System.out.println("\nDepartamento: " + departamento.getNome());
                System.out.println("Id do Departamento: " + departamento.getId());
                System.out.println("Funcionários neste Departamento:");

                for (Funcionario f : departamento.getFuncionarios()) {
                    System.out.println("  - Nome: " + f.getNome() + ", Id: " + f.getId());
                }
            }
        }

        System.out.println("\nFim da visualização.");
    }
}
