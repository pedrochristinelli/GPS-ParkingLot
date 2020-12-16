package Model.Entities.Funcionarios;

public class Administrador extends  Funcionario{
    public Administrador(String cpf, String nome, String senha, String telefone, String endereco, Enum<Efuncao> funcao) {
        super(cpf, nome, senha, telefone, endereco, funcao);
    }

    public Administrador(String cpf, String nome, String senha, String telefone, String endereco, Enum<Efuncao> funcao, int id) {
        super(cpf, nome, senha, telefone, endereco, funcao, id);
    }

    public Administrador(){}
}
